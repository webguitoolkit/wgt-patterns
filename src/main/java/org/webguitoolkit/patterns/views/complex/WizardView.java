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
package org.webguitoolkit.patterns.views.complex;

import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.dialog.IWizard;
import org.webguitoolkit.ui.controls.tab.ITab;

public class WizardView extends AbstractView {

	public WizardView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory,viewConnector);
	}

	@Override
	protected void createControls( WebGuiFactory factory, ICanvas viewConnector ) {
		// button to show the source code of this class
		factory.createButton( viewConnector, "./images/iconText.gif", "Source Code","Source Code", new SourceView( factory, viewConnector, this.getClass().getName() ) );

		IWizard wizard = factory.createWizard( viewConnector );
		
		ITab tab = factory.createTab(wizard, "First Tab", null);
		factory.createLabel(tab, "First Tab");
		tab = factory.createTab(wizard, "Second Tab", null);
		factory.createLabel(tab, "Second Tab");
		tab = factory.createTab(wizard, "Third Tab", null);
		factory.createLabel(tab, "Third Tab");
	}

}
