package org.gwings.client.table.pagination.model;

import org.gwings.client.table.model.Plotable;

public class AsyncEmptyProvider<T extends Plotable> extends
        AbstractAsyncDataProvider<T> {

    public void dataFetched(Page<T> page) {

    }

    public void fetchData(PageConfig cfg) {

    }

    public void fetchSize() {

    }

    public void sizeFetched(Integer size) {

    }
}