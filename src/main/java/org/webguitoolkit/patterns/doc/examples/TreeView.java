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
package org.webguitoolkit.patterns.doc.examples;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.Canvas;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.tree.GenericTreeModel;
import org.webguitoolkit.ui.controls.tree.ITree;
import org.webguitoolkit.ui.controls.tree.TreeNodeHandler;

public class TreeView extends AbstractView {

	public TreeView(WebGuiFactory factory, Canvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {

		ITree tree = factory.createTree(viewConnector);

		// create default node handler for all folder instances
		TreeNodeHandler nodeClassHandler = new TreeNodeHandler(Node.class);
		nodeClassHandler.setChildSelectors(new String[] { "children" });
		nodeClassHandler.setDisplayProperty("caption");
		nodeClassHandler.setHasCheckboxes(false);

		TreeNodeHandler leafClassHandler = new TreeNodeHandler(Leaf.class);
		leafClassHandler.setDisplayProperty("caption");
		leafClassHandler.setHasCheckboxes(false);

		GenericTreeModel gtm = new GenericTreeModel(true, true, false, false);
		gtm.setDefaultTreeNodeHandler(nodeClassHandler);
		gtm.addTreeNodeHandler(leafClassHandler);

		gtm.setRoot(new Node(""));
		tree.setModel(gtm);

		tree.load();

	}

	public class Node implements Serializable {
		String caption;
		List children = new ArrayList();

		public Node(String string) {
			caption = string;
			if (caption.length() < 6) {
				children.add(new Node(caption + "-1"));
				children.add(new Node(caption + "-2"));
				children.add(new Node(caption + "-3"));
				children.add(new Leaf(caption + "-1"));
				children.add(new Leaf(caption + "-2"));
				children.add(new Leaf(caption + "-3"));
			}
		}

		public List getChildren() {
			return children;
		}

		public String getCaption() {
			return "Node" + caption;
		}
	}

	public class Leaf implements Serializable {
		String caption;
		boolean checked = false;

		public Leaf(String string) {
			caption = string;
		}

		public String getCaption() {
			return "Leaf" + caption;
		}
	}
}
