package com.westchase.persistence.dto.report;

import java.io.Serializable;

/**
 * @author marc
 *
 */
public class CompanyPhoneBookDTO implements Serializable {
	private String title; //tblPhoneBook.Title, 
	private String firstName; //tblPhoneBook.FirstName, 
	private String middleInitial;
	private String lastName; //tblPhoneBook.LastName, 
	private String salutation;
	private String suffix;
	private String jobTitle; //tblPhoneBook.JobTitle, 
	private String company; //tblPhoneBook.Company, 
	private String stNumber; //tblCompany.StNumber, 
	private String stAddress; //tblCompany.StAddress, 
	private String roomNo; //tblCompany.RoomNo, 
	private String city; //tblCompany.City, 
	private String state; //tblCompany.State, 
	private String zipCode; //tblCompany.ZipCode, 
	private String email; //tblPhoneBook.Email, 
	private String wkPhone; //tblPhoneBook.WkPhone, 
	private String faxPhone; //tblPhoneBook.FaxPhone, 
	private Integer noEmployees;
	private String classification;
	private String subClassification;
	private String naics;
	private String hcad;
	private String other;
	private String owner;
	private String vendor;
	private String center;
	private String sqFeet;
	
	private String mapNo; //tblCompany.MapNo, 
	private String category; //tblPhonebookCategory.CategoryCode
	
	
//	tblCompany.Company, tblPhoneBook.Title, tblPhoneBook.FirstName, tblPhoneBook.[Middle Initial], tblPhoneBook.LastName, 
//	tblPhoneBook.Salutation, tblPhoneBook.Suffix, tblPhoneBook.JobTitle, tblCompany.StNumber, tblCompany.StAddress, 
//	tblCompany.RoomNo, tblCompany.City, tblCompany.State, tblCompany.ZipCode, tblCompany.WkPhone_Area AS Expr1, tblCompany.WkPhone, 
//	tblCompany.FaxPhone_Area AS Expr2, tblCompany.FaxPhone, tblCompany.NoEmployees, tblCompany.Classification,
//	tblCompany.SubClassification, tblCompany.[NAICS (SIC)], tblCompany.HCAD, tblCompany.MapNo, tblCompany.Other, 
//	tblCompany.Owner, tblCompany.Vendor, tblCompany.Center, tblCompany.[Square Feet], tblCompany.InputDate, tblCompany.LastUpdate, 
//	tblCompany.Website, tblPhoneBook.Department, tblPhoneBook.WkPhone, tblPhoneBook.Wkext, tblPhoneBook.FaxPhone, 
//	tblPhoneBook.[Mobile Phone], tblPhoneBook.Email, tblPhoneBook.[Don'tEmail?], tblPhoneBook.HomeAddress, tblPhoneBook.HomePhone, 
//tblPhoneBook.HomeFax, tblPhoneBook.Investor, tblPhoneBook.WestchaseToday, tblPhoneBook.Other, tblPhoneBook.InputDate, 
//tblPhoneBook.LastUpdate, tblPhoneBook.CompanyId, tblPhoneBook.PersonId
	
	public CompanyPhoneBookDTO() {
		super();
	}
	public CompanyPhoneBookDTO(String title, String firstName, String lastName, String jobTitle, String company,
			String stNumber, String stAddress, String roomNo, String city, String state, String zipCode, String email,
			String wkPhone, String faxPhone, String mapNo, String category) {
		super();
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.jobTitle = jobTitle;
		this.company = company;
		this.stNumber = stNumber;
		this.stAddress = stAddress;
		this.roomNo = roomNo;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.email = email;
		this.wkPhone = wkPhone;
		this.faxPhone = faxPhone;
		this.mapNo = mapNo;
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getStNumber() {
		return stNumber;
	}
	public void setStNumber(String stNumber) {
		this.stNumber = stNumber;
	}
	public String getStAddress() {
		return stAddress;
	}
	public void setStAddress(String stAddress) {
		this.stAddress = stAddress;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWkPhone() {
		return wkPhone;
	}
	public void setWkPhone(String wkPhone) {
		this.wkPhone = wkPhone;
	}
	public String getFaxPhone() {
		return faxPhone;
	}
	public void setFaxPhone(String faxPhone) {
		this.faxPhone = faxPhone;
	}
	public String getMapNo() {
		return mapNo;
	}
	public void setMapNo(String mapNo) {
		this.mapNo = mapNo;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
