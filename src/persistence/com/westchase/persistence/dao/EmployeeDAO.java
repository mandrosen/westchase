package com.westchase.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import com.westchase.persistence.model.Employee;

/**
 * @author marc
 *
 */
public class EmployeeDAO extends BaseDAO<Employee> {

	public Employee findByUsername(String username) {
		Employee emp = null;
		String query = "select u.employee from Wcuser u where u.username = :username";
		try {
			emp = (Employee) getSession().createQuery(query).setString("username", username).setMaxResults(1).uniqueResult();
		} catch (Exception e) {
			log.error("", e);
		}
		return emp;
	}

	public List<Employee> listVisible(Employee emp) {
		// TODO return all for admins
		
		List<Employee> visible = new ArrayList<Employee>();
		visible.add(emp);
		return visible;
	}

}
