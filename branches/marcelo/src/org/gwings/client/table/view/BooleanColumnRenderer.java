/**
 * 
 */
package org.gwings.client.table.view;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Widget;


/**
 * @author USER
 *
 */
public class BooleanColumnRenderer implements ColumnRenderer<Boolean> {
    private CheckBox renderer;
    
    public BooleanColumnRenderer(){
        renderer = new CheckBox();
    }
    
    public Widget renderType(Boolean value) {
        
        renderer.setChecked(value);
        return renderer;
    }

}
