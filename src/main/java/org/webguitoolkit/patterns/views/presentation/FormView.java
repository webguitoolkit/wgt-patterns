/*
Copyright 2008 Endress+Hauser Infoserve GmbH&Co KG
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
implied. See the License for the specific language governing permissions
and limitations under the License.
*/
package org.webguitoolkit.patterns.views.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.DataBag;
import org.webguitoolkit.ui.base.IDataBag;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.form.AbstractButtonBarListener;
import org.webguitoolkit.ui.controls.form.AssociationSelectModel;
import org.webguitoolkit.ui.controls.form.IButtonBar;
import org.webguitoolkit.ui.controls.form.ICompound;
import org.webguitoolkit.ui.controls.form.ILabel;
import org.webguitoolkit.ui.controls.form.IMultiselect;
import org.webguitoolkit.ui.controls.form.ISelect;
import org.webguitoolkit.ui.controls.form.IText;
import org.webguitoolkit.ui.controls.form.ITextarea;
import org.webguitoolkit.ui.controls.form.Multiselect;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.controls.util.conversion.ConvertUtil.ConversionException;
import org.webguitoolkit.ui.controls.util.validation.ValidatorUtil;

public class FormView extends AbstractView {

	private static final String KEY_IS_PUBLIC = "project.details.projectname@Is Public";
	private static final String KEY_PROJECT_DESCRIPTION = "project.details.projectname@Project Description";
	private static final String KEY_PROJECT_NAME = "project.details.projectname@Project Name";
	private static final String KEY_PROJECT_STATUS = "project.details.status@Project Status";

	private static final String KEY_PROJECT_COUNTRY = "project.details.country@Country (Multiselect Beta)";

	private ICompound compound;
	private ILabel result;
	private Project project = new Project("No", "Name", false);
	private ISelect projectStatus = null;
	private IMultiselect multi;

	public FormView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		// button to show the source code of this class
		factory.createButton(viewConnector, "./images/iconText.gif", "Source Code", "Source Code", new SourceView(factory, viewConnector,
				this.getClass().getName()));

		compound = factory.createCompound(viewConnector);

		compound.setBag(new DataBag(""));

		IButtonBar buttonbar = factory.createButtonBar(compound, "edit,delete,cancel,save,new", new ButtonBarListener(compound));
		// example how to add controls to the ButtonBar
		ILabel l = factory.createLabel(buttonbar, "I'm a control in the ButtonBar");

		ITableLayout layout = factory.createTableLayout(compound);

		l = factory.createLabel(layout, KEY_PROJECT_NAME);
		IText t = factory.createText(layout, "name", l);
		t.addValidator(ValidatorUtil.MANDATORY_VALIDATOR);
		layout.newRow();

		factory.createLabel(layout, KEY_PROJECT_DESCRIPTION);
		ITextarea desc = factory.createTextarea(layout, "description");
		desc.setRows(3);
		desc.addValidator(ValidatorUtil.MANDATORY_VALIDATOR);
		desc.setMaxlength(32);
		layout.newRow();

		factory.createLabel(layout, KEY_IS_PUBLIC);
		factory.createCheckBox(layout, "public");
		layout.newRow();

		// add new select for project status
		factory.createLabel(layout, KEY_PROJECT_STATUS);
		projectStatus = factory.createSelect(layout, "projectsts");
		projectStatus.addValidator(ValidatorUtil.MANDATORY_VALIDATOR);
		Collection<SelectOption> sval = new ArrayList<SelectOption>();
		sval.add(new SelectOption("0", "draft"));
		sval.add(new SelectOption("1", "new"));
		sval.add(new SelectOption("2", "in work"));
		sval.add(new SelectOption("3", "rejected"));
		sval.add(new SelectOption("4", "closed"));
		SelectOption selected = new SelectOption("5", "reopened");
		sval.add(selected);
		sval.add(new SelectOption("6", "other"));

		AssociationSelectModel model = new AssociationSelectModel();
		model.setOptions(sval);
		projectStatus.setModel(model);
		projectStatus.loadList();

		layout.newRow();
		factory.createLabel(layout, KEY_PROJECT_COUNTRY);
		multi = factory.createMultiselect(layout, "multi", 5);
		multi.setMaxSelectedOptions(Multiselect.MAX_SELECTED_OPTIONS_UNLIMITED);
		multi.addValidator(ValidatorUtil.MANDATORY_VALIDATOR);
		// multi.setValue("2,3,7");
		List<String[]> sel = new ArrayList<String[]>();
		sel.add(new String[] { "uk", "UK" });
		sel.add(new String[] { "de", "Germany" });
		sel.add(new String[] { "us", "USA" });
		sel.add(new String[] { "dk", "Denmark" });
		sel.add(new String[] { "fr", "France" });
		sel.add(new String[] { "ug", "Uganda" });
		sel.add(new String[] { "cl", "Chile" });
		sel.add(new String[] { "aus", "Australia" });
		sel.add(new String[] { "cn", "China" });

		// multi.getDefaultModel().setSingleValueList(sel);
		multi.loadList(sel);

		layout.newRow();

		factory.createLabel(layout, "result@Result:");
		result = factory.createLabel(layout, "");
		IDataBag bag = new DataBag(project);
		bag.addProperty("projectsts", selected);
		bag.addProperty("multi", "cl,de,us");
		compound.setBag(bag);
		compound.load();
	}

	public class ButtonBarListener extends AbstractButtonBarListener {

		public ButtonBarListener(ICompound compound) {
			super(compound);
		}

		@Override
		public Object newDelegate() {
			return new Project();
		}

		@Override
		public boolean refresh(Object delegate) {
			return true;
		}

		@Override
		public int persist() {
			return SAVE_OK;
		}

		@Override
		public void postSave() {
			// project = (Project) compound.getBag().getDelegate();
			try {
				result.setText(project.toString() + " / " + ((SelectOption)projectStatus.getConvertedValue()).getName() + " / "
						+ multi.getValue());
			}
			catch (ConversionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public boolean delete(Object delegate) {
			return true;
		}
	}

	public class SelectOption implements Serializable {
		String id = null;
		String name = null;

		public SelectOption(String newId, String newName) {
			this.id = newId;
			this.name = newName;
		}

		/**
		 * @return the id
		 */
		public String getId() {
			return id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(String id) {
			this.id = id;
		}

		/**
		 * @return the value
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param value the value to set
		 */
		public void setName(String name) {
			this.name = name;
		}

	}

	public class Project implements Serializable {
		private String name = "";
		private String description = "";
		private boolean isPublic = false;

		public Project() {
		}

		public Project(String name, String description, boolean isPublic) {
			this.name = name;
			this.description = description;
			this.isPublic = isPublic;

		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public boolean isPublic() {
			return isPublic;
		}

		public void setPublic(boolean isPublic) {
			this.isPublic = isPublic;
		}

		@Override
		public String toString() {
			return "Project {" + name + ", " + description + ", " + isPublic + " }";
		}

	}

}
