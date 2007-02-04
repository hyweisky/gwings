package org.gwings.client.ui;

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
public interface GTableModelListener {
	/**
	 * Fired when the rows and columns are cleared.
	 * 
	 * @param evt
	 */
	public void tableCleared(GTableModelEvent evt);

	/**
	 * Fired when the rows are cleared.
	 * 
	 * @param evt
	 */
	public void rowsCleared(GTableModelEvent evt);

	/**
	 * Fired when the content of a row is changed.
	 * 
	 * @param evt
	 */
	public void rowChanged(GTableModelEvent evt);

	/**
	 * Fired when a row is inserted.
	 * 
	 * @param evt
	 */
	public void rowAdded(GTableModelEvent evt);

	/**
	 * Fired when a row is removed.
	 * 
	 * @param evt
	 */
	public void rowRemoved(GTableModelEvent evt);

	/**
	 * Fired when a column is inserted.
	 * 
	 * @param evt
	 */
	public void columnAdded(GTableModelEvent evt);

	/**
	 * Fired when a column is removed.
	 * 
	 * @param evt
	 */
	public void columnRemoved(GTableModelEvent evt);

}
