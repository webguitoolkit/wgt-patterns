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

import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.form.IButton;
import org.webguitoolkit.ui.controls.form.ICheckBox;
import org.webguitoolkit.ui.controls.form.IRadio;
import org.webguitoolkit.ui.controls.form.ISelect;
import org.webguitoolkit.ui.controls.form.ITextarea;
import org.webguitoolkit.ui.controls.layout.ITableLayout;

public class TabIndexView extends AbstractView{
	public TabIndexView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls( WebGuiFactory factory, ICanvas viewConnector ) {
		ITableLayout layoutTable = factory.createTableLayout(viewConnector);
		
		factory.createLabel(layoutTable,"5");
		IButton bu = factory.createButton(layoutTable,null,"I am a button", "I am a button", this);
		bu.setTabindex(5);
		
		layoutTable.newRow();
		factory.createLabel(layoutTable,"3");
		IRadio ra1 = factory.createRadio(layoutTable, "test", "Radio 1");
		ra1.setTabindex(3);
		layoutTable.newRow();
		factory.createLabel(layoutTable,"1");
		ICheckBox cb1 = factory.createCheckBox(layoutTable, "box");
		cb1.setTabindex(1);
		layoutTable.newRow();
		factory.createLabel(layoutTable,"4");
		IButton lb = factory.createLinkButton(layoutTable, null, "I am a button", "I am a button", this);
		lb.setTabindex(4);
		layoutTable.newRow();
		factory.createLabel(layoutTable,"2");
		ISelect s = factory.createSelect(layoutTable, "select");
		s.setTabindex(2);		
		layoutTable.newRow();
		factory.createLabel(layoutTable,"5+1");
		ITextarea ta = factory.createTextarea(layoutTable, "textarea");
		ta.setTabindex(6);
		
		layoutTable.newRow();
		
	}
}
