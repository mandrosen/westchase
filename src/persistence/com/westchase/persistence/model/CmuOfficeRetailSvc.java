package com.westchase.persistence.model;

// Generated Sep 5, 2010 4:55:55 PM by Hibernate Tools 3.3.0.GA

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * CmuOfficeRetailSvc generated by hbm2java
 */
@Entity
@Table(name = "cmu_office_retail_svc")
public class CmuOfficeRetailSvc implements java.io.Serializable {

	private Integer id;
	private Property property;
	private CmuQuarter cmuQuarter;
	private String completedBy;
	private boolean forSale;
	private String forSaleContact;
	private String forSalePhone;
	private Double sqFtForLease;
	private Double occupancy;
	private Double occupied;
	private Double largestSpace;
	private Double largestSpace6mths;
	private Double largestSpace12mths;
	private String propertyMgr;
	private String propertyMgrPhone;
	private String propertyMgrFax;
	private String propertyMgrEmail;
	private String mgmtCompany;
	private String mgmtCompanyAddr;
	private String leasingCompany;
	private String leasingCompanyAddr;
	private String leasingAgent;
	private String leasingAgentPhone;
	private String leasingAgentFax;
	private String leasingAgentEmail;
	private String comments;
	private Date updated;
	private Date verified;
	private boolean staticInfoCorrect;

	public CmuOfficeRetailSvc() {
	}

	public CmuOfficeRetailSvc(Property property, CmuQuarter cmuQuarter, boolean forSale, Date updated) {
		this.property = property;
		this.cmuQuarter = cmuQuarter;
		this.forSale = forSale;
		this.updated = updated;
	}

	public CmuOfficeRetailSvc(Property property, CmuQuarter cmuQuarter, String completedBy, boolean forSale,
			String forSaleContact, String forSalePhone, Double sqFtForLease, Double occupancy, Double largestSpace,
			Double largestSpace6mths, Double largestSpace12mths, String propertyMgr, String propertyMgrPhone,
			String propertyMgrFax, String propertyMgrEmail, String mgmtCompany, String mgmtCompanyAddr,
			String leasingCompany, String leasingCompanyAddr, String leasingAgent, String leasingAgentPhone,
			String leasingAgentFax, String leasingAgentEmail, String comments, Date updated, Date verified) {
		this.property = property;
		this.cmuQuarter = cmuQuarter;
		this.completedBy = completedBy;
		this.forSale = forSale;
		this.forSaleContact = forSaleContact;
		this.forSalePhone = forSalePhone;
		this.sqFtForLease = sqFtForLease;
		this.occupancy = occupancy;
		this.largestSpace = largestSpace;
		this.largestSpace6mths = largestSpace6mths;
		this.largestSpace12mths = largestSpace12mths;
		this.propertyMgr = propertyMgr;
		this.propertyMgrPhone = propertyMgrPhone;
		this.propertyMgrFax = propertyMgrFax;
		this.propertyMgrEmail = propertyMgrEmail;
		this.mgmtCompany = mgmtCompany;
		this.mgmtCompanyAddr = mgmtCompanyAddr;
		this.leasingCompany = leasingCompany;
		this.leasingCompanyAddr = leasingCompanyAddr;
		this.leasingAgent = leasingAgent;
		this.leasingAgentPhone = leasingAgentPhone;
		this.leasingAgentFax = leasingAgentFax;
		this.leasingAgentEmail = leasingAgentEmail;
		this.comments = comments;
		this.updated = updated;
		this.verified = verified;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "property", nullable = false)
	public Property getProperty() {
		return this.property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "quarter", nullable = false)
	public CmuQuarter getCmuQuarter() {
		return this.cmuQuarter;
	}

	public void setCmuQuarter(CmuQuarter cmuQuarter) {
		this.cmuQuarter = cmuQuarter;
	}

	@Column(name = "completed_by")
	public String getCompletedBy() {
		return this.completedBy;
	}

	public void setCompletedBy(String completedBy) {
		this.completedBy = completedBy;
	}

	@Column(name = "for_sale", nullable = false)
	public boolean isForSale() {
		return this.forSale;
	}

	public void setForSale(boolean forSale) {
		this.forSale = forSale;
	}

	@Column(name = "for_sale_contact")
	public String getForSaleContact() {
		return this.forSaleContact;
	}

	public void setForSaleContact(String forSaleContact) {
		this.forSaleContact = forSaleContact;
	}

	@Column(name = "for_sale_phone", length = 20)
	public String getForSalePhone() {
		return this.forSalePhone;
	}

	public void setForSalePhone(String forSalePhone) {
		this.forSalePhone = forSalePhone;
	}

	@Column(name = "sq_ft_for_lease", precision = 22, scale = 0)
	public Double getSqFtForLease() {
		return this.sqFtForLease;
	}

	public void setSqFtForLease(Double sqFtForLease) {
		this.sqFtForLease = sqFtForLease;
	}

	@Column(name = "occupancy", precision = 22, scale = 0)
	public Double getOccupancy() {
		return this.occupancy;
	}

	public void setOccupancy(Double occupancy) {
		this.occupancy = occupancy;
	}

	@Column(name = "occupied")
	public Double getOccupied() {
		return occupied;
	}

	public void setOccupied(Double occupied) {
		this.occupied = occupied;
	}

	@Column(name = "largest_space", precision = 22, scale = 0)
	public Double getLargestSpace() {
		return this.largestSpace;
	}

	public void setLargestSpace(Double largestSpace) {
		this.largestSpace = largestSpace;
	}

	@Column(name = "largest_space_6mths", precision = 22, scale = 0)
	public Double getLargestSpace6mths() {
		return this.largestSpace6mths;
	}

	public void setLargestSpace6mths(Double largestSpace6mths) {
		this.largestSpace6mths = largestSpace6mths;
	}

	@Column(name = "largest_space_12mths", precision = 22, scale = 0)
	public Double getLargestSpace12mths() {
		return this.largestSpace12mths;
	}

	public void setLargestSpace12mths(Double largestSpace12mths) {
		this.largestSpace12mths = largestSpace12mths;
	}

	@Column(name = "property_mgr")
	public String getPropertyMgr() {
		return this.propertyMgr;
	}

	public void setPropertyMgr(String propertyMgr) {
		this.propertyMgr = propertyMgr;
	}

	@Column(name = "property_mgr_phone", length = 20)
	public String getPropertyMgrPhone() {
		return this.propertyMgrPhone;
	}

	public void setPropertyMgrPhone(String propertyMgrPhone) {
		this.propertyMgrPhone = propertyMgrPhone;
	}

	@Column(name = "property_mgr_fax", length = 20)
	public String getPropertyMgrFax() {
		return this.propertyMgrFax;
	}

	public void setPropertyMgrFax(String propertyMgrFax) {
		this.propertyMgrFax = propertyMgrFax;
	}

	@Column(name = "property_mgr_email")
	public String getPropertyMgrEmail() {
		return this.propertyMgrEmail;
	}

	public void setPropertyMgrEmail(String propertyMgrEmail) {
		this.propertyMgrEmail = propertyMgrEmail;
	}

	@Column(name = "mgmt_company")
	public String getMgmtCompany() {
		return this.mgmtCompany;
	}

	public void setMgmtCompany(String mgmtCompany) {
		this.mgmtCompany = mgmtCompany;
	}

	@Column(name = "mgmt_company_addr")
	public String getMgmtCompanyAddr() {
		return this.mgmtCompanyAddr;
	}

	public void setMgmtCompanyAddr(String mgmtCompanyAddr) {
		this.mgmtCompanyAddr = mgmtCompanyAddr;
	}

	@Column(name = "leasing_company")
	public String getLeasingCompany() {
		return this.leasingCompany;
	}

	public void setLeasingCompany(String leasingCompany) {
		this.leasingCompany = leasingCompany;
	}

	@Column(name = "leasing_company_addr")
	public String getLeasingCompanyAddr() {
		return this.leasingCompanyAddr;
	}

	public void setLeasingCompanyAddr(String leasingCompanyAddr) {
		this.leasingCompanyAddr = leasingCompanyAddr;
	}

	@Column(name = "leasing_agent")
	public String getLeasingAgent() {
		return this.leasingAgent;
	}

	public void setLeasingAgent(String leasingAgent) {
		this.leasingAgent = leasingAgent;
	}

	@Column(name = "leasing_agent_phone", length = 20)
	public String getLeasingAgentPhone() {
		return this.leasingAgentPhone;
	}

	public void setLeasingAgentPhone(String leasingAgentPhone) {
		this.leasingAgentPhone = leasingAgentPhone;
	}

	@Column(name = "leasing_agent_fax", length = 20)
	public String getLeasingAgentFax() {
		return this.leasingAgentFax;
	}

	public void setLeasingAgentFax(String leasingAgentFax) {
		this.leasingAgentFax = leasingAgentFax;
	}

	@Column(name = "leasing_agent_email")
	public String getLeasingAgentEmail() {
		return this.leasingAgentEmail;
	}

	public void setLeasingAgentEmail(String leasingAgentEmail) {
		this.leasingAgentEmail = leasingAgentEmail;
	}

	@Column(name = "comments")
	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated", nullable = true, length = 19)
	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "verified", nullable = true, length = 19)
	public Date getVerified() {
		return this.verified;
	}

	public void setVerified(Date verified) {
		this.verified = verified;
	}

	@Column(name = "static_info_correct")
	public boolean isStaticInfoCorrect() {
		return staticInfoCorrect;
	}

	public void setStaticInfoCorrect(boolean staticInfoCorrect) {
		this.staticInfoCorrect = staticInfoCorrect;
	}

}
