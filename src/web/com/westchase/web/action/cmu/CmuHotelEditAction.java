package com.westchase.web.action.cmu;

import java.util.Date;

import javax.naming.InitialContext;

import com.opensymphony.xwork2.Preparable;
import com.westchase.ejb.CmuService;
import com.westchase.persistence.model.CmuHotel;

/**
 * @author marc
 *
 */
public class CmuHotelEditAction extends AbstractCmuAction implements Preparable {

	
	private CmuHotel cmuHotel;
	
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
            	cmuHotel = cmuServ.getCmuHotel(new Integer(getId().intValue()));
            	if (cmuHotel != null && cmuHotel.getVerified() != null) {
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
	    CmuHotel cmuHotel = getCmuHotel();
		if (cmuHotel != null) {
			if (isVerified() && cmuHotel.getVerified() == null) {
				cmuHotel.setVerified(new Date());
			}
	        if (cmuServ != null) {
	        	long cmuHotelId = cmuServ.saveCmuHotel(cmuHotel);
	        	setId(new Long(cmuHotelId));
	        }
		}
		return execute();
	}

	public CmuHotel getCmuHotel() {
		return cmuHotel;
	}

	public void setCmuHotel(CmuHotel cmuHotel) {
		this.cmuHotel = cmuHotel;
	}

}
