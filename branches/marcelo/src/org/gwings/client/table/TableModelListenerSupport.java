/**
 * 
 */
package org.gwings.client.table;

import java.util.ArrayList;
import java.util.List;

/**
 * @author USER
 */
public class TableModelListenerSupport<T extends Plotable> {

    private List<TableModelListener<T>> listeners;

    public TableModelListenerSupport() {
        listeners = new ArrayList<TableModelListener<T>>();
    }

    public void removeTableModelListener(TableModelListener<T> l) {
        listeners.remove(l);
    }

    public void addTableModelListener(TableModelListener<T> l) {
        listeners.add(l);
    }
    
    public void fireRowAdded(TableModelEvent<T> evt) {
        for (int i = 0; i < listeners.size(); i++) {
            TableModelListener<T> listener = listeners.get(i);
            listener.rowAdded(evt);
        }
    }

    public void fireColumnAdded(TableModelEvent<T> evt) {
        for (int i = 0; i < listeners.size(); i++) {
            TableModelListener<T> listener = listeners.get(i);
            listener.columnAdded(evt);
        }
    }

    public void fireClearedAll(TableModelEvent<T> evt) {
        for (int i = 0; i < listeners.size(); i++) {
            TableModelListener<T> listener = listeners.get(i);
            listener.tableCleared(evt);
        }
    }

    public void fireRowsCleared(TableModelEvent<T> evt) {
        for (int i = 0; i < listeners.size(); i++) {
            TableModelListener<T> listener = listeners.get(i);
            listener.rowsCleared(evt);
        }
    }

    public void fireColumnRemoved(TableModelEvent<T> evt) {
        for (int i = 0; i < listeners.size(); i++) {
            TableModelListener<T> listener = listeners.get(i);
            listener.columnRemoved(evt);
        }
    }

    public void fireLineRemoved(TableModelEvent<T> evt) {
        for (int i = 0; i < listeners.size(); i++) {
            TableModelListener<T> listener = listeners.get(i);
            listener.rowRemoved(evt);
        }
    }

    public void fireTableChanged(TableModelEvent<T> event) {
        for (int i = 0; i < listeners.size(); i++) {
            TableModelListener<T> listener = listeners.get(i);
            listener.tableChanged(event);
        }
    }
}