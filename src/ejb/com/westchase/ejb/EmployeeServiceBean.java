package com.westchase.ejb;

import java.util.Date;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import org.apache.commons.lang.StringUtils;
import org.jboss.ejb3.annotation.SecurityDomain;

import com.westchase.persistence.dao.EmployeeDAO;
import com.westchase.persistence.dao.RoleDAO;
import com.westchase.persistence.dao.UserRoleDAO;
import com.westchase.persistence.dao.WcuserDAO;
import com.westchase.persistence.model.Employee;
import com.westchase.persistence.model.Role;
import com.westchase.persistence.model.UserRole;
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
	
	private boolean checkUniqueUsername(Wcuser user) {
		WcuserDAO dao = new WcuserDAO();
		List<Wcuser> otherUsers = dao.listByUsername(user.getUsername());
		if (user.getId() == null) {
			if (otherUsers != null && !otherUsers.isEmpty()) {
				return false;
			}
		} else {
			if (otherUsers != null && !otherUsers.isEmpty()) {
				for (Wcuser otherUser : otherUsers) {
					if (!otherUser.getId().equals(user.getId())) {
						return false;
					}
				}
			}
		}
		for (Wcuser otherUser : otherUsers) {
			dao.getSession().evict(otherUser);
		}
		return true;
	}

	@Override
	@RolesAllowed({"admin", "developer"})
	public String saveOrUpdate(Integer employeeId, Employee emp, Wcuser user, List<Integer> roleIdList) throws Exception {
		if (emp == null || user == null || roleIdList == null) {
			return "Missing required data.";
		}
		if (!checkUniqueUsername(user)) {
			return "Non-unique username";
		}
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
		
		saveUserRoles(user, roleIdList);

		if (audServ != null) {
			audServ.save(employeeId, emp, user);
		}
		return null;
	}
	
	private void saveUserRoles(Wcuser user, List<Integer> roleIdList) {
		UserRoleDAO dao = new UserRoleDAO();
		dao.deleteAllForUser(user.getId());
		
		for (Integer roleId : roleIdList) {
			UserRole userRole = new UserRole();
			userRole.setWcuser(user);
			userRole.setRole(new Role(roleId));
			dao.save(userRole);
		}
	}

	@Override
	public List<Role> listRoles() {
		RoleDAO dao = new RoleDAO();
		return dao.findAll();
	}

	@Override
	@RolesAllowed({"admin", "developer"})
	public List<Integer> listRoleIdsForUser(Integer userId) {
		UserRoleDAO dao = new UserRoleDAO();
		return dao.listRoleIdsForUser(userId);
	}

}
