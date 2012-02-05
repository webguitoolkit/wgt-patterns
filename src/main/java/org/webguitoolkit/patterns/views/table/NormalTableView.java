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
import org.webguitoolkit.ui.controls.table.ITable;
import org.webguitoolkit.ui.controls.table.ITableColumn;
import org.webguitoolkit.ui.controls.table.Table;
import org.webguitoolkit.ui.controls.util.conversion.ConvertUtil;

/**
 * Very simple sample table
 */
public class NormalTableView extends AbstractView {

	public NormalTableView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls( WebGuiFactory factory, ICanvas viewConnector ) {
		// button to show the source code of this class
		factory.createButton( viewConnector, "./images/iconText.gif", "Source Code","Source Code", new SourceView( factory, viewConnector, this.getClass().getName() ) );

		ITableLayout layout = factory.createTableLayout(viewConnector);

		//Table table = factory.createTable(layout, "Scroll Table Sample", 5);
		ITable table = factory.createTable(layout, "Scroll Table Sample", 5);
		table.setListener(new TableListener());
		ITableColumn col = factory.createTableColumn(table, "Type", "type", true);
		col.setWidth("50px");
		col = factory.createTableColumn(table, "name@Name", "name", true);
		col.setTooltip("The name");
		col.setWidth("200px");
		col = factory.createTableColumn(table, "description@Description", "description", true);
		col.setTooltip("The description");
		col.setWidth("500px");
		

		// Fill with data
		List<IDataBag> data = new ArrayList<IDataBag>();
		for (int i = 0; i < 100; i++) {
			IDataBag bag = new DataBag(null);
			bag.addProperty("type", new Float(i));
			bag.addProperty("name", "name " + i);
			bag.addProperty("description", "description " + i);
			data.add(bag);
		}
		table.getDefaultModel().setTableData(data);
		// send data to the front end
		table.reload();
		
		layout.newRow();
		factory.createLabel(layout, "Table with Buttons to scroll");
		layout.newRow();
		
		ITable buttontable = factory.createTable(layout, "Button Table Sample", 5);
		buttontable.setDisplayMode(Table.DISPLAY_MODE_SCROLL_BUTTONS);
		buttontable.setListener(new TableListener());
		col = factory.createTableColumn(buttontable, "Type", "type", true);
		col.setConverter( ConvertUtil.NUM0_CONVERTER );
		col.setWidth("50px");
		col = factory.createTableColumn(buttontable, "Name", "name", true);
		col.setWidth("200px");
//		col.getStyle().add("text-align", "right");
		col = factory.createTableColumn(buttontable, "Description", "description", true);
		col.setWidth("500px");

		buttontable.getDefaultModel().setTableData(data);
		// send data to the front end
		buttontable.reload();
		
		
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