package org.gwings.client.table;

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
public interface TableModelListener<T extends Plotable> {
        /**
         * Fired when the rows and columns are cleared.
         * 
         * @param evt
         */
        public void tableCleared(TableModelEvent<T> evt);

        /**
         * Fired when the rows are cleared.
         * 
         * @param evt
         */
        public void rowsCleared(TableModelEvent<T> evt);

        /**
         * Fired when the content of a row is changed.
         * 
         * @param evt
         */
        public void rowChanged(TableModelEvent<T> evt);

        /**
         * Fired when a row is inserted.
         * 
         * @param evt
         */
        public void rowAdded(TableModelEvent<T> evt);

        /**
         * Fired when a row is removed.
         * 
         * @param evt
         */
        public void rowRemoved(TableModelEvent<T> evt);

        /**
         * Fired when a column is inserted.
         * 
         * @param evt
         */
        public void columnAdded(TableModelEvent<T> evt);

        /**
         * Fired when a column is removed.
         * 
         * @param evt
         */
        public void columnRemoved(TableModelEvent<T> evt);
        
        /**
         * Update the all the table.
         * @param evt
         */
        public void tableChanged(TableModelEvent<T> evt);
}