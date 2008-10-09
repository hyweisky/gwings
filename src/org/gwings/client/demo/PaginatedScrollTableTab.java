package org.gwings.client.demo;

import org.gwings.client.table.model.TableModel;
import org.gwings.client.table.pagination.model.PageConfig;
import org.gwings.client.table.scroll.ResizePolicy;
import org.gwings.client.table.scroll.ScrollPolicy;
import org.gwings.client.table.scroll.pagination.PaginatedScrollTable;
import org.gwings.client.table.view.ColumnRenderer;

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
public class PaginatedScrollTableTab extends AbstractDemoPanel {
	
	private final HorizontalAlignmentConstant CENTER = HorizontalPanel.ALIGN_CENTER;
	private final VerticalAlignmentConstant MIDDLE = VerticalPanel.ALIGN_MIDDLE;

	private PaginatedScrollTable<LinePlotable> table;
	private TableModel<LinePlotable> model;
	
	private DockPanel layout;
	
	public PaginatedScrollTableTab() {
		initialize();
		setupUI();
		setupListener();
	}

	private void initialize() {
		PageConfig config = new PageConfig();
		config.setStart(0);
		config.setFinish(50);
		
		table = new PaginatedScrollTable<LinePlotable>();
		table.getPager().setPageConfig(config);
		table.setProvider(new LineProviderAsync());
		
		model = table.getTableModel();
		layout = new DockPanel();

		setupColumns();
		
		table.setColumnWidth(0, 70);
        table.setColumnWidth(1, 50);
        table.setColumnWidth(2, 450);
        table.setColumnWidth(3, 90);
        
        table.setPixelWidth(660);
        table.setHeight(400+"px");
        
        table.setResizePolicy(ResizePolicy.FIXED_WIDTH);
        table.setScrollPolicy(ScrollPolicy.BOTH);
	}

	/**
	 * 
	 */
	private void setupColumns() {
		model.addColumn("Boolean");
		model.addColumn("Image", new ColumnRenderer<String>() {
            public Widget renderType(String value) {
                return new Image(value);
            }
        });
		model.addColumn("String");
		model.addColumn("Date");
	}

	private void setupUI() {
		add(layout);
		layout.setHorizontalAlignment(CENTER);
		layout.setVerticalAlignment(MIDDLE);

		layout.add(table, DockPanel.CENTER);
		layout.setSize("100%", "100%");
	}
	
	private void setupListener() {
	}

	public FlexTable getProperties() {
		
		FlexTable flexTable = new FlexTable();
		return flexTable;
	}

	public HTML getLinks() {
		return new HTML("Table");
	}
}