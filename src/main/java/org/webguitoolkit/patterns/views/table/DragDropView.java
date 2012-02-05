package org.webguitoolkit.patterns.views.table;

import java.util.ArrayList;
import java.util.List;

import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.DataBag;
import org.webguitoolkit.ui.base.IDataBag;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.controls.table.AbstractTableListener;
import org.webguitoolkit.ui.controls.table.ITable;
import org.webguitoolkit.ui.controls.table.ITableColumn;
import org.webguitoolkit.ui.controls.table.Table;

public class DragDropView extends AbstractView {

	private ITable sourceTable;

	public DragDropView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls( WebGuiFactory factory, ICanvas viewConnector ) {
		// button to show the source code of this class
		factory.createButton( viewConnector, "./images/iconText.gif", "Source Code","Source Code", new SourceView( factory, viewConnector, this.getClass().getName() ) );

		ITableLayout layout = factory.createTableLayout(viewConnector);

		sourceTable = factory.createTable(layout, "table.header.draggable@Source Table", 5);
		sourceTable.setListener(new TableListener());
		
		ITableColumn col = factory.createTableColumn(sourceTable, "Type", "type", true);
		col.setWidth("50px");

		col = factory.createTableColumn(sourceTable, "Name", "name", true);
		col.setWidth("200px");

		col = factory.createTableColumn(sourceTable, "Description", "description", true);
		col.setWidth("500px");

		// set draggable
		sourceTable.setDraggable("name");
		
		List<IDataBag> data = new ArrayList<IDataBag>();
		for (int i = 0; i < 100; i++) {
			IDataBag bag = new DataBag(null);
			bag.addProperty("type", "type " + i);
			bag.addProperty("name", "name " + i);
			bag.addProperty("description", "description " + i);
			data.add(bag);
		}
		sourceTable.getDefaultModel().setTableData(data);

		sourceTable.reload();

		layout.newRow();

		ITable targetTable = factory.createTable(layout, "table.header.droppable@Target Table", 5);
		targetTable.setListener(new TableListener());
		
		col = factory.createTableColumn(targetTable, "Type", "type", true);
		col.setWidth("50px");

		col = factory.createTableColumn(targetTable, "Name", "name", true);
		col.setWidth("200px");

		col = factory.createTableColumn(targetTable, "Description", "description", true);
		col.setWidth("500px");
		
		data = new ArrayList<IDataBag>();
		for (int i = 100; i < 200; i++) {
			IDataBag bag = new DataBag(null);
			bag.addProperty("type", "type " + i);
			bag.addProperty("name", "name " + i);
			bag.addProperty("description", "description " + i);
			data.add(bag);
		}
		targetTable.getDefaultModel().setTableData(data);

		// set droppable
		targetTable.setDroppable();

		targetTable.reload();

	}

	class TableListener extends AbstractTableListener {

		@Override
		public void onDropped(ClientEvent event, String draggableId,
				String droppableId) {
			// this will add the dragged row to the target table
			super.onDropped(event, draggableId, droppableId);
			
			// remove the object from the source
	        ((Table)sourceTable).removeAndReload( sourceTable.getPage().getDraggable().getDataObject() );
		}

		@Override
		public void onRowSelection(ITable table, int row) {
			// Write your code here
		}

	}

}