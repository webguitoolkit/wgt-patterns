package org.webguitoolkit.patterns.views.form;

import org.webguitoolkit.patterns.views.SourceView;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.form.AbstractButtonBarListener;
import org.webguitoolkit.ui.controls.form.ButtonBar;
import org.webguitoolkit.ui.controls.form.IButtonBar;
import org.webguitoolkit.ui.controls.form.ICompound;
import org.webguitoolkit.ui.controls.form.ILabel;
import org.webguitoolkit.ui.controls.layout.ITableLayout;

public class ButtonBarView extends AbstractView {
	private static final long serialVersionUID = 1L;

	public ButtonBarView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		// button to show the source code of this class
		factory.createButton( viewConnector, "./images/iconText.gif", "Source Code","Source Code", new SourceView( factory, viewConnector, this.getClass().getName() ) );

		ICanvas displayArea = factory.createCanvas( viewConnector );
		displayArea.getStyle().add("border", "2px solid grey");
		displayArea.getStyle().add("width", "400px");
		displayArea.getStyle().add("height", "200px");

		ICompound comp = factory.createCompound( displayArea );
		
		// only image
		factory.createButtonBar( comp, 
				new String[]{ ButtonBar.BUTTON_CANCEL,ButtonBar.BUTTON_DELETE,ButtonBar.BUTTON_EDIT,ButtonBar.BUTTON_NEW,ButtonBar.BUTTON_SAVE }, 
				IButtonBar.BUTTON_DISPLAY_MODE_IMAGE, 
				new ButtonBarListener( comp ) );
		ITableLayout layout = factory.createTableLayout( comp );
		
		ILabel label = factory.createLabel( layout, "name@Name");
		factory.createText(layout, "name", label );

		// image and text
		factory.createButtonBar( comp, 
				new String[]{ ButtonBar.BUTTON_CANCEL,ButtonBar.BUTTON_DELETE,ButtonBar.BUTTON_EDIT,ButtonBar.BUTTON_NEW,ButtonBar.BUTTON_SAVE }, 
				IButtonBar.BUTTON_DISPLAY_MODE_IMAGE_TEXT, 
				new ButtonBarListener( comp ) );
		layout = factory.createTableLayout( comp );
		
		label = factory.createLabel( layout, "name@Name");
		factory.createText(layout, "name", label );

		// only text
		factory.createButtonBar( comp, 
				new String[]{ ButtonBar.BUTTON_CANCEL,ButtonBar.BUTTON_DELETE,ButtonBar.BUTTON_EDIT,ButtonBar.BUTTON_NEW,ButtonBar.BUTTON_SAVE }, 
				IButtonBar.BUTTON_DISPLAY_MODE_TEXT, 
				new ButtonBarListener( comp ) );
		layout = factory.createTableLayout( comp );
		
		label = factory.createLabel( layout, "name@Name");
		factory.createText(layout, "name", label );
	}

	public class ButtonBarListener extends AbstractButtonBarListener{

		public ButtonBarListener(ICompound compound) {
			super(compound);
		}

		@Override
		public boolean delete(Object delegate) {
			return false;
		}

		@Override
		public Object newDelegate() {
			return null;
		}

		@Override
		public int persist() {
			return 0;
		}

		@Override
		public boolean refresh(Object delegate) {
			return false;
		}
	}
}
