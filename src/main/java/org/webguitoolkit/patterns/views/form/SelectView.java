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
package org.webguitoolkit.patterns.views.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.event.IActionListener;
import org.webguitoolkit.ui.controls.form.AssociationSelectModel;
import org.webguitoolkit.ui.controls.form.Compound;
import org.webguitoolkit.ui.controls.form.DefaultSelectModel;
import org.webguitoolkit.ui.controls.form.ILabel;
import org.webguitoolkit.ui.controls.form.ISelect;
import org.webguitoolkit.ui.controls.form.Multiselect;
import org.webguitoolkit.ui.controls.form.Select;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.controls.util.conversion.ConvertUtil.ConversionException;

public class SelectView extends AbstractView {

	public SelectView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {

		// button to show the source code of this class
		factory.createButton(viewConnector, "./images/iconText.gif", "Source Code", "Source Code", new SourceView(factory, viewConnector,
				this.getClass().getName()));

		// ############ Simple key value pairs ############

		// key value pairs
		List<String[]> keyValues = new ArrayList<String[]>();
		keyValues.add(new String[] { "1", "one" });
		keyValues.add(new String[] { "2", "two" });
		keyValues.add(new String[] { "3", "three" });
		keyValues.add(new String[] { "4", "four" });
		keyValues.add(new String[] { "5", "five" });

		// create model
		DefaultSelectModel keyValueModel = new DefaultSelectModel();
		keyValueModel.setOptions(keyValues);

		// create controls
		ITableLayout layout = factory.createTableLayout(viewConnector);
		ILabel selectLabel = factory.createLabel(layout, "Key Value select");
		final ISelect keyValueSelect = factory.createSelect(layout, "selectItem");
		keyValueSelect.setDescribingLabel(selectLabel);
		keyValueSelect.setActionListener(new ChangeListener(keyValueSelect));
		keyValueSelect.setModel(keyValueModel);
		keyValueSelect.loadList();

		layout.newRow();

		// ############ Simple single value list ############

		// simple value
		List<String> singleValues = new ArrayList<String>();
		singleValues.add("one");
		singleValues.add("two");
		singleValues.add("three");
		singleValues.add("four");
		singleValues.add("five");

		// create model
		DefaultSelectModel singleValueModel = new DefaultSelectModel();
		singleValueModel.setSingleValueList(singleValues);

		// create controls
		selectLabel = factory.createLabel(layout, "Single Value Select");
		final ISelect singleValueSelect = factory.createSelect(layout, "selectItem");
		singleValueSelect.setDescribingLabel(selectLabel);
		singleValueSelect.setPrompt("Please select ...");
		singleValueSelect.setModel(singleValueModel);
		singleValueSelect.loadList();
		((Select)singleValueSelect).changeMode(Compound.MODE_READONLY);
		layout.newRow();

		// ############ Assosiation select ############

		// complex values
		List<SampleObject> associationValues = new ArrayList<SampleObject>();
		associationValues.add(new SampleObject("0", "Peter", "Zaretzke"));
		associationValues.add(new SampleObject("1", "Horst", "Timcke"));
		associationValues.add(new SampleObject("2", "Martin", "Hermann"));
		associationValues.add(new SampleObject("3", "Thorsten", "Springhart"));
		associationValues.add(new SampleObject("4", "Wolfram", "Kaiser"));
		associationValues.add(new SampleObject("5", "Jochen", "Gugel"));
		associationValues.add(new SampleObject("6", "Lars", "Brössler"));

		// create model
		AssociationSelectModel associationModel = new AssociationSelectModel(true);
		associationModel.setDisplayProperty("displayName");
		associationModel.setOptions(associationValues);

		// create controls
		selectLabel = factory.createLabel(layout, "Association Select");
		final ISelect assosiationSelect = factory.createSelect(layout, "selectItem");
		assosiationSelect.setDescribingLabel(selectLabel);
		assosiationSelect.setModel(associationModel);
		assosiationSelect.setPrompt("Please select ...");
		assosiationSelect.loadList();
		layout.newRow();

		factory.createButton(layout, null, "Check", "Check", new IActionListener() {
			public void onAction(ClientEvent event) {
				String msg = "Key Value Select: " + keyValueSelect.getValue() + "<br>";
				msg += "Single Value Select: " + singleValueSelect.getValue() + "<br>";
				try {
					SampleObject selected = (SampleObject)assosiationSelect.getConvertedValue();
					if (selected != null)
						msg += "Association Select: " + selected.getFirstName() + " " + selected.getLastName() + "<br>";
					else
						msg += "Association Select: No user selected <br>";
				}
				catch (ConversionException e) {
					getPage().sendInfo(e.getMessage());
				}
				getPage().sendInfo(msg);
			}
		});
		layout.newRow();

		// ############ Simple single value list ############

		// single values
		List<String> firstValues = new ArrayList<String>();
		firstValues.add("1");
		firstValues.add("2");
		firstValues.add("3");
		firstValues.add("4");
		firstValues.add("5");

		// create model
		DefaultSelectModel firstValueModel = new DefaultSelectModel();
		firstValueModel.setSingleValueList(firstValues);

		// create controls
		selectLabel = factory.createLabel(layout, "Dependent Selects");
		layout.newRow();
		ISelect firstValueSelect = factory.createSelect(layout, "selectItem");
		firstValueSelect.setDescribingLabel(selectLabel);
		firstValueSelect.setPrompt("Please select ...");
		firstValueSelect.setModel(firstValueModel);
		firstValueSelect.loadList();

		ISelect secondValueSelect = factory.createSelect(layout, "selectItem");
		secondValueSelect.setDescribingLabel(selectLabel);
		secondValueSelect.setPrompt("Please select ...");
		secondValueSelect.changeMode(Compound.MODE_READONLY);
		firstValueSelect.setActionListener(new FirstValueChangedListener(firstValueSelect, secondValueSelect));

		layout.newRow();

		// ############ MultiValue list ############

		// create controls
		factory.createLabel(layout, "Multi-Selectbox Sample");
		layout.getCurrentCell().setStyle("font-weight:bold;");
		layout.newRow();
		Multiselect multiselect = new Multiselect();
		layout.add(multiselect);
		multiselect.setRows(5);
		multiselect.getStyle().addStyleAttributes("vertical-align:top;width:150px;");
		multiselect.setProperty("productRoots");
		multiselect.setMaxSelectedOptions(Multiselect.MAX_SELECTED_OPTIONS_UNLIMITED);
		layout.getCurrentCell().setWidth(150);

		// load data
		multiselect.loadList(keyValues);
		multiselect.redraw();

	}

	public class ChangeListener implements IActionListener {
		private ISelect monitoredSelect;

		public ChangeListener(ISelect monitoredSelect) {
			super();
			this.monitoredSelect = monitoredSelect;
		}

		public void onAction(ClientEvent event) {
			getPage().sendInfo("Selected: " + monitoredSelect.getValue());
		}
	}

	public class FirstValueChangedListener implements IActionListener {
		private ISelect monitoredSelect;
		private ISelect targetSelect;

		public FirstValueChangedListener(ISelect monitoredSelect, ISelect targetSelect) {
			super();
			this.monitoredSelect = monitoredSelect;
			this.targetSelect = targetSelect;
		}

		public void onAction(ClientEvent event) {
			String firstValue = monitoredSelect.getValue();

			if (StringUtils.isEmpty(firstValue)) {
				List<String> secondValues = new ArrayList<String>();

				// create model
				DefaultSelectModel secondValueModel = new DefaultSelectModel();
				secondValueModel.setSingleValueList(secondValues);

				// load data
				targetSelect.setModel(secondValueModel);
				targetSelect.loadList();

				// readonly if nothing is selected
				targetSelect.changeMode(Compound.MODE_READONLY);
			}
			else {
				// single value pairs
				List<String> secondValues = new ArrayList<String>();
				secondValues.add(firstValue + ".1");
				secondValues.add(firstValue + ".2");
				secondValues.add(firstValue + ".3");
				secondValues.add(firstValue + ".4");
				secondValues.add(firstValue + ".5");

				// create model
				DefaultSelectModel secondValueModel = new DefaultSelectModel();
				secondValueModel.setSingleValueList(secondValues);

				// load data
				targetSelect.changeMode(Compound.MODE_EDIT);
				targetSelect.setModel(secondValueModel);
				targetSelect.loadList();
			}
		}

	}

	public class SampleObject implements Serializable {

		private String id;
		private String firstName;
		private String lastName;

		public SampleObject(String id, String firstName, String lastName) {
			super();
			this.firstName = firstName;
			this.id = id;
			this.lastName = lastName;
		}

		public String getId() {
			return id;
		}

		public String getFirstName() {
			return firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public String getDisplayName() {
			return lastName + ", " + firstName;
		}
	}

}
