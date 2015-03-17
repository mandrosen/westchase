package com.westchase.persistence.dto.cmu.report;

import java.util.Date;

import com.westchase.persistence.model.CmuDevsite;
import com.westchase.persistence.model.CmuQuarter;
import com.westchase.persistence.model.Property;

public class DevsiteDTO implements CmuDTO {

	private Integer id;
	private Property property;
	private CmuQuarter cmuQuarter;
	private String completedBy;
	private Double siteSize;
	private String frontage;
	private String contact;
	private String company;
	private String phone;
	private String fax;
	private String email;
	private boolean divide;
	private String priceSqFt;
	private String restrictions;
	private String comments;
	private Date updated;
	private Date verified;
	private boolean staticInfoCorrect;

	public DevsiteDTO(CmuDevsite dev) {
		super();
		if (dev != null) {
			setId(dev.getId());
			setProperty(dev.getProperty());
			setCmuQuarter(dev.getCmuQuarter());
			setCompletedBy(dev.getCompletedBy());
			setSiteSize(dev.getSiteSize());
			setFrontage(dev.getFrontage());
			setContact(dev.getContact());
			setCompany(dev.getCompany());
			setPhone(dev.getPhone());
			setFax(dev.getFax());
			setEmail(dev.getEmail());
			setDivide(dev.isDivide());
			setPriceSqFt(dev.getPriceSqFt());
			setRestrictions(dev.getRestrictions());
			setStaticInfoCorrect(dev.isStaticInfoCorrect());
			setVerified(dev.getVerified());
			setUpdated(dev.getUpdated());
			setComments(dev.getComments());
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

	public Double getSiteSize() {
		return siteSize;
	}

	public void setSiteSize(Double siteSize) {
		this.siteSize = siteSize;
	}

	public String getFrontage() {
		return frontage;
	}

	public void setFrontage(String frontage) {
		this.frontage = frontage;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isDivide() {
		return divide;
	}

	public void setDivide(boolean divide) {
		this.divide = divide;
	}

	public String getPriceSqFt() {
		return priceSqFt;
	}

	public void setPriceSqFt(String priceSqFt) {
		this.priceSqFt = priceSqFt;
	}

	public String getRestrictions() {
		return restrictions;
	}

	public void setRestrictions(String restrictions) {
		this.restrictions = restrictions;
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

}
