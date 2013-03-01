package com.westchase.persistence.criteria;

import java.util.Date;

import com.westchase.persistence.model.PatrolActivity;

public class PatrolActivitySearchCriteria extends SearchCriteria<PatrolActivity> {
	
	private Date activityDate;

	public PatrolActivitySearchCriteria() {
		super();
	}

	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

}
