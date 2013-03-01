package com.westchase.web.action.cmu;

import java.util.Date;

import javax.naming.InitialContext;

import com.opensymphony.xwork2.Preparable;
import com.westchase.ejb.CmuService;
import com.westchase.persistence.model.CmuDevsite;

/**
 * @author marc
 *
 */
public class CmuDevsiteEditAction extends AbstractCmuAction implements Preparable {

	
	private CmuDevsite cmuDevsite;
	
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
            	cmuDevsite = cmuServ.getCmuDevsite(new Integer(getId().intValue()));
            	if (cmuDevsite != null && cmuDevsite.getVerified() != null) {
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
	    CmuDevsite cmuDevsite = getCmuDevsite();
		if (cmuDevsite != null) {
			if (isVerified() && cmuDevsite.getVerified() == null) {
				cmuDevsite.setVerified(new Date());
			}
	        if (cmuServ != null) {
	        	long cmuDevsiteId = cmuServ.saveCmuDevsite(cmuDevsite);
	        	setId(new Long(cmuDevsiteId));
	        }
		}
		return execute();
	}

	public CmuDevsite getCmuDevsite() {
		return cmuDevsite;
	}

	public void setCmuDevsite(CmuDevsite cmuDevsite) {
		this.cmuDevsite = cmuDevsite;
	}

}
