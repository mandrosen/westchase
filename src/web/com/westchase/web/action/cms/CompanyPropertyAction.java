package com.westchase.web.action.cms;

import java.util.List;

import javax.naming.InitialContext;

import com.westchase.ejb.AuditService;
import com.westchase.ejb.CompanyService;
import com.westchase.ejb.PropertyService;
import com.westchase.persistence.model.Company;
import com.westchase.persistence.model.CompanyMapno;
import com.westchase.persistence.model.Employee;
import com.westchase.persistence.model.Property;
import com.westchase.web.action.AbstractWestchaseAction;

/**
 * @author marc
 *
 */
public class CompanyPropertyAction extends AbstractWestchaseAction {
	
    private PropertyService propServ;
    private CompanyService compServ;
    private AuditService audServ;
    private InitialContext ctx = null;   
    
    private Company currentCompany;
    private Integer currentCompanyId;
    private Integer companyPropertyId;
    private boolean primaryProp;
    
    private Integer propertyId;
    
    private List<Property> availableProperties;


	public String selectNewInit() {
		return SUCCESS;
	}

	public String selectInit() {
        try {
            ctx = new InitialContext();
            propServ = (PropertyService) ctx.lookup("westchase/PropertyServiceBean/local");
            compServ = (CompanyService) ctx.lookup("westchase/CompanyServiceBean/local");
        } catch (Exception e) {
            log.error(e.getMessage()); 
        }     	
        
        if (propServ != null) {
        	availableProperties = propServ.findAll();
        }		
        if (getCurrentCompanyId() != null && compServ != null) {
        	currentCompany = compServ.get(getCurrentCompanyId());
        }
        if (getCompanyPropertyId() != null) {
        	CompanyMapno cm = compServ.getCompanyProperty(getCompanyPropertyId());
        	primaryProp = cm.isPrimary();
        	propertyId = cm.getProperty().getId();        	
        }
		return SUCCESS;
	}
	
	public String save() {
        try {
            ctx = new InitialContext();
            compServ = (CompanyService) ctx.lookup("westchase/CompanyServiceBean/local");
            audServ = (AuditService) ctx.lookup("westchase/AuditServiceBean/local");
        } catch (Exception e) {
            log.error(e.getMessage()); 
        }     	
        
        if (compServ != null) {
        	compServ.saveCompanyProperty(getCompanyPropertyId(), getCurrentCompanyId(), getPropertyId(), isPrimaryProp());
        	if (audServ != null) {
        		// TODO:
        	}
        }				
		return SUCCESS;
	}
	
	public String remove() {
        try {
            ctx = new InitialContext();
            compServ = (CompanyService) ctx.lookup("westchase/CompanyServiceBean/local");
            audServ = (AuditService) ctx.lookup("westchase/AuditServiceBean/local");
        } catch (Exception e) {
            log.error(e.getMessage()); 
        }     
        Employee emp = getEmployee();
        if (emp != null && emp.getId() != null) {
	        if (compServ != null && getCompanyPropertyId() != null) {
	        	compServ.removeCompanyProperty(getCompanyPropertyId());
	        	if (audServ != null) {
        			int employeeId = emp.getId().intValue();
	        		audServ.delete(employeeId, CompanyMapno.class, getCompanyPropertyId());
	        	}        	
	        }
        }
		return SUCCESS;
	}
	
	public String addNewInit() {
		return SUCCESS;
	}

	
	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}
	
	public Integer getCurrentCompanyId() {
		return currentCompanyId;
	}

	public void setCurrentCompanyId(Integer currentCompanyId) {
		this.currentCompanyId = currentCompanyId;
	}

	public Integer getCompanyPropertyId() {
		return companyPropertyId;
	}

	public void setCompanyPropertyId(Integer companyPropertyId) {
		this.companyPropertyId = companyPropertyId;
	}

	public List<Property> getAvailableProperties() {
		return availableProperties;
	}

	public void setAvailableProperties(List<Property> availableProperties) {
		this.availableProperties = availableProperties;
	}

	public Company getCurrentCompany() {
		return currentCompany;
	}

	public void setCurrentCompany(Company currentCompany) {
		this.currentCompany = currentCompany;
	}

	public boolean isPrimaryProp() {
		return primaryProp;
	}

	public void setPrimaryProp(boolean primaryProp) {
		this.primaryProp = primaryProp;
	}
	
}
