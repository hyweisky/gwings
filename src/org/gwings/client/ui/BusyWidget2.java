/**
 * 
 */
package org.gwings.client.ui;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author USER
 */
public class BusyWidget2 extends Widget {
    
    private HTML busyDiv;
    private Widget workingWidget;
    private PopupPanel popup;
    
    public BusyWidget2(Widget hideWidget) {
        setWorkingWidget(hideWidget);
        init();
    }

    private void init() {
        popup = new PopupPanel();
        busyDiv = new HTML("<div></div>");
        
        Style busyStyle = busyDiv.getElement().getStyle();
        
        busyStyle.setProperty("background-color", "white");
        busyStyle.setProperty("filter", "alpha(opacity=50)");
        busyStyle.setProperty("opacity", "0.5");
        busyStyle.setProperty("-moz-opacity", "0.5");
        busyStyle.setProperty("display", "block");
        
        setElement(popup.getElement());
        popup.add(busyDiv);
        
        RootPanel.get().add(popup);
        
        setStylePrimaryName("org_gwings_BusyWidget");
    }
    
    public void show(boolean visible){
        String visibility = visible ? "visible" : "hidden";
        Style busyStyle = busyDiv.getElement().getStyle();
        busyStyle.setProperty("visibility", visibility);
        if(visible && getWorkingWidget() != null){
            int absoluteLeft = workingWidget.getAbsoluteLeft();
            int absoluteTop = workingWidget.getAbsoluteTop();
            int offsetHeight = workingWidget.getOffsetHeight();
            int offsetWidth = workingWidget.getOffsetWidth();

            present(workingWidget);
            busyStyle.setPropertyPx("top", absoluteTop);
            busyStyle.setPropertyPx("left", absoluteLeft);
            busyStyle.setPropertyPx("width", offsetWidth);
            busyStyle.setPropertyPx("height", offsetHeight);
            busyStyle.setProperty("z-index", Integer.MAX_VALUE+"");
            
            popup.setAnimationEnabled(true);
            popup.setPixelSize(offsetWidth, offsetHeight);
            popup.setPopupPosition(absoluteLeft, absoluteTop);
            popup.show();
            
            DeferredCommand.addPause();
        }
    }

    private void present(Widget working) {
        Element workingElement = workingWidget.getElement();
        Style workingStyle = workingElement.getStyle();
        
        String zIndex = workingStyle.getProperty("z-index");
        String top = workingStyle.getProperty("top");
        String left = workingStyle.getProperty("left");
        String clientWidth = workingStyle.getProperty("clientWidth");
        String clientHeight = workingStyle.getProperty("clientHeight");
        
        int absoluteLeft = workingWidget.getAbsoluteLeft();
        int absoluteTop = workingWidget.getAbsoluteTop();
        int offsetHeight = workingWidget.getOffsetHeight();
        int offsetWidth = workingWidget.getOffsetWidth();
        
        System.out.println("Z-Index = "+zIndex);
        System.out.println("top = "+top);
        System.out.println("left = "+left);
        System.out.println("clientWidth = "+clientWidth);
        System.out.println("clientHeight = "+clientHeight);
        System.out.println();

        System.out.println("absoluteLeft = "+ absoluteLeft);
        System.out.println("absoluteTop = "+ absoluteTop);
        System.out.println("offsetHeight = "+offsetHeight);
        System.out.println("offsetWidth = "+offsetWidth);
        
        System.out.println("==================================");
    }

    
    /**
     * @return the workingWidget
     */
    public Widget getWorkingWidget() {
        return workingWidget;
    }

    
    /**
     * @param workingWidget the workingWidget to set
     */
    public void setWorkingWidget(Widget workingWidget) {
        this.workingWidget = workingWidget;
    }
}
