package com.westchase.web.action.patrol.report;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.westchase.ejb.PatrolService;
import com.westchase.persistence.dto.patrol.PatrolActivityReportDTO;
import com.westchase.persistence.model.Officer;
import com.westchase.persistence.model.PatrolType;
import com.westchase.utils.DateUtils;
import com.westchase.utils.ejb.ServiceLocator;
import com.westchase.web.action.report.AbstractReportAction;

public class PatrolActivityScoreReportAction extends AbstractReportAction {
	
	private List<Officer> availableOfficers;
	private List<PatrolType> availablePatrolTypes;
	
	private List<Integer> officerIdList;
	private List<Integer> patrolTypeIdList;
	private String startDate;
	private String endDate;

	private List<PatrolActivityReportDTO> results;

	public PatrolActivityScoreReportAction() {
		super();
	}
	
	private void prepareLists() {
		PatrolService patrolServ = ServiceLocator.lookupPatrolService();
		if (patrolServ != null) {
			availableOfficers = patrolServ.listOfficers();
			availablePatrolTypes = patrolServ.listPatrolTypes();
		}
	}
	
	public String input() {
		prepareLists();
		return SUCCESS;
	}
	
	@Override
	public String execute() {
		results = new ArrayList<PatrolActivityReportDTO>();
		PatrolService patrolServ = ServiceLocator.lookupPatrolService();
		if (patrolServ != null) {
			prepareLists();
			
			results = patrolServ.runScoreReport(officerIdList, DateUtils.getDateFromWeb(startDate), DateUtils.getDateFromWeb(endDate), getPatrolTypeIdList());
			if (EXCEL.equals(type)) {
				return exportToExcel();
			}
			if (EMAIL.equals(type)) {
				return sendEmail();
			}
			setType(null);
		}     
		return SUCCESS;	
	}

	@Override
	protected ByteArrayOutputStream createWorkbook() {
		return null;
	}

	public List<Officer> getAvailableOfficers() {
		return availableOfficers;
	}

	public void setAvailableOfficers(List<Officer> availableOfficers) {
		this.availableOfficers = availableOfficers;
	}

	public List<PatrolType> getAvailablePatrolTypes() {
		return availablePatrolTypes;
	}

	public void setAvailablePatrolTypes(List<PatrolType> availablePatrolTypes) {
		this.availablePatrolTypes = availablePatrolTypes;
	}

	public List<Integer> getOfficerIdList() {
		return officerIdList;
	}

	public void setOfficerIdList(List<Integer> officerIdList) {
		this.officerIdList = officerIdList;
	}

	public List<Integer> getPatrolTypeIdList() {
		return patrolTypeIdList;
	}

	public void setPatrolTypeIdList(List<Integer> patrolTypeIdList) {
		this.patrolTypeIdList = patrolTypeIdList;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<PatrolActivityReportDTO> getResults() {
		return results;
	}

	public void setResults(List<PatrolActivityReportDTO> results) {
		this.results = results;
	}

}
