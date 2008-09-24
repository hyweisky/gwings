/**
 * 
 */
package org.gwings.client.util.paging;

import java.util.ArrayList;
import java.util.List;


/**
 * Implementação de uma página de dados do sistema.
 * @author MarceloEmanoel
 */
public class PageImpl implements Page {
	private List results;
	private boolean firstPage;
	private boolean lastPage;
	
	public PageImpl(){
		results = new ArrayList();
		firstPage = false;
		lastPage = false;
	}
	/* (non-Javadoc)
	 * @see br.edu.ufcg.ceei.server.database.paging.Page#getResults()
	 */
	public List getResults() {
		return results;
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.ceei.server.database.paging.Page#isFirstPage()
	 */
	public boolean isFirstPage() {
		return firstPage;
	}

	/* (non-Javadoc)
	 * @see br.edu.ufcg.ceei.server.database.paging.Page#isLastPage()
	 */
	public boolean isLastPage() {
		return lastPage;
	}
	/*
	 * (non-Javadoc)
	 * @see br.edu.ufcg.ceei.server.database.paging.Page#setFistPage(boolean)
	 */
	public void setFistPage(boolean firstPage) {
		this.firstPage = firstPage;
		
	}
	/*
	 * (non-Javadoc)
	 * @see br.edu.ufcg.ceei.server.database.paging.Page#setLastPage(boolean)
	 */
	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}

}
