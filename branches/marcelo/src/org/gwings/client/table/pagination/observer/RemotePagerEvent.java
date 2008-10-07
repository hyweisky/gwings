/**
 * 
 */
package org.gwings.client.table.pagination.observer;

import java.util.EventObject;

import org.gwings.client.table.model.Plotable;
import org.gwings.client.table.pagination.model.RemotePager;

/**
 * @author USER
 */
public class RemotePagerEvent<T extends Plotable> extends EventObject {

    private static final long serialVersionUID = 5305195395588760298L;

    public RemotePagerEvent(RemotePager<T> source) {
        super(source);
    }
    
    @SuppressWarnings("unchecked")
    public RemotePager<T> getPager(){
        return (RemotePager<T>) getSource();
    }
}
