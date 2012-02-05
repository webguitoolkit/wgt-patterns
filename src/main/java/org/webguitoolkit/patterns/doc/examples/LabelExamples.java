package org.webguitoolkit.patterns.doc.examples;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.Canvas;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.form.ILabel;

public class LabelExamples extends AbstractView {
	public LabelExamples(WebGuiFactory factory, Canvas viewConnector) {
		super(factory, viewConnector);
		
	}
	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		ILabel label = factory.createLabel(viewConnector, "Name ");
		factory.createText(viewConnector,"name",label);
	}
}
