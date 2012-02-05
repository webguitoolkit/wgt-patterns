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
package org.webguitoolkit.patterns.views.simple;

import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.event.IActionListener;
import org.webguitoolkit.ui.controls.form.IButton;
import org.webguitoolkit.ui.controls.form.Text;
import org.webguitoolkit.ui.controls.form.button.JQueryButton;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.sandbox.controls.form.Button2Zero;

public class ButtonJQView extends AbstractView {
	

	public void onAction(ClientEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource() instanceof Button2Zero) {
			getPage().sendInfo("I'v been clicked");
		} else {
			super.onAction(event);
		}
	}

	
	private Text text1, text2;

	public ButtonJQView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls( WebGuiFactory factory, ICanvas viewConnector ) {
		
		// button to show the source code of this class
		factory.createButton( viewConnector, "./images/iconText.gif", "Source Code","Source Code", new SourceView( factory, viewConnector, this.getClass().getName() ) );

		ITableLayout layout = factory.createTableLayout(viewConnector);
		
		IButton plain = new JQueryButton();
		layout.add(plain);
		plain.setActionListener(this);
		plain.setLabelKey("click.me@Click Me!");
		plain.setActionListener( new IActionListener() {
			public void onAction(ClientEvent event) {
				getPage().sendInfo("Button Clicked!");
			}
		});
		
		IButton icon = new JQueryButton();
		layout.add(icon);
		icon.setActionListener(this);
		icon.setSrc("ui-icon-arrow-1-n");
		
	}

}
