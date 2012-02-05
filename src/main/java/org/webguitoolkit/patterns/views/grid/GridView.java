package org.webguitoolkit.patterns.views.grid;

import java.util.ArrayList;
import java.util.List;

import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.DataBag;
import org.webguitoolkit.ui.base.IDataBag;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.grid.Grid;
import org.webguitoolkit.ui.controls.grid.GridColumn;
import org.webguitoolkit.ui.controls.grid.GridModel;

public class GridView extends AbstractView {

	public GridView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		// button to show the source code of this class
		factory.createButton(viewConnector, "./images/iconText.gif", "Source Code", "Source Code", new SourceView(factory, viewConnector,
				this.getClass().getName()));

		Grid grid = new Grid();
		viewConnector.add(grid);
		grid.setTitleKey("grid.title@My First Grid");
		grid.addColumn(new GridColumn("id", "Id", "20", false, true));
		grid.addColumn(new GridColumn("type", "Type", "100",true, true));
		grid.addColumn(new GridColumn("name", "Name", "100",true, true));
		grid.addColumn(new GridColumn("description", "Description", "500",true,false));

		// Fill with data
		List<IDataBag> data = new ArrayList<IDataBag>();
		for (int i = 0; i < 100; i++) {
			IDataBag bag = new DataBag(null);
			bag.addProperty("id", i);
			bag.addProperty("type", "type " + i);
			bag.addProperty("name", "name " + i);
			bag.addProperty("description", "description " + i);
			data.add(bag);
		}
		GridModel model = new GridModel();
		model.setTableData(data);

		grid.setModel(model);

		// grid.load();
	}
}
