package com.westchase.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import com.westchase.persistence.dto.cmu.CmuOfficeRetailSvcDTO;
import com.westchase.persistence.model.CmuLease;
import com.westchase.persistence.model.CmuOfficeRetailSvc;

/**
 * @author marc
 *
 */
public class CmuOfficeRetailSvcDAO extends BaseDAO<CmuOfficeRetailSvc> {
	
	@SuppressWarnings("unchecked")
	public List<CmuOfficeRetailSvc> listAll(int quarterId, int businessTypeId) {
		String query = "";
		if (businessTypeId == 1) {
			query = "select c from CmuOfficeRetailSvc c where c.cmuQuarter.id = :quarter and c.property.businessType like 'Office%'";
		} else if (businessTypeId == 2) {
			query = "select c from CmuOfficeRetailSvc c where c.cmuQuarter.id = :quarter and c.property.businessType like 'Retail%'";
		} else if (businessTypeId == 3) {
			query = "select c from CmuOfficeRetailSvc c where c.cmuQuarter.id = :quarter and (c.property.businessType like 'Service%' or c.property.businessType like 'Ind%')";
		}
		List<CmuOfficeRetailSvc> ors = new ArrayList<CmuOfficeRetailSvc>();
		try {
			ors = getSession().createQuery(query).setParameter("quarter", quarterId).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return ors;
	}

	public CmuOfficeRetailSvcDTO getWithLeases(Integer id) {
		CmuOfficeRetailSvcDTO dto = null;
		
		String query1 = "select c from CmuOfficeRetailSvc c where c.id = :id";

		String query2 = "select l from CmuOfficeRetailSvc c, CmuLease l where c.id = :id and c.property.id = l.property.id and c.cmuQuarter.id = l.cmuQuarter.id";
		
		try {
			CmuOfficeRetailSvc officeRetailSvc = (CmuOfficeRetailSvc) getSession().createQuery(query1).setParameter("id", id).uniqueResult();
			List<CmuLease> leases = getSession().createQuery(query2).setParameter("id", id).list();
			
			dto = new CmuOfficeRetailSvcDTO(officeRetailSvc, leases);
		} catch (Exception e) {
			log.error("", e);
		}
		return dto;
	}
	
}
