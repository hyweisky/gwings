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
 * Copyright 2007 Marcelo Emanoel B. Diniz <marceloemanoel AT gmail.com>, Luciano Broussal <luciano.broussal AT gmail.com>
 *
 * @author Marcelo Emanoel , Luciano Broussal
 * @since 07/03/2007
 */
package org.gwings.client.ui.impl;

import java.util.ArrayList;
import java.util.List;

import org.gwings.client.ui.ListSelectionListener;
import org.gwings.client.ui.ListSelectionListenerCollection;
import org.gwings.client.ui.ListSelectorModel;
import org.gwings.client.ui.exception.MultipleSelectionDeniedException;

public class DefaultListSelectionModel implements ListSelectorModel {
	private boolean multipleSelectionEnabled;
	private List availableItens;
	private List selectedItens;
	private ListSelectionListenerCollection listenerCollection;

	public DefaultListSelectionModel() {
		listenerCollection = new ListSelectionListenerCollection();
		setAvailableItems(new ArrayList());
		setSelectedItems(new ArrayList());
//		setMultipleSelectionEnabled(false);
	}
	
	public void addListSelectionListener(ListSelectionListener listener){
		listenerCollection.addListSelectionListener(listener);
	}
	
	public void removeListSelectionListener(ListSelectionListener listener){
		listenerCollection.removeListSelectionListener(listener);
	}
	
	public List getSelectedItems() {
		return this.selectedItens;
	}

	public boolean isMultipleSelectionEnabled() {
		return this.multipleSelectionEnabled;
	}

	public void select(int position) {
		Object itemRemoved = availableItens.remove(position);
		selectedItens.add(itemRemoved);
		listenerCollection.fireSelection(position);
	}

	public void select(int[] positions) throws MultipleSelectionDeniedException {
//		if(!isMultipleSelectionEnabled()){
//			throw new MultipleSelectionDeniedException();
//		}
//		Arrays.sort(positions);
		for(int i = positions.length; i > 0; i--){
			select(positions[i-1]);
		}
	}

	public void deselect(int position) {
		Object itemRemoved = selectedItens.remove(position);
		availableItens.add(itemRemoved);
		listenerCollection.fireDeselection(position);
	}

	public void deselect(int[] positions)
			throws MultipleSelectionDeniedException {
//		if(!isMultipleSelectionEnabled()){
//			throw new MultipleSelectionDeniedException();
//		}
//		Arrays.sort(positions);
		for(int i = positions.length; i > 0 ; i--){
			deselect(i-1);
		}
	}

	public void setAvailableItems(List availableItens) {
		this.availableItens = availableItens;
	}

	public List getAvailableItems(){
		return this.availableItens;
	}
	
//	public void setMultipleSelectionEnabled(boolean multipleSelectionEnabled) {
//		this.multipleSelectionEnabled = multipleSelectionEnabled;
//		if(multipleSelectionEnabled){
//			listenerCollection.fireMultipleSelectionEnabled();
//		}
//		else
//			listenerCollection.fireMultipleSelectionDisabled();
//	}

	public void setSelectedItems(List selectedItens) {
		this.selectedItens = selectedItens;
	}
}