package org.gwings.client.ui;

import java.util.ArrayList;
import java.util.List;
public class ListSelectionListenerCollection {
	private List listSelectionListeners;
	
	public ListSelectionListenerCollection(){
		listSelectionListeners = new ArrayList();
	}
	
	public void addListSelectionListener(ListSelectionListener listener){
		listSelectionListeners.add(listener);
	}
	
	public void removeListSelectionListener(ListSelectionListener listener){
		listSelectionListeners.remove(listener);
	}
	
	public void fireSelection(int originalPosition){
		for(int i = 0; i < listSelectionListeners.size(); i++){
			ListSelectionListener listener = (ListSelectionListener) listSelectionListeners.get(i);
			listener.itemSelected(originalPosition);
		}
	}
	
	public void fireDeselection(int originalPosition){
		for(int i = 0; i < listSelectionListeners.size(); i++){
			ListSelectionListener listener = (ListSelectionListener) listSelectionListeners.get(i);
			listener.itemDeselected(originalPosition);
		}
	}
	
//	public void fireMultipleSelectionEnabled(){
//		for(int i = 0; i < listSelectionListeners.size(); i++){
//			ListSelectionListener listener = (ListSelectionListener) listSelectionListeners.get(i);
//			listener.multipleSelectionEnabled();
//		}
//	}
//	public void fireMultipleSelectionDisabled(){
//		for(int i = 0; i < listSelectionListeners.size(); i++){
//			ListSelectionListener listener = (ListSelectionListener) listSelectionListeners.get(i);
//			listener.multipleSelectionDisabled();
//		}
//	}
}
