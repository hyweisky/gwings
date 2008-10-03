package org.gwings.client.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * @since 04/02/2007
 */
@SuppressWarnings("unchecked")
public class DefaultTableModel<T extends Plotable> implements TableModel<T> {

    private Map<String, Column> columns;
    private List<T> lines;
    private TableModelListenerSupport<T> support;
    
    public DefaultTableModel() {
        this(new ArrayList<T>(), new ArrayList<String>());
    }

    public DefaultTableModel(List<T> linhas, List<String> colunas) {
        support = new TableModelListenerSupport<T>();
        this.columns = new HashMap<String, Column>();
        this.lines = linhas;
    }

    public void addColumn(String columnName, ColumnRenderer type) {
        Column column = new Column(columnName, type);
       
        if (!columns.containsKey(columnName)) {
            columns.put(columnName, column);
            column.setPosition(columns.size());
            
            TableModelEvent<T> evt = makeEvent();
            evt.setColumn(column.getPosition()); //Alterar pra enviar a coluna em si!
            support.fireColumnAdded(evt);
        }
    }

    public void addColumn(String column) {
        addColumn(column, defaultColumnType());
    }

    public void addLine(int index, T line) {
        lines.add(index, line);

        TableModelEvent<T> evt = makeEvent();
        evt.setRow(index);
        support.fireRowAdded(evt);
    }

    public void appendLine(T line) {
        addLine(lines.size(), line);
    }

    public void clearAll() {
        lines.clear();
        columns.clear();

        TableModelEvent<T> evt = makeEvent();
        support.fireClearedAll(evt);
    }

    public void clearRows() {
        int rows = getRowCount();
        lines.clear();

        TableModelEvent<T> evt = makeEvent();
        evt.setRow(rows);
        support.fireRowsCleared(evt);
    }

    public int getColumnCount() {
        return columns.size();
    }

    public String getColumnName(int column) {
        if (columns.size() >= column && column >= 0) {
        	for(String key : columns.keySet()){
        		Column col = (Column) columns.get(key);
        		if(col.getPosition() == column){
        			return col.getName();
        		}
        	}
        }
        return "";
    }
    
    
    public ColumnRenderer getColumnRenderer(int columnPosition) {
        
        for(String key : columns.keySet()){
            Column column = columns.get(key);
            if(column.getPosition() == columnPosition){
                return column.getType();
            }
        }
        return defaultColumnType();
    }

    public ColumnRenderer getColumnRenderer(String columnName) {
        ColumnRenderer defaultType = defaultColumnType();
        Column coluna = new Column(columnName, defaultType);
        for (int i = 0; i < columns.size(); i++) {
            Column col = (Column) columns.get(i);
            if (coluna.equals(col)) {
                return col.getType();
            }
        }
        return defaultType;
    }

    public T getLine(int line) {
        if (line >= 0 && lines.size() > line) {
            return lines.get(line);
        }
        return null;
    }

    public int getRowCount() {
        return lines.size();
    }

    public String removeColumn(int column) {
        Column coluna = (Column) columns.remove(column);
        TableModelEvent<T> evt = makeEvent();
        evt.setColumn(column);
        support.fireColumnRemoved(evt);
        return coluna.getName();
    }

    public void removeColumn(String columnName) {
        Column column = columns.get(columnName);
        if (column != null) {
            columns.remove(columnName);

            TableModelEvent<T> evt = makeEvent();
            evt.setColumn(column.getPosition()); //Alterar o evento para conter a coluna!
            support.fireColumnRemoved(evt);
        }
    }

    public T removeLine(int line) {
        if (line < lines.size()) {
            T plotable = lines.remove(line);
            TableModelEvent<T> evt = makeEvent();
            evt.setRow(line);
            support.fireLineRemoved(evt);
            return plotable;
        }
        return null;
    }

    public void removeLine(T line) {
        if (lines.contains(line)) {
            int indice = lines.indexOf(line);
            lines.remove(indice);

            TableModelEvent<T> evt = makeEvent();
            evt.setRow(indice);
            support.fireLineRemoved(evt);
        }
    }
    public void setLines(List<T> lines) {
        this.lines = lines;
        support.fireTableChanged(makeEvent());
    }

    public void setColumnRenderer(int position, ColumnRenderer type) {
        for(String columnName : columns.keySet()){
            Column column = columns.get(columnName);
            
            if(column.getPosition() == position){
                column.setType(type);
                support.fireTableChanged(makeEvent());
                return;
            }
        }
    }

    public void setColumnRenderer(String columnName, ColumnRenderer type) {
        Column column = columns.get(columnName);
        column.setType(type);
        
        support.fireTableChanged(makeEvent());
    }

    /**
     * @return
     */
    private TableModelEvent<T> makeEvent() {
        TableModelEvent<T> evt = new TableModelEvent<T>(this);
        return evt;
    }

    /**
     * @return
     */
    private ColumnRenderer defaultColumnType() {
        return new DefaultColumnType();
    }

    public void removeTableModelListener(TableModelListener<T> l) {
        support.removeTableModelListener(l);
    }
    
    public void addTableModelListener(TableModelListener<T> l) {
        support.addTableModelListener(l);
    }
}