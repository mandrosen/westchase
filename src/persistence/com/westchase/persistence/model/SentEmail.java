package com.westchase.persistence.model;

// Generated Oct 6, 2009 7:26:21 PM by Hibernate Tools 3.2.4.GA

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SentEmail generated by hbm2java
 */
@Entity
@Table(name = "sent_email")
public class SentEmail implements java.io.Serializable {

	private Integer id;
	private PhoneBook phoneBook;
	private EmailTemplate emailTemplate;
	private String fromAddress;
	private String toAddress;
	private String subject;
	private String message;
	private byte[] attachment;
	private String attachmentName;
	private String messageId;
	private Date sentDate;
	private Employee employee;

	public SentEmail() {
	}

	public SentEmail(PhoneBook phoneBook, EmailTemplate emailTemplate, String fromAddress, String toAddress,
			String subject, String message, byte[] attachment, String attachmentName, String messageId, Date sentDate, Employee employee) {
		this.phoneBook = phoneBook;
		this.emailTemplate = emailTemplate;
		this.fromAddress = fromAddress;
		this.toAddress = toAddress;
		this.subject = subject;
		this.message = message;
		this.attachment = attachment;
		this.attachmentName = attachmentName;
		this.messageId = messageId;
		this.sentDate = sentDate;
		this.employee = employee;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "phone_book", nullable = true)
	public PhoneBook getPhoneBook() {
		return this.phoneBook;
	}

	public void setPhoneBook(PhoneBook phoneBook) {
		this.phoneBook = phoneBook;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "message_template", nullable = true)
	public EmailTemplate getEmailTemplate() {
		return this.emailTemplate;
	}

	public void setEmailTemplate(EmailTemplate emailTemplate) {
		this.emailTemplate = emailTemplate;
	}

	@Column(name = "from_address", nullable = false)
	public String getFromAddress() {
		return this.fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	@Column(name = "to_address", nullable = false)
	public String getToAddress() {
		return this.toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	@Column(name = "subject", nullable = false, length = 1000)
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(name = "message", nullable = false)
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column(name = "attachment", nullable = true)
	public byte[] getAttachment() {
		return this.attachment;
	}

	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}

	@Column(name = "message_id", nullable = false, length = 100)
	public String getMessageId() {
		return this.messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sent_date", nullable = false, length = 19)
	public Date getSentDate() {
		return this.sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee", nullable = false)
	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Column(name = "attachment_name", nullable = true, length = 255)
	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

}
