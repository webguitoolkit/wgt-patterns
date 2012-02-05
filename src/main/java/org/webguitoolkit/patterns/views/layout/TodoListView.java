package org.webguitoolkit.patterns.views.layout;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.IBaseControl;
import org.webguitoolkit.ui.controls.IComposite;
import org.webguitoolkit.ui.controls.container.Canvas;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.container.IHtmlElement;
import org.webguitoolkit.ui.controls.contextmenu.BaseContextMenuListener;
import org.webguitoolkit.ui.controls.contextmenu.ContextMenuItem;
import org.webguitoolkit.ui.controls.contextmenu.IContextMenu;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.event.IActionListener;
import org.webguitoolkit.ui.controls.event.IServerEventListener;
import org.webguitoolkit.ui.controls.event.ServerEvent;
import org.webguitoolkit.ui.controls.form.IButton;
import org.webguitoolkit.ui.controls.form.ICheckBox;
import org.webguitoolkit.ui.controls.list.ISortableList;

/**
 * A view to present a sortable list of TODO items. Your can provide the list title an the icon that is shown as trigger
 * for the context menu.
 * 
 * @author Peter
 * 
 */
public class TodoListView extends AbstractView {

	private static final String EDIT = "Edit";
	private static final String DELETE = "Delete";
	public static final String DESCRIPTION = "description";
	public static final String ICON = "icon";

	private List<Todo> todos;
	private Properties properties;

	/**
	 * 
	 * @param factory
	 *            the GUI factory
	 * @param viewConnector
	 *            the connector where the view will be attached
	 * @param todos
	 *            a list of TODO items
	 * @param properties
	 *            control the list title and the icon.
	 */
	public TodoListView(WebGuiFactory factory, ICanvas viewConnector, List<Todo> todos, Properties properties) {
		super(factory, viewConnector);
		this.todos = todos;
		this.properties = properties;
	}

	@Override
	protected void createControls(final WebGuiFactory factory, ICanvas viewConnector) {

		factory.createLabel(viewConnector, properties.getProperty(DESCRIPTION));
		// IButton newButton = factory.createLinkButton(viewConnector, null, "create new", null, null );
		IButton enableButton = factory.createLinkButton(viewConnector, null, "enable", null, null);
		factory.createLabel(viewConnector, "&nbsp;");
		IButton disableButton = factory.createLinkButton(viewConnector, null, "disable", null, null);

		final ISortableList list = factory.createSortableList(viewConnector);

		/*
		 * if you need to know when the list was reordered add a listener
		 * 
		 * IListController listener = new IListListener() {}; list.setListener(listener);
		 */
		//
		// newButton.setActionListener(new IActionListener() {
		// public void onAction(ClientEvent event) {
		// ICanvas itemContainer = factory.createCanvas(list);
		// createListItem(factory, itemContainer, new Todo("**enter description**"));
		// list.addListItem( itemContainer );
		// }
		// });
		enableButton.setActionListener(new IActionListener() {
			public void onAction(ClientEvent event) {
				list.enable();
			}
		});
		disableButton.setActionListener(new IActionListener() {
			public void onAction(ClientEvent event) {
				list.disable();
			}
		});
		ICanvas header = factory.createCanvas(list, "donotsortme");
		factory.createLabel(header, "<b>I'm a delimiter and not sortable</b>");

		for (Iterator<Todo> it = todos.iterator(); it.hasNext();) {
			final Todo todo = it.next();
			ICanvas itemContainer = factory.createCanvas(list);
			createListItem(factory, itemContainer, todo);
		}
		header = factory.createCanvas(list, "donotsortme");
		factory.createLabel(header, "<b>I'm a delimiter and not sortable</b>");
		list.setUnsortable("donotsortme");

		list.setHandle("movehere");
		//		
		// ISortableList simpleList = factory.createList(viewConnector);
		// for (int i = 0; i < 5; i++) {
		// simpleList.addListItem(factory.createLabel(simpleList, "Item " + i));
		// }
	}

	/**
	 * @param factory
	 * @param f
	 * @param list
	 * @param todo
	 */
	private void createListItem(WebGuiFactory factory, ICanvas itemContainer, final Todo todo) {
		// context menu
		createImage(itemContainer, "./images/move.gif", "movehere");
		IHtmlElement html = createImage(itemContainer, properties.getProperty(ICON), null);
		html.setTooltip("right-click me ");

		IContextMenu cMenu = factory.createContextMenu(html);
		EditListener listener = new EditListener(itemContainer, todo, factory);
		factory.createContextMenuItem(cMenu, EDIT, listener);
		factory.createContextMenuItem(cMenu, DELETE, listener);

		final ICheckBox checkBox = factory.createCheckBox(itemContainer, null);
		checkBox.setSelected(todo.isDone());

		checkBox.setActionListener(new IActionListener() {
			public void onAction(ClientEvent event) {
				todo.setDone(!todo.isDone());
				checkBox.setSelected(todo.isDone());
			}
		});

		factory.createLabel(itemContainer, todo.getDescription());
		createImage(itemContainer, "./images/timeclock-on.gif", null);
		factory.createLabel(itemContainer, " " + todo.getEffort());
	}

	private IHtmlElement createImage(IComposite parent, String url, String id) {
		IHtmlElement html;
		if (id == null)
			html = getFactory().createHtmlElement(parent);
		else {
			html = getFactory().createHtmlElement(parent, null, id);
		}
		html.setTagName("IMG");
		html.setAttribute("src", url);
		return html;
	}

	/**
	 * Context Menu listener for the EDIT function.
	 */
	class EditListener extends BaseContextMenuListener {

		private ICanvas itemContainer;
		private Todo todo;
		private WebGuiFactory factory;

		public EditListener(ICanvas itemContainer, Todo todo, WebGuiFactory factory) {
			this.itemContainer = itemContainer;
			this.todo = todo;
			this.factory = factory;
		}

		public void onAction(ClientEvent event, IBaseControl control) {
			if (((ContextMenuItem) event.getSource()).getLabel().equals(EDIT)) {

				TodoEditorView todoEditorView = new TodoEditorView(getFactory(), itemContainer, todo);
				todoEditorView.registerListener(TodoEditorView.SAVE_EVENT, new TodoEditorViewListener(itemContainer,
						factory));
				todoEditorView.registerListener(TodoEditorView.CANCEL_EVENT, new TodoEditorViewListener(itemContainer,
						factory));
				todoEditorView.show();
			} else if (((ContextMenuItem) event.getSource()).getLabel().equals(DELETE)) {
				ISortableList list = (ISortableList) ((Canvas) itemContainer).getParent();
				list.removeListItem(itemContainer);
			}
		}

	}

	/**
	 * Call back for the Editor. Called when save or cancel were clicked.
	 * 
	 */
	public class TodoEditorViewListener implements IServerEventListener {
		ICanvas itemContainer;
		WebGuiFactory factory;

		public TodoEditorViewListener(ICanvas canvas, WebGuiFactory factory) {
			this.itemContainer = canvas;
			this.factory = factory;
		}

		/**
		 * Recreates the list item in single line mode. Destroys the Edit View.F
		 */
		public void handle(ServerEvent event) {
			switch (event.getTypeAsInt()) {
			case TodoEditorView.SAVE_EVENT:
			case TodoEditorView.CANCEL_EVENT:
				((Canvas) itemContainer).removeAllChildren();
				// TODO-PZ gehört eigentlich an das ende der methode --- martin meldet sich
				((Canvas) itemContainer).redraw();
				final Todo todo = (Todo) event.getParameter("todo");
				createListItem(factory, itemContainer, todo);
				break;
			}

		}

	}
}
