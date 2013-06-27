package com.westchase.jobs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.varia.scheduler.Schedulable;

import com.westchase.ejb.CmuService;
import com.westchase.jobs.helpers.CmuObjectHelper;
import com.westchase.ws.cmu.CMULocator;
import com.westchase.ws.cmu.CMUPortType;
import com.westchase.ws.cmu.CmuApartment;
import com.westchase.ws.cmu.CmuDevsite;
import com.westchase.ws.cmu.CmuHotel;
import com.westchase.ws.cmu.CmuLease;
import com.westchase.ws.cmu.CmuOfficeRetailSvc;

/**
 * @author marc
 *
 */
public class CmuTransferJob implements Schedulable {
	
	private final Log log = LogFactory.getLog(getClass());

	private CmuService cmuServ;
	private CMUPortType stub;
	
	@Override
	public void perform(Date timeOfCall, long remainingRepititions) {
		try {
			InitialContext ctx = new InitialContext();
			cmuServ = (CmuService) ctx.lookup("westchase/CmuServiceBean/remote");
			stub = new CMULocator().getCMUPort();
		} catch (Exception e) {
			log.error("", e);
		}
		if (cmuServ != null && stub != null) {
			transferApartments();
			transferDevsites();
			transferHotels();
			transferOfficeRetailSvcs();
			transferLeases();
		}
	}
	
	private void transferApartments() {
		try {
			CmuApartment[] inputs = stub.getNewApartments();
			List<Long> ids = new ArrayList<Long>();
			if (inputs != null && inputs.length > 0) {
				for (CmuApartment input : inputs) {
					com.westchase.persistence.model.CmuApartment cmuApt = CmuObjectHelper.createCmuApartment(input);
					long savedId = cmuServ.saveCmuApartment(cmuApt);
					if (savedId > 0) {
						ids.add(new Long(input.getId()));
					}
				}
			}
			if (ids != null && !ids.isEmpty()) {
				stub.setTransferred("cmu_apartment", getIdArray(ids));
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}
	
	private void transferDevsites() {
		try {
			CmuDevsite[] inputs = stub.getNewDevsites();
			List<Long> ids = new ArrayList<Long>();
			if (inputs != null && inputs.length > 0) {
				for (CmuDevsite input : inputs) {
					com.westchase.persistence.model.CmuDevsite cmuDevsite = CmuObjectHelper.createCmuDevsite(input);
					long savedId = cmuServ.saveCmuDevsite(cmuDevsite);
					if (savedId > 0) {
						ids.add(new Long(input.getId()));
					}
				}
			}
			if (ids != null && !ids.isEmpty()) {
				stub.setTransferred("cmu_devsite", getIdArray(ids));
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}
	
	private void transferHotels() {
		try {
			CmuHotel[] inputs = stub.getNewHotels();
			List<Long> ids = new ArrayList<Long>();
			if (inputs != null && inputs.length > 0) {
				for (CmuHotel input : inputs) {
					com.westchase.persistence.model.CmuHotel cmuHotel = CmuObjectHelper.createCmuHotel(input);
					long savedId = cmuServ.saveCmuHotel(cmuHotel);
					if (savedId > 0) {
						ids.add(new Long(input.getId()));
					}
				}
			}
			if (ids != null && !ids.isEmpty()) {
				stub.setTransferred("cmu_hotel", getIdArray(ids));
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}
	
	private void transferLeases() {
		boolean deleted = false;
		try {
			CmuLease[] inputs = stub.getNewLeases();
			List<Long> ids = new ArrayList<Long>();
			if (inputs != null && inputs.length > 0) {
				for (CmuLease input : inputs) {
					com.westchase.persistence.model.CmuLease cmuLease = CmuObjectHelper.createCmuLease(input);
					if (!deleted) {
						deleted = cmuServ.deleteAllLeases(cmuLease.getProperty().getId(), cmuLease.getCmuQuarter().getId());
					}
					long savedId = cmuServ.saveCmuLease(cmuLease);
					if (savedId > 0) {
						ids.add(new Long(input.getId()));
					}
				}
			}
			if (ids != null && !ids.isEmpty()) {
				stub.setTransferred("cmu_lease", getIdArray(ids));
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}
	
	private void transferOfficeRetailSvcs() {
		try {
			CmuOfficeRetailSvc[] inputs = stub.getNewOfficeRetailSvcs();
			List<Long> ids = new ArrayList<Long>();
			if (inputs != null && inputs.length > 0) {
				for (CmuOfficeRetailSvc input : inputs) {
					com.westchase.persistence.model.CmuOfficeRetailSvc cmuOrs = CmuObjectHelper.createCmuOfficeRetailSvc(input);
					long savedId = cmuServ.saveCmuOfficeRetailSvc(cmuOrs);
					if (savedId > 0) {
						ids.add(new Long(input.getId()));
					}
				}
			}
			if (ids != null && !ids.isEmpty()) {
				stub.setTransferred("cmu_office_retail_svc", getIdArray(ids));
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}
	
	private int[] getIdArray(List<Long> ids) {
		int[] idArray = null;
		if (ids != null && !ids.isEmpty()) {
			idArray = new int[ids.size()];
			for (int i = 0; i < ids.size(); i++) {
				Long id = ids.get(i);
				if (id != null) {
					idArray[i] = ids.get(i).intValue();
				}
			}
		}
		return idArray;
	}

}
