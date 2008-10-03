package org.gwings.client.table.view;

import java.util.Collection;

import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;


public class CollectionColumnRenderer<T> implements ColumnRenderer<Collection<T>> {
    private ListBox listRenderer;
    
    public CollectionColumnRenderer(){
        listRenderer = new ListBox();
    }
    
    public Widget renderType(Collection<T> value) {
        listRenderer.clear();
        
        for(T t : value){
            listRenderer.addItem(t.toString());
        }
        
        return listRenderer;
    }
}
