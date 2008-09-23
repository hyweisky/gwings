/**
 * 
 */
package org.gwings.client.table.pagination;

import java.io.Serializable;

import org.gwings.client.table.Table;


import com.google.gwt.user.client.ui.Composite;


/**
 * @author USER
 *
 */
public class PaginationTable extends Composite {
    
    private Table table;
    private PaginationBar<? extends Serializable> paginationBar;
    
    public PaginationTable(Table table){
        
        init();
        setupLayout();
        setupListeners();
    }

    private void init() {
        // TODO Auto-generated method stub
        
    }

    private void setupLayout() {
        // TODO Auto-generated method stub
        
    }

    private void setupListeners() {
        // TODO Auto-generated method stub
        
    }
    
}
