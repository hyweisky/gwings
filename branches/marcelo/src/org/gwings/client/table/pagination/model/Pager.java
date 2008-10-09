/**
 * 
 */
package org.gwings.client.table.pagination.model;

import java.io.Serializable;
import java.util.Map;

import org.gwings.client.table.model.Plotable;
import org.gwings.client.table.pagination.observer.PagerEvent;
import org.gwings.client.table.pagination.observer.PagerReadyListener;
import org.gwings.client.table.pagination.observer.PagerReadySupport;
import org.gwings.client.table.pagination.observer.PagerRequestListener;
import org.gwings.client.table.pagination.observer.PagerRequestSupport;

/**
 * @author USER
 */
public class Pager<T extends Plotable> implements Serializable {

    private static final long serialVersionUID = -6567047121711989649L;

    private class ProxyCallback implements ProviderCallback<T> {
        private ProviderCallback<T> callback;
        
        public ProxyCallback(ProviderCallback<T> callback) {
            this.callback = callback;
        }

        public void dataFetched(ProviderRequest request, ProviderResponse<T> response) {
            
            setCurrentPage(response.getPage());
            callback.dataFetched(request, response);
            
        }

    }

    private DataProvider<T> provider;
    private PageConfig pageConfig;
    private Page<T> currentPage;
    private Map<String, String> params;

    private PagerRequestSupport<T> requestSupport;
    private PagerReadySupport<T> readySupport;

    public Pager() {
        setProvider(new EmptyProvider<T>());
        setPageConfig(new PageConfig());
        setCurrentPage(null);
        requestSupport = new PagerRequestSupport<T>();
        readySupport = new PagerReadySupport<T>();
    }

    public void addPagerRequestListener(PagerRequestListener<T> listener) {
        requestSupport.addPagerRequestListener(listener);
    }

    public void removePagerRequestListener(PagerRequestListener<T> listener) {
        requestSupport.removePagerRequestListener(listener);
    }

    public void addPagerReadyListener(PagerReadyListener<T> listener) {
        readySupport.addPagerReadyListener(listener);
    }

    public void removePagerReadyListener(PagerReadyListener<T> listener) {
        readySupport.addPagerReadyListener(listener);
    }

    public void nextPage() throws Exception {
        try {
            int nextPage = currentPageIndex() + 1;
            requestSupport.fireNextPageRequest(makePagerEvent());
            moveTo(nextPage, new ProviderCallback<T>() {

                public void dataFetched(ProviderRequest request, ProviderResponse<T> response) {
                    
                    readySupport.fireNextPageReady(makePagerEvent());
                    
                }
            });
        }
        catch (Exception e) {
            throw new Exception("Can't go to a next page. Pager already at the end.");
        }
    }

    public void previousPage() throws Exception {
        try {
            int previousPage = currentPageIndex() - 1;
            requestSupport.firePreviousPageRequest(makePagerEvent());
            moveTo(previousPage, new ProviderCallback<T>() {

                public void dataFetched(ProviderRequest request, ProviderResponse<T> response) {

                    readySupport.firePreviousPageReady(makePagerEvent());
                    
                }
            });
        }
        catch (Exception e) {
            throw new Exception("Can't go to a previous page. Pager already at the begining.");
        }
    }

    public void firstPage() throws Exception {
        int firstPage = 1;
        requestSupport.fireFirstPageRequest(makePagerEvent());
        moveTo(firstPage, new ProviderCallback<T>() {

            public void dataFetched(ProviderRequest request, ProviderResponse<T> response) {

                readySupport.fireFirstPageReady(makePagerEvent());
                
            }
        });
    }

    public void lastPage() throws Exception {
        Integer lastPage = getTotalPages();
        requestSupport.fireLastPageRequest(makePagerEvent());
        moveTo(lastPage, new ProviderCallback<T>() {

            public void dataFetched(ProviderRequest request, ProviderResponse<T> response) {

                readySupport.fireLastPageReady(makePagerEvent());
                
            }
        });
    }

    public void goToPage(Integer page) throws Exception {
        requestSupport.firePageChangeRequest(makePagerEvent());
        moveTo(page, new ProviderCallback<T>() {

            public void dataFetched(ProviderRequest request, ProviderResponse<T> response) {

                readySupport.firePageChangeReady(makePagerEvent());
                
            }
        });
    }

    private void moveTo(Integer page, ProviderCallback<T> callback) throws Exception {
        int finish = page * getPageSize();
        int start = finish - getPageSize();
        
        if(start < 0 || page > getTotalPages()) {
            throw new Exception("Invalid page request!");
        }
        
        PageConfig config = new PageConfig();
        config.setFinish(finish);
        config.setStart(start);
        config.setPageIndex(page);

        ProviderRequest request = new ProviderRequest();
        request.setConfig(config);
        request.setParams(getParams());

        provider.fetchData(request, new ProxyCallback(callback));
    }

    public void fetchSize() {
        Integer totalAvailable = pageConfig.getTotalAvailable();
        if(totalAvailable == null || totalAvailable < 0){
            
            ProviderRequest request = new ProviderRequest();
            request.setConfig(new PageConfig());
            request.setParams(getParams());
            
            provider.fetchData(request, new ProviderCallback<T>() {
                public void dataFetched(ProviderRequest request, ProviderResponse<T> response) {
                    
                    Page<T> page = response.getPage();
                    PageConfig config = page.getConfig();
                    Integer totalAvailable = config.getTotalAvailable();
                    getPageConfig().setTotalAvailable(totalAvailable);
                    
                    readySupport.firePageSizeReady(makePagerEvent());
                }
            });
        }
    }

    public Integer currentPageIndex() {
        return pageConfig.getPageIndex();
    }

    public Integer getTotalPages() throws Exception {
        return pageConfig.getTotalPages();
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
        if (currentPage != null) {
            setPageConfig(currentPage.getConfig());
        }
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

    /**
     * @return the params
     */
    public Map<String, String> getParams() {
        return params;
    }

    /**
     * @param params
     *            the params to set
     */
    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}