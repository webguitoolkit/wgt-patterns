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
package org.webguitoolkit.patterns.views.tree;

import org.webguitoolkit.patterns.prototype.Node;
import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.IBaseControl;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.contextmenu.ContextMenuItem;
import org.webguitoolkit.ui.controls.contextmenu.IContextMenu;
import org.webguitoolkit.ui.controls.contextmenu.IContextMenuListener;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.controls.table.ITable;
import org.webguitoolkit.ui.controls.tree.GenericTreeModel;
import org.webguitoolkit.ui.controls.tree.ITree;
import org.webguitoolkit.ui.controls.tree.TreeNodeHandler;

public class ContextMenuTreeView extends AbstractView {
	private ITree tree;

	public ContextMenuTreeView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls( WebGuiFactory factory, ICanvas viewConnector ) {
		// button to show the source code of this class
		factory.createButton( viewConnector, "./images/iconText.gif", "Source Code","Source Code", new SourceView( factory, viewConnector, this.getClass().getName() ) );

		ITableLayout layout = factory.createTableLayout(viewConnector);
		tree = factory.createTree(layout);
		//the treeStyle class is defined in the wgtPatter.css File and looks like this .treeStyle{overflow:scroll;width:400px;height:400px;}
		tree.addCssClass("treeStyle");

		// check this if round trip is needed on check
		tree.setFireOnCheckEvent(true);


		// create tree node handler for Node instances digging for "children"
		// and presenting itself with folder icon. Nodes can be dragged and dropped
		TreeNodeHandler nodeClassHandler = new TreeNodeHandler( Node.class );
		nodeClassHandler.setChildSelectors(new String[] { "children" });
		nodeClassHandler.setDisplayProperty("caption");
		nodeClassHandler.setIconSrc("images/wgt/tree/ltFld.gif",
				"images/wgt/tree/ltFld.gif", "images/wgt/tree/ltFld.gif");
		nodeClassHandler.setHasCheckboxes(false);

		// create default node handler for all non.Nodes instances
		TreeNodeHandler defaultHandler = new TreeNodeHandler();
		defaultHandler.setDisplayProperty("caption");
		defaultHandler.setIconSrc("images/wgt/tree/ltDoc.gif", "images/wgt/tree/ltDoc.gif",
				"images/wgt/tree/ltDoc.gif");
		defaultHandler.setHasCheckboxes(true);
		defaultHandler.setCheckboxParameter("checked");

		// create a generic tree model and apply to tree
		GenericTreeModel gtm = new GenericTreeModel(true, true, true, false);
		gtm.setDefaultTreeNodeHandler(defaultHandler);
		gtm.addTreeNodeHandler(nodeClassHandler);
		gtm.setRoot(new Node("Node"));
		tree.setModel(gtm);

		// configure context menu
		IContextMenu defaultContextMenu = getFactory().createContextMenu( tree );
		getFactory().createContextMenuItem(defaultContextMenu, "Edit", new ContextListener());
		getFactory().createContextMenuItem(defaultContextMenu, "Delete", new ContextListener());

		IContextMenu nodeContextMenu = getFactory().createContextMenu( tree );
		getFactory().createContextMenuItem(nodeContextMenu, "New Child Node", new ContextListener());
		getFactory().createContextMenuItem(nodeContextMenu, "New Leaf", new ContextListener());
		getFactory().createContextMenuItem(nodeContextMenu, "Edit", new ContextListener());
		getFactory().createContextMenuItem(nodeContextMenu, "Delete", new ContextListener());

		// asign different context menus to different handlers
		defaultHandler.setContextMenu( defaultContextMenu );
		nodeClassHandler.setContextMenu( nodeContextMenu );

		tree.load();
	}

	class ContextListener implements IContextMenuListener {
		public void onAction(ClientEvent event, IBaseControl control) {
			getPage().sendInfo("You selected " + ((ContextMenuItem)event.getSource()).getLabel() );
		}

		public void onAction(ClientEvent event, ITable table, int row) {
			getPage().sendInfo("You selected " + ((ContextMenuItem)event.getSource()).getLabel() );
		}

		public void onAction(ClientEvent event, ITree tree, String nodeId) {
			getPage().sendInfo("You selected " + ((ContextMenuItem)event.getSource()).getLabel() +" on tree node: " + tree.getModel().getTreeNode( nodeId ).getCaption() );
		}
	}
}
