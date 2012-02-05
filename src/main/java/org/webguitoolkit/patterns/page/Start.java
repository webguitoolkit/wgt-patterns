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

import java.lang.reflect.Constructor;

import org.jfree.data.gantt.GanttCategoryDataset;
import org.webguitoolkit.patterns.views.MissingView;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.Page;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.controls.util.TextService;
/**
 * Intended to start an view directly from the browser.
 * Requires a constructor like xxxView(WebGuiFactory factory, Canvas viewConnector)
 * http://localhost:8080/WebGuiPatterns/page/Start?view=org.webguitoolkit.patterns.views.presentation.TableView
 * @author peter
 *
 */
public class Start extends Page {

	private ICanvas viewConnector;
	private WebGuiFactory factory;

	@Override
	protected void pageDefaults() {
		super.pageDefaults();
		addWgtCSS("eh_theme.css");
		addWgtCSS("clickmenu_portal.css");
		addWgtCSS("standard/calendar.css");
		addWgtCSS("sandbox/button20.css");
		addFavicon("./images/wgt/iconw.gif");
		addWgtJS("select.js");
		addWgtJS("dragdrop.js");
//		addWgtJS("tree.js");
		addWgtJS( "wgt.controller.jquerytree" );
		addWgtJS( "jquery/jquery.dynatree.js");

		addWgtJS("contextmenu.js");
		addWgtJS("datepicker_" + TextService.getLocale().getLanguage() + ".js");
	}

	@Override
	protected void pageInit() {
		factory = new WebGuiFactory();

		ITableLayout layout = factory.createTableLayout(this);
		layout.getStyle().add("width","100%");
		layout.getEcsTable().setBorder(0);
		layout.getEcsTable().setCellPadding(0);
		layout.getEcsTable().setCellSpacing(0);
		
		viewConnector = factory.createCanvas(layout);
		String viewname = Page.getServletRequest().getParameter("view");
		AbstractView view = null;
		try {
			Class viewclass =  Class.forName(viewname);
			Constructor constructor = viewclass.getConstructor(WebGuiFactory.class,ICanvas.class);
			view = (AbstractView) constructor.newInstance(factory, viewConnector);
		} catch (Exception e) {
			view = new MissingView(factory, viewConnector);
		} 
		view.show();
	}

	@Override
	protected String title() {
		return "View Starter";
	}

}
