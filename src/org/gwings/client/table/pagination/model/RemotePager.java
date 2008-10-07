package org.gwings.client.table.pagination.model;

import java.io.Serializable;

import org.gwings.client.table.model.Plotable;
import org.gwings.client.table.pagination.observer.RemotePagerEvent;
import org.gwings.client.table.pagination.observer.RemotePagerListener;
import org.gwings.client.table.pagination.observer.RemotePagerListenerSupport;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class RemotePager<T extends Plotable> implements Serializable {

    private static final long serialVersionUID = 7944126422540689040L;

    private AsyncDataProvider<T> asyncProvider;
    private PageConfig pageConfig;
    private Page<T> currentPage;

    private RemotePagerListenerSupport<T> support;

    public RemotePager() {
        setAsyncProvider(new AsyncEmptyProvider<T>());
        setPageConfig(new PageConfig());
        setCurrentPage(null);
        support = new RemotePagerListenerSupport<T>();
    }

    /**
     * @param listener
     * @see org.gwings.client.table.pagination.observer.PagerListenerSupport#addPagerListener(org.gwings.client.table.pagination.observer.PagerListener)
     */
    public void addRemotePagerListener(RemotePagerListener<T> listener) {
        support.addRemotePagerListener(listener);
    }

    /**
     * @param listener
     * @see org.gwings.client.table.pagination.observer.PagerListenerSupport#removePagerListener(org.gwings.client.table.pagination.observer.PagerListener)
     */
    public void removeRemotePagerListener(RemotePagerListener<T> listener) {
        support.removeRemotePagerListener(listener);
    }

    public void nextPage() throws Exception {
        try {
            moveTo(pageConfig.currentPageIndex() + 1, new AsyncCallback<Page<T>>() {
                public void onSuccess(Page<T> result) {
                    support.fireNextPageArrived(makePagerEvent());
                }
                public void onFailure(Throwable caught) {
                    
                }
            });
            support.fireNextPageRequest(makePagerEvent());
        }
        catch (Exception e) {
            throw new Exception("Can't go to a next page. Pager already at the end.");
        }
    }

    public void previousPage() throws Exception {
        try {
            moveTo(pageConfig.currentPageIndex() - 1, new AsyncCallback<Page<T>>() {
                public void onSuccess(Page<T> result) {
                    support.firePreviousPageArrived(makePagerEvent());
                }
                public void onFailure(Throwable caught) {
                    
                }
            });
            support.firePreviousPageRequest(makePagerEvent());
        }
        catch (Exception e) {
            throw new Exception("Can't go to a previous page. Pager already at the begining.");
        }
    }

    public void firstPage() throws Exception {
        moveTo(1, new AsyncCallback<Page<T>>() {
            public void onFailure(Throwable caught) {
            }
            public void onSuccess(Page<T> result) {
                support.fireFirstPageArrived(makePagerEvent());
            }
        });
        support.fireFirstPageRequest(makePagerEvent());
    }

    public void lastPage() throws Exception {
        moveTo(pageConfig.getTotalPages(), new AsyncCallback<Page<T>>() {
            public void onFailure(Throwable caught) {
            }
            public void onSuccess(Page<T> result) {
                support.fireLastPageRequest(makePagerEvent());
            }
        });
        support.fireLastPageRequest(makePagerEvent());
    }

    public void goToPage(Integer page) throws Exception {
        moveTo(page, new AsyncCallback<Page<T>>() {
            public void onFailure(Throwable caught) {
            }
            public void onSuccess(Page<T> result) {
                support.firePageChanged(makePagerEvent());
            }
        });
        support.firePageChangeRequest(makePagerEvent());
    }

    private void moveTo(Integer page, final AsyncCallback<Page<T>> callback) throws Exception {
        Integer rowsAvailable = pageConfig.getTotalAvailable();

        if (page > pageConfig.getTotalPages() || page <= 0) {
            throw new Exception("Invalid page request!");
        }

        PageConfig config = new PageConfig();
        config.setFinish(page * getPageSize());
        config.setStart(config.getFinish() - getPageSize());
        config.setTotalAvailable(rowsAvailable);

//        asyncProvider.fetchData(config, new AsyncCallback<Page<T>>() {
//            public void onSuccess(Page<T> result) {
//                setCurrentPage(result);
//                callback.onSuccess(result);
//            }
//            public void onFailure(Throwable caught) {
//                Window.alert(caught.getMessage());
//            }
//        });
    }

    /**
     * @return the provider
     */
    public AsyncDataProvider<T> getAsyncProvider() {
        return asyncProvider;
    }

    /**
     * @param provider
     *            the provider to set
     */
    public void setAsyncProvider(AsyncDataProvider<T> provider) {
        this.asyncProvider = provider;
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

    private RemotePagerEvent<T> makePagerEvent() {
        return new RemotePagerEvent<T>(this);
    }
}
