package org.gwings.client.table;

import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.widgetideas.table.client.FixedWidthFlexTable;
import com.google.gwt.widgetideas.table.client.FixedWidthGrid;

/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License. Copyright
 * 2007 Marcelo Emanoel B. Diniz <marceloemanoel AT gmail.com>
 * 
 * @author Marcelo Emanoel
 * @since 07/03/2007
 */
public class Table extends ScrollTable implements TableModelListener {

    public static final int HEADER = 0;
    public static final int NONE = -1;
    private TableModel tableModel;
    private boolean zebraMode;
    private int selectedRow = NONE;
    
    private FixedWidthGrid dataTable;
    private FixedWidthFlexTable headerTable;
    
    public Table() {
        this(new DefaultTableModel());
    }

    public Table(TableModel model) {
//        super(null, null);
        initialize(model);
        setupStyles();
        setupListeners();
    }

    private void setupListeners() {
        dataTable.addTableListener(new TableListener() {

            public void onCellClicked(SourcesTableEvents sender, int row,
                                      int cell) {
                if (selectedRow != NONE) {
                    dataTable.getRowFormatter().removeStyleName(selectedRow, "selected");
                }
                if (row > HEADER) {
                    selectedRow = row;
                    dataTable.getRowFormatter().addStyleName(row, "selected");
                }
            }
        });
    }

    /**
     * @param model
     */
    private void initialize(TableModel model) {
        setTableModel(model);
        dataTable.insertRow(HEADER);
        setZebraMode(true);
    }

    private void setupStyles() {
        setStyleName("org_gwings_Table");
    }

    /**
     * Returns the GTableModel of this GTable.
     * 
     * @return The model of the specified GTable.
     */
    public TableModel getTableModel() {
        return tableModel;
    }

    /**
     * Set the model for the specified GTable.
     * 
     * @param theModel
     *            The GTableModel to be used by the specified table.
     */
    public void setTableModel(TableModel tableModel) {
        this.tableModel = tableModel;
        this.tableModel.addTableModelListener(this);
    }

    public void columnAdded(TableModelEvent evt) {
        TableModel model = evt.getSource();
        String columnName = model.getColumnName(evt.getColumn());
        for (int i = 0; i < model.getRowCount(); i++) {
//            dataTable.addCell(i);
        }
        int coluna = dataTable.getCellCount(HEADER);
        dataTable.setHTML(HEADER, coluna, columnName);
        dataTable.getRowFormatter().setStyleName(HEADER, "header");
    }

    public void columnRemoved(TableModelEvent evt) {
        TableModel model = evt.getSource();
        int column = evt.getColumn();
        for (int i = HEADER; i < model.getRowCount(); i++) {
//            dataTable.removeCell(i, column);
        }
    }

    public void rowAdded(TableModelEvent evt) {
        TableModel model = evt.getSource();
        int row = evt.getRow();

        Plotable plotable = model.getLine(row);

        Object[] line = plotable.plot();
        int nextRow = row + 1;
        dataTable.insertRow(nextRow);
        dataTable.getRowFormatter().addStyleName(nextRow, "row");
        if (isZebraMode()) {
            updateRowStyle(nextRow);
        }
        for (int i = 0; i < line.length; i++) {
            ColumnRenderer columnType = model.getColumnRenderer(i);
            Widget widget = columnType.renderType(line[i]);
            dataTable.setWidget(nextRow, i, widget);
            dataTable.getCellFormatter().setAlignment(nextRow, i,
                                                VerticalPanel.ALIGN_CENTER,
                                                VerticalPanel.ALIGN_MIDDLE);
        }
    }

    /**
     * @param row
     */
    private void updateRowStyle(int row) {
        String style = (row % 2 != 0 ? "even" : "odd");
        dataTable.getRowFormatter().setStyleName(row, style);
    }

    public void rowChanged(TableModelEvent evt) {
        rowRemoved(evt);
        rowAdded(evt);
    }

    public void rowRemoved(TableModelEvent evt) {
        int row = evt.getRow();
        dataTable.removeRow(row + 1);
        if (isZebraMode()) {
            for (int i = row + 1; i < dataTable.getRowCount(); i++) {
                updateRowStyle(i);
            }
        }
    }

    public void rowsCleared(TableModelEvent evt) {
        tableCleared(evt);
        TableModel model = evt.getSource();
        for (int i = 0; i < model.getColumnCount(); i++) {
            dataTable.setHTML(HEADER, i, model.getColumnName(i));
        }
    }

    public void tableCleared(TableModelEvent evt) {
        for (int i = 0; i < evt.getSource().getRowCount(); i++) {
            dataTable.removeRow(i + 1);
        }
        clear();
    }

    /**
     * @return the zebraMode
     */
    public boolean isZebraMode() {
        return zebraMode;
    }

    /**
     * @param zebraMode
     *            the zebraMode to set
     */
    public void setZebraMode(boolean zebraMode) {
        if (!this.zebraMode && zebraMode) {
            enableZebraMode();
        } else {
            disableZebraMode();
        }
        this.zebraMode = zebraMode;
    }

    private void enableZebraMode() {
        if (dataTable.getRowCount() > 1) {
            for (int i = 1; i < dataTable.getRowCount(); i++) {
                updateRowStyle(i);
            }
        }
    }

    private void disableZebraMode() {
        if (dataTable.getRowCount() > 1) {
            for (int i = 1; i < dataTable.getRowCount(); i++) {
                String styleName = (i % 2 != 0 ? "even" : "odd");
                dataTable.getRowFormatter().removeStyleName(i, styleName);
            }
        }
    }

    public void tableChanged(TableModelEvent evt) {
        tableCleared(evt);
    }
}