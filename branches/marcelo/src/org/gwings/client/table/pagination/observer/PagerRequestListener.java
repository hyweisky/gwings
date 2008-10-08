package org.gwings.client.table.pagination.observer;

import org.gwings.client.table.model.Plotable;

public interface PagerRequestListener<T extends Plotable> {

    public void pageChangeRequest(PagerEvent<T> evt);

    public void firstPageRequest(PagerEvent<T> evt);

    public void lastPageRequest(PagerEvent<T> evt);

    public void previousPageRequest(PagerEvent<T> evt);

    public void nextPageRequest(PagerEvent<T> evt);

}
