package com.westchase.persistence.criteria;

import java.util.Date;
import java.util.List;

import com.westchase.persistence.model.PatrolActivity;

public class PatrolActivitySearchCriteria extends SearchCriteria<PatrolActivity> {
	
	private Date startDate;
	private Date endDate;
	
	private List<Integer> patrolTypeIdList;

	public PatrolActivitySearchCriteria() {
		super();
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<Integer> getPatrolTypeIdList() {
		return patrolTypeIdList;
	}

	public void setPatrolTypeIdList(List<Integer> patrolTypeIdList) {
		this.patrolTypeIdList = patrolTypeIdList;
	}

}
