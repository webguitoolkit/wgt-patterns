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
package org.webguitoolkit.patterns.prototype;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Node implements Serializable {

	String caption;
	boolean checked = false;
	List children = new ArrayList();

	public Node(String string) {
		caption = string;
		if (caption.length() < 8) {
			children.add(new Node(caption + ".1"));
			children.add(new Node(caption + ".2"));
			children.add(new Node(caption + ".3", true));
			children.add(new Leaf(caption + ".1", true));
			children.add(new Leaf(caption + ".2"));
			children.add(new Leaf(caption + ".3"));
		}
	}
	public Node(String string, boolean checked ) {
		this.checked = checked;
		caption = string;
		if (caption.length() < 8) {
			children.add(new Node(caption + ".1"));
			children.add(new Node(caption + ".2"));
			children.add(new Node(caption + ".3", true));
			children.add(new Leaf(caption + ".1", true));
			children.add(new Leaf(caption + ".2"));
			children.add(new Leaf(caption + ".3"));
		}
	}

	public List getChildren() {
		return children;
	}

	public String getCaption() {
		return caption;
	}

	public boolean isChecked() {
		return checked;
	}

}
