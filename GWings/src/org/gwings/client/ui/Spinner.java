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
package org.gwings.client.ui;

import org.gwings.client.ui.impl.IntegerBoundModel;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ChangeListenerCollection;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SourcesChangeEvents;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
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
 * Copyright 2007 Marcelo Emanoel B. Diniz <marceloemanoel AT gmail.com> , <luciano.broussal AT gmail.com>
 *
 * @author Marcelo Emanoel, Luciano Broussal
 * @since 07/03/2007
 */
public class Spinner extends SimplePanel implements SourcesChangeEvents, BoundModelListener{
	
	private TextBox field;
	private Button incrementButton;
	private Button decrementButton;
	private FlexTable layout;
	private BoundModel spinnerModel;
	private ChangeListenerCollection changeListeners;
	
	public Spinner(){
		this(new IntegerBoundModel());
	}
	
	public Spinner(BoundModel model){
		super();
		initialize();
		setupUI();
		setupStyles();
		setupListeners();
		setSpinnerModel(model);
	}
	
	private void initialize() {
		field = new TextBox();
		incrementButton = new Button("");
		decrementButton = new Button("");
		layout = new FlexTable();
	}

	private void setupUI() {
		
		field.setTextAlignment(TextBox.ALIGN_RIGHT);
		field.setEnabled(false);
		
        VerticalPanel buttonPanel = new VerticalPanel();
		buttonPanel.add(incrementButton);
		buttonPanel.add(decrementButton);
		buttonPanel.setSpacing(0);
		
		layout.setCellSpacing(0);
		layout.setCellPadding(0);
		
		layout.setWidget(0,0,field);
		layout.setWidget(0, 1, buttonPanel);
		
		add(layout);
//		layout.setBorderWidth(1);
		
	}

	private void setupStyles() {
		setStyleName("org_gwings_Spinner");
		field.setStyleName("field");
		incrementButton.setStyleName("incrementButton");
		decrementButton.setStyleName("decrementButton");
	}

	private void setupListeners() {
		incrementButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				getSpinnerModel().increment();
				setTextValue();
			}
		});
		decrementButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				getSpinnerModel().decrement();
				setTextValue();
			}
		});
	}

	/**
	 * @return the spinnerModel
	 */
	public BoundModel getSpinnerModel() {
		return spinnerModel;
	}

	/**
	 * @param spinnerModel the spinnerModel to set
	 */
	public void setSpinnerModel(BoundModel spinnerModel) {
		this.spinnerModel = spinnerModel;
		setTextValue();
	}
	
	public Object getValue() {
		return getSpinnerModel().getValue();
	}

	public void setMinValue(Object minValue) {
		getSpinnerModel().setStart(minValue);
	}

	public void setMaxValue(Object maxValue) {
		getSpinnerModel().setFinish(maxValue);
	}

	public Object getMinValue() {
		return getSpinnerModel().getStart();
	}

	public Object getMaxValue() {
		return getSpinnerModel().getFinish();
	}

	public void setValue(Object value) {
		getSpinnerModel().setValue(value);
		setTextValue();
	}

	private void setTextValue() {
		field.setText(getSpinnerModel().formatValue());
	}

	public void setIncrement(Integer increment) {
		getSpinnerModel().setIncrement(increment);
	}

	public void setLimited(boolean limited) {
		getSpinnerModel().setLimited(limited);
	}

	public void addChangeListener(ChangeListener listener) {
		changeListeners.add(listener);
	}

	public void removeChangeListener(ChangeListener listener) {
		changeListeners.remove(listener);
	}

	public void valueDecremented() {
		changeListeners.fireChange(this);
	}

	public void valueIncremented() {
		changeListeners.fireChange(this);
	}

	public Integer getIncrement() {
		return (Integer) getSpinnerModel().getIncrement();
	}
}
