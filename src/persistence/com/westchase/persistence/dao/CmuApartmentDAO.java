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

	public CmuApartment getByPropertyQuarter(Integer propertyId, Integer quarterId) {
		CmuApartment cmuApt = null;
		String query = "select c from CmuApartment c where c.property.id = :propertyId and c.cmuQuarter.id = :quarter";
		try {
			cmuApt = (CmuApartment) getSession().createQuery(query).setParameter("propertyId", propertyId).setParameter("quarter", quarterId).setMaxResults(1).uniqueResult();
		} catch (Exception e) {
			log.error("", e);
		}
		return cmuApt;
	}

}
