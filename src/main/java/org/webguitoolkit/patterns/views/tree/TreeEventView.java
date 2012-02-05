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

import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.form.IButton;
import org.webguitoolkit.ui.controls.form.ILabel;
import org.webguitoolkit.ui.controls.layout.SequentialTableLayout;
import org.webguitoolkit.ui.controls.tree.AbstractTreeListener;
import org.webguitoolkit.ui.controls.tree.DefaultTreeModel;
import org.webguitoolkit.ui.controls.tree.DefaultTreeNode;
import org.webguitoolkit.ui.controls.tree.ITree;
import org.webguitoolkit.ui.controls.tree.ITreeNode;

public class TreeEventView extends AbstractView {
	private ITree tree;
	private ILabel eventLabel;

	public TreeEventView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);

	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		viewConnector.setLayout( new SequentialTableLayout() );

		// button to show the source code of this class
		IButton sourceButton = factory.createButton(viewConnector, "./images/iconText.gif", "Source Code", "Source Code", new SourceView(factory, viewConnector,
				this.getClass().getName()));
		sourceButton.setLayoutData(SequentialTableLayout.getLastInRow());

		eventLabel = factory.createLabel(viewConnector, "");
		eventLabel.setLayoutData( SequentialTableLayout.getLastInRow() );

		// create tree
		tree = factory.createTree(viewConnector);
		tree.setClickFolderMode( ITree.CLICK_FOLDER_MODE_EXPAND );

		// set the model to the tree
		tree.setModel(getModel());
		// load the data to the frontend
		tree.load();

		tree.expandNode(tree.getModel().getRootElement());
	}

	/*
	 * create the model
	 */
	private DefaultTreeModel getModel() {

		// build model
		DefaultTreeModel dtm = new DefaultTreeModel(true, true, true, true);

		// create root node
		DefaultTreeNode rootNode = new DefaultTreeNode();
		rootNode.setCaption("Root");
		rootNode.setListener(new TreeListener());

		// create child
		DefaultTreeNode child1Node = new DefaultTreeNode();
		child1Node.setCaption("Child1");
		child1Node.setListener(new TreeListener());
		child1Node.setHasCheckBox(true);
		child1Node.setDraggable(true);
		child1Node.setDroppable(true);
		// add child to root
		rootNode.getChildren().add(child1Node);

		DefaultTreeNode child11Node = new DefaultTreeNode();
		child11Node.setCaption("Child1.1");
		child11Node.setListener(new TreeListener());
		child11Node.setHasCheckBox(true);
		child11Node.setDraggable(true);
		child11Node.setDroppable(true);
		child1Node.getChildren().add(child11Node);

		DefaultTreeNode child2Node = new DefaultTreeNode();
		child2Node.setCaption("Child2");
		child2Node.setListener(new TreeListener());
		child2Node.setHasCheckBox(true);
		child2Node.setDraggable(true);
		child2Node.setDroppable(true);
		rootNode.getChildren().add(child2Node);

		DefaultTreeNode child3Node = new DefaultTreeNode();
		child3Node.setCaption("Child3");
		child3Node.setListener(new TreeListener());
		child3Node.setHasCheckBox(true);
		child3Node.setDraggable(true);
		child3Node.setDroppable(true);
		rootNode.getChildren().add(child3Node);

		// set the root node to the model
		dtm.setRootElement(rootNode);
		return dtm;
	}

	/*
	 * ActionListener if a tree node is selected
	 */
	class TreeListener extends AbstractTreeListener {
		public void onTreeNodeClicked(ITree tree, String nodeId) {
			ITreeNode node = tree.getModel().getTreeNode(nodeId);
			eventLabel.setText("onTreeNodeClicked: " + node.getCaption() );
		}

		@Override
		public void onTreeNodeChecked(ITree tree, String nodeId) {
			super.onTreeNodeChecked(tree, nodeId);
			ITreeNode node = tree.getModel().getTreeNode(nodeId);
			eventLabel.setText("onTreeNodeChecked: " + node.getCaption() );
		}

		@Override
		public void onTreeNodeDropped(ITree tree, String nodeId, String droppedId) {
			super.onTreeNodeDropped(tree, nodeId, droppedId);
			ITreeNode node = tree.getModel().getTreeNode(nodeId);
			ITreeNode dropped = tree.getModel().getTreeNode(droppedId);
			eventLabel.setText("onTreeNodeDropped: " + dropped.getCaption() + " to " + node.getCaption() );
		}

		@Override
		public void onTreeNodeDragged(ITree tree, String nodeId) {
			super.onTreeNodeDragged(tree, nodeId);
			ITreeNode node = tree.getModel().getTreeNode(nodeId);
			eventLabel.setText("onTreeNodeDragged: " + node.getCaption() );
		}

		@Override
		public void onTreeNodeOpen(ITree tree, String nodeId) {
			super.onTreeNodeOpen(tree, nodeId);
			ITreeNode node = tree.getModel().getTreeNode(nodeId);
			eventLabel.setText("onTreeNodeOpen: " + node.getCaption() );
		}

		@Override
		public void onTreeNodeClose(ITree tree, String nodeId) {
			super.onTreeNodeClose(tree, nodeId);
			ITreeNode node = tree.getModel().getTreeNode(nodeId);
			eventLabel.setText("onTreeNodeClose: " + node.getCaption() );
		}
	};

}
