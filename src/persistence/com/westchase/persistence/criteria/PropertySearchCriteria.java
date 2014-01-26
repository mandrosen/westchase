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
	
	public boolean isEmptySearch() {
		Property so = getSearchObject(); 
		return forSale == null && singleTenant == null && willDivide == null && trpassAfdvt == null && flagPole == null &&
				(so == null ||
				(so.getId() == null &&      
				so.getBuildingName() == null &&      
				so.getGeoNumber() == null &&      
				so.getGeoAddress() == null &&      
				so.getGeoCity() == null &&      
				so.getGeoState() == null &&      
				so.getGeoZipCode() == null &&      
				so.getLatitude() == null &&      
				so.getLongitude() == null &&      
				so.getPropertyType() == null &&      
				so.getBuildingSize() == null &&      
				so.getAcreage() == null &&      
				so.getBusinessType() == null &&      
				so.getCenter() == null &&      
				so.getCommercialSpcFore() == null &&      
				so.getFacet() == null &&       
				so.getFrontage() == null &&      
				so.getHcad() == null &&      
				so.getLargestVacancy() == null &&      
				so.getNoUnits() == null &&      
				so.getOccupancyRate() == null &&      
				so.getOccupiedSqFt() == null &&      
				so.getOwner() == null &&      
				so.getMetaOwner() == null &&      
				so.getPriceSqFt() == null &&      
				so.getRestrictions() == null &&        
				so.getTrspassDate() == null &&      
				so.getVacantUnits() == null &&       
				so.getYearBuilt() == null &&      
				so.getInputDate() == null &&   
				so.getLastUpdate() == null &&
				so.getNotes() == null));
	}
	
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
