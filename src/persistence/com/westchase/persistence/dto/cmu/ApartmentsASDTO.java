package com.westchase.persistence.dto.cmu;

import com.westchase.persistence.model.Company;
import com.westchase.persistence.model.PhoneBook;
import com.westchase.persistence.model.Property;

/**
 * @author marc
 *
 */
//TODO: remove?
public class ApartmentsASDTO {

	private Integer mapNo; // tblProperty.MapNo, 
	private String buildingName; // tblProperty.BuildingName,
	private String companyName; //tblCompany.Company, 
	private Integer noUnits; //tblProperty.NoUnits, 
	private Double occRate; //tblProperty.OccupancyRate, 
	private Integer vacantUnits; //tblProperty.VacantUnits, 
	private String firstName; //tblPhoneBook.FirstName, 
	private String lastName; // tblPhoneBook.LastName, 
	private String owner; //tblProperty.Owner, 
	private String categoryCode; //tblPhonebookCategory.CategoryCode, 
	private String businessType; //tblProperty.BusinessType
	
	public ApartmentsASDTO() {
		super();
	}
	
	public ApartmentsASDTO(Property property, Company company, PhoneBook phoneBook, String categoryCode) {
		this();
		if (property != null) {
			setMapNo(property.getId());
			setBuildingName(property.getBuildingName());
			setNoUnits(property.getNoUnits());
			setOccRate(property.getOccupancyRate());
			setVacantUnits(property.getVacantUnits());
			setOwner(property.getOwner());
			setBusinessType(property.getBusinessType());
		}
		if (company != null) {
			setCompanyName(company.getCompany());
		}
		if (phoneBook != null) {
			setFirstName(phoneBook.getFirstName());
			setLastName(phoneBook.getLastName());
		}
		setCategoryCode(categoryCode);
	}

	public Integer getMapNo() {
		return mapNo;
	}

	public void setMapNo(Integer mapNo) {
		this.mapNo = mapNo;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getNoUnits() {
		return noUnits;
	}

	public void setNoUnits(Integer noUnits) {
		this.noUnits = noUnits;
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

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
}
