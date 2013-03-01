package com.westchase.persistence.dao;

import com.westchase.persistence.model.PatrolActivityDetailCitizen;

public class PatrolActivityDetailCitizenDAO extends BaseDAO<PatrolActivityDetailCitizen> {

	public PatrolActivityDetailCitizenDAO() {
		super();
	}

	public void deleteAllForDetail(Long patrolActivityDetailId) {
		String query = "delete from PatrolActivityDetailCitizen padc where padc.patrolActivityDetail.id = :patrolActivityDetailId";
		try {
			getSession().createQuery(query).setParameter("patrolActivityDetailId", patrolActivityDetailId).executeUpdate();
		} catch (Exception e) {
			log.error("", e);
		}
	}

}
