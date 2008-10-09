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
public class PagerReadySupport<T extends Plotable> {

    private List<PagerReadyListener<T>> listeners;

    public PagerReadySupport() {
        listeners = new ArrayList<PagerReadyListener<T>>();
    }

    public void addPagerReadyListener(PagerReadyListener<T> listener) {
        listeners.add(listener);
    }

    public void removePagerReadyListener(PagerReadyListener<T> listener) {
        listeners.remove(listener);
    }

    public void fireNextPageReady(PagerEvent<T> evt) {
        for (PagerReadyListener<T> listener : listeners) {
            listener.nextPageReady(evt);
        }
    }

    public void firePreviousPageReady(PagerEvent<T> evt) {
        for (PagerReadyListener<T> listener : listeners) {
            listener.previousPageReady(evt);
        }
    }

    public void fireLastPageReady(PagerEvent<T> evt) {
        for (PagerReadyListener<T> listener : listeners) {
            listener.lastPageReady(evt);
        }
    }

    public void fireFirstPageReady(PagerEvent<T> evt) {
        for (PagerReadyListener<T> listener : listeners) {
            listener.firstPageReady(evt);
        }
    }

    public void firePageChangeReady(PagerEvent<T> evt) {
        for (PagerReadyListener<T> listener : listeners) {
            listener.pageChangeReady(evt);
        }
    }
}
