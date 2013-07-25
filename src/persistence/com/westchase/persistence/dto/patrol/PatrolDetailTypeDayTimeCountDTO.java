package com.westchase.persistence.dto.patrol;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class PatrolDetailTypeDayTimeCountDTO implements Serializable {
	
	private List<String> detailIdList;

	private String typeName;
	
	private Integer propertyId;
	
	private String propertyName;
	
	private String dayName;
	
	private Integer hourOfDay;
	
	private Long typeTotal;
	
	public PatrolDetailTypeDayTimeCountDTO() {
		super();
	}
	
	public PatrolDetailTypeDayTimeCountDTO(String idList, String typeName, Integer propertyId, String propertyName, String dayName, Integer hourOfDay, Long typeTotal) {
		this();
		setDetailIdList(idList);
		setTypeName(typeName);
		setPropertyId(propertyId);
		setPropertyName(propertyName);
		setDayName(dayName);
		setHourOfDay(hourOfDay);
		setTypeTotal(typeTotal);
	}

	private void setDetailIdList(String idList) {
		setDetailIdList(Arrays.asList(idList.split("\\s*,\\s*")));
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

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public List<String> getDetailIdList() {
		return detailIdList;
	}

	public void setDetailIdList(List<String> detailIdList) {
		this.detailIdList = detailIdList;
	}

}
