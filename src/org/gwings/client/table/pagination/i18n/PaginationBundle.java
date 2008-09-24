/**
 * 
 */
package org.gwings.client.table.pagination.i18n;

import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

/**
 * @author USER
 */
public interface PaginationBundle extends ImageBundle {
    
    @Resource("br/com/aptools/public/pics/pagination/first.png")
    public AbstractImagePrototype first();
    
    @Resource("br/com/aptools/public/pics/pagination/first-over.png")
    public AbstractImagePrototype firstOver();

    @Resource("br/com/aptools/public/pics/pagination/previous.png")
    public AbstractImagePrototype previous();
    
    @Resource("br/com/aptools/public/pics/pagination/previous-over.png")
    public AbstractImagePrototype previousOver();

    @Resource("br/com/aptools/public/pics/pagination/last.png")
    public AbstractImagePrototype last();

    @Resource("br/com/aptools/public/pics/pagination/last-over.png")
    public AbstractImagePrototype lastOver();
    
    @Resource("br/com/aptools/public/pics/pagination/next.png")
    public AbstractImagePrototype next();
    
    @Resource("br/com/aptools/public/pics/pagination/next-over.png")
    public AbstractImagePrototype nextOver();
    
    @Resource("br/com/aptools/public/pics/pagination/separator.png")
    public AbstractImagePrototype separator();
}
