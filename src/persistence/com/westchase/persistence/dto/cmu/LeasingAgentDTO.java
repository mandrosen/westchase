package com.westchase.persistence.dto.cmu;

import java.io.Serializable;

public class LeasingAgentDTO implements Serializable {
	
	private String name;
	private String company;
	private String email;
	private String phone;
	private String fax;

	public LeasingAgentDTO() {
		super();
	}
	
	public LeasingAgentDTO(String name, String company, String email, String phone, String fax) {
		this();
		setName(name);
		setCompany(company);
		setEmail(email);
		setPhone(phone);
		setFax(fax);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
