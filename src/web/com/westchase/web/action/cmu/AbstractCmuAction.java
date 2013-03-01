package com.westchase.web.action.cmu;

import com.westchase.ejb.CmuService;
import com.westchase.web.action.AbstractWestchaseAction;

/**
 * @author marc
 *
 */
public abstract class AbstractCmuAction extends AbstractWestchaseAction {

	protected CmuService cmuServ;
	
	private Long id;
	
	private boolean verified;
	
	public AbstractCmuAction() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}
}
