package com.westchase.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import com.westchase.persistence.dto.cmu.report.ApartmentDTO;
import com.westchase.persistence.model.CmuApartment;

/**
 * @author marc
 *
 */
public class CmuApartmentDAO extends BaseDAO<CmuApartment> {

	/*@SuppressWarnings("unchecked")
	public List<CmuApartment> listAll(int quarterId) {
		List<CmuApartment> cmuApts = new ArrayList<CmuApartment>();
		String query = "select c from CmuApartment c where c.cmuQuarter.id = :quarter";
		try {
			cmuApts = getSession().createQuery(query).setParameter("quarter", quarterId).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return cmuApts;
	}*/
	
	@SuppressWarnings("unchecked")
	public List<ApartmentDTO> listAll(int quarterId) {
		List<ApartmentDTO> aptList = new ArrayList<ApartmentDTO>();
		String query = "select new com.westchase.persistence.dto.cmu.report.ApartmentDTO(c) from CmuApartment c where c.cmuQuarter.id = :quarter";
		try {
			aptList = getSession().createQuery(query).setParameter("quarter", quarterId).list();
		} catch (Exception e) {
			log.error("", e);
		}
		if (aptList != null) {
			for (ApartmentDTO apt : aptList) {
				setCommunityManager(apt);
				setSupervisor(apt);
			}
		}
		return aptList;
	}

	private void setSupervisor(ApartmentDTO apt) {
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
				"pb.Email\r\n" + 
			"from phone_book_category ca\r\n" + 
			"inner join phone_book_relation pbr on ca.phonebookid = pbr.phone_book\r\n" + 
			"inner join phone_book pb on pbr.phone_book = pb.id\r\n" + 
			"inner join company c on pb.companyid = c.id\r\n" + 
			"where ca.categorycode = 'as'\r\n" + 
			"and pbr.property = :propertyId";
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
					.addScalar("Email", Hibernate.STRING)
					.setParameter("propertyId", apt.getProperty().getId()).setMaxResults(1).uniqueResult();
			if (resultAry != null) {
				apt.setSupervisor((String) resultAry[0], (String) resultAry[1]);
				apt.setMgmtCompany((String) resultAry[2]);
				apt.setMgmtCompanyAddr((String) resultAry[3], (String) resultAry[4], (String) resultAry[5], (String) resultAry[6], (String) resultAry[7], (String) resultAry[8]);
				apt.setSupervisorEmail((String) resultAry[11]);
				apt.setSupervisorPhone((String) resultAry[9], (String) resultAry[10]);
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}

	private void setCommunityManager(ApartmentDTO apt) {
		String query = "select pb.FirstName, " +
				"pb.LastName, " +
				"pb.WkPhone, " +
				"pb.Wkext, " +
				"pb.Email\r\n" + 
			"from phone_book_category ca\r\n" + 
			"inner join phone_book_relation pbr on ca.phonebookid = pbr.phone_book\r\n" + 
			"inner join phone_book pb on pbr.phone_book = pb.id\r\n" + 
			"where ca.categorycode = 'am' " + 
			"  and pbr.property = :propertyId";
		try {
			Object[] resultAry = (Object[]) getSession().createSQLQuery(query)
					.addScalar("FirstName", Hibernate.STRING)
					.addScalar("LastName", Hibernate.STRING)
					.addScalar("WkPhone", Hibernate.STRING)
					.addScalar("Wkext", Hibernate.STRING)
					.addScalar("Email", Hibernate.STRING)
					.setParameter("propertyId", apt.getProperty().getId()).setMaxResults(1).uniqueResult();
			if (resultAry != null) {
				apt.setCommunityMgr((String) resultAry[0], (String) resultAry[1]);
				apt.setCommunityMgrEmail((String) resultAry[4]);
				apt.setCommunityMgrPhone((String) resultAry[2], (String) resultAry[3]);				
			}
		} catch (Exception e) {
			log.error("", e);
		}
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
