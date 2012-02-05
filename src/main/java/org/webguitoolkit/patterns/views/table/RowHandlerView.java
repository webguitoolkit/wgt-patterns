package org.webguitoolkit.patterns.views.table;

import java.util.ArrayList;
import java.util.List;

import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.DataBag;
import org.webguitoolkit.ui.base.IDataBag;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.IBaseControl;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.contextmenu.IContextMenu;
import org.webguitoolkit.ui.controls.contextmenu.IContextMenuListener;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.controls.table.AbstractTableListener;
import org.webguitoolkit.ui.controls.table.ITable;
import org.webguitoolkit.ui.controls.table.ITableColumn;
import org.webguitoolkit.ui.controls.table.PropertyDependentRowHandler;
import org.webguitoolkit.ui.controls.table.Table;
import org.webguitoolkit.ui.controls.table.renderer.TextColumnRenderer;
import org.webguitoolkit.ui.controls.tree.ITree;

public class RowHandlerView extends AbstractView {
	private ITable table;

	public RowHandlerView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls( WebGuiFactory factory, ICanvas viewConnector ) {
		// button to show the source code of this class
		factory.createButton( viewConnector, "./images/iconText.gif", "Source Code","Source Code", new SourceView( factory, viewConnector, this.getClass().getName() ) );

		ITableLayout layout = factory.createTableLayout(viewConnector);

		table = factory.createTable(layout, "", 5);
		table.setListener(new TableListener());
		
		ITableColumn col = factory.createTableColumn(table, "Type", "type", false);
		col.setWidth("50px");

		col = factory.createTableColumn(table, "Name", "name", false);
		col.setWidth("200px");

		col = factory.createTableColumn(table, "Description", "description", false);
		col.setWidth("500px");

		// this is a editable text
		col = factory.createTableColumn(table, "Text", "input", true);
		col.setRenderer( new TextColumnRenderer() );
		col.setWidth("200px");

		List<IDataBag> data = new ArrayList<IDataBag>();
		for (int i = 0; i < 100; i++) {
			IDataBag bag = new DataBag(null);
			bag.addProperty("type", "type " + i);
			bag.addProperty("name", "name " + i);
			bag.addProperty("input", "text " + i);
			bag.addProperty("description", "description " + i);
			bag.addProperty("stat", new Boolean(i % 3 == 0));
			data.add(bag);
		}

		// inti row handler
		PropertyDependentRowHandler rowHandler1 = new PropertyDependentRowHandler("stat", new Boolean(true), "font-weight: bold;" );
		IContextMenu cm = factory.createContextMenu( table );
		factory.createContextMenuItem(cm, "edit@Edit", new ContextMenuListener());
		rowHandler1.setContextMenu( cm );
		
		PropertyDependentRowHandler rowHandler2 = new PropertyDependentRowHandler("stat", new Boolean(false), "font-weight: normal;" );
		// set the property to empty String, this makes the input field invisible
		rowHandler2.addProperyMapping("input", "");
		cm = factory.createContextMenu( table );
		factory.createContextMenuItem(cm, "delete@Delete", new ContextMenuListener());
		rowHandler2.setContextMenu( cm );

		// add row handler to table
		table.addRowHandler( rowHandler1 );
		table.addRowHandler( rowHandler2 );
		
		table.getDefaultModel().setTableData(data);

		table.reload();

	}

	class ContextMenuListener implements IContextMenuListener{

		public void onAction(ClientEvent event, IBaseControl control) {
		}

		public void onAction(ClientEvent event, ITable table, int row) {
			table.getPage().sendInfo( "Context menu on: " + ((Table)table).getRow(row).getString("name"));
		}

		public void onAction(ClientEvent event, ITree tree, String nodeId) {
		}
	}

	class TableListener extends AbstractTableListener {

		@Override
		public void onRowSelection(ITable table, int row) {
			// Write your code here
		}

	}

}