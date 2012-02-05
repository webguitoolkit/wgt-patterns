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
package org.webguitoolkit.patterns.doc.examples;

import java.util.ArrayList;
import java.util.List;

import org.webguitoolkit.ui.base.DataBag;
import org.webguitoolkit.ui.base.IDataBag;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.Canvas;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.controls.table.AbstractTableListener;
import org.webguitoolkit.ui.controls.table.ITable;
import org.webguitoolkit.ui.controls.table.ITableColumn;

public class TableView extends AbstractView {
	private ITable table;
	public TableView(WebGuiFactory factory, Canvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
	ITableLayout layout = factory.createTableLayout(viewConnector);
	table = factory.createTable(layout, "Simple table", 5);	
	table.getStyle().addWidth("300px");
	table.setListener(new AbstractTableListener(){
		@Override
		public void onRowSelection(ITable table, int row) {	
		}});
		
	ITableColumn col = factory.createTableColumn(table, "Name", "name", true);
	col.setWidth("100px");

	col = factory.createTableColumn(table, "Description", "description", true);
	col.setWidth("150px");

	List<IDataBag> data = new ArrayList<IDataBag>();
	for (int i = 0; i < 10; i++) {
		IDataBag bag = new DataBag(null);
		bag.addProperty("name", "name " + i);
		bag.addProperty("description", "description " + i);
		data.add(bag);
	}
	table.getDefaultModel().setTableData(data);
	table.reload(); // loading the data into the table 
}

}
