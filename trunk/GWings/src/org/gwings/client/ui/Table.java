package org.gwings.client.ui;

import org.gwings.client.ui.impl.DefaultTableModel;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

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
 * @since 07/03/2007
 */
public class Table extends FlexTable implements TableModelListener {

	private static final int HEADER = 0;
	private TableModel tableModel;

	public Table() {
		this(new DefaultTableModel());
	}

	public Table(TableModel model) {
		setTableModel(model);
		insertRow(HEADER);
		// tableView.setBorderWidth(1);
		setStyleName("org-gwings-gtable");
	}

	/**
	 * Returns the GTableModel of this GTable.
	 * 
	 * @return The model of the specified GTable.
	 */
	public TableModel getTableModel() {
		return tableModel;
	}

	/**
	 * Set the model for the specified GTable.
	 * 
	 * @param theModel
	 *            The GTableModel to be used by the specified table.
	 */
	public void setTableModel(TableModel tableModel) {
		this.tableModel = tableModel;
		this.tableModel.addTableModelListener(this);
	}

	public void columnAdded(TableModelEvent evt) {
		TableModel model = evt.getSource();
		String columnName = model.getColumnName(evt.getColumn());
		for (int i = 0; i < model.getRowCount(); i++) {
			addCell(i);
		}
		int coluna = getCellCount(HEADER);
		setHTML(HEADER, coluna, columnName);
		getRowFormatter().setStyleName(HEADER, "header");
	}

	public void columnRemoved(TableModelEvent evt) {
		TableModel model = evt.getSource();
		int column = evt.getColumn();
		for (int i = HEADER; i < model.getRowCount(); i++) {
			removeCell(i, column);
		}
	}

	public void rowAdded(TableModelEvent evt) {
		TableModel model = evt.getSource();
		int row = evt.getRow();

		Plotable plotable = model.getLine(row);

		Object[] line = plotable.plot();
		insertRow(row + 1);
		for (int i = 0; i < line.length; i++) {
			ColumnRenderer columnType = model.getColumnRenderer(i);
			Widget widget = columnType.renderType(line[i]);
			setWidget(row + 1, i, widget);
			getFlexCellFormatter().setAlignment(row + 1, i,
					VerticalPanel.ALIGN_CENTER, VerticalPanel.ALIGN_MIDDLE);
		}
	}

	public void rowChanged(TableModelEvent evt) {
		rowRemoved(evt);
		rowAdded(evt);
	}

	public void rowRemoved(TableModelEvent evt) {
		int row = evt.getRow();
		removeRow(row + 1);
	}

	public void rowsCleared(TableModelEvent evt) {
		tableCleared(evt);
		TableModel model = evt.getSource();
		for (int i = 0; i < model.getColumnCount(); i++) {
			setHTML(HEADER, i, model.getColumnName(i));
		}
	}

	public void tableCleared(TableModelEvent evt) {
		for (int i = 0; i < evt.getSource().getRowCount(); i++) {
			removeRow(i + 1);
		}
		clear();
	}

}