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
package org.webguitoolkit.patterns.doc.examples;

import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.Canvas;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.event.IActionListener;
import org.webguitoolkit.ui.controls.menu.IMenu;
import org.webguitoolkit.ui.controls.menu.IMenuBar;

public class MenuView extends AbstractView {

	public MenuView(WebGuiFactory factory, Canvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		IMenuBar menuBar = factory.createMenuBar(viewConnector);
		IMenu menu1 = factory.createMenu(menuBar, "Menu 1");
		IActionListener listen = new MenuListener();
		factory.createMenuItem( menu1, "Menu 1.1", null, listen, "M11");
		factory.createMenuItem( menu1, "Menu 1.2", null, listen, "M12");
		IMenu menu2 = factory.createMenu(menuBar, "Menu 2");
		factory.createMenuItem(menu2, "Menu 2.1", null, listen, "M21");
		factory.createMenuItem(menu2, "Menu 2.2", null, listen, "M22");
		
		IMenu menu21 = factory.createMenu(menu2, "Menu 2.3");
		factory.createMenuItem(menu21, "Menu 2.3.1", null, listen, "M231");
		
	}

	class MenuListener implements IActionListener {

		public void onAction(ClientEvent event) {
			getPage().sendInfo("You clicked " + event.getSource().getId() );
		}
		
	}
}
