package com.westchase.persistence.model;

// Generated Sep 6, 2009 7:24:14 PM by Hibernate Tools 3.2.4.GA

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Wcuser generated by hbm2java
 */
@Entity
@Table(name = "wcuser")
public class Wcuser implements java.io.Serializable {

	private Integer id;
	private Employee employee;
	private String username;
	private String password;
	private boolean disabled;
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);

	public Wcuser() {
	}

	public Wcuser(Employee employee, String username, String password, boolean disabled) {
		this.employee = employee;
		this.username = username;
		this.password = password;
		this.disabled = disabled;
	}

	public Wcuser(Employee employee, String username, String password, boolean disabled, Set<UserRole> userRoles) {
		this.employee = employee;
		this.username = username;
		this.password = password;
		this.disabled = disabled;
		this.userRoles = userRoles;
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
	@JoinColumn(name = "employee", nullable = false)
	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Column(name = "username", nullable = false, length = 100)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "disabled", nullable = false)
	public boolean isDisabled() {
		return this.disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "wcuser")
	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

}
