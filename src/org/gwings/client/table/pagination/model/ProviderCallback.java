/**
 * 
 */
package org.gwings.client.table.pagination.model;

import org.gwings.client.table.model.Plotable;

/**
 * @author USER
 */
public interface ProviderCallback<T extends Plotable> {

    public void dataFetched(ProviderRequest request, ProviderResponse<T> response);
}