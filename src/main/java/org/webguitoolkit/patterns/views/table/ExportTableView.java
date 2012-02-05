package org.webguitoolkit.patterns.views.table;

import java.util.ArrayList;
import java.util.List;

import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.DataBag;
import org.webguitoolkit.ui.base.IDataBag;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.form.button.StandardButton;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.controls.table.AbstractTableListener;
import org.webguitoolkit.ui.controls.table.ITable;
import org.webguitoolkit.ui.controls.table.ITableColumn;
import org.webguitoolkit.ui.controls.table.Table;
import org.webguitoolkit.ui.util.export.ExportButtonListener;
import org.webguitoolkit.ui.util.export.ITableExport;
import org.webguitoolkit.ui.util.export.TableExportOptions;

/**
 * sample of exporting table to pdf and excel 
 */
public class ExportTableView extends AbstractView {

	public ExportTableView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls( WebGuiFactory factory, ICanvas viewConnector ) {
		// button to show the source code of this class
		factory.createButton( viewConnector, "./images/iconText.gif", null,"Source Code", new SourceView( factory, viewConnector, this.getClass().getName() ) );

		ITableLayout layout = factory.createTableLayout(viewConnector);

		ITable table = factory.createTable(layout, "Scroll Table Sample", 5,"exportTable");
		TableExportOptions export = new TableExportOptions();
		export.setExportAsExcel(true);
		export.setExcelButtonLabelText("Excel Export");
		export.setExcelButtonTooltip("Excel Export");
		export.setExcelDateFormat("m/d/yyyy");
		export.setExcelSheetName("ExportShhet");
		
		export.setExportAsPDF(true);
		export.setPdfButtonLabelText("Pdf Export");
		export.setPdfButtonTooltip("Pdf Export");
		export.setFooterText("Hello Footer");
		export.setHeaderImage("EH_Claim_2c.jpg");
		export.setHeadline("Hello Headline");
		export.setPdfFontType(TableExportOptions.FONT_HELVETIVA);
		export.setPdfFontColour(TableExportOptions.COLOURS_MAGENTA);
		export.setPdfFontSize(14);
		export.setPdfPageColour(TableExportOptions.COLOURS_LIGHTGREY);
		export.setPdfPosition(TableExportOptions.PDF_PORTRAIT);
		export.setFileName("TableExport");		
		export.setShowDefaultFooter(true);
		export.setShowDefaultHeader(true);
		export.setShowPageNumber(true);
		table.setExportOptions(export);
		
		StandardButton xmlExport = new StandardButton();
		xmlExport.setLabelKey("button.xml@XML");
		xmlExport.setActionListener(new ExportButtonListener((Table)table, ITableExport.EXPORT_TYPE_XML ));
		table.addButton(xmlExport);
		
		// for custom exports you have to configure the ExportServlet in the web.xml with your ITableExport class
		
		table.setListener(new TableListener());
		ITableColumn col = factory.createTableColumn(table, "Type", "type", true);
		col.setWidth("50px");
		col = factory.createTableColumn(table, "Name", "name", true);
		col.setWidth("200px");
		col = factory.createTableColumn(table, "Description", "description", true);
		col.setWidth("500px");
		

		
		
		layout.newRow();
		ITable table1 = factory.createTable(layout, "Simple Export", 5, "simpleExport");
		table1.addExportButton(true, false);
		ITableColumn col1 = factory.createTableColumn(table1, "Type", "type", true);
		col1.setWidth("50px");
		col1 = factory.createTableColumn(table1, "Name", "name", true);
		col1.setWidth("200px");
		col1 = factory.createTableColumn(table1, "Description", "description", true);
		col1.setWidth("500px");
		
		
		// Fill with data
		List<IDataBag> data = new ArrayList<IDataBag>();
		for (int i = 0; i < 100; i++) {
			IDataBag bag = new DataBag(null);
			bag.addProperty("type", "type " + i);
			bag.addProperty("name", "name " + i);
			bag.addProperty("description", "description " + i);
			data.add(bag);
		}
		table1.getDefaultModel().setTableData(data);
		table1.reload();
		table.getDefaultModel().setTableData(data);
		table.reload();

	}

	/**
	 * Handles table related events
	 */
	class TableListener extends AbstractTableListener {
		@Override
		public void onRowSelection(ITable table, int row) {
			// get row representing DataBag
			IDataBag bag = table.getRow(row);
			
			// send info
			table.getPage().sendInfo( "Clicked: " + bag.getString("name") );
		}

	}

}