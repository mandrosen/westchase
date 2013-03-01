package com.westchase.web.action.cms;

import com.opensymphony.xwork2.Preparable;
import com.westchase.persistence.criteria.SearchCriteria;
import com.westchase.web.action.AbstractWestchaseAction;

/**
 * @author marc
 *
 */
public abstract class AbstractCMSAction<E, F extends SearchCriteria<E>> extends AbstractWestchaseAction implements Preparable {
	public static final int DEFAULT_NUMBER_OF_RESULTS = 40;
	
	public static final int DEFAULT_PAGE = 0;
	
	public static final String DEFAULT_ORDER_COL = "id";
	
	public static final String DEFAULT_ORDER_DIR = "asc";
	
	public static final String SESSION_ATTR_NAME = "_session";
	
	private int numberOfResults = DEFAULT_NUMBER_OF_RESULTS;

	private int page = DEFAULT_PAGE;
	private int nextPage;
	
	private String orderCol;
	
	private String currentOrderCol = DEFAULT_ORDER_COL;
	
	private String orderDir = DEFAULT_ORDER_DIR;
	
	private long totalCount;
	
	private long maxPage;
	
	private boolean updatedByMe;
	
	private int useLast;
	
//  this field should work, but struts2/ognl throws a class cast exception when it is used	
//	private E searchObject;
//	
//	public E getSearchObject() {
//		return searchObject;
//	}
//	
//	public void setSearchObject(E searchObject) {
//		this.searchObject = searchObject;
//	}
//	public abstract E getSearchObject();
//	public abstract void setSearchObject(E searchObject);

//	private Object searchObject;
//	public Object getSearchObject() {
//		return this.searchObject;
//	}
//	public void setSearchObject(Object searchObject) {
//		this.searchObject = searchObject;
//	}

	public abstract String goToPage(int page);
	
	public String goToPage() {
		return goToPage(getNextPage());
	}
	public String first() {
    	return goToPage(0);
    }
	public String next() {
    	return goToPage(getPage() + 1);
    }
	public String prev() {
    	return goToPage(getPage() - 1);
    }
	
	public void refreshLast() {
		@SuppressWarnings("unchecked")
		F criteria = (F) getRequest().getSession(true).getAttribute(getLastSearchAttributeName() + SESSION_ATTR_NAME);
		if (criteria == null) {
			refresh();
		} else {
	        runSearch(criteria);
		}
	}
	protected abstract void runSearch(F criteria);
	
	
	public String getOrderCol() {
		return orderCol;
	}

	public void setOrderCol(String orderCol) {
		this.orderCol = orderCol;
	}

	public String getOrderDir() {
		return orderDir;
	}

	public void setOrderDir(String orderDir) {
		this.orderDir = orderDir;
	}

	public int getNumberOfResults() {
		return numberOfResults;
	}

	public void setNumberOfResults(int numberOfResults) {
		this.numberOfResults = numberOfResults;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	// these should be able to be defined here but again, the class cast exception
//    protected abstract String next();
//    protected abstract String prev();
    protected abstract String list();
    
    protected String sort() {return null;}
    protected abstract void refresh();
    protected abstract String getLastSearchAttributeName();

	
	protected void setMax(long count) {
		setTotalCount(count);
    	if (count > 0) {
    		long max = count / getNumberOfResults();
    		if (count % getNumberOfResults() > 0) {
    			max++;
    		}
    		setMaxPage(max);
    	}
	}
	
	public long getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(long maxPage) {
		this.maxPage = maxPage;
	}

	public String getCurrentOrderCol() {
		return currentOrderCol;
	}

	public void setCurrentOrderCol(String currentOrderCol) {
		this.currentOrderCol = currentOrderCol;
	}

	public boolean isUpdatedByMe() {
		return updatedByMe;
	}

	public void setUpdatedByMe(boolean updatedByMe) {
		this.updatedByMe = updatedByMe;
	}

	public int getUseLast() {
		return useLast;
	}

	public void setUseLast(int useLast) {
		this.useLast = useLast;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	
//	public int getNumberOfResults();
//	boolean isLastPage();
//	boolean isFirstPage();
//	void nextPage();
//	void prevPage();
//	void order(String col);
//	
//	int getPageSize();
//	void setPageSize(int pageSize);	
}
