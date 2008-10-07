package org.gwings.client.table.pagination.model;

import org.gwings.client.table.model.Plotable;

public interface AsyncDataProvider<T extends Plotable>{

    public void fetchData(PageConfig cfg);

    public void fetchSize();

    public void dataFetched(Page<T> page);

    public void sizeFetched(Integer size);
    
    public void failOnFetchData(Throwable cause);
    
    public void failOnFetchSize(Throwable cause);
}
