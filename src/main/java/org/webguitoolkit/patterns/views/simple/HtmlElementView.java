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
import org.webguitoolkit.ui.controls.container.IHtmlElement;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.event.IActionListener;
import org.webguitoolkit.ui.controls.layout.ITableLayout;


public class HtmlElementView extends AbstractView {

	public HtmlElementView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls( WebGuiFactory factory, ICanvas viewConnector ) {
		// button to show the source code of this class
		factory.createButton( viewConnector, "./images/iconText.gif", "Source Code","Source Code", new SourceView( factory, viewConnector, this.getClass().getName() ) );

		// layout for demo page
		ITableLayout layout = factory.createTableLayout(viewConnector);
		//element description
		factory.createLabel(layout, "Image:");
		
		// create element add attach to layout
		IHtmlElement img = factory.createHtmlElement(layout,"img");
		// set element attributes
		img.setAttribute("src", "images/wgt/ajax-loader-big.gif");
		img.setAttribute("alt", "status gif");
		img.setAttribute("title", "status gif");
		
		layout.newRow();
		
		//element description
		factory.createLabel(layout, "IFrame:");
		
		// create element add attach to layout
		final IHtmlElement iframe = factory.createHtmlElement(layout,"iframe");
		// set element attributes
		iframe.setAttribute("src", "http://wetterstationen.meteomedia.de/messnetz/forecast/098020.html");
		iframe.setAttribute("width","800"); 
		iframe.setAttribute("height","400");
		iframe.setAttribute("name","SELFHTML_in_a_box");

		layout.newRow();

		factory.createButton(layout, null, "google", null, new IActionListener(){
			public void onAction(ClientEvent event) {		
				iframe.setAttribute("src", "http://google.de");
			}
		});

		layout.newRow();

		// create element add attach to layout
		IHtmlElement a = factory.createHtmlElement(layout, "a" );
		// set element attributes
		a.setAttribute("href", "http://wetterstationen.meteomedia.de/messnetz/forecast/098020.html");
		a.setAttribute("onkeydown", "if (event.keyCode==13) { document.location.href = 'http://wetterstationen.meteomedia.de/messnetz/forecast/098020.html'; };");
		a.innerHtml("Simple Html link");
	}

}
