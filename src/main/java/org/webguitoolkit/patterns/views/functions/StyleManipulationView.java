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
package org.webguitoolkit.patterns.views.functions;

import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.IBaseControl;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.event.IActionListener;
import org.webguitoolkit.ui.controls.form.IButton;
import org.webguitoolkit.ui.controls.form.ILabel;
import org.webguitoolkit.ui.controls.form.IText;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.controls.util.style.Style;
import org.webguitoolkit.ui.controls.util.style.selector.InlineSelector;

public class StyleManipulationView extends AbstractView {
	
	IText valueText, attributeText;
	public StyleManipulationView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls( WebGuiFactory factory, ICanvas viewConnector ) {
		factory.createButton( viewConnector, "./images/iconText.gif", "Source Code","Source Code", new SourceView( factory, viewConnector, this.getClass().getName() ) );

		ITableLayout layout = factory.createTableLayout( viewConnector );
		
		// button that changes the font size when pressed
		IButton button= factory.createButton(layout, null, "button font", "change font size", null );
		button.setActionListener( new ComponentStyleChangeActionListener(button,"font-size","20px") );

		// button that changes the page background when pressed
		button= factory.createButton(layout, null, "page bgcolor", "change bgcolor to green", null );
		button.setActionListener( new ComponentStyleChangeActionListener(viewConnector.getPage(),"background-color","blue") );
		
		layout.newRow();
		
		// add the same style to two text areas
		Style style = new Style( new InlineSelector() );
		
		ILabel attributeLabel = getFactory().createLabel(layout, "Style Attribute");
		attributeText = getFactory().createText( layout, "attribute", attributeLabel );
		attributeText.setStyle( style );
		attributeText.setValue( "height" );
		
		ILabel valueLabel = getFactory().createLabel(layout, "Style Value");
		valueText = getFactory().createText( layout, "value", valueLabel );
		valueText.setStyle( style );
		valueText.setValue( "40px" );

		// button for apply style
		button= factory.createButton(layout, null, "change style", "change style of the text fields", new StyleChangeActionListener( style ) );
	}
	
	class StyleChangeActionListener implements IActionListener{
		private Style style;

		public StyleChangeActionListener( Style style ) {
			super();
			this.style = style;
		}

		// applies the style attribute and value
		public void onAction(ClientEvent event) {
			style.add( attributeText.getValue(), valueText.getValue() );
		}
	}

	class ComponentStyleChangeActionListener implements IActionListener{
		private IBaseControl control;
		private String attribute;
		private String value;

		public ComponentStyleChangeActionListener(IBaseControl control, String attribute, String value) {
			super();
			this.control = control;
			this.attribute = attribute;
			this.value = value;
		}

		// applies the style attribute and value
		public void onAction(ClientEvent event) {
			control.getStyle().add( attribute, value );
		}
	}

}
