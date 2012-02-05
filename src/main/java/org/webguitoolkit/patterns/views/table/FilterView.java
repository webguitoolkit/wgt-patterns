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
import org.webguitoolkit.ui.controls.table.ITableFilter;
import org.webguitoolkit.ui.controls.table.SimpleFilterModel;
import org.webguitoolkit.ui.controls.table.renderer.BooleanColumnRenderer;
import org.webguitoolkit.ui.controls.table.renderer.ImageColumnRenderer;
import org.webguitoolkit.ui.controls.util.TextService;
import org.webguitoolkit.ui.controls.util.conversion.BooleanConverter;
import org.webguitoolkit.ui.util.export.TableExportOptions;

public class FilterView extends AbstractView {
	private ITable table;

	public FilterView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls( WebGuiFactory factory, ICanvas viewConnector ) {
		// button to show the source code of this class
		factory.createButton( viewConnector, "./images/iconText.gif", "Source Code","Source Code", new SourceView( factory, viewConnector, this.getClass().getName() ) );

		ITableLayout layout = factory.createTableLayout(viewConnector);

		table = factory.createTable(layout, "", 5);
		table.setListener(new TableListener());
		table.setTitle("Table Sample");
		table.setExportOptions(new TableExportOptions(true, true));

		List<String[]> values = new ArrayList<String[]>();
		values.add( new String[]{"type 1","type 1"} );
		values.add( new String[]{"type 2","type 2"} );
		values.add( new String[]{"type 4","type 4"} );
		values.add( new String[]{"type 8","type 8"} );
		SimpleFilterModel filterModel = new SimpleFilterModel( null, values ,"type" );

		ITableFilter filter = factory.createTableFilter(table);
		filter.setModel( filterModel );
		filter.setLabel("Type");

		values = new ArrayList<String[]>();
		values.add( new String[]{"true", TextService.getString( "true.key@available" ) } );
		values.add( new String[]{"false", TextService.getString( "false.key@unavailable" ) } );
		filterModel = new SimpleFilterModel( new BooleanConverter(), values ,"stat" );

		filter = factory.createTableFilter(table);
		filter.setModel( filterModel );
		filter.setLabelKey("status.key@Availability");

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