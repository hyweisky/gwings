package org.gwings.client.demo;

import java.util.ArrayList;

import org.gwings.client.ui.ListSelector;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
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
 * Copyright 2007 Marcelo Emanoel B. Diniz <marceloemanoel AT gmail.com>
 *
 * @author Marcelo Emanoel, Luciano Broussal
 * @since 12/03/2007
 */
public class ListSelectorTab extends AbstractDemoPanel {
	private ListSelector list;
	private DockPanel layout;
	
	private TextBox availableCaptionField;
	private TextBox selectedCaptionField;
	private TextBox maxVisibleItens; 
	
	public ListSelectorTab() {
		initilize();
		setupUI();
		setupListeners();
	}


	private void initilize() {
		list = new ListSelector();
		layout = new DockPanel();
		
		list.setAvailableCaption("These are the itens <br>available");
		list.setSelectCaption("And these are the <br> selected itens");
		
		availableCaptionField = new TextBox();
		selectedCaptionField = new TextBox();
		maxVisibleItens = new TextBox(); 

		availableCaptionField.setText(list.getAvailableCaption());
		selectedCaptionField.setText(list.getSelectedCaption());
		maxVisibleItens.setText(list.getMaxVisibleItens()+"");
	}

	private void setupUI() {
		add(layout);

		ArrayList<String> lista = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			lista.add(i + "");
		}
		list.getModel().setAvailableItems(lista);

		layout.setSize("100%", "100%");
		layout.add(list, DockPanel.CENTER);
		layout.setCellHorizontalAlignment(list, HorizontalPanel.ALIGN_CENTER);
		layout.setCellVerticalAlignment(list, VerticalPanel.ALIGN_MIDDLE);
	}

	private void setupListeners() {
		availableCaptionField.addChangeListener(new ChangeListener() {
			public void onChange(Widget sender) {
				updateAvailbleText();
			}
		});
		availableCaptionField.addKeyboardListener(new KeyboardListenerAdapter() {
			public void onKeyUp(Widget sender, char keyCode, int modifiers) {
				if(keyCode == KEY_ENTER){
					updateAvailbleText();
				}
			}
		});
		selectedCaptionField.addKeyboardListener(new KeyboardListenerAdapter() {
			public void onKeyUp(Widget sender, char keyCode, int modifiers) {
				if(keyCode == KEY_ENTER){
					updateSelectedText();
				}
			}
		});
		selectedCaptionField.addChangeListener(new ChangeListener() {
			public void onChange(Widget sender) {
				updateSelectedText();
			}
		});

		maxVisibleItens.addChangeListener(new ChangeListener() {
			public void onChange(Widget sender) {
				updateVisibleValues();
			}
		});
		maxVisibleItens.addKeyboardListener(new KeyboardListenerAdapter() {
			public void onKeyUp(Widget sender, char keyCode, int modifiers) {
				if(keyCode == KEY_ENTER){
					updateVisibleValues();
				}
			}
		});
	}

	private void updateAvailbleText() {
		list.setAvailableCaption(availableCaptionField.getText());
	}

	public FlexTable getProperties() {
		FlexTable flex = new FlexTable();
		flex.setWidget(0, 0, new HTML("Available Caption"));
		flex.setWidget(0, 1, availableCaptionField);
		flex.setWidget(1, 0, new HTML("Selected Caption"));
		flex.setWidget(1, 1, selectedCaptionField);
		flex.setWidget(2,0,new HTML("Max Itens Visible"));
		flex.setWidget(2, 1, maxVisibleItens);
		
		flex.getFlexCellFormatter().setWidth(0, 1, "80%");
		flex.getFlexCellFormatter().setWidth(1, 0, "80%");
		flex.getFlexCellFormatter().setWidth(2, 0, "80%");
		flex.getFlexCellFormatter().setWidth(3, 0, "80%");
		
		return flex;
	}

	public HTML getLinks() {
		return new HTML("List Selector");
	}


	/**
	 * 
	 */
	private void updateSelectedText() {
		list.setSelectCaption(selectedCaptionField.getText());
	}


	/**
	 * 
	 */
	private void updateVisibleValues() {
		try{
			String text = maxVisibleItens.getText();
			int value = Integer.parseInt(text);
			list.setMaxVisibleItens(value);
		}
		catch (Exception e) {
			Window.alert("Please insert an integer greater than zero.");
			DeferredCommand.addCommand(new Command() {
				public void execute() {
					maxVisibleItens.selectAll();
					maxVisibleItens.setFocus(true);
				}
			});
		}
	}
}
