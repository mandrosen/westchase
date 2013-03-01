package com.westchase.persistence.criteria;

import com.westchase.persistence.model.Property;

/**
 * @author marc
 *
 */
public class PropertySearchCriteria extends SearchCriteria<Property> {

	private Boolean forSale;
	private Boolean singleTenant;
	private Boolean willDivide;
	private Boolean trpassAfdvt;
	private Boolean flagPole;
	
	public Boolean getForSale() {
		return forSale;
	}
	public void setForSale(Boolean forSale) {
		this.forSale = forSale;
	}
	public Boolean getSingleTenant() {
		return singleTenant;
	}
	public void setSingleTenant(Boolean singleTenant) {
		this.singleTenant = singleTenant;
	}
	public Boolean getWillDivide() {
		return willDivide;
	}
	public void setWillDivide(Boolean willDivide) {
		this.willDivide = willDivide;
	}
	public Boolean getTrpassAfdvt() {
		return trpassAfdvt;
	}
	public void setTrpassAfdvt(Boolean trpassAfdvt) {
		this.trpassAfdvt = trpassAfdvt;
	}
	public Boolean getFlagPole() {
		return flagPole;
	}
	public void setFlagPole(Boolean flagPole) {
		this.flagPole = flagPole;
	}
	
	
}
