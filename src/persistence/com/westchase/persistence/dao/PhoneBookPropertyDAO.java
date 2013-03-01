package com.westchase.persistence.dao;

import com.westchase.persistence.model.PhoneBookProperty;

/**
 * @author marc
 *
 */
public class PhoneBookPropertyDAO extends BaseDAO<PhoneBookProperty> { 
	public void deleteAllForPerson(Integer id) {
		String query = "delete from PhoneBookProperty pb where pb.phoneBook.id = :phonebook";
		try {
			getSession().createQuery(query).setParameter("phonebook", id).executeUpdate();
		} catch (Exception e) {
			log.error("", e);
		}
	}
}
