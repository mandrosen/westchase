package com.westchase.persistence.dao;

import java.util.List;

import com.westchase.persistence.model.Employee;
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
	
	/**
	 * @deprecated use {@link #getByUsername(String)}
	 */
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
	
	public Wcuser getByUsername(String username) {
		Wcuser user = null;
		String query = "select u from Wcuser u where u.username = :username";
		try {
			user = (Wcuser) getSession().createQuery(query).setParameter("username", username).uniqueResult();
		} catch (Exception e) {
			log.error("", e);
		}
		return user;
	}
	
	public Employee getEmployeeByUsername(String username) {
		Employee emp = null;
		String query = "select u.employee from Wcuser u where u.username = :username";
		try {
			emp = (Employee) getSession().createQuery(query).setParameter("username", username).uniqueResult();
		} catch (Exception e) {
			log.error("", e);
		}
		return emp;
	}
	
	public Integer getEmployeeIdByUsername(String username) {
		Integer employeeId = null;
		String query = "select u.employee.id from Wcuser u where u.username = :username";
		try {
			employeeId = (Integer) getSession().createQuery(query).setParameter("username", username).uniqueResult();
		} catch (Exception e) {
			log.error("", e);
		}
		return employeeId;
	}

}
