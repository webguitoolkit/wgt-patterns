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
package org.webguitoolkit.patterns.views.patterns;

/**
 * This sample shows how to create a master-detail view. It will be also shown: 
 * <br>How to initialize the view including multiple tabs.
 * <br>How to load the content of the tab when the tab is changed.
 * <br>How to keep the data shown in the table in sync with the data from the form.
 * 
 * @author peter, martin 
 */
import java.util.ArrayList;
import java.util.List;

import org.webguitoolkit.patterns.prototype.MockData;
import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.DataBag;
import org.webguitoolkit.ui.base.IDataBag;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.form.AbstractButtonBarListener;
import org.webguitoolkit.ui.controls.form.Compound;
import org.webguitoolkit.ui.controls.form.ICompound;
import org.webguitoolkit.ui.controls.form.ILabel;
import org.webguitoolkit.ui.controls.form.IText;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.controls.tab.ITab;
import org.webguitoolkit.ui.controls.tab.ITabStrip;
import org.webguitoolkit.ui.controls.table.ITable;
import org.webguitoolkit.ui.controls.table.ITableColumn;
import org.webguitoolkit.ui.controls.table.Table;
import org.webguitoolkit.ui.controls.util.MasterDetailFactory;
import org.webguitoolkit.ui.controls.util.validation.ValidatorUtil;

public class MasterDetailFactoryView extends AbstractView {

	private ITab summaryTab;
	private ITab detailTab;
	private ICompound summaryCompound;
	private ICompound detailCompound;
	private ITable table;

	public MasterDetailFactoryView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls( WebGuiFactory factory, ICanvas viewConnector ) {
		// button to show the source code of this class
		factory.createButton( viewConnector, "./images/iconText.gif", "Source Code","Source Code", new SourceView( factory, viewConnector, this.getClass().getName() ) );

		ITableLayout layout = factory.createTableLayout(viewConnector);
		
		MasterDetailFactory mdf = new MasterDetailFactory();
		
		//create Master Table
		table = mdf.createTable(layout, "", 6);

		ITableColumn col = factory.createTableColumn(table, "Name", "name", true);
		col.setWidth("50px");

		col = factory.createTableColumn(table, "Role", "role", true);
		col.setWidth("200px");

		layout.newRow();

		//create Detail Tabs
		ITabStrip tabstrip = mdf.createTabStrip(layout);

		tabstrip.getStyle().add("height", "240px"); // TODO :

		summaryTab = factory.createTab(tabstrip, "Summary");

		detailTab = factory.createTab(tabstrip, "Details");

		summaryCompound = mdf.createCompound(summaryTab, null);
		summaryCompound.setBag(new DataBag("")); // TODO

		mdf.createMasterButtonBar(summaryCompound, "edit,delete,cancel,save,new", new ButtonBarListener(summaryCompound));

		ITableLayout summaryLayout = factory.createTableLayout(summaryCompound);

		ILabel label = factory.createLabel(summaryLayout, "Name");
		IText name = factory.createText(summaryLayout, "name", label);

		// check that this field	 has a value. Other converters are available
		name.addValidator(ValidatorUtil.MANDATORY_VALIDATOR);

		summaryLayout.newRow();

		label = factory.createLabel(summaryLayout, "Role");
		factory.createText(summaryLayout, "role", label);

		detailCompound = mdf.createCompound(detailTab, null );
		detailCompound.setBag(new DataBag("")); // TODO

		mdf.createMasterButtonBar(detailCompound, "edit,cancel,save", new ButtonBarListener(detailCompound));

		summaryLayout = factory.createTableLayout(detailCompound);

		label = factory.createLabel(summaryLayout, "Description");
		factory.createText(summaryLayout, "description", label);
		summaryLayout.newRow();

		label = factory.createLabel(summaryLayout, "Detail");
		factory.createText(summaryLayout, "detail", label);
		
		/**
		 * Put some data into the table
		 */
		List<IDataBag> data = new ArrayList<IDataBag>();
		for (int i = 0; i < 20; i++) {
			IDataBag bag = new DataBag(null);
			bag.addProperty("name", MockData.get("Name:name:y"));
			bag.addProperty("role", MockData.get("Role:role:y"));
			bag.addProperty("description", MockData.get("Description:lip:y"));
			bag.addProperty("detail", MockData.get("detail:lip:y"));
			data.add(bag);
		}
		table.getDefaultModel().setTableData(data);
		table.reload();

		// make sure that the table is not empty before calling this!
		if (data.size() > 0)
			((Table) table).callListenerSelectRow(0);

	}

	/**
	 * This extension of the AbstractButtonBarListener is just for demo
	 * purposes.
	 * 
	 * @author i102374
	 * 
	 */
	class ButtonBarListener extends AbstractButtonBarListener {

		private int row;

		public ButtonBarListener(ICompound compound) {
			super(compound);
		}

		@Override
		public Object newDelegate() {
			return null;
		}

		@Override
		public int persist() {
			return SAVE_OK;
		}

		@Override
		public boolean refresh(Object delegate) {
			return true;
		}

		@Override
		public boolean delete(Object delegate) {
			// TODO : mark delegate deleted and commit

			// remove the deleted object from from table
			int row = ((Table) table).getSelectedRowIndex();
			((Table) table).removeAndReload(compound.getBag());
			((Table) table).callListenerSelectRow(row); // TODO : last row handling
			return true;
		}

		@Override
		public void postSave() {
			// make sure that the table is updated in case of a new entry
			if (compound.getMode() == Compound.MODE_NEW) {
				((Table) table).addAndReload(compound.getBag());
			} else {
				// just reload
				table.load();
			}
		}

		@Override
		public void postNew() {
			row = ((Table) table).getSelectedRowIndex();
			table.selectionChange(-1,false);
		}

		@Override
		public void preCancel() {
			if (compound.getMode() == Compound.MODE_NEW) {
				((Table) table).callListenerSelectRow(row);
			}
		}
	}
}
