/**
 * 
 */
package org.gwings.client.table.pagination.model;

import java.io.Serializable;

/**
 * @author USER
 */
public class PageConfig implements Serializable {

    private static final long serialVersionUID = -6606776183212269494L;

    private Integer totalAvailable;
    private Integer start;
    private Integer finish;

    public PageConfig() {
        setTotalAvailable(0);
        setStart(0);
        setFinish(0);
    }

    /**
     * @return the totalAvailable
     */
    public Integer getTotalAvailable() {
        return totalAvailable;
    }

    /**
     * @param totalAvailable
     *            the totalAvailable to set
     */
    public void setTotalAvailable(Integer totalAvailable) {
        this.totalAvailable = totalAvailable;
    }

    /**
     * @return the start
     */
    public Integer getStart() {
        return start;
    }

    /**
     * @param start
     *            the start to set
     */
    public void setStart(Integer start) {
        this.start = start;
    }

    /**
     * @return the finish
     */
    public Integer getFinish() {
        return finish;
    }

    /**
     * @param finish
     *            the finish to set
     */
    public void setFinish(Integer finish) {
        this.finish = finish;
    }

    public Integer getPageSize() {
        return Math.abs(start - finish);
    }
}
