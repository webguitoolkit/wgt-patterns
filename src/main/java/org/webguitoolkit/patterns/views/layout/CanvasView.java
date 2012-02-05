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
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.BaseControl;
import org.webguitoolkit.ui.controls.container.Canvas;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.event.IActionListener;

public class CanvasView extends AbstractView {
	private class CanvasListener implements IActionListener {
		private ICanvas c;

		public CanvasListener(ICanvas canvas) {
			super();
			this.c = canvas;
		}

		public void onAction(ClientEvent event) {
			if (c.getTrayStatus() == Canvas.TRAY_STATUS_MAXIMIZED)
				c.setTrayStatus(Canvas.TRAY_STATUS_MINIMIZED);
			else
				c.setTrayStatus(Canvas.TRAY_STATUS_MAXIMIZED);
			((BaseControl)c).redraw();
		}

	}

	private class CanvasAllListener implements IActionListener {
		private ICanvas c, b;
		boolean expand = false;

		public CanvasAllListener(ICanvas canvas1, ICanvas canvas2, boolean expand) {
			super();
			this.c = canvas1;
			this.b = canvas2;
			this.expand = expand;

		}

		public void onAction(ClientEvent event) {
			if (expand) {
				c.setTrayStatus(Canvas.TRAY_STATUS_MAXIMIZED);
				b.setTrayStatus(Canvas.TRAY_STATUS_MAXIMIZED);				
			}
			else {
				c.setTrayStatus(Canvas.TRAY_STATUS_MINIMIZED);
				b.setTrayStatus(Canvas.TRAY_STATUS_MINIMIZED);				
			}
			((BaseControl)c).redraw();
			((BaseControl)b).redraw();
		}

	}

	public CanvasView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		viewConnector.getStyle().addWidth("80%");

		// button to show the source code of this class
		factory.createButton( viewConnector, "./images/iconText.gif", "Source Code","Source Code", new SourceView( factory, viewConnector, this.getClass().getName() ) );

		// new plain canvas
		ICanvas canvas3 = factory.createCanvas(viewConnector);
		factory.createLabel(canvas3, "Plain canvas: ");
		factory
				.createLabel(
						canvas3,
						"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Vestibulum feugiat nunc ac arcu. Fusce ullamcorper mattis risus. In semper posuere diam. Etiam vulputate. Vivamus congue adipiscing magna. Fusce luctus ante quis massa. Morbi ornare. Nam volutpat quam sit amet lorem. Mauris convallis vestibulum ipsum. Vestibulum vel neque. Ut ac pede in eros tristique bibendum. Suspendisse potenti. Suspendisse mi. Ut scelerisque varius felis.");

		// create new canvas
		ICanvas trayableCollapsed = factory.createCanvas(viewConnector);
		// specify type of canvas, use Canvas-Constants here.
		trayableCollapsed.setDisplayMode(Canvas.DISPLAY_MODE_INLINE_TRAYABLE);
		trayableCollapsed.setTitleKey("any@Trayable canvas collapsed");
		// add demo contend to canvas
		factory
				.createLabel(
						trayableCollapsed,
						"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Vestibulum feugiat nunc ac arcu. Fusce ullamcorper mattis risus. In semper posuere diam. Etiam vulputate. Vivamus congue adipiscing magna. Fusce luctus ante quis massa. Morbi ornare. Nam volutpat quam sit amet lorem. Mauris convallis vestibulum ipsum. Vestibulum vel neque. Ut ac pede in eros tristique bibendum. Suspendisse potenti. Suspendisse mi. Ut scelerisque varius felis.");
		// set initial TrayStatus to collapsed
		trayableCollapsed.setTrayStatus(Canvas.TRAY_STATUS_MINIMIZED);

		// create new canvas
		ICanvas trayableExpanded = factory.createCanvas(viewConnector);
		// specify type of canvas, use Canvas-Constants here.
		trayableExpanded.setDisplayMode(Canvas.DISPLAY_MODE_INLINE_TRAYABLE);
		trayableExpanded.setTitleKey("any@Trayable canvas expanded");
		// add demo contend to canvas
		factory
				.createLabel(
						trayableExpanded,
						"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Vestibulum feugiat nunc ac arcu. Fusce ullamcorper mattis risus. In semper posuere diam. Etiam vulputate. Vivamus congue adipiscing magna. Fusce luctus ante quis massa. Morbi ornare. Nam volutpat quam sit amet lorem. Mauris convallis vestibulum ipsum. Vestibulum vel neque. Ut ac pede in eros tristique bibendum. Suspendisse potenti. Suspendisse mi. Ut scelerisque varius felis.");
		// set initial TrayStatus to expanded
		trayableExpanded.setTrayStatus(Canvas.TRAY_STATUS_MAXIMIZED);

		getFactory().createButton(viewConnector, null, "top toggle", null, new CanvasListener(trayableCollapsed));
		getFactory().createButton(viewConnector, null, "bottom toggle", null, new CanvasListener(trayableExpanded));
		getFactory().createButton(viewConnector, null, "expand all", null, new CanvasAllListener(trayableCollapsed, trayableExpanded, true));
		getFactory().createButton(viewConnector, null, "collapse all", null, new CanvasAllListener(trayableCollapsed, trayableExpanded, false));

	}

}
