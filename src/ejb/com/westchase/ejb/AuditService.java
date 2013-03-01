package com.westchase.ejb;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.westchase.persistence.model.Citizen;
import com.westchase.persistence.model.CmuLease;
import com.westchase.persistence.model.CmuQuarter;
import com.westchase.persistence.model.Company;
import com.westchase.persistence.model.Employee;
import com.westchase.persistence.model.Message;
import com.westchase.persistence.model.Officer;
import com.westchase.persistence.model.PatrolActivity;
import com.westchase.persistence.model.PatrolActivityDetail;
import com.westchase.persistence.model.PhoneBook;
import com.westchase.persistence.model.Property;
import com.westchase.persistence.model.Todo;
import com.westchase.persistence.model.Wcuser;

/**
 * @author marc
 *
 */
@Local
public interface AuditService {

	void save(int employeeId, String object, Integer objectId, String desc);
	void save(int employeeId, String object, Long objectId, String desc);

	void save(int employeeId, Todo todo);

	void save(int employeeId, CmuLease cmuLease);

	void save(int employeeId, Company company);

	void save(int employeeId, PhoneBook phoneBook, List<String> categoryCodes, Map<Integer, String> properties);

	void save(int employeeId, Property property);

	void save(int employeeId, CmuQuarter quarter);

	void save(int employeeId, Message message);
	
	void save(int employeeId, Employee employee, Wcuser user);

	void save(int employeeId, PatrolActivity patrolActivity);

	void save(int employeeId, PatrolActivityDetail patrolActivityDetail);
	
	void save(int employeeId, Officer officer);
	
	void save(int employeeId, Citizen citizen);

	void delete(int employeeId, Class clazz, Integer id);

	void completeTodo(int employeeId, Integer todoId);

	void deleteCompany(Integer employeeId, Integer id);

	void deletePhoneBook(Integer employeeId, Integer id);
	
	void deletePatrolActivity(Integer employeeId, Long id);
	
	void deletePatrolActivityDetail(Integer employeeId, Long id);

}
