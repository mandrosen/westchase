package com.westchase.persistence.model;

// Generated Sep 16, 2009 6:21:51 PM by Hibernate Tools 3.2.4.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CompanyMapno generated by hbm2java
 */
@Entity
@Table(name = "company_mapno")
public class CompanyMapno implements java.io.Serializable {

	private Integer id;
	private Property property;
	private Company company;
	private boolean primary;

	public CompanyMapno() {
	}


	public CompanyMapno(Integer id) {
		this();
		this.id = id;
	}
	
	public CompanyMapno(Company company, boolean primary) {
		this.company = company;
		this.primary = primary;
	}

	public CompanyMapno(Property property, Company company, boolean primary) {
		this.property = property;
		this.company = company;
		this.primary = primary;
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
	@JoinColumn(name = "MapNo")
	public Property getProperty() {
		return this.property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CompanyID", nullable = false)
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Column(name = "`primary`", nullable = false)
	public boolean isPrimary() {
		return this.primary;
	}

	public void setPrimary(boolean primary) {
		this.primary = primary;
	}

}
