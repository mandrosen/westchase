package com.westchase.web.action.cmu;

import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.Preparable;
import com.westchase.ejb.CmuService;
import com.westchase.persistence.dto.cmu.CmuOfficeRetailSvcDTO;
import com.westchase.persistence.model.CmuLease;
import com.westchase.persistence.model.CmuOfficeRetailSvc;

/**
 * @author marc
 *
 */
public class CmuOfficeRetailSvcEditAction extends AbstractCmuAction implements Preparable {

	
	private CmuOfficeRetailSvc cmuOfficeRetailSvc;
	private List<CmuLease> cmuLeaseList;
	private int businessTypeId;
	
	@Override
	public void prepare() throws Exception {	
	}
	
	public String execute() {
        try {
        	InitialContext ctx = new InitialContext();
        	cmuServ = (CmuService) ctx.lookup("westchase/CmuServiceBean/remote");
        } catch (Exception e) {
            log.error(e.getMessage()); 
        }     	
    	if (getId() != null) {	
            if (cmuServ != null) {
            	CmuOfficeRetailSvcDTO cmuOfficeRetailSvcDto = cmuServ.getCmuOfficeRetailSvc(new Integer(getId().intValue()));
            	if (cmuOfficeRetailSvcDto != null) {
            		setCmuOfficeRetailSvc(cmuOfficeRetailSvcDto.getCmuOfficeRetailSvc());            	
            		if (cmuOfficeRetailSvc != null && cmuOfficeRetailSvc.getVerified() != null) {
                		setVerified(true);
                	}
            		setCmuLeaseList(cmuOfficeRetailSvcDto.getLeases());
            	}
            	if (getBusinessTypeId() <= 0) {
            		
            		if (cmuOfficeRetailSvc != null && cmuOfficeRetailSvc.getProperty() != null) {
            			String businessType = cmuOfficeRetailSvc.getProperty().getBusinessType();
            			if (StringUtils.startsWithIgnoreCase(businessType, "Office")) {
            				setBusinessTypeId(1);
            			} else if (StringUtils.startsWithIgnoreCase(businessType, "Retail")) {
            				setBusinessTypeId(2);
            			} else if (StringUtils.startsWithIgnoreCase(businessType, "Service") || StringUtils.startsWithIgnoreCase(businessType, "Ind")) {
            				setBusinessTypeId(3);
            			}
            		}
            		
            	}
            }
    	}
    	return SUCCESS;
	}
	
	public String save() {        
		try {
	    	InitialContext ctx = new InitialContext();
	    	cmuServ = (CmuService) ctx.lookup("westchase/CmuServiceBean/remote");
	    } catch (Exception e) {
	        log.error(e.getMessage()); 
	    }
	    CmuOfficeRetailSvc cmuOrs = getCmuOfficeRetailSvc();
		if (cmuOrs != null) {
			if (isVerified() && cmuOrs.getVerified() == null) {
				cmuOrs.setVerified(new Date());
			}
	        if (cmuServ != null) {
	        	long cmuOrsId = cmuServ.saveCmuOfficeRetailSvc(cmuOrs);
	        	setId(new Long(cmuOrsId));
	        }
		}
		return execute();
	}

	public CmuOfficeRetailSvc getCmuOfficeRetailSvc() {
		return cmuOfficeRetailSvc;
	}

	public void setCmuOfficeRetailSvc(CmuOfficeRetailSvc cmuOfficeRetailSvc) {
		this.cmuOfficeRetailSvc = cmuOfficeRetailSvc;
	}

	public List<CmuLease> getCmuLeaseList() {
		return cmuLeaseList;
	}

	public void setCmuLeaseList(List<CmuLease> cmuLeaseList) {
		this.cmuLeaseList = cmuLeaseList;
	}

	public int getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(int businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

}
