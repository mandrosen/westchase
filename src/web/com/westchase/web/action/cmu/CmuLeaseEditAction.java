package com.westchase.web.action.cmu;

import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;

import com.opensymphony.xwork2.Preparable;
import com.westchase.ejb.CmuService;
import com.westchase.persistence.dto.cmu.CmuOfficeRetailSvcDTO;
import com.westchase.persistence.model.CmuLease;
import com.westchase.persistence.model.CmuOfficeRetailSvc;
import com.westchase.persistence.model.CmuTransactionType;

/**
 * @author marc
 *
 */
public class CmuLeaseEditAction extends AbstractCmuAction implements Preparable {
	
	private CmuLease cmuLease;
	private List<CmuTransactionType> cmuTransactionTypeList;
	
	private Integer officeRetailSvcId;
	
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
        if (cmuServ != null) {
        	cmuTransactionTypeList = cmuServ.listTransactionTypes();
	    	if (getId() != null) {	
	        	cmuLease = cmuServ.getCmuLease(new Integer(getId().intValue()));
	        	if (cmuLease != null && cmuLease.getVerified() != null) {
	        		setVerified(true);
	        	}
	    	} else if (getOfficeRetailSvcId() != null) {
	    		cmuLease = new CmuLease();
	    		CmuOfficeRetailSvcDTO dto = cmuServ.getCmuOfficeRetailSvc(getOfficeRetailSvcId());
	    		if (dto != null && dto.getCmuOfficeRetailSvc() != null) {
	    			CmuOfficeRetailSvc cmuOfficeRetailSvc = dto.getCmuOfficeRetailSvc();
	    			cmuLease.setCmuQuarter(cmuOfficeRetailSvc.getCmuQuarter());
	    			cmuLease.setProperty(cmuOfficeRetailSvc.getProperty());
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
	    CmuLease cmuLease = getCmuLease();
		if (cmuLease != null) {
			if (isVerified() && cmuLease.getVerified() == null) {
				cmuLease.setVerified(new Date());
			}
	        if (cmuServ != null) {
	        	long cmuLeaseId = cmuServ.saveCmuLease(cmuLease);
	        	setId(new Long(cmuLeaseId));
	        }
		}
		return execute();
	}

	public CmuLease getCmuLease() {
		return cmuLease;
	}

	public void setCmuLease(CmuLease cmuLease) {
		this.cmuLease = cmuLease;
	}

	public List<CmuTransactionType> getCmuTransactionTypeList() {
		return cmuTransactionTypeList;
	}

	public void setCmuTransactionTypeList(List<CmuTransactionType> cmuTransactionTypeList) {
		this.cmuTransactionTypeList = cmuTransactionTypeList;
	}

	public Integer getOfficeRetailSvcId() {
		return officeRetailSvcId;
	}

	public void setOfficeRetailSvcId(Integer officeRetailSvcId) {
		this.officeRetailSvcId = officeRetailSvcId;
	}
}
