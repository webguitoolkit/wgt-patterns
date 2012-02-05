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
package org.webguitoolkit.patterns.prototype;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.webguitoolkit.ui.base.DataBag;
import org.webguitoolkit.ui.base.IDataBag;
import org.webguitoolkit.ui.base.WebGuiFactory;
import org.webguitoolkit.ui.controls.IComposite;
import org.webguitoolkit.ui.controls.form.Compound;
import org.webguitoolkit.ui.controls.form.Label;
import org.webguitoolkit.ui.controls.layout.TableLayout;
import org.webguitoolkit.ui.controls.table.Table;
import org.webguitoolkit.ui.controls.table.TableColumn;

/**
 * 
 * @author peter
 * 
 */
public class Prototyper implements Serializable {
	private WebGuiFactory factory;

	public Prototyper(WebGuiFactory factory) {
		this.factory = factory;
	}

	/**
	 * Generate a Table with columns
	 * 
	 * @param parent the composite where the table should be integrated.
	 * @param columns descriptors of the column in the format <name>:<type>?[Y|N]
	 * 
	 */
	public Table genTable(IComposite parent, String[] columns, int rows) {
		Table table = factory.newTable((IComposite)parent, "", rows);

		for (int i = 0; i < columns.length; i++) {
			String col = columns[i];
			StringTokenizer st = new StringTokenizer(col, ":?");
			assert st.countTokens() == 3 : "missing token " + col;
			String name = st.nextToken();
			String type = st.nextToken();
			boolean visible = st.nextToken().equalsIgnoreCase("Y");
			TableColumn column = factory.newTableColumn(table, name, name, true);
			// column.setVisible(visible);
		}

		return table;
	}

	public Compound genForm(IComposite parent, String[][] cell) {
		Compound comp = factory.newCompound((IComposite)parent);
		TableLayout layout = factory.newTableLayout(comp);

		for (int l = 0; l < cell.length; l++) {
			for (int c = 0; c < cell.length; c++) {
				String col = cell[l][c];
				StringTokenizer st = new StringTokenizer(col, ":");
				assert st.countTokens() == 3 : "missing token " + col;
				String name = st.nextToken();
				String type = st.nextToken();

				Label label = factory.newLabel(layout, name);
				factory.newText(layout, name.toLowerCase(), label);
			}
			layout.newLine();
		}

		return comp;
	}

	public IDataBag genBag(String[][] cell) {
		IDataBag result = new DataBag(null);
		for (int l = 0; l < cell.length; l++) {
			for (int c = 0; c < cell.length; c++) {
				String col = cell[l][c];
				StringTokenizer st = new StringTokenizer(col, ":");
				assert st.countTokens() == 3 : "missing token " + col;
				String name = st.nextToken();
				String type = st.nextToken();
				result.addProperty(name.toLowerCase(), MockData.get(col));
			}
		}
		return result;
	}

	public List<IDataBag> genMockdata(String[] field, int items) {
		List<IDataBag> result = new ArrayList<IDataBag>();
		for (; items > 0; items--) {
			IDataBag bag = new DataBag(null);
			for (int i = 0; i < field.length; i++) {
				StringTokenizer st = new StringTokenizer(field[i], ":?");
				String name = st.nextToken();
				bag.addProperty(name, MockData.get(field[i]));
			}
			result.add(bag);
		}
		return result;
	}
}
