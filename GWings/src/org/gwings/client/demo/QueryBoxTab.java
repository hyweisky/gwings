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

import java.util.List;

import org.gwings.client.demo.services.QueryBoxService;
import org.gwings.client.demo.services.QueryBoxServiceAsync;
import org.gwings.client.ui.QueryBox;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
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
 * @since 09/03/2007
 */
public class QueryBoxTab extends AbstractDemoPanel {
	private QueryBox queryBox;
	private DockPanel layout;
	private HTML instructions;
	
	public QueryBoxTab(){
		initialize();
		setupUI();
		setupStyles();
		setupListeners();
	}
	
	private void initialize() {
		queryBox = new QueryBox();
		layout = new DockPanel();
		instructions = new HTML();
		
		initializeInstruction();
	}

	private void initializeInstruction() {
		String text = "<p>Items available on the server side:</p>" +
				"<ul>" +
					"<li>Java</li>" +
					"<li>Javascript</li>" +
					"<li>Virtual Machine</li>" +
					"<li>GWT</li>" +
					"<li>Garbage Collector</li>" +
					"<li>GWings</li>" +
					"<li>GWT Window Manager</li>" +
					"<li>GWT Widgets</li>" +
				"</ul>";
		instructions.setHTML(text);
	}

	private void setupUI() {
		add(layout);
		
		layout.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		layout.setVerticalAlignment(VerticalPanel.ALIGN_MIDDLE);
		
		layout.add(queryBox, DockPanel.CENTER);
		layout.add(instructions, DockPanel.EAST);
		
		layout.setSize("100%", "100%");
		instructions.setStyleName("instructions");
		layout.setCellVerticalAlignment(instructions, DockPanel.ALIGN_MIDDLE);
		layout.setCellHorizontalAlignment(instructions, DockPanel.ALIGN_CENTER);
		layout.setCellWidth(instructions, "30%");
		layout.setCellHeight(instructions, "80%");
		layout.setBorderWidth(1);
	}
	private void setupStyles() {}

	private void setupListeners() {
		queryBox.addKeyboardListener(new KeyboardListenerAdapter() {
		
			public void onKeyUp(Widget sender, char keyCode, int modifiers) {
				if(!queryBox.getText().equals("")){
					showPossibleResults();
				}
			}
		
		});
	}

	public void showPossibleResults(){
		QueryBoxServiceAsync service = QueryBoxService.Util.getInstance();
		service.getMatches(queryBox.getText(), new AsyncCallback() {
			public void onSuccess(Object result) {
				List matches = (List) result;
				queryBox.response(matches);
			}
			public void onFailure(Throwable caught) {
				String message = caught.getMessage();
				message += "\nPs.: This message is not necessary :)";
				Window.alert(message);
				queryBox.setFocus(true);
			}
		});
	}
	
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
