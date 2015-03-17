package com.westchase.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import com.westchase.persistence.dto.cmu.report.DevsiteDTO;
import com.westchase.persistence.model.CmuDevsite;

/**
 * @author marc
 *
 */
public class CmuDevsiteDAO extends BaseDAO<CmuDevsite> {
	
	/*@SuppressWarnings("unchecked")
	public List<CmuDevsite> listAll(int quarterId) {
		List<CmuDevsite> devsites = new ArrayList<CmuDevsite>();
		String query = "select c from CmuDevsite c where c.cmuQuarter.id = :quarter";
		try {
			devsites = getSession().createQuery(query).setParameter("quarter", quarterId).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return devsites;
	}*/
	
	@SuppressWarnings("unchecked")
	public List<DevsiteDTO> listAll(int quarterId) {
		List<DevsiteDTO> devList = new ArrayList<DevsiteDTO>();
		String query = "select new com.westchase.persistence.dto.cmu.report.DevsiteDTO(c) from CmuDevsite c where c.cmuQuarter.id = :quarter";
		try {
			devList = getSession().createQuery(query).setParameter("quarter", quarterId).list();
		} catch (Exception e) {
			log.error("", e);
		}
//		if (devList != null) {
//			for (DevsiteDTO apt : aptList) {
//			}
//		}
		return devList;
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
