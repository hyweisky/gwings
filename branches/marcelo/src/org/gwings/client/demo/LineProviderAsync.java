package org.gwings.client.demo;

import org.gwings.client.demo.services.pagination.PaginatedLineProviderAsync;
import org.gwings.client.demo.services.pagination.ServiceFactory;
import org.gwings.client.table.pagination.model.DataProvider;
import org.gwings.client.table.pagination.model.Page;
import org.gwings.client.table.pagination.model.PageConfig;
import org.gwings.client.table.pagination.model.ProviderCallback;
import org.gwings.client.table.pagination.model.ProviderRequest;
import org.gwings.client.table.pagination.model.ProviderResponse;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class LineProviderAsync implements DataProvider<LinePlotable>,
        AsyncCallback<Page<LinePlotable>> {

    private static final long serialVersionUID = -6506240230479402719L;
    private PaginatedLineProviderAsync service;
    private ProviderRequest request;
    private ProviderCallback<LinePlotable> callback;
    private PageConfig config;

    public LineProviderAsync() {
        service = ServiceFactory.getPaginatedLineProviderService();
    }

    public void fetchData(final ProviderRequest request,
                          final ProviderCallback<LinePlotable> callback) {

        this.request = request;
        this.callback = callback;
        config = request.getConfig();
        service.getItems(request.getParams(), config, this);
    }

    public void onFailure(Throwable caught) {
    }

    public void onSuccess(Page<LinePlotable> result) {
        ProviderResponse<LinePlotable> response = new ProviderResponse<LinePlotable>(result);
        callback.dataFetched(request, response);
    }
}