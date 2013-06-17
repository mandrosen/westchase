package com.westchase.persistence.dto.patrol;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.westchase.persistence.model.IdNamed;
import com.westchase.persistence.model.Officer;

public class OfficerListCountListDTO<T1 extends IdNamed> implements Serializable {

	private List<Officer> officerList;
	
	private List<T1> itemList;
	
	private Map<String, OfficerCountDTO> officerCounts;
	
	public OfficerListCountListDTO() {
		super();
	}
	
	public OfficerListCountListDTO(List<Officer> officerList, List<T1> itemList, Map<String, OfficerCountDTO> officerCounts) {
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

	public List<T1> getItemList() {
		return itemList;
	}

	public void setItemList(List<T1> itemList) {
		this.itemList = itemList;
	}

	public Map<String, OfficerCountDTO> getOfficerCounts() {
		return officerCounts;
	}

	public void setOfficerCounts(Map<String, OfficerCountDTO> officerCounts) {
		this.officerCounts = officerCounts;
	}

}
