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
    
    @Resource("org/gwings/public/pics/pagingBar/fast_backward.gif")
    public AbstractImagePrototype first();
    
    @Resource("org/gwings/public/pics/pagingBar/fast_backward.gif")
    public AbstractImagePrototype firstOver();

    @Resource("org/gwings/public/pics/pagingBar/backward.gif")
    public AbstractImagePrototype previous();
    
    @Resource("org/gwings/public/pics/pagingBar/backward.gif")
    public AbstractImagePrototype previousOver();

    @Resource("org/gwings/public/pics/pagingBar/fast_forward.gif")
    public AbstractImagePrototype last();

    @Resource("org/gwings/public/pics/pagingBar/fast_forward.gif")
    public AbstractImagePrototype lastOver();
    
    @Resource("org/gwings/public/pics/pagingBar/forward.gif")
    public AbstractImagePrototype next();
    
    @Resource("org/gwings/public/pics/pagingBar/forward.gif")
    public AbstractImagePrototype nextOver();
    
    @Resource("org/gwings/public/pics/pagingBar/separator.png")
    public AbstractImagePrototype separator();
}
