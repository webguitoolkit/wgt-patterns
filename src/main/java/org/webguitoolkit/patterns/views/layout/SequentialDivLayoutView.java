/*
Copyright 2008 Endress+Hauser Infoserve GmbH&Co KG 
Licensed under the Apache License, Version 2.0 (the "License"); 
you may not use this file except in compliance with the License. 
You may obtain a copy of the License at 

http://www.apache.org/licenses/LICENSE-2.0 

Unless required by applicable law or agreed to in writing, software 
distributed under the License is distributed on an "AS IS" BASIS, 
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
implied. See the License for the specific language governing permissions 
and limitations under the License.
 */
package org.webguitoolkit.patterns.views.layout;

import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.form.IButton;
import org.webguitoolkit.ui.controls.form.ILabel;
import org.webguitoolkit.ui.controls.form.IText;
import org.webguitoolkit.ui.controls.layout.SequentialDivLayout;

/**
 * <pre>
 * --------------------------------------------------------------------------------------
 * | Label   factory.createLabel(layout, ...) | Text   factory.createText(layout, ...)   |
 * --------------------------------------------------------------------------------------
 * | Label   layout.getCurrentCell().setColspan(2)                                       |
 * --------------------------------------------------------------------------------------
 * |                                          | ---------------------------------------- |
 * |   empty                                  | | factory.createTableLaout(layout, ...)| |
 * |                                          | | Label          |      Text           | |
 * |                                          | ---------------------------------------- |
 * --------------------------------------------------------------------------------------
 * </pre>
 * 
 * @author Martin
 *
 */
public class SequentialDivLayoutView extends AbstractView {

	public SequentialDivLayoutView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {

		// set layout
		viewConnector.setLayout( new SequentialDivLayout() );
		// button to show the source code of this class
		IButton source = factory.createButton(viewConnector, "./images/iconText.gif", "Source Code", "Source Code", 
				new SourceView(factory, viewConnector, this.getClass().getName()));
		source.setLayoutData( SequentialDivLayout.getLayoutData().setCellClear( "left" ) );

		// add a label to the layout
		ILabel label = factory.createLabel(viewConnector, "Simple Text");
		label.setLayoutData( SequentialDivLayout.getLayoutData().setCellFloat( "left" ) );
		
		// add a text field to the layout
		IText text = factory.createText(viewConnector, WebGuiFactory.NO_LOCATOR, label );
		text.setLayoutData( SequentialDivLayout.getLayoutData().setCellFloat( "left" ) );

		// add a label with collspan to the layout
		label = factory.createLabel( viewConnector, "Long cell");
		label.setLayoutData( SequentialDivLayout.getLayoutData().setCellClear( "left" ) );
		
		// create inner layout
		ICanvas inner = factory.createCanvas( viewConnector );
		inner.setLayoutData( SequentialDivLayout.getLayoutData().setCellFloat( "left" ) );
		// set layout
		inner.setLayout( new SequentialDivLayout() );
		
		// add a label to the layout
		label = factory.createLabel(inner, "Simple Text");
		label.setLayoutData( SequentialDivLayout.getLayoutData().setCellFloat( "left" ) );
		
		// add a text field to the layout
		text = factory.createText(inner, WebGuiFactory.NO_LOCATOR );
		text.setLayoutData( SequentialDivLayout.getLayoutData().setCellFloat( "left" ) );

	}
}
