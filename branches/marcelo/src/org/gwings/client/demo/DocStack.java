package org.gwings.client.demo;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.StackPanel;

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
 * @since 09/03/2007
 */
public class DocStack extends StackPanel {
	private FlexTable tableProperties;
	private HTML usefulLinks;
	
	private SimplePanel propertiesPanel;
	private SimplePanel tipsPanel;
	
	
	public DocStack(){
		initilize();
		setupUI();
		setupStyles();
		setupListeners();
	}
	private void initilize() {
		propertiesPanel = new SimplePanel();
		tipsPanel = new SimplePanel();
	}

	private void setupUI() {
		setTableProperties(new FlexTable());
		setLinks(new HTML());
		
		add(propertiesPanel, "Properties");
		add(tipsPanel, "Useful Links");
		
		propertiesPanel.setHeight("100%");
		tipsPanel.setHeight("100%");
	}

	private void setupStyles() {}

	private void setupListeners() {}
	
	/**
	 * @param tableProperties the tableProperties to set
	 */
	public void setTableProperties(FlexTable tableProperties) {
		if(tableProperties != null){
			this.tableProperties = tableProperties;
			this.tableProperties.setStyleName("properties");
			propertiesPanel.setWidget(this.tableProperties);
			propertiesPanel.setStyleName("propertiesPanel");
		}
	}
	/**
	 * @param links the tips to set
	 */
	public void setLinks(HTML links) {
		if(links != null){
			this.usefulLinks = links;
			this.usefulLinks.setStyleName("usefulLinks");
			tipsPanel.setWidget(this.usefulLinks);
		}
	}
}