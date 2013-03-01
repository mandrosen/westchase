package com.westchase.ejb;

import java.util.List;

import javax.ejb.Local;

import com.westchase.persistence.model.Employee;
import com.westchase.persistence.model.Wcuser;

/**
 * @author marc
 *
 */
@Local
public interface EmployeeService {
	Employee loginEmployee(String username);

	List<Employee> findAll();
	
	Employee get(Integer id);
	
	void saveOrUpdate(Integer employeeId, Employee emp, Wcuser user) throws Exception;
}
