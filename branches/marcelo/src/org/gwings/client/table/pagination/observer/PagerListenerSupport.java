/**
 * 
 */
package org.gwings.client.table.pagination.observer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author USER
 */
public class PagerListenerSupport<T> implements Serializable {

    private static final long serialVersionUID = 5268124961156076693L;

    private List<PagerListener<T>> listeners;

    public PagerListenerSupport() {
        listeners = new ArrayList<PagerListener<T>>();
    }

    public void addPagerListener(PagerListener<T> listener) {
        listeners.add(listener);
    }

    public void removePagerListener(PagerListener<T> listener) {
        listeners.remove(listener);
    }

    public void fireNextPage(PagerEvent<T> evt) {
        for (PagerListener<T> listener : listeners) {
            listener.nextPage(evt);
        }
    }

    public void firePreviousPage(PagerEvent<T> evt) {
        for (PagerListener<T> listener : listeners) {
            listener.previousPage(evt);
        }
    }

    public void fireLastPage(PagerEvent<T> evt) {
        for (PagerListener<T> listener : listeners) {
            listener.lastPage(evt);
        }
    }

    public void fireFirstPage(PagerEvent<T> evt) {
        for (PagerListener<T> listener : listeners) {
            listener.firstPage(evt);
        }
    }

    public void firePageChanged(PagerEvent<T> evt) {
        for (PagerListener<T> listener : listeners) {
            listener.pageChanged(evt);
        }
    }
}