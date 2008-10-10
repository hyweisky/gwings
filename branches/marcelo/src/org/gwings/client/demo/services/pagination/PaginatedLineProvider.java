/**
 * 
 */
package org.gwings.client.demo.services.pagination;

import java.io.Serializable;
import java.util.Map;

import org.gwings.client.demo.LinePlotable;
import org.gwings.client.table.pagination.model.Page;
import org.gwings.client.table.pagination.model.PageConfig;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


/**
 * @author USER
 *
 */
@RemoteServiceRelativePath("/provider")
public interface PaginatedLineProvider extends RemoteService {
    
    public Page<LinePlotable> getItems(Map<String, ? extends Serializable> params, PageConfig cfg) throws Exception;
}
