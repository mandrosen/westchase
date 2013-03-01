package com.westchase.persistence.dto.report;

import com.westchase.persistence.model.PhoneBook;
import com.westchase.persistence.model.Property;

/**
 * @author marc
 *
 */
public class PhoneBookPropertyDTO {
	private PhoneBook phoneBook;
	private Property property;
	
	public PhoneBookPropertyDTO() {
		super();
	}
	
	public PhoneBookPropertyDTO(PhoneBook phoneBook, Property property) {
		this();
		this.phoneBook = phoneBook;
		this.property = property;
	}

	public PhoneBook getPhoneBook() {
		return phoneBook;
	}

	public void setPhoneBook(PhoneBook phoneBook) {
		this.phoneBook = phoneBook;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}
}
