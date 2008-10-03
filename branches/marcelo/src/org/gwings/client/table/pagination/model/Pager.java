/**
 * 
 */
package org.gwings.client.table.pagination.model;

import java.io.Serializable;
import java.util.ArrayList;

import org.gwings.client.table.pagination.observer.PagerEvent;
import org.gwings.client.table.pagination.observer.PagerListener;
import org.gwings.client.table.pagination.observer.PagerListenerSupport;

/**
 * @author USER
 */
public class Pager<T> implements Serializable {

    private final class EmptyProvider implements DataProvider<T> {

        private static final long serialVersionUID = 4404147801907185267L;

        public Page<T> fetchData(PageConfig config) throws Exception {
            return new Page<T>(new ArrayList<T>(), config);
        }

        public Integer fetchSize() throws Exception {
            return 0;
        }
    }

    private static final long serialVersionUID = -6567047121711989649L;

    private DataProvider<T> provider;
    private PageConfig pageConfig;
    private Page<T> currentPage;

    private PagerListenerSupport<T> support;

    public Pager() {
        setProvider(new EmptyProvider());
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

        if (finish == null || currentPage == null) {
            return 0;
        }
        return finish / getPageSize();
    }

    public Integer getTotalPages() throws Exception {
        Integer totalAvailable = getRowsAvailable();
        if (totalAvailable == null || totalAvailable < 0 || getPageSize() == 0) {
            return 0;
        }

        Integer rest = totalAvailable % getPageSize();
        int pages = totalAvailable / getPageSize();

        pages += (rest > 0 ? 1 : 0);

        return pages;
    }

    public void nextPage() throws Exception {
        try {
            moveTo(currentPageIndex() + 1);
        }
        catch (Exception e) {
            throw new Exception("Can't go to a next page. Pager already at the end.");
        }
        support.fireNextPage(makePagerEvent());
    }

    public void previousPage() throws Exception {
        try {
            moveTo(currentPageIndex()-1);
        }
        catch (Exception e) {
            throw new Exception("Can't go to a previous page. Pager already at the begining.");
        }
        support.firePreviousPage(makePagerEvent());
    }

    public void firstPage() throws Exception {
        moveTo(1);
        support.fireFirstPage(makePagerEvent());
    }

    public void lastPage() throws Exception {
        moveTo(getTotalPages());
        support.fireLastPage(makePagerEvent());
    }

    public void goToPage(Integer page) throws Exception {
        moveTo(page);
        support.firePageChanged(makePagerEvent());
    }

    private void moveTo(Integer page) throws Exception {
        Integer rowsAvailable = getRowsAvailable();

        if (page > getTotalPages() || page <= 0) {
            throw new Exception("Invalid page request!");
        }
        
        PageConfig config = new PageConfig();
        config.setFinish(page * getPageSize());
        config.setStart(config.getFinish() - getPageSize());
        config.setTotalAvailable(rowsAvailable);

        setCurrentPage(provider.fetchData(config));
    }

    private Integer getRowsAvailable() throws Exception {
        if (currentPage == null) {
            assert (provider != null);
            return provider.fetchSize();
        }
        assert (pageConfig != null);
        return pageConfig.getTotalAvailable();
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
        if(currentPage != null){
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
}