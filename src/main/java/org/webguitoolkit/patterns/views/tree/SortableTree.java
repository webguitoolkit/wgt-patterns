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

import java.util.List;

import org.webguitoolkit.patterns.prototype.Leaf;
import org.webguitoolkit.patterns.prototype.Node;
import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.controls.tree.AbstractTreeListener;
import org.webguitoolkit.ui.controls.tree.GenericTreeModel;
import org.webguitoolkit.ui.controls.tree.GenericTreeNode;
import org.webguitoolkit.ui.controls.tree.ITree;
import org.webguitoolkit.ui.controls.tree.TreeNodeHandler;

/**
 * This example shows how to implement a tree where the nodes (leafs) can be sorted within a folder.
 * 
 * @author peter
 * 
 */
public class SortableTree extends AbstractView {
	private ITree tree;

	public SortableTree(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);

	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		// button to show the source code of this class
		factory.createButton(viewConnector, "./images/iconText.gif", "Source Code", "Source Code", new SourceView(factory, viewConnector,
				this.getClass().getName()));

		ITableLayout layout = factory.createTableLayout(viewConnector);
		tree = factory.createTree(layout);
		// the treeStyle class is defined in the wgtPatter.css File and looks like this
		// .treeStyle{overflow:scroll;width:400px;height:400px;}
		tree.addCssClass("treeStyle");

		// check this if round trip is needed on check
		tree.setFireOnCheckEvent(true);

		// create tree node handler for Node instances digging for "children"
		// and presenting itself with folder icon. Nodes can be dragged and dropped
		TreeNodeHandler nodeClassHandler = new TreeNodeHandler(Node.class);
		nodeClassHandler.setChildSelectors(new String[] { "children" });
		nodeClassHandler.setDisplayProperty("caption");
		nodeClassHandler.setIconSrc("images/wgt/tree/ltFld.gif", "images/wgt/tree/ltFld.gif", "images/wgt/tree/ltFld.gif");
		nodeClassHandler.setHasCheckboxes(false);
		// add node action listener
		nodeClassHandler.setListener(new DragDropTreeListener());

		// create default node handler for all non.Nodes instances
		TreeNodeHandler defaultHandler = new TreeNodeHandler();
		defaultHandler.setDisplayProperty("caption");
		defaultHandler.setIconSrc("images/wgt/tree/ltDoc.gif", "images/wgt/tree/ltDoc.gif", "images/wgt/tree/ltDoc.gif");
		defaultHandler.setHasCheckboxes(false);

		// add node action listener
		defaultHandler.setListener(new DragDropTreeListener());

		// create a generic tree model and apply to tree
		GenericTreeModel gtm = new GenericTreeModel(true, true, true, false);
		gtm.setDefaultTreeNodeHandler(defaultHandler);
		gtm.addTreeNodeHandler(nodeClassHandler);
		gtm.setRoot(new Node("Root"));
		tree.setModel(gtm);

		// configure the Drag & Drop capabilities
		defaultHandler.setDraggable(true);
		defaultHandler.setDroppable(true);

		nodeClassHandler.setDraggable(true);
		nodeClassHandler.setDroppable(true);

		tree.load();
	}

	class DragDropTreeListener extends AbstractTreeListener {

		// called when someone dropped something into the tree
		@SuppressWarnings("unchecked")
		public void onTreeNodeDropped(ITree tree, String targetId, String sourceId) {
			GenericTreeNode targetNode = (GenericTreeNode)tree.getModel().getTreeNode(targetId);
			GenericTreeNode sourceNode = (GenericTreeNode)tree.getModel().getTreeNode(sourceId);

			Object source = sourceNode.getDataObject().getDelegate();
			Object target = targetNode.getDataObject().getDelegate();

			GenericTreeNode sourceParentNode = (GenericTreeNode)sourceNode.getParent();

			if (target instanceof Leaf && source instanceof Leaf) {
				GenericTreeNode targetParentNode = (GenericTreeNode)targetNode.getParent();
				if (sourceParentNode == targetParentNode) {
					// reorder
					Node node = (Node)sourceParentNode.getDataObject().getDelegate();
					List children = node.getChildren();
					int indexTarget = children.indexOf(targetNode.getDataObject().getDelegate());
					children.remove(sourceNode.getDataObject().getDelegate());
					children.add(indexTarget, sourceNode.getDataObject().getDelegate());
					tree.reloadChildren(targetParentNode);
				}
				else {
					Node sourceParent = (Node)sourceParentNode.getDataObject().getDelegate();
					List sourceChildren = sourceParent.getChildren();

					Node targetParent = (Node)targetParentNode.getDataObject().getDelegate();
					List targetChildren = targetParent.getChildren();

					int indexTarget = targetChildren.indexOf(targetNode.getDataObject().getDelegate());
					targetChildren.add(indexTarget + 1, sourceNode.getDataObject().getDelegate());
					sourceChildren.remove(sourceNode.getDataObject().getDelegate());
					tree.reloadChildren(targetParentNode);
					tree.reloadChildren(sourceParentNode);
					tree.expandNode(sourceParentNode);
					tree.expandNode(targetParentNode);
				}
			}
			else if (target instanceof Node) {
				Node sourceParent = (Node)sourceParentNode.getDataObject().getDelegate();
				List sourceChildren = sourceParent.getChildren();
				sourceChildren.remove(sourceNode.getDataObject().getDelegate());

				((Node)target).getChildren().add(sourceNode.getDataObject().getDelegate());
				tree.reloadChildren(targetNode);
				tree.reloadChildren(sourceParentNode);
			}
			else {
				tree.getPage().sendWarn("Can't move a folder into a Node!");
			}
		}

		public void onTreeNodeClicked(ITree tree, String nodeId) {
			// nothing to do
		}
	};
}
