package com.westchase.persistence.model;

// Generated Jun 1, 2011 7:34:49 PM by Hibernate Tools 3.3.0.GA

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * CompanyType generated by hbm2java
 */
@Entity
@Table(name = "company_type")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CompanyType implements java.io.Serializable {

	private Integer id;
	private String name;
	private Set<Company> companies = new HashSet<Company>(0);

	public CompanyType() {
	}

	public CompanyType(int id, String name) {
		this.id = new Integer(id);
		this.name = name;
	}
	
	public CompanyType(String name) {
		this.name = name;
	}

	public CompanyType(String name, Set<Company> companies) {
		this.name = name;
		this.companies = companies;
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

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "companyType")
	public Set<Company> getCompanies() {
		return this.companies;
	}

	public void setCompanies(Set<Company> companies) {
		this.companies = companies;
	}

}
