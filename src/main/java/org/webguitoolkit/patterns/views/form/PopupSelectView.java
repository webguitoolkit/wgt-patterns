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

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.apache.ecs.html.TD;
import org.webguitoolkit.patterns.prototype.Prototyper;
import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.IDataBag;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.event.IServerEventListener;
import org.webguitoolkit.ui.controls.event.ServerEvent;
import org.webguitoolkit.ui.controls.form.ILabel;
import org.webguitoolkit.ui.controls.form.popupselect.IPopupSelect;
import org.webguitoolkit.ui.controls.form.popupselect.Popup;
import org.webguitoolkit.ui.controls.form.popupselect.PopupSelect;
import org.webguitoolkit.ui.controls.form.popupselect.PopupSelectAction;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.controls.table.ITable;
import org.webguitoolkit.ui.controls.util.conversion.ConvertUtil.ConversionException;
import org.webguitoolkit.ui.controls.util.conversion.IConverter;

public class PopupSelectView extends AbstractView {

	public PopupSelectView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	private class Customer implements Serializable {
		private String name, nbr;

		public Customer(String name, String number) {
			this.name = name;
			this.nbr = number;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getNbr() {
			return nbr;
		}

		public void setNbr(String nbr) {
			this.nbr = nbr;
		}
	}

	private class Listener4PopupSelectAction implements IServerEventListener {

		public void handle(ServerEvent event) {
			Object selection = event.getParameter(Popup.SELECTION_EVENT_PARAMETER);
			if (selection instanceof IDataBag) {
				IDataBag cust = (IDataBag)selection;
				getPage().sendInfo("You selected " + cust.getString("Name"));
			}
			else {
				getPage().sendError("No Selection or some kind of failure");
			}
		}
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		// button to show the source code of this class
		factory.createButton(viewConnector, "./images/iconText.gif", "Source Code", "Source Code", new SourceView(factory, viewConnector,
				this.getClass().getName()));

		ITableLayout layout = factory.createTableLayout(viewConnector);

		ILabel lablel = factory.createLabel(layout, "Label for select");
		IPopupSelect select = getFactory().createPopupSelect(layout, "customer", "Name", false, new String[] { "Serial", "Name" },
				new String[] { "Customer Number", "Customer Name" }, lablel);
		((PopupSelect)select).setTableEditable(false);
		((PopupSelect)select).setTableDisplayMode(ITable.DISPLAY_MODE_SCROLL_BUTTONS);
		((PopupSelect)select).setDisplayConverter(new CombinedPropertyConverter());
		// create som Customers
		Prototyper p = new Prototyper(factory);
		List<IDataBag> customers = p.genMockdata(new String[] { "Name:name:y", "Firstname:fname:y", "Age:num:y", "City:city:y",
				"Serial:ser:y", "Date:dat:y", "Street:street:y", "Device:device:y" }, 20);
		select.setAvailableObjects(customers);

		layout.newRow();
		layout.addCell(new TD("&#160;"));
		layout.getCurrentCell().setColSpan(2);
		layout.newRow();

		// PopupSelectAction
		PopupSelectAction actionlistener = new PopupSelectAction(getPage(), false, new String[] { "Serial", "Name" }, new String[] {
				"Customer Number", "Customer Name" }, "Select", new Listener4PopupSelectAction());
		actionlistener.setAvailableObjects(customers);

		lablel = factory.createLabel(layout, "Popup Select Action:");
		getFactory().createButton(layout, null, "Show Options", "Show Options", actionlistener);

	}
	
	public class CombinedPropertyConverter implements IConverter{

		public Object convert(Class type, Object value) {
			if( value instanceof IDataBag){
				IDataBag bag = (IDataBag)value;
				return bag.get("Name")+" ("+bag.get("Serial")+")";
			}
			return null;
		}

		public Object parse(String textRep) throws ConversionException {
			throw new NotImplementedException();
		}
		
	}

}
