/**
 * 
 */
package org.gwings.client.table.pagination;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @author USER
 *
 */
public class PageResult<T extends Serializable> implements Serializable {
    private static final long serialVersionUID = 6961076316645572930L;

    private List<T> results;
    private PageConfig pageConfig;
    
    public PageResult(){
        this(new ArrayList<T>(), 0, 0);
    }
    
    public PageResult(List<T> results, Integer first, Integer last){
        this(results, new PageConfig(first, last, results.size()));
    }
    
    public PageResult(List<T> results, PageConfig page){
        setResults(results);
        setPageConfig(page);
    }

    /**
     * @return the page
     */
    public PageConfig getPageConfig() {
        return pageConfig;
    }
    
    /**
     * @param page the page to set
     */
    public void setPageConfig(PageConfig page) {
        this.pageConfig = page;
    }

    /**
     * @return the results
     */
    public List<T> getResults() {
        return results;
    }
    
    /**
     * @param results the results to set
     */
    public void setResults(List<T> results) {
        this.results = results;
    }

    /**
     * @return
     * @see org.gwings.client.table.pagination.PageConfig#getFirst()
     */
    public Integer getFirst() {
        return pageConfig.getFirst();
    }

    /**
     * @return
     * @see org.gwings.client.table.pagination.PageConfig#getLast()
     */
    public Integer getLast() {
        return pageConfig.getLast();
    }

    /**
     * @return
     * @see org.gwings.client.table.pagination.PageConfig#getSize()
     */
    public Integer getSize() {
        return pageConfig.getSize();
    }

    /**
     * @param first
     * @see org.gwings.client.table.pagination.PageConfig#setFirst(java.lang.Integer)
     */
    public void setFirst(Integer first) {
        pageConfig.setFirst(first);
    }

    /**
     * @param last
     * @see org.gwings.client.table.pagination.PageConfig#setLast(java.lang.Integer)
     */
    public void setLast(Integer last) {
        pageConfig.setLast(last);
    }

    /**
     * @param size
     * @see org.gwings.client.table.pagination.PageConfig#setSize(java.lang.Integer)
     */
    public void setSize(Integer size) {
        pageConfig.setSize(size);
    }
}
