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

import org.webguitoolkit.patterns.prototype.Leaf;
import org.webguitoolkit.patterns.prototype.Node;
import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.IDataBag;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.controls.tree.AbstractTreeListener;
import org.webguitoolkit.ui.controls.tree.GenericTreeModel;
import org.webguitoolkit.ui.controls.tree.GenericTreeNode;
import org.webguitoolkit.ui.controls.tree.ITree;
import org.webguitoolkit.ui.controls.tree.TreeNodeHandler;

public class DragAndDropTreeView extends AbstractView {
	private ITree tree;

	public DragAndDropTreeView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);

	}

	@Override
	protected void createControls( WebGuiFactory factory, ICanvas viewConnector ) {
		// button to show the source code of this class
		factory.createButton( viewConnector, "./images/iconText.gif", "Source Code","Source Code", new SourceView( factory, viewConnector, this.getClass().getName() ) );

		ITableLayout layout = factory.createTableLayout(viewConnector);
		tree = factory.createTree(layout);
		tree.getStyle().add( "width","400px");
		tree.getStyle().add( "height","400px");
		tree.getStyle().add( "overflow","scroll");

		// check this if round trip is needed on check
		tree.setFireOnCheckEvent(true);


		// create tree node handler for Node instances digging for "children"
		// and presenting itself with folder icon. Nodes can be dragged and dropped
		TreeNodeHandler nodeClassHandler = new TreeNodeHandler( Node.class );
		nodeClassHandler.setChildSelectors(new String[] { "children" });
		nodeClassHandler.setDisplayProperty("caption");
		nodeClassHandler.setIconSrc("images/wgt/tree/folderClosed.gif",
				"images/wgt/tree/folderOpen.gif", "images/wgt/tree/folderClosed.gif");
		nodeClassHandler.setHasCheckboxes(false);
		// add node action listener
		nodeClassHandler.setListener(new DragDropTreeListener());

		// create default node handler for all non.Nodes instances
		TreeNodeHandler defaultHandler = new TreeNodeHandler();
		defaultHandler.setDisplayProperty("caption");
		defaultHandler.setIconSrc("images/wgt/new.gif", "images/wgt/new.gif",
				"images/wgt/new.gif");
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

		// configure the Drag & Drop capabilities
		defaultHandler.setDraggable(true);
		nodeClassHandler.setDraggable(true);
		nodeClassHandler.setDroppable(true);

		tree.load();

	}

	class TreeListener extends AbstractTreeListener {

		public void onTreeNodeClicked(ITree tree, String nodeId) {
			getPage().sendWarn("You clicked " + nodeId);
		}

		public void onTreeNodeChecked(ITree tree, String nodeId) {
			getPage().sendWarn("You checked " + nodeId);
		}

	};

	class DragDropTreeListener extends AbstractTreeListener {

		// called when someone dropped something into the tree
		@SuppressWarnings("unchecked")
		public void onTreeNodeDropped(ITree tree, String nodeId, String droppedId) {
			GenericTreeNode droppedTo = (GenericTreeNode) tree.getModel().getTreeNode(nodeId);

			ITree source = tree;

			GenericTreeNode treeNode = (GenericTreeNode) source.getModel().getTreeNode(droppedId);

			// copy nodes
			Node target = (Node) droppedTo.getDataObject().getDelegate();
			target.getChildren().add(treeNode.getDataObject());
			tree.reloadChildren(droppedTo);

			IDataBag dataBag = treeNode.getDataObject();

			if (dataBag.getDelegate() instanceof Leaf) { // move
				// remove from old location

				// Node sourceNode = (Node)
				// draggedFrom.getDataObject().getDelegate();
				// sourceNode.getChildren().remove(treeNode.getDataObject());
				// tree.reloadChildren(draggedFrom);
			}
		}

		public void onTreeNodeClicked(ITree tree, String nodeId) {
			// nothing to do
		}

	};



}
