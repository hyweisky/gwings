/**
 * 
 */
package org.gwings.test.client.table.pagination;

import java.util.ArrayList;
import java.util.List;

import org.gwings.client.table.model.Plotable;
import org.gwings.client.table.pagination.model.DataProvider;
import org.gwings.client.table.pagination.model.Page;
import org.gwings.client.table.pagination.model.PageConfig;
import org.gwings.client.table.pagination.model.Pager;
import org.gwings.client.table.pagination.model.ProviderCallback;
import org.gwings.client.table.pagination.model.ProviderRequest;
import org.gwings.client.table.pagination.model.ProviderResponse;

import com.google.gwt.junit.client.GWTTestCase;

/**
 * @author USER
 */
public class PagerTest extends GWTTestCase {

    private class PagerItem implements Plotable {

        private static final long serialVersionUID = -3095561441687823948L;
        private String item;

        public PagerItem(String item) {
            this.item = item;
        }

        public Object[] plot() {
            return new Object[] { item };
        }
    }

    private class PagerItemProvider implements DataProvider<PagerItem> {

        private static final long serialVersionUID = -2309528292227765319L;
        private List<PagerItem> fullData;

        public PagerItemProvider() {
            makeFullData();
        }

        private void makeFullData() {
            fullData = new ArrayList<PagerItem>();
            for (int i = 0; i < 301; i++) {
                fullData.add(new PagerItem("data " + i));
            }
        }

        public void fetchData(ProviderRequest request,
                              ProviderCallback<PagerItem> callback) {

            PageConfig config = request.getConfig();
            Integer start = config.getStart();
            Integer finish = config.getFinish();
            config.setTotalAvailable(fullData.size());

            List<PagerItem> items = new ArrayList<PagerItem>();
            for (int i = start; i < finish && i < fullData.size(); i++) {
                items.add(fullData.get(i));
            }

            ProviderResponse<PagerItem> response = 
                                new ProviderResponse<PagerItem>(items, config);
            
            callback.dataFetched(request, response);
        }
    }

    private Pager<PagerItem> pager;

    @Override
    protected void gwtSetUp() throws Exception {
        pager = new Pager<PagerItem>();
        pager.setProvider(new PagerItemProvider());
    }

    /*
     * (non-Javadoc)
     * @see com.google.gwt.junit.client.GWTTestCase#getModuleName()
     */
    @Override
    public String getModuleName() {
        return "org.gwings.test.GWingsTest";
    }

    public void testGetTotalPages() {
        init();

        assertEquals(new Integer(10), pager.getPageSize());
        assertEquals(pager.getPageConfig().getPageSize(), pager.getPageSize());

        try {
            pager.nextPage();
            assertEquals(new Integer(31), pager.getTotalPages());
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testGetCurrentPage() {
        init();

        assertNull(pager.getCurrentPage());
        assertEquals(new Integer(0), pager.currentPageIndex());

        PageConfig pageConfig = pager.getPageConfig();
        pageConfig.setStart(0);
        pageConfig.setFinish(10);
        
        assertEquals(new Integer(0), pager.currentPageIndex());
        
        try {
            pager.nextPage();
        }
        catch (Exception e) {
            fail(e.getMessage());
        }

        assertNotNull(pager.getCurrentPage());
        assertEquals(new Integer(1), pager.currentPageIndex());
    }

    public void testNextPage() {
        init();
        
        try {
            pager.nextPage();
        }
        catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals(new Integer(0), pager.getPageConfig().getStart());
        assertEquals(new Integer(10), pager.getPageConfig().getFinish());
        assertEquals(new Integer(301), pager.getPageConfig().getTotalAvailable());

        Page<PagerItem> currentPage = pager.getCurrentPage();
        assertNotNull(currentPage);

        List<PagerItem> items = currentPage.getItems();
        assertNotNull(items);
        assertEquals(10, items.size());

        dataTest();

        try {
            pager.nextPage();
        }
        catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals(new Integer(10), pager.getPageConfig().getStart());
        assertEquals(new Integer(20), pager.getPageConfig().getFinish());
        assertEquals(new Integer(301), pager.getPageConfig().getTotalAvailable());

        currentPage = pager.getCurrentPage();
        assertNotNull(currentPage);

        items = currentPage.getItems();
        assertNotNull(items);
        assertEquals(10, items.size());

        try {
            pager.lastPage();
        }
        catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals(new Integer(31), pager.currentPageIndex());
        assertEquals(new Integer(300), pager.getPageConfig().getStart());
        assertEquals(new Integer(310), pager.getPageConfig().getFinish());
        assertEquals(new Integer(301), pager.getPageConfig().getTotalAvailable());

        try {
            pager.nextPage();
            fail();
        }
        catch (Exception e) {
            assertEquals("Can't go to a next page. Pager already at the end.", e.getMessage());
        }
    }

    public void testFirstPage() {
        init();

        try {
            pager.nextPage();
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
        assertEquals(new Integer(1), pager.currentPageIndex());
        assertEquals(new Integer(0), pager.getPageConfig().getStart());
        assertEquals(new Integer(10), pager.getPageConfig().getFinish());
        assertEquals(new Integer(301), pager.getPageConfig().getTotalAvailable());

        Page<PagerItem> currentPage = pager.getCurrentPage();
        assertNotNull(currentPage);

        List<PagerItem> items = currentPage.getItems();
        assertNotNull(items);
        assertEquals(10, items.size());

        dataTest();

        try {
            pager.nextPage();
        }
        catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals(new Integer(2), pager.currentPageIndex());
        assertEquals(new Integer(10), pager.getPageConfig().getStart());
        assertEquals(new Integer(20), pager.getPageConfig().getFinish());
        assertEquals(new Integer(301), pager.getPageConfig().getTotalAvailable());

        currentPage = pager.getCurrentPage();
        assertNotNull(currentPage);

        items = currentPage.getItems();
        assertNotNull(items);
        assertEquals(10, items.size());

        dataTest();

        try {
            pager.firstPage();
        }
        catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals(new Integer(1), pager.currentPageIndex());
        assertEquals(new Integer(0), pager.getPageConfig().getStart());
        assertEquals(new Integer(10), pager.getPageConfig().getFinish());
        assertEquals(new Integer(301), pager.getPageConfig().getTotalAvailable());
    }

    public void testPreviousPage() {
        init();

        try {
            pager.nextPage();
            pager.nextPage();
        }
        catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals(new Integer(2), pager.currentPageIndex());
        assertEquals(new Integer(10), pager.getPageConfig().getStart());
        assertEquals(new Integer(20), pager.getPageConfig().getFinish());
        assertEquals(new Integer(301), pager.getPageConfig().getTotalAvailable());

        try {
            pager.previousPage();
        }
        catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals(new Integer(1), pager.currentPageIndex());
        assertEquals(new Integer(0), pager.getPageConfig().getStart());
        assertEquals(new Integer(10), pager.getPageConfig().getFinish());
        assertEquals(new Integer(301), pager.getPageConfig().getTotalAvailable());

        try {
            pager.firstPage();
        }
        catch (Exception e) {
            fail(e.getMessage());
        }

        assertEquals(new Integer(1), pager.currentPageIndex());
        assertEquals(new Integer(0), pager.getPageConfig().getStart());
        assertEquals(new Integer(10), pager.getPageConfig().getFinish());
        assertEquals(new Integer(301), pager.getPageConfig().getTotalAvailable());

        try {
            pager.previousPage();
            fail();
        }
        catch (Exception e) {
            assertEquals("Can't go to a previous page. Pager already at the begining.", e.getMessage());
        }
    }

    public void testLastPage() {
        init();

        assertEquals(new Integer(0), pager.currentPageIndex());
        
        PageConfig pageConfig = pager.getPageConfig();
        assertNotNull(pageConfig);
        assertEquals(new Integer(0), pageConfig.getStart());
        assertEquals(new Integer(10), pageConfig.getFinish());
        assertNotNull(pageConfig.getTotalAvailable());

        try {
            pager.lastPage();
        }
        catch (Exception e) {
            fail(e.getMessage());
        }

        assertNotNull(pager.getCurrentPage());

        assertEquals(new Integer(31), pager.currentPageIndex());
        
        pageConfig = pager.getPageConfig();
        assertNotNull(pageConfig);
        assertEquals(new Integer(300), pageConfig.getStart());
        assertEquals(new Integer(310), pageConfig.getFinish());
        assertEquals(new Integer(301), pageConfig.getTotalAvailable());

        dataTest();
    }

    public void testGoToPage() {
        init();

        assertNull(pager.getCurrentPage());
        assertEquals(new Integer(0), pager.currentPageIndex());
        assertEquals(new Integer(0), pager.getPageConfig().getStart());
        assertEquals(new Integer(10), pager.getPageConfig().getFinish());
        assertEquals(new Integer(10), pager.getPageSize());
        assertNotNull(pager.getPageConfig().getTotalAvailable());

        try {
            pager.goToPage(15);
        }
        catch (Exception e) {
            fail(e.getMessage());
        }

        try {
            assertEquals(new Integer(31), pager.getTotalPages());
        }
        catch (Exception e1) {
            fail(e1.getMessage());
        }

        assertEquals(new Integer(15), pager.currentPageIndex());
        assertEquals(new Integer(140), pager.getPageConfig().getStart());
        assertEquals(new Integer(150), pager.getPageConfig().getFinish());
        assertEquals(new Integer(301), pager.getPageConfig().getTotalAvailable());

        dataTest();

        try {
            pager.goToPage(50);
            fail();
        }
        catch (Exception e) {
            assertEquals("Invalid page request!", e.getMessage());
        }
    }

    private void dataTest() {
        Integer start = pager.getPageConfig().getStart();

        List<PagerItem> items = pager.getCurrentPage().getItems();

        for (PagerItem item : items) {
            assertEquals("data " + start++, item.item);
        }
    }

    private void init() {
        assertNotNull(pager);

        PageConfig config = pager.getPageConfig();
        assertNotNull(config);
        
        config.setStart(0);
        config.setFinish(10);
    }
}
