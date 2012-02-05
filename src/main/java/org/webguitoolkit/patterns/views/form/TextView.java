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
package org.webguitoolkit.patterns.views.form;

import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.event.IActionListener;
import org.webguitoolkit.ui.controls.form.ILabel;
import org.webguitoolkit.ui.controls.form.IText;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.controls.util.JSUtil;
import org.webguitoolkit.ui.controls.util.validation.ValidationException;
import org.webguitoolkit.ui.controls.util.validation.ValidatorUtil;

public class TextView extends AbstractView {

	private ILabel info;

	public TextView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		// button to show the source code of this class
		factory.createButton(viewConnector, "./images/iconText.gif", "Source Code", "Source Code", new SourceView(
				factory, viewConnector, this.getClass().getName()));

		ITableLayout layout = factory.createTableLayout(viewConnector);

		// SIMPLE TEXT
		ILabel label = factory.createLabel(layout, "Simple Text");
		IText text1 = factory.createText(layout, WebGuiFactory.NO_LOCATOR, label );

		layout.newRow();

		// READ ONLY TEXT
		label = factory.createLabel(layout, "Readonly Text");
		IText text2 = factory.createText(layout, WebGuiFactory.NO_LOCATOR, label );
		text2.setEditable( false );

		layout.newRow();

		// TEXT WITH RETURN ACTION
		label = factory.createLabel(layout, "Text with return action");
		IText text3 = factory.createText(layout, "name", label);
		//default event (onReturn)
		text3.setActionListener( new ContentActionListener() );

		layout.newRow();

		// TEXT WITH KEYUP ACTION
		label = factory.createLabel(layout, "Text with keyup action");
		IText text4 = factory.createText(layout, "name", label);
		text4.setActionListener( new ContentActionListener() );
		//special events
		text4.setRegisteredActions( JSUtil.ACTION_ON_KEYUP );
		

		layout.newRow();

		// TEXT WITH VALIDATION
		label = factory.createLabel(layout, "Email Validation on return");
		IText text5 = factory.createText(layout, "name", label);
		text5.addValidator(ValidatorUtil.EMAIL_VALIDATOR );
		text5.setActionListener( new ValidationActionListener() );

		layout.newRow();

		// TEXT WITH VALIDATION
		label = factory.createLabel(layout, "Password");
		IText text6 = factory.createText(layout, "name", label);
		text6.setPassword(true);
		text6.setActionListener( new ContentActionListener() );

		layout.newRow();
		
		// info label for output of input
		info = factory.createLabel(layout, "");
		layout.getCurrentCell().setColSpan(2);


		// set the value of the text field
		text1.setValue("First Text");
		text2.setValue("Readonly");
	}
	//the action listener code
	class ContentActionListener implements IActionListener{
		public void onAction(ClientEvent e){
			info.setText( ((IText) e.getSource() ).getValue() );
		}
	}

	//the action listener code
	class ValidationActionListener implements IActionListener{
		public void onAction(ClientEvent e){
			String valinfo = "";
			try{
				((IText) e.getSource() ).validate();
			}
			catch( ValidationException ex ){
				valinfo = ex.getMessage(); 
			}
			info.setText( valinfo );
		}
	}

}
