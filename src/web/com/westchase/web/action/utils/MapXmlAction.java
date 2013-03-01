package com.westchase.web.action.utils;

import java.util.List;

import javax.naming.InitialContext;

import com.westchase.ejb.CompanyService;
import com.westchase.persistence.model.Company;
import com.westchase.persistence.model.CompanyType;
import com.westchase.web.action.AbstractWestchaseAction;
import com.westchase.xml.MapXmlHelper;

/**
 * @author marc
 *
 */
public class MapXmlAction extends AbstractWestchaseAction {

//	private PropertyService propServ;
    private CompanyService compServ;
	
//	private List<PropertyType> availablePropertyTypes;
	private List<CompanyType> availableCompanyTypes;
	
//	private int propertyTypeId;
	private int companyTypeId;
	
	@Override
	public String execute() throws Exception {
		try {
			InitialContext ctx = new InitialContext();
//			propServ = (PropertyService) ctx.lookup("westchase/PropertyServiceBean/local");
            compServ = (CompanyService) ctx.lookup("westchase/CompanyServiceBean/local");
		} catch (Exception e) {
			log.error("", e);
		}
//		if (propServ != null) {
//
//	    	availablePropertyTypes = propServ.listPropertyTypes();
//	    	// ognl won't let me use -1, I'll try another number large number
//	    	availablePropertyTypes.add(0, new PropertyType(-1, "All"));
//	    	
//			
//	    	if (this.propertyTypeId != 0) {
//	    		List<Property> properties = propServ.findAllWithType(propertyTypeId);
//				String xml = MapXmlHelper.propertiesToMapXml(properties);
//			
//				getRequest().setAttribute("xml", xml);
//	    	}
//		}
		if (compServ != null) {

			availableCompanyTypes = compServ.listCompanyTypes();
	    	// ognl won't let me use -1, I'll try another number large number
			availableCompanyTypes.add(0, new CompanyType(-1, "All"));
	    	
			
	    	if (this.companyTypeId != 0) {
	    		List<Company> companies = compServ.findAllWithType(companyTypeId);
				String xml = MapXmlHelper.companiesToMapXml(companies);
			
				getRequest().setAttribute("xml", xml);
	    	}
		}
		return SUCCESS;
	}

//	public int getPropertyTypeId() {
//		return propertyTypeId;
//	}
//
//	public void setPropertyTypeId(int propertyTypeId) {
//		this.propertyTypeId = propertyTypeId;
//	}
//
//	public List<PropertyType> getAvailablePropertyTypes() {
//		return availablePropertyTypes;
//	}
//
//	public void setAvailablePropertyTypes(List<PropertyType> availablePropertyTypes) {
//		this.availablePropertyTypes = availablePropertyTypes;
//	}

	public List<CompanyType> getAvailableCompanyTypes() {
		return availableCompanyTypes;
	}

	public void setAvailableCompanyTypes(List<CompanyType> availableCompanyTypes) {
		this.availableCompanyTypes = availableCompanyTypes;
	}

	public int getCompanyTypeId() {
		return companyTypeId;
	}

	public void setCompanyTypeId(int companyTypeId) {
		this.companyTypeId = companyTypeId;
	}

}
