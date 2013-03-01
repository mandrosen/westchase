package com.westchase.persistence.dto.cms;

import com.westchase.persistence.model.Property;

/**
 * @author marc
 *
 */
public class PhoneBookPropertyDTO {
	private Property property;
	private String suiteNumber;
	private boolean selected;
	public PhoneBookPropertyDTO() {
		super();
	}
	public PhoneBookPropertyDTO(Property property) {
		this();
		this.property = property;
	}
	public PhoneBookPropertyDTO(Property property, String suiteNumber) {
		this();
		this.property = property;
		this.suiteNumber = suiteNumber;
		this.selected = true;
	}
	public Property getProperty() {
		return property;
	}
	public void setProperty(Property property) {
		this.property = property;
	}
	public String getSuiteNumber() {
		return suiteNumber;
	}
	public void setSuiteNumber(String suiteNumber) {
		this.suiteNumber = suiteNumber;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
