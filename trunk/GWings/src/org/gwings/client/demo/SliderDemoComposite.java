package org.gwings.client.demo;

import org.gwings.client.ui.Slider;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
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
public class SliderDemoComposite extends Composite {
	private DockPanel layout;
	private Slider slider;

	public SliderDemoComposite() {
		initialize();
		setupUI();
	}

	/**
	 * 
	 */
	private void initialize() {
		slider = new Slider();
		layout = new DockPanel();
	}
	private void setupUI() {
		initWidget(layout);
		slider.setSize("300px", "10px");
		layout.setHorizontalAlignment(DockPanel.ALIGN_CENTER);
		layout.setVerticalAlignment(DockPanel.ALIGN_MIDDLE);
		
		layout.add(slider, DockPanel.CENTER);
		layout.setSize("100%", "100%");
		layout.setBorderWidth(1);
	}
}
