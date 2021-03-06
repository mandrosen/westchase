package com.westchase.persistence.model;

// Generated Sep 5, 2010 6:19:40 PM by Hibernate Tools 3.3.0.GA

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
 * CmuApartment generated by hbm2java
 */
@Entity
@Table(name = "cmu_apartment")
public class CmuApartment implements java.io.Serializable {

	private Integer id;
	private Property property;
	private CmuQuarter cmuQuarter;
	private String completedBy;
	private Double occupancyRate;
	private String communityMgr;
	private String communityMgrEmail;
	private String communityMgrPhone;
	private String communityMgrFax;
	private String mgmtCompany;
	private String mgmtCompanyAddr;
	private String supervisor;
	private String supervisorEmail;
	private String supervisorPhone;
	private String supervisorFax;
	private String owner;
	private String ownerAddress;
	private String ownerPhone;
	private String ownerFax;
	private String comments;
	private Date updated;
	private Date verified;
	private boolean staticInfoCorrect;

	public CmuApartment() {
	}

	public CmuApartment(Property property, CmuQuarter cmuQuarter, Date updated) {
		this.property = property;
		this.cmuQuarter = cmuQuarter;
		this.updated = updated;
	}

	public CmuApartment(Property property, CmuQuarter cmuQuarter, String completedBy, Double occupancyRate,
			String communityMgr, String communityMgrEmail, String communityMgrPhone, String communityMgrFax,
			String mgmtCompany, String mgmtCompanyAddr,
			String supervisor, String supervisorEmail, String supervisorPhone, String supervisorFax, String owner,
			String ownerAddress, String ownerPhone, String ownerFax, String comments, Date updated, Date verified) {
		this.property = property;
		this.cmuQuarter = cmuQuarter;
		this.completedBy = completedBy;
		this.occupancyRate = occupancyRate;
		this.communityMgr = communityMgr;
		this.communityMgrEmail = communityMgrEmail;
		this.communityMgrPhone = communityMgrPhone;
		this.communityMgrFax = communityMgrFax;
		this.mgmtCompany = mgmtCompany;
		this.mgmtCompanyAddr = mgmtCompanyAddr;
		this.supervisor = supervisor;
		this.supervisorEmail = supervisorEmail;
		this.supervisorPhone = supervisorPhone;
		this.supervisorFax = supervisorFax;
		this.owner = owner;
		this.ownerAddress = ownerAddress;
		this.ownerPhone = ownerPhone;
		this.ownerFax = ownerFax;
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

	@Column(name = "occupancy_rate", precision = 22, scale = 0)
	public Double getOccupancyRate() {
		return this.occupancyRate;
	}

	public void setOccupancyRate(Double occupancyRate) {
		this.occupancyRate = occupancyRate;
	}

	@Column(name = "community_mgr")
	public String getCommunityMgr() {
		return this.communityMgr;
	}

	public void setCommunityMgr(String communityMgr) {
		this.communityMgr = communityMgr;
	}

	@Column(name = "community_mgr_email")
	public String getCommunityMgrEmail() {
		return this.communityMgrEmail;
	}

	public void setCommunityMgrEmail(String communityMgrEmail) {
		this.communityMgrEmail = communityMgrEmail;
	}

	@Column(name = "community_mgr_phone", length = 20)
	public String getCommunityMgrPhone() {
		return this.communityMgrPhone;
	}

	public void setCommunityMgrPhone(String communityMgrPhone) {
		this.communityMgrPhone = communityMgrPhone;
	}

	@Column(name = "community_mgr_fax", length = 20)
	public String getCommunityMgrFax() {
		return this.communityMgrFax;
	}

	public void setCommunityMgrFax(String communityMgrFax) {
		this.communityMgrFax = communityMgrFax;
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

	@Column(name = "supervisor")
	public String getSupervisor() {
		return this.supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	@Column(name = "supervisor_email")
	public String getSupervisorEmail() {
		return this.supervisorEmail;
	}

	public void setSupervisorEmail(String supervisorEmail) {
		this.supervisorEmail = supervisorEmail;
	}

	@Column(name = "supervisor_phone", length = 20)
	public String getSupervisorPhone() {
		return this.supervisorPhone;
	}

	public void setSupervisorPhone(String supervisorPhone) {
		this.supervisorPhone = supervisorPhone;
	}

	@Column(name = "supervisor_fax", length = 20)
	public String getSupervisorFax() {
		return this.supervisorFax;
	}

	public void setSupervisorFax(String supervisorFax) {
		this.supervisorFax = supervisorFax;
	}

	@Column(name = "owner")
	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Column(name = "owner_address")
	public String getOwnerAddress() {
		return this.ownerAddress;
	}

	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}

	@Column(name = "owner_phone", length = 20)
	public String getOwnerPhone() {
		return this.ownerPhone;
	}

	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}

	@Column(name = "owner_fax", length = 20)
	public String getOwnerFax() {
		return this.ownerFax;
	}

	public void setOwnerFax(String ownerFax) {
		this.ownerFax = ownerFax;
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
