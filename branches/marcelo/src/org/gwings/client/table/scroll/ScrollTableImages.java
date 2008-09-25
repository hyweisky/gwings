package org.gwings.client.table.scroll;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

/**
 * An {@link ImageBundle} that provides images for {@link ScrollTable}.
 */
public interface ScrollTableImages extends ImageBundle {

    /**
     * An image used to fill the available width.
     * 
     * @return a prototype of this image
     */
    AbstractImagePrototype scrollTableFillWidth();

    /**
     * An image indicating that a column is sorted in ascending order.
     * 
     * @return a prototype of this image
     */
    AbstractImagePrototype scrollTableAscending();

    /**
     * An image indicating a column is sorted in descending order.
     * 
     * @return a prototype of this image
     */
    AbstractImagePrototype scrollTableDescending();
}
