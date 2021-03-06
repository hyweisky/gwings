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
public class TableModelEvent {
        private static final int NONE_SELECTION = -1;
        private TableModel source;
        private int column;
        private int row;
        
        public TableModelEvent(TableModel source){
                this.source = source;
                this.column = NONE_SELECTION;
                this.row = NONE_SELECTION;
        }

        public TableModel getSource() {
                return this.source;
        }

        public int getColumn() {
                return column;
        }
        public void setColumn(int column){
                this.column = column;
        }

        public int getRow() {
                return this.row;
        }
        public void setRow(int row){
                this.row = row;
        }
}