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

import java.util.ArrayList;
import java.util.List;

import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.DataBag;
import org.webguitoolkit.ui.base.IDataBag;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.form.DefaultSelectModel;
import org.webguitoolkit.ui.controls.form.IButton;
import org.webguitoolkit.ui.controls.form.ILabel;
import org.webguitoolkit.ui.controls.form.IRadio;
import org.webguitoolkit.ui.controls.form.ISelect;
import org.webguitoolkit.ui.controls.form.IText;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.controls.table.ITable;
import org.webguitoolkit.ui.controls.table.ITableColumn;
import org.webguitoolkit.ui.controls.table.Table;
import org.webguitoolkit.ui.controls.table.renderer.TeaserTextRenderer;
import org.webguitoolkit.ui.controls.util.Tooltip;

public class TooltipView extends AbstractView {
	private IRadio ra1;
	private IRadio ra2;

	public TooltipView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		// button to show the source code of this class
		factory.createButton(viewConnector, "./images/iconText.gif", "Source Code", "Source Code", new SourceView(
				factory, viewConnector, this.getClass().getName()), null);

		ITableLayout layoutTable = factory.createTableLayout(viewConnector);

		ILabel labelWithTooltip = factory.createLabel(layoutTable, "Move mouse over label to get a simple tooltip");
		Tooltip simpleTooltip = new Tooltip("This is a simple tooltip");
		labelWithTooltip.setTooltip(simpleTooltip);
		layoutTable.newRow();

		ISelect se = factory.createSelect(layoutTable, "");
		final List<String[]> options = new ArrayList<String[]>();
		for (int i = 0; i < 50; i++)
			options.add(new String[] { i + "_id", i + "_Name" });

		DefaultSelectModel model = new DefaultSelectModel();
		model.setOptions(options);
		se.setModel(model);
		se.loadList();

		Tooltip basicTooltip = new Tooltip(
				"This is a litte bit more complex for a tooltip - It even has a header and a body part!");
		basicTooltip.setTrackMouseMovement(true);
		basicTooltip.setShowBody("-");
		se.setTooltip(basicTooltip);

		layoutTable.newRow();
		IText inputFieldWithText = factory.createText(layoutTable, "");

		Tooltip formatedTooltip = new Tooltip(null);
		formatedTooltip.setTrackMouseMovement(true);
		formatedTooltip
				.setLongHTMLText("<b>This is a html</b> <i>formated</i> tooltip<br/><br/>Evene line feeds are possible");
		inputFieldWithText.setTooltip(formatedTooltip);

		layoutTable.newRow();
		IText inputFieldWithPicture = factory.createText(layoutTable, "");

		Tooltip imageTooltip = new Tooltip(null);
		imageTooltip.setTrackMouseMovement(true);
		imageTooltip.setImageSrc("http://www.endress.com/eh/home.nsf/imgref/N_top-banner.jpg/$FILE/top-banner.jpg");
		inputFieldWithPicture.setTooltip(imageTooltip);

		layoutTable.newRow();
		IButton bu = factory.createButton(layoutTable, null, "I am a button", "I am a button", this);
		bu.setTooltip(new Tooltip("I am a Buttontooltip"));
		layoutTable.newRow();
		ra1 = factory.createRadio(layoutTable, "test", "Radio 1");
		ra1.setTooltip(formatedTooltip);
		ra1.setSelected(true);

		ra2 = factory.createRadio(layoutTable, "test", "Radio 2");
		ra2.setTooltip(basicTooltip);
		
		layoutTable.newRow();
		ITable table = factory.createTable(layoutTable, "Test Table", 5);
		layoutTable.getCurrentCell().setColSpan(3);
		ITableColumn col1 = factory.createTableColumn(table, "Col1", "col1", true);
		ITableColumn col2 = factory.createTableColumn(table, "Col2", "col2", true);
		TeaserTextRenderer teaser = new TeaserTextRenderer(30);
		col2.setRenderer(teaser);
		ITableColumn col3 = factory.createTableColumn(table, "Col3", "col3", true);
		col3.setRenderer(teaser);
		String loremIpsum1 = "ä#ä+ä #ä#ä '' \"\" ^ # +*~~~ eLorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";
		String loremIpsum = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?";
		String loremIpsum2 = "At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.";
		// Fill with data
		List<IDataBag> data = new ArrayList<IDataBag>();
		for (int i = 0; i < 50; i++) {
			IDataBag bag = new DataBag(null);
			bag.addProperty("col1", loremIpsum);
			bag.addProperty("col2", i+" "+loremIpsum1);
			bag.addProperty("col3", loremIpsum2);
			data.add(bag);
		}
		table.getDefaultModel().setTableData(data);
		// send data to the front end
		table.reload();
		layoutTable.newRow();
		
		Table teaserTable = (Table)factory.createTable(layoutTable, "teaserTable", 5);
		TeaserTextRenderer t2 = new TeaserTextRenderer(20,"~~~");
		teaserTable.setDefaultRenderer(t2);
		layoutTable.getCurrentCell().setColSpan(3);
		factory.createTableColumn(teaserTable, "Col1", "col1", true);
		factory.createTableColumn(teaserTable, "Col2", "col2", true);
		teaserTable.getDefaultModel().setTableData(data);
		// send data to the front end
		teaserTable.reload();
		
		
		
	}
}
