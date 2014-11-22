package com.westchase.persistence.dao;

import java.util.List;

import com.westchase.persistence.model.Officer;
import com.westchase.persistence.model.PatrolActivityOfficer;

public class PatrolActivityOfficerDAO extends BaseDAO<PatrolActivityOfficer> {

	public PatrolActivityOfficerDAO() {
		super();
	}

	public List<Officer> listOfficers(Long patrolActivityId) {
		List<Officer> officerList = null;
		String query = "select pao.officer from PatrolActivityOfficer pao where pao.patrolActivity.id = :patrolActivityId";
		try {
			officerList = getSession().createQuery(query).setParameter("patrolActivityId", patrolActivityId).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return officerList;
	}

	public int removeAllForPatrolActivity(Long patrolActivityId) {
		int result = -1;
		String query = "delete PatrolActivityOfficer pao where pao.patrolActivity.id = :patrolActivityId";
		try {
			result = getSession().createQuery(query).setParameter("patrolActivityId", patrolActivityId).executeUpdate();
		} catch (Exception e) {
			log.error("", e);
		}
		return result;
	}

}
