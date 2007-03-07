package org.gwings.client.demo;

import java.util.ArrayList;

import org.gwings.client.ui.ListSelector;

import com.google.gwt.user.client.ui.Composite;

public class ListSelectorComposite extends Composite {
	private ListSelector list;
	
	public ListSelectorComposite(){
		list = new ListSelector();
		ArrayList lista = new ArrayList();
		for(int i = 0 ; i < 10; i++){
			lista.add(i+"");
		}
		list.getModel().setAvailableItens(lista);
		initWidget(list);
	}
}
