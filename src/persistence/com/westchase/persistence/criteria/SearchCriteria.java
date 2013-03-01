package com.westchase.persistence.criteria;

import java.io.Serializable;

public abstract class SearchCriteria<E> implements Serializable {

	private int page;
	
	private int numberOfResults;
	
	private String orderCol;
	
	private String orderDir;
	
	private Integer employeeId;
	
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

	private E searchObject;
	
	public E getSearchObject() {
		return searchObject;
	}

	public void setSearchObject(E searchObject) {
		this.searchObject = searchObject;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getNumberOfResults() {
		return numberOfResults;
	}

	public void setNumberOfResults(int numberOfResults) {
		this.numberOfResults = numberOfResults;
	}

	public SearchCriteria() {
		super();
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	
	

}
