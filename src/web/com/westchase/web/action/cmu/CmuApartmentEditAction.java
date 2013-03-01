package com.westchase.web.action.cmu;

import java.util.Date;

import javax.naming.InitialContext;

import com.opensymphony.xwork2.Preparable;
import com.westchase.ejb.CmuService;
import com.westchase.persistence.model.CmuApartment;

/**
 * @author marc
 *
 */
public class CmuApartmentEditAction extends AbstractCmuAction implements Preparable {

	
	private CmuApartment cmuApartment;
	
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
            	cmuApartment = cmuServ.getCmuApartment(new Integer(getId().intValue()));
            	if (cmuApartment != null && cmuApartment.getVerified() != null) {
            		setVerified(true);
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
	    CmuApartment cmuApt = getCmuApartment();
		if (cmuApt != null) {
			if (isVerified() && cmuApt.getVerified() == null) {
				cmuApt.setVerified(new Date());
			}
	        if (cmuServ != null) {
	        	long cmuAptId = cmuServ.saveCmuApartment(cmuApt);
	        	setId(new Long(cmuAptId));
	        }
		}
		return execute();
	}

	public CmuApartment getCmuApartment() {
		return cmuApartment;
	}

	public void setCmuApartment(CmuApartment cmuApartment) {
		this.cmuApartment = cmuApartment;
	}

}
