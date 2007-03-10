package org.gwings.client.demo;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.WindowResizeListener;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SourcesTabEvents;
import com.google.gwt.user.client.ui.TabListener;
import com.google.gwt.user.client.ui.TabPanel;

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
public class GWingsDemo implements EntryPoint, WindowResizeListener {
	private TabPanel panel;

	public void onModuleLoad() {

		Window.enableScrolling(false);
		Window.setMargin("5px");
		Window.addWindowResizeListener(this);

		panel = new TabPanel();

		panel.add(new TableDemoComposite(), "Table");
		panel.add(new ListSelectorComposite(), "Selector");
		//panel.add(new SliderDemoComposite(), "Slider");
		
		DeferredCommand.add(new Command() {
			public void execute() {
				int width = Window.getClientWidth();
				int height = Window.getClientHeight();

				onWindowResized(width, height);
				panel.selectTab(0);
			}
		});

		panel.addTabListener(new TabListener() {
		
			public void onTabSelected(SourcesTabEvents sender, int tabIndex) {
				int index = panel.getDeckPanel().getVisibleWidget();
				panel.getDeckPanel().getWidget(index).setStyleName("selected");
			}
		
			public boolean onBeforeTabSelected(SourcesTabEvents sender, int tabIndex) {
				return true;
			}
		
		});
		
		RootPanel.get().add(panel);
	}

	public void onWindowResized(int width, int height) {
		panel.setPixelSize(width - 10, height - 10);
	}
}
