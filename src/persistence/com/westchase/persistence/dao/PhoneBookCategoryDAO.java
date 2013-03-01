package com.westchase.persistence.dao;

import com.westchase.persistence.model.PhoneBookCategory;

/**
 * @author marc
 *
 */
public class PhoneBookCategoryDAO extends BaseDAO<PhoneBookCategory> {

	public void deleteAllForPerson(Integer id) {
		String query = "delete from PhoneBookCategory pc where pc.phoneBook.id = :phonebook";
		try {
			getSession().createQuery(query).setParameter("phonebook", id).executeUpdate();
		} catch (Exception e) {
			log.error("", e);
		}
	}

}
