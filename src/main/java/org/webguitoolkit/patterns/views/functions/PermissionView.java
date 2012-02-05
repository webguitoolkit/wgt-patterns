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
package org.webguitoolkit.patterns.views.functions;

import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.event.IActionListener;
import org.webguitoolkit.ui.controls.form.IButton;
import org.webguitoolkit.ui.controls.form.IText;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.permission.IPermissionBroker;
import org.webguitoolkit.ui.permission.PermissionManager;
import org.webguitoolkit.ui.permission.SimplePermissionMatrix;

public class PermissionView extends AbstractView {

	public PermissionView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls( WebGuiFactory factory, ICanvas viewConnector ) {
		// button to show the source code of this class
		factory.createButton( viewConnector, "./images/iconText.gif", "Source Code","Source Code", new SourceView( factory, viewConnector, this.getClass().getName() ) );

		ITableLayout layout = factory.createTableLayout(viewConnector);		
		
		// buttons to show some controls in different modes
		IButton noPermission = factory.createButton( layout, null, "no permission","no permission", null );
		IButton readPermission = factory.createButton( layout, null, "read permission","read permission", null );
		IButton executePermission = factory.createButton( layout, null, "execute permission","execute permission", null );

		layout.newRow();
		
		// the content area for the controls to show
		ICanvas canvas = factory.createCanvas( layout );
		layout.getCurrentCell().setColSpan( 3 );

		// add the ActionListener, this has to be done here cause we need the canvas for our listener
		noPermission.setActionListener( new TheView( factory, canvas, IPermissionBroker.NO_PERMISSION ) );
		readPermission.setActionListener( new TheView( factory, canvas, IPermissionBroker.READ_PERMISSION ) );
		executePermission.setActionListener( new TheView( factory, canvas, IPermissionBroker.EXECUTE_PERMISSION ) );
	}
	public class TheView extends AbstractView{

		protected int permission;
		
		public TheView(WebGuiFactory factory, ICanvas viewConnector, int permission) {
			super(factory, viewConnector);
			this.permission = permission;
		}

		@Override
		protected void createControls( WebGuiFactory factory, ICanvas viewConnector ) {
			ITableLayout layout = factory.createTableLayout(viewConnector);
			
			// add a text field to the view
			IText text = factory.createText( layout, "name", "textfield" );
			text.setValue( "some text" );
			
			// add a button to the view
			factory.createButton( layout, null, "button","button", new IActionListener(){
				public void onAction(ClientEvent event) {
					getPage().sendInfo( "Button pressed" );
				}}, "button" );
			
		}

		@Override
		public void onAction(ClientEvent event) {
			
			// set the permission in the permission manager
			SimplePermissionMatrix permissionMatrix = new SimplePermissionMatrix();
			permissionMatrix.addPermission( "textfield", permission);
			permissionMatrix.addPermission( "button", permission);
			PermissionManager.setPermissionBroker( permissionMatrix );
			
			// call super to show view
			super.onAction(event);
		}
		
	}

}
