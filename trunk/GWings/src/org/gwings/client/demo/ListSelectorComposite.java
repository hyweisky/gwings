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
		list.getModel().setAvailableItems(lista);

		layout.setSize("100%", "100%");
		layout.add(list, DockPanel.CENTER);
		layout.setCellHorizontalAlignment(list, HorizontalPanel.ALIGN_CENTER);
		layout.setCellVerticalAlignment(list, VerticalPanel.ALIGN_MIDDLE);
	}
}
