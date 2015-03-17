package com.westchase.persistence.dto.cmu.report;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.westchase.persistence.model.CmuApartment;
import com.westchase.persistence.model.CmuQuarter;
import com.westchase.persistence.model.Property;

public class ApartmentDTO implements CmuDTO {

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
	
	public ApartmentDTO(CmuApartment apt) {
		super();
		if (apt != null) {
			setId(apt.getId());
			setProperty(apt.getProperty());
			setCmuQuarter(apt.getCmuQuarter());
			setCompletedBy(apt.getCompletedBy());
			setOccupancyRate(apt.getOccupancyRate());
			setOwner(apt.getProperty().getOwner());
			setStaticInfoCorrect(apt.isStaticInfoCorrect());
			setVerified(apt.getVerified());
			setUpdated(apt.getUpdated());
			setComments(apt.getComments());
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Property getProperty() {
		return property;
	}
	
	public void setProperty(Property property) {
		this.property = property;
	}

	public CmuQuarter getCmuQuarter() {
		return cmuQuarter;
	}

	public void setCmuQuarter(CmuQuarter cmuQuarter) {
		this.cmuQuarter = cmuQuarter;
	}

	public String getCompletedBy() {
		return completedBy;
	}

	public void setCompletedBy(String completedBy) {
		this.completedBy = completedBy;
	}

	public Double getOccupancyRate() {
		return occupancyRate;
	}

	public void setOccupancyRate(Double occupancyRate) {
		this.occupancyRate = occupancyRate;
	}

	public String getCommunityMgr() {
		return communityMgr;
	}

	public void setCommunityMgr(String communityMgr) {
		this.communityMgr = communityMgr;
	}

	public String getCommunityMgrEmail() {
		return communityMgrEmail;
	}

	public void setCommunityMgrEmail(String communityMgrEmail) {
		this.communityMgrEmail = communityMgrEmail;
	}

	public String getCommunityMgrPhone() {
		return communityMgrPhone;
	}

	public void setCommunityMgrPhone(String communityMgrPhone) {
		this.communityMgrPhone = communityMgrPhone;
	}

	public String getCommunityMgrFax() {
		return communityMgrFax;
	}

	public void setCommunityMgrFax(String communityMgrFax) {
		this.communityMgrFax = communityMgrFax;
	}

	public String getMgmtCompany() {
		return mgmtCompany;
	}

	public void setMgmtCompany(String mgmtCompany) {
		this.mgmtCompany = mgmtCompany;
	}

	public String getMgmtCompanyAddr() {
		return mgmtCompanyAddr;
	}

	public void setMgmtCompanyAddr(String mgmtCompanyAddr) {
		this.mgmtCompanyAddr = mgmtCompanyAddr;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getSupervisorEmail() {
		return supervisorEmail;
	}

	public void setSupervisorEmail(String supervisorEmail) {
		this.supervisorEmail = supervisorEmail;
	}

	public String getSupervisorPhone() {
		return supervisorPhone;
	}

	public void setSupervisorPhone(String supervisorPhone) {
		this.supervisorPhone = supervisorPhone;
	}

	public String getSupervisorFax() {
		return supervisorFax;
	}

	public void setSupervisorFax(String supervisorFax) {
		this.supervisorFax = supervisorFax;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwnerAddress() {
		return ownerAddress;
	}

	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}

	public String getOwnerPhone() {
		return ownerPhone;
	}

	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}

	public String getOwnerFax() {
		return ownerFax;
	}

	public void setOwnerFax(String ownerFax) {
		this.ownerFax = ownerFax;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getVerified() {
		return verified;
	}

	public void setVerified(Date verified) {
		this.verified = verified;
	}

	public boolean isStaticInfoCorrect() {
		return staticInfoCorrect;
	}

	public void setStaticInfoCorrect(boolean staticInfoCorrect) {
		this.staticInfoCorrect = staticInfoCorrect;
	}

	public void setCommunityMgr(String firstName, String lastName) {
		setCommunityMgr(String.format("%s %s", firstName, lastName));
	}

	public void setCommunityMgrPhone(String phone, String ext) {
		if (StringUtils.isNotBlank(ext)) {
			setCommunityMgrPhone(String.format("%s x%s", phone, ext));
		} else {
			setCommunityMgrPhone(phone);
		}
	}

	public void setSupervisor(String firstName, String lastName) {
		setSupervisor(String.format("%s %s", firstName, lastName));
	}

	public void setMgmtCompanyAddr(String stNumber, String stAddress, String roomNum, String city, String state,
			String zip) {
		if (StringUtils.isNotBlank(roomNum)) {
			setMgmtCompanyAddr(String.format("%s %s %s %s %s %s", stNumber, stAddress, roomNum, city, state, zip));
		} else {
			setMgmtCompanyAddr(String.format("%s %s %s %s %s", stNumber, stAddress, city, state, zip));
		}
	}

	public void setSupervisorPhone(String phone, String ext) {
		if (StringUtils.isNotBlank(ext)) {
			setSupervisorPhone(String.format("%s x%s", phone, ext));
		} else {
			setSupervisorPhone(phone);
		}
	}

}
