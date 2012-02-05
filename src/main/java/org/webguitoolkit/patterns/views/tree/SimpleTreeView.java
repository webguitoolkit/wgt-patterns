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
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.controls.tree.AbstractTreeListener;
import org.webguitoolkit.ui.controls.tree.DefaultTreeModel;
import org.webguitoolkit.ui.controls.tree.DefaultTreeNode;
import org.webguitoolkit.ui.controls.tree.ITree;
import org.webguitoolkit.ui.controls.tree.ITreeNode;

public class SimpleTreeView extends AbstractView {
	private ITree tree;

	public SimpleTreeView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);

	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		// button to show the source code of this class
		factory.createButton(viewConnector, "./images/iconText.gif", "Source Code", "Source Code", new SourceView(factory, viewConnector,
				this.getClass().getName()));

		ITableLayout layout = factory.createTableLayout(viewConnector);
		// create tree
		tree = factory.createTree(layout);

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
		DefaultTreeModel dtm = new DefaultTreeModel(true, true, false, false);

		// create root node
		DefaultTreeNode rootNode = new DefaultTreeNode();
		rootNode.setCaption("Root");

		// create child
		DefaultTreeNode child1Node = new DefaultTreeNode();
		child1Node.setCaption("Child1");
		child1Node.setListener(new TreeListener());
		// add child to root
		rootNode.getChildren().add(child1Node);

		DefaultTreeNode child11Node = new DefaultTreeNode();
		child11Node.setCaption("Child1.1");
		child11Node.setListener(new TreeListener());
		child1Node.getChildren().add(child11Node);

		DefaultTreeNode child2Node = new DefaultTreeNode();
		child2Node.setCaption("Child2");
		child2Node.setListener(new TreeListener());
		rootNode.getChildren().add(child2Node);

		DefaultTreeNode child3Node = new DefaultTreeNode();
		child3Node.setCaption("Child3");
		child3Node.setListener(new TreeListener());
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
			ITreeNode clicked = tree.getModel().getTreeNode(nodeId);
			getPage().sendInfo("You clicked " + clicked.getCaption());
		}
	};

}
