/**
 * 
 */
package org.gwings.client.table.pagination.model;

import java.util.HashMap;
import java.util.Map;


/**
 * @author USER
 *
 */
public class ProviderRequest {
    private Map<String, String> params;
    private PageConfig config;
    
    public ProviderRequest(PageConfig config, Map<String, String> params) {
        super();
        setConfig(config);
        setParams(params);
    }
    
    public ProviderRequest(){
        this(new PageConfig(), new HashMap<String, String>());
    }
    
    /**
     * @return the params
     */
    public Map<String, String> getParams() {
        return params;
    }
    
    /**
     * @param params the params to set
     */
    public void setParams(Map<String, String> params) {
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
