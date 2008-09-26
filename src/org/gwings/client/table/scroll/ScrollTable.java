package org.gwings.client.table.scroll;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.gwings.client.table.ColumnRenderer;
import org.gwings.client.table.DefaultTableModel;
import org.gwings.client.table.Plotable;
import org.gwings.client.table.TableModel;
import org.gwings.client.table.TableModelEvent;
import org.gwings.client.table.TableModelListener;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.dom.client.TableElement;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.widgetideas.client.ResizableWidget;
import com.google.gwt.widgetideas.client.ResizableWidgetCollection;
import com.google.gwt.widgetideas.table.client.FixedWidthFlexTable;

/**
 * <p>
 * ScrollTable consists of a fixed header and footer (optional) that remain
 * visible and a scrollable body that contains the data.
 * </p>
 * 
 * <p>
 * In order for the columns in the header table and data table to line up, the
 * two table must have the same margin, padding, and border widths. You can use
 * CSS style sheets to manipulate the colors and styles of the cell's, but you
 * must keep the actual sizes consistent (especially with respect to the left
 * and right side of the cells).
 * </p>
 * 
 * <h3>CSS Style Rules</h3>
 * <ul class="css">
 * <li> .gwt-ScrollTable { applied to the entire widget } </li>
 * <li> .gwt-ScrollTable .headerTable { applied to the header table }
 * <li> .gwt-ScrollTable .dataTable { applied to the data table }
 * <li> .gwt-ScrollTable .footerTable { applied to the footer table }
 * <li> .gwt-ScrollTable .headerWrapper { wrapper around the header table }</li>
 * <li> .gwt-ScrollTable .dataWrapper { wrapper around the data table }</li>
 * <li> .gwt-ScrollTable .footerWrapper { wrapper around the footer table }</li>
 * </ul>
 */
public class ScrollTable extends ComplexPanel implements ResizableWidget, TableModelListener, EventListener {
    
    /**
     * The default style name.
     */
    public static final String DEFAULT_STYLE_NAME = "gwt-ScrollTable";

    private ColumnResizePolicy columnResizePolicy = ColumnResizePolicy.MULTI_CELL;
    private ResizePolicy resizePolicy = ResizePolicy.FLOW;
    private ScrollPolicy scrollPolicy;
    
    /**
     * Columns which have guaranteed sizes.
     */
    private Set<Integer> guaranteedColumns = new HashSet<Integer>();
    
    /**
     * Wrappers Div around the tables. Header and footer are non-scrollable, data
     * is scrollable.
     */
    private DivElement headerWrapper;
    private DivElement dataWrapper;
    private DivElement footerWrapper = null;

    /**
     * A spacer used to stretch the tables area so we can scroll past the
     * edge of the table.
     */
    private DivElement headerSpacer;
    private DivElement footerSpacer = null;
    
    /**
     * An image used to show a fill width button.
     */
    private Image fillWidthImage;
    
    /**
     * The tables of the scroll table.
     */
    private FixedWidthFlexTable headerTable = null;    
    private FixedWidthFlexTable dataTable = null;
    private FixedWidthFlexTable footerTable = null;
    
    /**
     * The last known height of this widget that the user set.
     */
    private String lastHeight = null;
    /**
     * The minimum allowed width of the data table.
     */
    private int minWidth = -1;
    
    /**
     * The worker that helps with mouse resize events.
     */
    private MouseResizeWorker resizeWorker = GWT.create(MouseResizeWorker.class);
    
    /**
     * A Deferred command used to resize tables vertically. Using this command
     * ensures that the tables don't resize until the client has time to modify
     * rendering.
     */
    private Command resizeTablesVerticallyCommand = new Command() {
        public void execute() {
            resizeTablesVerticallyNow();
        }
    };
    /**
     * A map of columns that cannot be sorted.
     * key = the column index
     * value = true if the column is sortable, false of not
     */
    private Map<Integer, Boolean> unsortableColumns = new HashMap<Integer, Boolean>();
    
    private TableModel tableModel;
    
    public ScrollTable(){
        this(new DefaultTableModel());
    }
    
    public ScrollTable(TableModel model){
        this(new FixedWidthFlexTable(), new FixedWidthFlexTable(), model);
    }
    
    /**
     * Constructor.
     * 
     * @param dataTable the data table
     * @param headerTable the header table
     */
    public ScrollTable(FixedWidthFlexTable dataTable, FixedWidthFlexTable headerTable, TableModel model) {
        this(dataTable, headerTable, (ScrollTableImages) GWT.create(ScrollTableImages.class), model);
        
    }

    /**
     * Constructor.
     * 
     * @param dataTable the data table
     * @param headerTable the header table
     * @param images the images to use in the table
     */
    public ScrollTable(FixedWidthFlexTable dataTable, FixedWidthFlexTable headerTable, ScrollTableImages images, TableModel model){
        super();
        init(images);
        setDataTable(dataTable);
        setHeaderTable(headerTable);
        setTableModel(model);
        setScrollPolicy(ScrollPolicy.DISABLED);
    }

    private void init(ScrollTableImages images){
        
        // Create the main div container
        DivElement mainElem = Document.get().createDivElement();
        setElement(mainElem);
        setStylePrimaryName(DEFAULT_STYLE_NAME);
        mainElem.getStyle().setProperty("padding", "0px");
        mainElem.getStyle().setProperty("overflow", "hidden");
        mainElem.getStyle().setProperty("position", "relative");

        resizeWorker.setScrollTable(this);
        
        // Create image to fill width
        fillWidthImage = new Image() {
            @Override
            public void onBrowserEvent(Event event) {
                super.onBrowserEvent(event);
                if (DOM.eventGetType(event) == Event.ONCLICK) {
                    fillWidth();
                }
            }
        };
        fillWidthImage.setTitle("Shrink/Expand to fill visible area");
        images.scrollTableFillWidth().applyTo(fillWidthImage);

        ImageElement fillWidthImageElem = (ImageElement)(Element)fillWidthImage.getElement();
        fillWidthImageElem.getStyle().setProperty("cursor", "pointer");
        fillWidthImageElem.getStyle().setProperty("position", "absolute");
        fillWidthImageElem.getStyle().setProperty("top", "0px");
        fillWidthImageElem.getStyle().setProperty("right", "0px");
        fillWidthImageElem.getStyle().setProperty("zIndex", "1");
        add(fillWidthImage, getElement());

        // Add some event handling
        sinkEvents(Event.ONMOUSEOUT);
        
        // Add to Resizable Collection
        ResizableWidgetCollection.get().add(this);
    }

    private void setDataTable(FixedWidthFlexTable dataTable) {
        if(this.dataTable != null){
            orphan(this.dataTable);
            dataWrapper.removeChild(this.dataTable.getElement());
            getElement().removeChild(dataWrapper);
            getChildren().remove(this.dataTable);
        }
        
        
        if(dataTable != null){
            dataTable.setCellSpacing(getCellSpacing());
            dataTable.setCellPadding(getCellPadding());
            prepareTable(dataTable, "dataTable");

            this.dataTable = dataTable;
            
            if(dataWrapper == null){
                dataWrapper = createWrapper("dataWrapper");
                DOM.setEventListener((com.google.gwt.user.client.Element)(Element)dataWrapper, this);
                Event.sinkEvents(dataWrapper, Event.ONSCROLL);
            }
            adoptTable(dataTable, dataWrapper, 2);
        }
        
        resizeTablesVertically();
    }

    public void setHeaderTable(FixedWidthFlexTable headerTable) {
        if(this.headerTable != null){
            orphan(this.headerTable);
            headerWrapper.removeChild(this.headerTable.getElement());
            getElement().removeChild(headerWrapper);
            
            getChildren().remove(this.headerTable);
        }
        
        this.headerTable = headerTable;
        if(headerTable != null){
            headerTable.setCellSpacing(getCellSpacing());
            headerTable.setCellPadding(getCellPadding());
            prepareTable(headerTable, "headerTable");
            
            if(headerWrapper == null){
                headerWrapper = createWrapper("headerWrapper");
                headerSpacer = createSpacer(headerWrapper);
                
                DOM.setEventListener((com.google.gwt.user.client.Element)(Element)headerWrapper, this);
                Event.sinkEvents(headerWrapper, Event.ONMOUSEMOVE | Event.ONMOUSEDOWN | Event.ONMOUSEUP | Event.ONCLICK);
            }
            
            adoptTable(headerTable, headerWrapper, 1);

        }
        
        resizeTablesVertically();
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
        setScrollPolicy(ScrollPolicy.DISABLED);

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
        setScrollPolicy(policy);
        scrollTables(false);
    }

    /**
     * @return the cell padding of the tables, in pixels
     */
    public int getCellPadding() {
        return (dataTable != null ? dataTable.getCellPadding() : 0);
    }

    /**
     * @return the cell spacing of the tables, in pixels
     */
    public int getCellSpacing() {
        return (dataTable != null ? dataTable.getCellSpacing() : 0);
    }

    /**
     * @return the column resize policy
     */
    public ColumnResizePolicy getColumnResizePolicy() {
        return columnResizePolicy;
    }

    /**
     * Return the column width for a given column index.
     * 
     * @param column the column index
     * @return the column width in pixels
     */
    public int getColumnWidth(int column) {
        return dataTable.getColumnWidth(column);
    }

    /**
     * @return the data table
     */
    public FixedWidthFlexTable getDataTable() {
        return dataTable;
    }

    /**
     * @return the footer table
     */
    public FixedWidthFlexTable getFooterTable() {
        return footerTable;
    }

    /**
     * @return the header table
     */
    public FixedWidthFlexTable getHeaderTable() {
        return headerTable;
    }

    /**
     * @return the minimum width of the data table
     */
    public int getMinWidth() {
        return minWidth;
    }

    /**
     * @return the resize policy
     */
    public ResizePolicy getResizePolicy() {
        return resizePolicy;
    }

    /**
     * @return the current scroll policy
     */
    public ScrollPolicy getScrollPolicy() {
        return scrollPolicy;
    }

    /**
     * @param column the column index
     * @return true if the column width is guaranteed
     */
    public boolean isColumnWidthGuaranteed(int column) {
        return guaranteedColumns.contains(new Integer(column));
    }

    /**
     * @see Widget
     */
    public void onBrowserEvent(Event event) {
        super.onBrowserEvent(event);
        Element target = event.getTarget();
        switch (event.getTypeInt()) {
            case Event.ONSCROLL:
                // Reposition the tables on scroll
                scrollTables(false);
                break;

            case Event.ONMOUSEDOWN:
                // Start resizing a header column
                if (event.getButton() != Event.BUTTON_LEFT) {
                    return;
                }
                if (resizeWorker.getCurrentCell() != null) {
                    event.preventDefault();
                    event.cancelBubble(true);
                    resizeWorker.startResizing(event);
                }
                break;
            case Event.ONMOUSEUP:
                if (event.getButton() != Event.BUTTON_LEFT) {
                    return;
                }
                // Stop resizing the header column
                if (resizeWorker.isResizing()) {
                    resizeWorker.stopResizing(event);
                } else {
                    // Scroll tables if needed
                    if (headerWrapper.isOrHasChild(target)) {
                        scrollTables(true);
                    } else {
                        scrollTables(false);
                    }
                }
                break;
            case Event.ONMOUSEMOVE:
                // Resize the header column
                if (resizeWorker.isResizing()) {
                    resizeWorker.resizeColumn(event);
                } else {
                    resizeWorker.setCurrentCell(event);
                }
                break;
            case Event.ONMOUSEOUT:
                // Unhover if the mouse leaves the table
                Element toElem = DOM.eventGetToElement(event);
                if (toElem == null || !dataWrapper.isOrHasChild(toElem)) {
                    // Check that the coordinates are not directly over the table
                    int clientX = DOM.eventGetClientX(event);
                    int clientY = DOM.eventGetClientY(event);
                    int tableLeft = dataWrapper.getAbsoluteLeft();
                    int tableTop = dataWrapper.getAbsoluteTop();
                    int tableWidth = dataWrapper.getPropertyInt("offsetWidth");
                    int tableHeight = dataWrapper.getPropertyInt("offsetHeight");
                    int tableBottom = tableTop + tableHeight;
                    int tableRight = tableLeft + tableWidth;
                    if (clientX > tableLeft && clientX < tableRight && clientY > tableTop
                            && clientY < tableBottom) {
                        return;
                    }

                    //          dataTable.hoverCell(null);
                }
                break;
        }
    }

    /**
     * This method is called when the dimensions of the parent element change.
     * Subclasses should override this method as needed.
     * 
     * @param width the new client width of the element
     * @param height the new client height of the element
     */
    public void onResize(int width, int height) {
        redraw();
    }

    /**
     * Redraw the table. This is a relatively cheap operation and should be called
     * after modifying the header or footer sections.
     */
    public void redraw() {
        resizeTablesVertically();

        if (resizePolicy == ResizePolicy.FILL_WIDTH) {
            DeferredCommand.addCommand(new Command() {
                public void execute() {
                    fillWidth();
                }
            });
        }
    }

    /**
     * Unsupported.
     * 
     * @param child the widget to be removed
     * @return false
     * @throws UnsupportedOperationException
     */
    public boolean remove(Widget child) {
        throw new UnsupportedOperationException(
        "This panel does not support remove()");
    }

    /**
     * Sets the amount of padding to be added around all cells.
     * 
     * @param padding the cell padding, in pixels
     */
    public void setCellPadding(int padding) {
        headerTable.setCellPadding(padding);
        dataTable.setCellPadding(padding);
        if (footerTable != null) {
            footerTable.setCellPadding(padding);
        }
    }

    /**
     * Sets the amount of spacing to be added around all cells.
     * 
     * @param spacing the cell spacing, in pixels
     */
    public void setCellSpacing(int spacing) {
        headerTable.setCellSpacing(spacing);
        dataTable.setCellSpacing(spacing);
        if (footerTable != null) {
            footerTable.setCellSpacing(spacing);
        }
    }

    /**
     * Set the resize policy applied to user actions that resize columns.
     * 
     * @param columnResizePolicy the resize policy
     */
    public void setColumnResizePolicy(ColumnResizePolicy columnResizePolicy) {
        this.columnResizePolicy = columnResizePolicy;
        updateFillWidthImage();
    }

    /**
     * Enable or disable sorting on a specific column. All columns are sortable by
     * default. Use {@link #setSortingEnabled(boolean)} to disable sorting on all
     * columns.
     * 
     * @param column the index of the column
     * @param sortable true to enable sorting for this column, false to disable
     */
    public void setColumnSortable(int column, boolean sortable) {
        unsortableColumns.put(new Integer(column), Boolean.valueOf(sortable));
    }

    /**
     * Set the width of a column. If the column has already been set using the
     * {@link #setGuaranteedColumnWidth(int, int)} method, the column will no
     * longer have a guaranteed column width.
     * 
     * @param column the index of the column
     * @param width the width in pixels
     * @return the new column width
     */
    public int setColumnWidth(int column, int width) {
        guaranteedColumns.remove(new Integer(column));
        return setColumnWidth(column, width, column + 1);
    }

    /**
     * Set the footer table that appears under the data table. If set to null, the
     * footer table will not be shown.
     * 
     * @param footerTable the table to use in the footer
     */
    public void setFooterTable(FixedWidthFlexTable footerTable) {
        // Disown the old footer table
        if (this.footerTable != null) {
            orphan(this.footerTable);
            footerWrapper.removeChild(this.footerTable.getElement());
            getElement().removeChild(footerWrapper);
            getChildren().remove(this.footerTable);
        }

        // Set the new footer table
        this.footerTable = footerTable;
        if (footerTable != null) {
            footerTable.setCellSpacing(getCellSpacing());
            footerTable.setCellPadding(getCellPadding());
            prepareTable(footerTable, "footerTable");

            // Create the footer wrapper and spacer
            if (footerWrapper == null) {
                footerWrapper = createWrapper("footerWrapper");
                footerSpacer = createSpacer(footerWrapper);
                
                DOM.setEventListener((com.google.gwt.user.client.Element)(Element)footerWrapper, this);
                Event.sinkEvents(footerWrapper, Event.ONMOUSEUP);
            }

            // Adopt the header table into the panel
            adoptTable(footerTable, footerWrapper, 3);
        }

        // Resize the tables
        resizeTablesVertically();
    }

    /**
     * Set the width of a column and guarantees that the width will not change,
     * regardless of the resize policy.
     * 
     * @param column the index of the column
     * @param width the width in pixels
     * @return the new column width
     */
    public int setGuaranteedColumnWidth(int column, int width) {
        guaranteedColumns.add(new Integer(column));
        return setColumnWidth(column, width, column + 1);
    }

    /**
     * @see com.google.gwt.user.client.ui.UIObject
     */
    public void setHeight(String height) {
        this.lastHeight = height;
        super.setHeight(height);
    }

    /**
     * Set the minimum allowable width of the data table. If the width of this
     * Widget is smaller than the width of the data table, a horizontal scroll bar
     * will appear.
     * 
     * The minWidth property only applies to the policy
     * {@link ResizePolicy#FILL_WIDTH}.
     * 
     * @param minWidth the minimum width, or -1 to disable
     */
    public void setMinWidth(int minWidth) {
        this.minWidth = minWidth;
        maybeFillWidth();
    }

    /**
     * Set the resize policy of the table.
     * 
     * @param resizePolicy the resize policy
     */
    public void setResizePolicy(ResizePolicy resizePolicy) {
        this.resizePolicy = resizePolicy;
        updateFillWidthImage();
        maybeFillWidth();
    }

    /**
     * Set the scroll policy of the table.
     * 
     * @param scrollPolicy the new scroll policy
     */
    public void setScrollPolicy(ScrollPolicy scrollPolicy) {
        this.scrollPolicy = scrollPolicy;
        if (scrollPolicy == ScrollPolicy.DISABLED) {
            // Disabled scroll bars
            super.setHeight("auto");
            
            dataWrapper.getStyle().setProperty("height", "auto");
            dataWrapper.getStyle().setProperty("overflow", "");
            getElement().getStyle().setProperty("overflow", "");
        }
        else {
            if (scrollPolicy == ScrollPolicy.HORIZONTAL) {
                // Only show horizontal scroll bar
                super.setHeight("auto");
                dataWrapper.getStyle().setProperty("overflow", "auto");
                getElement().getStyle().setProperty("overflow", "hidden");
            }
            else{ 
                if (scrollPolicy == ScrollPolicy.BOTH) {
                    // Show both scroll bars
                    if (lastHeight != null) {
                        super.setHeight(lastHeight);
                    }
                    dataWrapper.getStyle().setProperty("overflow", "auto");
                    getElement().getStyle().setProperty("overflow", "hidden");
                }
            }
        }
        // Resize the tables
        redraw();
    }

    /**
     * @return the wrapper element around the data table
     */
    protected DivElement getDataWrapper() {
        return dataWrapper;
    }

    /**
     * Resize the widget and redistribute space as needed.
     */
    protected void onAttach() {
        super.onAttach();
        resizeTablesVertically();
        repositionHeaderSpacer();
        maybeFillWidth();
    }

    /**
     * Fixes the table heights so the header is visible and the data takes up the
     * remaining vertical space.
     */
    protected void resizeTablesVertically() {
        DeferredCommand.addCommand(resizeTablesVerticallyCommand);
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
        int headerHeight = headerTable.getOffsetHeight();
        int footerHeight = 0;
        if (footerTable != null) {
            footerHeight = footerTable.getOffsetHeight();
        }
        headerWrapper.getStyle().setPropertyPx("height", headerHeight);
        if (footerWrapper != null) {
            footerWrapper.getStyle().setPropertyPx("height", footerHeight);
        }
        int height = totalHeight - headerHeight - footerHeight;
        dataWrapper.getStyle().setPropertyPx("height", (height > 0 ? height : 1));
        dataWrapper.getStyle().setProperty("overflow", "hidden");
        dataWrapper.getStyle().setProperty("overflow", "auto");
        scrollTables(true);
    }

    /**
     * Sets the scroll property of the header and data wrappers when scrolling so
     * that the header, footer, and data tables line up.
     * 
     * @param baseHeader If true, use the header as the alignment source
     */
    protected void scrollTables(boolean baseHeader) {
        // Return if scrolling is disabled
        if (scrollPolicy == ScrollPolicy.DISABLED) {
            return;
        }

        if (isAttached()) {
            if (baseHeader) {
                int headerScrollLeft = headerWrapper.getScrollLeft();
                if(headerScrollLeft > 0){
                    dataWrapper.setScrollLeft(headerScrollLeft);
                }
            }
            int scrollLeft = dataWrapper.getScrollLeft();
            headerWrapper.setScrollLeft(scrollLeft);
            if (footerWrapper != null) {
                footerWrapper.setScrollLeft(scrollLeft);
            }
        }
    }

    /**
     * Set the width of a column.
     * 
     * @param column the index of the column
     * @param width the width in pixels
     * @param sacrificeColumn the column that will be shrunk to maintain the width
     * @return the new column width
     */
    protected int setColumnWidth(int column, int width, int sacrificeColumn) {
        // A zero width will render improperly, so the width must be at least 1
        width = Math.max(width, 1);

        // Try to constrain the size of the grid
        if (resizePolicy != ResizePolicy.UNCONSTRAINED) {
            int diff = width - getColumnWidth(column);
            diff += redistributeWidth(-diff, sacrificeColumn);

            // Prevent over resizing
            if (resizePolicy == ResizePolicy.FIXED_WIDTH
                    || resizePolicy == ResizePolicy.FILL_WIDTH) {
                width -= diff;
            }
        }

        // Resize the column
        dataTable.setColumnWidth(column, width);
        headerTable.setColumnWidth(column, width);
        if (footerTable != null) {
            footerTable.setColumnWidth(column, width);
        }

        // Reposition things as needed
        repositionHeaderSpacer();
        scrollTables(false);
        return width;
    }

    /**
     * Adopt a table into this {@link ScrollTable} within its wrapper.
     * 
     * @param table the table to adopt
     * @param wrapper the wrapper element
     * @param index the index to insert the wrapper in the main element
     */
    private void adoptTable(Widget table, DivElement wrapper, int index) {
        getChildren().add(table);
        Node sibling = getElement().getChildNodes().getItem(index);
        getElement().insertBefore(wrapper, sibling);
        wrapper.appendChild(table.getElement());
        adopt(table);
    }

    /**
     * Create a spacer element that allows scrolling past the edge of a table.
     * 
     * @param wrapper the wrapper element
     * @return a new spacer element
     */
    private DivElement createSpacer(DivElement wrapper) {
        DivElement spacer = Document.get().createDivElement();
        spacer.getStyle().setProperty("height", "1px");
        spacer.getStyle().setProperty("width", "10000px");
        spacer.getStyle().setProperty("position", "absolute");
        spacer.getStyle().setProperty("top", "1px");
        spacer.getStyle().setProperty("left", "1px");
        wrapper.appendChild(spacer);
        return spacer;
    }

    /**
     * Create a wrapper element that will hold a table.
     * 
     * @param cssName the style name added to the base name
     * @return a new wrapper element
     */
    private DivElement createWrapper(String cssName) {
        DivElement wrapper = Document.get().createDivElement();
        wrapper.getStyle().setProperty("width", "100%");
        wrapper.getStyle().setProperty("overflow", "hidden");
        wrapper.getStyle().setProperty("position", "relative");
        wrapper.getStyle().setPropertyPx("padding", 0);
        wrapper.getStyle().setPropertyPx("margin", 0);
        wrapper.getStyle().setPropertyPx("border", 0);
        wrapper.setClassName(cssName);
        return wrapper;
    }

    /**
     * Extend the columns to exactly fill the available space, if the current
     * {@link ResizePolicy} requires it.
     */
    private void maybeFillWidth() {
        if (resizePolicy == ResizePolicy.FILL_WIDTH) {
            fillWidth();
        }
    }

    /**
     * Prepare a table to be added to the {@link ScrollTable}.
     * 
     * @param table the table to prepare
     * @param cssName the style name added to the base name
     */
    private void prepareTable(Widget table, String cssName) {
        TableElement tableElem = TableElement.as(table.getElement());
        tableElem.getStyle().setProperty("margin", "0px");
        tableElem.getStyle().setProperty("border", "0px");
        table.addStyleName(cssName);
    }

    /**
     * Distribute a given amount of width over all columns to the right of the
     * specified column.
     * 
     * @param width the width to distribute
     * @param startColumn the index of the first column to receive the width
     * @return the actual amount of distributed width
     */
    private int redistributeWidth(int width, int startColumn) {
        // Make sure we have a column to distribute to
        int numColumns = Math.max(headerTable.getColumnCount(),
                                  dataTable.getColumnCount());
        if (startColumn >= numColumns) {
            return 0;
        }

        // Find the first column with a non-guaranteed width
        if (isColumnWidthGuaranteed(startColumn)) {
            boolean columnFound = false;
            for (int i = startColumn + 1; i < numColumns; i++) {
                if (!isColumnWidthGuaranteed(i)) {
                    startColumn = i;
                    columnFound = true;
                    break;
                }
            }
            if (!columnFound) {
                return 0;
            }
        }

        // Redistribute the width across the columns
        int actualWidth = 0;
        if (width > 0) {
            int startWidth = getColumnWidth(startColumn);
            int newWidth = startWidth + width;
            dataTable.setColumnWidth(startColumn, newWidth);
            headerTable.setColumnWidth(startColumn, newWidth);
            if (footerTable != null) {
                footerTable.setColumnWidth(startColumn, newWidth);
            }
            actualWidth = width;
        } else if (width < 0) {
            for (int i = startColumn; i < numColumns && width < 0; i++) {
                if (!isColumnWidthGuaranteed(i)) {
                    int startWidth = getColumnWidth(i);
                    int newWidth = startWidth + width;
                    dataTable.setColumnWidth(i, newWidth);
                    headerTable.setColumnWidth(i, newWidth);
                    if (footerTable != null) {
                        footerTable.setColumnWidth(i, newWidth);
                    }
                    int colDiff = startWidth - getColumnWidth(i);
                    width += colDiff;
                    actualWidth -= colDiff;
                }
            }
        }

        // Return the actual width
        return actualWidth;
    }

    /**
     * Reposition the header spacer next to the header table.
     */
    private void repositionHeaderSpacer() {
        int headerWidth = headerTable.getOffsetWidth();
        headerSpacer.getStyle().setPropertyPx("left", headerWidth);
        if (footerTable != null) {
            int footerWidth = footerTable.getOffsetWidth();
            footerSpacer.getStyle().setPropertyPx("left", footerWidth);
        }
    }

    /**
     * Show or hide to fillWidthImage depending on current policies.
     */
    private void updateFillWidthImage() {
        if (columnResizePolicy == ColumnResizePolicy.DISABLED
                || resizePolicy == ResizePolicy.FILL_WIDTH
                || resizePolicy == ResizePolicy.FIXED_WIDTH) {
            fillWidthImage.setVisible(false);
        } else {
            fillWidthImage.setVisible(true);
        }
    }
    
    public void columnAdded(TableModelEvent evt) {
        TableModel model = evt.getSource();
        String columnName = model.getColumnName(evt.getColumn());
        for (int i = 0; i < model.getRowCount(); i++) {
            dataTable.addCell(i);
            headerTable.addCell(i);
        }
        if(headerTable.getRowCount() == 0){
            headerTable.insertRow(0);
        }
        int coluna = headerTable.getCellCount(0);
        headerTable.setHTML(0, coluna, columnName);
        headerTable.getRowFormatter().setStyleName(0, "header");
        
        redraw();
        
    }
    
    public void columnRemoved(TableModelEvent evt) {
        TableModel model = evt.getSource();
        int column = evt.getColumn();
        
        headerTable.removeCell(0, column);
        
        for (int i = 0; i < model.getRowCount(); i++) {
            dataTable.removeCell(i, column);
        }
    }
    

    @SuppressWarnings("unchecked")
    public void rowAdded(TableModelEvent evt) {
        TableModel model = evt.getSource();
        int row = evt.getRow();

        Plotable plotable = model.getLine(row);

        Object[] line = plotable.plot();
        int nextRow = row;
        dataTable.insertRow(nextRow);
        dataTable.getRowFormatter().addStyleName(nextRow, "row");
        for (int i = 0; i < line.length; i++) {
            ColumnRenderer renderer = model.getColumnRenderer(i);
            Widget widget = renderer.renderType(line[i]);
            dataTable.setWidget(nextRow, i, widget);
            dataTable.getCellFormatter().setAlignment(nextRow, i,
                                                      VerticalPanel.ALIGN_CENTER,
                                                      VerticalPanel.ALIGN_MIDDLE);
        }
    }

    public void rowChanged(TableModelEvent evt) {
        rowRemoved(evt);
        rowAdded(evt);
    }

    public void rowRemoved(TableModelEvent evt) {
        int row = evt.getRow();
        dataTable.removeRow(row + 1);
    }

    public void rowsCleared(TableModelEvent evt) {
        tableCleared(evt);
    }
    
    @SuppressWarnings("unchecked")
    public void tableChanged(TableModelEvent evt) {
        //destruir a tabela e reconstruir com o table model do evento.
        tableCleared(evt);
        
        TableModel model = evt.getSource();
        for(int column = 0; column < model.getColumnCount(); column++){
            String columnName = model.getColumnName(column);
            headerTable.setHTML(0, column, columnName);
        }
        
        
        for(int row = 0; row < model.getRowCount(); row++){
            Plotable plotable = model.getLine(row);

            Object[] line = plotable.plot();
            dataTable.insertRow(row);
            dataTable.getRowFormatter().addStyleName(row, "row");
            for (int i = 0; i < line.length; i++) {
                ColumnRenderer renderer = model.getColumnRenderer(i);
                Widget widget = renderer.renderType(line[i]);
                dataTable.setWidget(row, i, widget);
                dataTable.getCellFormatter().setAlignment(row, i,
                                                          VerticalPanel.ALIGN_CENTER,
                                                          VerticalPanel.ALIGN_MIDDLE);
            }
        }
    }

    public void tableCleared(TableModelEvent evt) {
        for (int i = 0; i < evt.getSource().getRowCount(); i++) {
            dataTable.removeRow(i + 1);
        }
        dataTable.clear();
    }
    
    /**
     * @return the headerWrapper
     */
    DivElement getHeaderWrapper() {
        return headerWrapper;
    }
    
    /**
     * @return the footerWrapper
     */
    DivElement getFooterWrapper() {
        return footerWrapper;
    }
    
    TableCellElement getHeaderEventTargetCell(Event evt){
        com.google.gwt.user.client.Element targetCell = headerTable.getEventTargetCell(evt);
        return (TableCellElement)(Element)targetCell;
    }
    
    int getHeaderColumnIndex(int row, int cell){
        return headerTable.getColumnIndex(row, cell);
    }

    /**
     * @return the tableModel
     */
    public TableModel getTableModel() {
        return tableModel;
    }
    
    /**
     * @param tableModel the tableModel to set
     */
    public void setTableModel(TableModel tableModel) {
        if(this.tableModel != null){
            this.tableModel.removeTableModelListener(this);
        }
        
        this.tableModel = tableModel;
        this.tableModel.addTableModelListener(this);
        
        tableChanged(new TableModelEvent(this.tableModel));
    }
}