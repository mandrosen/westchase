package com.westchase.web.action.admin;

import java.util.List;
import java.util.Set;

import javax.naming.InitialContext;

import com.westchase.ejb.EmployeeService;
import com.westchase.persistence.model.Employee;
import com.westchase.persistence.model.Wcuser;

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
	
	private EmployeeService empServ;

	public String execute() throws Exception {
        return super.execute();
    }
	public String list() {
        try {
        	InitialContext ctx = new InitialContext();
        	empServ = (EmployeeService) ctx.lookup("westchase/EmployeeServiceBean/local");
        } catch (Exception e) {
            log.error(e.getMessage()); 
        }        	
    	
        if (empServ != null) {
        	employees = empServ.findAll();
        }
        
		return SUCCESS;
	}
	
	   public String save() throws Exception {
	        if (getCurrentEmployee() != null && getCurrentUser() != null) {

	        	Wcuser user = getCurrentUser();
	        	if (user.getPassword() != null && !user.getPassword().equals(getConfirmPassword())) {
	        		addActionError("Password and Confirm Password do not match");
	    			return INPUT;
	        	}
	        	
	        	if (user.getId() == null || user.getId().intValue() <= 0) {
	        		addActionError("Password is required for new accounts");
	    			return INPUT;
	        	}
	        	
	            try {
	            	InitialContext ctx = new InitialContext();
	                empServ = (EmployeeService) ctx.lookup("westchase/EmployeeServiceBean/local");
	            } catch (Exception e) {
	                log.error(e.getMessage()); 
	            }        	

	            Employee emp = getEmployee();
	            if (emp != null && emp.getId() != null) {
	            	empServ.saveOrUpdate(emp.getId(), getCurrentEmployee(), getCurrentUser());
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
		if (getEmployeeId() != null)  {
	        try {
	        	InitialContext ctx = new InitialContext();
	        	empServ = (EmployeeService) ctx.lookup("westchase/EmployeeServiceBean/local");
	        } catch (Exception e) {
	            log.error(e.getMessage()); 
	        }
			
	        Employee emp = empServ.get(getEmployeeId());
	        setCurrentEmployee(emp);
	        if (emp != null) {
	        	Set<Wcuser> users = emp.getWcusers();
	        	if (users != null && !users.isEmpty()) {
	        		Wcuser user = users.iterator().next();
	        		if (user != null) {
	        			user.setPassword(null);
	        		}
	        		setCurrentUser(user);
	        	}
	        }
		}
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
	
}
