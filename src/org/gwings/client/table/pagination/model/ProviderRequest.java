/**
 * 
 */
package org.gwings.client.table.pagination.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * @author USER
 *
 */
public class ProviderRequest {
    private Map<String, ? extends Serializable> params;
    private PageConfig config;
    
    public ProviderRequest(PageConfig config, Map<String, Serializable> params) {
        super();
        setConfig(config);
        setParams(params);
    }
    
    public ProviderRequest(){
        this(new PageConfig(), new HashMap<String, Serializable>());
    }
    
    /**
     * @return the params
     */
    public Map<String, ? extends Serializable> getParams() {
        return params;
    }
    
    /**
     * @param params the params to set
     */
    public void setParams(Map<String, ? extends Serializable> params) {
        this.params = params;
    }
    
    /**
     * @return the config
     */
    public PageConfig getConfig() {
        return config;
    }
    
    /**
     * @param config the config to set
     */
    public void setConfig(PageConfig config) {
        this.config = config;
    }
}
