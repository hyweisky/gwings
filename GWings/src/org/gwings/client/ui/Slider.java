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
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MouseListenerAdapter;
import com.google.gwt.user.client.ui.SourcesChangeEvents;
import com.google.gwt.user.client.ui.Widget;
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
	
	private static final String LEFT_IMAGE_URL = "pics/slider/horizontal_left_slider.gif"; 
	private static final String RIGHT_IMAGE_URL = "pics/slider/horizontal_right_slider.gif"; 
	private static final String TOP_IMAGE_URL = "pics/slider/vertical_top_slider.gif"; 
	private static final String BOTTOM_IMAGE_URL = "pics/slider/vertical_bottom_slider.gif"; 
	private static final String VERTICAL_BAR_IMAGE_URL = "pics/slider/vertical_bar_slider.gif"; 
	private static final String HORIZONTAL_BAR_IMAGE_URL = "pics/slider/horizontal_bar_slider.gif"; 
	private static final String HORIZONTAL_CURSOR_IMAGE_URL = "pics/slider/horizontal_slider_cursor.gif";
	private static final String VERTICAL_CURSOR_IMAGE_URL = "pics/slider/vertical_slider_cursor.gif";
	
	private Image left;
	private Image right;
	private Image top;
	private Image bottom;
	private Image cursor;
	private HTML bar;
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
		left = new Image(LEFT_IMAGE_URL);
		right = new Image(RIGHT_IMAGE_URL);
		top = new Image(TOP_IMAGE_URL);
		bottom = new Image(BOTTOM_IMAGE_URL);
		bar = new HTML();
		cursor = new Image();
		
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

	private void updateUI() {
		int zIndex = DOM.getIntStyleAttribute(layout.getElement(), "zindex");
		DOM.setIntStyleAttribute(cursor.getElement(), "zindex", ++zIndex);
		DOM.setStyleAttribute(cursor.getElement(), "position", "absolute");

		String htmlBar;
		layout.clear();
		if(isHorizontal()){
			htmlBar = makeHorizontalBar();
			cursor.setUrl(HORIZONTAL_CURSOR_IMAGE_URL);
			
			layout.setWidget(0, 0, left);
			layout.setWidget(0, 1, bar);
			layout.setWidget(0, 2, right);
			
			layout.getCellFormatter().setWidth(0, 0, "10px");
			layout.getCellFormatter().setWidth(0, 2, "10px");
		}
		else{
			htmlBar = makeVerticalBar();
			cursor.setUrl(VERTICAL_CURSOR_IMAGE_URL);

			layout.setWidget(0, 0, top);
			layout.setWidget(1, 0, bar);
			layout.setWidget(2, 0, bottom);
			
			layout.getCellFormatter().setHeight(0, 0, "10px");
			layout.getCellFormatter().setHeight(2, 0, "10px");
		}
		bar.setHTML(htmlBar);
	}

	/**
	 * @return
	 */
	private String makeHorizontalBar() {
		return makeBar(HORIZONTAL_BAR_IMAGE_URL, "100%","10px");
	}

	private String makeVerticalBar(){
		return makeBar(VERTICAL_BAR_IMAGE_URL,"10px","100% ");
	}

	/**
	 * @param image
	 * @return
	 */
	private String makeBar(String image, String width, String height) {
		String theBar = "<div>" +
		"<img style=\"width:"+width+";height:"+height+";\" src=\""+image+"\"></img>" +
		"</div>";
		return theBar;
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
							System.out.println("X:"+xMin+"<="+newX+"<="+xMax);
							System.out.println("Y:"+cursor.getAbsoluteTop());
							System.out.println("Value:"+getValue());
							setCursorPosition(newX, startY);
							updateValue(newX);
						}
					}
					else{
						if(!isHorizontal()){
							int absY = y + cursor.getAbsoluteTop();
							int newY = absY - startY;
							if ((yMin <= newY && newY <= yMax)) {
								System.out.println("X:"+cursor.getAbsoluteLeft());
								System.out.println("Y:"+yMin+"<="+newY+"<="+yMax);
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
	
	private void updateValue(int pos){
		int value;
		int max = getMaxValue().intValue();
		int min = getMinValue().intValue();
		int inc = getIncrement().intValue();
		
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
				if(isHorizontal()){
					leftPosition = left.getAbsoluteLeft();
					topPosition = left.getAbsoluteTop();
				}
				else{
					leftPosition = bottom.getAbsoluteLeft();
					topPosition = bottom.getAbsoluteTop();
				}
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
				
				if(isHorizontal()){
					int x = ((xMax - xMin)*(value - min))/(max - min);
					setCursorPosition(x, getWidgetTop(cursor));
				}
				else{
					int y = ((yMax - yMin)*(value-min))/(max-min);
					setCursorPosition(getWidgetLeft(cursor), y);
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

	public void valueChanged(BoundEvent evt) {
		System.out.println("alterar a posição do cursor!");
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
