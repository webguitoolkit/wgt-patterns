package org.webguitoolkit.patterns.views.table;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.DataBag;
import org.webguitoolkit.ui.base.IDataBag;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.event.IActionListener;
import org.webguitoolkit.ui.controls.form.DefaultSelectModel;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.controls.table.ITable;
import org.webguitoolkit.ui.controls.table.ITableColumn;
import org.webguitoolkit.ui.controls.table.Table;
import org.webguitoolkit.ui.controls.table.renderer.BooleanColumnRenderer;
import org.webguitoolkit.ui.controls.table.renderer.ButtonColumnRenderer;
import org.webguitoolkit.ui.controls.table.renderer.CheckboxColumnRenderer;
import org.webguitoolkit.ui.controls.table.renderer.ITableButtonActionListener;
import org.webguitoolkit.ui.controls.table.renderer.ImageColumnRenderer;
import org.webguitoolkit.ui.controls.table.renderer.LinkButtonColumnRenderer;
import org.webguitoolkit.ui.controls.table.renderer.SelectColumnRenderer;
import org.webguitoolkit.ui.controls.table.renderer.TextColumnRenderer;
import org.webguitoolkit.ui.controls.util.conversion.ConvertUtil;

public class ColumnRendererView extends AbstractView {
	private ITable table;

	public ColumnRendererView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		// button to show the source code of this class
		factory.createButton(viewConnector, "./images/iconText.gif", "Source Code", "Source Code", new SourceView(factory, viewConnector,
				this.getClass().getName()));

		ITableLayout layout = factory.createTableLayout(viewConnector);

		table = factory.createTable(layout, "", 5);
		table.setTitle("Table Sample");

		// shortcut to add a CheckBox to a table
		// table.addCheckboxColumn("toDelete", null);

		// manual way to add a CheckBox to a table (with ActionListener)
		CheckboxColumnRenderer rend = new CheckboxColumnRenderer();
		ITableColumn col = getFactory().createTableColumn(table, "", "toDelete", false);
		col.setSortable(false);
		col.setRenderer(rend);
		col.setMandatory(true);
		rend.getCheckBox().setActionListener(new CheckbocListener());

		// use of ImageColumnRenderer
		col = factory.createTableColumn(table, " ", "img", false);
		col.setRenderer(new ImageColumnRenderer());
		col.setSortable(false);
		col.setWidth("18px");

		// make select model for select column renderer
		List<String[]> values = new ArrayList<String[]>();
		values.add(new String[] { "a", "Type A" });
		values.add(new String[] { "b", "Type B" });
		DefaultSelectModel typeSelect = new DefaultSelectModel();
		typeSelect.setOptions(values);

		// SelectBox renderer
		col = factory.createTableColumn(table, "Type", "type", true);
		col.setRenderer(new SelectColumnRenderer(typeSelect));
		col.setWidth("70px");

		// this is a editable text
		col = factory.createTableColumn(table, "Name", "name", true);
		col.setRenderer(new TextColumnRenderer());
		col.setWidth("200px");

		// date renderer
		col = factory.createTableColumn(table, "Date", "date", true);
		col.setConverter(ConvertUtil.DATE_CONVERTER);
		col.setWidth("350px");

		// use of BooleanColumnRenderer
		col = factory.createTableColumn(table, "status.key@Availability", "stat", true);
		col.setRenderer(new BooleanColumnRenderer("true.key@available", "false.key@unavailable"));

		// image button column renderer
		col = factory.createTableColumn(table, "", "button", true);
		col.setRenderer(new ButtonColumnRenderer(true, "name", new ButtonListener(), false));

		// static column renderer with text and image
		col = factory.createTableColumn(table, "", "textLinkButton", true);
		col.setRenderer(new ButtonColumnRenderer("delete@Delete", "./images/wgt/icons/delete.gif", "delete.tooltip@Delete Row", true,
				new ButtonListener()));
		col.setWidth("100px");

		// Link button column renderer
		col = factory.createTableColumn(table, "", "link", true);
		col.setRenderer(new LinkButtonColumnRenderer(null, new ButtonListener()));

		layout.newRow();
		factory.createButton(layout, "./images/wgt/icons/edit.gif", "Save", "Save", new EditListener(table));

		List<IDataBag> data = new ArrayList<IDataBag>();
		for (int i = 0; i < 3; i++) {
			IDataBag bag = new DataBag(null);
			if (i % 4 == 0)
				bag.addProperty("type", "a");
			else
				bag.addProperty("type", "b");

			bag.addProperty("name", "name " + i);
			bag.addProperty("link", "link " + i);
			bag.addProperty("img", "./images/iconText.gif");
			Calendar cal = new GregorianCalendar();
			cal.add(Calendar.MONTH, i);
			bag.addProperty("date", cal.getTime());
			bag.addProperty("toDelete", new Boolean(i % 3 == 0));
			bag.addProperty("stat", new Boolean(i % 3 == 0));
			if (i % 2 == 0)
				bag.addProperty("button", "./images/wgt/icons/edit.gif");
			else
				bag.addProperty("button", "");
			data.add(bag);
		}
		table.getDefaultModel().setTableData(data);

		table.reload();

	}

	class ButtonListener implements ITableButtonActionListener {

		public void onAction(ClientEvent event, ITable table, int row) {
			table.getPage().sendInfo("Clicked page: " + table.getRow(row).getString("name"));
		}

	}

	class EditListener implements IActionListener {

		private ITable table;

		public EditListener(ITable table) {
			this.table = table;
		}

		public void onAction(ClientEvent event) {
			table.save2Bag();
			List<IDataBag> data = table.getDefaultModel().getTableData();
			String result = "";
			for (IDataBag bag : data) {
				result += "Name: " + bag.getString("name");
				result += ", Type: " + bag.getString("type");
				result += ", Checked: " + bag.getBool("toDelete");
				result += "<br>";
			}
			table.getPage().sendInfo(result);
		}

	}

	class CheckbocListener implements IActionListener {
		public void onAction(ClientEvent event) {

			// work around cause there is no other way to get the row
			// only the relative rowId,
			String rowid = StringUtils.substringBetween(event.getSourceId(), ".r", ".");

			try {
				// we have to add the offset
				int row = Integer.parseInt(rowid) + ((Table)table).getFirstRow();

				// store data in bags
				table.save2Bag();

				// get the DataBag for the row
				IDataBag checkedRow = table.getRow(row);

				table.getPage().sendInfo("You checked row " + row + " check: " + checkedRow.getBool("toDelete"));
			}
			catch (NumberFormatException e) {
				// boom!
			}
		}
	}
}
