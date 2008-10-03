/**
 * 
 */
package org.gwings.client.table.view;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MouseListenerAdapter;
import com.google.gwt.user.client.ui.Widget;


/**
 * @author USER
 *
 */
public class DateTimeColumnRenderer implements ColumnRenderer<Date> {
    private static final String STYLE_NAME = "org_gwings_DateRenderer";
    private static final String STYLE_OVER = "mouseOver";

    private DateTimeFormat format;
    private Label renderer;
    
    public DateTimeColumnRenderer(String pattern){
        format = DateTimeFormat.getFormat(pattern);
        renderer = new Label();
        setStyles();
    }

    private void setStyles() {
        renderer.setStylePrimaryName(STYLE_NAME);
        renderer.addMouseListener(new MouseListenerAdapter() {
            public void onMouseEnter(Widget sender) {
                renderer.addStyleDependentName(STYLE_OVER);
            }
            public void onMouseLeave(Widget sender) {
                renderer.removeStyleDependentName(STYLE_OVER);
            }
        });
    }
    
    public Widget renderType(Date value) {
        renderer.setText(format.format(value));
        return renderer;
    }
    
    
}
