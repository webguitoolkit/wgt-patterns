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
import org.webguitoolkit.ui.controls.table.ITable;
import org.webguitoolkit.ui.controls.table.ITableColumn;
import org.webguitoolkit.ui.controls.table.renderer.BooleanColumnRenderer;

public class ExportView extends AbstractView {
	private ITable table;

	public ExportView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls( WebGuiFactory factory, ICanvas viewConnector ) {
		// button to show the source code of this class
		factory.createButton( viewConnector, "./images/iconText.gif", "Source Code","Source Code", new SourceView( factory, viewConnector, this.getClass().getName() ) );

		ITableLayout layout = factory.createTableLayout(viewConnector);

		table = factory.createTable(layout, "", 5);
		table.addExportButton(true, true );
		table.setTitle("Table Sample");

		table.addCheckboxColumn("toDelete", null);
				
		ITableColumn col = factory.createTableColumn(table, "Type", "type", true);
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
			bag.addProperty("description", "description " + i);
			// bag.addProperty("text", "text " + i);
			bag.addProperty("toDelete", new Boolean(i % 3 == 0));
			bag.addProperty("stat", new Boolean(i % 3 == 0));
			data.add(bag);
		}
		table.getDefaultModel().setTableData(data);

		table.reload();

	}

}