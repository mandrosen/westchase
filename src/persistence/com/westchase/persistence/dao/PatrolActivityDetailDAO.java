package com.westchase.persistence.dao;

import java.util.List;

import com.westchase.persistence.model.PatrolActivityDetail;

public class PatrolActivityDetailDAO extends BaseDAO<PatrolActivityDetail> {

	public PatrolActivityDetailDAO() {
		super();
	}
	
	public List<PatrolActivityDetail> findByActivity(Long patrolActivityId) {
		List<PatrolActivityDetail> detailList = null;
		String query = "select pad from PatrolActivityDetail pad where pad.patrolActivity.id = :patrolActivityId order by pad.receivedDateTime";
		try {
			detailList = getSession().createQuery(query).setParameter("patrolActivityId", patrolActivityId).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return detailList;
	}

}
