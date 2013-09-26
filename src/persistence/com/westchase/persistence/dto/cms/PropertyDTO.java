package com.westchase.persistence.dto.cms;

import org.apache.commons.lang.StringUtils;


public class PropertyDTO {

	private Integer id;
	
	private String buildingName;
	
	private String hcad;
	
	private String geoNumber;
	
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
	
	public PropertyDTO(Integer id, String buildingName, String hcad, String geoNumber, String geoAddress) {
		this(id, buildingName, hcad, geoAddress);
		setGeoNumber(geoNumber);
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
		summary.append(StringUtils.leftPad(String.valueOf(id), 4, '0')).append(" - ").append(buildingName);
		return summary.toString();
	}

	public void setSummaryStringForPublicSafety(String summaryString) {
	}

	public String getSummaryStringForPublicSafetyByAddr() {
		StringBuffer summary = new StringBuffer();
		summary.append(geoNumber).append(" ").append(geoAddress).append(" - ").append(StringUtils.leftPad(String.valueOf(id), 4, '0'));
		return summary.toString();
	}

	public void setSummaryStringForPublicSafetyByAddr(String summaryString) {
	}

	public String getGeoNumber() {
		return geoNumber;
	}

	public void setGeoNumber(String geoNumber) {
		this.geoNumber = geoNumber;
	}

}
