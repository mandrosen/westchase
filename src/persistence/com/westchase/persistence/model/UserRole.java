package com.westchase.persistence.model;

// Generated Sep 6, 2009 7:20:09 PM by Hibernate Tools 3.2.4.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * UserRole generated by hbm2java
 */
@Entity
@Table(name = "user_role")
public class UserRole implements java.io.Serializable {

	private Integer id;
	private Role role;
	private Wcuser wcuser;

	public UserRole() {
	}

	public UserRole(Role role, Wcuser wcuser) {
		this.role = role;
		this.wcuser = wcuser;
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
	@JoinColumn(name = "role", nullable = false)
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user", nullable = false)
	public Wcuser getWcuser() {
		return this.wcuser;
	}

	public void setWcuser(Wcuser wcuser) {
		this.wcuser = wcuser;
	}

}
