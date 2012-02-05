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
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.event.IActionListener;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.controls.tree.AbstractTreeListener;
import org.webguitoolkit.ui.controls.tree.GenericTreeModel;
import org.webguitoolkit.ui.controls.tree.ITree;
import org.webguitoolkit.ui.controls.tree.ITreeNode;
import org.webguitoolkit.ui.controls.tree.TreeNodeHandler;

public class CheckboxTreeView extends AbstractView {
	private ITree tree;

	public CheckboxTreeView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);

	}

	@Override
	protected void createControls( WebGuiFactory factory, ICanvas viewConnector ) {
		// button to show the source code of this class
		factory.createButton( viewConnector, "./images/iconText.gif", "Source Code","Source Code", new SourceView( factory, viewConnector, this.getClass().getName() ) );

		ITableLayout layout = factory.createTableLayout(viewConnector);

		// create tree
		tree = factory.createTree(layout);

		// check this if round trip is needed on check
		tree.setFireOnCheckEvent(true);
		

		// create tree node handler for Node instances digging for "children"
		// and presenting itself with folder icon. Nodes can be dragged and dropped
		TreeNodeHandler nodeClassHandler = new TreeNodeHandler( Node.class );
		nodeClassHandler.setChildSelectors(new String[] { "children" });
		nodeClassHandler.setDisplayProperty("caption");
		nodeClassHandler.setIconSrc("images/wgt/tree/ltFld.gif",
				"images/wgt/tree/ltFld.gif", "wgt/tree/ltFld.gif");
		nodeClassHandler.setHasCheckboxes(false);

		// create default node handler for all non.Nodes instances
		TreeNodeHandler defaultHandler = new TreeNodeHandler();
		defaultHandler.setDisplayProperty("caption");
		defaultHandler.setIconSrc("images/wgt/icons/new.gif", "images/wgt/icons/new.gif",
				"images/wgt/icons/new.gif");
		defaultHandler.setHasCheckboxes(true);
		defaultHandler.setCheckboxParameter("checked");
		// add node action listener
		defaultHandler.setListener(new TreeListener());

		// create a generic tree model and apply to tree
		GenericTreeModel gtm = new GenericTreeModel(true, true, true, false);
		gtm.setDefaultTreeNodeHandler(defaultHandler);
		gtm.addTreeNodeHandler(nodeClassHandler);
		gtm.setRoot(new Node("Node"));
		tree.setModel(gtm);

		tree.load();
		
		// button to show the selected nodes
		factory.createButton( viewConnector, null, "Selected Nodes", null, new ShowSelectionListener() );
	}
	
	class ShowSelectionListener implements IActionListener{

		public void onAction(ClientEvent event) {
			String selectedNodes = "Selected Nodes: ";
			
			// get checked nodeIds from tree
			String[] selectedNodeIds = tree.getCheckedNodeIds();
			for( String selectedId : selectedNodeIds ){
				// get node from model
				ITreeNode treeNode = tree.getModel().getTreeNode( selectedId );
				selectedNodes += treeNode.getCaption() + ", ";
			}
			getPage().sendInfo( selectedNodes );
		}
	}

	class TreeListener extends AbstractTreeListener {

		public void onTreeNodeClicked(ITree tree, String nodeId) {
			// not needed for this example
		}

		public void onTreeNodeChecked(ITree tree, String nodeId) {
			ITreeNode clicked = tree.getModel().getTreeNode( nodeId );
			getPage().sendInfo("You checked " + clicked.getCaption() );
		}
	}
}
