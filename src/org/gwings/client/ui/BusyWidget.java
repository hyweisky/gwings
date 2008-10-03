/**
 * 
 */
package org.gwings.client.ui;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author USER
 */
public class BusyWidget extends PopupPanel {

    private static final String PRIMARY_STYLE = "org_gwings_BusyWidget";
    private static final String VISIBLE_STYLE = "visible";
    private static final String HIDDEN_STYLE = "hidden";
    
    private HTML div;
    private Widget workingWidget;
    
    public BusyWidget(Widget workingWidget) {
        setWorkingWidget(workingWidget);
        init();
        setupStyles();
    }

    private void init() {
        div = new HTML("<div></div>");
        add(div);
    }
    
    private void setupStyles() {
        div.setStylePrimaryName(PRIMARY_STYLE);
        setStylePrimaryName(PRIMARY_STYLE);
    }
    
    public void setVisible(boolean visible){
        if(visible){
            updatePosition();
            updateSize();
            div.removeStyleDependentName(HIDDEN_STYLE);
            div.addStyleDependentName(VISIBLE_STYLE);
            show();
        }
        else{
            div.removeStyleDependentName(VISIBLE_STYLE);
            div.addStyleDependentName(HIDDEN_STYLE);
            hide(true);
        }
    }
    
    private void updateSize() {
        int width = getWorkingWidget().getOffsetWidth();
        int height = getWorkingWidget().getOffsetHeight();
        
        div.setPixelSize(width, height);
    }

    private void updatePosition(){
        int top = getWorkingWidget().getAbsoluteTop();
        int left = getWorkingWidget().getAbsoluteLeft();
        
        setPopupPosition(left, top);
    }

    /**
     * @param workingWidget the workingWidget to set
     */
    public void setWorkingWidget(Widget workingWidget) {
        this.workingWidget = workingWidget;
    }

    /**
     * @return the workingWidget
     */
    public Widget getWorkingWidget() {
        return workingWidget;
    }
}
