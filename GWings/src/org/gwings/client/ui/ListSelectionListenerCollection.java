package org.gwings.client.ui;

import java.util.ArrayList;

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
 * @author Marcelo Emanoel, Luciano Broussal
 * @since 07/03/2007
 */
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
