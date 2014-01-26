package com.westchase.persistence.criteria;

import com.westchase.persistence.model.PhoneBook;

/**
 * @author marc
 *
 */
public class PhoneBookSearchCriteria extends SearchCriteria<PhoneBook> {
	private Boolean dontEmail;
	private Boolean westchaseToday;
	private Boolean investor;
	private boolean orphanedOnly;
	
	public boolean isEmptySearch() {
		PhoneBook so = getSearchObject(); 
		return dontEmail == null && westchaseToday == null && investor == null && orphanedOnly == false &&
				(so == null ||
				(so.getId() == null &&
				so.getTitle() == null &&
				so.getFirstName() == null &&
				so.getMiddleInitial() == null &&
				so.getLastName() == null &&
				so.getSuffix() == null &&
				so.getSalutation() == null &&
				(so.getCompany() == null || so.getCompany().getCompany() == null) &&
				so.getDepartment() == null &&
				so.getJobTitle() == null &&
				so.getEmail() == null &&
				so.getAltEmail() == null &&
				so.getWkPhone() == null &&
				so.getFaxPhone() == null &&
				so.getMobilePhone() == null &&
				so.getHomePhone() == null &&
				so.getHomeFax() == null &&
				so.getHomeAddress() == null &&
				so.getInputDate() == null &&
				so.getLastupdate() == null));
	}
	
	public Boolean getDontEmail() {
		return dontEmail;
	}
	public void setDontEmail(Boolean dontEmail) {
		this.dontEmail = dontEmail;
	}
	public Boolean getWestchaseToday() {
		return westchaseToday;
	}
	public void setWestchaseToday(Boolean westchaseToday) {
		this.westchaseToday = westchaseToday;
	}
	public Boolean getInvestor() {
		return investor;
	}
	public void setInvestor(Boolean investor) {
		this.investor = investor;
	}
	public boolean isOrphanedOnly() {
		return orphanedOnly;
	}
	public void setOrphanedOnly(boolean orphanedOnly) {
		this.orphanedOnly = orphanedOnly;
	}
}
