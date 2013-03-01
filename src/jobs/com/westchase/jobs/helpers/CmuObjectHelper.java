package com.westchase.jobs.helpers;

import com.westchase.persistence.model.CmuApartment;
import com.westchase.persistence.model.CmuDevsite;
import com.westchase.persistence.model.CmuHotel;
import com.westchase.persistence.model.CmuLease;
import com.westchase.persistence.model.CmuOfficeRetailSvc;
import com.westchase.persistence.model.CmuQuarter;
import com.westchase.persistence.model.CmuTransactionType;
import com.westchase.persistence.model.Property;

/**
 * @author marc
 *
 */
public class CmuObjectHelper {
	
	public static CmuApartment createCmuApartment(com.westchase.ws.cmu.CmuApartment input) {
		CmuApartment cmuApt = new CmuApartment();
		cmuApt.setCmuQuarter(new CmuQuarter(input.getQuarter()));
		cmuApt.setComments(input.getComments());
		cmuApt.setCommunityMgr(input.getCommunity_mgr());
		cmuApt.setCommunityMgrEmail(input.getCommunity_mgr_email());
		cmuApt.setCommunityMgrFax(input.getCommunity_mgr_fax());
		cmuApt.setCommunityMgrPhone(input.getCommunity_mgr_phone());
		cmuApt.setCompletedBy(input.getCompleted_by());
		cmuApt.setMgmtCompany(input.getMgmt_company());
		cmuApt.setMgmtCompanyAddr(input.getMgmt_company_addr());
		cmuApt.setOccupancyRate(new Double(input.getOccupancy_rate()));
		cmuApt.setOwner(input.getOwner());
		cmuApt.setOwnerAddress(input.getOwner_address());
		cmuApt.setOwnerFax(input.getOwner_fax());
		cmuApt.setOwnerPhone(input.getOwner_phone());
		cmuApt.setProperty(new Property(input.getProperty()));
		cmuApt.setSupervisor(input.getSupervisor());
		cmuApt.setSupervisorEmail(input.getSupervisor_email());
		cmuApt.setSupervisorFax(input.getSupervisor_fax());
		cmuApt.setSupervisorPhone(input.getSupervisor_phone());
		return cmuApt;
	}
	
	public static CmuDevsite createCmuDevsite(com.westchase.ws.cmu.CmuDevsite input) {
		CmuDevsite cmuDevsite = new CmuDevsite();
		cmuDevsite.setCmuQuarter(new CmuQuarter(input.getQuarter()));
		cmuDevsite.setComments(input.getComments());
		cmuDevsite.setCompany(input.getCompany());
		cmuDevsite.setCompletedBy(input.getCompleted_by());
		cmuDevsite.setContact(input.getContact());
		cmuDevsite.setDivide(input.getDivide() > 0);
		cmuDevsite.setEmail(input.getEmail());
		cmuDevsite.setFax(input.getFax());
		cmuDevsite.setFrontage(input.getFrontage());
		cmuDevsite.setPhone(input.getPhone());
		cmuDevsite.setPriceSqFt(input.getPrice_sq_ft());
		cmuDevsite.setProperty(new Property(input.getProperty()));
		cmuDevsite.setRestrictions(input.getRestrictions());
		cmuDevsite.setSiteSize(new Double(input.getSite_size()));
		return cmuDevsite;
	}
	
	public static CmuHotel createCmuHotel(com.westchase.ws.cmu.CmuHotel input) {
		CmuHotel cmuHotel = new CmuHotel();
		cmuHotel.setCmuQuarter(new CmuQuarter(input.getQuarter()));
		cmuHotel.setComments(input.getComments());
		cmuHotel.setCompletedBy(input.getCompleted_by());
		cmuHotel.setGeneralMgr(input.getGeneral_mgr());
		cmuHotel.setGeneralMgrEmail(input.getGeneral_mgr_email());
		cmuHotel.setGeneralMgrPhone(input.getGeneral_mgr_phone());
		cmuHotel.setOccupancy(new Double(input.getOccupancy()));
		cmuHotel.setProperty(new Property(input.getProperty()));
		return cmuHotel;
	}
	
	public static CmuLease createCmuLease(com.westchase.ws.cmu.CmuLease input) {
		CmuLease cmuLease = new CmuLease();
		cmuLease.setCmuQuarter(new CmuQuarter(input.getQuarter()));
		cmuLease.setCmuTransactionType(new CmuTransactionType(input.getLease_trans_type()));
		cmuLease.setOwnersRep(input.getOwners_rep());
		cmuLease.setProperty(new Property(input.getProperty()));
		cmuLease.setSqFt(new Double(input.getSq_ft()));
		cmuLease.setTenantName(input.getTenant_name());
		cmuLease.setTenantsRep(input.getTenants_rep());
		return cmuLease;
	}
	
	public static CmuOfficeRetailSvc createCmuOfficeRetailSvc(com.westchase.ws.cmu.CmuOfficeRetailSvc input) {
		CmuOfficeRetailSvc cmuOrs = new CmuOfficeRetailSvc();
		cmuOrs.setCmuQuarter(new CmuQuarter(input.getQuarter()));
		cmuOrs.setComments(input.getComments());
		cmuOrs.setCompletedBy(input.getCompleted_by());
		cmuOrs.setForSale(input.getFor_sale() > 0);
		cmuOrs.setForSaleContact(input.getFor_sale_contact());
		cmuOrs.setForSalePhone(input.getFor_sale_phone());
		cmuOrs.setLargestSpace(new Double(input.getLargest_space()));
		cmuOrs.setLargestSpace6mths(new Double(input.getLargest_space_6Mths()));
		cmuOrs.setLargestSpace12mths(new Double(input.getLargest_space_12Mths()));
		cmuOrs.setLeasingAgent(input.getLeasing_agent());
		cmuOrs.setLeasingAgentEmail(input.getLeasing_agent_email());
		cmuOrs.setLeasingAgentFax(input.getLeasing_agent_fax());
		cmuOrs.setLeasingAgentPhone(input.getLeasing_agent_phone());
		cmuOrs.setLeasingCompany(input.getLeasing_company());
		cmuOrs.setLeasingCompanyAddr(input.getLeasing_company_addr());
		cmuOrs.setMgmtCompany(input.getMgmt_company());
		cmuOrs.setMgmtCompanyAddr(input.getMgmt_company_addr());
		cmuOrs.setOccupancy(new Double(input.getOccupancy()));
		cmuOrs.setProperty(new Property(input.getProperty()));
		cmuOrs.setPropertyMgr(input.getProperty_mgr());
		cmuOrs.setPropertyMgrEmail(input.getProperty_mgr_email());
		cmuOrs.setPropertyMgrFax(input.getProperty_mgr_fax());
		cmuOrs.setPropertyMgrPhone(input.getProperty_mgr_phone());
		cmuOrs.setSqFtForLease(new Double(input.getSq_ft_for_lease()));
		return cmuOrs;
	}
	
}
