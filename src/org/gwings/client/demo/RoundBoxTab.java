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
 * Copyright 2007 Marcelo Emanoel B. Diniz <marceloemanoel AT gmail.com>, Luciano Broussal <luciano.broussal AT gmail.com>
 */
package org.gwings.client.demo;

import org.gwings.client.ui.Color;
import org.gwings.client.ui.ColouredBox;
import org.gwings.client.ui.RoundBox;

import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;

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
 * @author Marcelo Emanoel, Luciano Broussal
 * @since 11/03/2007
 */
public class RoundBoxTab extends AbstractDemoPanel {
	
	private RoundBox roundBox;
	private ColouredBox content;
	private DockPanel layout;
	
	public RoundBoxTab(){
		initialize();
		setupUI();
		setupStyles();
		setupListeners();
	}
	
	private void initialize() {
		roundBox = new RoundBox();
		layout = new DockPanel();
		content = new ColouredBox(new Color(99,165,255));
		roundBox.setContent(content);
	}

	private void setupUI() {
		add(layout);
		
		layout.setVerticalAlignment(DockPanel.ALIGN_MIDDLE);
		layout.setHorizontalAlignment(DockPanel.ALIGN_CENTER);
		
		layout.add(roundBox, DockPanel.CENTER);
		
		content.setPixelSize(300,100);
		layout.setSize("100%", "100%");
	}

	private void setupStyles() {}

	private void setupListeners() {}

	/* (non-Javadoc)
	 * @see org.gwings.client.demo.AbstractDemoPanel#getLinks()
	 */
	public HTML getLinks() {
		return new HTML();
	}

	/* (non-Javadoc)
	 * @see org.gwings.client.demo.AbstractDemoPanel#getProperties()
	 */
	public FlexTable getProperties() {
		return new FlexTable();
	}

}
