package org.gwings.client.demo;

import java.util.Date;

import org.gwings.client.table.TableModel;
import org.gwings.client.table.scroll.ScrollTable;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
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
 * Copyright 2007 Marcelo Emanoel B. Diniz <marceloemanoel AT gmail.com>
 * 
 * @author Marcelo Emanoel
 * @since 04/02/2007
 */
public class TableTab extends AbstractDemoPanel {
	
	private final HorizontalAlignmentConstant CENTER = HorizontalPanel.ALIGN_CENTER;
	private final VerticalAlignmentConstant MIDDLE = VerticalPanel.ALIGN_MIDDLE;

	private ScrollTable table;
	private TableModel model;
	private DockPanel layout;
	private CheckBox enableZebra;
	

	public TableTab() {
		initialize();
		setupUI();
		setupListener();
	}

	private void initialize() {
		table = new ScrollTable();
		layout = new DockPanel();
		enableZebra = new CheckBox();
		model = table.getTableModel();
		
		setupColumns();
		makeMockObjects();

	}

	/**
	 * 
	 */
	private void makeMockObjects() {
		
		SimpleLinePlotable plotable = new SimpleLinePlotable(Boolean.TRUE, new Image("pics/table/star_on.gif"), "Forbidden Knowledge Conference", new Date());
		SimpleLinePlotable plotable1 = new SimpleLinePlotable(Boolean.FALSE, new Image("pics/table/star_off.gif"), "Forbidden Knowledge Conference", new Date());
		SimpleLinePlotable plotable2 = new SimpleLinePlotable(Boolean.FALSE, new Image("pics/table/star_off.gif"), "Forbidden Knowledge Conference", new Date());
		SimpleLinePlotable plotable3 = new SimpleLinePlotable(Boolean.TRUE, new Image("pics/table/star_on.gif"), "Forbidden Knowledge Conference", new Date());
		
		model.appendLine(plotable);
		model.appendLine(plotable1);
		model.appendLine(plotable2);
		model.appendLine(plotable3);
		
	}

	/**
	 * 
	 */
	private void setupColumns() {
		model.addColumn("Boolean");
		model.addColumn("Image");
		model.addColumn("String");
		model.addColumn("Date");
	}

	private void setupUI() {
		add(layout);
		
		enableZebra.setStyleName("enableZebra");
		
		layout.setHorizontalAlignment(CENTER);
		layout.setVerticalAlignment(MIDDLE);

		layout.add(table, DockPanel.CENTER);
		layout.setSize("100%", "100%");
		
	}
	
	private void setupListener() {
//		enableZebra.addClickListener(new ClickListener() {
//			public void onClick(Widget sender) {
//				table.setZebraMode(enableZebra.isChecked());
//			}
//		});
	}

	public FlexTable getProperties() {
		
		FlexTable flexTable = new FlexTable();
		flexTable.setWidget(0, 0, new HTML("Zebra Mode"));
		flexTable.setWidget(0, 1, enableZebra);
		return flexTable;
	}

	public HTML getLinks() {
		return new HTML("Table");
	}
}