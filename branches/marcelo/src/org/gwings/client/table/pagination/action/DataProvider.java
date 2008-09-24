/**
 * 
 */
package org.gwings.client.table.pagination.action;

import java.io.Serializable;

import org.gwings.client.table.pagination.PageConfig;
import org.gwings.client.table.pagination.PageResult;


/**
 * @author USER
 */
public interface DataProvider<T extends Serializable> {

    public PageResult<T> fetchData(PageConfig config);
    
}
