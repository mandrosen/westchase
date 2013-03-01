package com.westchase.web.action;

import org.apache.commons.lang.StringUtils;

import com.westchase.persistence.model.Employee;


/**
 * @author marc
 *
 */
public class ErrorAction extends AbstractWestchaseAction {
	
	private final static String HTTP404 = "404";
	
	private String code;
	
	private Exception exception;
	private String exceptionStack;
	
	public String execute() {
		Employee emp = getEmployee();
		String empName = "";
		if (emp != null && StringUtils.isNotBlank(emp.getFirstName())) {
			empName = emp.getFirstName() + " " + emp.getLastName();
		}
		
		if (HTTP404.equals(this.code)) {
			log.error(empName + " got 404 error " + getRequest());
		} else {
			if (exception != null) {
				log.error(empName + "\n" + exception + "\n" + getExceptionStack() + "\n" + getRequest());
			}
		}
		
		return SUCCESS;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public String getExceptionStack() {
		return exceptionStack;
	}

	public void setExceptionStack(String exceptionStack) {
		this.exceptionStack = exceptionStack;
	}
	
}
