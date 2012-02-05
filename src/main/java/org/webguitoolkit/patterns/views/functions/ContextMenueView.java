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
package org.webguitoolkit.patterns.views.functions;

import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.IBaseControl;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.contextmenu.ContextMenuItem;
import org.webguitoolkit.ui.controls.contextmenu.IContextMenu;
import org.webguitoolkit.ui.controls.contextmenu.IContextMenuListener;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.form.ILabel;
import org.webguitoolkit.ui.controls.table.ITable;
import org.webguitoolkit.ui.controls.tree.ITree;

/**
 * @author i102374
 * 
 */
public class ContextMenueView extends AbstractView {

	/**
	 * @param factory
	 * @param viewConnector
	 */
	public ContextMenueView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.endress.infoserve.gui.AbstractView#createControls()
	 */
	@Override
	protected void createControls( WebGuiFactory factory, ICanvas viewConnector ) {
		// button to show the source code of this class
		factory.createButton( viewConnector, "./images/iconText.gif", "Source Code","Source Code", new SourceView( factory, viewConnector, this.getClass().getName() ) );
		
		ILabel label = factory.createLabel(viewConnector, "I have a context menu. Right click me!");
		// context menu for label2s
		IContextMenu cMenu = factory.createContextMenu( label );

		factory.createContextMenuItem(cMenu, "Item 1", new ContextListener());
		factory.createContextMenuItem(cMenu, "Item 2", new ContextListener());
	}

	class ContextListener implements IContextMenuListener {

		public void onAction(ClientEvent event, IBaseControl control) {
			getPage().sendInfo("You selected " + ((ContextMenuItem)event.getSource()).getLabel() );
		}

		public void onAction(ClientEvent event, ITable table, int row) {
		}

		public void onAction(ClientEvent event, ITree tree, String nodeId) {
		}
	}
}
