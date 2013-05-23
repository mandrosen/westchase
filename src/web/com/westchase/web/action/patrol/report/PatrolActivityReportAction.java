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
			
			int totalOfficers = 0;
			int totalDutyHoursMins = 0;
			
			int totalMiles = 0;
			int totalHikePatrolMins = 0;
			
			int totalGeneralPatrolCount = 0;
			int totalBikePatrolCount = 0;
			int totalAptInitCount = 0;
			int totalSpecialOpsCount = 0;
			int totalEventCount = 0;
			int totalOtherCount = 0;
			
			int totalCrimeArrestsFelony = 0;
			int totalCrimeArrestsClassAbMisdemeanor = 0;
			int totalCrimeArrestsClassCTicket = 0;
			int totalCrimeArrestsTrafficDrt = 0;
			int totalWarrantsCity = 0;
			int totalWarrantsFelony = 0;
			int totalWarrantsMisdemeanor = 0;
			int totalWarrantsSetcic = 0;
			int totalDrtInvestigationsWarnings = 0;
			int totalDrtInvestigationsAbatements = 0;
			int totalDrtInvestigationsTickets = 0;
			int totalDrtInvestigationsOffenseReports = 0;
			int totalFieldParking = 0;
			int totalFieldChargesFiled = 0;
			int totalFieldSuspectsInJail = 0;
			int totalFieldHolds = 0;
			int totalFieldTrafficStops = 0;
			int totalTrafficMoving = 0;
			int totalTrafficNonMoving = 0;
			int totalPrimaryCalls = 0;
			int totalSecondaryCalls = 0;
			int totalOnViewsFlaggedDown = 0;
			int totalIncidentReports = 0;
			int totalAccidentReports = 0;
			int totalSupplementReports = 0;
			int totalCrimeInitiatives = 0;
			int totalCrimeInitiativesInWcVehicle = 0;
			int totalAdminAssignments = 0;
			int totalAmChecklistCompleted = 0;
			int totalBusinessChecksCompletedEast = 0;
			int totalBusinessChecksCompletedWest = 0;
			int totalCommunityApartmentLiaisonMeetings = 0;
			int totalCommunityHotelLiaisonMeetings = 0;
			int totalCommunityRetailLiaisonMeetings = 0;
			int totalCommunityOfficeBuildingLiasonMeetings = 0;
			int totalCommunityCitizenContacts = 0;
			int totalCommunityCrimePreventionPamphlets = 0;
			int totalCommunityEvents = 0;
			int totalCommunityCptedInspections = 0;
			int totalCommunityCrimePreventionSeminars = 0;
			

            String[] headers = {"Officer", 
            		"Duty Hours", 
            		"Miles", 
            		"Hike/Bike Patrol Hours", 
            		
            		"General Patrols",
            		"Bike Patrols",
            		"Apt Initiatives",
            		"Special Ops",
            		"Events",
            		"Others",
            		
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
            		"Admin Assignments",
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
            		"Crime Prevention Seminars",
            		"TOTAL" };
            



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

            int col = 2;
			if (results != null && !results.isEmpty()) {
				
				for (PatrolActivityReportDTO result : results) {
					rowNum = 0;
					
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getOfficer().getLastName(), style);

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
					

					totalOfficers++;
					totalDutyHoursMins = addMins(totalDutyHoursMins, result.getDutyHours());
					totalMiles += result.getMiles();
					totalHikePatrolMins = addMins(totalHikePatrolMins, result.getHikePatrolHours());
					totalGeneralPatrolCount += result.getGeneralPatrolCount();
					totalBikePatrolCount += result.getBikePatrolCount();
					totalAptInitCount += result.getAptInitCount();
					totalSpecialOpsCount += result.getSpecialOpsCount();
					totalEventCount += result.getEventCount();
					totalOtherCount += result.getOtherCount();
					totalCrimeArrestsFelony += result.getCrimeArrestsFelony();
					totalCrimeArrestsClassAbMisdemeanor += result.getCrimeArrestsClassAbMisdemeanor();
					totalCrimeArrestsClassCTicket += result.getCrimeArrestsClassCTicket();
					totalCrimeArrestsTrafficDrt += result.getCrimeArrestsTrafficDrt();
					totalWarrantsCity += result.getWarrantsCity();
					totalWarrantsFelony += result.getWarrantsFelony();
					totalWarrantsMisdemeanor += result.getWarrantsMisdemeanor();
					totalWarrantsSetcic += result.getWarrantsSetcic();
					totalDrtInvestigationsWarnings += result.getDrtInvestigationsWarnings();
					totalDrtInvestigationsAbatements += result.getDrtInvestigationsAbatements();
					totalDrtInvestigationsTickets += result.getDrtInvestigationsTickets();
					totalDrtInvestigationsOffenseReports += result.getDrtInvestigationsOffenseReports();
					totalFieldParking += result.getFieldParking();
					totalFieldChargesFiled += result.getFieldChargesFiled();
					totalFieldSuspectsInJail += result.getFieldSuspectsInJail();
					totalFieldHolds += result.getFieldHolds();
					totalFieldTrafficStops += result.getFieldTrafficStops();
					totalTrafficMoving += result.getTrafficMoving();
					totalTrafficNonMoving += result.getTrafficNonMoving();
					totalPrimaryCalls += result.getPrimaryCalls();
					totalSecondaryCalls += result.getSecondaryCalls();
					totalOnViewsFlaggedDown += result.getOnViewsFlaggedDown();
					totalIncidentReports += result.getIncidentReports();
					totalAccidentReports += result.getAccidentReports();
					totalSupplementReports += result.getSupplementReports();
					totalCrimeInitiatives += result.getCrimeInitiatives();
					totalCrimeInitiativesInWcVehicle += result.getCrimeInitiativesInWcVehicle();
					totalAdminAssignments += result.getAdminAssignments();
					totalAmChecklistCompleted += result.getAmChecklistCompleted();
					totalBusinessChecksCompletedEast += result.getBusinessChecksCompletedEast();
					totalBusinessChecksCompletedWest += result.getBusinessChecksCompletedWest();
					totalCommunityApartmentLiaisonMeetings += result.getCommunityApartmentLiaisonMeetings();
					totalCommunityHotelLiaisonMeetings += result.getCommunityHotelLiaisonMeetings();
					totalCommunityRetailLiaisonMeetings += result.getCommunityRetailLiaisonMeetings();
					totalCommunityOfficeBuildingLiasonMeetings += result.getCommunityOfficeBuildingLiasonMeetings();
					totalCommunityCitizenContacts += result.getCommunityCitizenContacts();
					totalCommunityCrimePreventionPamphlets += result.getCommunityCrimePreventionPamphlets();
					totalCommunityEvents += result.getCommunityEvents();
					totalCommunityCptedInspections += result.getCommunityCptedInspections();
					totalCommunityCrimePreventionSeminars += result.getCommunityCrimePreventionSeminars();
					
					rowNum++;
					
					col++;
				}
				
			}
			
			// write totals
			int totalRowStart = 0;
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalOfficers, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, convertMinutesToHoursMinutes(totalDutyHoursMins), style);
			
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalMiles, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, convertMinutesToHoursMinutes(totalHikePatrolMins), style);
			
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalGeneralPatrolCount, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalBikePatrolCount, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalAptInitCount, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalSpecialOpsCount, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalEventCount, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalOtherCount, style);
			
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalCrimeArrestsFelony, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalCrimeArrestsClassAbMisdemeanor, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalCrimeArrestsClassCTicket, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalCrimeArrestsTrafficDrt, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalWarrantsCity, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalWarrantsFelony, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalWarrantsMisdemeanor, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalWarrantsSetcic, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalDrtInvestigationsWarnings, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalDrtInvestigationsAbatements, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalDrtInvestigationsTickets, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalDrtInvestigationsOffenseReports, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalFieldParking, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalFieldChargesFiled, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalFieldSuspectsInJail, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalFieldHolds, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalFieldTrafficStops, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalTrafficMoving, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalTrafficNonMoving, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalPrimaryCalls, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalSecondaryCalls, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalOnViewsFlaggedDown, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalIncidentReports, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalAccidentReports, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalSupplementReports, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalCrimeInitiatives, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalCrimeInitiativesInWcVehicle, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalAdminAssignments, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalAmChecklistCompleted, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalBusinessChecksCompletedEast, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalBusinessChecksCompletedWest, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalCommunityApartmentLiaisonMeetings, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalCommunityHotelLiaisonMeetings, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalCommunityRetailLiaisonMeetings, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalCommunityOfficeBuildingLiasonMeetings, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalCommunityCitizenContacts, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalCommunityCrimePreventionPamphlets, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalCommunityEvents, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalCommunityCptedInspections, style);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalCommunityCrimePreventionSeminars, style);
			
			wb.write(bos);
			bos.close();
            
		} catch (Exception e) {
			log.error("error with PatrolActivityReport", e);
		}
    	
		return bos;
	}
	
	private String convertMinutesToHoursMinutes(int totalMinutes) {
		int hours = totalMinutes / 60; //since both are ints, you get an int
		int minutes = totalMinutes % 60;
		return String.format("%d:%02d", hours, minutes);
	}

	private int addMins(int totalDutyHoursMins, String dutyHours) {
		if (StringUtils.isBlank(dutyHours)) {
			return totalDutyHoursMins;
		}
		int totalMins = totalDutyHoursMins;
		String[] hoursMinsSplit = dutyHours.split(":");
		if (hoursMinsSplit.length == 2) {
			int hours = Integer.parseInt(hoursMinsSplit[0]);
			int mins = Integer.parseInt(hoursMinsSplit[1]);
			totalMins += (60 * hours) + mins;
		}
		return totalMins;
	}

	/*protected ByteArrayOutputStream createWorkbook2() {
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
	}*/

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
