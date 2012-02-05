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
package org.webguitoolkit.patterns.sandbox;

import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;

public class PermissionView extends AbstractView {

	public PermissionView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls( WebGuiFactory factory, ICanvas viewConnector ) {
//		final PermissionControl pc = new PermissionControl();
//
//		pc.addPermission( "read", new String[]{} );
//		pc.addPermission( "update", new String[]{ "read" } );
//		pc.addPermission( "insert", new String[]{ "read", "update" } );
//		pc.addPermission( "delete", new String[]{ "read", "update", "insert" } );
//		pc.addPermission( "grant", new String[]{ "read", "update", "insert", "delete" } );
//		pc.addPermission( "execute", new String[]{ "read" } );
//
//		viewConnector.add( pc );
//		
//		Button button = getFactory().newButton( viewConnector, null, "clear", "", new IActionListener(){
//			public void onAction(ClientEvent event) {
//				pc.setValue("");
//			}
//		});
//		button = getFactory().newButton( viewConnector, null, "value", "", new IActionListener(){
//			public void onAction(ClientEvent event) {
//				viewConnector.getPage().sendInfo( pc.getValue() );
//			}
//		});
		
	}

}
