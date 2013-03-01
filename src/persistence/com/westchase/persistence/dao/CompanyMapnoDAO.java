package com.westchase.persistence.dao;

import com.westchase.persistence.model.CompanyMapno;

/**
 * @author marc
 *
 */
public class CompanyMapnoDAO extends BaseDAO<CompanyMapno> {

	public boolean isPrimary(Integer id) {
		boolean primary = false;
		String query = "select cm.primary from CompanyMapno cm where cm.id = :cmid";
		try {
			Boolean res = (Boolean) getSession().createQuery(query).setParameter("cmid", id).setMaxResults(1).uniqueResult();
			if (res != null) {
				primary = res.booleanValue();
			}
		} catch (Exception e) {
			log.error("", e);
		}		
		return primary;
	}

}
