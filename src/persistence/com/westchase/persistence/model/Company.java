package com.westchase.persistence.model;

// Generated Oct 25, 2009 1:51:28 PM by Hibernate Tools 3.2.4.GA

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

/**
 * Company generated by hbm2java
 */
@Entity
@Table(name = "company")
public class Company implements java.io.Serializable {

	private Integer id;
	private CompanyType companyType;
	private String company;
	private String stNumber;
	private String stAddress;
	private String roomNo;
	private String city;
	private String state;
	private String zipCode;
	private String wkPhone;
	private String faxPhone;
	private String latitude;
	private String longitude;
	private Integer noEmployees;
	private String classification;
	private String subClassification;
	private String naics;
	private String hcad;
	private String mapNo;
	private String other;
	private String owner;
	private boolean vendor;
	private String center;
	private Integer squareFeet;
	private Date inputDate;
	private Date lastUpdate;
	private String website;
	private String notes;
	private Set<CompanyMapno> companyMapnos = new HashSet<CompanyMapno>(0);
	private Set<PhoneBook> phoneBooks = new HashSet<PhoneBook>(0);
	private Set<AuditCompany> auditCompanies = new HashSet<AuditCompany>(0);

	public Company() {
	}
	
	public Company(Integer id) {
		this();
		this.id = id;
	}

	public Company(boolean vendor, Date lastUpdate) {
		this.vendor = vendor;
		this.lastUpdate = lastUpdate;
	}

	public Company(CompanyType companyType, String company, String stNumber, String stAddress, String roomNo, String city, String state,
			String zipCode, String wkPhone, String faxPhone, String latitude,
			String longitude, Integer noEmployees, String classification,
			String subClassification, String naics, String hcad, String mapNo, String other, String owner,
			boolean vendor, String center, Integer squareFeet, Date inputDate, Date lastUpdate, String website,
			String notes, Set<CompanyMapno> companyMapnos, Set<PhoneBook> phoneBooks, Set<AuditCompany> auditCompanies) {
		this.companyType = companyType;
		this.company = company;
		this.stNumber = stNumber;
		this.stAddress = stAddress;
		this.roomNo = roomNo;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.wkPhone = wkPhone;
		this.faxPhone = faxPhone;
		this.latitude = latitude;
		this.longitude = longitude;
		this.noEmployees = noEmployees;
		this.classification = classification;
		this.subClassification = subClassification;
		this.naics = naics;
		this.hcad = hcad;
		this.mapNo = mapNo;
		this.other = other;
		this.owner = owner;
		this.vendor = vendor;
		this.center = center;
		this.squareFeet = squareFeet;
		this.inputDate = inputDate;
		this.lastUpdate = lastUpdate;
		this.website = website;
		this.notes = notes;
		this.companyMapnos = companyMapnos;
		this.phoneBooks = phoneBooks;
		this.auditCompanies = auditCompanies;
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
	@JoinColumn(name = "CompanyType")
	public CompanyType getCompanyType() {
		return this.companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}

	@Column(name = "Company", length = 100)
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "StNumber", length = 50)
	public String getStNumber() {
		return this.stNumber;
	}

	public void setStNumber(String stNumber) {
		this.stNumber = stNumber;
	}

	@Column(name = "StAddress", length = 150)
	public String getStAddress() {
		return this.stAddress;
	}

	public void setStAddress(String stAddress) {
		this.stAddress = stAddress;
	}

	@Column(name = "RoomNo", length = 50)
	public String getRoomNo() {
		return this.roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	@Column(name = "City", length = 100)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "State", length = 2)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "ZipCode", length = 10)
	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Column(name = "WkPhone", length = 15)
	public String getWkPhone() {
		return this.wkPhone;
	}

	public void setWkPhone(String wkPhone) {
		this.wkPhone = wkPhone;
	}

	@Column(name = "FaxPhone", length = 15)
	public String getFaxPhone() {
		return this.faxPhone;
	}

	public void setFaxPhone(String faxPhone) {
		this.faxPhone = faxPhone;
	}

	@Column(name = "Latitude", length = 20)
	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Column(name = "Longitude", length = 20)
	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Column(name = "NoEmployees")
	public Integer getNoEmployees() {
		return this.noEmployees;
	}

	public void setNoEmployees(Integer noEmployees) {
		this.noEmployees = noEmployees;
	}

	@Column(name = "Classification", length = 50)
	public String getClassification() {
		return this.classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	@Column(name = "SubClassification", length = 50)
	public String getSubClassification() {
		return this.subClassification;
	}

	public void setSubClassification(String subClassification) {
		this.subClassification = subClassification;
	}

	@Column(name = "NAICS", length = 5)
	public String getNaics() {
		return this.naics;
	}

	public void setNaics(String naics) {
		this.naics = naics;
	}

	@Column(name = "HCAD", length = 50)
	public String getHcad() {
		return this.hcad;
	}

	public void setHcad(String hcad) {
		this.hcad = hcad;
	}

	@Column(name = "MapNo", length = 50)
	public String getMapNo() {
		return this.mapNo;
	}

	public void setMapNo(String mapNo) {
		this.mapNo = mapNo;
	}

	@Column(name = "Other", length = 250)
	public String getOther() {
		return this.other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	@Column(name = "Owner", length = 50)
	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Column(name = "Vendor", nullable = false)
	public boolean isVendor() {
		return this.vendor;
	}

	public void setVendor(boolean vendor) {
		this.vendor = vendor;
	}

	@Column(name = "Center", length = 50)
	public String getCenter() {
		return this.center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	@Column(name = "SquareFeet")
	public Integer getSquareFeet() {
		return this.squareFeet;
	}

	public void setSquareFeet(Integer squareFeet) {
		this.squareFeet = squareFeet;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "InputDate", length = 19)
	public Date getInputDate() {
		return this.inputDate;
	}

	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastupdate", nullable = false, length = 19)
	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Column(name = "Website")
	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Column(name = "Notes")
	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public Set<CompanyMapno> getCompanyMapnos() {
		return this.companyMapnos;
	}

	public void setCompanyMapnos(Set<CompanyMapno> companyMapnos) {
		this.companyMapnos = companyMapnos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public Set<PhoneBook> getPhoneBooks() {
		return this.phoneBooks;
	}

	public void setPhoneBooks(Set<PhoneBook> phoneBooks) {
		this.phoneBooks = phoneBooks;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public Set<AuditCompany> getAuditCompanies() {
		return this.auditCompanies;
	}

	public void setAuditCompanies(Set<AuditCompany> auditCompanies) {
		this.auditCompanies = auditCompanies;
	}

	@Transient
	public String getCompanyString() {
		String value = this.company + " : " + stNumber + " " + stAddress;
		if (StringUtils.isNotBlank(this.roomNo)) {
			value += " " + this.roomNo;
		}
		return value;
	}
	
}
