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
import java.util.ArrayList;
import java.util.List;

import org.webguitoolkit.patterns.prototype.Prototyper;
import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.DataBag;
import org.webguitoolkit.ui.base.IDataBag;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.form.AbstractButtonBarListener;
import org.webguitoolkit.ui.controls.form.IButtonBar;
import org.webguitoolkit.ui.controls.form.ICompound;
import org.webguitoolkit.ui.controls.form.ILabel;
import org.webguitoolkit.ui.controls.form.Text;
import org.webguitoolkit.ui.controls.form.popupselect.IPopupSelect;
import org.webguitoolkit.ui.controls.layout.ITableLayout;

public class MultiPopupSelectView extends AbstractView {
	private Text text1, text2;

	public MultiPopupSelectView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	private class ButtonBarListener extends AbstractButtonBarListener {

		public ButtonBarListener(ICompound compound) {
			super(compound);
			// TODO Auto-generated constructor stub
		}

		@Override
		public boolean delete(Object delegate) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Object newDelegate() {
			// TODO Auto-generated method stub
			return new Order();
		}

		@Override
		public int persist() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean refresh(Object delegate) {
			// TODO Auto-generated method stub
			return false;
		}

	}

	public class Customer implements Serializable {
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

	public class Order implements Serializable {
		private List<Customer> customers = null;

		public void setCustomers(List<Customer> customers) {
			this.customers = customers;
		}

		public List<Customer> getCustomers() {
			return customers;
		}

	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		// button to show the source code of this class
		factory.createButton(viewConnector, "./images/iconText.gif", "Source Code", "Source Code", new SourceView(factory, viewConnector,
				this.getClass().getName()));

		ICompound comp = getFactory().createCompound(viewConnector);

		IButtonBar bb = getFactory().createButtonBar(comp, "new,cancel,edit,save", new ButtonBarListener(comp));

		ITableLayout layout = factory.createTableLayout(comp);

		ILabel lablel = factory.createLabel(layout, "Label for select");
		IPopupSelect select = getFactory().createPopupSelect(layout, "customers", "name", true, new String[] { "nbr", "name" },
				new String[] { "Cusomer Number", "Cusomer Name" }, lablel);
		// create some Customers
		Prototyper p = new Prototyper(factory);
		List<IDataBag> customers = p.genMockdata(new String[] { "Name:name:y", "Firstname:fname:y", "Age:num:y", "City:city:y",
				"Serial:ser:y", "Date:dat:y", "Street:street:y", "Device:device:y" }, 20);
		List<Customer> customerObjects = new ArrayList<Customer>();
		for (IDataBag bag : customers) {
			Customer c = new Customer(bag.getString("Name"), bag.getString("Serial"));
			customerObjects.add(c);
		}

		select.setAvailableObjects(customerObjects);

		comp.setBag(new DataBag(new Order()));
		comp.load();
	}

}
