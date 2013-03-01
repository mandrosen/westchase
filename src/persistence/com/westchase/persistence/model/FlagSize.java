package com.westchase.persistence.model;

// Generated Oct 8, 2012 6:19:31 PM by Hibernate Tools 3.3.0.GA

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
 * FlagSize generated by hbm2java
 */
@Entity
@Table(name = "flag_size")
public class FlagSize implements java.io.Serializable {

	private Integer id;
	private String name;
	private Set<Property> properties = new HashSet<Property>(0);

	public FlagSize() {
	}

	public FlagSize(String name) {
		this.name = name;
	}

	public FlagSize(String name, Set<Property> properties) {
		this.name = name;
		this.properties = properties;
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

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "flagSize")
	public Set<Property> getProperties() {
		return this.properties;
	}

	public void setProperties(Set<Property> properties) {
		this.properties = properties;
	}

}
