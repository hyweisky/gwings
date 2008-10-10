/**
 * 
 */
package org.gwings.client.demo.services.pagination;

import java.io.Serializable;
import java.util.Map;

import org.gwings.client.demo.LinePlotable;
import org.gwings.client.table.pagination.model.Page;
import org.gwings.client.table.pagination.model.PageConfig;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;

/**
 * @author USER
 */
public interface PaginatedLineProviderAsync extends RemoteService {

    public void getItems(Map<String,? extends Serializable> params, PageConfig cfg,
                         AsyncCallback<Page<LinePlotable>> callback);

}
