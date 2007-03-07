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
		setAvailableItens(new ArrayList());
		setSelectedItens(new ArrayList());
		setMultipleSelectionEnabled(false);
	}
	
	public void addListSelectionListener(ListSelectionListener listener){
		listenerCollection.addListSelectionListener(listener);
	}
	
	public void removeListSelectionListener(ListSelectionListener listener){
		listenerCollection.removeListSelectionListener(listener);
	}
	
	public List getSelectedItens() {
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
		if(!isMultipleSelectionEnabled()){
			throw new MultipleSelectionDeniedException();
		}
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
		if(!isMultipleSelectionEnabled()){
			throw new MultipleSelectionDeniedException();
		}
//		Arrays.sort(positions);
		for(int i = positions.length; i > 0 ; i--){
			deselect(i-1);
		}
	}

	public void setAvailableItens(List availableItens) {
		this.availableItens = availableItens;
	}

	public List getAvailableItens(){
		return this.availableItens;
	}
	
	public void setMultipleSelectionEnabled(boolean multipleSelectionEnabled) {
		this.multipleSelectionEnabled = multipleSelectionEnabled;
		if(multipleSelectionEnabled){
			listenerCollection.fireMultipleSelectionEnabled();
		}
		else
			listenerCollection.fireMultipleSelectionDisabled();
	}

	public void setSelectedItens(List selectedItens) {
		this.selectedItens = selectedItens;
	}
}