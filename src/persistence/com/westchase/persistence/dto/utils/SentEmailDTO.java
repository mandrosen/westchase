package com.westchase.persistence.dto.utils;

import com.westchase.persistence.model.EmailTemplate;
import com.westchase.persistence.model.PhoneBook;
import com.westchase.persistence.model.SentEmail;

/**
 * @author marc
 *
 */
public class SentEmailDTO {
	private SentEmail sentEmail;
	private EmailTemplate emailTemplate;
	private PhoneBook phoneBook;
	
	public SentEmailDTO() {
		super();
	}
	
	public SentEmailDTO(SentEmail sentEmail, EmailTemplate emailTemplate, PhoneBook phoneBook) {
		this();
		this.sentEmail = sentEmail;
		this.emailTemplate = emailTemplate;
		this.phoneBook = phoneBook;
	}

	public SentEmail getSentEmail() {
		return sentEmail;
	}

	public void setSentEmail(SentEmail sentEmail) {
		this.sentEmail = sentEmail;
	}

	public EmailTemplate getEmailTemplate() {
		return emailTemplate;
	}

	public void setEmailTemplate(EmailTemplate emailTemplate) {
		this.emailTemplate = emailTemplate;
	}

	public PhoneBook getPhoneBook() {
		return phoneBook;
	}

	public void setPhoneBook(PhoneBook phoneBook) {
		this.phoneBook = phoneBook;
	}
	
}
