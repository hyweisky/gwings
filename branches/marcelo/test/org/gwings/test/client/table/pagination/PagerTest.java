/**
 * 
 */
package org.gwings.test.client.table.pagination;

import java.util.ArrayList;
import java.util.List;

import org.gwings.client.table.Plotable;
import org.gwings.client.table.pagination.model.DataProvider;
import org.gwings.client.table.pagination.model.Page;
import org.gwings.client.table.pagination.model.PageConfig;
import org.gwings.client.table.pagination.model.Pager;

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

        public Page<PagerItem> fetchData(PageConfig config) throws Exception {
            Integer start = config.getStart();
            Integer finish = config.getFinish();

            List<PagerItem> items = new ArrayList<PagerItem>();
            for (int i = start; i < finish; i++) {
                items.add(fullData.get(i));
            }

            Page<PagerItem> page = new Page<PagerItem>();
            page.setItems(items);
            page.setConfig(config);
            return page;
        }

        public Integer fetchSize() throws Exception {
            return fullData.size();
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
        
        PageConfig pageConfig = pager.getPageConfig();
        
        assertEquals(new Integer(10), pageConfig.getPageSize());
        assertEquals(pageConfig.getPageSize(), pager.getPageSize());

        try {
            assertEquals(new Integer(31), pager.getTotalPages());
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testGetCurrentPage() {
        init();
        
        assertNull(pager.getCurrentPage());
        assertEquals(new Integer(-1), pager.currentPageIndex());
        
        try {
            pager.nextPage();
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
        
        assertNotNull(pager.getCurrentPage());
        assertEquals(new Integer(1), pager.currentPageIndex());
    }
    
    public void testNextPage(){
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
        
        assertEquals(new Integer(30), pager.currentPageIndex());
        assertEquals(new Integer(291), pager.getPageConfig().getStart());
        assertEquals(new Integer(301), pager.getPageConfig().getFinish());
        assertEquals(new Integer(301), pager.getPageConfig().getTotalAvailable());
        
        try {
            pager.nextPage();
            fail();
        }
        catch (Exception e) {
            assertEquals("Invalid interval! Finish greater than total.", e.getMessage());
        }
    }
    
    public void testFirstPage(){
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

    
    public void testPreviousPage(){
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
            assertEquals("Invalid interval! Start less than zero!", e.getMessage());
        }
    }
    
    public void testLastPage(){
        init();
        
        assertEquals(new Integer(-1), pager.currentPageIndex());
        assertEquals(new Integer(0), pager.getPageConfig().getStart());
        assertEquals(new Integer(10), pager.getPageConfig().getFinish());
        assertNotNull(pager.getPageConfig().getTotalAvailable());
        
        try {
            pager.lastPage();
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
        
        assertNotNull(pager.getCurrentPage());
        
        assertEquals(new Integer(30), pager.currentPageIndex());
        assertEquals(new Integer(291), pager.getPageConfig().getStart());
        assertEquals(new Integer(301), pager.getPageConfig().getFinish());
        assertEquals(new Integer(301), pager.getPageConfig().getTotalAvailable());
        
        dataTest();
    }

    public void testGoToPage(){
        init();
        
        assertNull(pager.getCurrentPage());
        assertEquals(new Integer(-1), pager.currentPageIndex());
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
            assertEquals("Invalid page number!", e.getMessage());
        }
    }
    
    private void dataTest(){
        Integer start = pager.getPageConfig().getStart();
        
        List<PagerItem> items = pager.getCurrentPage().getItems();
        
        for(PagerItem item : items){
            assertEquals("data "+start++, item.item);
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
