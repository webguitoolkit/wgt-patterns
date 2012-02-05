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
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.event.IActionListener;
import org.webguitoolkit.ui.controls.form.ILabel;
import org.webguitoolkit.ui.controls.form.IText;

public class PickTextView extends AbstractView {
	private IText text;
	private ILabel output;

	public PickTextView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}
	
	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		factory.createButton( viewConnector, "./images/iconText.gif", "Source Code","Source Code", new SourceView( factory, viewConnector, this.getClass().getName() ) );

		ILabel label = factory.createLabel(getViewConnector(), "Root ");
		text = factory.createText(getViewConnector(), "name", label);
		output = factory.createLabel(getViewConnector(), "<>");
		factory.createButton(getViewConnector(), null, "Select", "",
				new IActionListener() {

					public void onAction(ClientEvent arg0) {
						String value = text.getValue();
						output.setText(value);
					}
				});

	}

}
