package com.westchase.persistence.dto.patrol;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.westchase.persistence.model.Officer;

public class OfficerListCountListDTO<E, J> implements Serializable {

	private List<Officer> officerList;
	
	private List<E> itemList;
	
	private Map<String, J> officerCounts;
	
	public OfficerListCountListDTO() {
		super();
	}
	
	public OfficerListCountListDTO(List<Officer> officerList, List<E> itemList, Map<String, J> officerCounts) {
		this();
		setOfficerList(officerList);
		setItemList(itemList);
		setOfficerCounts(officerCounts);
	}

	public List<Officer> getOfficerList() {
		return officerList;
	}

	public void setOfficerList(List<Officer> officerList) {
		this.officerList = officerList;
	}

	public List<E> getItemList() {
		return itemList;
	}

	public void setItemList(List<E> itemList) {
		this.itemList = itemList;
	}

	public Map<String, J> getOfficerCounts() {
		return officerCounts;
	}

	public void setOfficerCounts(Map<String, J> officerCounts) {
		this.officerCounts = officerCounts;
	}

}
