package com.westchase.persistence.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.westchase.persistence.criteria.OfficerSearchCriteria;
import com.westchase.persistence.model.Officer;

public class OfficerDAO extends BaseDAO<Officer> {

	public OfficerDAO() {
		super();
	}
	
	public List<Officer> findAllOrdered() {
		List<Officer> officers = getSession().createCriteria(Officer.class).addOrder(Order.asc("lastName")).addOrder(Order.desc("firstName")).list();
		return officers;
	}
	
	public List<Officer> findAllActiveOrdered() {
		List<Officer> officers = getSession().createCriteria(Officer.class)
				.add(Restrictions.eq("active", true))
				.addOrder(Order.asc("lastName")).addOrder(Order.desc("firstName")).list();
		return officers;
	}

	public List<Officer> findOrdered(List<Integer> officerIdList) {
		if (!hasListValues(officerIdList)) {
			return findAllOrdered();
		}
		List<Officer> officers = getSession()
				.createCriteria(Officer.class)
				.add(Restrictions.in("id", officerIdList))
				.addOrder(Order.asc("lastName"))
				.addOrder(Order.desc("firstName")).list();
		return officers;
	}
	
	private Query getQuery(OfficerSearchCriteria criteria, String select, String alias) {
		StringBuffer query = new StringBuffer(select);
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		Officer officer = criteria.getSearchObject();
		if (officer != null) {
			if (officer.getId() != null) {
				query.append(" and ").append(alias).append(".id = :id ");
				paramMap.put("id", officer.getId());
			}
			if (StringUtils.isNotBlank(officer.getFirstName())) {
				query.append(" and " + alias + ".firstName like concat('%','").append(officer.getFirstName()).append("','%')");
			}
			if (StringUtils.isNotBlank(officer.getLastName())) {
				query.append(" and " + alias + ".lastName like concat('%','").append(officer.getLastName()).append("','%')");
			}
			if (officer.getBadge() != null) {
				query.append(" and ").append(alias).append(".badge = :badge ");
				paramMap.put("badge", officer.getBadge());
			}
		}
		if (StringUtils.isNotBlank(criteria.getOrderCol())) {
			query.append(" order by ").append(alias).append(".").append(criteria.getOrderCol()).append(" ").append(criteria.getOrderDir());
		}
		Query q = getSession().createQuery(query.toString());
		if (paramMap != null && !paramMap.isEmpty()) {
			for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
				q.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return q;
	}
	
	public List<Officer> findAll(OfficerSearchCriteria criteria) {
		List<Officer> officers = null;
		try {
			Query q = getQuery(criteria, "select o from Officer o where 1 = 1 ", "o");
			officers = prepareQuery(q, criteria).list();		
		} catch (Exception e) {
			log.error("", e);
		}		
		return officers;
	}

	public long findAllCount(OfficerSearchCriteria criteria) {
		long count = 0;
		try {
			Long l = null;
			Query q = getQuery(criteria, "select count(o) from Officer o where 1 = 1 ", "o");
			l = (Long) q.uniqueResult();	
			if (l != null) {
				count = l.longValue();
			}			
		} catch (Exception e) {
			log.error("", e);
		}
		return count;
	}

}
