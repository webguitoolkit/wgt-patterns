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
package org.webguitoolkit.patterns.page;

import org.webguitoolkit.patterns.sandbox.PermissionView;
import org.webguitoolkit.ui.controls.Page;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.controls.menu.IMenu;
import org.webguitoolkit.ui.controls.menu.IMenuBar;
import org.webguitoolkit.ui.controls.util.TextService;

public class SandBox extends Page {

	@Override
	protected void pageDefaults() {
		super.pageDefaults();
		addWgtCSS("eh_theme.css");
		addWgtCSS("clickmenu_portal.css");
		addWgtCSS("standard/calendar.css");
		addWgtCSS("sandbox/button20.css");
		addWgtJS("user_include.js");
		addWgtJS("select.js");
		addWgtJS("dragdrop.js");
		addWgtJS("tree.js");
		addWgtJS("contextmenu.js");
		addWgtJS("datepicker_"+TextService.getLocale().getLanguage()+".js");		
	}

	@Override
	protected void pageInit() {
		getStyle().add("padding", "0px");
		getStyle().add("margin", "0px");
		
		ITableLayout layout = getFactory().createTableLayout(this);
		layout.getStyle().add("width","100%");
		layout.getEcsTable().setBorder(0);
		layout.getEcsTable().setCellPadding( 0 );
		layout.getEcsTable().setCellSpacing( 0 );

		IMenuBar menuBar = getFactory().createMenuBar( layout );
		layout.getCurrentCell().setStyle("padding-top: 0px; margin-top: 0px;");
		layout.newRow();

		ICanvas viewConnector = getFactory().createCanvas(layout);
		viewConnector.getStyle().add("margin", "10px");

		// Menu simple controls
		IMenu menuSimpleControls = getFactory().createMenu( menuBar, "User Management" );
		getFactory().createMenuItem(menuSimpleControls, "Permission Control", null, new PermissionView(getFactory(), viewConnector) );
	}

	@Override
	protected String title() {
		return "Sand Box";
	}

}
