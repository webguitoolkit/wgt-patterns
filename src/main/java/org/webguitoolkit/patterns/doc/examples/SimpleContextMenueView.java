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
/**
 * 
 */
package org.webguitoolkit.patterns.doc.examples;

import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.BaseControl;
import org.webguitoolkit.ui.controls.container.Canvas;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.contextmenu.BaseContextMenuListener;
import org.webguitoolkit.ui.controls.contextmenu.ContextMenuItem;
import org.webguitoolkit.ui.controls.contextmenu.IContextMenu;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.form.ILabel;

/**
 * @author i102374
 * 
 */
public class SimpleContextMenueView extends AbstractView {

	/**
	 * @param factory
	 * @param viewConnector
	 */
	public SimpleContextMenueView(WebGuiFactory factory, Canvas viewConnector) {
		super(factory, viewConnector);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.endress.infoserve.gui.AbstractView#createControls()
	 */
	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {

		ILabel label = factory.createLabel(viewConnector,
				"I have a context menu. Right click me!");
		// context menu for label2s
		IContextMenu cMenu = factory.createContextMenu(label);

		factory.createContextMenuItem(cMenu, "Menu item 1", new ContextListener());
		factory.createContextMenuItem(cMenu, "Menu item 2", new ContextListener());
	}

	class ContextListener extends BaseContextMenuListener {
		public void onAction(ClientEvent event, BaseControl control) {
			getPage().sendInfo(
					"You selected "
							+ ((ContextMenuItem) event.getSource()).getLabel());
		}
	}
}
