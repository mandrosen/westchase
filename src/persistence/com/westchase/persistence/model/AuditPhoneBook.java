package com.westchase.persistence.model;

// Generated Oct 25, 2009 1:51:28 PM by Hibernate Tools 3.2.4.GA

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
 * AuditPhoneBook generated by hbm2java
 */
@Entity
@Table(name = "audit_phone_book")
public class AuditPhoneBook implements java.io.Serializable {

	private Long id;
	private PhoneBook phoneBook;
	private Employee employee;
	private String updateDescription;
	private Date updateDate;

	public AuditPhoneBook() {
	}

	public AuditPhoneBook(PhoneBook phoneBook, Employee employee, String updateDescription, Date updateDate) {
		this.phoneBook = phoneBook;
		this.employee = employee;
		this.updateDescription = updateDescription;
		this.updateDate = updateDate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "phone_book", nullable = false)
	public PhoneBook getPhoneBook() {
		return this.phoneBook;
	}

	public void setPhoneBook(PhoneBook phoneBook) {
		this.phoneBook = phoneBook;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee", nullable = false)
	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Column(name = "update_description", nullable = false)
	public String getUpdateDescription() {
		return this.updateDescription;
	}

	public void setUpdateDescription(String updateDescription) {
		this.updateDescription = updateDescription;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date", nullable = false, length = 19)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
