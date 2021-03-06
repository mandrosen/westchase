package com.westchase.persistence.model;

// Generated Oct 20, 2009 6:00:51 PM by Hibernate Tools 3.2.4.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Employee generated by hbm2java
 */
@Entity
@Table(name = "employee")
public class Employee implements java.io.Serializable {

	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String ext;
	private Date lastLogin;
	private Set<Wcuser> wcusers = new HashSet<Wcuser>(0);
	private Set<Audit> audits = new HashSet<Audit>(0);
	private Set<Message> messages = new HashSet<Message>(0);
	private Set<EmailTemplate> emailTemplates = new HashSet<EmailTemplate>(0);
	private Set<Todo> todos = new HashSet<Todo>(0);

	public Employee() {
	}

	public Employee(int id) {
		this(new Integer(id));
	}

	public Employee(Integer id) {
		this.id = id;
	}

	public Employee(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Employee(String firstName, String lastName, String email, String phone, String ext, Date lastLogin,
			Set<Wcuser> wcusers, Set<Audit> audits, Set<Message> messages, Set<EmailTemplate> emailTemplates,
			Set<Todo> todos) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.ext = ext;
		this.lastLogin = lastLogin;
		this.wcusers = wcusers;
		this.audits = audits;
		this.messages = messages;
		this.emailTemplates = emailTemplates;
		this.todos = todos;
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

	@Column(name = "first_name", nullable = false, length = 50)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", nullable = false, length = 100)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "email", nullable = false)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "phone", length = 10)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "ext", length = 4)
	public String getExt() {
		return this.ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login", length = 19)
	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "employee")
	public Set<Wcuser> getWcusers() {
		return this.wcusers;
	}

	public void setWcusers(Set<Wcuser> wcusers) {
		this.wcusers = wcusers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
	public Set<Audit> getAudits() {
		return this.audits;
	}

	public void setAudits(Set<Audit> audits) {
		this.audits = audits;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
	public Set<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
	public Set<EmailTemplate> getEmailTemplates() {
		return this.emailTemplates;
	}

	public void setEmailTemplates(Set<EmailTemplate> emailTemplates) {
		this.emailTemplates = emailTemplates;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
	public Set<Todo> getTodos() {
		return this.todos;
	}

	public void setTodos(Set<Todo> todos) {
		this.todos = todos;
	}

}
