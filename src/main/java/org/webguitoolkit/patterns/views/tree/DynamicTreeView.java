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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

public class DynamicTreeView extends AbstractView {
	private ITree tree;

	public DynamicTreeView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);

	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		// button to show the source code of this class
		factory.createButton(viewConnector, "./images/iconText.gif", "Source Code", "Source Code", new SourceView(factory, viewConnector,
				this.getClass().getName()));

		ITableLayout layout = factory.createTableLayout(viewConnector);
		tree = factory.createTree(layout);

		// create tree node handler for Node instances digging for "children"
		// and presenting itself with folder icon. Nodes can be dragged and dropped
		TreeNodeHandler nodeClassHandler = new TreeNodeHandler(Node.class);
		nodeClassHandler.setChildSelectors(new String[] { "leafs" });
		nodeClassHandler.setDisplayProperty("name");
		nodeClassHandler.setTooltipProperty("name");
		nodeClassHandler.setIconSrc("images/wgt/tree/ltFld.gif", "images/wgt/tree/ltFld.gif", "images/wgt/tree/ltFld.gif");
		nodeClassHandler.setHasCheckboxes(false);

		// create default node handler for all non.Nodes instances
		TreeNodeHandler defaultHandler = new TreeNodeHandler();
		defaultHandler.setDisplayProperty("description");
		defaultHandler.setIconSrc("images/wgt/tree/ltDoc.gif", "images/wgt/tree/ltDoc.gif", "images/wgt/tree/ltDoc.gif");
		// add node action listener
		defaultHandler.setListener(new TreeListener());

		// create a generic tree model and apply to tree
		GenericTreeModel gtm = new GenericTreeModel(true, true, false, false);
		gtm.setDefaultTreeNodeHandler(defaultHandler);
		gtm.addTreeNodeHandler(nodeClassHandler);
		gtm.setRoot(new Node(""));
		tree.setModel(gtm);
		tree.load();
	}

	class TreeListener extends AbstractTreeListener {
		public void onTreeNodeClicked(ITree tree, String nodeId) {
			GenericTreeNode clicked = (GenericTreeNode)tree.getModel().getTreeNode(nodeId);
			Leaf leaf = (Leaf)clicked.getDataObject().getDelegate();
			getPage().sendInfo("You clicked " + leaf.getDescription());
		}
	};

	/**
	 * Dummy Object to be displayed in tree
	 */
	public class Node implements Serializable {

		String name;
		boolean checked = false;
		List children = new ArrayList();

		public Node(String string) {
			name = "Node" + string;
			// avoid endless loop
			if (name.length() < 8) {
				children.add(new Node(string + ".1"));
				children.add(new Node(string + ".2"));
				children.add(new Node(string + ".3"));
				children.add(new Leaf(string + ".1"));
				children.add(new Leaf(string + ".2"));
				children.add(new Leaf(string + ".3"));
			}
		}

		public List getLeafs() {
			return children;
		}

		public String getName() {
			return name;
		}

		public boolean isChecked() {
			return checked;
		}

	}

	/**
	 * Dummy Object to be displayed in tree
	 */
	public class Leaf implements Serializable {
		String description;
		boolean checked = false;

		public Leaf(String string) {
			description = "Leaf" + string;
		}

		public String getDescription() {
			return description;
		}

		public boolean isChecked() {
			return checked;
		}
	}
}
