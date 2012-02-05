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
package org.webguitoolkit.patterns.views.layout;

import java.util.Random;

import org.webguitoolkit.patterns.prototype.MockData;
import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.event.IActionListener;
import org.webguitoolkit.ui.controls.form.IButton;
import org.webguitoolkit.ui.controls.tab.ITab;
import org.webguitoolkit.ui.controls.tab.ITabStrip;
import org.webguitoolkit.ui.controls.tab.StandardTabStrip;
import org.webguitoolkit.ui.controls.util.style.Style;
import org.webguitoolkit.ui.controls.util.style.selector.InlineSelector;

public class AccordionView extends AbstractView {

	private ITab top;
	private ITab middle;
	private ITab bottom;
	private IButton buttonToggle, buttonRandom;
	private ITabStrip accordion;
	private boolean accortionTogglefolds = true;

	public AccordionView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		// button to show the source code of this class
		factory.createButton(viewConnector, "./images/iconText.gif", "Source Code", "Source Code", new SourceView(factory, viewConnector, this
				.getClass().getName()));

		accordion = factory.createTabStrip(viewConnector);
		accordion.setDisplayMode(StandardTabStrip.DISPLAY_MODE_ACCORDION);
		accordion.setToggleAccordion(true);

		top = factory.createTab(accordion, "Top fold");
		middle = factory.createTab(accordion, "Middle fold");
		bottom = factory.createTab(accordion, "Bottom fold");
		ITab sub = factory.createTab(accordion, "Very bottom fold");
		ITab extra = factory.createTab(accordion, "Extra fold");

		factory.createLabel(top, "Some text");
		factory.createLabel(top, "Some more text");
		factory.createButton(middle, null, "i'm a button", "", new ThisListener());
		factory.createLabel(bottom, "Some text");
		factory.createLabel(bottom, "Some more text");
		factory.createLabel(sub, MockData.lipsum);
		factory.createLabel(extra, "nothing special here");

		// set default open fold
		accordion.selectTab(1);

		// set optional custom styles
		accordion.getStyle().add("width", "300px");
		middle.getStyle().add("color", "red");
		middle.getStyle().add("background-color", "#dddddd");
		top.getStyle().add("text-align", "right");
		sub.getStyle().add("font-size", "x-small");

		// create new Style for a header..
		Style h3style = new Style(new InlineSelector());
		h3style.add("font-style", "italic");
		h3style.add("color", "black");
		h3style.add("text-align", "center");
		h3style.add("background-color", "#22dd55");

		bottom.setAccordionHeaderSyle(h3style);
		middle.setAccordionHeaderSyle(h3style);

		buttonRandom = factory.createButton(viewConnector, null, "open a random fold", "", new ThisListener());
		buttonToggle = factory.createButton(viewConnector, null, "change behavior to toggle=false", "", new ToggleListener());
	}

	class ThisListener implements IActionListener {

		public void onAction(ClientEvent event) {
			if (event.getSource() == buttonRandom) {
				int r = new Random().nextInt(5);
				accordion.selectTab(r);
			}
			else {
				getPage().sendInfo("did you say something?");
			}
		}
	}

	class ToggleListener implements IActionListener {
		public void onAction(ClientEvent event) {
			accordion.setToggleAccordion(!accortionTogglefolds);
			accortionTogglefolds = !accortionTogglefolds;
			buttonToggle.setLabel("change behavior (toggle="+accortionTogglefolds+")");
		}
	}

	// class TabstripActionListener extends AbstractTabActionListener {
	//
	// public TabstripActionListener() {
	// }
	//
	// @Override
	// public boolean onTabChange(Tab old, Tab selected) {
	// if (selected == summaryTab) {
	// // load summary data
	// }
	// if (selected == detailTab) {
	// // load detail data
	// }
	//
	// return true; // loading OK;
	// // TODO : return false if something went wrong while loading in
	// // order to stay on the old tab
	// }
	// }

}
