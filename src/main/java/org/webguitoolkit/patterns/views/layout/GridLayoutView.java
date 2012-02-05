package org.webguitoolkit.patterns.views.layout;

import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.form.ILabel;
import org.webguitoolkit.ui.controls.form.IText;
import org.webguitoolkit.ui.controls.layout.GridLayout;

public class GridLayoutView extends AbstractView{

	public GridLayoutView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		
		// set the layout manager
		viewConnector.setLayout( new GridLayout() );
		
		// create controls
		ILabel label = factory.createLabel(viewConnector, "Pos 1 0" );
		// set the position in the layout
		label.setLayoutData( GridLayout.getLayoutData(1, 0).setCellStyle( "background-color: green;" ) );
		
		label = factory.createLabel(viewConnector, "Pos 0 0" );
		// set the position in the layout
		label.setLayoutData( GridLayout.getLayoutData(0, 0).setCellStyle( "background-color: green;" ) );

		label = factory.createLabel(viewConnector, "Pos 3 1" );
		// set the position in the layout
		label.setLayoutData( GridLayout.getLayoutData(3, 1).setCellStyle( "background-color: green;" ));

		label = factory.createLabel(viewConnector, "Pos 2 1" );
		// set the position in the layout
		label.setLayoutData( GridLayout.getLayoutData(2, 1).setCellStyle( "background-color: green;" ));

		label = factory.createLabel(viewConnector, "Pos 5 2" );
		// set the position in the layout
		label.setLayoutData( GridLayout.getLayoutData(5, 2).setCellStyle( "background-color: green;" ));

		label = factory.createLabel(viewConnector, "Pos 4 2" );
		// set the position in the layout
		label.setLayoutData( GridLayout.getLayoutData(4, 2).setCellStyle( "background-color: green;" ));

		label = factory.createLabel(viewConnector, "Pos 2 0" );
		// set the position in the layout
		label.setLayoutData( GridLayout.getLayoutData(2, 0).setCellStyle( "background-color: green;" ));

		label = factory.createLabel(viewConnector, "Pos 0 4" );
		// set the position in the layout
		label.setLayoutData( GridLayout.getLayoutData(0, 4).setCellStyle( "background-color: green;" ));

	}

}
