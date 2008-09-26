package org.gwings.client.table;

public class Column {

    private String name;
    private ColumnRenderer type;
    private ColumnOrder order;
    private Integer position;

    public Column(String name, ColumnRenderer type) {
        setName(name);
        setType(type);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ColumnRenderer getType() {
        return this.type;
    }

    public void setType(ColumnRenderer type) {
        this.type = type;
    }

    public boolean equals(Object obj) {
        Column col = (Column) obj;
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