package org.gwings.client.table.scroll;

/**
 * The resize policies of table cells.
 * <ul>
 * <li>UNCONSTRAINED - Columns shrink and expand independently of each other</li>
 * <li>FLOW - As one column expands or shrinks, the columns to the right will do
 * the opposite, trying to maintain the same size. The user can still expand the
 * grid if there is no more space to take from the columns on the right.</li>
 * <li>FIXED_WIDTH - As one column expands or shrinks, the columns to the right
 * will do the opposite, trying to maintain the same size. The width of the grid
 * will remain constant, ignoring column resizing that would result in the grid
 * growing in size.</li>
 * <li>FILL_WIDTH - Same as FIXED_WIDTH, but the grid will always fill the
 * available width, even if the widget is resized.</li>
 * </ul>
 */
public enum ResizePolicy {
    UNCONSTRAINED, FLOW, FIXED_WIDTH, FILL_WIDTH
}
