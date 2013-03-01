package com.westchase.persistence.dto.report;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.westchase.persistence.model.Company;
import com.westchase.persistence.model.PhoneBook;
import com.westchase.persistence.model.Property;

/**
 * @author marc
 *
 */
public class ContactDTO implements Serializable {
	private Integer id;
	private String email;
	private String lastName;
	private String firstName;
	private String salutation;
	private String company;
	private Integer personId;
	
	private String categoryCode;
	private Integer propertyId;
	private String mapNo;
	private String title;
	private String jobTitle;
	private String stNumber;
	private String stAddress;
	private String roomNo;
	private String city;
	private String state; 
	private String zipCode; 
	
	private String classification;
	private String wkPhone;
	private String mobilePhone;
	private String faxPhone;
	private String naics;
	
	private String buildingName;
	private String geoNumber;
	private String geoAddress;

	private String noEmployees;
                                                                                                                                                                                                                                                                                                              
	                                                                                                                                                                                                                                                                                                               
	/**
	 * Email Contact constructor
	 */
	public ContactDTO(String email, String title, String lastName, String firstName, String salutation, String company,
			Integer personId, String categoryCode) {
		super();
		this.email = email;
		this.title = title;
		this.lastName = lastName;
		this.firstName = firstName;
		this.salutation = salutation;
		this.company = company;
		this.personId = personId;
		this.categoryCode = categoryCode;
	}
	
	public ContactDTO(Object categoryCodes, PhoneBook phoneBook, Company company) {
		this(null, phoneBook, company, null, null);
		if (categoryCodes != null) {
			setCategoryCode(categoryCodes.toString());
		}
//		if (categoryCodes != null && categoryCodes.length > 0) {
//			if (categoryCodes.length == 1) {
//				setCategoryCode(categoryCodes[0]);
//			} else {
//				StringBuffer codes = new StringBuffer();
//				for (String code : categoryCodes) {
//					codes.append(code).append(",");
//				}
//				setCategoryCode(codes.toString());
//			}
//		}
	}
	
	/**
	 * Regular Contact constructor
	 */
	public ContactDTO(String categoryCode, PhoneBook phoneBook, Company company, Property property, String suiteNumber) {
		super();
		setPhoneBook(phoneBook);
		this.categoryCode = categoryCode;
		this.company = company.getCompany();
		
		// if property address is available, use that one
		if (property != null) {
			this.mapNo = String.valueOf(property.getId());
			
			this.stNumber = property.getGeoNumber();
			this.stAddress = property.getGeoAddress();
			this.roomNo = suiteNumber;
			this.city = property.getGeoCity();
			this.state = property.getGeoState();
			this.zipCode = property.getGeoZipCode();
		}
		
		// otherwise, use the company address
		if (StringUtils.isBlank(this.stNumber) && StringUtils.isBlank(this.stAddress)) {
			this.stNumber = company.getStNumber();
			this.stAddress = company.getStAddress();
			this.roomNo = company.getRoomNo();
			this.city = company.getCity();
			this.state = company.getState();
			this.zipCode = company.getZipCode();
		}
		this.naics = company.getNaics();
		if (company.getNoEmployees() != null) {
			this.noEmployees = company.getNoEmployees().toString();
		}
	}
	
	public void setPhoneBook(PhoneBook phoneBook) {
		if (phoneBook != null) {
			this.id = phoneBook.getId();
	
			this.salutation = phoneBook.getSalutation();
			this.firstName = phoneBook.getFirstName();
			this.lastName = phoneBook.getLastName();
			this.title = phoneBook.getTitle();
			this.jobTitle = phoneBook.getJobTitle();
			
			this.email = phoneBook.getEmail();
			this.wkPhone = phoneBook.getWkPhone();
			if (StringUtils.isNotBlank(phoneBook.getWkext())) {
				wkPhone += " x" + phoneBook.getWkext();
			}
			this.mobilePhone = phoneBook.getMobilePhone();
			this.faxPhone = phoneBook.getFaxPhone();
		}
	}
	
	/**
	 * Regular Contact constructor
	 */
	public ContactDTO(Integer id, String categoryCode, String salutation, String lastName, String firstName, String title, String jobTitle,
			String company, String stNumber, String stAddress, String roomNo, String city, String state, String zipCode, String email, int dummy) {
		super();
		this.id = id;
		this.categoryCode = categoryCode;
//		this.mapNo = mapNo;
		this.salutation = salutation;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.jobTitle = jobTitle;
		this.company = company;
		this.stNumber = stNumber;
		this.stAddress = stAddress;
		this.roomNo = roomNo;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.email = email;
	}

	/**
	 * Classification/NAICS constructor
	 */
	public ContactDTO(String lastName, String firstName, String title, String jobTitle, String company, String stNumber, 
			String stAddress, String roomNo, String zipCode, String email, String wkPhone, String categoryCode, 
			String classification, String naics) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.title = title;
		this.jobTitle = jobTitle;
		this.company = company;
		this.stNumber = stNumber;
		this.stAddress = stAddress;
		this.roomNo = roomNo;
		this.zipCode = zipCode;
		this.email = email;
		this.wkPhone = wkPhone;
		this.categoryCode = categoryCode;
		this.classification = classification;
		this.naics = naics;
	}
	
	/**
	 * Flag Pole Contact constructor
	 */
	public ContactDTO(Integer mapno, String buildingName, String geoNumber, String geoAddress) {
		super();
		setPropertyId(mapno);
		if (mapno != null) {
			this.mapNo = mapno.toString();
		}
		this.buildingName = buildingName;
		this.geoNumber = geoNumber;
		this.geoAddress = geoAddress;
	}   
	
	/**
	 * Constructor for email blast test contact
	 */
	public ContactDTO(String title, String firstName, String salutation, String lastName, String email, String company) {
		super();
		this.title = title;
		this.firstName = firstName;
		this.salutation = salutation;
		this.lastName = lastName;
		this.email = email;
		this.company = company;
	}
	
	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getMapNo() {
		return mapNo;
	}

	public void setMapNo(String mapNo) {
		this.mapNo = mapNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
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
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSalutation() {
		return salutation;
	}
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}


	public String getClassification() {
		return classification;
	}


	public void setClassification(String classification) {
		this.classification = classification;
	}


	public String getWkPhone() {
		return wkPhone;
	}


	public void setWkPhone(String wkPhone) {
		this.wkPhone = wkPhone;
	}


	public String getNaics() {
		return naics;
	}


	public void setNaics(String naics) {
		this.naics = naics;
	}


	public String getBuildingName() {
		return buildingName;
	}


	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}


	public String getGeoNumber() {
		return geoNumber;
	}


	public void setGeoNumber(String geoNumber) {
		this.geoNumber = geoNumber;
	}


	public String getGeoAddress() {
		return geoAddress;
	}


	public void setGeoAddress(String geoAddress) {
		this.geoAddress = geoAddress;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getNoEmployees() {
		return noEmployees;
	}

	public void setNoEmployees(String noEmployees) {
		this.noEmployees = noEmployees;
	}

	public String getFaxPhone() {
		return faxPhone;
	}

	public void setFaxPhone(String faxPhone) {
		this.faxPhone = faxPhone;
	}

	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}
	
}
