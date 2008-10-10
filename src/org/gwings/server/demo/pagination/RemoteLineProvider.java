package org.gwings.server.demo.pagination;

import java.io.Serializable;
import java.util.Map;

import org.gwings.client.demo.LinePlotable;
import org.gwings.client.demo.services.pagination.PaginatedLineProvider;
import org.gwings.client.table.pagination.model.Page;
import org.gwings.client.table.pagination.model.PageConfig;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class RemoteLineProvider extends RemoteServiceServlet implements
        PaginatedLineProvider {

    private static final long serialVersionUID = -6820131024394722617L;

    public Page<LinePlotable> getItems(Map<String, ? extends Serializable> params,
                                       PageConfig cfg) throws Exception {
        
        LineProviderFacade facade = LineProviderFacade.getInstance();
        cfg.setTotalAvailable(facade.countTotal());
        
        Page<LinePlotable> page = new Page<LinePlotable>();
        page.setItems(facade.list(cfg));
        page.setConfig(cfg);
        
        return page;
    }
}
