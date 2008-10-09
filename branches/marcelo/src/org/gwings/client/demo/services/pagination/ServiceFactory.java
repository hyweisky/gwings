/**
 * 
 */
package org.gwings.client.demo.services.pagination;

import com.google.gwt.core.client.GWT;


/**
 * @author USER
 *
 */
public class ServiceFactory {
    private static PaginatedLineProviderAsync service = null;
    
    public static PaginatedLineProviderAsync getPaginatedLineProviderService(){
        if(service ==  null){
            service = GWT.create(PaginatedLineProvider.class);
        }
        return service;
    }
}
