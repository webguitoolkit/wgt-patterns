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
package org.webguitoolkit.patterns.views.patterns;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.event.IActionListener;
import org.webguitoolkit.ui.controls.event.ServerEvent;
import org.webguitoolkit.ui.controls.form.IRadio;
import org.webguitoolkit.ui.controls.layout.ITableLayout;

public class FilePickerView extends AbstractView {
	public static final int SAVE = 0;
	private String root;

	public FilePickerView(WebGuiFactory factory, ICanvas viewConnector, String root) {
		super(factory, viewConnector);
		this.root = root;
	}

	@Override
	protected void createControls( WebGuiFactory factory, ICanvas viewConnector ) {
		// button to show the source code of this class
		factory.createButton( viewConnector, "./images/iconText.gif", "Source Code","Source Code", new SourceView( factory, viewConnector, this.getClass().getName() ) );

		// take root and find files below
		File f = new File(root);
		if (f.isDirectory()) {
			ITableLayout layout = factory.createTableLayout(viewConnector);
			File[] listFiles = f.listFiles();
			final List<IRadio> radios = new ArrayList<IRadio>();
			for (int i = 0; i < listFiles.length; i++) {
				File file = listFiles[i];
				IRadio newRadio = factory.createRadio(layout, "Files", "");
				newRadio.setLabel(file.getName());
				radios.add(newRadio);
				layout.newRow();
			}
			
			factory.createButton(layout, null, "Save", "Click to save selection", new IActionListener() {

				public void onAction(ClientEvent event) {
					for (Iterator<IRadio> it = radios.iterator(); it.hasNext();) {
						IRadio r = it.next();
						if (r.isSelected()) {
							ServerEvent serverEvent = new ServerEvent(SAVE);
							serverEvent.putParameter("name", r.getLabel());
							fireServerEvent(serverEvent);
						}
					}
				}
			});

			// show list of files
			// pass selected file via event
		} else {
			// error message
		}

	}

}
