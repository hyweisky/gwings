package org.gwings.client.ui;

import java.util.List;

import org.gwings.client.ui.exception.MultipleSelectionDeniedException;
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
public interface ListSelectorModel {

	/**
	 * Tells wether this model accepts multiple selection or not.
	 * @return the multipleSelectionEnabled
	 */
	public boolean isMultipleSelectionEnabled();

	/**
	 * Toggle between single or multiple selection of itens.
	 * @param multipleSelectionEnabled
	 *            the multipleSelectionEnabled to set
	 */
	public void setMultipleSelectionEnabled(boolean multipleSelectionEnabled);

	/**
	 * Moves the selected item from the available itens list to the selected
	 * itens list.
	 * 
	 * @param position the position of the item to be moved.
	 */
	public void select(int position);

	/**
	 * Moves the selected positions from the available itens list to the
	 * selected itens list.
	 * 
	 * @param positions The positions of the itens to be moved.
	 * @throws MultipleSelectionDeniedException
	 *             When the user attempt to select multiple itens on a single
	 *             selection list.
	 */
	public void select(int[] positions) throws MultipleSelectionDeniedException;
	
	/**
	 * Moves the selected item from the selected itens list to the available itens list.
	 * @param position the position of the item to be moved.
	 */
	public void deselect(int position);
	
	/**
	 * Moves the selected itens from the available itens list to the
	 * selected itens list.
	 * 
	 * @param positions The positions of the itens to be moved.
	 * @throws MultipleSelectionDeniedException
	 *             When the user attempt to select multiple itens on a single
	 *             selection list.
	 */
	public void deselect(int[] positions)
			throws MultipleSelectionDeniedException;

	/**
	 * @return the selectedItens
	 */
	public List getSelectedItens();

	/**
	 * 
	 * @param selectedItens
	 *            the selectedItens to set
	 */
	public void setSelectedItens(List selectedItens);

	/**
	 * @param availableItens
	 *            the availableItens to set
	 */
	public void setAvailableItens(List availableItens);
	
	/**
	 * @return the selectedItens
	 */
	public List getAvailableItens();
	
	public void addListSelectionListener(ListSelectionListener listener);
	
	public void removeListSelectionListener(ListSelectionListener listener);
}
