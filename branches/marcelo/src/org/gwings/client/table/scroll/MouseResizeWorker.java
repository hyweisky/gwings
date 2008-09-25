package org.gwings.client.table.scroll;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Timer;

/**
 * A helper class that handles some of the mouse events associated with resizing
 * columns.
 */
public class MouseResizeWorker {

    /**
     * The current header cell that the mouse is affecting.
     */
    private TableCellElement curCell = null;

    /**
     * The current columns under the colSpan of the current cell, ordered from
     * narrowest to widest.
     */
    private List<ColumnNode> curCellColumns = new ArrayList<ColumnNode>();

    /**
     * The index of the current header cell.
     */
    private int curCellIndex = 0;

    /**
     * The current x position of the mouse.
     */
    private int mouseXCurrent = 0;

    /**
     * The last x position of the mouse when we resized.
     */
    private int mouseXLast = 0;

    /**
     * The starting x position of the mouse when resizing a column.
     */
    private int mouseXStart = 0;

    /**
     * A timer used to resize the columns. As long as the timer is active, it
     * will poll for the new row size and resize the columns.
     */
    private Timer resizeTimer = new Timer() {
        @Override
        public void run() {
            resizeColumn();
            schedule(100);
        }
    };

    /**
     * A boolean indicating that we are resizing the current header cell.
     */
    private boolean resizing = false;

    /**
     * The column that will be sacrificed.
     */
    private int sacrificeColumn = -1;

    /**
     * The table that this worker affects.
     */
    private ScrollTable table = null;

    /**
     * Returns the current cell.
     * 
     * @return the current cell
     */
    public TableCellElement getCurrentCell() {
        return curCell;
    }

    /**
     * Returns true if a header is currently being resized.
     * 
     * @return true if resizing
     */
    public boolean isResizing() {
        return resizing;
    }

    /**
     * Resize the column on a mouse event. This method also marks the client as
     * busy so we do not try to change the size repeatedly.
     * 
     * @param event
     *            the mouse event
     */
    public void resizeColumn(Event event) {
        mouseXCurrent = event.getClientX();
    }

    /**
     * Set the current cell that will be resized based on the mouse event.
     * 
     * @param event
     *            the event that triggered the new cell
     * @return true if the cell was actually changed
     */
    public boolean setCurrentCell(Event event) {
        // Check the resize policy of the table
        TableCellElement cell = null;
        switch(table.getColumnResizePolicy()){
            case MULTI_CELL:{
                cell = TableCellElement.as(table.headerTable.getEventTargetCell(event));
                break;
            }
            case SINGLE_CELL:{
                cell = TableCellElement.as(table.headerTable.getEventTargetCell(event));
                if (cell != null && cell.getColSpan() > 1) {
                    cell = null;
                }
            }
        }

        // See if we are near the edge of the cell
        if (cell != null) {
            int clientX = event.getClientX();
            int absRight = cell.getAbsoluteLeft() + cell.getOffsetWidth();
            if (clientX < absRight - 15 || clientX > absRight) {
                cell = null;
            }
        }

        // Change out the current cell
        if (cell != curCell) {
            // Clear the old cell
            if (curCell != null) {
                curCell.getStyle().setProperty("cursor", "default");
            }

            // Set the new cell
            curCell = cell;
            if (curCell != null) {
                curCellIndex = getCellIndex(curCell);
                if (table.isColumnWidthGuaranteed(curCellIndex)) {
                    curCell = null;
                    return false;
                }
                curCell.getStyle().setProperty("cursor", "e-resize");
            }
            return true;
        }

        // The cell did not change
        return false;
    }

    /**
     * Set the ScrollTable table that this worker affects.
     * 
     * @param table
     *            the scroll table
     */
    public void setScrollTable(ScrollTable table) {
        this.table = table;
    }

    /**
     * Start resizing the current cell when the user clicks on the right edge of
     * the cell.
     * 
     * @param event
     *            the mouse event
     */
    public void startResizing(Event event) {
        if (curCell != null) {
            resizing = true;
            mouseXStart = event.getClientX();
            mouseXLast = mouseXStart;
            mouseXCurrent = mouseXStart;

            // Get the columns under this cell's colspan
            int colSpan = curCell.getColSpan();
            sacrificeColumn = curCellIndex + colSpan;
            for (int i = 0; i < colSpan; i++) {
                int newCellIndex = curCellIndex + i;
                if (!table.isColumnWidthGuaranteed(newCellIndex)) {
                    int originalWidth = table.getColumnWidth(newCellIndex);

                    // Insert the node into the ordered list by width
                    int insertIndex = 0;
                    for (ColumnNode curNode : curCellColumns) {
                        if (originalWidth > curNode.getOriginalWidth()) {
                            insertIndex++;
                        } else {
                            break;
                        }
                    }

                    // Add the node at the correct index
                    curCellColumns.add(insertIndex,new ColumnNode(newCellIndex, originalWidth));
                }
            }

            // Start the timer and listen for changes
            Event.setCapture(table.getHeaderWrapper());
            resizeTimer.schedule(20);
        }
    }

    /**
     * Stop resizing the current cell.
     * 
     * @param event
     *            the mouse event
     */
    public void stopResizing(Event event) {
        if (curCell != null && resizing) {
            curCellColumns.clear();
            resizing = false;
            Event.releaseCapture(table.getHeaderWrapper());
            resizeTimer.cancel();
            resizeColumn();
        }
    }

    /**
     * Get the scroll table.
     * 
     * @return the scroll table
     */
    protected ScrollTable getScrollTable() {
        return table;
    }

    /**
     * Get the actual cell index of a cell in the header table.
     * 
     * @param cell
     *            the cell element
     * @return the cell index
     */
    private int getCellIndex(TableCellElement cell) {
        TableRowElement rowElement = TableRowElement.as(cell.getParentElement());
        int row = rowElement.getRowIndex() - 1;
        int column = cell.getCellIndex();
        return table.headerTable.getColumnIndex(row, column);
    }

    /**
     * Helper method that actually sets the column size. This method is called
     * periodically by a timer.
     */
    private void resizeColumn() {
        if (mouseXLast != mouseXCurrent) {
            mouseXLast = mouseXCurrent;
            
            ScrollPolicy policy = table.getScrollPolicy();
            
            table.setScrollPolicy(ScrollPolicy.DISABLED);
            int columnsRemaining = curCellColumns.size();
            int totalDelta = mouseXCurrent - mouseXStart;
            
            for (ColumnNode curNode : curCellColumns) {
                int originalWidth = curNode.getOriginalWidth();
                int column = curNode.getCellIndex();
                int delta = totalDelta / columnsRemaining;
                int colWidth = table.setColumnWidth(column, originalWidth + delta, sacrificeColumn);

                totalDelta -= (colWidth - originalWidth);
                columnsRemaining--;
            }
            
            table.setScrollPolicy(policy);
            table.scrollTables(false);
        }
    }
}
