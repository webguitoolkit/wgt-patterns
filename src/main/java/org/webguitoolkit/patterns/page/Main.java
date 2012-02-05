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

/**
 * This is the start page of the project portal application.
 * When this page is called the user has been already authenticated.
 * 
 * The page provides by default the list of projects the user is assigned to.
 *
 * @author PZ
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.webguitoolkit.patterns.doc.examples.ChartView;
import org.webguitoolkit.patterns.views.MissingView;
import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.patterns.views.complex.WizardPopupView;
import org.webguitoolkit.patterns.views.complex.WizardView;
import org.webguitoolkit.patterns.views.complex.WizardViewView;
import org.webguitoolkit.patterns.views.form.ButtonBarView;
import org.webguitoolkit.patterns.views.form.CheckBoxView;
import org.webguitoolkit.patterns.views.form.CompoundView;
import org.webguitoolkit.patterns.views.form.DatePickerView;
import org.webguitoolkit.patterns.views.form.FileUploadView;
import org.webguitoolkit.patterns.views.form.MultiPopupSelectView;
import org.webguitoolkit.patterns.views.form.OptionControlGroupView;
import org.webguitoolkit.patterns.views.form.PopupSelectView;
import org.webguitoolkit.patterns.views.form.RadioView;
import org.webguitoolkit.patterns.views.form.SelectView;
import org.webguitoolkit.patterns.views.form.TextView;
import org.webguitoolkit.patterns.views.functions.ContextMenueView;
import org.webguitoolkit.patterns.views.functions.DragDropListsView;
import org.webguitoolkit.patterns.views.functions.PermissionView;
import org.webguitoolkit.patterns.views.functions.StyleManipulationView;
import org.webguitoolkit.patterns.views.grid.GridView;
import org.webguitoolkit.patterns.views.layout.AccordionView;
import org.webguitoolkit.patterns.views.layout.BorderLayoutView;
import org.webguitoolkit.patterns.views.layout.ButtonTabView;
import org.webguitoolkit.patterns.views.layout.CanvasView;
import org.webguitoolkit.patterns.views.layout.GridLayoutView;
import org.webguitoolkit.patterns.views.layout.PopupExmpleView;
import org.webguitoolkit.patterns.views.layout.SequentialDivLayoutView;
import org.webguitoolkit.patterns.views.layout.SequentialTableLayoutView;
import org.webguitoolkit.patterns.views.layout.TabView;
import org.webguitoolkit.patterns.views.layout.TableLayoutView;
import org.webguitoolkit.patterns.views.layout.Todo;
import org.webguitoolkit.patterns.views.layout.TodoListView;
import org.webguitoolkit.patterns.views.patterns.MasterDetailControllerView;
import org.webguitoolkit.patterns.views.patterns.MasterDetailFactoryView;
import org.webguitoolkit.patterns.views.patterns.MasterDetailView;
import org.webguitoolkit.patterns.views.patterns.MockView;
import org.webguitoolkit.patterns.views.patterns.ViewSampleView;
import org.webguitoolkit.patterns.views.presentation.FormView;
import org.webguitoolkit.patterns.views.presentation.MixedObjectTableView;
import org.webguitoolkit.patterns.views.presentation.TableView;
import org.webguitoolkit.patterns.views.presentation.TreeTableViewNew;
import org.webguitoolkit.patterns.views.simple.ButtonJQView;
import org.webguitoolkit.patterns.views.simple.ButtonView;
import org.webguitoolkit.patterns.views.simple.HtmlElementView;
import org.webguitoolkit.patterns.views.simple.LabelView;
import org.webguitoolkit.patterns.views.simple.SuggestView;
import org.webguitoolkit.patterns.views.simple.TabIndexView;
import org.webguitoolkit.patterns.views.simple.TooltipView;
import org.webguitoolkit.patterns.views.table.ColumnRendererView;
import org.webguitoolkit.patterns.views.table.ContextMenuView;
import org.webguitoolkit.patterns.views.table.DragDropView;
import org.webguitoolkit.patterns.views.table.ExportTableView;
import org.webguitoolkit.patterns.views.table.FilterView;
import org.webguitoolkit.patterns.views.table.NormalTableView;
import org.webguitoolkit.patterns.views.table.RowHandlerView;
import org.webguitoolkit.patterns.views.table.SimpleView;
import org.webguitoolkit.patterns.views.table.TableConfigurationView;
import org.webguitoolkit.patterns.views.tree.CheckboxTreeView;
import org.webguitoolkit.patterns.views.tree.ContextMenuTreeView;
import org.webguitoolkit.patterns.views.tree.DragAndDropTreeView;
import org.webguitoolkit.patterns.views.tree.DynamicTreeView;
import org.webguitoolkit.patterns.views.tree.SimpleTreeView;
import org.webguitoolkit.patterns.views.tree.SortableTree;
import org.webguitoolkit.patterns.views.tree.TreeEventView;
import org.webguitoolkit.patterns.views.tree.TreeFunctionsView;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.Page;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.controls.menu.IMenu;
import org.webguitoolkit.ui.controls.menu.IMenuBar;
import org.webguitoolkit.ui.controls.menu.MenuItem;
import org.webguitoolkit.ui.tools.SysinfoView;

public class Main extends Page {

	@Override
	protected void pageDefaults() {
		super.pageDefaults();
		initPatternHeader();
	}

	protected void initPatternHeader() {
		addWgtCSS("standard_theme.css");
		addHeaderCSS("styles/theme_patterns.css");
		addHeaderCSS("styles/wgtCalendar.css");
		addHeaderCSS("css/wgtPattern.css");
		addWgtCSS("clickmenu_portal.css");
		addWgtCSS("sandbox/button20.css");
		addFavicon("./images/wgt/iconw.gif");
		addWgtJS("preventBackspaceBack.js");
//		addWgtJS("wgt.controller.select.js");
//		addWgtJS("wgt.controller.table.js");
//		addWgtJS( ResourceServlet.FULL_JS );
		// List control
		addWgtCSS("standard/wgtlist.css");
		addWgtCSS("standard/jquery-ui.css");
	}

	@Override
	protected void pageInit() {

		getStyle().add("padding", "0px");
		getStyle().add("margin", "0px");

		WebGuiFactory factory = getFactory();

		ITableLayout layout = factory.createTableLayout(this);
		layout.getStyle().add("width", "100%");
		layout.getEcsTable().setBorder(0);
		layout.getEcsTable().setCellPadding(0);
		layout.getEcsTable().setCellSpacing(0);

		IMenuBar menuBar = factory.createMenuBar(layout);
		layout.getCurrentCell().setStyle("padding-top: 0px; margin-top: 0px;");
		layout.newRow();

		ICanvas viewConnector = factory.createCanvas(layout);
		viewConnector.getStyle().add("margin", "10px");

		// Menu simple controls
		IMenu menuSimpleControls = factory.createMenu(menuBar, "Simple Controls");
		factory.createMenuItem(menuSimpleControls, "Label", null, new LabelView(factory, viewConnector));
		factory.createMenuItem(menuSimpleControls, "Buttons", null, new ButtonView(factory, viewConnector));
		// factory.createMenuItem(menuSimpleControls, "Button 2.0", null, new Button2ZeroView(factory, viewConnector));
		factory.createMenuItem(menuSimpleControls, "HTML Element", null, new HtmlElementView(factory, viewConnector));
		factory.createMenuItem(menuSimpleControls, "Advanced Tooltips", null, new TooltipView(factory, viewConnector));
		factory.createMenuItem(menuSimpleControls, "Type ahead suggest", null, new SuggestView(factory, viewConnector));
		factory.createMenuItem(menuSimpleControls, "Tabindex", null, new TabIndexView(factory, viewConnector));

		// Menu form controls
		IMenu menuFormControls = factory.createMenu(menuBar, "Form Controls");
		factory.createMenuItem(menuFormControls, "Text", null, new TextView(factory, viewConnector));
		factory.createMenuItem(menuFormControls, "Checkbox", null, new CheckBoxView(factory, viewConnector));
		factory.createMenuItem(menuFormControls, "Radio", null, new RadioView(factory, viewConnector));
		factory.createMenuItem(menuFormControls, "Date Picker", null, new DatePickerView(factory, viewConnector));
		factory.createMenuItem(menuFormControls, "Select Box", null, new SelectView(factory, viewConnector));
		factory.createMenuItem(menuFormControls, "Buttonbar", null, new ButtonBarView(factory, viewConnector));
		factory.createMenuItem(menuFormControls, "Popup Select", null, new PopupSelectView(factory, viewConnector));
		factory.createMenuItem(menuFormControls, "Multi Popup Select", null, new MultiPopupSelectView(factory, viewConnector));
		factory.createMenuItem(menuFormControls, "Radio and Checkbox Group", null, new OptionControlGroupView(factory, viewConnector));
		factory.createMenuItem(menuFormControls, "File Upload", null, new FileUploadView(factory, viewConnector));

		// Menu complex controls
		IMenu menuComplexControls = factory.createMenu(menuBar, "Complex Controls");
		factory.createMenuItem(menuComplexControls, "Wizard", null, new WizardView(factory, viewConnector));
		factory.createMenuItem(menuComplexControls, "Wizard as Popup", null, new WizardPopupView(factory, viewConnector));
		factory.createMenuItem(menuComplexControls, "Wizard as View", null, new WizardViewView(factory, viewConnector));
		// factory.createMenuItem(menuComplexControls, "File Upload", null, new
		// FileUploadView(factory, viewConnector));

		// Menu data presentation
		IMenu menuDataPresentation = factory.createMenu(menuBar, "Data Presentation");
		factory.createMenuItem(menuDataPresentation, "Simple Compound", null, new CompoundView(factory, viewConnector));
		factory.createMenuItem(menuDataPresentation, "Form", null, new FormView(factory, viewConnector));
		factory.createMenuItem(menuDataPresentation, "Table", null, new TableView(factory, viewConnector));
		factory.createMenuItem(menuDataPresentation, "Export Table", null, new ExportTableView(factory, viewConnector));
		factory.createMenuItem(menuDataPresentation, "MixedTable", null, new MixedObjectTableView(factory, viewConnector));
		factory.createMenuItem(menuDataPresentation, "Tree", null, new DragAndDropTreeView(factory, viewConnector));
		factory.createMenuItem(menuDataPresentation, "Tree Table", null, new TreeTableViewNew(factory, viewConnector));
		factory.createMenuItem(menuDataPresentation, "Chart (Pie)", null, new ChartView(factory, viewConnector));

		List<Todo> todos = new ArrayList<Todo>();
		todos.add(new Todo(" Wake up at 6:00"));
		todos.add(new Todo(" Take shower at 6:10"));
		todos.add(new Todo(" Have breakfast at 6:30"));
		todos.add(new Todo(" Check-out morning news at 7:00"));
		todos.add(new Todo(" Do workout at 7:30"));
		todos.add(new Todo(" Go to work at 8:00"));
		Properties p = new Properties();
		p.put(TodoListView.DESCRIPTION,
				"<h2>Sortable List: You can sort the items with drag & drop and you can edit them via the context menu</h2>");
		p.put(TodoListView.ICON, "./images/mouse.png");

		factory.createMenuItem(menuDataPresentation, "Sortable List", null, new TodoListView(factory, viewConnector, todos, p));

		// Menu layout
		IMenu menuLayout = factory.createMenu(menuBar, "Layout");
		factory.createMenuItem(menuLayout, "TableLayout", null, new TableLayoutView(factory, viewConnector));
		factory.createMenuItem(menuLayout, "Tabs", null, new TabView(factory, viewConnector));
		factory.createMenuItem(menuLayout, "ButtonTabs", null, new ButtonTabView(factory, viewConnector));
		factory.createMenuItem(menuLayout, "Accordion", null, new AccordionView(factory, viewConnector));
		factory.createMenuItem(menuLayout, "Canvas", null, new CanvasView(factory, viewConnector));
		factory.createMenuItem(menuLayout, "PopUp", null, new PopupExmpleView(factory, viewConnector));
		factory.createMenuItem(menuLayout, "GridLayout", null, new GridLayoutView(factory, viewConnector));
		factory.createMenuItem(menuLayout, "BorderLayout", null, new BorderLayoutView(factory, viewConnector));
		factory.createMenuItem(menuLayout, "SequentialTableLayout", null, new SequentialTableLayoutView(factory, viewConnector));
		factory.createMenuItem(menuLayout, "SequentialDivLayout", null, new SequentialDivLayoutView(factory, viewConnector));

		IMenu menuPatterns = factory.createMenu(menuBar, "Typical Pattern");
		factory.createMenuItem(menuPatterns, "Master-Detail", null, new MasterDetailView(factory, viewConnector));
		factory.createMenuItem(menuPatterns, "Tree-Form", null, new MissingView(factory, viewConnector));
//		factory.createMenuItem(menuPatterns, "Split Screen", null, new SplitScreenView(factory, viewConnector, 550));
		factory.createMenuItem(menuPatterns, "Mock", null, new MockView(factory, viewConnector));
		factory.createMenuItem(menuPatterns, "View", null, new ViewSampleView(factory, viewConnector));
		factory.createMenuItem(menuPatterns, "Master-Detail-Factory new (development)", null, new MasterDetailFactoryView(factory,
				viewConnector));
		factory.createMenuItem(menuPatterns, "Master-Detail-Controller new (development)", null, new MasterDetailControllerView(factory,
				viewConnector));

		// Menu functions
		IMenu menuFunctions = factory.createMenu(menuBar, "Functions");
		factory.createMenuItem(menuFunctions, "Context Menue", null, new ContextMenueView(factory, viewConnector));
		factory.createMenuItem(menuFunctions, "Permissions", null, new PermissionView(factory, viewConnector));
		factory.createMenuItem(menuFunctions, "Change Style", null, new StyleManipulationView(factory, viewConnector));
		factory.createMenuItem(menuFunctions, "Drag/Drop Lists", null, new DragDropListsView(factory, viewConnector));

		// Table based patterns
		IMenu tableFunctions = factory.createMenu(menuBar, "Table");
		factory.createMenuItem(tableFunctions, "Normal Tables", null, new NormalTableView(factory, viewConnector));
		factory.createMenuItem(tableFunctions, "Simple Table", null, new SimpleView(factory, viewConnector));
		factory.createMenuItem(tableFunctions, "Column Renderer", null, new ColumnRendererView(factory, viewConnector));
		factory.createMenuItem(tableFunctions, "Drag Drop", null, new DragDropView(factory, viewConnector));
		factory.createMenuItem(tableFunctions, "Filter", null, new FilterView(factory, viewConnector));
		factory.createMenuItem(tableFunctions, "Row Handler", null, new RowHandlerView(factory, viewConnector));
		factory.createMenuItem(tableFunctions, "Context Menu", null, new ContextMenuView(factory, viewConnector));
		factory.createMenuItem(tableFunctions, "Table Configuration", null, new TableConfigurationView(factory, viewConnector));

		// Tree based patterns
		IMenu treeFunctions = factory.createMenu(menuBar, "Tree");
		factory.createMenuItem(treeFunctions, "Simple Tree", null, new SimpleTreeView(factory, viewConnector));
		factory.createMenuItem(treeFunctions, "Tree Events", null, new TreeEventView(factory, viewConnector));
		factory.createMenuItem(treeFunctions, "Dynamic Tree", null, new DynamicTreeView(factory, viewConnector));
		factory.createMenuItem(treeFunctions, "Tree Functions", null, new TreeFunctionsView(factory, viewConnector));
		factory.createMenuItem(treeFunctions, "Tree with Check Boxes", null, new CheckboxTreeView(factory, viewConnector));
		factory.createMenuItem(treeFunctions, "Tree Context Menu", null, new ContextMenuTreeView(factory, viewConnector));
		factory.createMenuItem(treeFunctions, "Drag&Drop Tree", null, new DragAndDropTreeView(factory, viewConnector));
		factory.createMenuItem(treeFunctions, "Sortable Tree", null, new SortableTree(factory, viewConnector));

		IMenu gridFunctions = factory.createMenu(menuBar, "Under Construction");
		factory.createMenuItem(gridFunctions, "Simple Grid", null, new GridView(factory, viewConnector));
		factory.createMenuItem(gridFunctions, "JQ Button", null, new ButtonJQView(factory, viewConnector));

		createAdditionalMenuEntries( factory, viewConnector, menuBar );
		
		MenuItem menuItem = new MenuItem();
		menuBar.addMenuItem(menuItem);
		menuItem.setLabel("Page Source");
		menuItem.setActionListener(new SourceView(factory, viewConnector, this.getClass().getName()));

		menuItem = new MenuItem();
		menuBar.addMenuItem(menuItem);
		menuItem.setLabel("About WGT Pattern");
		menuItem.setActionListener(new SysinfoView(factory, viewConnector));

		
	}

	/**
	 * can be overwridden 
	 * @param menuBar
	 */
	protected void createAdditionalMenuEntries(WebGuiFactory factory, ICanvas viewConnector, IMenuBar menuBar) {
	}

	@Override
	protected String title() {
		return "WebGUIToolkit Patterns ";
	}

}
