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
import java.util.List;

import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.DataBag;
import org.webguitoolkit.ui.base.IDataBag;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.controls.table.AbstractTableListener;
import org.webguitoolkit.ui.controls.table.ClassDependentRowHandler;
import org.webguitoolkit.ui.controls.table.ITable;
import org.webguitoolkit.ui.controls.table.ITableColumn;
import org.webguitoolkit.ui.controls.table.ITableFilter;

public class MixedObjectTableView extends AbstractView {

	public MixedObjectTableView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		// button to show the source code of this class
		factory.createButton(viewConnector, "./images/iconText.gif", "Source Code", "Source Code", new SourceView(factory, viewConnector,
				this.getClass().getName()));

		ITableLayout layout = factory.createTableLayout(viewConnector);

		ITable table = factory.createTable(layout, "", 5);

		table.setListener(new TableListener());

		ITableFilter filter = factory.createTableFilter(table);
		filter.setLabel("Type");

		filter = factory.createTableFilter(table);
		filter.setLabel("Customer");

		table.addCheckboxColumn("toDelete", null);
		table.setTitleKey("table.mixed.objects@Mixed Objects");

		ITableColumn col = factory.createTableColumn(table, "Type", "type", true);
		col.setWidth("50px");

		col = factory.createTableColumn(table, "Name", "name", true);
		col.setWidth("200px");

		col = factory.createTableColumn(table, "Description", "description", true);
		col.setWidth("500px");

		ClassDependentRowHandler countryRowhandler = new ClassDependentRowHandler(Country.class, "background-color: #FFCC99;");
		countryRowhandler.addProperyMapping("description", "countryDescription");
		ClassDependentRowHandler cityRowhandler = new ClassDependentRowHandler(City.class, "background-color: #CCFF99;");
		cityRowhandler.addProperyMapping("description", "cityDescription");
		table.addRowHandler(countryRowhandler);
		table.addRowHandler(cityRowhandler);

		List<IDataBag> data = new ArrayList<IDataBag>();
		for (int i = 0; i < 100; i++) {
			Object delegate = null;
			if (i % 3 == 0)
				delegate = new Country("Country " + i, "Large Country " + i);
			else
				delegate = new City("City " + i, "Big city " + i);

			IDataBag bag = new DataBag(delegate);
			bag.addProperty("type", "type " + i);
			bag.addProperty("toDelete", new Boolean(i % 3 == 0));
			data.add(bag);
		}
		table.getDefaultModel().setTableData(data);

		table.reload();
	}

	class TableListener extends AbstractTableListener {

		@Override
		public void onRowSelection(ITable table, int row) {
			// TODO Auto-generated method stub

		}

	}

	public class City implements Serializable {
		private String name;
		private String cityDescription;

		public City(String name, String cityDescription) {
			super();
			this.name = name;
			this.cityDescription = cityDescription;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCityDescription() {
			return cityDescription;
		}

		public void setCityDescription(String cityDescription) {
			this.cityDescription = cityDescription;
		}
	}

	public class Country implements Serializable {
		private String name;
		private String countryDescription;

		public Country(String name, String countryDescription) {
			super();
			this.name = name;
			this.countryDescription = countryDescription;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCountryDescription() {
			return countryDescription;
		}

		public void setCountryDescription(String countryDescription) {
			this.countryDescription = countryDescription;
		}
	}

}
