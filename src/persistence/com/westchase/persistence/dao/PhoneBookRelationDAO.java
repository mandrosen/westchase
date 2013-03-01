package com.westchase.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import com.westchase.persistence.model.PhoneBookRelation;

/**
 * @author marc
 *
 */
public class PhoneBookRelationDAO extends BaseDAO<PhoneBookRelation> {

	public List<PhoneBookRelation> list(Integer phoneBookId) {
		List<PhoneBookRelation> relations = new ArrayList<PhoneBookRelation>();
		try {
			relations = getSession().createQuery("select r from PhoneBookRelation r where r.phoneBook.id = :phoneBook and r.property.deleted = 0 order by r.property.id").setParameter("phoneBook", phoneBookId).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return relations;
	}

}
