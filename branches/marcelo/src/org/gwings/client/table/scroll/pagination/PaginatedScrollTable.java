/**
 * 
 */
package org.gwings.client.table.scroll.pagination;

import java.util.List;

import org.gwings.client.table.model.Plotable;
import org.gwings.client.table.pagination.model.DataProvider;
import org.gwings.client.table.pagination.model.Pager;
import org.gwings.client.table.pagination.observer.PagerEvent;
import org.gwings.client.table.pagination.observer.PagerReadyListener;
import org.gwings.client.table.pagination.view.PaginationBar;
import org.gwings.client.table.scroll.ResizePolicy;
import org.gwings.client.table.scroll.ScrollPolicy;
import org.gwings.client.table.scroll.ScrollTable;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author USER
 */
public class PaginatedScrollTable<T extends Plotable> extends ScrollTable<T> implements PagerReadyListener<T> {

    private PaginationBar<T> paginationBar;
    private DivElement paginationWrapper;
    
    public PaginatedScrollTable(){
        super();
        init();
        setScrollPolicy(ScrollPolicy.DISABLED);
    }

    private void init() {
        paginationWrapper = super.createWrapper("paginationWrapper");
        paginationBar = new PaginationBar<T>(this);
        setPager(paginationBar.getPager());
        adoptTable(paginationBar, paginationWrapper, this.getWidgetCount());
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
     * Adjust all column widths so they take up the maximum amount of space
     * without needing a horizontal scroll bar. The distribution will be
     * proportional to the current width of each column.
     * 
     * The {@link ScrollTable} must be visible on the page for this method to
     * work.
     */
    public void fillWidth() {
        // Calculate how much room we have to work with
        int clientWidth = -1;
        if (scrollPolicy == ScrollPolicy.BOTH) {
            dataWrapper.getStyle().setProperty("overflow", "scroll");
            clientWidth = dataWrapper.getPropertyInt("clientWidth") - 1;
            dataWrapper.getStyle().setProperty("overflow", "auto");
        } else {
            clientWidth = dataWrapper.getPropertyInt("clientWidth");
        }
        if (clientWidth <= 0) {
            return;
        }
        clientWidth = Math.max(clientWidth, minWidth);
        int diff = clientWidth - ((Widget) dataTable).getOffsetWidth();
        
        // Temporarily set resize policy to unconstrained
        ResizePolicy tempResizePolicy = getResizePolicy();
        resizePolicy = ResizePolicy.UNCONSTRAINED;

        // Calculate the total width of the columns that aren't guaranteed
        int totalWidth = 0;
        int numColumns = dataTable.getColumnCount();
        int[] colWidths = new int[numColumns];
        for (int i = 0; i < numColumns; i++) {
            if (isColumnWidthGuaranteed(i)) {
                colWidths[i] = 0;
            } else {
                colWidths[i] = dataTable.getColumnWidth(i);
            }
            totalWidth += colWidths[i];
        }
        
        ScrollPolicy policy = getScrollPolicy();
        scrollPolicy = ScrollPolicy.DISABLED;

        // Distribute the difference across all columns, weighted by current size
        int remainingDiff = diff;
        for (int i = 0; i < numColumns; i++) {
            if (colWidths[i] > 0) {
                int colDiff = (int) (diff * (colWidths[i] / (float) totalWidth));
                colDiff = setColumnWidth(i, colWidths[i] + colDiff) - colWidths[i];
                remainingDiff -= colDiff;
                colWidths[i] += colDiff;
            }
        }

        // Spread out remaining diff however possible
        if (remainingDiff != 0) {
            for (int i = 0; i < numColumns && remainingDiff != 0; i++) {
                if (!isColumnWidthGuaranteed(i)) {
                    int colWidth = setColumnWidth(i, colWidths[i] + remainingDiff);
                    remainingDiff -= (colWidth - colWidths[i]);
                }
            }
        }

        // Reset the resize policy
        resizePolicy = tempResizePolicy;
        scrollPolicy = policy;
        scrollTables(true);
        DeferredCommand.addCommand(new Command() {
            public void execute() {
                paginationWrapper.setPropertyInt("width", headerTable.getOffsetWidth());
                paginationWrapper.getStyle().setPropertyPx("width", headerTable.getOffsetWidth());
            }
        });
    }

    @Override
    public void setPixelWidth(int pixels) {
    	super.setPixelWidth(pixels);
    	paginationWrapper.setPropertyInt("width", pixels+30);
    	paginationWrapper.getStyle().setPropertyPx("width", pixels+30);
    }
    /**
     * @return
     * @see org.gwings.client.table.pagination.view.PaginationBar#getPager()
     */
    public Pager<T> getPager() {
        return paginationBar.getPager();
    }

    /**
     * @param pager
     * @see org.gwings.client.table.pagination.view.PaginationBar#setPager(org.gwings.client.table.pagination.model.Pager)
     */
    public void setPager(Pager<T> pager) {
        if(getPager() != null){
            getPager().removePagerReadyListener(this);
        }
        paginationBar.setPager(pager);
        getPager().addPagerReadyListener(this);
    }

    /**
     * @return the provider
     */
    public DataProvider<T> getProvider() {
        return paginationBar.getProvider();
    }

    /**
     * @param provider the provider to set
     */
    public void setProvider(DataProvider<T> provider) {
        paginationBar.setProvider(provider);
    }
    
    /**
     * @param evt
     * @see org.gwings.client.table.pagination.view.PaginationBar#firstPage(org.gwings.client.table.pagination.observer.PagerEvent)
     */
    public void firstPage(PagerEvent<T> evt) {
        updateLines(evt);
    }
    
    private void updateLines(final PagerEvent<T> evt) {
        DeferredCommand.addCommand(new Command() {
            public void execute() {
                DeferredCommand.addPause();
                List<T> items = evt.getPager().getCurrentPage().getItems();
                getTableModel().clearRows();
                getTableModel().setLines(items);
                paginationBar.stopBusy();
            }
        });
    }

    public void firstPageReady(PagerEvent<T> evt) {
        updateLines(evt);
    }

    public void lastPageReady(PagerEvent<T> evt) {
        updateLines(evt);
    }

    public void nextPageReady(PagerEvent<T> evt) {
        updateLines(evt);
    }

    public void pageChangeReady(PagerEvent<T> evt) {
        updateLines(evt);
    }

    public void previousPageReady(PagerEvent<T> evt) {
        updateLines(evt);
    }
}