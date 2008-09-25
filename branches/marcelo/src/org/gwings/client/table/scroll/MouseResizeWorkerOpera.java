package org.gwings.client.table.scroll;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * The Opera version of the mouse worker fixes an Opera bug where the cursor
 * isn't updated if the mouse is hovering over an element DOM object when its
 * cursor style is changed.
 */
@SuppressWarnings("unused")
public class MouseResizeWorkerOpera extends MouseResizeWorker {

    /**
     * A div used to force the cursor to update.
     */
    private DivElement cursorUpdateDiv;

    /**
     * Constructor.
     */
    public MouseResizeWorkerOpera() {
        cursorUpdateDiv = Document.get().createDivElement();
        cursorUpdateDiv.getStyle().setProperty("position", "absolute");
    }

    /**
     * Set the current cell that will be resized based on the mouse event.
     * 
     * @param event
     *            the event that triggered the new cell
     * @return true if the cell was actually changed
     */
    @Override
    public boolean setCurrentCell(Event event) {
        // Check if cursor update div is active
        if (event.getTarget() == cursorUpdateDiv) {
            removeCursorUpdateDiv();
            return false;
        }

        // Use the parent method
        boolean cellChanged = super.setCurrentCell(event);

        // Position a div that forces a cursor redraw in Opera
        if (cellChanged) {
            Event.setCapture(getScrollTable().getHeaderWrapper());
            cursorUpdateDiv.getStyle().setPropertyPx("height", (Window.getClientHeight() - 1));
            cursorUpdateDiv.getStyle().setPropertyPx("width", (Window.getClientWidth() - 1));
            cursorUpdateDiv.getStyle().setProperty("left", "0px");
            cursorUpdateDiv.getStyle().setProperty("top", "0px");
            RootPanel.getBodyElement().appendChild(cursorUpdateDiv);
        }
        return cellChanged;
    }

    /**
     * Start resizing the current cell.
     * 
     * @param event
     *            the mouse event
     */
    @Override
    public void startResizing(Event event) {
        removeCursorUpdateDiv();
        super.startResizing(event);
    }

    /**
     * Remove the cursor update div from the page.
     */
    private void removeCursorUpdateDiv() {
        if (DOM.getCaptureElement() != null) {
            RootPanel.getBodyElement().removeChild(cursorUpdateDiv);
            Event.releaseCapture(getScrollTable().getHeaderWrapper());
        }
    }
}
