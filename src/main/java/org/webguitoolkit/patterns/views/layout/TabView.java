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
package org.webguitoolkit.patterns.views.layout;

import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.DataBag;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.event.IActionListener;
import org.webguitoolkit.ui.controls.form.AbstractButtonBarListener;
import org.webguitoolkit.ui.controls.form.ICompound;
import org.webguitoolkit.ui.controls.form.ILabel;
import org.webguitoolkit.ui.controls.form.IText;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
import org.webguitoolkit.ui.controls.tab.ITab;
import org.webguitoolkit.ui.controls.tab.ITabListener;
import org.webguitoolkit.ui.controls.tab.ITabStrip;
import org.webguitoolkit.ui.controls.util.validation.ValidatorUtil;

public class TabView extends AbstractView {

	private ITab summaryTab;
	private ITab detailTab;

	public TabView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		// button to show the source code of this class
		factory.createButton(viewConnector, "./images/iconText.gif", "Source Code", "Source Code", new SourceView(factory, viewConnector,
				this.getClass().getName()));

		final ITabStrip tabstrip = factory.createTabStrip(viewConnector);

		// tabstrip.getStyle().add( "height","200px" ); // TODO :
		// tabstrip.getStyle().add( "min-height","100px" );
		// tabstrip.getStyle().add( "height","100px" );
		tabstrip.setListener(new TabstripActionListener());

		summaryTab = factory.createTab(tabstrip, "Summary");
		summaryTab.addCssClass("mytab");
		detailTab = factory.createTab(tabstrip, "Details");

		ITab tabInTab = factory.createTab(tabstrip, "Tab in Tab");
		final ITab lastTab = factory.createTab(tabstrip, "Tab 3");

		factory.createLabel(lastTab, "Some text");

		ITabStrip subtabstrip = factory.createTabStrip(tabInTab);
		// subtabstrip.setDisplayMode(ITabStrip.DISPLAY_MODE_TAB_NO_STRIP);
		ITab tabInTabTab = factory.createTab(subtabstrip, "Sub-Tab 1");
		factory.createLabel(tabInTabTab, "Some text");

		ICompound summaryCompound = factory.createCompound(summaryTab);
		summaryCompound.setBag(new DataBag("")); // TODO

		factory.createButtonBar(summaryCompound, "edit,delete,cancel,save,new", new ButtonBarListener(summaryCompound));
		ITableLayout summaryLayout = factory.createTableLayout(summaryCompound);

		ILabel label = factory.createLabel(summaryLayout, "Name");
		IText name = factory.createText(summaryLayout, "name", label);
		// check that this field has a value. Other converters are available
		name.addValidator(ValidatorUtil.MANDATORY_VALIDATOR);
		summaryLayout.newRow();
		label = factory.createLabel(summaryLayout, "Name");
		name = factory.createText(summaryLayout, "name", label);
		summaryLayout.newRow();

		label = factory.createLabel(summaryLayout, "Name");
		name = factory.createText(summaryLayout, "name", label);
		summaryLayout.newRow();
		label = factory.createLabel(summaryLayout, "Name");
		name = factory.createText(summaryLayout, "name", label);
		summaryLayout.newRow();
		label = factory.createLabel(summaryLayout, "Name");
		name = factory.createText(summaryLayout, "name", label);
		summaryLayout.newRow();
		label = factory.createLabel(summaryLayout, "Role");
		factory.createText(summaryLayout, "role", label);

		ICompound detailCompound = factory.createCompound(detailTab);
		detailCompound.setBag(new DataBag("")); // TODO

		factory.createButtonBar(detailCompound, "edit,cancel,save", new ButtonBarListener(detailCompound));

		summaryLayout = factory.createTableLayout(detailCompound);

		label = factory.createLabel(summaryLayout, "Description");
		factory.createText(summaryLayout, "description", label);
		summaryLayout.newRow();

		label = factory.createLabel(summaryLayout, "Detail");
		factory.createText(summaryLayout, "detail", label);

		factory.createButton(viewConnector, null, "Open Summary Tab", null, new IActionListener() {
			public void onAction(ClientEvent event) {
				tabstrip.selectTab(summaryTab);
			}
		});

		factory.createButton(viewConnector, null, "Show/Hide Summary Tab", null, new IActionListener() {
			boolean isVisible = true;

			public void onAction(ClientEvent event) {
				if (isVisible)
					tabstrip.hideTab(summaryTab);
				else
					tabstrip.showTab(summaryTab);
				isVisible = !isVisible;
			}
		});
		factory.createButton(viewConnector, null, "Show/Hide Detail Tab", null, new IActionListener() {
			boolean isVisible = true;

			public void onAction(ClientEvent event) {
				if (isVisible)
					tabstrip.hideTab(detailTab);
				else
					tabstrip.showTab(detailTab);
				isVisible = !isVisible;
			}
		});
		factory.createButton(viewConnector, null, "Show/Hide Last Tab", null, new IActionListener() {
			boolean isVisible = true;

			public void onAction(ClientEvent event) {
				if (isVisible)
					tabstrip.hideTab(lastTab);
				else
					tabstrip.showTab(lastTab);
				isVisible = !isVisible;
			}
		});
		factory.createButton(viewConnector, null, "Enable/Disable Summary Tab", null, new IActionListener() {
			boolean isEnabled = true;

			public void onAction(ClientEvent event) {
				if (isEnabled)
					tabstrip.setDisabled(summaryTab);
				else
					tabstrip.setEnabled(summaryTab);
				isEnabled = !isEnabled;
			}
		});

	}

	class ButtonBarListener extends AbstractButtonBarListener {

		public ButtonBarListener(ICompound compound) {
			super(compound);
		}

		@Override
		public Object newDelegate() {
			// TODO : return a fresh instance of your delegate to be used while
			// processing onNew method
			return null;
		}

		@Override
		public boolean refresh(Object delegate) {
			// TODO : refresh persistent object if needed to minimize concurrent
			// modification exceptions in case of optimistic locking
			return true;
		}

		@Override
		public int persist() {
			// commit ...
			// TODO : return the result from you persister, catch exceptions
			// here
			return SAVE_OK;
		}

		@Override
		public boolean delete(Object delegate) {
			// TODO : mark delegate deleted and commit
			return true;
		}
	}

	class TabstripActionListener implements ITabListener {

		public TabstripActionListener() {
		}

		public boolean onTabChange(ITab old, ITab selected, ClientEvent event) {
			if (selected == summaryTab) {
				// load summary data
			}
			if (selected == detailTab) {
				// load detail data
			}

			return true; // loading OK;
			// TODO : return false if something went wrong while loading in
			// order to stay on the old tab
		}
	}

}
