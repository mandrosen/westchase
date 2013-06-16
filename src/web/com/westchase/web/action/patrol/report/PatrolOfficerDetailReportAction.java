package com.westchase.web.action.patrol.report;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.westchase.ejb.PatrolService;
import com.westchase.persistence.dto.patrol.OfficerListCountListDTO;
import com.westchase.persistence.dto.patrol.PatrolActivityReportDTO;
import com.westchase.persistence.model.Officer;
import com.westchase.utils.DateUtils;
import com.westchase.utils.ejb.ServiceLocator;
import com.westchase.web.action.report.AbstractReportAction;

public class PatrolOfficerDetailReportAction extends AbstractReportAction {

	private List<Officer> availableOfficers;

	private List<Integer> officerIdList;
	private String startDate;
	private String endDate;
	
	private int reportType;
	
	private OfficerListCountListDTO results;

	public PatrolOfficerDetailReportAction() {
		super();
	}
	
	private void prepareLists() {
		PatrolService patrolServ = ServiceLocator.lookupPatrolService();
		if (patrolServ != null) {
			availableOfficers = patrolServ.listOfficers();
		}
	}
	
	public String input() {
		prepareLists();
		return SUCCESS;
	}
	
	@Override
	public String execute() {
		results = new OfficerListCountListDTO();
		PatrolService patrolServ = ServiceLocator.lookupPatrolService();
		if (patrolServ != null) {
			prepareLists();
			
			if (reportType == 1) {
				results = patrolServ.runOfficerDetailCategoryReport(officerIdList, DateUtils.getDateFromWeb(startDate), DateUtils.getDateFromWeb(endDate));
			} else {
				results = patrolServ.runOfficerDetailTypeReport(officerIdList, DateUtils.getDateFromWeb(startDate), DateUtils.getDateFromWeb(endDate));
			}
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
		// TODO Auto-generated method stub
		return null;
	}

	public List<Officer> getAvailableOfficers() {
		return availableOfficers;
	}

	public void setAvailableOfficers(List<Officer> availableOfficers) {
		this.availableOfficers = availableOfficers;
	}

	public List<Integer> getOfficerIdList() {
		return officerIdList;
	}

	public void setOfficerIdList(List<Integer> officerIdList) {
		this.officerIdList = officerIdList;
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

	public int getReportType() {
		return reportType;
	}

	public void setReportType(int reportType) {
		this.reportType = reportType;
	}

	public OfficerListCountListDTO getResults() {
		return results;
	}

	public void setResults(OfficerListCountListDTO results) {
		this.results = results;
	}

}
