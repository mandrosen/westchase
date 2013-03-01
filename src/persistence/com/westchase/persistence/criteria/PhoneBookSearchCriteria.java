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
