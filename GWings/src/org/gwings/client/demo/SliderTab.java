package org.gwings.client.demo;

import org.gwings.client.ui.Slider;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
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
public class SliderTab extends AbstractDemoPanel {
	private FlexTable layout;
	private Slider horizontalSlider;
	private Slider verticalSlider;
	private CheckBox checkHorizontal;
	private CheckBox checkVertical;
	private TextBox horizontalValueBox;
	private TextBox verticalValueBox;
	
	public SliderTab() {
		initialize();
		setupUI();
		setupListeners();
	}

	private void initialize() {
		checkHorizontal = new CheckBox();
		checkVertical = new CheckBox();
		horizontalSlider = new Slider();
		horizontalValueBox = new TextBox();
		verticalSlider = new Slider(false);
		verticalValueBox = new TextBox();
		layout = new FlexTable();
		
		checkHorizontal.setChecked(horizontalSlider.isHorizontal());
		checkVertical.setChecked(verticalSlider.isHorizontal());
		checkHorizontal.setEnabled(false);
		checkVertical.setEnabled(false);
		
		horizontalSlider.setMaxValue(new Integer(30));
		horizontalSlider.setMinValue(new Integer(0));
		horizontalSlider.setValue(new Integer(20));
		horizontalSlider.setIncrement(new Integer(10));
		
		horizontalValueBox.setText(horizontalSlider.getValue().toString());
	}
	private void setupUI() {
//		initWidget(layout);
//		RootPanel.get().add(layout);
		add(layout);
		
		horizontalSlider.setPixelSize(300, 150);
		verticalSlider.setPixelSize(30, 300);
		
		layout.setWidget(0, 0, horizontalSlider);
		layout.setWidget(0, 1, verticalSlider);
		
//		layout.setBorderWidth(1);
	}

	private void setupListeners() {
		checkHorizontal.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				horizontalSlider.setHorizontal(checkHorizontal.isChecked());
			}
		});
		checkVertical.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				verticalSlider.setHorizontal(!checkHorizontal.isChecked());
			}
		});
		horizontalSlider.addChangeListener(new ChangeListener() {
			public void onChange(Widget sender) {
				horizontalValueBox.setText(horizontalSlider.getValue().toString());
			}
		});
		horizontalValueBox.addKeyboardListener(new KeyboardListenerAdapter(){
			public void onKeyUp(Widget sender, char keyCode, int modifiers) {
				if(keyCode == KEY_ENTER){
					submitHorizontalChange();
				}
			}
		});
		horizontalValueBox.addChangeListener(new ChangeListener() {
			public void onChange(Widget sender) {
				submitHorizontalChange();
			}
		});
		
	}
	
	public FlexTable getProperties() {
		FlexTable flex = new FlexTable();
		flex.setWidget(0, 0, new Label("Horizontal Slider"));
		flex.setWidget(1, 1, new Label("Horizontal"));
		flex.setWidget(1, 2, checkHorizontal);
		flex.setWidget(2, 1, new Label("Value"));
		flex.setWidget(2, 2, horizontalValueBox);
		
		flex.setWidget(3, 0, new Label("Vertical Slider"));
		flex.setWidget(4, 1, new Label("Horizontal"));
		flex.setWidget(4, 2, checkVertical);
		flex.setWidget(5, 1, new Label("Value"));
		flex.setWidget(5, 2, verticalValueBox);
		return flex;
	}

	public HTML getLinks() {
		return new HTML("Slider");
	}

	/**
	 * 
	 */
	private void submitHorizontalChange() {
		try{
			int value = Integer.parseInt(horizontalValueBox.getText());
			horizontalSlider.setValue(new Integer(value));
		}
		catch (Exception e) {
			Window.alert("Please insert a valid integer value.");
			DeferredCommand.add(new Command() {
				public void execute() {
					horizontalValueBox.selectAll();
					horizontalValueBox.setFocus(true);
				}
			});
		}
	}
}