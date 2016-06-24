package com.westchase.persistence.dto.report;

import java.io.Serializable;

public class PropertyCompanyPhoneBookDTO implements Serializable {

	private static final long serialVersionUID = -9093328127732175906L;
	
	private Integer propertyId;
	private Integer companyId;
	private Integer phoneBookId;
	private String company;
	private String firstName;
	private String lastName;
	private String jobTitle;
	private String address;
	private String roomNo;
	private String city;
	private String state;
	private String zipCode;
	private String wkPhone;
	private String faxPhone;
	private String mobilePhone;
	private String email;
	private String cats;
	
	public PropertyCompanyPhoneBookDTO() {
		super();
	}

	public PropertyCompanyPhoneBookDTO(Integer propertyId, Integer companyId, Integer phoneBookId, String company,
			String firstName, String lastName, String address, String roomNo, String city, String state,
			String zipCode, String wkPhone, String email, String cats) {
		this();
		this.propertyId = propertyId;
		this.companyId = companyId;
		this.phoneBookId = phoneBookId;
		this.company = company;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.roomNo = roomNo;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.wkPhone = wkPhone;
		this.email = email;
		this.cats = cats;
	}
	
	public PropertyCompanyPhoneBookDTO(Object[] result) {
		this((Integer) result[0],
				(Integer) result[1],
				(Integer) result[2],
				(String) result[3],
				(String) result[4],
				(String) result[5],
				(String) result[6],
				(String) result[7],
				(String) result[8],
				(String) result[9],
				(String) result[10],
				(String) result[11],
				(String) result[12],
				(String) result[13]);
		if (result.length > 14) {
			faxPhone = (String) result[14];
			mobilePhone = (String) result[15];
			jobTitle = (String) result[16];
		}
	}

	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getPhoneBookId() {
		return phoneBookId;
	}

	public void setPhoneBookId(Integer phoneBookId) {
		this.phoneBookId = phoneBookId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getWkPhone() {
		return wkPhone;
	}

	public void setWkPhone(String wkPhone) {
		this.wkPhone = wkPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCats() {
		return cats;
	}

	public void setCats(String cats) {
		this.cats = cats;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getFaxPhone() {
		return faxPhone;
	}

	public void setFaxPhone(String faxPhone) {
		this.faxPhone = faxPhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

}
