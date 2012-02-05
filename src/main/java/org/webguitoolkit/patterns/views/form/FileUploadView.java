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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.event.IActionListener;
import org.webguitoolkit.ui.controls.form.ICompound;
import org.webguitoolkit.ui.controls.form.ILabel;
import org.webguitoolkit.ui.controls.form.fileupload.AbstractUploadFileHandler;
import org.webguitoolkit.ui.controls.form.fileupload.IFileUpload;
import org.webguitoolkit.ui.controls.layout.ITableLayout;

public class FileUploadView extends AbstractView {

	private ILabel info;
	private IFileUpload upload;
	private ILabel nameLabel;
	private ILabel sizeLabel;

	public FileUploadView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		// button to show the source code of this class
		factory.createButton(viewConnector, "./images/iconText.gif", "Source Code", "Source Code", new SourceView(factory, viewConnector,
				this.getClass().getName()));

		ITableLayout layout = factory.createTableLayout(viewConnector);

		// SIMPLE TEXT
		ILabel label = factory.createLabel(layout, "File Upload");
		upload = factory.createFileUpload(layout, "fileupload", new UploadHandler(), new ActionListener());

		upload.changeMode(ICompound.MODE_NEW);

		layout.newRow();

		label = factory.createLabel(layout, "Name");
		nameLabel = factory.createLabel(layout, "");
		layout.newRow();
		label = factory.createLabel(layout, "Size");
		sizeLabel = factory.createLabel(layout, "");

	}

	public class UploadHandler extends AbstractUploadFileHandler {

		long size = 0;
		String name = null;

		@Override
		public List getEventParameters() {
			List returnList = new ArrayList();
			returnList.add(name);
			returnList.add(Long.toString(size));
			return returnList;
		}

		@Override
		public void processUpload() throws Exception {
			List<FileItem> fileItems2 = getFileItems();
			for (FileItem fi : fileItems2) {
				size = fi.getSize();
				name = fi.getName();
			}

		}
	}

	public class ActionListener implements IActionListener {

		public void onAction(ClientEvent event) {
			String name = event.getParameter(0);
			String size = event.getParameter(1);

			upload.changeMode(ICompound.MODE_NEW);

			nameLabel.setText(name);
			sizeLabel.setText(size + " Byte");
		}

	}
}
