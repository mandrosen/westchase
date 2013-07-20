package com.westchase.persistence.dto.patrol;

import java.io.Serializable;

public class PatrolDetailTypeDayTimeCountDTO implements Serializable {

	private String typeName;
	
	private String dayName;
	
	private Integer hourOfDay;
	
	private Long typeTotal;
	
	public PatrolDetailTypeDayTimeCountDTO() {
		super();
	}
	
	public PatrolDetailTypeDayTimeCountDTO(String typeName, String dayName, Integer hourOfDay, Long typeTotal) {
		this();
		setTypeName(typeName);
		setDayName(dayName);
		setHourOfDay(hourOfDay);
		setTypeTotal(typeTotal);
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDayName() {
		return dayName;
	}

	public void setDayName(String dayName) {
		this.dayName = dayName;
	}

	public Integer getHourOfDay() {
		return hourOfDay;
	}

	public void setHourOfDay(Integer hourOfDay) {
		this.hourOfDay = hourOfDay;
	}

	public Long getTypeTotal() {
		return typeTotal;
	}

	public void setTypeTotal(Long typeTotal) {
		this.typeTotal = typeTotal;
	}

}
