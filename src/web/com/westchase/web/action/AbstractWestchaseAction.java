package com.westchase.web.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.westchase.persistence.model.Employee;
import com.westchase.web.servlet.SecurityFilter;

/**
 * @author marc
 *
 */
public class AbstractWestchaseAction extends ActionSupport implements ServletRequestAware {

	protected final Log log = LogFactory.getLog(getClass());
	
	protected static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	private HttpServletRequest request;

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	protected HttpServletRequest getRequest() {
		return this.request;
	}
	
	protected Employee getEmployee() {
		if (request != null) {
			HttpSession session = request.getSession();
			if (session != null) {
				return (Employee) session.getAttribute(SecurityFilter.LOGGED_IN_EMPLOYEE);
			}
		}
		return null;
	}
}
