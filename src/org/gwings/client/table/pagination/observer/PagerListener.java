package org.gwings.client.table.pagination.observer;


public interface PagerListener<T> {

   public void pageChanged(PagerEvent<T> evt);

   public void firstPage(PagerEvent<T> evt);

   public void lastPage(PagerEvent<T> evt);

   public void previousPage(PagerEvent<T> evt);

   public void nextPage(PagerEvent<T> evt);
    
}
