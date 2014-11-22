package com.westchase.persistence.dto.patrol;

import java.io.Serializable;
import java.util.Date;

import com.westchase.persistence.model.Officer;
import com.westchase.persistence.model.PatrolType;

public class PatrolActivitySearchResultDTO implements Serializable {

	private Long id;
	private Officer officer;
	private Date activityDate;
	private PatrolType patrolType;
	
	public PatrolActivitySearchResultDTO() {
		super();
	}

	public PatrolActivitySearchResultDTO(Long id, Officer officer, Date activityDate, PatrolType patrolType) {
		this();
		setId(id);
		setOfficer(officer);
		setActivityDate(activityDate);
		setPatrolType(patrolType);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Officer getOfficer() {
		return officer;
	}

	public void setOfficer(Officer officer) {
		this.officer = officer;
	}

	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	public PatrolType getPatrolType() {
		return patrolType;
	}

	public void setPatrolType(PatrolType patrolType) {
		this.patrolType = patrolType;
	}

}
