package com.westchase.web.action.patrol.report;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.westchase.ejb.PatrolService;
import com.westchase.persistence.dto.patrol.PatrolActivityReportDTO;
import com.westchase.persistence.model.Officer;
import com.westchase.utils.ejb.ServiceLocator;
import com.westchase.web.action.report.AbstractReportAction;

public class PatrolActivityReportAction extends AbstractReportAction {

	private List<Officer> availableOfficers;
	
	private Integer officerId;
	private String startDate;
	private String endDate;
	private List<PatrolActivityReportDTO> results;

	public PatrolActivityReportAction() {
		super();
	}
	
	private Date getDate(String dateStr) {
		Date d = null;
		if (StringUtils.isNotBlank(dateStr)) {
			try {
				d = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
			} catch (Exception e) {
				log.error("bad date: " + dateStr, e);
			}
		}
		return d;
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
		results = new ArrayList<PatrolActivityReportDTO>();
		PatrolService patrolServ = ServiceLocator.lookupPatrolService();
		if (patrolServ != null) {
			prepareLists();
			
			results = patrolServ.runReport(officerId, getDate(startDate), getDate(endDate));
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
		ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
		try {
			Workbook wb = new XSSFWorkbook();
		    Sheet sheet = wb.createSheet("report");

			
			writeTitle(wb, sheet, "Patrol Activity Report");
			
			
			// TODO: these aren't showing up properly
			writeSuperHeaderCell(wb, sheet, "Crime Arrest Activity", HEADER_ROW, 4, 7);
			writeSuperHeaderCell(wb, sheet, "Warrants", HEADER_ROW, 8, 11);
			writeSuperHeaderCell(wb, sheet, "DRT Investigations", HEADER_ROW, 12, 15);
			writeSuperHeaderCell(wb, sheet, "Field Activity", HEADER_ROW, 16, 20);	
			writeSuperHeaderCell(wb, sheet, "Traffic", HEADER_ROW, 21, 22);
			writeSuperHeaderCell(wb, sheet, "Patrol Activity", HEADER_ROW, 23, 28);
			writeSuperHeaderCell(wb, sheet, "Directed Patrol Activity", HEADER_ROW, 29, 34);
			writeSuperHeaderCell(wb, sheet, "Community Services", HEADER_ROW, 35, 43);
			

            String[] headers = {"Officer", 
            		"Duty Hours", 
            		"Miles", 
            		"Hike/Bike Patrol Hours", 
            		
            		"General Patrol Count",
            		"Bike Patrol Count",
            		"Apt Init Count",
            		"Special Ops Count",
            		"Event Count",
            		"Other Count",
            		
            		"Felony",
            		"Class A/B Misdemeanor",
            		"Class C (No Ticket)",
            		"Non-Traffic/DRT",
            		"City",
            		"Felony",
            		"Misdemeanor",
            		"SETCIC",
            		"Warnings",
            		"Abatements",
            		"Tickets",
            		"Offense Reports",
            		"Parking",
            		"Charges Filed",
            		"Suspects In Jail",
            		"Holds",
            		"Traffic Stops",
            		"Moving",
            		"Non-Moving",
            		"Primary Calls",
            		"Secondary Calls",
            		"On Views Flagged Down",
            		"Incident Reports",
            		"Accident Reports",
            		"Supplement Reports",
            		"Crime Initiatives",
            		"Crime Initiatives In WC Vehicle",
            		"admin Assignments",
            		"AM Checklist Completed",
            		"Business Checks Completed East",
            		"Business Checks Completed West",
            		"Apartment Liaison Meetings",
            		"Hotel Liaison Meetings",
            		"Retail Liaison Meetings",
            		"Office Building Liason Meetings",
            		"Citizen Contacts",
            		"Crime Prevention Pamphlets",
            		"Events",
            		"CPTED Inspections",
            		"Crime Prevention Seminars" };
            



    		CellStyle style = wb.createCellStyle();
    		style.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
    		Font font = wb.createFont();
    		font.setFontName(FONT_NAME);
    		font.setFontHeightInPoints(FONT_HEIGHT);
    		style.setFont(font);
            
    		// write headers along left column
			int startRowNum = FIRST_DATA_ROW + 1;
			int rowNum = startRowNum;
            for (int i = 0; i < headers.length; i++) {
				Row row = sheet.createRow(rowNum);
				
				writeCell(wb, sheet, row, 0, headers[i], style);
				
				rowNum++;
            }

			if (results != null && !results.isEmpty()) {
				int col = 2;
				
				for (PatrolActivityReportDTO result : results) {
					rowNum = 0;
					
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getOfficer().getFullNameReverse(), style);

					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getDutyHours(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getMiles(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getHikePatrolHours(), style);
					

					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getGeneralPatrolCount(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getBikePatrolCount(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getAptInitCount(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getSpecialOpsCount(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getEventCount(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getOtherCount(), style);
					
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getCrimeArrestsFelony(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getCrimeArrestsClassAbMisdemeanor(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getCrimeArrestsClassCTicket(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getCrimeArrestsTrafficDrt(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getWarrantsCity(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getWarrantsFelony(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getWarrantsMisdemeanor(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getWarrantsSetcic(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getDrtInvestigationsWarnings(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getDrtInvestigationsAbatements(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getDrtInvestigationsTickets(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getDrtInvestigationsOffenseReports(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getFieldParking(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getFieldChargesFiled(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getFieldSuspectsInJail(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getFieldHolds(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getFieldTrafficStops(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getTrafficMoving(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getTrafficNonMoving(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getPrimaryCalls(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getSecondaryCalls(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getOnViewsFlaggedDown(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getIncidentReports(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getAccidentReports(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getSupplementReports(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getCrimeInitiatives(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getCrimeInitiativesInWcVehicle(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getAdminAssignments(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getAmChecklistCompleted(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getBusinessChecksCompletedEast(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getBusinessChecksCompletedWest(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getCommunityApartmentLiaisonMeetings(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getCommunityHotelLiaisonMeetings(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getCommunityRetailLiaisonMeetings(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getCommunityOfficeBuildingLiasonMeetings(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getCommunityCitizenContacts(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getCommunityCrimePreventionPamphlets(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getCommunityEvents(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getCommunityCptedInspections(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getCommunityCrimePreventionSeminars(), style);
					
					rowNum++;
					
					col++;
				}
				
			}
			
			wb.write(bos);
			bos.close();
            
		} catch (Exception e) {
			log.error("error with PatrolActivityReport", e);
		}
    	
		return bos;
	}

	protected ByteArrayOutputStream createWorkbook2() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
		try {
			Workbook wb = new XSSFWorkbook();
		    Sheet sheet = wb.createSheet("report");

			
			writeTitle(wb, sheet, "Patrol Activity Report");
			
			
			// TODO: these aren't showing up properly
			writeSuperHeaderCell(wb, sheet, "Crime Arrest Activity", HEADER_ROW, 10, 13);
			writeSuperHeaderCell(wb, sheet, "Warrants", HEADER_ROW, 14, 17);
			writeSuperHeaderCell(wb, sheet, "DRT Investigations", HEADER_ROW, 18, 21);
			writeSuperHeaderCell(wb, sheet, "Field Activity", HEADER_ROW, 22, 26);	
			writeSuperHeaderCell(wb, sheet, "Traffic", HEADER_ROW, 27, 29);
			writeSuperHeaderCell(wb, sheet, "Patrol Activity", HEADER_ROW, 29, 34);
			writeSuperHeaderCell(wb, sheet, "Directed Patrol Activity", HEADER_ROW, 35, 40);
			writeSuperHeaderCell(wb, sheet, "Community Services", HEADER_ROW, 41, 49);
			

            String[] headers = {"Officer", 
            		"Duty Hours", 
            		"Miles", 
            		"Hike/Bike Patrol Hours", 
            		
            		"General Patrol Count",
            		"Bike Patrol Count",
            		"Apt Init Count",
            		"Special Ops Count",
            		"Event Count",
            		"Other Count",
            		
            		"Felony",
            		"Class A/B Misdemeanor",
            		"Class C (No Ticket)",
            		"Non-Traffic/DRT",
            		"City",
            		"Felony",
            		"Misdemeanor",
            		"SETCIC",
            		"Warnings",
            		"Abatements",
            		"Tickets",
            		"Offense Reports",
            		"Parking",
            		"Charges Filed",
            		"Suspects In Jail",
            		"Holds",
            		"Traffic Stops",
            		"Moving",
            		"Non-Moving",
            		"Primary Calls",
            		"Secondary Calls",
            		"On Views Flagged Down",
            		"Incident Reports",
            		"Accident Reports",
            		"Supplement Reports",
            		"Crime Initiatives",
            		"Crime Initiatives In WC Vehicle",
            		"admin Assignments",
            		"AM Checklist Completed",
            		"Business Checks Completed East",
            		"Business Checks Completed West",
            		"Apartment Liaison Meetings",
            		"Hotel Liaison Meetings",
            		"Retail Liaison Meetings",
            		"Office Building Liason Meetings",
            		"Citizen Contacts",
            		"Crime Prevention Pamphlets",
            		"Events",
            		"CPTED Inspections",
            		"Crime Prevention Seminars" };
           
            // Write the Header to the excel file
            writeHeaders(wb, sheet, headers, 1);

    		CellStyle style = wb.createCellStyle();
    		style.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
    		Font font = wb.createFont();
    		font.setFontName(FONT_NAME);
    		font.setFontHeightInPoints(FONT_HEIGHT);
    		style.setFont(font);
            
			if (results != null && !results.isEmpty()) {
				int rowNum = FIRST_DATA_ROW + 1;
				int col = 0;
				for (PatrolActivityReportDTO result : results) {
					Row row = sheet.createRow(rowNum);
					writeCell(wb, sheet, row, col++, result.getOfficer().getFullNameReverse(), style);

					writeCell(wb, sheet, row, col++, result.getDutyHours(), style);
					writeCell(wb, sheet, row, col++, result.getMiles(), style);
					writeCell(wb, sheet, row, col++, result.getHikePatrolHours(), style);
					

					writeCell(wb, sheet, row, col++, result.getGeneralPatrolCount(), style);
					writeCell(wb, sheet, row, col++, result.getBikePatrolCount(), style);
					writeCell(wb, sheet, row, col++, result.getAptInitCount(), style);
					writeCell(wb, sheet, row, col++, result.getSpecialOpsCount(), style);
					writeCell(wb, sheet, row, col++, result.getEventCount(), style);
					writeCell(wb, sheet, row, col++, result.getOtherCount(), style);
					
					writeCell(wb, sheet, row, col++, result.getCrimeArrestsFelony(), style);
					writeCell(wb, sheet, row, col++, result.getCrimeArrestsClassAbMisdemeanor(), style);
					writeCell(wb, sheet, row, col++, result.getCrimeArrestsClassCTicket(), style);
					writeCell(wb, sheet, row, col++, result.getCrimeArrestsTrafficDrt(), style);
					writeCell(wb, sheet, row, col++, result.getWarrantsCity(), style);
					writeCell(wb, sheet, row, col++, result.getWarrantsFelony(), style);
					writeCell(wb, sheet, row, col++, result.getWarrantsMisdemeanor(), style);
					writeCell(wb, sheet, row, col++, result.getWarrantsSetcic(), style);
					writeCell(wb, sheet, row, col++, result.getDrtInvestigationsWarnings(), style);
					writeCell(wb, sheet, row, col++, result.getDrtInvestigationsAbatements(), style);
					writeCell(wb, sheet, row, col++, result.getDrtInvestigationsTickets(), style);
					writeCell(wb, sheet, row, col++, result.getDrtInvestigationsOffenseReports(), style);
					writeCell(wb, sheet, row, col++, result.getFieldParking(), style);
					writeCell(wb, sheet, row, col++, result.getFieldChargesFiled(), style);
					writeCell(wb, sheet, row, col++, result.getFieldSuspectsInJail(), style);
					writeCell(wb, sheet, row, col++, result.getFieldHolds(), style);
					writeCell(wb, sheet, row, col++, result.getFieldTrafficStops(), style);
					writeCell(wb, sheet, row, col++, result.getTrafficMoving(), style);
					writeCell(wb, sheet, row, col++, result.getTrafficNonMoving(), style);
					writeCell(wb, sheet, row, col++, result.getPrimaryCalls(), style);
					writeCell(wb, sheet, row, col++, result.getSecondaryCalls(), style);
					writeCell(wb, sheet, row, col++, result.getOnViewsFlaggedDown(), style);
					writeCell(wb, sheet, row, col++, result.getIncidentReports(), style);
					writeCell(wb, sheet, row, col++, result.getAccidentReports(), style);
					writeCell(wb, sheet, row, col++, result.getSupplementReports(), style);
					writeCell(wb, sheet, row, col++, result.getCrimeInitiatives(), style);
					writeCell(wb, sheet, row, col++, result.getCrimeInitiativesInWcVehicle(), style);
					writeCell(wb, sheet, row, col++, result.getAdminAssignments(), style);
					writeCell(wb, sheet, row, col++, result.getAmChecklistCompleted(), style);
					writeCell(wb, sheet, row, col++, result.getBusinessChecksCompletedEast(), style);
					writeCell(wb, sheet, row, col++, result.getBusinessChecksCompletedWest(), style);
					writeCell(wb, sheet, row, col++, result.getCommunityApartmentLiaisonMeetings(), style);
					writeCell(wb, sheet, row, col++, result.getCommunityHotelLiaisonMeetings(), style);
					writeCell(wb, sheet, row, col++, result.getCommunityRetailLiaisonMeetings(), style);
					writeCell(wb, sheet, row, col++, result.getCommunityOfficeBuildingLiasonMeetings(), style);
					writeCell(wb, sheet, row, col++, result.getCommunityCitizenContacts(), style);
					writeCell(wb, sheet, row, col++, result.getCommunityCrimePreventionPamphlets(), style);
					writeCell(wb, sheet, row, col++, result.getCommunityEvents(), style);
					writeCell(wb, sheet, row, col++, result.getCommunityCptedInspections(), style);
					writeCell(wb, sheet, row, col++, result.getCommunityCrimePreventionSeminars(), style);
					
					rowNum++;
					col = 0;
				}
			}
			
			fixColumns(sheet, headers.length);
			
			wb.write(bos);
			bos.close();
            
		} catch (Exception e) {
			log.error("error with PatrolActivityReport", e);
		}
    	
		return bos;
	}

	@Override
	protected String getReportFileName() {
		return "patrol-activity_" + getReportDate();
	}

	public List<PatrolActivityReportDTO> getResults() {
		return results;
	}

	public void setResults(List<PatrolActivityReportDTO> results) {
		this.results = results;
	}

	public Integer getOfficerId() {
		return officerId;
	}

	public void setOfficerId(Integer officerId) {
		this.officerId = officerId;
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

	public List<Officer> getAvailableOfficers() {
		return availableOfficers;
	}

	public void setAvailableOfficers(List<Officer> availableOfficers) {
		this.availableOfficers = availableOfficers;
	}

}
