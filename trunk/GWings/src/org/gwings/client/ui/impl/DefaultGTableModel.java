/**
 * 
 */
package org.gwings.client.ui.impl;

import java.util.ArrayList;
import java.util.List;

import org.gwings.client.ui.GColumnRenderer;
import org.gwings.client.ui.GPlotable;
import org.gwings.client.ui.GTableModel;
import org.gwings.client.ui.GTableModelEvent;
import org.gwings.client.ui.GTableModelListener;
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
public class DefaultGTableModel implements GTableModel {
	private List colunas;
	private List linhas;
	private List listeners;

	private class Column {
		private String name;
		private GColumnRenderer type;

		public Column(String name, GColumnRenderer type) {

			setName(name);
			setType(type);
		}

		public String getName() {

			return this.name;
		}

		public void setName(String name) {

			this.name = name;
		}

		public GColumnRenderer getType() {

			return this.type;
		}

		public void setType(GColumnRenderer type) {

			this.type = type;
		}

		public boolean equals(Object obj) {

			Column col = (Column) obj;
			return col.getName().equals(getName());
		}
	}

	public DefaultGTableModel() {

		this(new ArrayList(), new ArrayList());
	}

	public DefaultGTableModel(List linhas, List colunas) {

		listeners = new ArrayList();
		this.colunas = colunas;
		this.linhas = linhas;
	}

	public void addColumn(String columnName, GColumnRenderer type) {

		Column column = new Column(columnName, type);
		if (!colunas.contains(column)) {
			colunas.add(column);
			int index = colunas.indexOf(column);
			GTableModelEvent evt = makeEvent();
			evt.setColumn(index);
			fireColumnAdded(evt);
		}
	}

	public void addColumn(String column) {

		addColumn(column, makeColumnType());
	}

	public void addLine(int index, GPlotable line) {

		linhas.add(index, line);
		
		GTableModelEvent evt = makeEvent();
		evt.setRow(index);
		fireRowAdded(evt);
	}

	public void appendLine(GPlotable line) {
		addLine(linhas.size(), line);
	}

	public void clearAll() {

		linhas.clear();
		colunas.clear();
		GTableModelEvent evt = makeEvent();
		fireClearedAll(evt);
	}

	public void clearRows() {

		linhas.clear();
		GTableModelEvent evt = makeEvent();
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

	public GColumnRenderer getColumnRenderer(int column) {

		Column col = (Column) colunas.get(column);
		return col.getType();
	}

	public GColumnRenderer getColumnRenderer(String columnName) {

		GColumnRenderer defaultType = makeColumnType();
		Column coluna = new Column(columnName, defaultType);
		for (int i = 0; i < colunas.size(); i++) {
			Column col = (Column) colunas.get(i);
			if (coluna.equals(col)) {
				return col.getType();
			}
		}
		return defaultType;
	}

	public GPlotable getLine(int line) {
		
		if (line >= 0 && linhas.size() > line) {
			return (GPlotable) linhas.get(line);
		}
		return null;
	}

	public int getRowCount() {

		return linhas.size();
	}

	public String removeColumn(int column) {

		Column coluna = (Column) colunas.remove(column);
		GTableModelEvent evt = makeEvent();
		evt.setColumn(column);
		fireColumnRemoved(evt);
		return coluna.getName();
	}

	public void removeColumn(String columnName) {

		Column coluna = new Column(columnName, makeColumnType());
		int index = colunas.indexOf(coluna);
		if (index != -1) {
			colunas.remove(coluna);

			GTableModelEvent evt = makeEvent();
			evt.setColumn(index);
			fireColumnRemoved(evt);
		}
	}

	public GPlotable removeLine(int line) {

		if (line < linhas.size()) {
			GPlotable plotable = (GPlotable) linhas.remove(line);
			GTableModelEvent evt = makeEvent();
			evt.setRow(line);
			fireLineRemoved(evt);
			return plotable;
		}
		return null;
	}

	public void removeLine(GPlotable line) {

		if (linhas.contains(line)) {
			int indice = linhas.indexOf(line);
			linhas.remove(indice);

			GTableModelEvent evt = makeEvent();
			evt.setRow(indice);
			fireLineRemoved(evt);
		}
	}

	public void removeTableModelListener(GTableModelListener l) {

		listeners.remove(l);
	}

	public void addTableModelListener(GTableModelListener l) {

		listeners.add(l);
	}

	public void setColumnRenderer(int column, GColumnRenderer type) {

		Column coluna = (Column) colunas.get(column);
		coluna.setType(type);
	}

	public void setColumnRenderer(String columnName, GColumnRenderer type) {

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
	private GTableModelEvent makeEvent() {

		GTableModelEvent evt = new GTableModelEvent(this);
		return evt;
	}

	/**
	 * @return
	 */
	private GColumnRenderer makeColumnType() {

		return new DefaultGColumnType();
	}

	/*
	 * Fire events methods!
	 */

	private void fireRowAdded(GTableModelEvent evt) {

		for (int i = 0; i < listeners.size(); i++) {
			GTableModelListener listener = (GTableModelListener) listeners.get(i);
			listener.rowAdded(evt);
		}
	}

	private void fireColumnAdded(GTableModelEvent evt) {

		for (int i = 0; i < listeners.size(); i++) {
			GTableModelListener listener = (GTableModelListener) listeners.get(i);
			listener.columnAdded(evt);
		}
	}

	private void fireClearedAll(GTableModelEvent evt) {

		for (int i = 0; i < listeners.size(); i++) {
			GTableModelListener listener = (GTableModelListener) listeners.get(i);
			listener.tableCleared(evt);
		}
	}

	private void fireRowsCleared(GTableModelEvent evt) {

		for (int i = 0; i < listeners.size(); i++) {
			GTableModelListener listener = (GTableModelListener) listeners.get(i);
			listener.rowsCleared(evt);
		}
	}

	private void fireColumnRemoved(GTableModelEvent evt) {

		for (int i = 0; i < listeners.size(); i++) {
			GTableModelListener listener = (GTableModelListener) listeners.get(i);
			listener.columnRemoved(evt);
		}
	}

	private void fireLineRemoved(GTableModelEvent evt) {

		for (int i = 0; i < listeners.size(); i++) {
			GTableModelListener listener = (GTableModelListener) listeners.get(i);
			listener.rowRemoved(evt);
		}
	}

}