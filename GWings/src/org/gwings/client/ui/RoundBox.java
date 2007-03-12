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
package org.gwings.client.ui;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;
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
 * @author Marcelo Emanoel, Luciano Broussal
 * @since 11/03/2007
 */
public class RoundBox extends Widget {
	private HTML nwDiv;
	private HTML swDiv;
	private HTML neDiv;
	private HTML seDiv;
	private HTML wDiv;
	private HTML eDiv;
	private HTML nDiv;
	private HTML sDiv;
	private FlexTable layout;
	private Widget content;
	
	public RoundBox(){
		initialize();
		setupUI();
		setupStyles();
		setupListeners();
	}

	private void initialize() {
		nwDiv = makeDiv("nw");
		swDiv = makeDiv("sw");
		neDiv = makeDiv("ne");
		seDiv = makeDiv("se");
		wDiv = makeDiv("w");
		eDiv = makeDiv("e");
		nDiv = makeDiv("n");
		sDiv = makeDiv("s");
		layout = new FlexTable();
		setContent(new SimplePanel());
		setElement(layout.getElement());
	}

	/**
	 * @return
	 */
	private HTML makeDiv(String e) {
		HTML html = new HTML("<div></div>");
		html.setSize("100%", "100%");
		return html;
	}

	private void setupUI() {
		layout.setWidget(0, 0, nwDiv);
		layout.setWidget(2, 0, swDiv);
		
		layout.setWidget(0, 1, nDiv);
		layout.setWidget(2, 1, sDiv);
		layout.setWidget(1, 0, wDiv);
		layout.setWidget(1, 2, eDiv);
		
		layout.setWidget(0, 2, neDiv);
		layout.setWidget(2, 2, seDiv);
		
		layout.setCellPadding(0);
		layout.setCellSpacing(0);
//		layout.setBorderWidth(1);
	}

	private void setupStyles() {
		layout.setStyleName("org_gwings_RoundBox");
		layout.getCellFormatter().setStyleName(0, 0, "nw");
		layout.getCellFormatter().setStyleName(2, 0, "sw");
		layout.getCellFormatter().setStyleName(0, 1, "n");
		layout.getCellFormatter().setStyleName(2, 1, "s");
		layout.getCellFormatter().setStyleName(1, 0, "w");
		layout.getCellFormatter().setStyleName(1, 2, "e");
		layout.getCellFormatter().setStyleName(0, 2, "ne");
		layout.getCellFormatter().setStyleName(2, 2, "se");
	}

	private void setupListeners() {}

	/**
	 * @return the content
	 */
	public Widget getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(Widget content) {
		this.content = content;
		if(content != null){
			layout.setWidget(1, 1, this.content);
		}
	}
}
