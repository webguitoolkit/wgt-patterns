package org.webguitoolkit.patterns.views.layout;

import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.form.ILabel;
import org.webguitoolkit.ui.controls.form.IText;
import org.webguitoolkit.ui.controls.layout.BorderLayout;
import org.webguitoolkit.ui.controls.layout.GridLayout;

public class BorderLayoutView extends AbstractView{

	public BorderLayoutView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		
		// set the layout manager
		viewConnector.setLayout( new BorderLayout() );
		
		
		ILabel label = factory.createLabel(viewConnector, "Header (North)" );
		// set the position in the layout
		label.setLayoutData( BorderLayout.north().setCellStyle("background-color: green;") );
		label.getStyle().add("font-size", "20px");

		label = factory.createLabel(viewConnector, "Content (Center)");
		// set the position in the layout
		label.setLayoutData( BorderLayout.center().setCellStyle("background-color: blue; height: 100px;") );

		label = factory.createLabel(viewConnector, "Info (East)" );
		// set the position in the layout
		label.setLayoutData( BorderLayout.east().setCellStyle("background-color: red;") );

		label = factory.createLabel(viewConnector, "Menu (West)" );
		// set the position in the layout
		label.setLayoutData( BorderLayout.west().setCellStyle("background-color: red;") );

		// create controls
		label = factory.createLabel(viewConnector, "Footer (South)" );
		// set the position in the layout
		label.setLayoutData( BorderLayout.south().setCellStyle("background-color: green;") );

	}

}
