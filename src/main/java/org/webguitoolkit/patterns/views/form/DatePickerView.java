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
import java.sql.Date;
import java.text.DateFormat;

import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.DataBag;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.event.IActionListener;
import org.webguitoolkit.ui.controls.form.IButton;
import org.webguitoolkit.ui.controls.form.ICompound;
import org.webguitoolkit.ui.controls.form.ILabel;
import org.webguitoolkit.ui.controls.form.IText;
import org.webguitoolkit.ui.controls.layout.SequentialTableLayout;
import org.webguitoolkit.ui.controls.util.conversion.ConvertUtil;
import org.webguitoolkit.ui.controls.util.validation.ValidatorUtil;

public class DatePickerView extends AbstractView {

	public DatePickerView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		// button to show the source code of this class

		viewConnector.setLayout(new SequentialTableLayout());

		factory.createButton(viewConnector, "./images/iconText.gif", null, "Source Code",
				new SourceView(factory, viewConnector, this.getClass().getName())).setLayoutData(SequentialTableLayout.getLastInRow());

		final ICompound compound = factory.createCompound(viewConnector);
		compound.setBag(new DataBag(new DateTest()));
		compound.setLayout(new SequentialTableLayout());

		ILabel lablel = factory.createLabel(compound, "Date1-Date Short");

		final IText text1 = factory.createText(compound, "date1", lablel);
		text1.setTooltip(ValidatorUtil.DATE_VALIDATOR.getTooltip());
		text1.setConverter(new ConvertUtil.DateConverter());
		text1.setLayoutData(SequentialTableLayout.getLastInRow());

		lablel = factory.createLabel(compound, "Date2-Date Short 4 Letter Year");

		final IText text2 = factory.createText(compound, "date2", lablel);
		text2.setTooltip(ValidatorUtil.DATE_VALIDATOR.getTooltip());
		text2.setConverter(new ConvertUtil.DateConverter(ConvertUtil.DateConverter.DATEPATTERN_SHORT_4YEAR));
		// text2.setConverter(ConvertUtil.DATE_CONVERTER); // should be the same because this format is now default
		text2.setLayoutData(SequentialTableLayout.getLastInRow());

		lablel = factory.createLabel(compound, "Date3-Date Medium");

		final IText text3 = factory.createText(compound, "date3", lablel);
		text3.setTooltip(ValidatorUtil.DATE_VALIDATOR.getTooltip());
		text3.setConverter(new ConvertUtil.DateConverter(ConvertUtil.DateConverter.DATEPATTERN_MEDIUM));
		text3.setLayoutData(SequentialTableLayout.getLastInRow());

		lablel = factory.createLabel(compound, "Date4-Date Long");

		final IText text4 = factory.createText(compound, "date4", lablel);
		text4.setTooltip(ValidatorUtil.DATE_VALIDATOR.getTooltip());
		text4.setConverter(new ConvertUtil.DateConverter(ConvertUtil.DateConverter.DATEPATTERN_LONG));
		text4.setLayoutData(SequentialTableLayout.getLastInRow());

		lablel = factory.createLabel(compound, "Date5-Date Full");

		final IText text5 = factory.createText(compound, "date5", lablel);
		text5.setTooltip(ValidatorUtil.DATE_VALIDATOR.getTooltip());
		text5.setConverter(new ConvertUtil.DateConverter(ConvertUtil.DateConverter.DATEPATTERN_FULL));
		text5.setLayoutData(SequentialTableLayout.getLastInRow());

		lablel = factory.createLabel(compound, "Date6-DateTime");

		final IText text6 = factory.createText(compound, "date6", lablel);
		text6.setTooltip(ValidatorUtil.DATE_VALIDATOR.getTooltip());
		text6.setConverter(new ConvertUtil.DateTimeConverter());
		text6.setLayoutData(SequentialTableLayout.getLastInRow());

		IButton savebutton = factory.createButton(compound, null, "Save", "", new IActionListener() {
			public void onAction(ClientEvent event) {
				compound.save();
				compound.getBag().save();
				DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
				DateTest delegate = (DateTest)compound.getBag().getDelegate();
				getPage().sendInfo("Date6 " + dateFormat.format(delegate.getDate6()));
				getPage().sendInfo("Date5 " + dateFormat.format(delegate.getDate5()));
				getPage().sendInfo("Date4 " + dateFormat.format(delegate.getDate4()));
				getPage().sendInfo("Date3 " + dateFormat.format(delegate.getDate3()));
				getPage().sendInfo("Date2 " + dateFormat.format(delegate.getDate2()));
				getPage().sendInfo("Date1 " + dateFormat.format(delegate.getDate1()));
			}
		});
		savebutton.setLayoutData(SequentialTableLayout.getLastInRow());
		compound.load();
	}

	public class DateTest implements Serializable {
		public Date date1;
		public Date date2;
		public Date date3;
		public Date date4;
		public Date date5;
		public Date date6;

		public DateTest() {
			date1 = new Date(System.currentTimeMillis());
			date2 = new Date(System.currentTimeMillis());
			date3 = new Date(System.currentTimeMillis());
			date4 = new Date(System.currentTimeMillis());
			date5 = new Date(System.currentTimeMillis());
			date6 = new Date(System.currentTimeMillis());
		}

		public Date getDate1() {
			return date1;
		}

		public void setDate1(Date date1) {
			this.date1 = date1;
		}

		public Date getDate2() {
			return date2;
		}

		public void setDate2(Date date2) {
			this.date2 = date2;
		}

		public Date getDate3() {
			return date3;
		}

		public void setDate3(Date date3) {
			this.date3 = date3;
		}

		public Date getDate4() {
			return date4;
		}

		public void setDate4(Date date4) {
			this.date4 = date4;
		}

		public Date getDate5() {
			return date5;
		}

		public void setDate5(Date date5) {
			this.date5 = date5;
		}

		public Date getDate6() {
			return date6;
		}

		public void setDate6(Date date6) {
			this.date6 = date6;
		}

	}
}
