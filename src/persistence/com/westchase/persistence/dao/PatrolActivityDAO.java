package com.westchase.persistence.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

import com.westchase.persistence.criteria.PatrolActivitySearchCriteria;
import com.westchase.persistence.dto.patrol.PatrolActivityReportDTO;
import com.westchase.persistence.model.PatrolActivity;

public class PatrolActivityDAO extends BaseDAO<PatrolActivity> {

	public PatrolActivityDAO() {
		super();
	}
	
	private Query getQuery(PatrolActivitySearchCriteria criteria, String select, String alias) {
		StringBuffer query = new StringBuffer(select);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		PatrolActivity pa = criteria.getSearchObject();
		if (pa != null) {
			if (pa.getId() != null) {
				query.append(" and ").append(alias).append(".id = :id ");
				paramMap.put("id", pa.getId());
			}
			if (criteria.getActivityDate() != null) {
				query.append(" and ").append(alias).append(".startDateTime >= :activityDate ");
				paramMap.put("activityDate", criteria.getActivityDate());
			}
			if (pa.getOfficer() != null && pa.getOfficer().getId() != null && pa.getOfficer().getId().intValue() > 0) {
				query.append(" and ").append(alias).append(".officer.id = :officerId ");
				paramMap.put("officerId", pa.getOfficer().getId());
			}
		}
		if (hasListValues(criteria.getPatrolTypeIdList())) {
			query.append(" and p.patrolType.id in (:patrolTypeIdList) ");
		}
		if (StringUtils.isNotBlank(criteria.getOrderCol())) {
			String orderBy = alias + "." + criteria.getOrderCol() + " " + criteria.getOrderDir();
			if (!"officer.lastName".equals(criteria.getOrderCol())) {
				orderBy += ", " + alias + ".officer.lastName asc";
			}
			query.append("order by ").append(orderBy);
		}
		Query q = getSession().createQuery(query.toString());
		if (paramMap != null && !paramMap.isEmpty()) {
			for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
				q.setParameter(entry.getKey(), entry.getValue());
			}
		}
		if (hasListValues(criteria.getPatrolTypeIdList())) {
			q.setParameterList("patrolTypeIdList", criteria.getPatrolTypeIdList());
		}
		return q;
	}
	
	public List<PatrolActivity> findAll(PatrolActivitySearchCriteria criteria) {
		List<PatrolActivity> patrolActivities = null;
		try {
			Query q = getQuery(criteria, "select p from PatrolActivity p where p.deleted = 0 ", "p");
			patrolActivities = prepareQuery(q, criteria).list();		
		} catch (Exception e) {
			log.error("", e);
		}		
		return patrolActivities;
	}

	public long findAllCount(PatrolActivitySearchCriteria criteria) {
		long count = 0;
		try {
			Long l = null;
			Query q = getQuery(criteria, "select count(p) from PatrolActivity p where p.deleted = 0 ", "p");
			l = (Long) q.uniqueResult();	
			if (l != null) {
				count = l.longValue();
			}			
		} catch (Exception e) {
			log.error("", e);
		}
		return count;
	}

	public List<PatrolActivityReportDTO> runReport(List<Integer> officerIdList, Date startDate, Date endDate, List<Integer> patrolTypeIdList) {
		List<PatrolActivityReportDTO> results = null;
		
		Date now = new Date(); // date used for coalesce with null patrol times (hikeBike)
		
		StringBuffer query = new StringBuffer("select new com.westchase.persistence.dto.patrol.PatrolActivityReportDTO(")
		
		.append("p.officer, ") 
		.append("sum(timestampdiff(MINUTE, coalesce(p.startDateTime, :now), coalesce(p.endDateTime, :now))), ")
		.append("sum(p.endMiles - p.startMiles), ")
		.append("sum(timestampdiff(MINUTE, coalesce(p.hikePatrolledDateTimeStart1, :now), coalesce(p.hikePatrolledDateTimeEnd1, :now))) + ")
			.append("sum(timestampdiff(MINUTE, coalesce(p.hikePatrolledDateTimeStart2, :now), coalesce(p.hikePatrolledDateTimeEnd2, :now))) + ")
			.append("sum(timestampdiff(MINUTE, coalesce(p.hikePatrolledDateTimeStart3, :now), coalesce(p.hikePatrolledDateTimeEnd3, :now))), ")
			

		.append("sum(case when p.patrolType.id = 1 then 1 else 0 end), ")
		.append("sum(case when p.patrolType.id = 2 then 1 else 0 end), ")
		.append("sum(case when p.patrolType.id = 3 then 1 else 0 end), ")
		.append("sum(case when p.patrolType.id = 4 then 1 else 0 end), ")
		.append("sum(case when p.patrolType.id = 6 then 1 else 0 end), ")
		.append("sum(case when p.patrolType.id = 5 then 1 else 0 end), ")
		
		.append("sum(p.crimeArrestsFelony), ")
		.append("sum(p.crimeArrestsClassAbMisdemeanor), ")
		.append("sum(p.crimeArrestsClassCTicket), ")
		.append("sum(p.crimeArrestsTrafficDrt), ")
		.append("sum(p.warrantsCity), ")
		.append("sum(p.warrantsFelony), ")
		.append("sum(p.warrantsMisdemeanor), ")
		.append("sum(p.warrantsSetcic), ")
		.append("sum(p.drtInvestigationsWarnings), ")
		.append("sum(p.drtInvestigationsAbatements), ")
		.append("sum(p.drtInvestigationsTickets), ")
		.append("sum(p.drtInvestigationsOffenseReports), ")
		.append("sum(p.fieldParking), ")
		.append("sum(p.fieldChargesFiled), ")
		.append("sum(p.fieldSuspectsInJail), ")
		.append("sum(p.fieldHolds), ")
		.append("sum(p.fieldTrafficStops), ")
		.append("sum(p.trafficMoving), ")
		.append("sum(p.trafficNonMoving), ")
		
		.append("sum(p.primaryCalls), ")
		.append("sum(p.secondaryCalls), ")
		.append("sum(p.onViewsFlaggedDown), ")
		.append("sum(p.incidentReports), ")
		.append("sum(p.accidentReports), ")
		.append("sum(p.supplementReports), ")
		.append("sum(p.crimeInitiatives), ")
		.append("sum(p.crimeInitiativesInWcVehicle), ")
		.append("sum(p.adminAssignments), ")
		.append("sum(case when p.amChecklistCompleted is true then 1 else 0 end), ")
		.append("sum(case when p.businessChecksCompletedEast is true then 1 else 0 end), ")
		.append("sum(case when p.businessChecksCompletedWest is true then 1 else 0 end), ")
		.append("sum(p.communityApartmentLiaisonMeetings), ")
		.append("sum(p.communityHotelLiaisonMeetings), ")
		.append("sum(p.communityRetailLiaisonMeetings), ")
		.append("sum(p.communityOfficeBuildingLiasonMeetings), ")
		.append("sum(p.communityCitizenContacts), ")
		.append("sum(p.communityCrimePreventionPamphlets), ")
		.append("sum(p.communityEvents), ")
		.append("sum(p.communityCptedInspections), ")
		.append("sum(p.communityCrimePreventionSeminars) ")
		
		.append(") from PatrolActivity p where p.deleted = 0 ");
		
		if (hasListValues(officerIdList)) {
			query.append(" and p.officer.id in (:officerIdList) ");
		}
		if (startDate != null) {
			query.append(" and p.startDateTime >= :startDate ");
		}
		if (endDate != null) {
			query.append(" and p.startDateTime < :endDate ");
		}
		
		if (hasListValues(patrolTypeIdList)) {
			query.append(" and p.patrolType.id in (:patrolTypeIdList) ");
		}
		
		query.append(" group by p.officer.id order by p.officer.lastName, p.officer.firstName ");
		try {
			Query q = getSession().createQuery(query.toString());
			q.setParameter("now", now);
			if (hasListValues(officerIdList)) {
				q.setParameterList("officerIdList", officerIdList);
			}
			if (startDate != null) {
				q.setParameter("startDate", startDate);
			}
			if (endDate != null) {
				q.setParameter("endDate", endDate);
			}
			if (hasListValues(patrolTypeIdList)) {
				q.setParameterList("patrolTypeIdList", patrolTypeIdList);
			}
			results = q.list();
		} catch (Exception e) {
			log.error("", e);
		}
		return results;
	}
	
	private boolean hasListValues(List<Integer> listValues) {
		boolean hasListValues = false;
		if (listValues != null && !listValues.isEmpty()) {
			for (Integer listValue : listValues) {
				if (listValue != null && listValue.intValue() > -1) {
					hasListValues = true;
				}
			}
		}
		return hasListValues;
	}

	public List<PatrolActivity> listOtherByOfficerAndDay(Long id, Integer officerId, Date activityDate) {
		List<PatrolActivity> patrolActivityList = null;
		String query = "select pa from PatrolActivity pa where pa.officer.id = :officerId and pa.startDateTime >= :activityDate and pa.startDateTime < date_add_interval(:activityDate, 1, day) ";
		if (id != null && id.longValue() > 0) {
			query += " and pa.id != :currentId";
		}
		
		try {
			Query q = getSession().createQuery(query).setParameter("officerId", officerId).setParameter("activityDate", activityDate);
			if (id != null && id.longValue() > 0) {
				q.setParameter("currentId", id);
			}
			patrolActivityList = q.list();
		} catch (Exception e) {
			log.error("", e);
		}
		return patrolActivityList;
	}

//	public Date getMaxEndDateForOfficer(Integer officerId) {
//		Date maxEndDate = null;
//		String query = "select max(p.endDateTime) from PatrolActivity p where p.officer.id = :officerId";
//		try {
//			maxEndDate = (Date) getSession().createQuery(query).setParameter("officerId", officerId).uniqueResult();
//		} catch (Exception e) {
//			log.error("", e);
//		}
//		return maxEndDate;
//	}

}
