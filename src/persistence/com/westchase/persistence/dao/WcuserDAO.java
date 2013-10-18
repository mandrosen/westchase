package com.westchase.persistence.dao;

import java.util.List;

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
	
	public List<Wcuser> listByUsername(String username) {
		List<Wcuser> userList = null;
		String query = "select u from Wcuser u where u.username = :username";
		try {
			userList = getSession().createQuery(query).setParameter("username", username).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return userList;
	}

}
