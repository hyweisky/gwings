/**
 * 
 */
package org.gwings.client.util.paging;

import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;


/**
 * Componente de responsável por oferecer resultados de pesquisa paginados.
 * 
 * @author MarceloEmanoel
 */
public interface Pager extends IsSerializable{
	/**
	 * Retorna todos os resultados da consulta sem paginação.
	 * 
	 * @return java.util.List uma lista com todos os resultados da consulta.
	 */
	public List getResults();
	/**
	 * Insere todos os resultados da consulta no paginador.
	 * @param results
	 */
	public void setResults(List results);
	/**
	 * Retorna a quantidade máxima de resultados de uma página.
	 * 
	 * @return int A quantidade máxima de resultados por página.
	 */
	public int getMaxResultsPerPage();

	/**
	 * Altera a quantidade máxima de resultados de uma página.
	 * 
	 * @param maxResults
	 *            A nova quantidade máxima de resultados por página.
	 */
	public void setMaxResultsPerPage(int maxResults);

	/**
	 * Diz se existem mais páginas a serem percorridas.
	 * 
	 * @return True caso existam páginas a serem percorridas ou false caso
	 *         contrário.
	 */
	public boolean hasMorePages();

	/**
	 * Percorre as páginas da consulta no sentido início-fim.
	 * 
	 * @return A próxima página de resultados da consulta.
	 */
	public Page nextPage() throws IlegalPagerException;

	/**
	 * Percorre as páginas da consulta no sentido fim-início.
	 * 
	 * @return A página anterior de resultados da consulta.
	 */
	public Page previousPage() throws IlegalPagerException;

	/**
	 * Retorna para a primeira página do componente.
	 * 
	 * @return Uma página contendo os primeiros resultados da consulta.
	 * @throws IlegalPagerException
	 *             Caso não hajam resultados na consulta.
	 */
	public Page firstPage() throws IlegalPagerException;

	/**
	 * Retorna para a última página do componente.
	 * 
	 * @return Uma página contendo os ultimos resultados da consulta.
	 * @throws IlegalPagerException
	 *             Caso não hajam resultados na consulta.
	 */
	public Page lastPage() throws IlegalPagerException;
	
	/**
	 * Returns the index of the current page. 
	 * @return Zero based index of the current Page.
	 */
	public Integer pageIndex();
	
	/**
	 * Returns the total number of itens on the pager.
	 * @return
	 */
	public Integer getTotal();
}
