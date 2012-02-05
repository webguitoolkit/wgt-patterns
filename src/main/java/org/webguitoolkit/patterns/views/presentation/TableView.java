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

import java.util.ArrayList;
import java.util.List;

import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.DataBag;
import org.webguitoolkit.ui.base.IDataBag;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.event.IActionListener;
import org.webguitoolkit.ui.controls.form.Button;
import org.webguitoolkit.ui.controls.form.button.StandardButton;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.controls.table.AbstractTableListener;
import org.webguitoolkit.ui.controls.table.ITable;
import org.webguitoolkit.ui.controls.table.ITableColumn;
import org.webguitoolkit.ui.controls.table.ITableFilter;
import org.webguitoolkit.ui.controls.table.renderer.BooleanColumnRenderer;
import org.webguitoolkit.ui.controls.table.renderer.ImageColumnRenderer;

public class TableView extends AbstractView {
	private ITable table;

	public TableView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls( WebGuiFactory factory, ICanvas viewConnector ) {
		// button to show the source code of this class
		factory.createButton( viewConnector, "./images/iconText.gif", "Source Code","Source Code", new SourceView( factory, viewConnector, this.getClass().getName() ) );

		ITableLayout layout = factory.createTableLayout(viewConnector);

		table = factory.createTable(layout, "", 5);
		table.addExportButton(true, true );
		Button editButton = new StandardButton();
		editButton.setActionListener(new IActionListener(){public void onAction(ClientEvent event) {getPage().sendInfo("Edit pressed");}});
		editButton.setSrc("./images/wgt/icons/edit.gif");
		table.addButton(editButton);
		
		table.setListener(new TableListener());
		table.setTitle("Table Sample");

		ITableFilter filter = factory.createTableFilter(table);
		filter.setLabel("Type");

		filter = factory.createTableFilter(table);
		filter.setLabel("Customer");

		table.addCheckboxColumn("toDelete", null);
		
		//use of ImageColumnRenderer
		ITableColumn col = factory.createTableColumn(table, " ", "img", false);
		col.setRenderer(new ImageColumnRenderer());
		col.setSortable(false);
		col.setWidth("18px");
		
		col = factory.createTableColumn(table, "Type", "type", true);
		col.setWidth("50px");

		col = factory.createTableColumn(table, "Name", "name", true);
		col.setWidth("200px");

		col = factory.createTableColumn(table, "Description", "description", true);
		col.setWidth("500px");
		
		//use of BooleanColumnRenderer
		col = factory.createTableColumn(table, "status.key@Availability", "stat", true);
		col.setRenderer(new BooleanColumnRenderer("true.key@available","false.key@unavailable"));

		List<IDataBag> data = new ArrayList<IDataBag>();
		for (int i = 0; i < 100; i++) {
			IDataBag bag = new DataBag(null);
			bag.addProperty("type", "type " + i);
			bag.addProperty("name", "name " + i);
			bag.addProperty("img", "./images/iconText.gif");
			bag.addProperty("description", "description " + i);
			// bag.addProperty("text", "text " + i);
			bag.addProperty("toDelete", new Boolean(i % 3 == 0));
			bag.addProperty("stat", new Boolean(i % 3 == 0));
			data.add(bag);
		}
		table.getDefaultModel().setTableData(data);

		table.reload();

	}

	class TableListener extends AbstractTableListener {

		@Override
		public void onRowSelection(ITable table, int row) {
			// Write your code here
		}

	}

}
