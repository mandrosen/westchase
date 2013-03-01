package com.westchase.persistence.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

import com.westchase.persistence.criteria.CitizenSearchCriteria;
import com.westchase.persistence.model.Citizen;

public class CitizenDAO extends BaseDAO<Citizen> {

	public CitizenDAO() {
		super();
	}
	
	private Query getQuery(CitizenSearchCriteria criteria, String select, String alias) {
		StringBuffer query = new StringBuffer(select);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		Citizen citizen = criteria.getSearchObject();
		if (citizen != null) {
			if (citizen.getId() != null) {
				query.append(" and ").append(alias).append(".id = :id ");
				paramMap.put("id", citizen.getId());
			}
			if (StringUtils.isNotBlank(citizen.getFirstName())) {
				query.append(" and " + alias + ".firstName like concat('%','").append(citizen.getFirstName()).append("','%')");
			}
			if (StringUtils.isNotBlank(citizen.getMiddleName())) {
				query.append(" and " + alias + ".middleName like concat('%','").append(citizen.getMiddleName()).append("','%')");
			}
			if (StringUtils.isNotBlank(citizen.getLastName())) {
				query.append(" and " + alias + ".lastName like concat('%','").append(citizen.getLastName()).append("','%')");
			}
			if (citizen.getTxDl() != null) {
				query.append(" and ").append(alias).append(".txDl = :txDl ");
				paramMap.put("txDl", citizen.getTxDl());
			}
		}
		Query q = getSession().createQuery(query.toString());
		if (paramMap != null && !paramMap.isEmpty()) {
			for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
				q.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return q;
	}
	
	public List<Citizen> findAll(CitizenSearchCriteria criteria) {
		List<Citizen> citizens = null;
		try {
			Query q = getQuery(criteria, "select c from Citizen c where 1 = 1 ", "c");
			citizens = prepareQuery(q, criteria).list();		
		} catch (Exception e) {
			log.error("", e);
		}		
		return citizens;
	}

	public long findAllCount(CitizenSearchCriteria criteria) {
		long count = 0;
		try {
			Long l = null;
			Query q = getQuery(criteria, "select count(c) from Citizen c where 1 = 1 ", "c");
			l = (Long) q.uniqueResult();	
			if (l != null) {
				count = l.longValue();
			}			
		} catch (Exception e) {
			log.error("", e);
		}
		return count;
	}

	public List<Citizen> findByActivityDetail(Long patrolActivityDetailId) {
		List<Citizen> citizens = null;
		String query = "select p.citizen from PatrolActivityDetailCitizen p where p.patrolActivityDetail.id = :activityDetailId order by p.citizen.firstName, p.citizen.lastName";
		try {
			citizens = getSession().createQuery(query).setParameter("activityDetailId", patrolActivityDetailId).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return citizens;
	}

	public List<Citizen> listNonSelected(Long patrolActivityDetailId) {
		List<Citizen> citizens = null;
		String query = "select c from Citizen c where c.id not in (select padc.citizen.id from PatrolActivityDetailCitizen padc where padc.patrolActivityDetail.id = :patrolActivityDetailId) order by c.firstName, c.lastName";
		try {
			citizens = getSession().createQuery(query).setParameter("patrolActivityDetailId", patrolActivityDetailId).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return citizens;
	}

}
