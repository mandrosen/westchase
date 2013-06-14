package com.westchase.persistence.criteria;

import java.util.Date;
import java.util.List;

import com.westchase.persistence.model.PatrolActivity;

public class PatrolActivitySearchCriteria extends SearchCriteria<PatrolActivity> {
	
	private Date activityDate;
	
	private List<Integer> patrolTypeIdList;

	public PatrolActivitySearchCriteria() {
		super();
	}

	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	public List<Integer> getPatrolTypeIdList() {
		return patrolTypeIdList;
	}

	public void setPatrolTypeIdList(List<Integer> patrolTypeIdList) {
		this.patrolTypeIdList = patrolTypeIdList;
	}

}
