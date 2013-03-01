package com.westchase.web.servlet;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.westchase.persistence.model.Employee;

/**
 * @author marc
 *
 */
public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		Employee emp = (Employee) event.getSession().getAttribute(SecurityFilter.LOGGED_IN_EMPLOYEE);
		if (emp != null) {
			HttpSession session = event.getSession();
			if (session != null) {
				session.invalidate();
			}
		}
	}

}
