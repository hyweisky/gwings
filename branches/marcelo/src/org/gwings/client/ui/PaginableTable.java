/**
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
 * Copyright 2007 Marcelo Emanoel B. Diniz <marceloemanoel AT gmail.com>, Luciano Broussal <luciano.broussal AT gmail.com>
 */
package org.gwings.client.ui;

import java.util.Iterator;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TableListener;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HTMLTable.CellFormatter;
import com.google.gwt.user.client.ui.HTMLTable.ColumnFormatter;
import com.google.gwt.user.client.ui.HTMLTable.RowFormatter;

/**
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
 * @author Marcelo Emanoel, Luciano Broussal
 * @since 18/03/2007
 */
public class PaginableTable extends SimplePanel implements Paginable {
	
	private FlexTable layout;
	private PagingBar pagingBar;
	private boolean paginable;
	private Table table;
	
	public PaginableTable(){
		this(new Table());
	}
	
	public PaginableTable(Table table){
		initialize(table);
		setupUI();
	}
	
	private void initialize(Table theTable) {
		this.table = theTable;
		pagingBar = new PagingBar(theTable);
		layout = new FlexTable();
		setPaginable(true);
	}

	private void setupUI() {
		super.add(layout);
		
		layout.setWidget(0, 0, new Label("Teste"));
		layout.setWidget(0, 0, table);
		layout.setWidget(1, 0, pagingBar);
		
		layout.setCellSpacing(0);
		layout.setCellPadding(0);
//		layout.setBorderWidth(1);
		
	}

	/* (non-Javadoc)
	 * @see org.gwings.client.ui.Paginable#isPaginable()
	 */
	public boolean isPaginable() {
		return this.paginable;
	}

	/* (non-Javadoc)
	 * @see org.gwings.client.ui.Paginable#setPaginable(boolean)
	 */
	public void setPaginable(boolean paginable) {
		this.paginable = paginable;
	}

	/**
	 * @param w
	 * @see com.google.gwt.user.client.ui.Panel#add(com.google.gwt.user.client.ui.Widget)
	 */
	public void add(Widget w) {
		table.add(w);
	}

	/**
	 * @param row
	 * @see com.google.gwt.user.client.ui.FlexTable#addCell(int)
	 */
	public void addCell(int row) {
		table.addCell(row);
	}

	/**
	 * @param style
	 * @see com.google.gwt.user.client.ui.UIObject#addStyleName(java.lang.String)
	 */
	public void addStyleName(String style) {
		table.addStyleName(style);
	}

	/**
	 * @param listener
	 * @see com.google.gwt.user.client.ui.HTMLTable#addTableListener(com.google.gwt.user.client.ui.TableListener)
	 */
	public void addTableListener(TableListener listener) {
		table.addTableListener(listener);
	}

	/**
	 * 
	 * @see com.google.gwt.user.client.ui.HTMLTable#clear()
	 */
	public void clear() {
		table.clear();
	}

	/**
	 * @param row
	 * @param column
	 * @return
	 * @see com.google.gwt.user.client.ui.HTMLTable#clearCell(int, int)
	 */
	public boolean clearCell(int row, int column) {
		return table.clearCell(row, column);
	}

	/**
	 * @param evt
	 * @see org.gwings.client.ui.Table#columnAdded(org.gwings.client.ui.TableModelEvent)
	 */
	public void columnAdded(TableModelEvent evt) {
		table.columnAdded(evt);
	}

	/**
	 * @param evt
	 * @see org.gwings.client.ui.Table#columnRemoved(org.gwings.client.ui.TableModelEvent)
	 */
	public void columnRemoved(TableModelEvent evt) {
		table.columnRemoved(evt);
	}

	/**
	 * @param obj
	 * @return
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return table.equals(obj);
	}

	/**
	 * @return
	 * @see com.google.gwt.user.client.ui.UIObject#getAbsoluteLeft()
	 */
	public int getAbsoluteLeft() {
		return table.getAbsoluteLeft();
	}

	/**
	 * @return
	 * @see com.google.gwt.user.client.ui.UIObject#getAbsoluteTop()
	 */
	public int getAbsoluteTop() {
		return table.getAbsoluteTop();
	}

	/**
	 * @param row
	 * @return
	 * @see com.google.gwt.user.client.ui.FlexTable#getCellCount(int)
	 */
	public int getCellCount(int row) {
		return table.getCellCount(row);
	}

	/**
	 * @return
	 * @see com.google.gwt.user.client.ui.HTMLTable#getCellFormatter()
	 */
	public CellFormatter getCellFormatter() {
		return table.getCellFormatter();
	}

	/**
	 * @return
	 * @see com.google.gwt.user.client.ui.HTMLTable#getCellPadding()
	 */
	public int getCellPadding() {
		return table.getCellPadding();
	}

	/**
	 * @return
	 * @see com.google.gwt.user.client.ui.HTMLTable#getCellSpacing()
	 */
	public int getCellSpacing() {
		return table.getCellSpacing();
	}

	/**
	 * @return
	 * @see com.google.gwt.user.client.ui.HTMLTable#getColumnFormatter()
	 */
	public ColumnFormatter getColumnFormatter() {
		return table.getColumnFormatter();
	}

	/**
	 * @return
	 * @see com.google.gwt.user.client.ui.FlexTable#getFlexCellFormatter()
	 */
	public FlexCellFormatter getFlexCellFormatter() {
		return table.getFlexCellFormatter();
	}

	/**
	 * @param row
	 * @param column
	 * @return
	 * @see com.google.gwt.user.client.ui.HTMLTable#getHTML(int, int)
	 */
	public String getHTML(int row, int column) {
		return table.getHTML(row, column);
	}

	/**
	 * @return
	 * @see com.google.gwt.user.client.ui.UIObject#getOffsetHeight()
	 */
	public int getOffsetHeight() {
		return table.getOffsetHeight();
	}

	/**
	 * @return
	 * @see com.google.gwt.user.client.ui.UIObject#getOffsetWidth()
	 */
	public int getOffsetWidth() {
		return table.getOffsetWidth();
	}

	/**
	 * @return
	 * @see com.google.gwt.user.client.ui.Widget#getParent()
	 */
	public Widget getParent() {
		return table.getParent();
	}

	/**
	 * @return
	 * @see com.google.gwt.user.client.ui.FlexTable#getRowCount()
	 */
	public int getRowCount() {
		return table.getRowCount();
	}

	/**
	 * @return
	 * @see com.google.gwt.user.client.ui.HTMLTable#getRowFormatter()
	 */
	public RowFormatter getRowFormatter() {
		return table.getRowFormatter();
	}

	/**
	 * @return
	 * @see com.google.gwt.user.client.ui.UIObject#getStyleName()
	 */
	public String getStyleName() {
		return table.getStyleName();
	}

	/**
	 * @return
	 * @see org.gwings.client.ui.Table#getTableModel()
	 */
	public TableModel getTableModel() {
		return table.getTableModel();
	}

	/**
	 * @param row
	 * @param column
	 * @return
	 * @see com.google.gwt.user.client.ui.HTMLTable#getText(int, int)
	 */
	public String getText(int row, int column) {
		return table.getText(row, column);
	}

	/**
	 * @return
	 * @see com.google.gwt.user.client.ui.UIObject#getTitle()
	 */
	public String getTitle() {
		return table.getTitle();
	}

	/**
	 * @param row
	 * @param column
	 * @return
	 * @see com.google.gwt.user.client.ui.HTMLTable#getWidget(int, int)
	 */
	public Widget getWidget(int row, int column) {
		return table.getWidget(row, column);
	}

	/**
	 * @return
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return table.hashCode();
	}

	/**
	 * @param beforeRow
	 * @param beforeColumn
	 * @see com.google.gwt.user.client.ui.FlexTable#insertCell(int, int)
	 */
	public void insertCell(int beforeRow, int beforeColumn) {
		table.insertCell(beforeRow, beforeColumn);
	}

	/**
	 * @param beforeRow
	 * @return
	 * @see com.google.gwt.user.client.ui.FlexTable#insertRow(int)
	 */
	public int insertRow(int beforeRow) {
		return table.insertRow(beforeRow);
	}

	/**
	 * @return
	 * @see com.google.gwt.user.client.ui.Widget#isAttached()
	 */
	public boolean isAttached() {
		return table.isAttached();
	}

	/**
	 * @param row
	 * @param column
	 * @return
	 * @see com.google.gwt.user.client.ui.HTMLTable#isCellPresent(int, int)
	 */
	public boolean isCellPresent(int row, int column) {
		return table.isCellPresent(row, column);
	}

	/**
	 * @return
	 * @see com.google.gwt.user.client.ui.UIObject#isVisible()
	 */
	public boolean isVisible() {
		return table.isVisible();
	}

	/**
	 * @return
	 * @see org.gwings.client.ui.Table#isZebraMode()
	 */
	public boolean isZebraMode() {
		return table.isZebraMode();
	}

	/**
	 * @return
	 * @see com.google.gwt.user.client.ui.HTMLTable#iterator()
	 */
	public Iterator iterator() {
		return table.iterator();
	}

	/**
	 * @param event
	 * @see com.google.gwt.user.client.ui.HTMLTable#onBrowserEvent(com.google.gwt.user.client.Event)
	 */
	public void onBrowserEvent(Event event) {
		table.onBrowserEvent(event);
	}

	/**
	 * @param widget
	 * @return
	 * @see com.google.gwt.user.client.ui.HTMLTable#remove(com.google.gwt.user.client.ui.Widget)
	 */
	public boolean remove(Widget widget) {
		return table.remove(widget);
	}

	/**
	 * @param row
	 * @param col
	 * @see com.google.gwt.user.client.ui.FlexTable#removeCell(int, int)
	 */
	public void removeCell(int row, int col) {
		table.removeCell(row, col);
	}

	/**
	 * @param row
	 * @param column
	 * @param num
	 * @see com.google.gwt.user.client.ui.FlexTable#removeCells(int, int, int)
	 */
	public void removeCells(int row, int column, int num) {
		table.removeCells(row, column, num);
	}

	/**
	 * @param row
	 * @see com.google.gwt.user.client.ui.FlexTable#removeRow(int)
	 */
	public void removeRow(int row) {
		table.removeRow(row);
	}

	/**
	 * @param style
	 * @see com.google.gwt.user.client.ui.UIObject#removeStyleName(java.lang.String)
	 */
	public void removeStyleName(String style) {
		table.removeStyleName(style);
	}

	/**
	 * @param listener
	 * @see com.google.gwt.user.client.ui.HTMLTable#removeTableListener(com.google.gwt.user.client.ui.TableListener)
	 */
	public void removeTableListener(TableListener listener) {
		table.removeTableListener(listener);
	}

	/**
	 * @param evt
	 * @see org.gwings.client.ui.Table#rowAdded(org.gwings.client.ui.TableModelEvent)
	 */
	public void rowAdded(TableModelEvent evt) {
		table.rowAdded(evt);
	}

	/**
	 * @param evt
	 * @see org.gwings.client.ui.Table#rowChanged(org.gwings.client.ui.TableModelEvent)
	 */
	public void rowChanged(TableModelEvent evt) {
		table.rowChanged(evt);
	}

	/**
	 * @param evt
	 * @see org.gwings.client.ui.Table#rowRemoved(org.gwings.client.ui.TableModelEvent)
	 */
	public void rowRemoved(TableModelEvent evt) {
		table.rowRemoved(evt);
	}

	/**
	 * @param evt
	 * @see org.gwings.client.ui.Table#rowsCleared(org.gwings.client.ui.TableModelEvent)
	 */
	public void rowsCleared(TableModelEvent evt) {
		table.rowsCleared(evt);
	}

	/**
	 * @param width
	 * @see com.google.gwt.user.client.ui.HTMLTable#setBorderWidth(int)
	 */
	public void setBorderWidth(int width) {
		table.setBorderWidth(width);
	}

	/**
	 * @param padding
	 * @see com.google.gwt.user.client.ui.HTMLTable#setCellPadding(int)
	 */
	public void setCellPadding(int padding) {
		table.setCellPadding(padding);
	}

	/**
	 * @param spacing
	 * @see com.google.gwt.user.client.ui.HTMLTable#setCellSpacing(int)
	 */
	public void setCellSpacing(int spacing) {
		table.setCellSpacing(spacing);
	}

	/**
	 * @param height
	 * @see com.google.gwt.user.client.ui.UIObject#setHeight(java.lang.String)
	 */
	public void setHeight(String height) {
		table.setHeight(height);
	}

	/**
	 * @param row
	 * @param column
	 * @param html
	 * @see com.google.gwt.user.client.ui.HTMLTable#setHTML(int, int, java.lang.String)
	 */
	public void setHTML(int row, int column, String html) {
		table.setHTML(row, column, html);
	}

	/**
	 * @param width
	 * @param height
	 * @see com.google.gwt.user.client.ui.UIObject#setPixelSize(int, int)
	 */
	public void setPixelSize(int width, int height) {
		table.setPixelSize(width, height);
	}

	/**
	 * @param width
	 * @param height
	 * @see com.google.gwt.user.client.ui.UIObject#setSize(java.lang.String, java.lang.String)
	 */
	public void setSize(String width, String height) {
		table.setSize(width, height);
	}

	/**
	 * @param style
	 * @see com.google.gwt.user.client.ui.UIObject#setStyleName(java.lang.String)
	 */
	public void setStyleName(String style) {
		table.setStyleName(style);
	}

	/**
	 * @param tableModel
	 * @see org.gwings.client.ui.Table#setTableModel(org.gwings.client.ui.TableModel)
	 */
	public void setTableModel(TableModel tableModel) {
		table.setTableModel(tableModel);
	}

	/**
	 * @param row
	 * @param column
	 * @param text
	 * @see com.google.gwt.user.client.ui.HTMLTable#setText(int, int, java.lang.String)
	 */
	public void setText(int row, int column, String text) {
		table.setText(row, column, text);
	}

	/**
	 * @param title
	 * @see com.google.gwt.user.client.ui.UIObject#setTitle(java.lang.String)
	 */
	public void setTitle(String title) {
		table.setTitle(title);
	}

	/**
	 * @param visible
	 * @see com.google.gwt.user.client.ui.UIObject#setVisible(boolean)
	 */
	public void setVisible(boolean visible) {
		table.setVisible(visible);
	}

	/**
	 * @param row
	 * @param column
	 * @param widget
	 * @see com.google.gwt.user.client.ui.HTMLTable#setWidget(int, int, com.google.gwt.user.client.ui.Widget)
	 */
	public void setWidget(int row, int column, Widget widget) {
		table.setWidget(row, column, widget);
	}

	/**
	 * @param width
	 * @see com.google.gwt.user.client.ui.UIObject#setWidth(java.lang.String)
	 */
	public void setWidth(String width) {
		table.setWidth(width);
	}

	/**
	 * @param zebraMode
	 * @see org.gwings.client.ui.Table#setZebraMode(boolean)
	 */
	public void setZebraMode(boolean zebraMode) {
		table.setZebraMode(zebraMode);
	}

	/**
	 * @param eventBitsToAdd
	 * @see com.google.gwt.user.client.ui.UIObject#sinkEvents(int)
	 */
	public void sinkEvents(int eventBitsToAdd) {
		table.sinkEvents(eventBitsToAdd);
	}

	/**
	 * @param evt
	 * @see org.gwings.client.ui.Table#tableCleared(org.gwings.client.ui.TableModelEvent)
	 */
	public void tableCleared(TableModelEvent evt) {
		table.tableCleared(evt);
	}

	/**
	 * @return
	 * @see com.google.gwt.user.client.ui.UIObject#toString()
	 */
	public String toString() {
		return table.toString();
	}

	/**
	 * @param eventBitsToRemove
	 * @see com.google.gwt.user.client.ui.UIObject#unsinkEvents(int)
	 */
	public void unsinkEvents(int eventBitsToRemove) {
		table.unsinkEvents(eventBitsToRemove);
	}

	/**
	 * @return the pagingBar
	 */
	public PagingBar getPagingBar() {
		return pagingBar;
	}

	/**
	 * @param pagingBar the pagingBar to set
	 */
	public void setPagingBar(PagingBar pagingBar) {
		this.pagingBar = pagingBar;
	}

}
