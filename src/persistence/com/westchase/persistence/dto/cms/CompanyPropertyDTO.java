package com.westchase.persistence.dto.cms;

import com.westchase.persistence.model.Property;

/**
 * @author marc
 *
 */
public class CompanyPropertyDTO {
	private Integer id;
	private Property property;
	private boolean primary;
	public CompanyPropertyDTO() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Property getProperty() {
		return property;
	}
	public void setProperty(Property property) {
		this.property = property;
	}
	public boolean isPrimary() {
		return primary;
	}
	public void setPrimary(boolean primary) {
		this.primary = primary;
	}
	public CompanyPropertyDTO(Integer id, Property property, boolean primary) {
		this();
		this.id = id;
		this.property = property;
		this.primary = primary;
	}
}
