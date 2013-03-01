package com.westchase.web.dwr;

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.westchase.ejb.CompanyService;
import com.westchase.ejb.PhoneBookService;
import com.westchase.persistence.dto.cms.PhoneBookPropertyDTO;
import com.westchase.persistence.model.Company;

/**
 * @author marc
 *
 */
public class CMSUtilities {
	
	private static Log log = LogFactory.getLog(CMSUtilities.class);

	public String getCompanyName(Integer companyId) {
		String compName = "";
		CompanyService compServ = null;
		try {
			InitialContext ctx = new InitialContext();
			compServ = (CompanyService) ctx.lookup("westchase/CompanyServiceBean/local");	
		} catch (Exception e) {
			log.error("", e);
		}
		if (compServ != null) {
			Company c = compServ.get(companyId);
			if (c != null) {
				compName = c.getCompany();
			}
		}
		return compName;
	}
	
	
	public List<PhoneBookPropertyDTO> getProperties(Integer phoneBookId, Integer companyId) {
		List<PhoneBookPropertyDTO> props = null;
		PhoneBookService pbServ = null;
		try {
			InitialContext ctx = new InitialContext();
			pbServ = (PhoneBookService) ctx.lookup("westchase/PhoneBookServiceBean/local");	
		} catch (Exception e) {
			log.error("", e);
		}
		if (pbServ != null) {
			props = pbServ.findPhoneBookProperties(phoneBookId, companyId);
		}
		return props;
	}
	
}
