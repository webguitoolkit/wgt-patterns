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
import org.webguitoolkit.ui.controls.form.IButton;
import org.webguitoolkit.ui.controls.form.button.StandardButton;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.controls.layout.TableLayout;

public class ButtonView extends AbstractView {

	private IButton standard1;
	private IButton standard2;
	private IButton standard3;
	private IButton standard4;
	private IButton standard5;
	private IButton standard6;
	private IButton link1;
	private IButton link2;
	private IButton link3;

	boolean imageChanged = false;
	boolean textChanged = false;
	boolean disabled = true;

	public ButtonView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {

		// button to show the source code of this class
		factory.createButton(viewConnector, "./images/iconText.gif", "Source", "Source Code", new SourceView(factory,
				viewConnector, this.getClass().getName()), null);

		ITableLayout layout = factory.createTableLayout(viewConnector);

		String labelStyle = "background-color: #cccccc;";
		String buttonStyle = "background-color: #eeeeee;";

		factory.createLabel(layout, "");
		layout.getCurrentCell().setStyle(labelStyle);

		factory.createLabel(layout, "Only Text");
		layout.getCurrentCell().setStyle(labelStyle);

		factory.createLabel(layout, "Only Image");
		((TableLayout) layout).getCurrentCell().setStyle(labelStyle);

		factory.createLabel(layout, "Text With Image");
		layout.getCurrentCell().setStyle(labelStyle);
		layout.getCurrentCell().setColSpan(2);

		factory.createLabel(layout, "us as simple link");
		layout.getCurrentCell().setStyle(labelStyle);

		layout.newRow();

		factory.createLabel(layout, "StandardButton");
		layout.getCurrentCell().setStyle(labelStyle);

		standard1 = factory.createButton(layout, null, "Click Me!", null, new ButtonListener());
		layout.getCurrentCell().setStyle(buttonStyle);

		standard2 = factory.createButton(layout, "./images/wgt/icons/delete.gif", null, null,
				new ButtonListener());
		layout.getCurrentCell().setStyle(buttonStyle);

		standard3 = factory.createButton(layout, "./images/wgt/icons/delete.gif", "Click Me!", null,
				new ButtonListener(), null);
		layout.getCurrentCell().setStyle(buttonStyle);
		layout.getCurrentCell().setColSpan(2);

		// factory.createLink(layout, null, "Load Google!", null, "http://www.google.de", "_blank");
		// layout.getCurrentCell().setStyle(buttonStyle);

		layout.newRow();

		factory.createLabel(layout, "LinkButton");
		layout.getCurrentCell().setStyle(labelStyle);

		link1 = factory.createLinkButton(layout, null, "Click Me!", null, new ButtonListener());
		layout.getCurrentCell().setStyle(buttonStyle);
		link2 = factory
				.createLinkButton(layout, "./images/wgt/icons/delete.gif", null, null, new ButtonListener());
		layout.getCurrentCell().setStyle(buttonStyle);
		link3 = factory.createLinkButton(layout, "./images/wgt/icons/delete.gif", "Click Me!", null,
				new ButtonListener());
		layout.getCurrentCell().setStyle(buttonStyle);
		layout.getCurrentCell().setColSpan(2);

		factory.createLink(layout, null, "Load Google!", null, "http://www.google.de", "_blank");
		layout.getCurrentCell().setStyle(buttonStyle);

		layout.newRow();
		factory.createLabel(layout, "Button Group");
		layout.getCurrentCell().setStyle(labelStyle);

		standard4 = new StandardButton();
		layout.add(standard4);
		standard4.setLabel("New");
		standard4.setAlignment(IButton.POSITION_LEFT);
		standard4.setActionListener(new ButtonListener());
		standard5 = new StandardButton();
		layout.appendToCurrentCell(standard5);
		standard5.setLabel("Edit");
		standard5.setAlignment(IButton.POSITION_MIDDLE);
		standard5.setActionListener(new ButtonListener());
		standard6 = new StandardButton();
		layout.appendToCurrentCell(standard6);
		standard6.setLabel("Delete");
		standard6.setAlignment(IButton.POSITION_RIGHT);
		standard6.setActionListener(new ButtonListener());
		layout.getCurrentCell().setStyle(buttonStyle);
		layout.getCurrentCell().setColSpan(4);

		layout.newRow();
		factory.createButton(layout, null, "Change Text", null, new CangeTextListener());
		factory.createButton(layout, null, "Change Image", null, new CangeImageListener());
		factory.createButton(layout, null, "Disable", null, new DisableListener());
		layout.getCurrentCell().setColSpan(4);
	}

	public class ButtonListener implements IActionListener {
		public void onAction(ClientEvent event) {
			getPage().sendInfo("I'v been clicked");
		}
	}

	public class CangeImageListener implements IActionListener {
		public void onAction(ClientEvent event) {
			String image = imageChanged ? "./images/wgt/icons/delete.gif"
					: "./images/wgt/icons/new.gif";

			standard2.setSrc(image);
			standard3.setSrc(image);

			link2.setSrc(image);
			link3.setSrc(image);

			imageChanged = !imageChanged;

		}
	}

	public class CangeTextListener implements IActionListener {
		public void onAction(ClientEvent event) {
			String newText = textChanged ? "Press me!" : "Click Me!";

			standard1.setLabel(newText);
			standard1.setTooltip(newText);
			standard3.setLabel(newText);
			standard3.setTooltip(newText);

			link1.setLabel(newText);
			link3.setLabel(newText);

			textChanged = !textChanged;
		}
	}

	public class DisableListener implements IActionListener {
		public void onAction(ClientEvent event) {

			standard1.setDisabled(disabled);
			standard2.setDisabled(disabled);
			standard3.setDisabled(disabled);
			link1.setDisabled(disabled);
			link2.setDisabled(disabled);
			link3.setDisabled(disabled);
			standard4.setDisabled(disabled);
			standard5.setDisabled(disabled);
			standard6.setDisabled(disabled);

			disabled = !disabled;
		}
	}

}
