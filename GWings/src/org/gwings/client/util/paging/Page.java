/**
 * 
 */
package org.gwings.client.util.paging;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

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
 * @author Marcelo Emanoel, Luciano Broussal
 * @since 18/03/2007
 */
public interface Page extends IsSerializable{

	/**
	 * Retorna uma lista contendo os resultados da consulta presentes na página.
	 * A lista possui Arrays de String que representam cada objeto da lista
	 * original.
	 * 
	 * @return Uma lista com os resultados presentes na página.
	 */
	public List getResults();

	/**
	 * Diz se a página é a primeira página da consulta.
	 * 
	 * @return True caso seja a primeira página da consulta, false caso
	 *         contrário.
	 */
	public boolean isFirstPage();

	/**
	 * Diz se a página é a última página da consulta.
	 * 
	 * @return True caso seja a última página da consulta, false caso contrário.
	 */
	public boolean isLastPage();

	/**
	 * Seta a página como sendo a última da consulta.
	 * 
	 * @param b
	 */
	public void setLastPage(boolean b);

	/**
	 * Seta a página como sendo a primeira da consulta.
	 * 
	 * @param b
	 */
	public void setFistPage(boolean b);

}
