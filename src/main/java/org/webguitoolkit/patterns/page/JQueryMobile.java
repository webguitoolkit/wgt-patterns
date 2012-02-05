package org.webguitoolkit.patterns.page;

import org.webguitoolkit.ui.ajax.IContext;
import org.webguitoolkit.ui.controls.Page;
import org.webguitoolkit.ui.controls.container.Canvas;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.container.IHtmlElement;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.event.IActionListener;
import org.webguitoolkit.ui.controls.form.IButton;
import org.webguitoolkit.ui.controls.form.ILabel;
import org.webguitoolkit.ui.controls.list.SimpleList;
import org.webguitoolkit.ui.controls.util.json.JSONObject;

public class JQueryMobile extends Page {

	@Override
	protected void pageInit() {
		// set the view port width

		// addHeaderCSS("./styles/ipad.css");
		addWgtCSS("jquery/jquery.mobile-1.0a3.css");

		addWgtJS("wgt.controller.jquerymobile.js");

		ICanvas can = getFactory().createCanvas(this);
		can.addCssClass("current");
		((Canvas)can).setAttribute("data-role", "page");
		((Canvas)can).setAttribute("data-theme", "b");

		ICanvas toolbar = getFactory().createCanvas(can);
		// toolbar.addCssClass("toolbar");
		((Canvas)toolbar).setAttribute("data-role", "header");

		final ICanvas details = getFactory().createCanvas(can);
		details.addCssClass("details");
		((Canvas)details).setAttribute("data-role", "content");

		IHtmlElement header = getFactory().createHtmlElement(toolbar, "H1");
		header.innerHtml("Header Test");
		IButton button = getFactory().createLink(toolbar, null, "test.button.about@About", "test.button.tt@Test Button",
				"#" + details.getId(), null);
		// button.addCssClass("button");
		// button.addCssClass("slideup");

		SimpleList list = (SimpleList)getFactory().createList(details);
		// list.setListCSS("rounded");
		// list.setListItemCSS("arrow");

		list.setAttribute("data-role", "listview");
		list.setAttribute("data-inset", "true");
		list.setAttribute("data-theme", "c");
		list.setAttribute("data-dividertheme", "b");
		
		ILabel headerLabel = getFactory().createLabel(list, "Header" );
		
		list.setAttribute(headerLabel, "data-role", "list-divider");

		button = getFactory().createLinkButton(list, null, "test.button1@Test Button 1", "test.button.tt@Test Button", new IActionListener() {
			public void onAction(ClientEvent event) {
				sendInfo("Test Clicked");
			}
		});
		button = getFactory().createLinkButton(list, null, "test.button2@Test Button 2", "test.button.tt@Test Button", new IActionListener() {
			public void onAction(ClientEvent event) {
				sendInfo("Test Clicked");
			}
		});
	}

	private void initJQTouch(String icon, boolean glossIcon, String startUp, String statusBar, String[] preloadImages) {
		JSONObject obj = new JSONObject();
		obj.addAttribute("icon", icon);
		obj.addAttribute("addGlossToIcon", glossIcon);
		obj.addAttribute("startupScreen", startUp);
		obj.addAttribute("statusBar", statusBar);
		obj.addAttribute("preloadImages", preloadImages);

		getContext().add(getId() + "_jqtouch", obj.toString(), "jqti", IContext.STATUS_EDITABLE);
	}

	@Override
	protected String title() {
		return "WGT Mobile";
	}

	@Override
	protected void defBase() {
		// TODO Auto-generated method stub
		super.defBase();
	}

}
