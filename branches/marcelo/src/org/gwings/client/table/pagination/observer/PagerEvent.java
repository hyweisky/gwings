package org.gwings.client.table.pagination.observer;

import java.util.EventObject;

import org.gwings.client.table.model.Plotable;
import org.gwings.client.table.pagination.model.Pager;


public class PagerEvent<T extends Plotable> extends EventObject{

    private static final long serialVersionUID = -7827016929684045928L;

    public PagerEvent(Pager<T> pager) {
        super(pager);
    }
    
    @SuppressWarnings("unchecked")
    public Pager<T> getPager(){
        return (Pager<T>) getSource();
    }
}
