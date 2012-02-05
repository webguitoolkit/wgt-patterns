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
package org.webguitoolkit.patterns.file;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.Canvas;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.form.fileupload.AbstractUploadFileHandler;
/**
 * Show how to upload a file.
 * 
 * @author peter
 *
 */
public class FileUploadView extends AbstractView {

	public FileUploadView(WebGuiFactory factory, Canvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls( WebGuiFactory factory, ICanvas viewConnector ) {

		factory.createFileUpload(viewConnector, "filename", new FileHandler(), null  );
	
	}

	public class FileHandler extends AbstractUploadFileHandler {

		@Override
		public List getEventParameters() {
			return getFileNames();
		}

		@Override
		public void processUpload() throws Exception {
			List<FileItem> fileItems = getFileItems();
			getFileNames();
			for (Iterator<FileItem> it = fileItems.iterator(); it.hasNext();) {
				FileItem fi = it.next();
				System.out.println("FOUND " + fi.getName());
				// fi.getInputStream()
			}
		}

	}
}
