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

import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.Canvas;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.event.IActionListener;
import org.webguitoolkit.ui.controls.form.ICheckBox;
import org.webguitoolkit.ui.controls.form.IRadio;
import org.webguitoolkit.ui.controls.form.IText;
import org.webguitoolkit.ui.controls.layout.ITableLayout;

public class InputView extends AbstractView {

	public InputView(WebGuiFactory factory, Canvas viewConnector) {
		super(factory, viewConnector);
	
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		factory = new WebGuiFactory();
		ITableLayout layout = factory.createTableLayout(viewConnector);
		
		final IText textInput = factory.createText(layout, "text");
		layout.newRow();
		final IRadio r1 = factory.createRadio(layout, "radiogroup1", "A");
		final IRadio r2 = factory.createRadio(layout, "radiogroup1", "B");
		layout.newRow();
		final ICheckBox c1 = factory.createCheckBox(layout, "check");
		layout.newRow();
		factory.createButton(layout, "images/wgt/refresh.gif", null, "Read input", new IActionListener() {
			public void onAction(ClientEvent event) {
				String msg = "Your input: " + textInput.getValue();
				msg += "<br>A is selected :" + r1.isSelected();
				msg += "<br>B is selected :" + r2.isSelected();
				msg += "<br>Checkbox is checked :" + c1.isSelected();
				getPage().sendInfo(msg);
			}
		});

	}

}
