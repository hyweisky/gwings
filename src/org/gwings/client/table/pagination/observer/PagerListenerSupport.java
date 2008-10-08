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
public class PagerListenerSupport<T extends Plotable> implements Serializable {

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

    public void fireNextPageRequest(PagerEvent<T> evt) {
        for (PagerListener<T> listener : listeners) {
            listener.nextPageRequest(evt);
        }
    }

    public void fireNextPageReady(PagerEvent<T> evt) {
        for (PagerListener<T> listener : listeners) {
            listener.nextPageReady(evt);
        }
    }

    public void firePreviousPageRequest(PagerEvent<T> evt) {
        for (PagerListener<T> listener : listeners) {
            listener.previousPageRequest(evt);
        }
    }

    public void firePreviousPageReady(PagerEvent<T> evt) {
        for (PagerListener<T> listener : listeners) {
            listener.previousPageReady(evt);
        }
    }

    public void fireLastPageRequest(PagerEvent<T> evt) {
        for (PagerListener<T> listener : listeners) {
            listener.lastPageRequest(evt);
        }
    }

    public void fireLastPageReady(PagerEvent<T> evt) {
        for (PagerListener<T> listener : listeners) {
            listener.lastPageReady(evt);
        }
    }

    public void fireFirstPageRequest(PagerEvent<T> evt) {
        for (PagerListener<T> listener : listeners) {
            listener.firstPageRequest(evt);
        }
    }

    public void fireFirstPageReady(PagerEvent<T> evt) {
        for (PagerListener<T> listener : listeners) {
            listener.firstPageReady(evt);
        }
    }

    public void firePageChangeRequest(PagerEvent<T> evt) {
        for (PagerListener<T> listener : listeners) {
            listener.pageChangeRequest(evt);
        }
    }

    public void firePageChangeReady(PagerEvent<T> evt) {
        for (PagerListener<T> listener : listeners) {
            listener.pageChangeReady(evt);
        }
    }
}