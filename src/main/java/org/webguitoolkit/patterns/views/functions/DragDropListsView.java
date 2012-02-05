package org.webguitoolkit.patterns.views.functions;

import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.event.IActionListener;
import org.webguitoolkit.ui.controls.list.ISortableList;
import org.webguitoolkit.ui.controls.list.SortableList;

public class DragDropListsView extends AbstractView {

	public DragDropListsView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		// button to show the source code of this class
		factory.createButton( viewConnector, "./images/iconText.gif", "Source Code","Source Code", new SourceView( factory, viewConnector, this.getClass().getName() ) );


		factory.createLabel(viewConnector,
				"<h2>drag and drop from one list to the other and sort within the lists</h2>");

		final ISortableList list1 = factory.createSortableList(viewConnector);
		for (int i = 0; i < 5; i++) {
			ICanvas canvas = factory.createCanvas(list1);
			factory.createLabel(canvas, "** Item 1." + i);
		}

		factory.createLabel(viewConnector, "****************************************************");

		final ISortableList list2 = factory.createSortableList(viewConnector);
		for (int i = 0; i < 5; i++) {
			ICanvas canvas = factory.createCanvas(list2);
			factory.createLabel(canvas, "** Item 2." + i);
		}
		
		factory.createLinkButton(viewConnector, null, "force redraw", null, new IActionListener() {

			public void onAction(ClientEvent event) {
				((SortableList) list1).redraw();
				((SortableList) list2).redraw();
			}
		});
		
		list1.connectWith(list2);
		list2.connectWith(list1);

	}

}
