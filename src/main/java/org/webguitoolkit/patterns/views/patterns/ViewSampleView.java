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
package org.webguitoolkit.patterns.views.patterns;

import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;

public class ViewSampleView extends AbstractView {

	public ViewSampleView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls( WebGuiFactory factory, ICanvas viewConnector ) {
		
//		// create a layout 
//		TableLayout layout = factory.newTableLayout( viewConnector );
//		
//		TableLayout buttons = factory.newTableLayout( layout );
//		layout.getCurrentCell().setVAlign("top");
//		
//		// create a view connector
//		Canvas viewArea = factory.newCanvas( layout );
//		
//		ImageView imageView = new ImageView(getFactory(), viewArea);
//		imageView.setSource( "./images/wgt/edit.gif" );
//		getFactory().newButton(buttons , null, "edit Image", "preview", imageView );
//		buttons.newLine();
//
//		imageView = new ImageView(getFactory(), viewArea);
//		imageView.setSource( "http://www.google.de/intl/de_de/images/logo.gif" );
//		getFactory().newButton(buttons , null, "Google Banner", "preview", imageView );
//		buttons.newLine();
//
//		IFrameView iframeView = new IFrameView(getFactory(), viewArea);
//		iframeView.setSource( "http://www.google.de" );
//		getFactory().newButton(buttons , null, "Google", "google", iframeView );
//		buttons.newLine();
//
//		iframeView = new IFrameView(getFactory(), viewArea);
//		iframeView.setSource( "http://www.endress.com" );
//		getFactory().newButton(buttons , null, "E+H Page", "Endress+Hauser", iframeView );
//		buttons.newLine();
	}

}
