package org.gwings.client.table.pagination.model;

import org.gwings.client.table.model.Plotable;

public final class EmptyProvider<T extends Plotable> implements DataProvider<T> {

    private static final long serialVersionUID = 4404147801907185267L;

    public void fetchData(ProviderRequest request, ProviderCallback<T> callback) {
    }

}