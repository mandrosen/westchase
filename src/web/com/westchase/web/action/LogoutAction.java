package com.westchase.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author marc
 *
 */
public class LogoutAction extends ActionSupport implements ServletRequestAware {
	
	private HttpServletRequest request;
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public String execute() throws Exception {
		HttpSession session = request.getSession();
		if (session != null) {
			session.invalidate();
		}
		return SUCCESS;
	}
	
	

}
