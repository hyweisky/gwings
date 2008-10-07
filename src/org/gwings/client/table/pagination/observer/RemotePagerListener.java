package org.gwings.client.table.pagination.observer;

import org.gwings.client.table.model.Plotable;

public interface RemotePagerListener<T extends Plotable> {
    
    public void pageWillChange(RemotePagerEvent<T> evt);
    
    public void pageChanged(RemotePagerEvent<T> evt);
    
    public void firstPageRequested(RemotePagerEvent<T> evt);
    
    public void firstPageArrived(RemotePagerEvent<T> evt);

    public void lastPageRequested(RemotePagerEvent<T> evt);
    
    public void lastPageArrived(RemotePagerEvent<T> evt);
    
    public void previousPageRequested(RemotePagerEvent<T> evt);
    
    public void previousPageArrived(RemotePagerEvent<T> evt);

    public void nextPageArrived(RemotePagerEvent<T> evt);
    
    public void nextPageRequested(RemotePagerEvent<T> evt);

}
