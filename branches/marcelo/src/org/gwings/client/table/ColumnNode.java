package org.gwings.client.table;
/**
 * A Linked List Node that contains information about a column within the
 * colspan of the current cell including its offset from the current cell's
 * index and its original width.
 */
public class ColumnNode {
    /**
     * The cell index.
     */
    private int cellIndex;

    /**
     * The original width of this cell.
     */
    private int originalWidth;

    public ColumnNode(int cellIndex, int originalWidth) {
        this.cellIndex = cellIndex;
        this.originalWidth = originalWidth;
    }

    public int getCellIndex() {
        return cellIndex;
    }

    public int getOriginalWidth() {
        return originalWidth;
    }
}
