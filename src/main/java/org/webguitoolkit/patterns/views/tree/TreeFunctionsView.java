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

import org.apache.commons.lang.StringUtils;
import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractPopup;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.Page;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.event.IActionListener;
import org.webguitoolkit.ui.controls.form.ILabel;
import org.webguitoolkit.ui.controls.form.IText;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.controls.tree.DefaultTreeModel;
import org.webguitoolkit.ui.controls.tree.DefaultTreeNode;
import org.webguitoolkit.ui.controls.tree.ITree;

public class TreeFunctionsView extends AbstractView {
	private ITree tree;

	public TreeFunctionsView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);

	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		// button to show the source code of this class
		factory.createButton(viewConnector, "./images/iconText.gif", "Source Code", "Source Code", new SourceView(factory, viewConnector,
				this.getClass().getName()));

		ITableLayout layout = factory.createTableLayout(viewConnector);

		ITableLayout buttonLayout = factory.createTableLayout(layout);

		// Buttons for new, edit und delete
		factory.createButton(buttonLayout, null, "new Child", "new Child", new NewChildButtonListenter());
		factory.createButton(buttonLayout, null, "edit Node", "edit Node", new EditButtonListenter());
		factory.createButton(buttonLayout, null, "delete Node", "delete Node", new DeleteButtonListenter());
		factory.createButton(buttonLayout, null, "select RootNode", "select RootNode", new SelectButtonListenter());

		layout.newRow();

		// new tree instance
		tree = factory.createTree(layout);

		// create the tree model and apply to tree
		tree.setModel(getModel());
		tree.load();

	}

	public class DeleteButtonListenter implements IActionListener {
		public void onAction(ClientEvent event) {
			if (StringUtils.isEmpty(tree.getSelectedNodeId())) {
				getPage().sendInfo("No node selected!");
				return;
			}
			DefaultTreeNode treeNode = (DefaultTreeNode)tree.getModel().getTreeNode(tree.getSelectedNodeId());
			DefaultTreeNode parent = (DefaultTreeNode)treeNode.getParent();
			if (parent == null) {
				getPage().sendInfo("Can't delete Root node!");
				return;
			}
			// remove in Model
			parent.getChildren().remove(treeNode);

			// reload the children of the parent, so the delete is not displayed anymore
			tree.reloadChildren(parent);
		}
	}

	public class NewChildButtonListenter implements IActionListener {
		public void onAction(ClientEvent event) {
			if (StringUtils.isEmpty(tree.getSelectedNodeId())) {
				getPage().sendInfo("No node selected!");
				return;
			}
			// show popup for new child
			EditNodePopup popup = new EditNodePopup(getFactory(), getPage(), "New Node", 500, 200, null, tree);
			popup.show();
		}
	}

	public class EditButtonListenter implements IActionListener {
		public void onAction(ClientEvent event) {
			if (StringUtils.isEmpty(tree.getSelectedNodeId())) {
				getPage().sendInfo("No node selected!");
				return;
			}
			// show popup for edit node
			EditNodePopup popup = new EditNodePopup(getFactory(), getPage(), "Edit Node", 500, 200,
					(DefaultTreeNode)tree.getModel().getTreeNode(tree.getSelectedNodeId()), tree);
			popup.show();
		}
	}

	public class SelectButtonListenter implements IActionListener {
		public void onAction(ClientEvent event) {
			tree.selectNode( tree.getModel().getRootElement() );
		}
	}

	/**
	 * create the model and add some initial nodes
	 */
	private DefaultTreeModel getModel() {
		DefaultTreeModel dtm = new DefaultTreeModel(true, true, false, false);
		DefaultTreeNode rootNode = new DefaultTreeNode();
		rootNode.setCaption("Root");

		DefaultTreeNode child1Node = new DefaultTreeNode();
		child1Node.setCaption("Child1");
		rootNode.getChildren().add(child1Node);

		DefaultTreeNode child11Node = new DefaultTreeNode();
		child11Node.setCaption("Child1.1");
		child1Node.getChildren().add(child11Node);

		DefaultTreeNode child2Node = new DefaultTreeNode();
		child2Node.setCaption("Child2");
		rootNode.getChildren().add(child2Node);

		DefaultTreeNode child3Node = new DefaultTreeNode();
		child3Node.setCaption("Child3");
		rootNode.getChildren().add(child3Node);

		dtm.setRootElement(rootNode);
		return dtm;
	}

	/**
	 * Popup for edit or create the nodes
	 */
	public class EditNodePopup extends AbstractPopup {

		DefaultTreeNode node;
		ITree tree;
		private IText captionText;

		public EditNodePopup(WebGuiFactory factory, Page page, String titel, int width, int height, DefaultTreeNode node, ITree tree) {
			super(factory, page, titel, width, height);
			this.node = node;
			this.tree = tree;
		}

		@Override
		protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {

			ITableLayout layout = factory.createTableLayout(viewConnector);

			ILabel label = factory.createLabel(layout, "Caption");
			captionText = factory.createText(layout, "caption");
			captionText.setDescribingLabel(label);

			if (node != null)
				captionText.setValue(node.getCaption());

			layout.newRow();

			factory.createButton(layout, "./images/wgt/icons/save.gif", "save", "save", new SaveListener());
		}

		/**
		 * SaveListener for creating or updating the model and the tree
		 */
		class SaveListener implements IActionListener {
			public void onAction(ClientEvent event) {
				if (node != null) {
					// update the model
					node.setCaption(captionText.getValue());
					// update the tree
					tree.updateNodeLabel(node);
				}
				else {
					// create new node
					DefaultTreeNode newNode = new DefaultTreeNode();
					newNode.setCaption(captionText.getValue());
					// get current selected
					String nodeId = tree.getSelectedNodeId();
					DefaultTreeNode parent = (DefaultTreeNode)tree.getModel().getTreeNode(nodeId);
					// update the model
					parent.getChildren().add(newNode);
					// update the tree
					tree.reloadChildren(parent);
				}
				EditNodePopup.this.close();
			}
		}
	}
}
