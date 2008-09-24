/**
 * 
 */
package org.gwings.client.util.paging;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementação do componente de paginação do sistema. 
 * @author MarceloEmanoel
 */
public class PagerWizard implements Pager {
	private List results;
	private int maxResultsPerPage;
	private int posicaoInicial;
	private int posicaoFinal;
	private Page currentPage;
	private boolean hasMorePages;

	public PagerWizard() {
		results = new ArrayList();
		maxResultsPerPage = 0;
		currentPage = new PageImpl();
		hasMorePages = true;
		posicaoInicial = 0;
		posicaoFinal = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.edu.ufcg.ceei.server.database.paging.Pager#getMaxResultsPerPage()
	 */
	public int getMaxResultsPerPage() {
		return this.maxResultsPerPage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.edu.ufcg.ceei.server.database.paging.Pager#getResults()
	 */
	public List getResults() {
		return this.results;
	}
	/*
	 * (non-Javadoc)
	 * @see br.edu.ufcg.ceei.server.database.paging.Pager#setResults(java.util.List)
	 */
	public void setResults(List results){
		this.results = results;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see br.edu.ufcg.ceei.server.database.paging.Pager#hasMorePages()
	 */
	public boolean hasMorePages() {
		return this.hasMorePages && (posicaoFinal < results.size());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.edu.ufcg.ceei.server.database.paging.Pager#nextPage()
	 */
	public Page nextPage() throws IlegalPagerException {
		if (currentPage.isLastPage()) {
			throw new IlegalPagerException(
					"Não existem páginas posteriores à última página.");
		}
		posicaoInicial = posicaoFinal;
		int novaPosicaoFinal = posicaoFinal + maxResultsPerPage;
		posicaoFinal = (novaPosicaoFinal > results.size() ? results.size() : novaPosicaoFinal);
		if(currentPage.isFirstPage()){
			currentPage.setFistPage(false);
		}
		currentPage.setLastPage(posicaoFinal == results.size());
		preenchePagina();
		return currentPage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.edu.ufcg.ceei.server.database.paging.Pager#previousPage()
	 */
	public Page previousPage() throws IlegalPagerException {
		if (currentPage.isFirstPage()) {
			throw new IlegalPagerException(
					"Não existem páginas anteriores à primeira página.");
		}
		posicaoFinal = posicaoInicial;
		int novoComeco = posicaoInicial - maxResultsPerPage;
		posicaoInicial = (novoComeco < 0 ? 0 : novoComeco);
		if(currentPage.isLastPage()){
			currentPage.setLastPage(false);
		}
		currentPage.setFistPage(posicaoInicial == 0);
		preenchePagina();
		return currentPage;
	}
	
	/**
	 * Preenche a página conrrente com os dados da consulta.
	 */
	private void preenchePagina() {
		currentPage.getResults().clear();
		for (int i = posicaoInicial; i < posicaoFinal ; i++) {
			Object to = results.get(i);
			currentPage.getResults().add(to);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.edu.ufcg.ceei.server.database.paging.Pager#setMaxResultsPerPage(int)
	 */
	public void setMaxResultsPerPage(int maxResults) {
		this.maxResultsPerPage = maxResults;
	}
	/*
	 * (non-Javadoc)
	 * @see br.edu.ufcg.ceei.server.database.paging.Pager#firstPage()
	 */
	public Page firstPage() throws IlegalPagerException {
		if(results.isEmpty()){
			throw new IlegalPagerException("Não existem páginas de resultados.");
		}
		posicaoInicial = 0;
		posicaoFinal = (results.size() > maxResultsPerPage? maxResultsPerPage : results.size());
		preenchePagina();
		currentPage.setFistPage(true);
		currentPage.setLastPage(false);
		return currentPage;
	}
	
	/*
	 * (non-Javadoc)
	 * @see br.edu.ufcg.ceei.server.database.paging.Pager#lastPage()
	 */
	public Page lastPage() throws IlegalPagerException {
		if(results.isEmpty()){
			throw new IlegalPagerException("Não existem páginas de resultados.");
		}
		posicaoFinal = results.size();
		int pos = results.size() - maxResultsPerPage;
		hasMorePages = false;
		posicaoInicial = (pos < 0 ? 0 : pos);
		preenchePagina();
		currentPage.setFistPage(false);
		currentPage.setLastPage(true);
		return currentPage;
	}

	public Integer getTotal() {
		return new Integer(results.size());
	}

	public Integer pageIndex() {
		return new Integer(posicaoInicial);
	}
}
