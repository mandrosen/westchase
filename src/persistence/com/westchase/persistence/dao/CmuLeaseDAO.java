package com.westchase.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import com.westchase.persistence.dto.cmu.report.LeaseStatsDTO;
import com.westchase.persistence.dto.cmu.report.LeaseStatsType;
import com.westchase.persistence.model.CmuLease;

/**
 * @author marc
 *
 */
public class CmuLeaseDAO extends BaseDAO<CmuLease> {

	@SuppressWarnings("unchecked")
	public List<CmuLease> listAll(int quarterId) {
		List<CmuLease> leases = new ArrayList<CmuLease>();
		String query = "select c from CmuLease c where c.cmuQuarter.id = :quarter order by c.property.businessType, c.property.buildingName";
		try {
			leases = getSession().createQuery(query).setParameter("quarter", quarterId).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return leases;
	}
	
	@SuppressWarnings("unchecked")
	public LeaseStatsDTO getStats(int quarterId) {
		LeaseStatsDTO stats = new LeaseStatsDTO();
		
		String query = "select new com.westchase.persistence.dto.cmu.report.LeaseStatsType(c.cmuTransactionType.description, count(c.id), sum(c.sqFt)) from CmuLease c where c.cmuQuarter.id = :quarter group by c.cmuTransactionType.id";
		try {
			List<LeaseStatsType> statsTypeList = getSession().createQuery(query).setParameter("quarter", quarterId).list();
			stats.setTransTypeStats(statsTypeList);
		} catch (Exception e) {
			log.error("", e);
		}
		
		query = "select new com.westchase.persistence.dto.cmu.report.LeaseStatsType(c.property.businessType, count(c.id), sum(c.sqFt)) from CmuLease c where c.cmuQuarter.id = :quarter group by c.property.businessType";
		try {
			List<LeaseStatsType> statsTypeList = getSession().createQuery(query).setParameter("quarter", quarterId).list();
			stats.setTransTypeStats(statsTypeList);
		} catch (Exception e) {
			log.error("", e);
		}		
		
		return stats;
	}

}
