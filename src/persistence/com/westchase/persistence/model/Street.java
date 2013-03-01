package com.westchase.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.Length;

@Entity
@Table(name = "street")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Street implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	
	public Street() {
		super();
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", length = 255)
	@Length(max = 255)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
