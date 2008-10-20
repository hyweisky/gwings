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
    
    @Resource("org/gwings/public/pics/pagingBar/first.png")
    public AbstractImagePrototype first();
    
    @Resource("org/gwings/public/pics/pagingBar/firstOver.png")
    public AbstractImagePrototype firstOver();

    @Resource("org/gwings/public/pics/pagingBar/previous.png")
    public AbstractImagePrototype previous();
    
    @Resource("org/gwings/public/pics/pagingBar/previousOver.png")
    public AbstractImagePrototype previousOver();
    
    @Resource("org/gwings/public/pics/pagingBar/previousDisabled.png")
    public AbstractImagePrototype previousDisabled();
    
    @Resource("org/gwings/public/pics/pagingBar/last.png")
    public AbstractImagePrototype last();

    @Resource("org/gwings/public/pics/pagingBar/lastOver.png")
    public AbstractImagePrototype lastOver();
    
    @Resource("org/gwings/public/pics/pagingBar/next.png")
    public AbstractImagePrototype next();
    
    @Resource("org/gwings/public/pics/pagingBar/nextOver.png")
    public AbstractImagePrototype nextOver();
    
    @Resource("org/gwings/public/pics/pagingBar/nextDisabled.png")
    public AbstractImagePrototype nextDisabled();
    
    @Resource("org/gwings/public/pics/pagingBar/separator.png")
    public AbstractImagePrototype separator();

    @Resource("org/gwings/public/pics/pagingBar/last.png")
    public AbstractImagePrototype lastDisabled();
    
    @Resource("org/gwings/public/pics/pagingBar/first.png")
    public AbstractImagePrototype firstDisabled();
}
