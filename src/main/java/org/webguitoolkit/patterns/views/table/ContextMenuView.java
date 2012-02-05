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
import org.webguitoolkit.ui.controls.table.Table;
import org.webguitoolkit.ui.controls.tree.ITree;

public class ContextMenuView extends AbstractView {
	private ITable table;

	public ContextMenuView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls( WebGuiFactory factory, ICanvas viewConnector ) {
		// button to show the source code of this class
		factory.createButton( viewConnector, "./images/iconText.gif", "Source Code","Source Code", new SourceView( factory, viewConnector, this.getClass().getName() ) );

		ITableLayout layout = factory.createTableLayout(viewConnector);

		table = factory.createTable(layout, "", 5);
		table.setTitle("Table Sample");
				
		ITableColumn col = factory.createTableColumn(table, "Type", "type", true);
		col.setWidth("50px");

		col = factory.createTableColumn(table, "Name", "name", true);
		col.setWidth("200px");

		col = factory.createTableColumn(table, "Description", "description", true);
		col.setWidth("500px");

		IContextMenu cm = factory.createContextMenu( table );
		factory.createContextMenuItem(cm, "Edit", new EditListener() );
		
		List<IDataBag> data = new ArrayList<IDataBag>();
		for (int i = 0; i < 100; i++) {
			IDataBag bag = new DataBag(null);
			bag.addProperty("type", "type " + i);
			bag.addProperty("name", "name " + i);
			bag.addProperty("description", "description " + i);
			data.add(bag);
		}
		table.getDefaultModel().setTableData(data);

		table.reload();

	}

	class EditListener implements IContextMenuListener{

		public void onAction(ClientEvent event, IBaseControl control) {
		}

		public void onAction(ClientEvent event, ITable table, int row) {
			table.getPage().sendInfo("Edit pressed on: " + ((Table)table).getRow( row ).getString("name") );
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