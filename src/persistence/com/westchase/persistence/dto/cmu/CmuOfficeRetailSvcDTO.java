package com.westchase.persistence.dto.cmu;

import java.util.List;

import com.westchase.persistence.model.CmuLease;
import com.westchase.persistence.model.CmuOfficeRetailSvc;

/**
 * @author marc
 *
 */
public class CmuOfficeRetailSvcDTO {

	private CmuOfficeRetailSvc cmuOfficeRetailSvc;
	private List<CmuLease> leases;
	
	public CmuOfficeRetailSvcDTO() {
		super();
	}

	public CmuOfficeRetailSvcDTO(CmuOfficeRetailSvc cmuOfficeRetailSvc, List<CmuLease> leases) {
		super();
		this.cmuOfficeRetailSvc = cmuOfficeRetailSvc;
		this.leases = leases;
	}

	public CmuOfficeRetailSvc getCmuOfficeRetailSvc() {
		return cmuOfficeRetailSvc;
	}

	public void setCmuOfficeRetailSvc(CmuOfficeRetailSvc cmuOfficeRetailSvc) {
		this.cmuOfficeRetailSvc = cmuOfficeRetailSvc;
	}

	public List<CmuLease> getLeases() {
		return leases;
	}

	public void setLeases(List<CmuLease> leases) {
		this.leases = leases;
	}
	
}
