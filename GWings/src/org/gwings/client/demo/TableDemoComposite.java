package org.gwings.client.demo;

import org.gwings.client.ui.GPlotable;
import org.gwings.client.ui.GTable;
import org.gwings.client.ui.GTableModel;
import org.gwings.client.ui.impl.DefaultGTable;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Copyright 2007 Marcelo Emanoel B. Diniz <marceloemanoel AT gmail.com>
 * 
 * @author Marcelo Emanoel
 * @since 04/02/2007
 */
public class TableDemoComposite extends Composite {
	private GTable table;

	private class TestPlotable implements GPlotable {
		private String string;
		private Boolean bool;

		public TestPlotable(String st, Boolean b) {

			this.string = st;
			this.bool = b;
		}

		public Object[] plot() {

			return new Object[] { string, bool };
		}

	}

	public TableDemoComposite() {

		table = new DefaultGTable();

		GTableModel model = table.getTableModel();

		model.addColumn("String");
		model.addColumn("Boolean value");

		TestPlotable plotable = new TestPlotable("true", Boolean.TRUE);
		TestPlotable plotable2 = new TestPlotable("false", Boolean.FALSE);

		model.appendLine(plotable);
		model.appendLine(plotable2);

		VerticalPanel vPanel = new VerticalPanel();
		vPanel.add(table.getTableView());

		initWidget(vPanel);
	}
}
