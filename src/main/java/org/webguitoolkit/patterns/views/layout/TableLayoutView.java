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

import java.util.Random;

import org.webguitoolkit.patterns.prototype.MockData;
import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.event.IActionListener;
import org.webguitoolkit.ui.controls.form.IButton;
import org.webguitoolkit.ui.controls.form.ILabel;
import org.webguitoolkit.ui.controls.form.IText;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.controls.tab.ITab;
import org.webguitoolkit.ui.controls.tab.ITabStrip;
import org.webguitoolkit.ui.controls.tab.StandardTabStrip;
import org.webguitoolkit.ui.controls.util.style.Style;
import org.webguitoolkit.ui.controls.util.style.selector.InlineSelector;

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
 * @author i102415
 *
 */
public class TableLayoutView extends AbstractView {

	public TableLayoutView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		// button to show the source code of this class
		factory.createButton(viewConnector, "./images/iconText.gif", "Source Code", "Source Code", new SourceView(factory, viewConnector, this
				.getClass().getName()));

		// create layout
		ITableLayout layout = factory.createTableLayout(viewConnector);
		// just for visualization, for styling use layout.setCssClass
		layout.getEcsTable().setBorder(1);

		// add a label to the layout
		ILabel label = factory.createLabel(layout, "Simple Text");
		
		// add a text field to the layout
		factory.createText(layout, WebGuiFactory.NO_LOCATOR, label );

		// new row
		layout.newRow();

		// add a label with collspan to the layout
		factory.createLabel(layout, "Long cell");
		layout.getCurrentCell().setColSpan(2);

		// new row
		layout.newRow();
		
		// add empty cell
		layout.addEmptyCell();
		
		// create inner layout
		ITableLayout inner = factory.createTableLayout( layout );
		// just for visualization, for styling use inner.setCssClass
		inner.getEcsTable().setBorder(1);
		
		// add a label to the layout
		factory.createLabel(inner, "Simple Text");
		
		// add a text field to the layout
		factory.createText(inner, WebGuiFactory.NO_LOCATOR );

	}
}
