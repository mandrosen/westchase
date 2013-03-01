package com.westchase.utils.ejb;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.westchase.ejb.AuditService;
import com.westchase.ejb.CategoryService;
import com.westchase.ejb.CmuReportService;
import com.westchase.ejb.CmuService;
import com.westchase.ejb.CompanyService;
import com.westchase.ejb.EmailService;
import com.westchase.ejb.EmployeeService;
import com.westchase.ejb.HpdNewsService;
import com.westchase.ejb.MessageTodoService;
import com.westchase.ejb.PatrolService;
import com.westchase.ejb.PhoneBookRelationService;
import com.westchase.ejb.PhoneBookService;
import com.westchase.ejb.PropertyService;
import com.westchase.ejb.ReportService;

public class ServiceLocator {

	private static final Log log = LogFactory.getLog(ServiceLocator.class);

	private static ServiceLocator INSTANCE;
	
	private InitialContext context;
	
	private ServiceLocator() {
		super();
		try {
			context = new InitialContext();
		} catch (NamingException e) {
			log.error("", e);
		}
	}
	
	public Object lookupBean(final String name) {
		if (context == null) {
			return null;
		}
		try {
			return context.lookup(name);
		} catch (NamingException e) {
			log.error("", e);
		}
		return null;
	}
	
	public static final ServiceLocator instance() {
		if (INSTANCE == null) {
			INSTANCE = new ServiceLocator();
		}
		return INSTANCE;
	}
	
	public static AuditService lookupAuditService() {
		return (AuditService) instance().lookupBean("westchase/AuditServiceBean/local");
	}
	
	public static CategoryService lookupCategoryService() {
		return (CategoryService) instance().lookupBean("westchase/CategoryServiceBean/local");
	}
	
	public static CmuReportService lookupCmuReportService() {
		return (CmuReportService) instance().lookupBean("westchase/CmuReportServiceBean/local");
	}
	
	public static CmuService lookupCmuService() {
		return (CmuService) instance().lookupBean("westchase/CmuServiceBean/local");
	}
	
	public static CompanyService lookupCompanyService() {
		return (CompanyService) instance().lookupBean("westchase/CompanyServiceBean/local");
	}
	
	public static EmailService lookupEmailService() {
		return (EmailService) instance().lookupBean("westchase/EmailServiceBean/local");
	}
	
	public static EmployeeService lookupEmployeeService() {
		return (EmployeeService) instance().lookupBean("westchase/EmployeeServiceBean/local");
	}
	
	public static HpdNewsService lookupHpdNewsService() {
		return (HpdNewsService) instance().lookupBean("westchase/HpdNewsServiceBean/local");
	}
	
	public static MessageTodoService lookupMessageTodoService() {
		return (MessageTodoService) instance().lookupBean("westchase/MessageTodoServiceBean/local");
	}
	
	public static PhoneBookRelationService lookupPhoneBookRelationService() {
		return (PhoneBookRelationService) instance().lookupBean("westchase/PhoneBookRelationServiceBean/local");
	}
	
	public static PhoneBookService lookupPhoneBookService() {
		return (PhoneBookService) instance().lookupBean("westchase/PhoneBookServiceBean/local");
	}
	
	public static PropertyService lookupPropertyService() {
		return (PropertyService) instance().lookupBean("westchase/PropertyServiceBean/local");
	}
	
	public static ReportService lookupReportService() {
		return (ReportService) instance().lookupBean("westchase/ReportServiceBean/local");
	}
	
	public static PatrolService lookupPatrolService() {
		return (PatrolService) instance().lookupBean("westchase/PatrolServiceBean/local");
	}

}
