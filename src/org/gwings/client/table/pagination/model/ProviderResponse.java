/**
 * 
 */
package org.gwings.client.table.pagination.model;

import java.util.List;

import org.gwings.client.table.model.Plotable;


/**
 * @author USER
 *
 */
public class ProviderResponse<T extends Plotable> {
    private Page<T>  page;
    
    public ProviderResponse(List<T> items, PageConfig config){
        page = new Page<T>();
        setConfig(config);
        setItems(items);
    }
    
    /**
     * @param config
     * @see org.gwings.client.table.pagination.model.Page#setConfig(org.gwings.client.table.pagination.model.PageConfig)
     */
    public void setConfig(PageConfig config) {
        page.setConfig(config);
    }

    /**
     * @param items
     * @see org.gwings.client.table.pagination.model.Page#setItems(java.util.List)
     */
    public void setItems(List<T> items) {
        page.setItems(items);
    }

    
    /**
     * @return the page
     */
    public Page<T> getPage() {
        return page;
    }
    
    
}
