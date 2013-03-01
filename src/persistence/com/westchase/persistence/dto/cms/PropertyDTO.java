package com.westchase.persistence.dto.cms;

import org.apache.commons.lang.StringUtils;


public class PropertyDTO {

	private Integer id;
	
	private String summaryString;
	
	public PropertyDTO() {
		super();
	}
	
	public PropertyDTO(Integer id, String buildingName, String hcad, String geoAddress) {
		this();
		setId(id);
		StringBuffer summary = new StringBuffer();
		summary.append(StringUtils.leftPad(String.valueOf(id), 3, '0')).append(" ");
		if (buildingName != null) {
			summary.append(" [").append(buildingName).append("] ");
		}
		setSummaryString(summary.toString());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSummaryString() {
		return summaryString;
	}

	public void setSummaryString(String summaryString) {
		this.summaryString = summaryString;
	}

}
