package com.westchase.persistence.dao;

import java.util.List;

import com.westchase.persistence.model.PatrolDetailCategory;

public class PatrolDetailCategoryDAO extends BaseDAO<PatrolDetailCategory> {

	public PatrolDetailCategoryDAO() {
		super();
	}

	public List<PatrolDetailCategory> findAll(List<Integer> itemIdList) {
		List<PatrolDetailCategory> patrolDetailCategoryList = null;
		String query = "select pa from PatrolDetailCategory pa where pa.id in (:itemIdList) order by pa.name ";
		try {
			patrolDetailCategoryList = getSession().createQuery(query).setParameterList("itemIdList", itemIdList).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return patrolDetailCategoryList;
	}

}
