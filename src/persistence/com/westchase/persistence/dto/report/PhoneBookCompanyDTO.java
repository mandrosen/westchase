package com.westchase.persistence.dto.report;

import com.westchase.persistence.model.Company;
import com.westchase.persistence.model.PhoneBook;

/**
 * @author marc
 *
 */
public class PhoneBookCompanyDTO {
	private PhoneBook phoneBook;
	private Company company;
	private String categories;
	public PhoneBookCompanyDTO() {
		super();
	}
	public PhoneBookCompanyDTO(PhoneBook phoneBook, Company company) {
		super();
		this.phoneBook = phoneBook;
		this.company = company;
	}
	public PhoneBookCompanyDTO(PhoneBook phoneBook, Company company, String categories) {
		this(phoneBook, company);
		this.categories = categories;
	}
	public PhoneBook getPhoneBook() {
		return phoneBook;
	}
	public void setPhoneBook(PhoneBook phoneBook) {
		this.phoneBook = phoneBook;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public String getCategories() {
		return categories;
	}
	public void setCategories(String categories) {
		this.categories = categories;
	}
}
