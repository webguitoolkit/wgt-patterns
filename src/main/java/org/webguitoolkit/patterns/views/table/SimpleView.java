package org.webguitoolkit.patterns.views.table;

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
import org.webguitoolkit.ui.controls.table.ISimpleTable;
import org.webguitoolkit.ui.controls.table.ITable;
import org.webguitoolkit.ui.controls.table.ITableColumn;
import org.webguitoolkit.ui.controls.table.SimpleTable;

/**
 * Very simple sample table
 */
public class SimpleView extends AbstractView {

	public SimpleView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls( WebGuiFactory factory, ICanvas viewConnector ) {
		// button to show the source code of this class
		factory.createButton( viewConnector, "./images/iconText.gif", "Source Code","Source Code", new SourceView( factory, viewConnector, this.getClass().getName() ) );

		ITableLayout layout = factory.createTableLayout(viewConnector);

		ISimpleTable table = factory.createSimpleTable(layout, "Simple Table Sample");
		table.setFixedViewPort(true);
		table.setViewPortWidth("400px;");
		table.setViewPortHeight("250px;");
		table.setListener(new TableListener());
		ITableColumn col = factory.createTableColumn(table, "Type", "type", true);
		col.setWidth("50px");
		col = factory.createTableColumn(table, "Name", "name", true);
		col.setWidth("200px");
		col = factory.createTableColumn(table, "Description", "description", true);
		col.setWidth("500px");
		

		// Fill with data
		List<IDataBag> data = new ArrayList<IDataBag>();
		for (int i = 0; i < 100; i++) {
			IDataBag bag = new DataBag(null);
			bag.addProperty("type", "type " + i);
			bag.addProperty("name", "name " + i);
			bag.addProperty("description", "description " + i);
			data.add(bag);
		}
		table.getDefaultModel().setTableData(data);
		// send data to the front end
		table.reload();
		
		layout.newRow();
		factory.createLabel(layout, "Table with no fixed Viewport");
		layout.newRow();
		ISimpleTable simpletable = factory.createSimpleTable(layout, "Simple Table Sample");
		simpletable.setListener(new TableListener());
		col = factory.createTableColumn(simpletable, "Type", "type", true);
		col.setWidth("50px");
		col = factory.createTableColumn(simpletable, "Name", "name", true);
		col.setWidth("200px");
		col = factory.createTableColumn(simpletable, "Description", "description", true);
		col.setWidth("500px");

		simpletable.getDefaultModel().setTableData(data);
		// send data to the front end
		simpletable.reload();
		
		
	}

	/**
	 * Handles table related events
	 */
	class TableListener extends AbstractTableListener {
		@Override
		public void onRowSelection(ITable table, int row) {
			// get row representing DataBag
			IDataBag bag = table.getRow(row);
			
			// send info
			table.getPage().sendInfo( "Clicked: " + bag.getString("name") );
		}

	}

}