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
import java.util.Iterator;
import java.util.List;

import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.DataBag;
import org.webguitoolkit.ui.base.IDataBag;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.contextmenu.BaseContextMenuListener;
import org.webguitoolkit.ui.controls.contextmenu.IContextMenu;
import org.webguitoolkit.ui.controls.contextmenu.IContextMenuItem;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.form.AbstractButtonBarListener;
import org.webguitoolkit.ui.controls.form.ICompound;
import org.webguitoolkit.ui.controls.form.ILabel;
import org.webguitoolkit.ui.controls.form.ITextarea;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.controls.table.AbstractTableListener;
import org.webguitoolkit.ui.controls.table.ClassDependentRowHandler;
import org.webguitoolkit.ui.controls.table.DefaultTreeTableModel;
import org.webguitoolkit.ui.controls.table.ITable;
import org.webguitoolkit.ui.controls.table.ITableColumn;
import org.webguitoolkit.ui.controls.table.renderer.ImageColumnRenderer;

public class TreeTableViewNew extends AbstractView {

	public TreeTableViewNew(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		// button to show the source code of this class
		factory.createButton(viewConnector, "./images/iconText.gif", "Source Code", "Source Code", new SourceView(factory, viewConnector,
				this.getClass().getName()));

		ITableLayout layout = factory.createTableLayout(viewConnector);

		ITable table = factory.createTable(layout, "", 5);
		table.setModel(new DefaultTreeTableModel(Country.class));

		ITableColumn col = factory.createTableColumn(table, "Type", "type", true);
		col.setSortable(true);
		col.setWidth("50px");

		col = factory.createTableColumn(table, "I", "icon", false);
		col.setSortable(false);
		col.setWidth("10px");
		col.setRenderer(new ImageColumnRenderer());

		col = factory.createTableColumn(table, "Name", "name", true);
		col.setSortable(true);
		col.setWidth("200px");

		col = factory.createTableColumn(table, "Description", "description", false);
		col.setSortable(false);
		col.setWidth("500px");

		layout.newRow();
		ICanvas viewConnector1 = factory.createCanvas(layout);
		table.setListener(new TableListener(viewConnector1));
		IContextMenu countryMenu = factory.createContextMenu(table);
		IContextMenuItem item = factory.createContextMenuItem(countryMenu, "edit country", new BaseContextMenuListener() {
			@Override
			public void onAction(ClientEvent event, ITable table, int row) {
				table.getPage().sendInfo("Pressed edit for: " + table.getRow(row).getString("name"));
			}
		});
		item.setImgSrc("./images/wgt/edit.gif");
		IContextMenu cityMenu = factory.createContextMenu(table);
		factory.createContextMenuItem(cityMenu, "edit city", new BaseContextMenuListener() {
			@Override
			public void onAction(ClientEvent event, ITable table, int row) {
				table.getPage().sendInfo("Pressed edit for: " + table.getRow(row).getString("name"));
			}
		});

		ClassDependentRowHandler countryRowhandler = new ClassDependentRowHandler(Country.class, "font-weight: bold;");
		countryRowhandler.addProperyMapping("description", "countryDescription");
		countryRowhandler.setContextMenu(countryMenu);
		ClassDependentRowHandler cityRowhandler = new ClassDependentRowHandler(City.class, "font-weight: normal;");
		cityRowhandler.addProperyMapping("description", "cityDescription");
		cityRowhandler.setContextMenu(cityMenu);
		table.addRowHandler(countryRowhandler);
		table.addRowHandler(cityRowhandler);

		List<IDataBag> data = new ArrayList<IDataBag>();
		data.add(createCountry("Germany", new String[] { "Düsseldorf", "Freiburg", "Köln", "Hamburg", "München", "Berlin" }));
		data.add(createCountry("Great Britain", new String[] { "London", "Manchester", "Liverpool" }));
		data.add(createCountry("Netherlands", new String[] { "Amsterdam" }));
		data.add(createCountry("Switzerland", new String[] { "Basel", "Bern" }));
		data.add(createCountry("Austria", new String[] { "Wien", "Salzburg", "Graz" }));
		data.add(createCountry("France", new String[] { "Paris", "Marseilles", "Lyon" }));
		data.add(createCountry("Norway", new String[] { "Bergen", "Oslo", "Starwanger" }));
		data.add(createCountry("USA", new String[] { "Boston", "Denver", "New York" }));
		table.getDefaultModel().setTableData(data);

		table.reload();
	}

	private IDataBag createCountry(String countryName, String[] cities) {
		Country country = new Country(countryName, "Description for " + countryName);
		for (int i = 0; i < cities.length; i++)
			country.addCity(new City(cities[i], "Description for " + cities[i]));
		IDataBag bag = new DataBag(country);
		bag.addProperty("type", countryName.substring(0, 2).toUpperCase());
		bag.addProperty("expanded", new Boolean(false));
		bag.addProperty("icon", "./images/wgt/eh/euhicon.gif");
		bag.addProperty("icon.title", countryName);
		return bag;
	}

	class TableListener extends AbstractTableListener {

		private final ICanvas viewConnector;

		public TableListener(ICanvas viewConnector) {
			this.viewConnector = viewConnector;
		}

		@Override
		public void onRowSelection(ITable table, int row) {
			IDataBag selected = table.getRow(row);
			if (selected.getDelegate() instanceof Country) {
				boolean expanded = selected.getBool("expanded");
				if (expanded) {
					int selectedIndex = table.getDefaultModel().getTableData().indexOf(selected);
					List<IDataBag> tableData = table.getDefaultModel().getTableData();
					while (tableData.size() > selectedIndex + 1 && (tableData.get(selectedIndex + 1)).getDelegate() instanceof City) {
						tableData.remove(selectedIndex + 1);
					}
				}
				else {
					Country country = (Country)selected.getDelegate();
					int selectedIndex = table.getDefaultModel().getTableData().indexOf(selected);
					List<IDataBag> tableData = table.getDefaultModel().getTableData();
					for (Iterator<City> iter = country.getCities().iterator(); iter.hasNext();) {
						City city = iter.next();
						tableData.add(++selectedIndex, new DataBag(city));
					}
				}
				table.reload();
				table.selectionChange(row);
				selected.addProperty("expanded", new Boolean(!expanded));
				new CountryView(getFactory(), viewConnector, selected).show();
			}
			else {
				new CityView(getFactory(), viewConnector, selected).show();
			}
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
		private List<City> cities;

		public Country(String name, String countryDescription) {
			super();
			this.name = name;
			this.countryDescription = countryDescription;
			this.cities = new ArrayList<City>();
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

		public List<City> getCities() {
			return cities;
		}

		public void setCities(List<City> cities) {
			this.cities = cities;
		}

		public void addCity(City city) {
			this.cities.add(city);
		}
	}

	public class CityView extends AbstractView {

		private static final String KEY_PROJECT_DESCRIPTION = "name@Name";
		private static final String KEY_PROJECT_NAME = "description@Description";

		private ICompound compound;
		private final IDataBag city;

		public CityView(WebGuiFactory factory, ICanvas viewConnector, IDataBag city) {
			super(factory, viewConnector);
			this.city = city;
		}

		@Override
		protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
			compound = factory.createCompound(viewConnector);

			compound.setBag(new DataBag(""));

			factory.createButtonBar(compound, "edit,delete,cancel,save,new", new ButtonBarListener(compound));

			ITableLayout layout = factory.createTableLayout(compound);

			factory.createLabel(layout, KEY_PROJECT_NAME);
			factory.createText(layout, "name");
			layout.newRow();

			factory.createLabel(layout, KEY_PROJECT_DESCRIPTION);
			ITextarea desc = factory.createTextarea(layout, "cityDescription");
			desc.setRows(3);
			layout.newRow();

			compound.setBag(city);
			compound.load();
		}

		public class ButtonBarListener extends AbstractButtonBarListener {

			public ButtonBarListener(ICompound compound) {
				super(compound);
			}

			@Override
			public Object newDelegate() {
				return new City("", "");
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
			}

			@Override
			public boolean delete(Object delegate) {
				return true;
			}

		}
	}

	public class CountryView extends AbstractView {

		private ICompound compound;
		private final IDataBag country;

		public CountryView(WebGuiFactory factory, ICanvas viewConnector, IDataBag country) {
			super(factory, viewConnector);
			this.country = country;
		}

		@Override
		protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
			compound = factory.createCompound(viewConnector);

			compound.setBag(new DataBag(""));

			factory.createButtonBar(compound, "edit,delete,cancel,save,new", new ButtonBarListener(compound));

			ITableLayout layout = factory.createTableLayout(compound);

			ILabel label = factory.createLabel(layout, "Type");
			factory.createText(layout, "type", label);
			layout.newRow();

			label = factory.createLabel(layout, "Name");
			factory.createText(layout, "name", label);
			layout.newRow();

			label = factory.createLabel(layout, "Description");
			ITextarea desc = factory.createTextarea(layout, "countryDescription");
			desc.setDescribingLabel(label);
			desc.setRows(3);
			layout.newRow();

			compound.setBag(country);
			compound.load();
		}

		public class ButtonBarListener extends AbstractButtonBarListener {

			public ButtonBarListener(ICompound compound) {
				super(compound);
			}

			@Override
			public Object newDelegate() {
				return new City("", "");
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
			}

			@Override
			public boolean delete(Object delegate) {
				return true;
			}

		}
	}

}
