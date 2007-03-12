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
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

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
 * Copyright 2007 Marcelo Emanoel B. Diniz <marceloemanoel AT gmail.com>,
 * Luciano Broussal <luciano.broussal AT gmail.com>
 * 
 * @author Marcelo Emanoel, Luciano Broussal
 * @since 07/03/2007
 */
public class QueryBox extends TextBox {

    private static final String SELECTED_ROW_STYLE = "selected";
    private static final String RESULTS_STYLE = "results";
    private static final String QUERY_BOX_STYLE = "org_gwings_QueryBox";
    private static final String POPUP_STYLE = "org_gwings_QueryBoxPopup";
    private static final String EVEN_STYLE = "even";
    private static final String ODD_STYLE = "odd";
    private static final int NO_SELECTION = -1;
    private PopupPanel queryPopup;
    private FlexTable tableResults;
    private int maxResults;
    private int selectedRow;

    public QueryBox() {
        super();
        initialize();
        setupUI();
        setupStyles();
        setupListeners();

    }

    private void initialize() {
        queryPopup = new PopupPanel();
        tableResults = new FlexTable();
        setMaxResults(10);
        selectedRow = NO_SELECTION;
    }

    private void setupUI() {
        queryPopup.setWidth(this.getOffsetWidth() + "px");
        queryPopup.add(tableResults);
        tableResults.setWidth("100%");
    }

    private void setupStyles() {
        setStyleName(QUERY_BOX_STYLE);
        queryPopup.setStyleName(POPUP_STYLE);
        tableResults.setStyleName(RESULTS_STYLE);
    }

    private void setupListeners() {
        this.addKeyboardListener(new KeyboardListenerAdapter() {
            public void onKeyUp(Widget sender, char keyCode, int modifiers) {
                int rowCount = tableResults.getRowCount();
                FlexCellFormatter flexFormatter = tableResults.getFlexCellFormatter();
				if (hasSelectedLine()) {
                    flexFormatter.removeStyleName(selectedRow, 0, SELECTED_ROW_STYLE);
                }

                switch (keyCode) {
                case KEY_DOWN: {
                    int nextRow = selectedRow + 1;
                    selectedRow = (nextRow == rowCount ? 0 : nextRow);
                    flexFormatter.addStyleName(selectedRow, 0, SELECTED_ROW_STYLE);
                    break;
                }
                case KEY_UP: {
                     int previousRow = selectedRow - 1;
                     selectedRow = (previousRow <= NO_SELECTION ? rowCount -1 : previousRow);
                     flexFormatter.addStyleName(selectedRow, 0, SELECTED_ROW_STYLE);
                     break;
                }
                case KEY_ENTER: {
                    if (hasSelectedLine()) {
                        commit(selectedRow, 0);
                    } else {
                        hideResults();
                    }
                    break;
                }
                default:
                    hideResults();
                    break;
                }
            }

            public void onKeyDown(Widget sender, char keyCode, int modifiers) {
                if (keyCode != KEY_DOWN && keyCode != KEY_UP) {
                    clearTableResults();
                }
            }
        });
        tableResults.addTableListener(new TableListener() {
            public void onCellClicked(SourcesTableEvents sender, int row, int cell) {
                commit(row, cell);
            }
        });

    }

    private void hideResults() {
        queryPopup.hide();
        clearTableResults();
        selectedRow = -1;
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

    public void response(final Collection results) {
        if (results != null) {
            DeferredCommand.add(new Command() {
                public void execute() {
                    updateResponse(results);
                }
            });
        }
    }

    /**
     * @param row
     * @param cell
     */
    private void commit(int row, int cell) {
        String text = tableResults.getText(row, cell);
        setText(text);
        hideResults();
    }

    /**
     * @return
     */
    private boolean hasSelectedLine() {
        return selectedRow != NO_SELECTION;
    }

	/**
	 * @param results
	 */
	private void updateResponse(Collection results) {
		int left = getAbsoluteLeft();
		int top = getAbsoluteTop() + getOffsetHeight() + 1;
		int row = 0;
		int max = getMaxResults();
		Iterator it = results.iterator();

		queryPopup.setPopupPosition(left, top);
		queryPopup.setPixelSize(getOffsetWidth(), getOffsetHeight());
		queryPopup.show();
		
		for (Iterator i = it; i.hasNext() && row < max; row++) {
		    tableResults.setText(row, 0, (String) i.next());
		    String style = (row % 2 == 0 ? ODD_STYLE : EVEN_STYLE);
		    tableResults.getRowFormatter().setStyleName(row, style);

		}
	}
}
