package com.westchase.persistence.dto.cmu;

import com.westchase.persistence.model.Company;
import com.westchase.persistence.model.PhoneBook;
import com.westchase.persistence.model.Property;

/**
 * @author marc
 *
 */
// TODO: remove?
public class ApartmentsAMDTO {

	private Integer mapNo; //tblProperty.MapNo, 
	private String companyName; //tblCompany.Company, 
	private String companyStNumber; // tblCompany.StNumber, 
	private String companyStAddress; //tblCompany.StAddress, 
	private String firstName; //tblPhoneBook.FirstName, 
	private String lastName; //tblPhoneBook.LastName, 
	private String wkPhone; //tblPhoneBook.WkPhone, 
	private String faxPhone; //tblPhoneBook.FaxPhone, 
	private String owner; //tblProperty.Owner, 
	private String email; //tblPhoneBook.Email, 
	private Double occRate; //tblProperty.OccupancyRate, 
	private Integer vacantUnits; //tblProperty.VacantUnits, 
	private String categoryCode; //tblPhonebookCategory.CategoryCode
	
	public ApartmentsAMDTO() {
		super();
	}
	
	public ApartmentsAMDTO(Property property, Company company, PhoneBook phoneBook, String categoryCode) {
		this();
		if (property != null) {
			setMapNo(property.getId());
			setOwner(property.getOwner());
			setOccRate(property.getOccupancyRate());
			setVacantUnits(property.getVacantUnits());
		}
		if (company != null) {
			setCompanyName(company.getCompany());
			setCompanyStNumber(company.getStNumber());
			setCompanyStAddress(company.getStAddress());
		}
		if (phoneBook != null) {
			setFirstName(phoneBook.getFirstName());
			setLastName(phoneBook.getLastName());
			setWkPhone(phoneBook.getWkPhone());
			setFaxPhone(phoneBook.getFaxPhone());
			setEmail(phoneBook.getEmail());
		}
		setCategoryCode(categoryCode);
	}

	public Integer getMapNo() {
		return mapNo;
	}

	public void setMapNo(Integer mapNo) {
		this.mapNo = mapNo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyStNumber() {
		return companyStNumber;
	}

	public void setCompanyStNumber(String companyStNumber) {
		this.companyStNumber = companyStNumber;
	}

	public String getCompanyStAddress() {
		return companyStAddress;
	}

	public void setCompanyStAddress(String companyStAddress) {
		this.companyStAddress = companyStAddress;
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

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Double getOccRate() {
		return occRate;
	}

	public void setOccRate(Double occRate) {
		this.occRate = occRate;
	}

	public Integer getVacantUnits() {
		return vacantUnits;
	}

	public void setVacantUnits(Integer vacantUnits) {
		this.vacantUnits = vacantUnits;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	
}
