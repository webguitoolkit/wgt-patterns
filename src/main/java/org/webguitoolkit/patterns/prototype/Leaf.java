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

public class Leaf implements Serializable {
	String caption;
	boolean checked = false;

	public Leaf(String string) {
		caption = string;
	}
	public Leaf(String string, boolean checked ) {
		caption = string;
		this.checked = checked;
	}

	public String getCaption() {
		return caption;
	}

	public boolean isChecked() {
		return checked;
	}
}
