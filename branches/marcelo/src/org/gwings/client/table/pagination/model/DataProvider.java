package org.gwings.client.table.pagination.model;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("/demoProvider")
public interface DataProvider<T> extends Serializable, RemoteService {
    
    public Page<T> fetchData(PageConfig config) throws Exception;
    
    public Integer fetchSize() throws Exception;
    
}
