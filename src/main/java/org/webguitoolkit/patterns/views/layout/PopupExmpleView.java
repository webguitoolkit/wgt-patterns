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
import org.webguitoolkit.ui.controls.AbstractPopup;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.Page;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.event.IActionListener;

public class PopupExmpleView extends AbstractView {
	
	private class Popup extends AbstractPopup{
		public Popup(WebGuiFactory factory, Page page, String titel, int width, int height) {
			super(factory, page, titel, width, height);
		}

		@Override
		protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
			getFactory().createLabel(viewConnector, "I'm the reqested popup. You could call close() to close me!");
		}
	}

	public PopupExmpleView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		// button to show the source code of this class
		factory.createButton( viewConnector, "./images/iconText.gif", "Source Code","Source Code", new SourceView( factory, viewConnector, this.getClass().getName() ) );
		ICanvas canvas = getFactory().createCanvas(viewConnector);
		factory.createButton( canvas, null, "Show Popup",null, new Popup( factory, getPage(), "MyTitle", 300, 200 ) );
		factory.createButton( canvas, null, "Show Popup2",null, new IActionListener() {
			
			public void onAction(ClientEvent event) {
				try {
					Thread.sleep(1000);
				}
				catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				new Popup( getFactory(), getPage(), "MyTitle", 300, 200 ).show();
				
			}
		});
	}
}
