/**
 * 
 */
package org.gwings.client.table.pagination.observer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.gwings.client.table.model.Plotable;

/**
 * @author USER
 */
public class RemotePagerListenerSupport<T extends Plotable> implements Serializable {

    private static final long serialVersionUID = 5268124961156076693L;

    private List<RemotePagerListener<T>> listeners;

    public RemotePagerListenerSupport() {
        listeners = new ArrayList<RemotePagerListener<T>>();
    }

    public void addRemotePagerListener(RemotePagerListener<T> listener) {
        listeners.add(listener);
    }

    public void removeRemotePagerListener(RemotePagerListener<T> listener) {
        listeners.remove(listener);
    }

    public void firePageChangeRequest(RemotePagerEvent<T> evt){
        for(RemotePagerListener<T> listener : listeners ){
            listener.pageWillChange(evt);
        }
    }

    public void firePageChanged(RemotePagerEvent<T> evt){
        for(RemotePagerListener<T> listener : listeners ){
            listener.pageChanged(evt);
        }
    }

    public void fireFirstPageRequest(RemotePagerEvent<T> evt){
        for(RemotePagerListener<T> listener : listeners ){
            listener.firstPageRequested(evt);
        }
    }

    public void fireFirstPageArrived(RemotePagerEvent<T> evt){
        for(RemotePagerListener<T> listener : listeners ){
            listener.firstPageArrived(evt);
        }
    }

    public void fireLastPageRequest(RemotePagerEvent<T> evt){
        for(RemotePagerListener<T> listener : listeners ){
            listener.lastPageRequested(evt);
        }
    }

    public void lastPageArrived(RemotePagerEvent<T> evt){
        for(RemotePagerListener<T> listener : listeners ){
            listener.lastPageArrived(evt);
        }
    }

    public void firePreviousPageRequest(RemotePagerEvent<T> evt){
        for(RemotePagerListener<T> listener : listeners ){
            listener.previousPageRequested(evt);
        }
    }

    public void firePreviousPageArrived(RemotePagerEvent<T> evt){
        for(RemotePagerListener<T> listener : listeners ){
            listener.previousPageArrived(evt);
        }
    }
    public void fireNextPageRequest(RemotePagerEvent<T> evt){
        for(RemotePagerListener<T> listener : listeners ){
            listener.nextPageRequested(evt);
        }
    }
    public void fireNextPageArrived(RemotePagerEvent<T> evt){
        for(RemotePagerListener<T> listener : listeners ){
            listener.nextPageArrived(evt);
        }
    }
}