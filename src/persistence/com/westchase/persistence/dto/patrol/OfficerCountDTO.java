package com.westchase.persistence.dto.patrol;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class OfficerCountDTO implements Serializable {
	
	private Integer officerId;
	
	private String officerName;
	
	private Integer itemId;
	
	private String itemName;
	
	private Long officerItemTotal;
	
	private List<String> officerItemIdList;
	
	private Long officerTotal;
	
	private Long itemTotal;

	public OfficerCountDTO() {
		super();
	}

	public OfficerCountDTO(Integer officerId, String officerName, Integer itemId, String itemName,
			Long officerItemTotal, String officerItemIds, Long officerTotal, Long itemTotal) {
		this();
		setOfficerId(officerId);
		setOfficerName(officerName);
		setItemId(itemId);
		setItemName(itemName);
		setOfficerItemTotal(officerItemTotal);
		setOfficerItemIdList(officerItemIds);
		setOfficerTotal(officerTotal);
		setItemTotal(itemTotal);
	}
	
	private void setOfficerItemIdList(String officerItemIds) {
		setOfficerItemIdList(Arrays.asList(officerItemIds.split("\\s*,\\s*")));
	}

	public OfficerCountDTO(Long itemTotal) {
		this();
		setItemTotal(itemTotal);
	}

	public Integer getOfficerId() {
		return officerId;
	}

	public void setOfficerId(Integer officerId) {
		this.officerId = officerId;
	}

	public String getOfficerName() {
		return officerName;
	}

	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Long getOfficerItemTotal() {
		return officerItemTotal;
	}

	public void setOfficerItemTotal(Long officerItemTotal) {
		this.officerItemTotal = officerItemTotal;
	}

	public Long getOfficerTotal() {
		return officerTotal;
	}

	public void setOfficerTotal(Long officerTotal) {
		this.officerTotal = officerTotal;
	}

	public Long getItemTotal() {
		return itemTotal;
	}

	public void setItemTotal(Long itemTotal) {
		this.itemTotal = itemTotal;
	}

	public List<String> getOfficerItemIdList() {
		return officerItemIdList;
	}

	public void setOfficerItemIdList(List<String> officerItemIdList) {
		this.officerItemIdList = officerItemIdList;
	}

	
}
