package com.westchase.persistence.model;

// Generated Sep 5, 2010 4:55:55 PM by Hibernate Tools 3.3.0.GA

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * CmuQuarter generated by hbm2java
 */
@Entity
@Table(name = "cmu_quarter")
public class CmuQuarter implements java.io.Serializable {

	private Integer id;
	private String description;
	private int year;
	private int quarterNum;
	private Set<CmuApartment> cmuApartments = new HashSet<CmuApartment>(0);
	private Set<CmuLease> cmuLeases = new HashSet<CmuLease>(0);
	private Set<CmuDevsite> cmuDevsites = new HashSet<CmuDevsite>(0);
	private Set<CmuOfficeRetailSvc> cmuOfficeRetailSvcs = new HashSet<CmuOfficeRetailSvc>(0);
	private Set<CmuHotel> cmuHotels = new HashSet<CmuHotel>(0);

	public CmuQuarter() {
	}
	
	public CmuQuarter(int id) {
		this();
		this.id = new Integer(id);
	}

	public CmuQuarter(int year, int quarterNum) {
		this.year = year;
		this.quarterNum = quarterNum;
	}

	public CmuQuarter(String description, int year, int quarterNum, Set<CmuApartment> cmuApartments,
			Set<CmuLease> cmuLeases, Set<CmuDevsite> cmuDevsites, Set<CmuOfficeRetailSvc> cmuOfficeRetailSvcs,
			Set<CmuHotel> cmuHotels) {
		this.description = description;
		this.year = year;
		this.quarterNum = quarterNum;
		this.cmuApartments = cmuApartments;
		this.cmuLeases = cmuLeases;
		this.cmuDevsites = cmuDevsites;
		this.cmuOfficeRetailSvcs = cmuOfficeRetailSvcs;
		this.cmuHotels = cmuHotels;
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

	@Column(name = "description", length = 45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "year", nullable = false)
	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Column(name = "quarter_num", nullable = false)
	public int getQuarterNum() {
		return this.quarterNum;
	}

	public void setQuarterNum(int quarterNum) {
		this.quarterNum = quarterNum;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cmuQuarter")
	public Set<CmuApartment> getCmuApartments() {
		return this.cmuApartments;
	}

	public void setCmuApartments(Set<CmuApartment> cmuApartments) {
		this.cmuApartments = cmuApartments;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cmuQuarter")
	public Set<CmuLease> getCmuLeases() {
		return this.cmuLeases;
	}

	public void setCmuLeases(Set<CmuLease> cmuLeases) {
		this.cmuLeases = cmuLeases;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cmuQuarter")
	public Set<CmuDevsite> getCmuDevsites() {
		return this.cmuDevsites;
	}

	public void setCmuDevsites(Set<CmuDevsite> cmuDevsites) {
		this.cmuDevsites = cmuDevsites;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cmuQuarter")
	public Set<CmuOfficeRetailSvc> getCmuOfficeRetailSvcs() {
		return this.cmuOfficeRetailSvcs;
	}

	public void setCmuOfficeRetailSvcs(Set<CmuOfficeRetailSvc> cmuOfficeRetailSvcs) {
		this.cmuOfficeRetailSvcs = cmuOfficeRetailSvcs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cmuQuarter")
	public Set<CmuHotel> getCmuHotels() {
		return this.cmuHotels;
	}

	public void setCmuHotels(Set<CmuHotel> cmuHotels) {
		this.cmuHotels = cmuHotels;
	}

}
