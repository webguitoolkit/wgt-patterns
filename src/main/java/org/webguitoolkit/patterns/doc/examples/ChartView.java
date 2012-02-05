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
package org.webguitoolkit.patterns.doc.examples;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.chart.DefaultChartModel;
import org.webguitoolkit.ui.controls.chart.IChart;
import org.webguitoolkit.ui.controls.chart.IChartModel;
import org.webguitoolkit.ui.controls.container.ICanvas;

public class ChartView extends AbstractView {

	public ChartView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {	
		// create a dataset for JFreeChart...
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("WGT", 30);
		dataset.setValue("Rap", 19);
		dataset.setValue("GWT", 51);
		
		// create pie chart 
		JFreeChart jfchart = ChartFactory.createPieChart(
				"GUI Toolkits",	dataset,
				true, // legend?
				true, // tooltips?
				false // URLs?
				);		
		
		IChart chart = factory.createChart(viewConnector);
		IChartModel model = chart.getModel();
		((DefaultChartModel) model).setChart(jfchart);

		// set the width and heights of the chart image
		chart.setHeight(250);
		chart.setWidth(250);
		
		// send the chart url to the frontend
		chart.load();
	}
}
