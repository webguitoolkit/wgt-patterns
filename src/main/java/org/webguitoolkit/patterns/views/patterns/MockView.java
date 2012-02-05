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

import org.webguitoolkit.patterns.prototype.Prototyper;
import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.IDataBag;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.IComposite;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.form.Compound;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.controls.table.AbstractTableListener;
import org.webguitoolkit.ui.controls.table.ITable;
import org.webguitoolkit.ui.controls.table.Table;

public class MockView extends AbstractView {

	private Table table;
	private Prototyper p;
	private Compound form;

	String[] columns = new String[] { "Name:name:y", "Firstname:fname:y", "Age:num:y", "City:city:y", "Serial:ser:y",
			"Date:dat:y", "Street:street:y", "Device:device:y" };

	String[][] grid = new String[][] { { "Name:name:y", "Firstname:fname:y" }, { "Age:num:y", "City:city:y" } };

	public MockView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls( WebGuiFactory factory, ICanvas viewConnector ) {
		// button to show the source code of this class
		factory.createButton( viewConnector, "./images/iconText.gif", "Source Code","Source Code", new SourceView( factory, viewConnector, this.getClass().getName() ) );

		p = new Prototyper(factory);
		ITableLayout layout = factory.createTableLayout(viewConnector);
		table = p.genTable((IComposite) layout, columns, 10);
		table.setListener(new TableListener());
		layout.newRow();

		form = p.genForm((IComposite) layout, grid);

		table.getDefaultModel().setTableData(p.genMockdata(columns, 20));
		table.reload();
		
		IDataBag bag = p.genBag(grid);
		form.setBag(bag);
		form.load();
		
	}

	class TableListener extends AbstractTableListener {

		@Override
		public void onRowSelection(ITable table, int row) {
		}

	}
}
