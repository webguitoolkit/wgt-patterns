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
import org.webguitoolkit.ui.controls.tab.ITab;
import org.webguitoolkit.ui.controls.tab.ITabStrip;

public class TabstripView extends AbstractView {

	public TabstripView(WebGuiFactory factory, Canvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		ITabStrip tabstrip = factory.createTabStrip(viewConnector);

		tabstrip.getStyle().addHeight("50px");
		tabstrip.getStyle().addWidth("200px");

		ITab summary = factory.createTab(tabstrip, "Summary");
		factory.createLabel(summary, " I'm the content of the summary tab");

		ITab detail = factory.createTab(tabstrip, "Details");
		factory.createLabel(detail, " I'm the content of the detail tab");

	}

}
