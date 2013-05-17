package com.westchase.persistence.dto.cms;

import com.westchase.persistence.model.PhoneBook;

public class PhoneBookCategoryDTO {
	
	private PhoneBook phoneBook;
//	private List<Category> categories;
	private String categoriesStr;

	public PhoneBookCategoryDTO() {
		super();
	}

	public PhoneBookCategoryDTO(PhoneBook phoneBook, String categoriesStr) {
		super();
		this.phoneBook = phoneBook;
		this.categoriesStr = categoriesStr;
	}

	public PhoneBook getPhoneBook() {
		return phoneBook;
	}

	public void setPhoneBook(PhoneBook phoneBook) {
		this.phoneBook = phoneBook;
	}

//	public List<Category> getCategories() {
//		return categories;
//	}
//
//	public void setCategories(List<Category> categories) {
//		this.categories = categories;
//	}

	public String getCategoriesStr() {
		return categoriesStr;
	}

	public void setCategoriesStr(String categoriesStr) {
		this.categoriesStr = categoriesStr;
	}

}
