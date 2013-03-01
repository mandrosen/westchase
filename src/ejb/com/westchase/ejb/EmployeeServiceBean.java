package com.westchase.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import org.apache.commons.lang.StringUtils;
import org.jboss.ejb3.annotation.SecurityDomain;

import com.westchase.persistence.dao.EmployeeDAO;
import com.westchase.persistence.dao.WcuserDAO;
import com.westchase.persistence.model.Employee;
import com.westchase.persistence.model.Wcuser;
import com.westchase.utils.encryption.EncryptionUtils;

/**
 * @author marc
 *
 */
@Stateless
@SecurityDomain("WestchaseRealm")
@Local(EmployeeService.class)
public class EmployeeServiceBean implements EmployeeService {
	
	@EJB
	private AuditService audServ;

	@Override
	public Employee loginEmployee(String username) {
		EmployeeDAO dao = new EmployeeDAO();
		Employee emp = dao.findByUsername(username);
		emp.setLastLogin(new Date());
		return emp;
	}

	@Override
	public List<Employee> findAll() {
		EmployeeDAO dao = new EmployeeDAO();
		return dao.findAll();
	}

	@Override
	public Employee get(Integer id) {
		EmployeeDAO dao = new EmployeeDAO();
		return dao.get(id);
	}

	@Override
	public void saveOrUpdate(Integer employeeId, Employee emp, Wcuser user) throws Exception {
		WcuserDAO userDao = new WcuserDAO();
		EmployeeDAO dao = new EmployeeDAO();
		dao.saveOrUpdate(emp);
		if (user.getEmployee() == null) {
			user.setEmployee(emp);
		}
		
		if (user != null && user.getId() != null && StringUtils.isBlank(user.getPassword())) {
			user.setPassword(userDao.getPassword(user.getId()));
		} else {
			user.setPassword(EncryptionUtils.encryptString(user.getPassword()));
		}
		
		userDao.saveOrUpdate(user);

		if (audServ != null) {
			audServ.save(employeeId, emp, user);
		}
	}

}
