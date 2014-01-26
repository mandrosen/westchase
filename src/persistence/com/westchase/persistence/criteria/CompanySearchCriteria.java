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
	
	public boolean isEmptySearch() {
		Company so = getSearchObject();
		return vendor == null && mapNo == null && !orphanedOnly && !outsideDistrict && employeeRange == null &&
				(so == null ||
				(so.getId() == null &&      
				so.getCompany() == null &&
				so.getStNumber() == null &&
				so.getStAddress() == null &&
				so.getRoomNo() == null &&
				so.getCity() == null &&
				so.getState() == null &&
				so.getZipCode() == null &&
				so.getWkPhone() == null &&
				so.getFaxPhone() == null &&
				so.getLatitude() == null &&
				so.getLongitude() == null &&
				(so.getCompanyType() == null || so.getCompanyType().getId() == null) &&
				so.getWebsite() == null &&
				so.getOwner() == null &&
				so.getCenter() == null &&
				so.getHcad() == null && 
				so.getNaics() == null &&
				so.getSquareFeet() == null &&
				so.getClassification() == null &&
				so.getSubClassification() == null &&
				so.getNotes() == null &&
				so.getOther() == null &&
				so.getInputDate() == null &&
				so.getLastUpdate() == null));
	}
	
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
