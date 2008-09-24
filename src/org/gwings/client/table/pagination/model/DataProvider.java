package org.gwings.client.table.pagination.model;

import java.io.Serializable;



public interface DataProvider<T> extends Serializable {
    
    public Page<T> fetchData(PageConfig config) throws Exception;
    
    public Integer fetchSize() throws Exception;
    
}
