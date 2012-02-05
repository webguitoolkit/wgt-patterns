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
import java.util.ArrayList;
import java.util.List;

import org.webguitoolkit.ui.base.DataBag;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.AbstractView;
import org.webguitoolkit.ui.controls.container.ICanvas;
import org.webguitoolkit.ui.controls.event.ClientEvent;
import org.webguitoolkit.ui.controls.event.IActionListener;
import org.webguitoolkit.ui.controls.form.ICheckBox;
import org.webguitoolkit.ui.controls.form.ICompound;
import org.webguitoolkit.ui.controls.form.IRadio;
import org.webguitoolkit.ui.controls.form.OptionGroupController;
import org.webguitoolkit.ui.controls.layout.SequentialTableLayout;
import org.webguitoolkit.ui.controls.util.conversion.AssociationConverter;
import org.webguitoolkit.ui.controls.util.conversion.CollectionConverter;

/**
 * Example for the use of OptionBroupControllers
 */
public class OptionControlGroupView extends AbstractView {

	private Question q;
	private ICompound comp;

	public OptionControlGroupView(WebGuiFactory factory, ICanvas viewConnector) {
		super(factory, viewConnector);
	}

	@Override
	protected void createControls(WebGuiFactory factory, ICanvas viewConnector) {
		viewConnector.setLayout(new SequentialTableLayout());

		List<String> answers2 = new ArrayList<String>();
		answers2.add("fast");
		answers2.add("powerful");

		List<Answer> answers3 = new ArrayList<Answer>();
		answers3.add(new Answer("web", "Web"));
		answers3.add(new Answer("friend", "Friend"));
		answers3.add(new Answer("other", "Other"));

		List<Answer> answers4 = new ArrayList<Answer>();
		answers4.add(new Answer("peter", "Peter"));
		answers4.add(new Answer("paul", "Paul"));
		answers4.add(new Answer("mary", "Mary"));

		// create the 'business object'
		q = new Question("Do You like WGT?", "yes", "What things do you like on WGT?", answers2, "Where did You here about WGT?",
				answers3.get(2), "Recocment WGT to:", answers4);

		// create the compound
		comp = factory.createCompound(viewConnector);
		comp.setLayout(new SequentialTableLayout());
		comp.setBag(new DataBag(q));

		// ######### 1. Question, simple single value ##########

		OptionGroupController a1Controller = new OptionGroupController("answer1");

		factory.createLabel(comp, q.getQuestion1());

		// create Radio
		IRadio radio = factory.createRadio(comp, null, "Yes");
		radio.setProperty("yes");
		a1Controller.addOptionControl(radio);

		// create Radio
		radio = factory.createRadio(comp, null, "No");
		radio.setProperty("no");
		radio.setLayoutData(SequentialTableLayout.getLastInRow());
		a1Controller.addOptionControl(radio);

		// ######### 2. Question, simple collection of values ##########

		OptionGroupController a2Controller = new OptionGroupController("answer2");
		a2Controller.setConverter(new CollectionConverter());

		factory.createLabel(comp, q.getQuestion2());

		// create Radio
		ICheckBox check = factory.createCheckBox(comp, "fast");
		check.setLabelKey("fast");
		a2Controller.addOptionControl(check);

		// create Radio
		check = factory.createCheckBox(comp, "powerful");
		check.setLabelKey("powerful");
		check.setLayoutData(SequentialTableLayout.getLastInRow());
		a2Controller.addOptionControl(check);

		// ######### 3. Question, associated single value ##########

		OptionGroupController a3Controller = new OptionGroupController("answer3");
		a3Controller.setConverter(new AssociationConverter(answers3, "id"));

		factory.createLabel(comp, q.getQuestion3());

		for (Answer answer : answers3) {
			radio = factory.createRadio(comp, null, answer.getText());
			radio.setProperty(answer.getId());
			a3Controller.addOptionControl(radio);
		}
		radio.setLayoutData(SequentialTableLayout.getLastInRow());

		// ######### 4. Question, associated collection of values ##########

		OptionGroupController a4Controller = new OptionGroupController("answer4");
		a4Controller.setConverter(new CollectionConverter(new AssociationConverter(answers4, "id"), ArrayList.class));

		factory.createLabel(comp, q.getQuestion4());

		for (Answer answer : answers4) {
			check = factory.createCheckBox(comp, answer.getId());
			check.setLabelKey(answer.getText());
			a4Controller.addOptionControl(check);
		}
		check.setLayoutData(SequentialTableLayout.getLastInRow());

		// button to evaluate the result
		factory.createButton(comp, null, "Save", "save", new CompoundListener());

		comp.load();

	}

	/**
	 * The action listener
	 */
	public class CompoundListener implements IActionListener {
		public void onAction(ClientEvent event) {
			// save the values to the data bag
			comp.save();
			// save the values of the data bag to the business object
			comp.getBag().save();

			// evaluate the values of the business object and generate a message
			List<String> a2 = q.getAnswer2();
			Answer a3 = q.getAnswer3();
			List<Answer> a4 = q.getAnswer4();
			String answer4 = "";
			for (Answer a : a4) {
				answer4 += (a.getText() + ",");
			}
			String answer1 = q.getAnswer1();
			String answer2 = (String)new CollectionConverter().convert(String.class, a2);
			StringBuffer msg = new StringBuffer();
			if ("no".equals(answer1)) {
				msg.append("'" + q.getAnswer1() + "'! You just testing the radio, aren't You?");
			}
			else {
				msg.append("1. Question: " + q.getAnswer1());
			}
			msg.append("<br>2. Question: " + answer2);
			msg.append("<br>3. Question: " + a3.getText());
			msg.append("<br>4. Question: " + answer4);

			getPage().sendInfo(msg.toString());
		}
	}

	/**
	 * The dummy business object
	 */
	public class Question implements Serializable {
		private String question1;
		private String question2;
		private String question3;
		private String question4;
		private String answer1;
		private List<String> answer2;
		private Answer answer3;
		private List<Answer> answer4;

		public Question() {

		}

		public Question(String question1, String answer1, String question2, List<String> answer2, String question3, Answer answer3,
				String question4, List<Answer> answer4) {
			super();
			this.question1 = question1;
			this.answer1 = answer1;
			this.question2 = question2;
			this.answer2 = answer2;
			this.question3 = question3;
			this.answer3 = answer3;
			this.question4 = question4;
			this.answer4 = answer4;
		}

		/**
		 * @param question1 the question1 to set
		 */
		public void setQuestion1(String question1) {
			this.question1 = question1;
		}

		/**
		 * @return the header
		 */
		public String getQuestion1() {
			return question1;
		}

		/**
		 * @param answer1 the answer1 to set
		 */
		public void setAnswer1(String answer1) {
			this.answer1 = answer1;
		}

		/**
		 * @return the answer
		 */
		public String getAnswer1() {
			return answer1;
		}

		/**
		 * @param question2 the question2 to set
		 */
		public void setQuestion2(String question2) {
			this.question2 = question2;
		}

		/**
		 * @return the question2
		 */
		public String getQuestion2() {
			return question2;
		}

		/**
		 * @param answer2 the answer2 to set
		 */
		public void setAnswer2(List<String> answer2) {
			this.answer2 = answer2;
		}

		/**
		 * @return the answer2
		 */
		public List<String> getAnswer2() {
			return answer2;
		}

		/**
		 * @param question3 the question3 to set
		 */
		public void setQuestion3(String question3) {
			this.question3 = question3;
		}

		/**
		 * @return the question3
		 */
		public String getQuestion3() {
			return question3;
		}

		/**
		 * @param question4 the question4 to set
		 */
		public void setQuestion4(String question4) {
			this.question4 = question4;
		}

		/**
		 * @return the question4
		 */
		public String getQuestion4() {
			return question4;
		}

		/**
		 * @param answer3 the answer3 to set
		 */
		public void setAnswer3(Answer answer3) {
			this.answer3 = answer3;
		}

		/**
		 * @return the answer3
		 */
		public Answer getAnswer3() {
			return answer3;
		}

		/**
		 * @param answer4 the answer4 to set
		 */
		public void setAnswer4(List<Answer> answer4) {
			this.answer4 = answer4;
		}

		/**
		 * @return the answer4
		 */
		public List<Answer> getAnswer4() {
			return answer4;
		}
	}

	public class Answer implements Serializable {
		private String id, text;

		public Answer(String id, String text) {
			super();
			this.id = id;
			this.text = text;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(String id) {
			this.id = id;
		}

		/**
		 * @return the id
		 */
		public String getId() {
			return id;
		}

		/**
		 * @param text the text to set
		 */
		public void setText(String text) {
			this.text = text;
		}

		/**
		 * @return the text
		 */
		public String getText() {
			return text;
		}
	}
}
