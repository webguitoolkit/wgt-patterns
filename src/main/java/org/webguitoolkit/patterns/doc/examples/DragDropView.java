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

public class DragDropView extends AbstractView {

	public DragDropView(WebGuiFactory factory, Canvas viewConnector) {
		super(factory, viewConnector);
		
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		ICanvas canv1 = factory.createCanvas( viewConnector, "Canv1" );
		canv1.getStyle().addHeight("200px").addWidth("200px");
		canv1.getStyle().add("border", "1px solid black");
		
		ICanvas box = factory.createCanvas( canv1, "box" );
		box.getStyle().addHeight("50px").addWidth("50px");
		box.getStyle().add("border", "1px solid black");
		box.getStyle().add("background-color", "#FFDDDD");
		box.setDragable(true);
		
		ICanvas canv2 = factory.createCanvas( viewConnector, "Canv2" );
		canv2.getStyle().addHeight("200px").addWidth("200px");
		canv2.getStyle().add("border", "1px solid black");

	}

}
