package com.westchase.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import com.westchase.persistence.model.CmuDevsite;
import com.westchase.persistence.model.CmuHotel;

/**
 * @author marc
 *
 */
public class CmuDevsiteDAO extends BaseDAO<CmuDevsite> {
	
	@SuppressWarnings("unchecked")
	public List<CmuDevsite> listAll(int quarterId) {
		List<CmuDevsite> devsites = new ArrayList<CmuDevsite>();
		String query = "select c from CmuDevsite c where c.cmuQuarter.id = :quarter";
		try {
			devsites = getSession().createQuery(query).setParameter("quarter", quarterId).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return devsites;
	}

	public CmuDevsite getByPropertyQuarter(Integer propertyId, Integer quarterId) {
		CmuDevsite cmuDevsite = null;
		String query = "select c from CmuDevsite c where c.property.id = :propertyId and c.cmuQuarter.id = :quarter";
		try {
			cmuDevsite = (CmuDevsite) getSession().createQuery(query).setParameter("propertyId", propertyId).setParameter("quarter", quarterId).setMaxResults(1).uniqueResult();
		} catch (Exception e) {
			log.error("", e);
		}
		return cmuDevsite;
	}
	
}
