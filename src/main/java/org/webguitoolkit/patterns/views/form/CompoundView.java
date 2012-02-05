package org.webguitoolkit.patterns.views.form;

import java.io.Serializable;

import org.webguitoolkit.ui.base.DataBag;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.form.AbstractButtonBarListener;
import org.webguitoolkit.ui.controls.form.Compound;
import org.webguitoolkit.ui.controls.form.IButtonBar;
import org.webguitoolkit.ui.controls.form.IButtonBarListener;
import org.webguitoolkit.ui.controls.form.ICompound;
import org.webguitoolkit.ui.controls.form.ILabel;
import org.webguitoolkit.ui.controls.form.IText;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.controls.util.validation.ValidatorUtil;

public class CompoundView extends AbstractView {

	public CompoundView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {

		// create a compound
		ICompound compound = factory.createCompound(viewConnector);

		// create a ButtonBar to control the compounds mode
		IButtonBar buttonBar = factory.createButtonBar(compound, "new,edit,delete,save,cancel", new ButtonBarListener(compound));
		buttonBar.setDeleteKey("person.delete.msg@Are You sure you want to delete the person?");

		// create a TableLayout for layouting the forms fields
		ITableLayout formLayout = factory.createTableLayout(compound);

		// create a label
		ILabel labelForName = factory.createLabel(formLayout, "name@Name");

		// create a text field and assign the label
		IText nameField = factory.createText(formLayout, "name", labelForName);

		// add a validator to text field, the validation is done on the compounds save() method
		nameField.addValidator(ValidatorUtil.MANDATORY_VALIDATOR);

		Person person = new Person("Jim");
		// add person wrapped with a DataBag to the compound
		compound.setBag(new DataBag(person));
		// send data to the browser
		compound.load();
	}

	public class ButtonBarListener implements IButtonBarListener {

		private final ICompound compound;

		public ButtonBarListener(ICompound compound) {
			this.compound = compound;
		}

		public void onCancel(ClientEvent event) {
			// undo changes
			compound.getBag().undo();
			// load data to browser
			compound.load();
			// make compound read only
			compound.changeElementMode(Compound.MODE_READONLY);
		}

		public void onDelete(ClientEvent event) {
			// clear bag
			compound.setBag(new DataBag(null));
			// send data to browser
			compound.load();
		}

		public void onEdit(ClientEvent event) {
			// make compound editable
			compound.changeElementMode(Compound.MODE_EDIT);
		}

		public void onNew(ClientEvent event) {
			// clear bag
			compound.setBag(new DataBag(null));
			// send data to browser
			compound.load();
			// make compound editable
			compound.changeElementMode(Compound.MODE_NEW);
		}

		public void onSave(ClientEvent event) {
			// if new set a new delegate to the bag
			if (compound.getMode() == Compound.MODE_NEW)
				compound.getBag().setDelegate(new Person());
			// save data to model in this case to the Databag
			compound.save();
			// check if there where errors during save operation and return if there are errors
			if (compound.hasErrors())
				return;
			// save the Databag to the delegate
			compound.getBag().save();
			// make compound read only
			compound.changeElementMode(Compound.MODE_READONLY);

			// check if the name is set in the delegate
			Person thePerson = (Person)compound.getBag().getDelegate();
			getPage().sendInfo("Name is: " + thePerson.getName());
		}
	}

	public class ButtonBarListener2 extends AbstractButtonBarListener {

		public ButtonBarListener2(ICompound compound) {
			super(compound);
		}

		/**
		 * delete object from database here
		 */
		public boolean delete(Object delegate) {
			return true;
		}

		/**
		 * create a new instance of the Databag's delegate
		 */
		public Object newDelegate() {
			return new Person();
		}

		/**
		 * save object to database
		 */
		public int persist() {
			return AbstractButtonBarListener.SAVE_OK;
		}

		/**
		 * refresh object from database
		 */
		public boolean refresh(Object delegate) {
			return true;
		}

	}

	public class Person implements Serializable {
		private String name;

		public Person() {
			this.setName("");
		}

		public Person(String name) {
			this.setName(name);
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}
}
