/**
 * 
 */
package org.gwings.client.table.scroll.pagination;

import org.gwings.client.table.Plotable;
import org.gwings.client.table.pagination.model.DataProvider;
import org.gwings.client.table.pagination.model.Pager;
import org.gwings.client.table.pagination.observer.PagerEvent;
import org.gwings.client.table.pagination.view.PaginationBar;
import org.gwings.client.table.scroll.ScrollPolicy;
import org.gwings.client.table.scroll.ScrollTable;

import com.google.gwt.dom.client.DivElement;

/**
 * @author USER
 */
public class PaginatedScrollTable<T extends Plotable> extends ScrollTable<T> {

    private PaginationBar<T> paginationBar;
    private DivElement paginationWrapper;
    
    public PaginatedScrollTable(){
        super();
        init();
        setScrollPolicy(ScrollPolicy.DISABLED);
    }

    private void init() {
        paginationWrapper = super.createWrapper("paginationWrapper");
        paginationBar = new PaginationBar<T>();
        
        super.adoptTable(paginationBar, paginationWrapper, 4);
    }
    
    /**
     * Helper method that actually performs the vertical resizing.
     */
    protected void resizeTablesVerticallyNow() {
        // Force browser to redraw
        if (scrollPolicy == ScrollPolicy.DISABLED) {
            dataWrapper.getStyle().setProperty("overflow", "auto");
            dataWrapper.getStyle().setProperty("overflow", "");
            return;
        } 
        if (scrollPolicy == ScrollPolicy.HORIZONTAL) {
            dataWrapper.getStyle().setProperty("overflow", "hidden");
            dataWrapper.getStyle().setProperty("overflow", "auto");
            scrollTables(true);
            return;
        }

        // Give the data wrapper all remaining height
        int totalHeight = getElement().getPropertyInt("clientHeight");
        totalHeight = totalHeight > 0 ? totalHeight : getElement().getPropertyInt("offsetHeight");
        int headerHeight = headerTable.getOffsetHeight();
        int pagingHeight = paginationBar.getOffsetHeight();
        int footerHeight = 0;
        
        if (footerTable != null) {
            footerHeight = footerTable.getOffsetHeight();
        }
        paginationWrapper.getStyle().setPropertyPx("height", pagingHeight);
        headerWrapper.getStyle().setPropertyPx("height", headerHeight);
        if (footerWrapper != null) {
            footerWrapper.getStyle().setPropertyPx("height", footerHeight);
        }
        int height = totalHeight - headerHeight - footerHeight - pagingHeight;
        dataWrapper.getStyle().setPropertyPx("height", (height > 0 ? height : 100));
        dataWrapper.getStyle().setProperty("overflow", "hidden");
        dataWrapper.getStyle().setProperty("overflow", "auto");
        scrollTables(true);
    }
    
    /**
     * @param evt
     * @see org.gwings.client.table.pagination.view.PaginationBar#firstPage(org.gwings.client.table.pagination.observer.PagerEvent)
     */
    public void firstPage(PagerEvent<T> evt) {
        paginationBar.firstPage(evt);
    }

    /**
     * @return
     * @see org.gwings.client.table.pagination.view.PaginationBar#getPager()
     */
    public Pager<T> getPager() {
        return paginationBar.getPager();
    }

    /**
     * @param evt
     * @see org.gwings.client.table.pagination.view.PaginationBar#lastPage(org.gwings.client.table.pagination.observer.PagerEvent)
     */
    public void lastPage(PagerEvent<T> evt) {
        paginationBar.lastPage(evt);
    }

    /**
     * @param evt
     * @see org.gwings.client.table.pagination.view.PaginationBar#nextPage(org.gwings.client.table.pagination.observer.PagerEvent)
     */
    public void nextPage(PagerEvent<T> evt) {
        paginationBar.nextPage(evt);
    }

    /**
     * @param evt
     * @see org.gwings.client.table.pagination.view.PaginationBar#pageChanged(org.gwings.client.table.pagination.observer.PagerEvent)
     */
    public void pageChanged(PagerEvent<T> evt) {
        paginationBar.pageChanged(evt);
    }

    /**
     * @param evt
     * @see org.gwings.client.table.pagination.view.PaginationBar#previousPage(org.gwings.client.table.pagination.observer.PagerEvent)
     */
    public void previousPage(PagerEvent<T> evt) {
        paginationBar.previousPage(evt);
    }


    /**
     * @param pager
     * @see org.gwings.client.table.pagination.view.PaginationBar#setPager(org.gwings.client.table.pagination.model.Pager)
     */
    public void setPager(Pager<T> pager) {
        paginationBar.setPager(pager);
    }

    
    /**
     * @return the provider
     */
    public DataProvider<T> getProvider() {
        return paginationBar.getPager().getProvider();
    }

    
    /**
     * @param provider the provider to set
     */
    public void setProvider(DataProvider<T> provider) {
        paginationBar.getPager().setProvider(provider);
    }


}