/**
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
 */
package org.gwings.client.demo;

import java.util.Date;

import org.gwings.client.ui.Spinner;
import org.gwings.client.ui.impl.DateBoundModel;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
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
 * @since 08/03/2007
 */
public class SpinnerTab extends AbstractDemoPanel {
	private Spinner numberSpinner;
	private Spinner dateSpinner;
	private Label labelNumber;
	private Label labelDate;
	private DockPanel layout;
	private TextBox maxNumberValueBox;
	private TextBox minNumberValueBox;
	private TextBox incrementValueBox;
	private CheckBox isNumberUnlimited;
	
	
	public SpinnerTab(){
		initialize();
		setupUI();
		setupStyles();
		setupListeners();
	}
	
	private void initialize() {
		labelNumber = new Label("Number Spinner");
		labelDate = new Label("Date spinner");
		numberSpinner = new Spinner();
		dateSpinner = new Spinner();
		layout = new DockPanel();
		
		numberSpinner.setMinValue(new Integer(0));
		numberSpinner.setMaxValue(new Integer(100));
		numberSpinner.setValue(new Integer(50));
		
		dateSpinner.setSpinnerModel(new DateBoundModel());
		dateSpinner.setMinValue(new Date());
		
		maxNumberValueBox = new TextBox();
		minNumberValueBox = new TextBox();
		incrementValueBox = new TextBox();
		isNumberUnlimited = new CheckBox();

		maxNumberValueBox.setText(numberSpinner.getMaxValue().toString());
		minNumberValueBox.setText(numberSpinner.getMinValue().toString());
		incrementValueBox.setText(numberSpinner.getIncrement().toString());
		isNumberUnlimited.setChecked(numberSpinner.isLimited());
	}

	private void setupUI() {
//		initWidget(layout);
		add(layout);
		
		FlexTable flex = new FlexTable();
		flex.setWidget(0, 0, labelNumber);
		flex.setWidget(0, 1, numberSpinner);
		flex.setWidget(1, 0, labelDate);
		flex.setWidget(1, 1, dateSpinner);

		layout.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		layout.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		layout.add(flex, DockPanel.CENTER);
		layout.setSize("100%", "100%");
	}

	private void setupStyles() {
		labelNumber.setStyleName("numberStyle");
		labelDate.setStyleName("dateStyle");
	}

	private void setupListeners() {
		minNumberValueBox.addChangeListener(new ChangeListener() {
			public void onChange(Widget sender) {
				updateMinNumberValue();
			}
		});
		minNumberValueBox.addKeyboardListener(new KeyboardListenerAdapter() {
			public void onKeyUp(Widget sender, char keyCode, int modifiers) {
				if(keyCode == KEY_ENTER){
					updateMinNumberValue();
				}
			}
		});
		maxNumberValueBox.addChangeListener(new ChangeListener() {
			public void onChange(Widget sender) {
				updateMaxNumberValue();
			}
		});
		maxNumberValueBox.addKeyboardListener(new KeyboardListenerAdapter() {
			public void onKeyUp(Widget sender, char keyCode, int modifiers) {
				if(keyCode == KEY_ENTER){
					updateMaxNumberValue();
				}
			}
		});
		incrementValueBox.addChangeListener(new ChangeListener() {
			public void onChange(Widget sender) {
				updateNumberIncrement();
			}
		});
		incrementValueBox.addKeyboardListener(new KeyboardListenerAdapter() {
			public void onKeyUp(Widget sender, char keyCode, int modifiers) {
				if(keyCode == KEY_ENTER){
					updateNumberIncrement();
				}
			}
		});
		isNumberUnlimited.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				numberSpinner.setLimited(isNumberUnlimited.isChecked());
			}
		});
	}

	public FlexTable getProperties() {
		FlexTable flex = new FlexTable();
		flex.setWidget(0, 0, new Label("Number Spinner"));
		flex.setWidget(1, 1, new Label("Min Value"));
		flex.setWidget(1, 2, minNumberValueBox);
		flex.setWidget(2, 1, new Label("Max Value"));
		flex.setWidget(2, 2, maxNumberValueBox);
		flex.setWidget(3, 1, new Label("Increment Value"));
		flex.setWidget(3, 2, incrementValueBox);
		flex.setWidget(4, 1, new Label("Limited"));
		flex.setWidget(4, 2, isNumberUnlimited);
		
		
		flex.setWidget(5, 0, new Label("Date Spinner"));
		return flex;
	}

	public HTML getLinks() {
		return new HTML("Spinner");
	}

	/**
	 * 
	 */
	private Integer setValue(TextBox field) {
		try{
			return new Integer(field.getText());
		}
		catch (Exception e) {
			Window.alert("Please, insert a valid integer.");
			DeferredCommand.add(new Command() {
				public void execute() {
					minNumberValueBox.selectAll();
					minNumberValueBox.setFocus(true);
				}
			});
		}
		return null;
	}

	/**
	 * 
	 */
	private void updateMinNumberValue() {
		Integer value = setValue(minNumberValueBox);
		if(value != null){
			numberSpinner.setMinValue(value);
		}
	}

	/**
	 * 
	 */
	private void updateMaxNumberValue() {
		Integer value = setValue(maxNumberValueBox);
		if(value != null){
			numberSpinner.setMaxValue(value);
		}
	}

	/**
	 * 
	 */
	private void updateNumberIncrement() {
		Integer value = setValue(incrementValueBox);
		if(value != null){
			numberSpinner.setIncrement(value);
		}
	}
	
}
