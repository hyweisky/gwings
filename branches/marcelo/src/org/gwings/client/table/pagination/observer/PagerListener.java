package org.gwings.client.table.pagination.observer;


public interface PagerListener<T> {

    void pageChanged(PagerEvent<T> evt);

    void firstPage(PagerEvent<T> evt);

    void lastPage(PagerEvent<T> evt);

    void previousPage(PagerEvent<T> evt);

    void nextPage(PagerEvent<T> evt);
    
}
