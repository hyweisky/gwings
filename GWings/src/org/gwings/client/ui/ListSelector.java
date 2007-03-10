package org.gwings.client.ui;

import java.util.ArrayList;
import java.util.List;

import org.gwings.client.ui.exception.MultipleSelectionDeniedException;
import org.gwings.client.ui.impl.DefaultListSelectionModel;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.google.gwt.user.client.ui.HasVerticalAlignment.VerticalAlignmentConstant;
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
 * Copyright 2007 Marcelo Emanoel B. Diniz <marceloemanoel AT gmail.com> , Luciano Broussal <luciano.broussal AT gmail.com>
 *
 * @author Marcelo Emanoel
 * @since 07/03/2007
 */
public class ListSelector extends Composite implements ListSelectionListener {
	public static final int DEFAULT_VISIBLE_ITENS = 10;
	private HTML labelAvailableCaption;
	private HTML labelSelectedCaption;
	private Button multipleSelectButton;
	private Button multipleDeselectButton;
	private Button selectButton;
	private Button deselectButton;
	private StylableListBox availableListItens;
	private StylableListBox selectedListItens;
	private FlexTable layout;
	
	private ListSelectorModel model;
	private String availableCaption;
	private String selectedCaption;
	private int maxVisibleItens;

	public ListSelector(){
		initialize();
		setupUI();
		setupStyles();
		setupListeners();
	}
	
	private void initialize() {
		layout = new FlexTable();
		labelAvailableCaption = new HTML("availableCaption", true);
		labelSelectedCaption = new HTML("selectedCaption", true);
		multipleSelectButton = new Button(" &gt;&gt; ");
		multipleDeselectButton = new Button(" &lt;&lt; ");
		selectButton = new Button(" &gt; ");
		deselectButton = new Button(" &lt; ");
		availableListItens = new StylableListBox();
		selectedListItens = new StylableListBox();
		availableListItens.setVisibleItemCount(DEFAULT_VISIBLE_ITENS);
		selectedListItens.setVisibleItemCount(DEFAULT_VISIBLE_ITENS);
		setModel(new DefaultListSelectionModel());
		setMultipleSelectionEnabled(true);
	}

	private void setupStyles() {
		setStyleName("org_gwings_ListSelector");
		labelAvailableCaption.setStyleName("availableLabel");
		labelSelectedCaption.setStyleName("selectedLabel");
		multipleSelectButton.setStyleName("multipleSelectButton");
		multipleDeselectButton.setStyleName("multipleDeselectButton");
		selectButton.setStyleName("selectButton");
		deselectButton.setStyleName("deselectButton");
		availableListItens.setStyleName("availableItens");
		selectedListItens.setStyleName("selectedItens");
	}
	
	private void setupUI() {
		initWidget(layout);
		
		VerticalAlignmentConstant middle = VerticalPanel.ALIGN_MIDDLE;
		HorizontalAlignmentConstant center = HorizontalPanel.ALIGN_CENTER;
		VerticalPanel selectButtonsPanel = new VerticalPanel();
		VerticalPanel deselectButtonsPanel = new VerticalPanel();

		selectButtonsPanel.setHorizontalAlignment(center);
		selectButtonsPanel.setVerticalAlignment(middle);
		deselectButtonsPanel.setHorizontalAlignment(center);
		deselectButtonsPanel.setVerticalAlignment(middle);
		
		selectButtonsPanel.add(multipleSelectButton);
		selectButtonsPanel.add(selectButton);
		selectButtonsPanel.setSpacing(2);
		
		deselectButtonsPanel.add(deselectButton);
		deselectButtonsPanel.add(multipleDeselectButton);
		deselectButtonsPanel.setSpacing(2);
		
		layout.setWidget(1, 1, labelAvailableCaption);
		layout.setWidget(1, 5, labelSelectedCaption);
		layout.setWidget(2, 1, availableListItens);
		layout.setWidget(2, 5, selectedListItens);
		layout.setWidget(3, 2, selectButtonsPanel);
		layout.setWidget(5, 2, deselectButtonsPanel);
		layout.insertRow(6);
		layout.insertCell(6, 0);
		layout.insertCell(1, 6);
		
		layout.getFlexCellFormatter().setRowSpan(2, 1, 7);
		layout.getFlexCellFormatter().setRowSpan(2, 5, 7);
		layout.getCellFormatter().setAlignment(3, 2, center, middle);
		layout.getCellFormatter().setAlignment(5, 2, center, middle);
		layout.getCellFormatter().setAlignment(1, 1, center, middle);
		layout.getCellFormatter().setAlignment(1, 5, center, middle);
		
		
//		selectButtonsPanel.setBorderWidth(1);
//		deselectButtonsPanel.setBorderWidth(1);
		
		
		
//		multipleSelectButton.setSize("100%", "100%");
//		multipleDeselectButton.setSize("100%", "100%");
//		selectButton.setSize("100%", "100%");
//		deselectButton.setSize("100%", "100%");
		availableListItens.setWidth("100%");
		selectedListItens.setWidth("100%");
		selectButtonsPanel.setSize("100%", "100%");
		deselectButtonsPanel.setSize("100%", "100%");
		
		DeferredCommand.add(new Command() {
			public void execute() {
				List availableItens = model.getAvailableItems();
				for(int i = 0; i < availableItens.size(); i++){
					availableListItens.addItem(availableItens.get(i).toString());
				}
                updateItemsStyles(availableItens , availableListItens);
			}
		
		});
//		layout.setBorderWidth(1);
	}
    
    

    
    private void updateItemsStyles(List list , StylableListBox listBox) {
        for(int i = 0; i < list.size(); i++){
            String style = (i % 2 == 0 ? "odd" : "even");
            listBox.setStyleName(i, style);
        }
    }
    
	private void setupListeners() {
		
		ClickListener selectListener = new ClickListener() {
			public void onClick(Widget sender) {
				int position = availableListItens.getSelectedIndex();
				if(position > -1){
					model.select(position);
				}
			}
		};
		ClickListener deselectListener = new ClickListener() {
			public void onClick(Widget sender) {
				int position = selectedListItens.getSelectedIndex();
				if(position > -1){
					model.deselect(position);
				}
			}
		};
		ClickListener multipleSelectListener = new ClickListener() {
			public void onClick(Widget sender) {
				if(availableListItens.isMultipleSelect()){
					List positions = new ArrayList();
					for(int i = 0; i < availableListItens.getItemCount(); i++){
//						if(availableListItens.isItemSelected(i)){
							positions.add(new Integer(i));
//					}
					}
					int[] itemPositions = new int[positions.size()];
					for(int i = 0; i < positions.size();i++){
						itemPositions[i] = ((Integer)positions.get(i)).intValue();
					}
					try {
						model.select(itemPositions);
						availableListItens.setSelectedIndex(-1);
					} catch (MultipleSelectionDeniedException e) {
						Window.alert(e.getMessage());
					}
				}
			}
		};
		ClickListener multipleDeselectListener = new ClickListener() {
			public void onClick(Widget sender) {
				if(selectedListItens.isMultipleSelect()){
					List positions = new ArrayList();
					for(int i = 0; i < selectedListItens.getItemCount(); i++){
					
//                        if(selectedListItens.isItemSelected(i)){
							positions.add(new Integer(i));
//						}
					}
					int[] itemPositions = new int[positions.size()];
					for(int i = 0; i < positions.size();i++){
						itemPositions[i] = ((Integer)positions.get(i)).intValue();
					}
					try {
						model.deselect(itemPositions);
						selectedListItens.setSelectedIndex(-1);
					} catch (MultipleSelectionDeniedException e) {
						Window.alert(e.getMessage());
					}
				}
			}
		};
		
		multipleSelectButton.addClickListener(multipleSelectListener);
		multipleDeselectButton.addClickListener(multipleDeselectListener);
		selectButton.addClickListener(selectListener);
		deselectButton.addClickListener(deselectListener);
	}
	
	/**
	 * @return the selectCaption
	 */
	public String getSelectCaption(){
		return this.selectedCaption;
	}
	
	/**
	 * @param selectCaption the selectCaption to set
	 */
	public void setSelectCaption(String selectCaption){
		if(selectCaption != null && !selectCaption.equals("")){
			this.selectedCaption = selectCaption;
			this.labelSelectedCaption.setHTML(selectCaption);
		}
	}
	
	/**
	 * @return the availableCaption
	 */
	public String getAvailableCaption(){
		return this.availableCaption;
	}
	/**
	 * @param availableCaption the availableCaption to set
	 */
	public void setAvailableCaption(String availableCaption){
		if(availableCaption != null && !availableCaption.equals("")){
			this.availableCaption = availableCaption;
			this.labelAvailableCaption.setHTML(availableCaption);
		}
	}
	/**
	 * @return the maxVisibleItens
	 */
	public int getMaxVisibleItens(){
		return this.maxVisibleItens;
	}
	/**
	 * @param maxVisibleItens the maxVisibleItens to set
	 */
	public void setMaxVisibleItens(int maxVisibleItens){
		if(maxVisibleItens > 0){
			this.maxVisibleItens = maxVisibleItens;
			this.availableListItens.setVisibleItemCount(maxVisibleItens);
			this.selectedListItens.setVisibleItemCount(maxVisibleItens);
		}
	}

	public void itemDeselected(int originalPosition) {
		String itemText = selectedListItens.getItemText(originalPosition);
		selectedListItens.removeItem(originalPosition);
		availableListItens.addItem(itemText);
//		setItemStyleName(availableListItens);
        updateItemsStyles(model.getAvailableItems() , availableListItens);
        updateItemsStyles(model.getSelectedItems() , selectedListItens);
	}

	public void itemSelected(int originalPosition) {
		String itemText = availableListItens.getItemText(originalPosition);
		availableListItens.removeItem(originalPosition);
		selectedListItens.addItem(itemText);
		//setItemStyleName(selectedListItens);
        updateItemsStyles(model.getAvailableItems() , availableListItens);
        System.out.println(availableListItens);
        updateItemsStyles(model.getSelectedItems() , selectedListItens);
        System.out.println(selectedListItens);
      
	}

//	
//	private void setItemStyleName(StylableListBox list) {
//		int nextInsert = list.getItemCount()-1;
//		String styleName = (nextInsert % 2 != 0? "even": "odd");
//		list.setStyleName(nextInsert, styleName);
//	}

	public void multipleSelectionDisabled() {
		showMultipleButtons(false);
	}

	public void multipleSelectionEnabled() {
		showMultipleButtons(true);
	}

	private void showMultipleButtons(boolean show) {
		multipleSelectButton.setVisible(show);
		multipleDeselectButton.setVisible(show);
		multipleSelectButton.setEnabled(show);
		multipleDeselectButton.setEnabled(show);
		availableListItens.setMultipleSelect(show);
		selectedListItens.setMultipleSelect(show);
	}

	/**
	 * @return the model
	 */
	public ListSelectorModel getModel() {
		return model;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(ListSelectorModel model) {
		this.model = model;
		model.addListSelectionListener(this);
	}
	
	/**
	 * Tells wether this model accepts multiple selection or not.
	 * @return the multipleSelectionEnabled
	 */
	public boolean isMultipleSelectionEnabled(){
		return getModel().isMultipleSelectionEnabled();
	}

	/**
	 * Toggle between single or multiple selection of itens.
	 * @param multipleSelectionEnabled
	 *            the multipleSelectionEnabled to set
	 */
	public void setMultipleSelectionEnabled(boolean multipleSelectionEnabled){
		getModel().setMultipleSelectionEnabled(multipleSelectionEnabled);
	}
}