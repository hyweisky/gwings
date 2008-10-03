package org.gwings.client.table.model;

import org.gwings.client.table.view.ColumnOrder;
import org.gwings.client.table.view.ColumnRenderer;

public class Column<T> {

    private String name;
    private ColumnRenderer<T> type;
    private ColumnOrder order;
    private Integer position;

    public Column(String name, ColumnRenderer<T> type) {
        setName(name);
        setType(type);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ColumnRenderer<T> getType() {
        return this.type;
    }

    public void setType(ColumnRenderer<T> type) {
        this.type = type;
    }
    
    
    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        Column<T> col = (Column) obj;
        return col.getName().equals(getName());
    }

    
    /**
     * @return the order
     */
    public ColumnOrder getOrder() {
        return order;
    }

    
    /**
     * @param order the order to set
     */
    public void setOrder(ColumnOrder order) {
        this.order = order;
    }

    
    /**
     * @return the position
     */
    public Integer getPosition() {
        return position;
    }

    
    /**
     * @param position the position to set
     */
    public void setPosition(Integer position) {
        this.position = position;
    }
}