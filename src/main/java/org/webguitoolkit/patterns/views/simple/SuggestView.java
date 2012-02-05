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
import org.webguitoolkit.ui.controls.event.ISuggestListener;
import org.webguitoolkit.ui.controls.form.ILabel;
import org.webguitoolkit.ui.controls.form.TextSuggest;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.controls.util.Tooltip;

public class SuggestView extends AbstractView implements ISuggestListener, IActionListener{
	private TextSuggest suggest;
	
	public SuggestView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}


	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		factory.createButton( viewConnector, "./images/iconText.gif", "Source Code","Source Code", new SourceView( factory, viewConnector, this.getClass().getName() ) );

		ITableLayout layoutTable		= factory.createTableLayout(viewConnector);  
		ILabel description = factory.createLabel(layoutTable, "Please type the 'Hello' for a quick demonstration into the inputfield below. The suggest starts with the 3ird character entered!");
		description.setTooltip(new Tooltip("Please type the name of a large german city into the inputfield below."));	
		layoutTable.newRow();
		
		suggest = new TextSuggest();
		layoutTable.add(suggest);
			suggest.setSuggestListener(this);
		
	}

	public String[] onRequestSuggenstion(ClientEvent event, String suggestString) {
		//the suggest box just makes sense if you fetch the suggest values from a database. so here stand should be your 
		//database query with the suggestString as where clause.
		String[] result =  {"Hello", "Hello Friend", "Hello friendly user", "Hello World", "hellwhatafunction"};
		return result;
	}

}
