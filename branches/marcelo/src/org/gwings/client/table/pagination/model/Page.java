/**
 * 
 */
package org.gwings.client.table.pagination.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author USER
 */
public class Page<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = -4535920819699994229L;

    private List<T> items;
    private PageConfig config;

    public Page() {
        this(new ArrayList<T>(), new PageConfig());
    }

    public Page(List<T> myItems, PageConfig config) {
        setItems(myItems);
        setConfig(config);
    }

    /**
     * @return the items
     */
    public List<T> getItems() {
        return items;
    }

    /**
     * @param items
     *            the items to set
     */
    public void setItems(List<T> items) {
        this.items = items;
    }

    /**
     * @return the config
     */
    public PageConfig getConfig() {
        return config;
    }

    /**
     * @param config
     *            the config to set
     */
    public void setConfig(PageConfig config) {
        this.config = config;
    }
}
