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

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

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

	private void setupListeners() {}

	public FlexTable getProperties() {
		return new FlexTable();
	}

	public HTML getLinks() {
		return new HTML("Spinner");
	}
	
}
