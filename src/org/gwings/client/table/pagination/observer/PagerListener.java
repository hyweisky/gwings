/**
 * 
 */
package org.gwings.client.table.pagination.observer;

import org.gwings.client.table.model.Plotable;

/**
 * @author USER
 */
public interface PagerListener<T extends Plotable> extends
        PagerRequestListener<T>, PagerReadyListener<T> {

}
