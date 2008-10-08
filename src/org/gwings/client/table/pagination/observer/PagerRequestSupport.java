/**
 * 
 */
package org.gwings.client.table.pagination.observer;

import java.util.ArrayList;
import java.util.List;

import org.gwings.client.table.model.Plotable;

/**
 * @author USER
 */
public class PagerRequestSupport<T extends Plotable> {

    private List<PagerRequestListener<T>> listeners;

    public PagerRequestSupport() {
        listeners = new ArrayList<PagerRequestListener<T>>();
    }

    public void addPagerListener(PagerRequestListener<T> listener) {
        listeners.add(listener);
    }

    public void removePagerListener(PagerRequestListener<T> listener) {
        listeners.remove(listener);
    }

    public void fireNextPageRequest(PagerEvent<T> evt) {
        for (PagerRequestListener<T> listener : listeners) {
            listener.nextPageRequest(evt);
        }
    }

    public void firePreviousPageRequest(PagerEvent<T> evt) {
        for (PagerRequestListener<T> listener : listeners) {
            listener.previousPageRequest(evt);
        }
    }

    public void fireLastPageRequest(PagerEvent<T> evt) {
        for (PagerRequestListener<T> listener : listeners) {
            listener.lastPageRequest(evt);
        }
    }

    public void fireFirstPageRequest(PagerEvent<T> evt) {
        for (PagerRequestListener<T> listener : listeners) {
            listener.firstPageRequest(evt);
        }
    }

    public void firePageChangeRequest(PagerEvent<T> evt) {
        for (PagerRequestListener<T> listener : listeners) {
            listener.pageChangeRequest(evt);
        }
    }
}
