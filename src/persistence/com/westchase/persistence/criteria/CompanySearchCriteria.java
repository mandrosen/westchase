package com.westchase.persistence.criteria;

import com.westchase.persistence.model.Company;


/**
 * @author marc
 *
 */
public class CompanySearchCriteria extends SearchCriteria<Company> {
	private Boolean vendor;
	private Integer mapNo;

	private boolean orphanedOnly;
	private boolean outsideDistrict;
	private String employeeRange;
	
	public Boolean getVendor() {
		return vendor;
	}

	public void setVendor(Boolean vendor) {
		this.vendor = vendor;
	}

	public boolean isOutsideDistrict() {
		return outsideDistrict;
	}

	public void setOutsideDistrict(boolean outsideDistrict) {
		this.outsideDistrict = outsideDistrict;
	}

	public boolean isOrphanedOnly() {
		return orphanedOnly;
	}

	public void setOrphanedOnly(boolean orphanedOnly) {
		this.orphanedOnly = orphanedOnly;
	}

	public Integer getMapNo() {
		return mapNo;
	}

	public void setMapNo(Integer mapNo) {
		this.mapNo = mapNo;
	}

	public String getEmployeeRange() {
		return employeeRange;
	}

	public void setEmployeeRange(String employeeRange) {
		this.employeeRange = employeeRange;
	}
}
