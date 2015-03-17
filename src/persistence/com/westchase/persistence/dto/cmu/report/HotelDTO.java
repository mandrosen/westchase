package com.westchase.persistence.dto.cmu.report;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.westchase.persistence.model.CmuHotel;
import com.westchase.persistence.model.CmuQuarter;
import com.westchase.persistence.model.Property;

public class HotelDTO implements CmuDTO {

	private Integer id;
	private Property property;
	private CmuQuarter cmuQuarter;
	private String completedBy;
	private String generalMgr;
	private String generalMgrEmail;
	private String generalMgrPhone;
	private Double occupancy;
	private String comments;
	private Date updated;
	private Date verified;
	private boolean staticInfoCorrect;
	
	public HotelDTO(CmuHotel hotel) {
		super();
		if (hotel != null) {
			setId(hotel.getId());
			setProperty(hotel.getProperty());
			setCmuQuarter(hotel.getCmuQuarter());
			setCompletedBy(hotel.getCompletedBy());
			setOccupancy(hotel.getOccupancy());
			setStaticInfoCorrect(hotel.isStaticInfoCorrect());
			setVerified(hotel.getVerified());
			setUpdated(hotel.getUpdated());
			setComments(hotel.getComments());
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

	public String getGeneralMgr() {
		return generalMgr;
	}

	public void setGeneralMgr(String generalMgr) {
		this.generalMgr = generalMgr;
	}

	public String getGeneralMgrEmail() {
		return generalMgrEmail;
	}

	public void setGeneralMgrEmail(String generalMgrEmail) {
		this.generalMgrEmail = generalMgrEmail;
	}

	public String getGeneralMgrPhone() {
		return generalMgrPhone;
	}

	public void setGeneralMgrPhone(String generalMgrPhone) {
		this.generalMgrPhone = generalMgrPhone;
	}

	public Double getOccupancy() {
		return occupancy;
	}

	public void setOccupancy(Double occupancy) {
		this.occupancy = occupancy;
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
	
	public void setGeneralMgr(String firstName, String lastName) {
		setGeneralMgr(String.format("%s %s", firstName, lastName));
	}

	public void setGeneralMgrPhone(String phone, String ext) {
		if (StringUtils.isNotBlank(ext)) {
			setGeneralMgrPhone(String.format("%s x%s", phone, ext));
		} else {
			setGeneralMgrPhone(phone);
		}
	}

}
