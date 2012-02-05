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
package org.webguitoolkit.patterns.views;

import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractPopup;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.container.IHtmlElement;

public class SourceView extends AbstractPopup{

	private String className;
	public SourceView(WebGuiFactory factory, ICanvas viewConnector, String className ) {
		super(factory, viewConnector.getPage(), className, 900, 800 );
		this.className = className;
	}

	@Override
	protected void createControls( WebGuiFactory factory, ICanvas viewConnector ) {
		IHtmlElement iFrame = factory.createHtmlElement( viewConnector );
		iFrame.setTagName( "iframe" );
		iFrame.getStyle().add("width", "900px");
		iFrame.getStyle().add("height", "800px");
		String pathToClass = "./"+ className.replace( '.', '/' ) + ".java";
		iFrame.setAttribute("src", pathToClass );
	}

}
