package com.westchase.persistence.model;

// Generated Sep 2, 2009 7:10:22 PM by Hibernate Tools 3.2.4.GA

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
 * Audit generated by hbm2java
 */
@Entity
@Table(name = "audit")
public class Audit implements java.io.Serializable {

	private Long id;
	private Employee employee;
	private String object;
	private Long objectId;
	private Date updateDate;
	private String updateDescription;

	public Audit() {
	}

	public Audit(Employee employee, String object, Integer objectId, Date updateDate, String updateDescription) {
		this.employee = employee;
		this.object = object;
		if (objectId != null) this.objectId = new Long(objectId);
		this.updateDate = updateDate;
		this.updateDescription = updateDescription;
	}

	public Audit(Employee employee, String object, Long objectId, Date updateDate, String updateDescription) {
		this.employee = employee;
		this.object = object;
		this.objectId = objectId;
		this.updateDate = updateDate;
		this.updateDescription = updateDescription;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee", nullable = false)
	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Column(name = "object", nullable = false)
	public String getObject() {
		return this.object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	@Column(name = "object_id", nullable = false)
	public Long getObjectId() {
		return this.objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date", nullable = false, length = 19)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Column(name = "update_description", nullable = false)
	public String getUpdateDescription() {
		return this.updateDescription;
	}

	public void setUpdateDescription(String updateDescription) {
		this.updateDescription = updateDescription;
	}

}
