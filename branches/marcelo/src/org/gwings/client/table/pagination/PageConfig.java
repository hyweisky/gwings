/**
 * 
 */
package org.gwings.client.table.pagination;

import java.io.Serializable;

/**
 * @author USER
 */
public class PageConfig implements Serializable, Cloneable {

    private static final long serialVersionUID = 4276442839446364658L;

    private Integer size;
    private Integer first;
    private Integer last;
    private Integer current;
    
    public PageConfig() {
        this(0, 0, 0);
    }

    public PageConfig(Integer first, Integer last, int size) {
        setFirst(first);
        setLast(last);
        setSize(size);
    }

    /**
     * @return the size
     */
    public Integer getSize() {
        return size;
    }

    /**
     * @param size
     *            the size to set
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * @return the first
     */
    public Integer getFirst() {
        return first;
    }

    /**
     * @param first
     *            the first to set
     */
    public void setFirst(Integer first) {
        this.first = first;
    }

    /**
     * @return the last
     */
    public Integer getLast() {
        return last;
    }

    /**
     * @param last
     *            the last to set
     */
    public void setLast(Integer last) {
        this.last = last;
    }

    /**
     * @return the current
     */
    public Integer getCurrent() {
        return current;
    }

    /**
     * @param current the current to set
     */
    public void setCurrent(Integer current) {
        this.current = current;
    }
    
    protected PageConfig clone(){
        PageConfig clone = new PageConfig();
        clone.setCurrent(getCurrent());
        clone.setFirst(getFirst());
        clone.setLast(getLast());
        clone.setSize(getSize());
        return clone;
    }
}