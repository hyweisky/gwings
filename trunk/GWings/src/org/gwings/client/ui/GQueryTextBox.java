/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License. Copyright
 * 2007 Marcelo Emanoel B. Diniz <marceloemanoel AT gmail.com>
 */
package org.gwings.client.ui;

import java.util.Collection;
import java.util.Iterator;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License. Copyright
 * 2007 Marcelo Emanoel B. Diniz <marceloemanoel AT gmail.com>
 * 
 * @author Marcelo Emanoel
 * @since 03/03/2007
 */
public class GQueryTextBox extends TextBox {
	private PopupPanel queryPopup;
	private FlexTable tableResults;
	private int maxResults;

	public GQueryTextBox() {
		super();
		initialize();
		setupUI();
		setupListeners();

	}

	private void initialize() {
		queryPopup = new PopupPanel();
		tableResults = new FlexTable();
		queryPopup.setStyleName("popup");
		tableResults.setStyleName("results");
		setMaxResults(10);

	}

	private void setupUI() {
		DeferredCommand.add(new Command() {
			public void execute() {
				queryPopup.setPopupPosition(getAbsoluteLeft(), getAbsoluteTop()
						+ getOffsetHeight() + 1);
			}
		});
		queryPopup.setWidth(this.getOffsetWidth() + "px");
		queryPopup.add(tableResults);
	}

	private void setupListeners() {
		this.addKeyboardListener(new KeyboardListenerAdapter() {
			public void onKeyUp(Widget sender, char keyCode, int modifiers) {
				if (getText().equals("")) {
					hideResults();
				}
			}

			public void onKeyDown(Widget sender, char keyCode, int modifiers) {
				clearTableResults();
			}
		});
	}

	private void hideResults() {
		queryPopup.hide();
		clearTableResults();
	}

	/**
	 * @return the maxResults
	 */
	public int getMaxResults() {
		return maxResults;
	}

	/**
	 * @param maxResults
	 *            the maxResults to set
	 * @throws Exception
	 */
	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	private void clearTableResults() {
		for (int i = 0; i < tableResults.getRowCount(); i++) {
			tableResults.removeRow(i);
		}
	}

	public void response(Collection results) {
		if (results != null) {
			queryPopup.show();
			int row = 0;
			for (Iterator i = results.iterator(); i.hasNext()
					&& row < getMaxResults(); row++) {
				tableResults.setText(row, 0, (String) i.next());
				String style = (row % 2 == 0 ? "odd" : "even");
				tableResults.getRowFormatter().setStyleName(row, style);

			}
		}
	}

}
