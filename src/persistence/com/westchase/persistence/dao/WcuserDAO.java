package com.westchase.persistence.dao;

import com.westchase.persistence.model.Wcuser;

/**
 * @author marc
 *
 */
public class WcuserDAO extends BaseDAO<Wcuser> {

	public String getPassword(Integer id) {
		String pw = null;
		String query = "select u.password from Wcuser u where u.id = :uid";
		try {
			pw = (String) getSession().createQuery(query).setParameter("uid", id).setMaxResults(1).uniqueResult();
		} catch (Exception e) {
			log.error("", e);
		}
		return pw;
	}

}
