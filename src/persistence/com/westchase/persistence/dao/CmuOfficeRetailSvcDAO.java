package com.westchase.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import com.westchase.persistence.dto.cmu.CmuOfficeRetailSvcDTO;
import com.westchase.persistence.dto.cmu.report.OfficeRetailSvcDTO;
import com.westchase.persistence.model.CmuLease;
import com.westchase.persistence.model.CmuOfficeRetailSvc;

/**
 * @author marc
 *
 */
public class CmuOfficeRetailSvcDAO extends BaseDAO<CmuOfficeRetailSvc> {
	
	/*@SuppressWarnings("unchecked")
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
	}*/
	
	@SuppressWarnings("unchecked")
	public List<OfficeRetailSvcDTO> listAll(int quarterId, int businessTypeId) {
		String query = "";
		if (businessTypeId == 1) {
			query = "select new com.westchase.persistence.dto.cmu.report.OfficeRetailSvcDTO(c) from CmuOfficeRetailSvc c where c.cmuQuarter.id = :quarter and c.property.businessType like 'Office%'";
		} else if (businessTypeId == 2) {
			query = "select new com.westchase.persistence.dto.cmu.report.OfficeRetailSvcDTO(c) from CmuOfficeRetailSvc c where c.cmuQuarter.id = :quarter and c.property.businessType like 'Retail%'";
		} else if (businessTypeId == 3) {
			query = "select new com.westchase.persistence.dto.cmu.report.OfficeRetailSvcDTO(c) from CmuOfficeRetailSvc c where c.cmuQuarter.id = :quarter and (c.property.businessType like 'Service%' or c.property.businessType like 'Ind%')";
		}
		List<OfficeRetailSvcDTO> orsList = new ArrayList<OfficeRetailSvcDTO>();
		try {
			orsList = getSession().createQuery(query).setParameter("quarter", quarterId).list();
		} catch (Exception e) {
			log.error("", e);
		}
		if (orsList != null) {
			for (OfficeRetailSvcDTO ors : orsList) {
				setLeasingCompany(ors);
				setManagementCompany(ors);
			}
		}
		return orsList;
	}

	private void setManagementCompany(OfficeRetailSvcDTO ors) {
		if (ors != null && ors.getProperty() != null && ors.getProperty().getId() != null) {
			String query = "select pb.FirstName, " +
					"pb.LastName, " +
					"pb.Email, " +
					"pb.WkPhone, " +
					"pb.Wkext, " +
					"pb.FaxPhone, " + 
					"c.Company, " +
					"c.StNumber, " +
					"c.StAddress, " +
					"c.RoomNo, " +
					"c.City, " +
					"c.State, " +
					"c.ZipCode\r\n" + 
					"from phone_book pb\r\n" + 
					"	inner join company c on pb.companyid = c.id\r\n" + 
					"	inner join phone_book_category pbc on pb.id = pbc.phonebookid\r\n" + 
					"	inner join phone_book_relation pbr on pb.id = pbr.phone_book\r\n" + 
					"	inner join property p on pbr.property = p.id\r\n" + 
					"where pbc.categorycode = 'bm'\r\n" + 
					"	and p.id = :propertyId";
			try {
				Object[] resultAry = (Object[]) getSession().createSQLQuery(query)
						.addScalar("FirstName", Hibernate.STRING)
						.addScalar("LastName", Hibernate.STRING)
						.addScalar("Email", Hibernate.STRING)
						.addScalar("WkPhone", Hibernate.STRING)
						.addScalar("Wkext", Hibernate.STRING)
						.addScalar("FaxPhone", Hibernate.STRING)
						.addScalar("Company", Hibernate.STRING)
						.addScalar("StNumber", Hibernate.STRING)
						.addScalar("StAddress", Hibernate.STRING)
						.addScalar("RoomNo", Hibernate.STRING)
						.addScalar("City", Hibernate.STRING)
						.addScalar("State", Hibernate.STRING)
						.addScalar("ZipCode", Hibernate.STRING)
						.setParameter("propertyId", ors.getProperty().getId()).setMaxResults(1).uniqueResult();
				if (resultAry != null) {
					ors.setPropertyMgr((String) resultAry[0], (String) resultAry[1]);
					ors.setPropertyMgrEmail((String) resultAry[2]);
					ors.setPropertyMgrPhone((String) resultAry[3], (String) resultAry[4]);
					ors.setMgmtCompany((String) resultAry[6]);
					ors.setMgmtCompanyAddr((String) resultAry[7], (String) resultAry[8], (String) resultAry[9], (String) resultAry[10], (String) resultAry[11], (String) resultAry[12]); 
				}
			} catch (Exception e) {
				log.error("", e);
			}
		}
	}

	private void setLeasingCompany(OfficeRetailSvcDTO ors) {
		if (ors != null && ors.getProperty() != null && ors.getProperty().getId() != null) {
			String query = "select pb.FirstName, " +
					"pb.LastName, " +
					"c.Company, " +
					"c.StNumber, " +
					"c.StAddress, " +
					"c.RoomNo, " +
					"c.City, " +
					"c.State, " +
					"c.ZipCode, " +
					"pb.WkPhone, " +
					"pb.Wkext, " +
					"pb.FaxPhone, " +
					"pb.Email\r\n" + 
					"from phone_book_category ca\r\n" + 
					"inner join phone_book_relation pbr on ca.phonebookid = pbr.phone_book\r\n" + 
					"inner join phone_book pb on pbr.phone_book = pb.id\r\n" + 
					"inner join company c on pb.companyid = c.id\r\n" + 
					"where ca.categorycode = 'la'\r\n" + 
					"  and pbr.property = :propertyId";
			try {
				Object[] resultAry = (Object[]) getSession().createSQLQuery(query)
						.addScalar("FirstName", Hibernate.STRING)
						.addScalar("LastName", Hibernate.STRING)
						.addScalar("Company", Hibernate.STRING)
						.addScalar("StNumber", Hibernate.STRING)
						.addScalar("StAddress", Hibernate.STRING)
						.addScalar("RoomNo", Hibernate.STRING)
						.addScalar("City", Hibernate.STRING)
						.addScalar("State", Hibernate.STRING)
						.addScalar("ZipCode", Hibernate.STRING)
						.addScalar("WkPhone", Hibernate.STRING)
						.addScalar("Wkext", Hibernate.STRING)
						.addScalar("FaxPhone", Hibernate.STRING)
						.addScalar("Email", Hibernate.STRING)
						.setParameter("propertyId", ors.getProperty().getId()).setMaxResults(1).uniqueResult();
				if (resultAry != null) {
					ors.setLeasingAgent((String) resultAry[0], (String) resultAry[1]);
					ors.setLeasingAgentEmail((String) resultAry[12]);
					ors.setLeasingAgentPhone((String) resultAry[9], (String) resultAry[10]);
					ors.setLeasingCompany((String) resultAry[2]);
					ors.setLeasingCompanyAddr((String) resultAry[3], (String) resultAry[4], (String) resultAry[5], (String) resultAry[6], (String) resultAry[7], (String) resultAry[8]); 
				}
			} catch (Exception e) {
				log.error("", e);
			}
		}
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

	public CmuOfficeRetailSvc getByPropertyQuarter(Integer propertyId, Integer quarterId) {
		CmuOfficeRetailSvc cmuOrs = null;
		String query = "select c from CmuOfficeRetailSvc c where c.property.id = :propertyId and c.cmuQuarter.id = :quarter";
		try {
			cmuOrs = (CmuOfficeRetailSvc) getSession().createQuery(query).setParameter("propertyId", propertyId).setParameter("quarter", quarterId).setMaxResults(1).uniqueResult();
		} catch (Exception e) {
			log.error("", e);
		}
		return cmuOrs;
	}
	
}
