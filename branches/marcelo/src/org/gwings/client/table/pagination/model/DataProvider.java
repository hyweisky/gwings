package org.gwings.client.table.pagination.model;

import org.gwings.client.table.model.Plotable;

public interface DataProvider<T extends Plotable> {

    public void fetchData(ProviderRequest request, ProviderCallback<T> callback);

}