package com.westchase.web.servlet;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.westchase.ejb.EmployeeService;
import com.westchase.persistence.model.Employee;

/**
 * @author marc
 * 
 */
public class SecurityFilter implements Filter {
//	private final static String SECURITY_FILTER = "SECURITY_FILTER";
	public final static String LOGGED_IN_EMPLOYEE = "LOGGED_IN_EMPLOYEE";
	public final static String LOGIN_ERROR = "LOGIN_ERROR";
	private final static String HOME_PAGE = "/westchase/home.action";
	private final static String LOGIN_PAGE = "/WEB-INF/login.jsp";

	private static Log log = LogFactory.getLog(SecurityFilter.class);

	@SuppressWarnings("unused")
	private FilterConfig config;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		if (httpRequest.getSession(true).getAttribute(LOGGED_IN_EMPLOYEE) == null) {
			if (httpRequest.getUserPrincipal() != null) {
				try {
					InitialContext ctx = new InitialContext();
					EmployeeService empServ = (EmployeeService) ctx.lookup("westchase/EmployeeServiceBean/local");
					Employee emp = empServ.loginEmployee(httpRequest.getUserPrincipal().getName());
					httpRequest.getSession(true).setAttribute(LOGGED_IN_EMPLOYEE, emp);
					httpResponse.sendRedirect(HOME_PAGE);
				} catch (Exception e) {
					log.error("", e);
				}
			} else {
				httpRequest.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	@Override
	public void destroy() {
	}

}
