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

import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.BaseControl;
import org.webguitoolkit.ui.controls.container.Canvas;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.container.ICanvasWindowListener;
import org.webguitoolkit.ui.controls.container.IHtmlElement;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.form.ILabel;
import org.webguitoolkit.ui.controls.layout.ITableLayout;
@Deprecated
public class SplitScreenView extends AbstractView {

	private ICanvas top, bottom;
	private int availableSpace = 0;

	public SplitScreenView(WebGuiFactory factory, ICanvas viewConnector,
			int spaceInPixel) {
		super(factory, viewConnector);
		availableSpace = spaceInPixel;
	}

	@Override
	protected void createControls( WebGuiFactory factory, ICanvas viewConnector ) {
		// button to show the source code of this class
		factory.createButton( viewConnector, "./images/iconText.gif", "Source Code","Source Code", new SourceView( factory, viewConnector, this.getClass().getName() ) );

		
		
		top = factory.createCanvas(viewConnector);

		SplitScreenListener listener = new SplitScreenListener();

		// set displayMode to trayable
		((Canvas)top).setDisplayMode(Canvas.DISPLAY_MODE_INLINE_TRAYABLE);
		// set initial tray status to resized
		((Canvas)top).setTrayStatus(Canvas.TRAY_STATUS_MAXIMIZED);
		
		top.getStyle().addHeight(getResizeHeight());
		//Style is set in the wgtPatters.css file. .splitScreenStyle{width:80%;overflow:hidden;}
		top.addCssClass("splitScreenStyle");

		top.setTitle("Top Canvas");
		// show resize icon
		((Canvas)top).setTrayResizable(true);
		// set Listener
		top.setWindowActionListener(listener);

		// set content
		ITableLayout topcontent = factory.createTableLayout(top);
		ILabel topheader = factory.createLabel(topcontent, "Top Canvas Content");
		//Style is set in the wgtPatters.css file. .topLabelStyle{font-size:2.0em;font-weight:bold;}
		topheader.addCssClass("topLabelStyle");
		topcontent.getCurrentCell().setColSpan(2);
		topcontent.newRow();
		factory
				.createLabel(
						topcontent,
						"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Vestibulum feugiat nunc ac arcu. Fusce ullamcorper mattis risus. In semper posuere diam. Etiam vulputate. Vivamus congue adipiscing magna. Fusce luctus ante quis massa. Morbi ornare. Nam volutpat quam sit amet lorem. Mauris convallis vestibulum ipsum. Vestibulum vel neque. Ut ac pede in eros tristique bibendum. Suspendisse potenti. Suspendisse mi. Ut scelerisque varius felis.");
		factory
				.createLabel(
						topcontent,
						"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Vestibulum feugiat nunc ac arcu. Fusce ullamcorper mattis risus. In semper posuere diam. Etiam vulputate. Vivamus congue adipiscing magna. Fusce luctus ante quis massa. Morbi ornare. Nam volutpat quam sit amet lorem. Mauris convallis vestibulum ipsum. Vestibulum vel neque. Ut ac pede in eros tristique bibendum. Suspendisse potenti. Suspendisse mi. Ut scelerisque varius felis.");
		topcontent.newRow();
		factory
				.createLabel(
						topcontent,
						"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Vestibulum feugiat nunc ac arcu. Fusce ullamcorper mattis risus. In semper posuere diam. Etiam vulputate. Vivamus congue adipiscing magna. Fusce luctus ante quis massa. Morbi ornare. Nam volutpat quam sit amet lorem. Mauris convallis vestibulum ipsum. Vestibulum vel neque. Ut ac pede in eros tristique bibendum. Suspendisse potenti. Suspendisse mi. Ut scelerisque varius felis.");
		factory
				.createLabel(
						topcontent,
						"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Vestibulum feugiat nunc ac arcu. Fusce ullamcorper mattis risus. In semper posuere diam. Etiam vulputate. Vivamus congue adipiscing magna. Fusce luctus ante quis massa. Morbi ornare. Nam volutpat quam sit amet lorem. Mauris convallis vestibulum ipsum. Vestibulum vel neque. Ut ac pede in eros tristique bibendum. Suspendisse potenti. Suspendisse mi. Ut scelerisque varius felis.");
		topcontent.newRow();
		factory
				.createLabel(
						topcontent,
						"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Vestibulum feugiat nunc ac arcu. Fusce ullamcorper mattis risus. In semper posuere diam. Etiam vulputate. Vivamus congue adipiscing magna. Fusce luctus ante quis massa. Morbi ornare. Nam volutpat quam sit amet lorem. Mauris convallis vestibulum ipsum. Vestibulum vel neque. Ut ac pede in eros tristique bibendum. Suspendisse potenti. Suspendisse mi. Ut scelerisque varius felis.");
		factory
				.createLabel(
						topcontent,
						"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Vestibulum feugiat nunc ac arcu. Fusce ullamcorper mattis risus. In semper posuere diam. Etiam vulputate. Vivamus congue adipiscing magna. Fusce luctus ante quis massa. Morbi ornare. Nam volutpat quam sit amet lorem. Mauris convallis vestibulum ipsum. Vestibulum vel neque. Ut ac pede in eros tristique bibendum. Suspendisse potenti. Suspendisse mi. Ut scelerisque varius felis.");
		topcontent.newRow();
		factory
				.createLabel(
						topcontent,
						"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Vestibulum feugiat nunc ac arcu. Fusce ullamcorper mattis risus. In semper posuere diam. Etiam vulputate. Vivamus congue adipiscing magna. Fusce luctus ante quis massa. Morbi ornare. Nam volutpat quam sit amet lorem. Mauris convallis vestibulum ipsum. Vestibulum vel neque. Ut ac pede in eros tristique bibendum. Suspendisse potenti. Suspendisse mi. Ut scelerisque varius felis.");
		factory
				.createLabel(
						topcontent,
						"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Vestibulum feugiat nunc ac arcu. Fusce ullamcorper mattis risus. In semper posuere diam. Etiam vulputate. Vivamus congue adipiscing magna. Fusce luctus ante quis massa. Morbi ornare. Nam volutpat quam sit amet lorem. Mauris convallis vestibulum ipsum. Vestibulum vel neque. Ut ac pede in eros tristique bibendum. Suspendisse potenti. Suspendisse mi. Ut scelerisque varius felis.");
		topcontent.newRow();
		factory
				.createLabel(
						topcontent,
						"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Vestibulum feugiat nunc ac arcu. Fusce ullamcorper mattis risus. In semper posuere diam. Etiam vulputate. Vivamus congue adipiscing magna. Fusce luctus ante quis massa. Morbi ornare. Nam volutpat quam sit amet lorem. Mauris convallis vestibulum ipsum. Vestibulum vel neque. Ut ac pede in eros tristique bibendum. Suspendisse potenti. Suspendisse mi. Ut scelerisque varius felis.");
		factory
				.createLabel(
						topcontent,
						"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Vestibulum feugiat nunc ac arcu. Fusce ullamcorper mattis risus. In semper posuere diam. Etiam vulputate. Vivamus congue adipiscing magna. Fusce luctus ante quis massa. Morbi ornare. Nam volutpat quam sit amet lorem. Mauris convallis vestibulum ipsum. Vestibulum vel neque. Ut ac pede in eros tristique bibendum. Suspendisse potenti. Suspendisse mi. Ut scelerisque varius felis.");


		bottom = factory.createCanvas(viewConnector);
		bottom.setDisplayMode(Canvas.DISPLAY_MODE_INLINE_TRAYABLE);
		((Canvas)bottom).setTrayStatus(Canvas.TRAY_STATUS_MINIMIZED);
		//Style is set in the wgtPatters.css file. .bottomStyle{width:80%;overflow:hidden;}
		bottom.getStyle().addHeight(getResizeHeight());
		bottom.addCssClass("bottomStyle");

		((Canvas)bottom).setTrayResizable(true);
		bottom.setWindowActionListener(listener);
		bottom.setTitle("Bottom Canvas");

		ITableLayout bottomcontent = factory.createTableLayout(bottom);
		ILabel bottomheader = factory.createLabel(bottomcontent,"Bottom Canvas Content");
		//Style is set in the wgtPatters.css file. .bottomHeaderStyle{font-size:2.0em;font-weight:bold;}
		bottomheader.addCssClass("bottomHeaderStyle");
		bottomcontent.newRow();
		factory.createLabel(bottomcontent,
						"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Vestibulum feugiat nunc ac arcu. Fusce ullamcorper mattis risus. In semper posuere diam. Etiam vulputate. Vivamus congue adipiscing magna. Fusce luctus ante quis massa. Morbi ornare. Nam volutpat quam sit amet lorem. Mauris convallis vestibulum ipsum. Vestibulum vel neque. Ut ac pede in eros tristique bibendum. Suspendisse potenti. Suspendisse mi. Ut scelerisque varius felis.");
		bottomcontent.newRow();
		// create element add attach to layout
		IHtmlElement iframe = factory.createHtmlElement(bottomcontent);
		// specify element tag
		iframe.setTagName("iframe");
		// set element attributes
		iframe.setAttribute("src", "http://wetterstationen.meteomedia.de/messnetz/forecast/098020.html");
		iframe.setAttribute("width","95%"); 
		iframe.setAttribute("height","400");
		iframe.setAttribute("name","weather_in_a_box");
	}

	private String getMaxHeight() {
		return String.valueOf(availableSpace - 40) + "px";
	}

	private String getResizeHeight() {
		return String.valueOf((availableSpace - 40) / 2) + "px";
	}
	
	class SplitScreenListener implements ICanvasWindowListener {

		public void onClose(ClientEvent event) {
			// not available for trayable canvas
		}

		public void onMinimize(ClientEvent event) {
			// minimise the clicked canvas
			((Canvas) event.getSource())
					.setTrayStatus(Canvas.TRAY_STATUS_MINIMIZED);
			((Canvas) event.getSource()).getStyle().removeAttributeByType(
					"height");
			((Canvas) event.getSource()).redraw();

			// the other canvas follows
			if (event.getSource() == top) {
				((Canvas)bottom).setTrayStatus(Canvas.TRAY_STATUS_MAXIMIZED);
				bottom.getStyle().addHeight(getMaxHeight());
				((BaseControl)bottom).redraw();
			} else {
				((Canvas)top).setTrayStatus(Canvas.TRAY_STATUS_MAXIMIZED);
				top.getStyle().addHeight(getMaxHeight());
				((BaseControl)top).redraw();
			}
		}

		public void onMaximize(ClientEvent event) {
			// maximise the clicked canvas
			((Canvas) event.getSource())
					.setTrayStatus(Canvas.TRAY_STATUS_MAXIMIZED);
			((Canvas) event.getSource()).getStyle().addHeight(getMaxHeight());
			((Canvas) event.getSource()).redraw();

			// the other canvas follows
			if (event.getSource() == top) {
				((Canvas)bottom).setTrayStatus(Canvas.TRAY_STATUS_MINIMIZED);
				bottom.getStyle().removeAttributeByType("height");
				((BaseControl)bottom).redraw();
			} else {
				((Canvas)top).setTrayStatus(Canvas.TRAY_STATUS_MINIMIZED);
				top.getStyle().removeAttributeByType("height");
				((BaseControl)top).redraw();
			}
		}

	}

}
