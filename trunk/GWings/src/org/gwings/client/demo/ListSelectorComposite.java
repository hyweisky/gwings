package org.gwings.client.demo;

import java.util.ArrayList;

import org.gwings.client.ui.ListSelector;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ListSelectorComposite extends Composite {
	private ListSelector list;
	private DockPanel layout;

	public ListSelectorComposite() {
		initilize();
		setupUI();

	}

	private void initilize() {
		list = new ListSelector();
		layout = new DockPanel();
		
		list.setAvailableCaption("These are the itens <br>available");
		list.setSelectCaption("And these are the <br> selected itens");
	}

	private void setupUI() {
		initWidget(layout);

		ArrayList lista = new ArrayList();
		for (int i = 0; i < 10; i++) {
			lista.add(i + "");
		}
		list.getModel().setAvailableItens(lista);

		layout.setSize("100%", "100%");
		layout.add(list, DockPanel.CENTER);
		layout.setCellHorizontalAlignment(list, HorizontalPanel.ALIGN_CENTER);
		layout.setCellVerticalAlignment(list, VerticalPanel.ALIGN_MIDDLE);
	}
}
