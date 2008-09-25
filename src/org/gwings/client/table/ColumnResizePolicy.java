package org.gwings.client.table;

/**
 * The resize policies related to user resizing.
 * <ul>
 * <li>DISABLED - Columns cannot be resized by the user</li>
 * <li>SINGLE_CELL - Only cells with a colspan of 1 can be resized</li>
 * <li>MULTI_CELL - All cells can be resized by the user</li>
 * </ul>
 */
public enum ColumnResizePolicy {
    DISABLED, SINGLE_CELL, MULTI_CELL
}