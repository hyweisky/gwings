package org.gwings.client.table.pagination.model;

import org.gwings.client.table.model.Plotable;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class AbstractAsyncDataProvider<T extends Plotable> implements
        AsyncDataProvider<T> {

    protected AsyncCallback<Page<T>> dataCallback = new AsyncCallback<Page<T>>() {

        public void onFailure(Throwable caught) {
            failOnFetchData(caught);
        }

        public void onSuccess(Page<T> result) {
            dataFetched(result);
        };
    };

    protected AsyncCallback<Integer> sizeCallback = new AsyncCallback<Integer>() {

        public void onFailure(Throwable caught) {
            failOnFetchSize(caught);
        }

        public void onSuccess(Integer result) {
            sizeFetched(result);
        }
    };

    public void failOnFetchData(Throwable cause) {
        presentError(cause);
    }

    public void failOnFetchSize(Throwable cause) {
        presentError(cause);
    }

    public void presentError(Throwable error) {
        Window.alert(error.getMessage());
    }
}
