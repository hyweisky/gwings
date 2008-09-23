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
public class TableModelAdapter implements TableModelListener {
        /*
         * (non-Javadoc)
         * 
         * @see org.gwings.client.ui.GTableModelListener#columnAdded(org.gwings.client.ui.GTableModelEvent)
         */
        public void columnAdded(TableModelEvent evt) {}

        /*
         * (non-Javadoc)
         * 
         * @see org.gwings.client.ui.GTableModelListener#columnRemoved(org.gwings.client.ui.GTableModelEvent)
         */
        public void columnRemoved(TableModelEvent evt) {}

        /*
         * (non-Javadoc)
         * 
         * @see org.gwings.client.ui.GTableModelListener#rowAdded(org.gwings.client.ui.GTableModelEvent)
         */
        public void rowAdded(TableModelEvent evt) {}

        /*
         * (non-Javadoc)
         * 
         * @see org.gwings.client.ui.GTableModelListener#rowChanged(org.gwings.client.ui.GTableModelEvent)
         */
        public void rowChanged(TableModelEvent evt) {}

        /*
         * (non-Javadoc)
         * 
         * @see org.gwings.client.ui.GTableModelListener#rowRemoved(org.gwings.client.ui.GTableModelEvent)
         */
        public void rowRemoved(TableModelEvent evt) {}

        /*
         * (non-Javadoc)
         * 
         * @see org.gwings.client.ui.GTableModelListener#rowsCleared(org.gwings.client.ui.GTableModelEvent)
         */
        public void rowsCleared(TableModelEvent evt) {}

        /*
         * (non-Javadoc)
         * 
         * @see org.gwings.client.ui.GTableModelListener#tableCleared(org.gwings.client.ui.GTableModelEvent)
         */
        public void tableCleared(TableModelEvent evt) {}

        public void tableChanged(TableModelEvent evt) {}

}