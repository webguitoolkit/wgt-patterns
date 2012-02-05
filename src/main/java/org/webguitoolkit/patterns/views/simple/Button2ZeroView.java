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
import org.webguitoolkit.ui.controls.form.Text;
import org.webguitoolkit.ui.controls.layout.TableLayout;
import org.webguitoolkit.ui.controls.util.TextService;
import org.webguitoolkit.ui.sandbox.controls.form.Button2Zero;

public class Button2ZeroView extends AbstractView {
	

	public void onAction(ClientEvent event) {
		// TODO Auto-generated method stub
		if (event.getSource() instanceof Button2Zero) {
			getPage().sendInfo("I'v been clicked");
		} else {
			super.onAction(event);
		}
	}

	
	private Text text1, text2;

	public Button2ZeroView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls( WebGuiFactory factory, ICanvas viewConnector ) {
		
		
		// button to show the source code of this class
		factory.newButton( viewConnector, "./images/iconText.gif", "Source Code","Source Code", new SourceView( factory, viewConnector, this.getClass().getName() ) );

		TableLayout layout = factory.newTableLayout(viewConnector);
		
		Button2Zero plain = new Button2Zero();
		layout.add(plain);
		plain.setActionListener(this);
		//plain.setSrc("./images/wgt/icons/edit.gif", 16, 16);
		plain.setLabel(TextService.getString("Click Me!"));
		//plain.getStyle().add("display", "inline");
		
		
		Button2Zero icon = new Button2Zero();
		layout.add(icon);
		icon.setActionListener(this);
		icon.setSrc("./images/wgt/icons/msg_icon_error.gif");
		icon.getStyle().addWidth("32px");
		icon.getStyle().addHeight("32px");
		
		
		
		Button2Zero iconAndText = new Button2Zero();
		layout.add(iconAndText);
		iconAndText.setActionListener(this);
		iconAndText.setSrc("./images/wgt/icons/edit.gif", 16, 16);
		iconAndText.setLabel(TextService.getString("Click Me!"));
		iconAndText.getStyle().addHeight("20px");
		
		Button2Zero iconAndTextVertical = new Button2Zero();
		layout.add(iconAndTextVertical);
		iconAndTextVertical.setActionListener(this);
		iconAndTextVertical.setSrc("./images/wgt/icons/edit.gif", 16, 16);
		iconAndTextVertical.setLabel(TextService.getString("Click Me!"));
		iconAndTextVertical.getStyle().addHeight("40px");
		iconAndTextVertical.setDisplayMode(Button2Zero.DISPLAY_MODE_VERTICAL);

	}

}
