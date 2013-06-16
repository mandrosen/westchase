package com.westchase.persistence.dto.patrol;

import com.westchase.persistence.model.Officer;

public class OfficerCountDTO<E> {
	
	private Officer officer;
	
	private E type;
	
	private Long count;

	public OfficerCountDTO(Officer officer, E type, Long count) {
		super();
		setOfficer(officer);
		setType(type);
		setCount(count);
	}

	public Officer getOfficer() {
		return officer;
	}

	public void setOfficer(Officer officer) {
		this.officer = officer;
	}

	public E getType() {
		return type;
	}

	public void setType(E type) {
		this.type = type;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
}
