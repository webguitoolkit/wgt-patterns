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
import org.webguitoolkit.ui.controls.event.IActionListener;
import org.webguitoolkit.ui.controls.form.IButton;
import org.webguitoolkit.ui.controls.form.ILabel;
import org.webguitoolkit.ui.controls.form.IText;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.controls.layout.SequentialTableLayout;
import org.webguitoolkit.ui.controls.table.AbstractTableListener;
import org.webguitoolkit.ui.controls.table.ITable;
import org.webguitoolkit.ui.controls.table.ITableColumn;
import org.webguitoolkit.ui.controls.table.Table;

/**
 * Very simple sample table
 */
public class TableConfigurationView extends AbstractView {

	ILabel layoutInfo;
	
	public TableConfigurationView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls( WebGuiFactory factory, ICanvas viewConnector ) {

		viewConnector.setLayout( new SequentialTableLayout() );
		
		// button to show the source code of this class
		IButton sourceButton = factory.createButton( viewConnector, "./images/iconText.gif", "Source Code","Source Code", new SourceView( factory, viewConnector, this.getClass().getName() ) );
		sourceButton.setLayoutData( SequentialTableLayout.getLastInRow() );
		
		//Table table = factory.createTable(layout, "Scroll Table Sample", 5);
		ITable table = factory.createTable(viewConnector, "Scroll Table Sample", 5);
		table.setListener(new TableListener());
		ITableColumn col = factory.createTableColumn(table, "Type", "type", true);
		col.setWidth("50px");
		col.setMandatory( true );
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
		table.setLayoutData( SequentialTableLayout.getLastInRow() );		
		
		layoutInfo = factory.createLabel(viewConnector, "TableConfig");
		layoutInfo.setLayoutData( SequentialTableLayout.getLastInRow() );		
		ILabel input = factory.createLabel(viewConnector, "Input");
		IText text = factory.createText(viewConnector, "colConfig", input);
		text.setLayoutData( SequentialTableLayout.APPEND );		
		IButton send = factory.createButton( viewConnector, null, "Set", "Set", new ChangeConfigListener( table, text ) );
		send.setLayoutData( SequentialTableLayout.APPEND );		
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

		@Override
		public void onEditTableLayout(ClientEvent event, int rowCount,
				String tableSetting) {
			super.onEditTableLayout(event, rowCount, tableSetting);
			layoutInfo.setText( ((ITable)event.getSource()).getColumnConfig() );
		}
	}

	class ChangeConfigListener implements IActionListener{

		private final ITable table;
		private final IText text;

		public ChangeConfigListener(ITable table, IText text) {
			this.table = table;
			this.text = text;
		}

		public void onAction(ClientEvent event) {
			String newConfig = text.getValue();
			table.loadColumnConfig( newConfig );
	        ((Table)table).redraw();
	        table.reload();
		}
	}
}