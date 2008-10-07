package org.gwings.server.demo.pagination;

import org.gwings.client.demo.LineConstants;
import org.gwings.client.demo.LinePlotable;
import org.gwings.client.table.pagination.model.DataProvider;
import org.gwings.client.table.pagination.model.Page;
import org.gwings.client.table.pagination.model.PageConfig;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;



public class RemoteLineProvider extends RemoteServiceServlet implements DataProvider<LinePlotable>, LineConstants {

    private static final long serialVersionUID = -6820131024394722617L;

    public Page<LinePlotable> fetchData(PageConfig config) throws Exception {
        LineProviderFacade facade = LineProviderFacade.getInstance();
       
        Page<LinePlotable> page = new Page<LinePlotable>();
        page.setItems(facade.list(config));
        page.setConfig(config);
        
        return page;
    }

    public Integer fetchSize() throws Exception {
        return LineProviderFacade.getInstance().countTotal();
    }

}
