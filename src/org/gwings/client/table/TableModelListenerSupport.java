/**
 * 
 */
package org.gwings.client.table;

import java.util.ArrayList;
import java.util.List;

/**
 * @author USER
 */
public class TableModelListenerSupport {

    private List<TableModelListener> listeners;

    public TableModelListenerSupport() {
        listeners = new ArrayList<TableModelListener>();
    }

    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }

    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
    }
    
    public void fireRowAdded(TableModelEvent evt) {
        for (int i = 0; i < listeners.size(); i++) {
            TableModelListener listener = (TableModelListener) listeners.get(i);
            listener.rowAdded(evt);
        }
    }

    public void fireColumnAdded(TableModelEvent evt) {
        for (int i = 0; i < listeners.size(); i++) {
            TableModelListener listener = (TableModelListener) listeners.get(i);
            listener.columnAdded(evt);
        }
    }

    public void fireClearedAll(TableModelEvent evt) {
        for (int i = 0; i < listeners.size(); i++) {
            TableModelListener listener = (TableModelListener) listeners.get(i);
            listener.tableCleared(evt);
        }
    }

    public void fireRowsCleared(TableModelEvent evt) {
        for (int i = 0; i < listeners.size(); i++) {
            TableModelListener listener = (TableModelListener) listeners.get(i);
            listener.rowsCleared(evt);
        }
    }

    public void fireColumnRemoved(TableModelEvent evt) {
        for (int i = 0; i < listeners.size(); i++) {
            TableModelListener listener = (TableModelListener) listeners.get(i);
            listener.columnRemoved(evt);
        }
    }

    public void fireLineRemoved(TableModelEvent evt) {
        for (int i = 0; i < listeners.size(); i++) {
            TableModelListener listener = (TableModelListener) listeners.get(i);
            listener.rowRemoved(evt);
        }
    }

    public void fireTableChanged(TableModelEvent event) {
        for (int i = 0; i < listeners.size(); i++) {
            TableModelListener listener = (TableModelListener) listeners.get(i);
            listener.tableChanged(event);
        }
    }
}