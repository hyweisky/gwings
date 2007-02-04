/**
 * 
 */
package org.gwings.client.ui.impl;

import org.gwings.client.ui.GColumnRenderer;
import org.gwings.client.ui.GPlotable;
import org.gwings.client.ui.GTable;
import org.gwings.client.ui.GTableModel;
import org.gwings.client.ui.GTableModelEvent;
import org.gwings.client.ui.GTableModelListener;

import com.google.gwt.user.client.ui.Composite;
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
 * @author Marcelo Emanoel
 * @since 04/02/2007
 */
public class DefaultGTable extends Composite implements GTable, GTableModelListener{
	private static final int HEADER = 0;
	private GTableModel tableModel;
	private FlexTable tableView;
	
	public DefaultGTable(){
		this(new DefaultGTableModel());
	}
	
	public DefaultGTable(GTableModel model){
		setTableModel(model);
		tableView = new FlexTable();
		tableView.insertRow(HEADER);
//		tableView.setBorderWidth(1);
		tableView.addStyleName("org-gwings-gtable");
		this.initWidget(tableView);
	}

	/**
	 * @return the tableModel
	 */
	public GTableModel getTableModel() {
		return tableModel;
	}

	/**
	 * @param tableModel the tableModel to set
	 */
	public void setTableModel(GTableModel tableModel) {
		this.tableModel = tableModel;
		this.tableModel.addTableModelListener(this);
	}

	/**
	 * @return the tableView
	 */
	public FlexTable getTableView() {
		return tableView;
	}

	public void columnAdded(GTableModelEvent evt) {
		GTableModel model = evt.getSource();
		String columnName = model.getColumnName(evt.getColumn());
		for(int i=0; i < model.getRowCount(); i++){
			tableView.addCell(i);
		}
		int coluna = tableView.getCellCount(HEADER);
		tableView.setHTML(HEADER, coluna, columnName);
		tableView.getRowFormatter().setStyleName(HEADER,"header");
	}

	public void columnRemoved(GTableModelEvent evt) {
		GTableModel model = evt.getSource();
		int column = evt.getColumn();
		for(int i=HEADER; i < model.getRowCount(); i++){
			tableView.removeCell(i,column);
		}
	}

	public void rowAdded(GTableModelEvent evt) {
		GTableModel model = evt.getSource();
		int row = evt.getRow();
		
		GPlotable plotable = model.getLine(row);
		
		Object[] line = plotable.plot();
		tableView.insertRow(row+1);
		for(int i=0; i < line.length; i++){
			GColumnRenderer columnType = model.getColumnRenderer(i);
			Widget widget = columnType.renderType(line[i]);
			tableView.setWidget(row+1, i, widget);
			tableView.getFlexCellFormatter().setAlignment(row+1, i, VerticalPanel.ALIGN_CENTER, VerticalPanel.ALIGN_MIDDLE);
		}
	}

	public void rowChanged(GTableModelEvent evt) {
		rowRemoved(evt);
		rowAdded(evt);
	}
	
	public void rowRemoved(GTableModelEvent evt) {
		int row = evt.getRow();
		tableView.removeRow(row+1);
	}

	public void rowsCleared(GTableModelEvent evt) {
		tableCleared(evt);
		GTableModel model = evt.getSource();
		for(int i=0; i < model.getColumnCount(); i++){
			tableView.setHTML(HEADER, i, model.getColumnName(i));
		}
	}

	public void tableCleared(GTableModelEvent evt) {
		for(int i =0; i < evt.getSource().getRowCount();i++){
			tableView.removeRow(i+1);
		}
		tableView.clear();
	}
}

