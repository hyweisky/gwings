package org.gwings.client.table;

import java.util.List;

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
 * @since 07/03/2007
 */
public interface TableModel {

        /**
         * Adds a column to the model specifying the renderer to be used with it.
         * 
         * @param columnName
         *            This name will appear on the table header.
         * @param renderer
         *            This object will render properly the values of this column.
         */
        public void addColumn(String columnName, ColumnRenderer renderer);

        /**
         * Adds a column to the model. This column will use the default renderer.
         * 
         * @param column
         *            The name that will appear on the table header.
         */
        public void addColumn(String column);

        /**
         * Removes the specified column of the model.
         * 
         * @param column
         *            The order of the column.
         * 
         * <pre>
         * 0 &lt;= column &lt; getColumnCount()
         * </pre>
         * 
         * @return the name of the column removed.
         */
        public String removeColumn(int column);

        /**
         * Remove the first ocurrency of columns with the name specified.
         * 
         * @param columnName
         *            The name of the column to be removed.
         */
        public void removeColumn(String columnName);

        /**
         * Returns the name of the column acording to the position.
         * 
         * @param column
         *            The position of the column.
         * 
         * <pre>
         * 0 &lt;= column &lt; getColumnCount()
         * </pre>
         * 
         * @return The column name.
         */
        public String getColumnName(int column);

        /**
         * Returns how many columns there is on this model.
         * 
         * @return A integer representing the number of columns in the model.
         */
        public int getColumnCount();

        /**
         * Returns the column renderer of the specified column.
         * 
         * @param column
         *            the order of the column.
         * 
         * <pre>
         * 0 &lt;= column &lt; getColumnCount()
         * </pre>
         * 
         * @return the respective column renderer
         */
        public ColumnRenderer getColumnRenderer(int column);

        /**
         * Returns the column renderer of the specified column.
         * 
         * @param columnName
         *            The name of the column.
         * @return the respective column renderer
         */
        public ColumnRenderer getColumnRenderer(String columnName);

        /**
         * Sets the renderer of the column.
         * 
         * @param column
         *            The order of the column.
         * 
         * <pre>
         * 0 &lt;= column &lt; getColumnCount()
         * </pre>
         * 
         * @param renderer
         *            The proper renderer for the column.
         */
        public void setColumnRenderer(int column, ColumnRenderer renderer);

        /**
         * Sets the renderer of the column.
         * 
         * @param columnName
         *            The name of the column.
         * @param renderer
         *            The proper renderer for the column.
         */
        public void setColumnRenderer(String columnName, ColumnRenderer renderer);

        /**
         * Append a line to the model.
         * 
         * @param line
         *            The line to be added.
         */
        public void appendLine(Plotable line);

        /**
         * Inserts a line at the specified index.
         * 
         * @param index
         *            The index of the new line.
         * @param line
         *            The new line.
         */
        public void addLine(int index, Plotable line);

        /**
         * Removes the specified line of the model.
         * 
         * @param lineNumber
         *            The number of the line to be removed.
         * @return The object represented by that line.
         */
        public Plotable removeLine(int lineNumber);

        /**
         * Removes the specified object of the model.
         * 
         * @param line
         *            The object represented by the line.
         */
        public void removeLine(Plotable line);

        /**
         * Returns the object represented by the line.
         * 
         * @param lineNumber
         *            The number of the line representing the object.
         * @return The object represented.
         */
        public Plotable getLine(int lineNumber);

        /**
         * Returns the current row count of the model.
         * 
         * @return An integer representing the current row count.
         */
        public int getRowCount();

        /**
         * Removes all rows from this model.
         */
        public void clearRows();

        /**
         * Adds a listener to this model.
         * 
         * @param theListener
         *            The listener of this model.
         */
        public void addTableModelListener(TableModelListener theListener);

        /**
         * Removes a specified listener of this model.
         * 
         * @param theListener
         *            The listener to be removed.
         */
        public void removeTableModelListener(TableModelListener theListener);

        /**
         * Clear the entire model. Removes all rows and columns.
         */
        public void clearAll();
        
        /**
         * Sets the collection of lines.
         * @param lines
         */
        public void setLines(List<Plotable> lines);
}