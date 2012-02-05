package org.webguitoolkit.patterns.views.layout;

import org.webguitoolkit.ui.base.DataBag;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.event.IServerEventListener;
import org.webguitoolkit.ui.controls.event.ServerEvent;
import org.webguitoolkit.ui.controls.form.AbstractButtonBarListener;
import org.webguitoolkit.ui.controls.form.Compound;
import org.webguitoolkit.ui.controls.form.ICheckBox;
import org.webguitoolkit.ui.controls.form.ICompound;
import org.webguitoolkit.ui.controls.form.ILabel;
import org.webguitoolkit.ui.controls.form.IText;
import org.webguitoolkit.ui.controls.layout.ITableLayout;

/**
 * A view to edit a single TODO item. A using application has to register for SAVE and CABCEL event in order to handle
 * the cleanup of the view. With the vie a save and cancel button is presented. It is intended that the control returns
 * to the caller if save or cancel was clicked by the user.
 * 
 * @author Peter
 * 
 */
public class TodoEditorView extends AbstractView {

	public static final int SAVE_EVENT = 42;
	public static final int CANCEL_EVENT = 43;

	private Todo todo;

	/**
	 * 
	 * @param factory
	 *            the WGT factory
	 * @param viewConnector
	 *            where to connect
	 * @param todo
	 *            a Todo item to be edited.
	 */
	public TodoEditorView(WebGuiFactory factory, ICanvas viewConnector, Todo todo) {
		super(factory, viewConnector);
		this.todo = todo;
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		getPage().addHeaderCSS("./styles/todolist.css");
		ICompound compound = factory.createCompound(viewConnector);
		
		DataBag bag = new DataBag(todo);
		compound.setBag(bag);
		bag.addProperty("spent", ""); // add this virtual property
		
		factory.createButtonBar(compound, "cancel,save", new CRUDListener(compound));

		ITableLayout layout = factory.createTableLayout(compound);

		ICheckBox checkBox = factory.createCheckBox(layout, "done");
		ILabel dlabel = factory.createLabel(layout, "Description");
		IText descr = factory.createText(layout, "description", dlabel);
		descr.setValue(todo.getDescription());
		descr.addCssClass("todoDescription"); // TODO-PZ
		ILabel elabel = factory.createLabel(layout, "Add time");
		IText spent = factory.createText(layout, "spent", elabel);
		spent.setValue("0");
		spent.addCssClass("todoTime"); // TODO-PZ
		ILabel tlabel = factory.createLabel(layout, "Total time");
		IText total = factory.createText(layout, "effort", tlabel);
		total.setValue(Integer.toString(todo.getEffort()));
		total.addCssClass("todoTime"); // TODO-PZ
		((Compound) compound).changeElementMode(ICompound.MODE_EDIT);
		compound.load();
	}

	/**
	 * Listener for the form.
	 */
	public class CRUDListener extends AbstractButtonBarListener {

		public CRUDListener(ICompound compound) {
			super((Compound) compound);
		}

		@Override
		public Object newDelegate() {
			// Not needed
			return new Todo("<enter>");
		}

		@Override
		public boolean refresh(Object delegate) {
			return true;
		}

		@Override
		public int persist() {
			return SAVE_OK;
		}

		@Override
		public void postSave() {
			Todo todo = (Todo) compound.getBag().getDelegate();
			String spent = (String) compound.getBag().get("spent");
			if (spent != null && spent.length() > 0)
				try {
					todo.setEffort(todo.getEffort() + Integer.parseInt(spent));
				} catch (NumberFormatException e) {
					// OK - forget the input
				}
			ServerEvent saveEvent = new ServerEvent(SAVE_EVENT);
			saveEvent.putParameter("todo", todo);
			fireServerEvent(saveEvent);
		}

		@Override
		public void postCancel() {
			super.postCancel();
			ServerEvent cancelEvent = new ServerEvent(SAVE_EVENT);
			Todo todo = (Todo) compound.getBag().getDelegate();
			cancelEvent.putParameter("todo", todo);
			fireServerEvent(cancelEvent);
		}

		@Override
		public boolean delete(Object delegate) {
			return true;
		}
	}

	
	@Override
	public void registerListener(int eventtype, IServerEventListener liz) {
		int[] events = supportedEvents();
		for (int i = 0; i < events.length; i++) {
			if (eventtype == events[i]) {
				super.registerListener(eventtype, liz);
				return;
			}
		}
		throw new IllegalArgumentException("Event " + eventtype + " is not supported in "
				+ this.getClass().getCanonicalName());
	}

	/**
	 * @return an array with the supported events.
	 */
	public int[] supportedEvents() {
		return new int[] { SAVE_EVENT, CANCEL_EVENT };
	}
}
