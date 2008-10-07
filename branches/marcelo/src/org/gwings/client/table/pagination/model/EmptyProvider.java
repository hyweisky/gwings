package org.gwings.client.table.pagination.model;


public final class EmptyProvider<T> implements DataProvider<T> {

    private static final long serialVersionUID = 4404147801907185267L;

    public Page<T> fetchData(PageConfig config) throws Exception {
        return new Page<T>();
    }

    public Integer fetchSize() throws Exception {
        return 0;
    }

}