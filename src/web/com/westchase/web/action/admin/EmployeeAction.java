package com.westchase.web.action.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.westchase.ejb.EmployeeService;
import com.westchase.persistence.model.Employee;
import com.westchase.persistence.model.Role;
import com.westchase.persistence.model.Wcuser;
import com.westchase.utils.ejb.ServiceLocator;

/**
 * @author marc
 *
 */
public class EmployeeAction extends AbstractAdminAction {

	private List<Employee> employees;
	
	private Integer employeeId;
	private Employee currentEmployee;
	private Wcuser currentUser;
	private String confirmPassword;
	
	private List<Role> roleList;
	private List<Integer> roleIdList;
	
	private EmployeeService empServ;

	public String execute() throws Exception {
        return super.execute();
    }
	public String list() {
        empServ = ServiceLocator.lookupEmployeeService();
    	
        if (empServ != null) {
        	employees = empServ.findAll();
        }
        
		return SUCCESS;
	}
	
	private void setRoleIdList() {        	
        empServ = ServiceLocator.lookupEmployeeService();
        
		if (currentUser != null && currentUser.getId() != null) {
			roleIdList = empServ.listRoleIdsForUser(currentUser.getId());
		}
		if (roleIdList == null) {
			roleIdList = new ArrayList<Integer>();
		}
	}
	
	private String validateUserEmployee() {
		StringBuffer error = new StringBuffer();
    	Wcuser user = getCurrentUser();
    	Employee employee = getCurrentEmployee();
    	if (StringUtils.isBlank(user.getUsername())) {
    		error.append("Username is required.  ");
    	}
    	if (StringUtils.isBlank(employee.getFirstName())) {
    		error.append("First name is required.  ");
    	}
    	if (StringUtils.isBlank(employee.getLastName())) {
    		error.append("Last name is required.  ");
    	}
    	if (StringUtils.isBlank(employee.getEmail())) {
    		error.append("Email is required.  ");
    	}
    	if (user.getPassword() != null && !user.getPassword().equals(getConfirmPassword())) {
    		error.append("Password and Confirm Password do not match.  ");
    	}
    	if (!user.isDisabled() && (roleIdList == null || roleIdList.isEmpty())) {
    		error.append("You must select at least one Role for active accounts.  ");
    	}
    	return error.toString();
	}
	
   public String save() throws Exception {
        if (getCurrentEmployee() != null && getCurrentUser() != null) {
        	
        	String error = validateUserEmployee();
        	if (StringUtils.isNotBlank(error)) {
        		addActionError(error);
    			return INPUT;
        	}
        	
	        empServ = ServiceLocator.lookupEmployeeService();

            Employee emp = getEmployee();
            if (emp != null && emp.getId() != null) {
            	error = empServ.saveOrUpdate(emp.getId(), getCurrentEmployee(), getCurrentUser(), roleIdList);
            	if (StringUtils.isNotBlank(error)) {
            		addActionError(error);
        			return INPUT;
            	}
            	
            	getRequest().getSession(true).setAttribute("WCActionMessage", "Saved employee #" + getCurrentEmployee().getId());
            }
        }
        return SUCCESS;
    }

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee getCurrentEmployee() {
		return currentEmployee;
	}

	public void setCurrentEmployee(Employee currentEmployee) {
		this.currentEmployee = currentEmployee;
	}

	@Override
	public void prepare() throws Exception {
        empServ = ServiceLocator.lookupEmployeeService();
		
        roleList = empServ.listRoles();
        
		if (getEmployeeId() != null)  {
	        Employee emp = empServ.get(getEmployeeId());
	        setCurrentEmployee(emp);
	        if (emp != null) {
	        	Set<Wcuser> users = emp.getWcusers();
	        	if (users != null && !users.isEmpty()) {
	        		Wcuser user = users.iterator().next();
	        		if (user != null) {
	        			user.setPassword(null);
		        		setCurrentUser(user);
	        		}
	        	}
	        }
		} else {
			setCurrentEmployee(new Employee());
			setCurrentUser(new Wcuser());
		}
		setRoleIdList();
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public Wcuser getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(Wcuser currentUser) {
		this.currentUser = currentUser;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	public List<Integer> getRoleIdList() {
		return roleIdList;
	}
	public void setRoleIdList(List<Integer> roleIdList) {
		this.roleIdList = roleIdList;
	}
	
}
