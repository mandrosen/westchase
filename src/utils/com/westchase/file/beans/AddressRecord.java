package com.westchase.file.beans;

import org.apache.commons.lang.StringUtils;

/**
 * @author marc
 *
 */
public class AddressRecord implements Comparable<AddressRecord> {
	
	public static final String OWNER = "O";
	public static final String MAIL_TO = "M";
	public static final String ADDRESS = "A";
	public static final String CITY = "C";
	public static final String STATE = "S";
	public static final String ZIP = "Z";
	public static final String FOREIGN_COUNTRY = "F";

	private String accountNumber;
	private String owner;
	private String mailTo;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zip;
	private String foreignCountry;
	
	public AddressRecord() {
		super();
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getForeignCountry() {
		return foreignCountry;
	}

	public void setForeignCountry(String foreignCountry) {
		this.foreignCountry = foreignCountry;
	}
	
	public String getName() {
		String name = StringUtils.defaultIfEmpty(StringUtils.defaultIfEmpty(getOwner(), getMailTo()), getAddress1());
		return name;
	}

	@Override
	public int compareTo(AddressRecord other) {
		if (this == other) return 0;
		return getName().compareTo(other.getName());
	}

}
