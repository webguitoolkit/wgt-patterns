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
package org.webguitoolkit.patterns.views.simple;

import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.form.ILabel;
import org.webguitoolkit.ui.controls.layout.ITableLayout;

public class LabelView extends AbstractView {

	public LabelView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {

		// button to show the source code of this class
		factory.createButton(viewConnector, "./images/iconText.gif", "Source", "Source Code", 
				new SourceView(factory, viewConnector, this.getClass().getName()));

		ITableLayout layout = factory.createTableLayout(viewConnector, null);

		factory.createLabel(layout, "My label");
		layout.newRow();

		// create label with tool tip
		ILabel label = factory.createLabel(layout, "Another label with tooltip");
		label.setTooltip("The tooltip of a label");

		layout.newRow();

		// create label with style attributes
		ILabel bold = factory.createLabel(layout, "Another label with background and font style");
		//Uses style defined in the wgtPatters file. .boldLabel{background-color:#f0f0f0;font-size:20px;}
		bold.addCssClass("boldLabel");
	}

}
