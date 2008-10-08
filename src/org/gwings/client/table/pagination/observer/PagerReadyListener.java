/**
 * 
 */
package org.gwings.client.table.pagination.observer;

import org.gwings.client.table.model.Plotable;

/**
 * @author USER
 */
public interface PagerReadyListener<T extends Plotable> {

    public void pageChangeReady(PagerEvent<T> evt);

    public void firstPageReady(PagerEvent<T> evt);

    public void lastPageReady(PagerEvent<T> evt);

    public void previousPageReady(PagerEvent<T> evt);

    public void nextPageReady(PagerEvent<T> evt);

}
