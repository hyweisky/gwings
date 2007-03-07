/**
 * 
 */
package org.gwings.client.ui.impl;

import java.util.ArrayList;
import java.util.List;

import org.gwings.client.ui.ColumnRenderer;
import org.gwings.client.ui.Plotable;
import org.gwings.client.ui.TableModel;
import org.gwings.client.ui.TableModelEvent;
import org.gwings.client.ui.TableModelListener;
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
public class DefaultTableModel implements TableModel {
	private List colunas;
	private List linhas;
	private List listeners;

	private class Column {
		private String name;
		private ColumnRenderer type;

		public Column(String name, ColumnRenderer type) {

			setName(name);
			setType(type);
		}

		public String getName() {

			return this.name;
		}

		public void setName(String name) {

			this.name = name;
		}

		public ColumnRenderer getType() {

			return this.type;
		}

		public void setType(ColumnRenderer type) {

			this.type = type;
		}

		public boolean equals(Object obj) {

			Column col = (Column) obj;
			return col.getName().equals(getName());
		}
	}

	public DefaultTableModel() {

		this(new ArrayList(), new ArrayList());
	}

	public DefaultTableModel(List linhas, List colunas) {

		listeners = new ArrayList();
		this.colunas = colunas;
		this.linhas = linhas;
	}

	public void addColumn(String columnName, ColumnRenderer type) {

		Column column = new Column(columnName, type);
		if (!colunas.contains(column)) {
			colunas.add(column);
			int index = colunas.indexOf(column);
			TableModelEvent evt = makeEvent();
			evt.setColumn(index);
			fireColumnAdded(evt);
		}
	}

	public void addColumn(String column) {

		addColumn(column, makeColumnType());
	}

	public void addLine(int index, Plotable line) {

		linhas.add(index, line);
		
		TableModelEvent evt = makeEvent();
		evt.setRow(index);
		fireRowAdded(evt);
	}

	public void appendLine(Plotable line) {
		addLine(linhas.size(), line);
	}

	public void clearAll() {

		linhas.clear();
		colunas.clear();
		TableModelEvent evt = makeEvent();
		fireClearedAll(evt);
	}

	public void clearRows() {

		linhas.clear();
		TableModelEvent evt = makeEvent();
		fireRowsCleared(evt);

	}

	public int getColumnCount() {

		return colunas.size();
	}

	public String getColumnName(int column) {
		if(column < colunas.size()){
			Column col = (Column) colunas.get(column);
			return col.getName();
		}
		return "";
	}

	public ColumnRenderer getColumnRenderer(int column) {

		Column col = (Column) colunas.get(column);
		return col.getType();
	}

	public ColumnRenderer getColumnRenderer(String columnName) {

		ColumnRenderer defaultType = makeColumnType();
		Column coluna = new Column(columnName, defaultType);
		for (int i = 0; i < colunas.size(); i++) {
			Column col = (Column) colunas.get(i);
			if (coluna.equals(col)) {
				return col.getType();
			}
		}
		return defaultType;
	}

	public Plotable getLine(int line) {
		
		if (line >= 0 && linhas.size() > line) {
			return (Plotable) linhas.get(line);
		}
		return null;
	}

	public int getRowCount() {

		return linhas.size();
	}

	public String removeColumn(int column) {

		Column coluna = (Column) colunas.remove(column);
		TableModelEvent evt = makeEvent();
		evt.setColumn(column);
		fireColumnRemoved(evt);
		return coluna.getName();
	}

	public void removeColumn(String columnName) {

		Column coluna = new Column(columnName, makeColumnType());
		int index = colunas.indexOf(coluna);
		if (index != -1) {
			colunas.remove(coluna);

			TableModelEvent evt = makeEvent();
			evt.setColumn(index);
			fireColumnRemoved(evt);
		}
	}

	public Plotable removeLine(int line) {

		if (line < linhas.size()) {
			Plotable plotable = (Plotable) linhas.remove(line);
			TableModelEvent evt = makeEvent();
			evt.setRow(line);
			fireLineRemoved(evt);
			return plotable;
		}
		return null;
	}

	public void removeLine(Plotable line) {

		if (linhas.contains(line)) {
			int indice = linhas.indexOf(line);
			linhas.remove(indice);

			TableModelEvent evt = makeEvent();
			evt.setRow(indice);
			fireLineRemoved(evt);
		}
	}

	public void removeTableModelListener(TableModelListener l) {

		listeners.remove(l);
	}

	public void addTableModelListener(TableModelListener l) {

		listeners.add(l);
	}

	public void setColumnRenderer(int column, ColumnRenderer type) {

		Column coluna = (Column) colunas.get(column);
		coluna.setType(type);
	}

	public void setColumnRenderer(String columnName, ColumnRenderer type) {

		Column coluna = new Column(columnName, makeColumnType());
		int index = colunas.indexOf(coluna);
		coluna = (Column) colunas.get(index);
		coluna.setType(type);
	}

	/*
	 * Factory Methods
	 */

	/**
	 * @return
	 */
	private TableModelEvent makeEvent() {

		TableModelEvent evt = new TableModelEvent(this);
		return evt;
	}

	/**
	 * @return
	 */
	private ColumnRenderer makeColumnType() {

		return new DefaultColumnType();
	}

	/*
	 * Fire events methods!
	 */

	private void fireRowAdded(TableModelEvent evt) {

		for (int i = 0; i < listeners.size(); i++) {
			TableModelListener listener = (TableModelListener) listeners.get(i);
			listener.rowAdded(evt);
		}
	}

	private void fireColumnAdded(TableModelEvent evt) {

		for (int i = 0; i < listeners.size(); i++) {
			TableModelListener listener = (TableModelListener) listeners.get(i);
			listener.columnAdded(evt);
		}
	}

	private void fireClearedAll(TableModelEvent evt) {

		for (int i = 0; i < listeners.size(); i++) {
			TableModelListener listener = (TableModelListener) listeners.get(i);
			listener.tableCleared(evt);
		}
	}

	private void fireRowsCleared(TableModelEvent evt) {

		for (int i = 0; i < listeners.size(); i++) {
			TableModelListener listener = (TableModelListener) listeners.get(i);
			listener.rowsCleared(evt);
		}
	}

	private void fireColumnRemoved(TableModelEvent evt) {

		for (int i = 0; i < listeners.size(); i++) {
			TableModelListener listener = (TableModelListener) listeners.get(i);
			listener.columnRemoved(evt);
		}
	}

	private void fireLineRemoved(TableModelEvent evt) {

		for (int i = 0; i < listeners.size(); i++) {
			TableModelListener listener = (TableModelListener) listeners.get(i);
			listener.rowRemoved(evt);
		}
	}

}