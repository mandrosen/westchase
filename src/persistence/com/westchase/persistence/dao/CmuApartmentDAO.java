package com.westchase.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import com.westchase.persistence.model.CmuApartment;

/**
 * @author marc
 *
 */
public class CmuApartmentDAO extends BaseDAO<CmuApartment> {

	@SuppressWarnings("unchecked")
	public List<CmuApartment> listAll(int quarterId) {
		List<CmuApartment> cmuApts = new ArrayList<CmuApartment>();
		String query = "select c from CmuApartment c where c.cmuQuarter.id = :quarter";
		try {
			cmuApts = getSession().createQuery(query).setParameter("quarter", quarterId).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return cmuApts;
	}

}
