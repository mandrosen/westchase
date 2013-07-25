package com.westchase.persistence.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.westchase.persistence.criteria.PatrolActivitySearchCriteria;
import com.westchase.persistence.dto.patrol.PatrolDetailTypeDayTimeCountDTO;
import com.westchase.persistence.dto.patrol.OfficerCountDTO;
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
	

	// TODO: fix parameters (start/end date and officerIdList)
	private Map<String, OfficerCountDTO> getOfficerCountMap(String item, String itemJoinColumn, List<Integer> officerIdList, Date startDate, Date endDate) {
		Map<String, OfficerCountDTO> counts = new HashMap<String, OfficerCountDTO>();
		String query = "select " + 
				" t1.officer_id, " + 
				" concat(o.last_name, ' ', o.first_name) as officer_name, " + 
				" t1.type_id, " + 
				" t1.type_name, " + 
				" t1.officer_type_total, " + 
				" t1.officer_total, " + 
				" t1.type_total  " + 
				"from ( " + 
				"select pa.officer_id, " + 
				" item.id as type_id, " + 
				" item.name as type_name, " + 
				" count(pad.id) officer_type_total, " + 
				" (select count(pad1.id) " + 
				" from " + item + " item1 inner join patrol_activity_detail pad1 on pad1." + itemJoinColumn + " = item1.id " + 
				" inner join patrol_activity pa1 on pad1.patrol_activity_id = pa1.id " + 
				" where pa1.officer_id = pa.officer_id ";

			if (hasListValues(officerIdList)) {
				query += " and pa1.officer_id in (:officerIdList) ";
			}
			if (startDate != null) {
				query += " and pa1.start_date_time >= :startDate ";
			}
			if (endDate != null) {
				query += " and pa1.end_date_time < :endDate ";
			}
			
			query += " ) as officer_total, " + 
				" (select count(pad2.id) " + 
				" from " + item + " item2 left outer join patrol_activity_detail pad2 on pad2." + itemJoinColumn + " = item2.id " + 
				" inner join patrol_activity pa2 on pad2.patrol_activity_id = pa2.id " +
				" where item2.id = item.id ";

			if (hasListValues(officerIdList)) {
				query += " and pa2.officer_id in (:officerIdList) ";
			}
			if (startDate != null) {
				query += " and pa2.start_date_time >= :startDate ";
			}
			if (endDate != null) {
				query += " and pa2.end_date_time < :endDate ";
			}
			
			query += " ) as type_total " + 
				"from patrol_activity_detail pad " + 
				" inner join patrol_activity pa on pad.patrol_activity_id = pa.id " + 
				" inner join " + item + " item on pad." + itemJoinColumn + " = item.id " +
				"where 1 = 1 ";
		if (hasListValues(officerIdList)) {
			query += " and pa.officer_id in (:officerIdList) ";
		}
		if (startDate != null) {
			query += " and pa.start_date_time >= :startDate ";
		}
		if (endDate != null) {
			query += " and pa.end_date_time < :endDate ";
		}
		query += "group by pa.officer_id, item.id) t1 " + 
				" inner join officer o on t1.officer_id = o.id";
		try {
			// TODO: get transformer to work
//			Query q = getSession().createSQLQuery(query).setResultTransformer(Transformers.aliasToBean(OfficerCountDTO.class));
			Query q = getSession().createSQLQuery(query)
					.addScalar("officer_id", Hibernate.INTEGER)
					.addScalar("officer_name", Hibernate.STRING)
					.addScalar("type_id", Hibernate.INTEGER)
					.addScalar("type_name", Hibernate.STRING)
					.addScalar("officer_type_total", Hibernate.LONG)
					.addScalar("officer_total", Hibernate.LONG)
					.addScalar("type_total", Hibernate.LONG);
			if (hasListValues(officerIdList)) {
				q.setParameterList("officerIdList", officerIdList);
			}
			if (startDate != null) {
				q.setParameter("startDate", startDate);
			}
			if (endDate != null) {
				q.setParameter("endDate", endDate);
			}
			List<Object[]> countList = q.list();
			if (countList != null && !countList.isEmpty()) {
				for (Object[] count : countList) {
					Integer officerId = (Integer) count[0];
					String officerName = (String) count[1];
					Integer itemId = (Integer) count[2];
					String itemName = (String) count[3];
					Long officerItemTotal = (Long) count[4];
					Long officerTotal = (Long) count[5];
					Long itemTotal = (Long) count[6];
					if (officerId != null && itemId != null) {
						counts.put(officerId + ":" + itemId, new OfficerCountDTO(officerId, officerName, itemId, itemName, officerItemTotal, officerTotal, itemTotal));
						
						// add the total for this item (officer id = 0)
						if (itemTotal != null && itemTotal.longValue() > 0) {
							counts.put("0:" + itemId, new OfficerCountDTO(itemTotal));
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("", e);
		}
		return counts;
	}

	public Map<String, OfficerCountDTO> countDetailTypeByOfficer(List<Integer> officerIdList, Date startDate, Date endDate) {
		return getOfficerCountMap("patrol_detail_type", "patrol_detail_type_id", officerIdList, startDate, endDate);
	}
	
	public Map<String, OfficerCountDTO> countDetailCategoryByOfficer(List<Integer> officerIdList, Date startDate, Date endDate) {
		return getOfficerCountMap("patrol_detail_category", "patrol_detail_category_id", officerIdList, startDate, endDate);
	}

	public List<Date> listAvailableDatesForDetail(Long patrolActivityId) {
		List<Date> dateList = new ArrayList<Date>();
		String query = "select date(pa.startDateTime), date(pa.endDateTime) from PatrolActivity pa where pa.id = :patrolActivityId";
		try {
			Object[] dateObjs = (Object[]) getSession().createQuery(query).setParameter("patrolActivityId", patrolActivityId).setMaxResults(1).uniqueResult();
			if (dateObjs != null && dateObjs.length == 2) {
				Date startDate = (Date) dateObjs[0];
				Date endDate = (Date) dateObjs[1];
				dateList.add(startDate);
				if (endDate.after(startDate)) {
					dateList.add(endDate);
				}
			}
		} catch (Exception e) {
			log.error("", e);
		}
		return dateList;
	}
	
	public List<PatrolDetailTypeDayTimeCountDTO> countDetailTypeByDayTime(Date startDate, Date endDate,
			boolean includeProperty, boolean includeDay, boolean includeTime, 
			List<Integer> patrolDetailTypeIdList, List<Integer> propertyIdList, List<Integer> dayIdList) {
		List<PatrolDetailTypeDayTimeCountDTO> counts = new ArrayList<PatrolDetailTypeDayTimeCountDTO>();
		String query = "select group_concat(pad.id) as id_list, pdt.name as type_name ";
		if (includeProperty) {
			query += ", p.id as mapno, p.buildingName as building_name ";
		}
		if (includeDay) {
			query += ", dayname(pad.received_date_time) as day_name ";
		}
		if (includeTime) {
			query += ", hour(pad.received_date_time) as hour_of_day ";
		}
		query += ", count(1) as type_total " +
				" from patrol_activity_detail pad inner join patrol_detail_type pdt on pad.patrol_detail_type_id = pdt.id ";
		if (includeProperty) {
			query += " inner join property p on pad.location_property_id = p.id ";
		}
		query += " where pad.received_date_time is not null ";
		if (startDate != null) {
			query += " and pad.received_date_time >= :startDate ";
		}
		if (endDate != null) {
			query += " and pad.received_date_time < :endDate ";
		}
		if (hasListValues(patrolDetailTypeIdList)) {
			query += " and pdt.id in (:patrolDetailTypeIdList) ";
		}
		if (hasListValues(propertyIdList)) {
			query += " and p.id in (:propertyIdList) ";
		}
		if (hasListValues(dayIdList)) {
			query += " and dayofweek(pad.received_date_time) in (:dayIdList) ";
		}
		
		query += " group by pdt.id ";
		if (includeProperty) {
			query += ", p.id ";
		}
		if (includeDay) {
			query += ", dayofweek(pad.received_date_time) ";
		}
		if (includeTime) {
			query += ", hour(pad.received_date_time) ";
		}
		query += " order by type_name";
		if (includeDay) {
			query += ", dayofweek(pad.received_date_time) ";
		}
		if (includeTime) {
			query += ", hour_of_day ";
		}
		System.out.println(query);
		try {
			SQLQuery q = getSession().createSQLQuery(query)
					.addScalar("id_list", Hibernate.STRING)
					.addScalar("type_name", Hibernate.STRING);
			if (includeProperty) {
				q.addScalar("mapno", Hibernate.INTEGER);
				q.addScalar("building_name", Hibernate.STRING);
			}
			if (includeDay) {
				q.addScalar("day_name", Hibernate.STRING);
			}
			if (includeTime) {
				q.addScalar("hour_of_day", Hibernate.INTEGER);
			}
			q.addScalar("type_total", Hibernate.LONG);

			if (hasListValues(patrolDetailTypeIdList)) {
				q.setParameterList("patrolDetailTypeIdList", patrolDetailTypeIdList);
			}
			if (hasListValues(propertyIdList)) {
				q.setParameterList("propertyIdList", propertyIdList);
			}
			if (hasListValues(dayIdList)) {
				q.setParameterList("dayIdList", dayIdList);
			}
			
			if (startDate != null) {
				q.setParameter("startDate", startDate);
			}
			if (endDate != null) {
				q.setParameter("endDate", endDate);
			}
			
			List<Object[]> countList = q.list();
			if (countList != null && !countList.isEmpty()) {
				for (Object[] count : countList) {
					Integer propertyId = null;
					String propertyName = null;
					int col = 0;
					String idList = (String) count[col++];
					String typeName = (String) count[col++];
					String dayName = null;
					Integer hourOfDay = null;
					if (includeProperty) {
						propertyId = (Integer) count[col++];
						propertyName = (String) count[col++];
					}
					if (includeDay) {
						dayName = (String) count[col++];
					}
					if (includeTime) {
						hourOfDay = (Integer) count[col++];
					}
					Long typeTotal = (Long) count[col++];
					counts.add(new PatrolDetailTypeDayTimeCountDTO(idList, typeName, propertyId, propertyName, dayName, hourOfDay, typeTotal));
				}
			}
		} catch (Exception e) {
			log.error("", e);
		}
		return counts;
	}

}
