package org.gwings.client.ui;

import com.google.gwt.user.client.ui.HTMLTable;

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
public interface GTable {
	/**
	 * Returns the GTableModel of this GTable.
	 * @return The model of the specified GTable.
	 */
	public GTableModel getTableModel();

	/**
	 * Set the model for the specified GTable.
	 * @param theModel The GTableModel to be used by the specified table.
	 */
	public void setTableModel(GTableModel theModel);

	/**
	 * Returns the View of the GTable.
	 * @return Returns the view part of the MVC.
	 */
	public HTMLTable getTableView();

}
