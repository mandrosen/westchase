package com.westchase.jobs;

import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.varia.scheduler.Schedulable;

import com.westchase.ejb.CompanyService;
import com.westchase.persistence.model.Company;
import com.westchase.xml.MapXmlHelper;

/**
 * @author marc
 *
 */
public class MapXmlJob implements Schedulable {
	
	private final Log log = LogFactory.getLog(getClass());
	
//	private PropertyService propServ;
	private CompanyService compServ;
	
//	private int propertyTypeId;
	private int companyTypeId;
	
	public MapXmlJob() {
		super();
	}
	
//	public MapXmlJob(int propertyTypeId) {
//		this();
//		this.propertyTypeId = propertyTypeId;
//	}
	
	public MapXmlJob(int companyTypeId) {
		this();
		this.companyTypeId = companyTypeId;
	}
	
	@Override
	public void perform(Date timeOfCall, long remainingRepititions) {
		try {
			InitialContext ctx = new InitialContext();
//			propServ = (PropertyService) ctx.lookup("westchase/PropertyServiceBean/local");
            compServ = (CompanyService) ctx.lookup("westchase/CompanyServiceBean/local");
		} catch (Exception e) {
			log.error("", e);
		}
//		if (propServ != null) {
//			List<Property> properties = propServ.findAllWithType(propertyTypeId);
//			String xml = MapXmlHelper.propertiesToMapXml(properties);
//			
//			// TODO: send xml to marketingalliance
//		}
		if (compServ != null) {
			List<Company> companies = compServ.findAllWithType(companyTypeId);
			String xml = MapXmlHelper.companiesToMapXml(companies);
			
			// TODO: send xml to marketingalliance
		}
	}

}
