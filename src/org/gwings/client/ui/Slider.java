package org.gwings.client.ui;

import org.gwings.client.ui.impl.IntegerBoundModel;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ChangeListenerCollection;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.MouseListenerAdapter;
import com.google.gwt.user.client.ui.SourcesChangeEvents;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HTMLTable.CellFormatter;
/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * Copyright 2007 Marcelo Emanoel B. Diniz <marceloemanoel AT gmail.com>
 *
 * @author Marcelo Emanoel
 * @since 07/03/2007
 */
public class Slider extends AbsolutePanel implements SourcesChangeEvents{
	
	private HTML cursor;
	private FlexTable layout;
	private boolean dragging;
	private int startX, startY;
	private int width;
	private int height;
	
	private boolean horizontal;
	private BoundModel sliderModel;
	private ChangeListenerCollection changeListenerCollection;
	private int xMin;
	private int yMin;
	private int xMax;
	private int yMax;
	
	public Slider(boolean horizontal){
		initialize(horizontal);
		setupUI();
		setupListeners();
		placeCursor();
	}
	
	public Slider(){
		this(true);
	}

	private void initialize(boolean horizontal) {
		layout = new FlexTable();
		cursor = new HTML("<div></div>");
		
		changeListenerCollection = new ChangeListenerCollection();
		
		setSliderModel(new IntegerBoundModel());
		setHorizontal(horizontal);
		
	}
	
	private void setupUI() {
		add(layout);
		layout.setCellSpacing(0);
		layout.setCellPadding(0);
		
//		layout.setBorderWidth(1);
	}

	private void setupListeners() {
		cursor.addMouseListener(new MouseListenerAdapter() {
			public void onMouseDown(Widget sender, int x, int y) {
				int widgetLeft = getWidgetLeft(cursor);
				int widgetTop = getWidgetTop(cursor);
				dragging = true;
				DOM.setCapture(cursor.getElement());

				startX = (isHorizontal() ? x + widgetLeft : widgetLeft);
				startY = (isHorizontal() ? widgetTop : y + widgetTop);
			}
			public void onMouseMove(Widget sender, int x, int y) {
				xMin = getWidgetLeft(layout);
				yMin = getWidgetTop(layout);
				xMax = layout.getOffsetWidth() - cursor.getOffsetWidth();
				yMax = layout.getOffsetHeight() - cursor.getOffsetHeight();
				if (dragging) {
					if(isHorizontal()){
						int absX = x + cursor.getAbsoluteLeft();
						int newX = absX - startX;
						if ((xMin <= newX && newX <= xMax)) {
							setCursorPosition(newX, startY);
							updateValue(newX);
						}
					}
					else{
						if(!isHorizontal()){
							int absY = y + cursor.getAbsoluteTop();
							int newY = absY - startY;
							if ((yMin <= newY && newY <= yMax)) {
								setCursorPosition(startX, newY);
								updateValue(newY);
							}
						}
					}
				}
			}

			public void onMouseUp(Widget sender, int x, int y) {
				dragging = false;
				DOM.releaseCapture(cursor.getElement());
			}
		});
	}
	
	private void updateUI() {
		int zIndex = DOM.getIntStyleAttribute(layout.getElement(), "zindex");
		DOM.setIntStyleAttribute(cursor.getElement(), "zindex", ++zIndex);
		DOM.setStyleAttribute(cursor.getElement(), "position", "absolute");
		
		if(isHorizontal()){
			layout.setWidget(0, 0, makeDiv());
			layout.setWidget(0, 1, makeDiv());
			layout.setWidget(0, 2, makeDiv());
		}
		else{
			layout.setWidget(0, 0, makeDiv());
			layout.setWidget(1, 0, makeDiv());
			layout.setWidget(2, 0, makeDiv());
		}
		setupStyles();
	}

	/**
	 * @return
	 */
	private HTML makeDiv() {
		HTML div = new HTML("<div></div>");
		div.setSize("100%", "100%");
		return div;
	}

	private void setupStyles() {
		setStyleName("org_gwings_Slider");
		CellFormatter formatter = layout.getCellFormatter();
		if(isHorizontal()){
			cursor.setStyleName("horizontalCursor");
			formatter.setStyleName(0, 0, "horizontalLeading");
			formatter.setStyleName(0, 1, "horizontalBar");
			formatter.setStyleName(0, 2, "horizontalTrailing");
		}
		else{
			cursor.setStyleName("verticalCursor");
			formatter.setStyleName(0, 0, "verticalLeading");
			formatter.setStyleName(1, 0, "verticalBar");
			formatter.setStyleName(2, 0, "verticalTrailing");
		}
	}

	private void updateValue(int pos){
		int value;
		int max = getMaxValue().intValue();
		int min = getMinValue().intValue();
		
		if(isHorizontal()){
			value = (((pos - xMin)*(max-min))/(xMax - xMin)) + min;
		}
		else{
			value = (((pos - yMin)*(max - min))/(yMax - yMin)) + min;
		}
		setValue(new Integer(value));
	}
	
	private void placeCursor() {
		DeferredCommand.add(new Command() {
			public void execute() {
				int leftPosition;
				int topPosition;
				leftPosition = layout.getAbsoluteLeft();
				topPosition = layout.getAbsoluteTop();
				setCursorPosition(leftPosition, topPosition);
				add(cursor);
			}
		
		});
	}
	
	public void setPixelSize(int width, int height) {
		setWidth(width);
		setHeight(height);
		super.setSize(width+"px", height+"px");
		if(isHorizontal()){
			layout.setSize(width+"px", "10px");
			final int newTop = height;
			setWidgetPosition(layout, 0, newTop-14);
			DeferredCommand.add(new Command() {
				public void execute() {
					setCursorPosition(cursor.getAbsoluteLeft(), newTop-35);
				}
			});
		}
		else{
			layout.setSize("10px", height+"px");
			final int newLeft = width;
			setWidgetPosition(layout, (newLeft -14), 0);
			DeferredCommand.add(new Command() {
				public void execute() {
					setCursorPosition(newLeft-35, cursor.getAbsoluteTop());
				}
			});
		}
	}

	/**
	 * @deprecated
	 */
	public void setSize(String width, String height) {}
	
	private void setCursorPosition(int left, int top) {
	    Element elem = cursor.getElement();
	    DOM.setStyleAttribute(elem, "left", left + "px");
	    DOM.setStyleAttribute(elem, "top", top + "px");
	}
	
	private void updateCursorPosition() {
		DeferredCommand.add(new Command() {
			public void execute() {
				xMin = getWidgetLeft(layout);
				yMin = getWidgetTop(layout);
				xMax = layout.getOffsetWidth() - cursor.getOffsetWidth();
				yMax = layout.getOffsetHeight() - cursor.getOffsetHeight();
				
				int value = ((Integer)getSliderModel().getValue()).intValue();
				int max = ((Integer)getSliderModel().getFinish()).intValue();
				int min = ((Integer)getSliderModel().getStart()).intValue();
				
				if (max != min) {
					if (isHorizontal()) {
						int x = ((xMax - xMin) * (value - min)) / (max - min);
						setCursorPosition(x, getWidgetTop(cursor));
					} else {
						int y = ((yMax - yMin) * (value - min)) / (max - min);
						setCursorPosition(getWidgetLeft(cursor), y);
					}
				}
			}
		});
	}
	
	/**
	 * @return the horizontal
	 */
	public boolean isHorizontal() {
		return horizontal;
	}

	/**
	 * @param horizontal the horizontal to set
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
		updateUI();
	}

	/**
	 * @return the model
	 */
	public BoundModel getSliderModel() {
		return sliderModel;
	}

	/**
	 * @param model the model to set
	 */
	public void setSliderModel(BoundModel model) {
		this.sliderModel = model;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
		layout.setHeight(height+"px");
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
		layout.setWidth(width+"px");
	}
	
	public void setMaxValue(Integer maxValue){
		getSliderModel().setFinish(maxValue);
	}
	public Integer getMaxValue(){
		return (Integer) getSliderModel().getFinish();
	}
	public void setMinValue(Integer minValue){
		getSliderModel().setStart(minValue);
	}
	public Integer getMinValue(){
		return (Integer) getSliderModel().getStart();
	}
	public void setValue(Integer value){
		getSliderModel().setValue(value);
		updateCursorPosition();
		changeListenerCollection.fireChange(this);
	}
	
	public void setIncrement(Integer increment){
		getSliderModel().setIncrement(increment);
	}
	
	public Integer getIncrement(){
		return (Integer) getSliderModel().getIncrement();
	}
	
	public Integer getValue(){
		return (Integer) getSliderModel().getValue();
	}

	public void addChangeListener(ChangeListener listener) {
		changeListenerCollection.add(listener);
	}

	public void removeChangeListener(ChangeListener listener) {
		changeListenerCollection.remove(listener);
	}
}
