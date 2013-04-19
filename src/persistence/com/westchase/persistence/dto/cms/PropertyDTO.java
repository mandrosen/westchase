package com.westchase.persistence.dto.cms;

import org.apache.commons.lang.StringUtils;


public class PropertyDTO {

	private Integer id;
	
	private String buildingName;
	
	private String hcad;
	
	private String geoAddress;
	
	public PropertyDTO() {
		super();
	}
	
	public PropertyDTO(Integer id, String buildingName, String hcad, String geoAddress) {
		this();
		setId(id);
		setBuildingName(buildingName);
		setGeoAddress(geoAddress);
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getHcad() {
		return hcad;
	}

	public void setHcad(String hcad) {
		this.hcad = hcad;
	}

	public String getGeoAddress() {
		return geoAddress;
	}

	public void setGeoAddress(String geoAddress) {
		this.geoAddress = geoAddress;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSummaryString() {
		StringBuffer summary = new StringBuffer();
		summary.append(StringUtils.leftPad(String.valueOf(id), 3, '0')).append(" ");
		if (buildingName != null) {
			summary.append(" [").append(buildingName).append("] ");
		}
		return summary.toString();
	}

	public void setSummaryString(String summaryString) {
	}

	public String getSummaryStringForPublicSafety() {
		StringBuffer summary = new StringBuffer();
		summary.append(buildingName).append(" [").append(StringUtils.leftPad(String.valueOf(id), 3, '0')).append("]");
		return summary.toString();
	}

	public void setSummaryStringForPublicSafety(String summaryString) {
	}

}
