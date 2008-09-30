/**
 * 
 */
package org.gwings.client.table.pagination.model;

import java.io.Serializable;

import org.gwings.client.table.pagination.observer.PagerEvent;
import org.gwings.client.table.pagination.observer.PagerListener;
import org.gwings.client.table.pagination.observer.PagerListenerSupport;

/**
 * @author USER
 */
public class Pager<T> implements Serializable {

    private static final long serialVersionUID = -6567047121711989649L;

    private DataProvider<T> provider;
    private PageConfig pageConfig;
    private Page<T> currentPage;

    private PagerListenerSupport<T> support;

    public Pager() {
        setProvider(null);
        setPageConfig(new PageConfig());
        setCurrentPage(null);
        support = new PagerListenerSupport<T>();
    }

    /**
     * @param listener
     * @see org.gwings.client.table.pagination.observer.PagerListenerSupport#addPagerListener(org.gwings.client.table.pagination.observer.PagerListener)
     */
    public void addPagerListener(PagerListener<T> listener) {
        support.addPagerListener(listener);
    }

    /**
     * @param listener
     * @see org.gwings.client.table.pagination.observer.PagerListenerSupport#removePagerListener(org.gwings.client.table.pagination.observer.PagerListener)
     */
    public void removePagerListener(PagerListener<T> listener) {
        support.removePagerListener(listener);
    }

    public Integer currentPageIndex() {
        Integer finish = pageConfig.getFinish();
        
        if(finish == null || currentPage == null){
            return -1;
        }
        return finish / getPageSize();
    }
    
    public Integer getTotalPages() throws Exception {
        Integer totalAvailable = getRowsAvailable();
        Integer rest = totalAvailable % getPageSize(); 
        
        if(totalAvailable == null || totalAvailable < 0){
            return -1;
        }
        
        int pages = totalAvailable / getPageSize();
        pages += (rest > 0 ? 1 : 0);
        
        return pages;
    }

    public void nextPage() throws Exception{
        Integer reference = pageConfig.getFinish();
        Integer totalAvailable = 0;

        if(currentPage == null){
            totalAvailable = provider.fetchSize();
            reference = pageConfig.getStart();
        }
        else{
            totalAvailable = pageConfig.getTotalAvailable();
        }
        
        Integer start = reference;
        Integer finish = start + getPageSize();
        
        if(finish > totalAvailable){
            throw new Exception("Invalid interval! Finish greater than total.");
        }
        
        PageConfig nextConfig = new PageConfig();
        nextConfig.setStart(start);
        nextConfig.setFinish(finish);
        nextConfig.setTotalAvailable(totalAvailable);
        
        setCurrentPage(provider.fetchData(nextConfig));
        setPageConfig(nextConfig);
        
        support.fireNextPage(makePagerEvent());
    }

    public void previousPage() throws Exception{
        Integer start = pageConfig.getStart();
        Integer available = pageConfig.getTotalAvailable();
        
        Integer finish = pageConfig.getStart();
        start = start - getPageSize();
        
        if(start < 0){
            throw new Exception("Invalid interval! Start less than zero!");
        }
        
        PageConfig previousConfig = new PageConfig();
        previousConfig.setFinish(finish);
        previousConfig.setStart(start);
        previousConfig.setTotalAvailable(available);
        
        setCurrentPage(provider.fetchData(previousConfig));
        setPageConfig(previousConfig);
        
        support.firePreviousPage(makePagerEvent());
    }

    public void firstPage() throws Exception{
        Integer available = getRowsAvailable();
        
        PageConfig firstConfig = new PageConfig();
        firstConfig.setStart(0);
        firstConfig.setFinish(getPageSize());
        firstConfig.setTotalAvailable(available);
        
        setPageConfig(firstConfig);
        setCurrentPage(provider.fetchData(firstConfig));
        
        support.fireFirstPage(makePagerEvent());
    }

    public void lastPage() throws Exception{
        Integer available = getRowsAvailable();
        
        PageConfig lastConfig = new PageConfig();
        lastConfig.setFinish(available);
        lastConfig.setStart(available - getPageSize());
        lastConfig.setTotalAvailable(available);
        
        setPageConfig(lastConfig);
        setCurrentPage(provider.fetchData(lastConfig));
        
        support.fireLastPage(makePagerEvent());
    }

    public void goToPage(Integer page) throws Exception{
        Integer rowsAvailable = getRowsAvailable();
        
        if(page > getTotalPages()){
            throw new Exception("Invalid page number!");
        }
        
        PageConfig config = new PageConfig();
        config.setFinish(page * getPageSize());
        config.setStart(config.getFinish() - getPageSize());
        config.setTotalAvailable(rowsAvailable);
        
        setPageConfig(config);
        setCurrentPage(provider.fetchData(config));
        
        support.firePageChanged(makePagerEvent());
    }

    private Integer getRowsAvailable() throws Exception {
        return (currentPage == null ? provider.fetchSize() : pageConfig.getTotalAvailable());
    }

    /**
     * @return the provider
     */
    public DataProvider<T> getProvider() {
        return provider;
    }

    /**
     * @param provider
     *            the provider to set
     */
    public void setProvider(DataProvider<T> provider) {
        this.provider = provider;
    }

    /**
     * @return the pageConfig
     */
    public PageConfig getPageConfig() {
        return pageConfig;
    }

    /**
     * @param pageConfig
     *            the pageConfig to set
     */
    public void setPageConfig(PageConfig pageConfig) {
        this.pageConfig = pageConfig;
    }

    /**
     * @return the currentPage
     */
    public Page<T> getCurrentPage() {
        return currentPage;
    }

    /**
     * @param currentPage
     *            the currentPage to set
     */
    public void setCurrentPage(Page<T> currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @return the pageSize
     */
    public Integer getPageSize() {
        return pageConfig.getPageSize();
    }
    
    private PagerEvent<T> makePagerEvent() {
        return new PagerEvent<T>(this);
    }
}
