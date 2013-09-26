package com.westchase.persistence.dao;

import java.util.List;

import com.westchase.persistence.model.PatrolDetailType;

public class PatrolDetailTypeDAO extends BaseDAO<PatrolDetailType> {

	public PatrolDetailTypeDAO() {
		super();
	}

	public List<PatrolDetailType> findAll(List<Integer> itemIdList) {
		List<PatrolDetailType> patrolDetailTypeList = null;
		String query = "select pa from PatrolDetailType pa where pa.id in (:itemIdList) order by pa.name ";
		try {
			patrolDetailTypeList = getSession().createQuery(query).setParameterList("itemIdList", itemIdList).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return patrolDetailTypeList;
	}

}
