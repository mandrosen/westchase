package com.westchase.web.action.patrol.report;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
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
import com.westchase.persistence.model.PatrolType;
import com.westchase.utils.DateUtils;
import com.westchase.utils.ejb.ServiceLocator;
import com.westchase.web.action.report.AbstractReportAction;

public class PatrolActivityReportAction extends AbstractReportAction {

	public static final String HEADER_MARKER = ">";
	
	private List<Officer> availableOfficers;
	private List<PatrolType> availablePatrolTypes;
	
	private List<Integer> officerIdList;
	private String startDate;
	private String endDate;
	
	private List<Integer> patrolTypeIdList;

	private List<PatrolActivityReportDTO> results;

	public PatrolActivityReportAction() {
		super();
	}
	
	public List<Integer> getPatrolTypeIdList() {
		return patrolTypeIdList;
	}

	public void setPatrolTypeIdList(List<Integer> patrolTypeIdList) {
		this.patrolTypeIdList = patrolTypeIdList;
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
			
			results = patrolServ.runReport(officerIdList, DateUtils.getDateFromWeb(startDate), DateUtils.getDateFromWeb(endDate), getPatrolTypeIdList());
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
			int totalFootPatrolCount = 0;
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
            		
            		HEADER_MARKER + "PATROL TYPE",
            		
            		"General Patrols",
            		"Bike Patrols",
            		"Apt Initiatives",
            		"Special Ops",
            		"Events",
            		"Foot Patrol",
            		"Others",
            		
            		HEADER_MARKER + "CRIME ARREST ACTIVITY",
            		
            		"Felony",
            		"Class A/B Misdemeanor",
            		"Class C (No Ticket)",
            		"Non-Traffic/DRT",
            		
            		HEADER_MARKER + "WARRANTS",
            		
            		"City",
            		"Felony",
            		"Misdemeanor",
            		"SETCIC",
            		
            		HEADER_MARKER + "DRT INVESTIGATIONS",
            		
            		"Warnings",
            		"Abatements",
            		"Tickets",
            		"Offense Reports",
            		
            		HEADER_MARKER + "FIELD ACTIVITY",
            		
            		"Parking",
            		"Charges Filed",
            		"Suspects In Jail",
            		"Holds",
            		"Traffic Stops",
            		
            		HEADER_MARKER + "TRAFFIC ENFORCEMENT",
            		
            		"Moving",
            		"Non-Moving",
            		
            		HEADER_MARKER + "PATROL ACTIVITY",
            		
            		"Primary Calls",
            		"Secondary Calls",
            		"On Views Flagged Down",
            		"Incident Reports",
            		"Accident Reports",
            		"Supplement Reports",
            		
            		HEADER_MARKER + "DIRECTED PATROL ACTIVITY",
            		
            		"Crime Initiatives",
            		"Crime Initiatives In WC Vehicle",
            		"Admin Assignments",
            		"AM Checklist Completed",
            		"Business Checks Completed East",
            		"Business Checks Completed West",
            		
            		HEADER_MARKER + "COMMUNITY SERVICES",
            		
            		"Apartment Liaison Meetings",
            		"Hotel Liaison Meetings",
            		"Retail Liaison Meetings",
            		"Office Building Liason Meetings",
            		"Citizen Contacts",
            		"Crime Prevention Pamphlets",
            		"Events",
            		"CPTED Inspections",
            		"Crime Prevention Seminars"};
            



    		CellStyle style = wb.createCellStyle();
    		style.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
//    		style.setFillPattern(CellStyle.BIG_SPOTS);
    		style.setAlignment(CellStyle.ALIGN_CENTER);
    		Font font = wb.createFont();
    		font.setFontName(FONT_NAME);
    		font.setFontHeightInPoints(FONT_HEIGHT);
    		style.setFont(font);
    		
    		CellStyle headerStyle = wb.createCellStyle();
//    		headerStyle.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
//    		headerStyle.setFillPattern(CellStyle.BIG_SPOTS);
//    		headerStyle.setFillPattern((short) 1);
    		Font headerFont = wb.createFont();
    		headerFont.setFontName(FONT_NAME);
    		headerFont.setFontHeightInPoints(FONT_HEIGHT);
    		headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
    		headerStyle.setFont(headerFont);
            
    		// write headers along left column
			int startRowNum = FIRST_DATA_ROW + 1;
			int rowNum = startRowNum;
            for (int i = 0; i < headers.length; i++) {
				Row row = sheet.createRow(rowNum);
				
				String header = headers[i];
				if (header.startsWith(HEADER_MARKER)) {
					writeCell(wb, sheet, row, 0, header.substring(1), headerStyle);
				} else {
					writeCell(wb, sheet, row, 0, header, headerStyle);
				}
				
				rowNum++;
            }

            int col = 2;
			if (results != null && !results.isEmpty()) {
				
				for (PatrolActivityReportDTO result : results) {
					rowNum = 0;
					
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getOfficer().getLastName().toUpperCase(), headerStyle);

					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getDutyHours(), headerStyle);
					
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getMiles(), headerStyle);
					
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getHikePatrolHours(), headerStyle);
					
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, "", headerStyle);
					
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getGeneralPatrolCount(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getBikePatrolCount(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getAptInitCount(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getSpecialOpsCount(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getEventCount(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getFootPatrolCount(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getOtherCount(), style);
					
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, "", headerStyle);
					
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getCrimeArrestsFelony(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getCrimeArrestsClassAbMisdemeanor(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getCrimeArrestsClassCTicket(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getCrimeArrestsTrafficDrt(), style);
					
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, "", headerStyle);
					
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getWarrantsCity(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getWarrantsFelony(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getWarrantsMisdemeanor(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getWarrantsSetcic(), style);
					
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, "", headerStyle);
					
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getDrtInvestigationsWarnings(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getDrtInvestigationsAbatements(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getDrtInvestigationsTickets(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getDrtInvestigationsOffenseReports(), style);
					
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, "", headerStyle);
					
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getFieldParking(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getFieldChargesFiled(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getFieldSuspectsInJail(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getFieldHolds(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getFieldTrafficStops(), style);
					
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, "", headerStyle);
					
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getTrafficMoving(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getTrafficNonMoving(), style);
					
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, "", headerStyle);
					
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getPrimaryCalls(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getSecondaryCalls(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getOnViewsFlaggedDown(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getIncidentReports(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getAccidentReports(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getSupplementReports(), style);
					
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, "", headerStyle);
					
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getCrimeInitiatives(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getCrimeInitiativesInWcVehicle(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getAdminAssignments(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getAmChecklistCompleted(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getBusinessChecksCompletedEast(), style);
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, result.getBusinessChecksCompletedWest(), style);
					
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, "", headerStyle);
					
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
					totalFootPatrolCount += result.getFootPatrolCount();
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
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalOfficers, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, convertMinutesToHoursMinutes(totalDutyHoursMins), headerStyle);
			
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalMiles, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, convertMinutesToHoursMinutes(totalHikePatrolMins), headerStyle);
			
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, "", headerStyle);
			
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalGeneralPatrolCount, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalBikePatrolCount, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalAptInitCount, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalSpecialOpsCount, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalEventCount, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalFootPatrolCount, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalOtherCount, headerStyle);
			
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, "", headerStyle);
			
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalCrimeArrestsFelony, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalCrimeArrestsClassAbMisdemeanor, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalCrimeArrestsClassCTicket, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalCrimeArrestsTrafficDrt, headerStyle);
			
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, "", headerStyle);
			
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalWarrantsCity, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalWarrantsFelony, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalWarrantsMisdemeanor, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalWarrantsSetcic, headerStyle);
			
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, "", headerStyle);
			
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalDrtInvestigationsWarnings, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalDrtInvestigationsAbatements, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalDrtInvestigationsTickets, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalDrtInvestigationsOffenseReports, headerStyle);
			
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, "", headerStyle);
			
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalFieldParking, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalFieldChargesFiled, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalFieldSuspectsInJail, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalFieldHolds, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalFieldTrafficStops, headerStyle);
			
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, "", headerStyle);
			
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalTrafficMoving, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalTrafficNonMoving, headerStyle);
			
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, "", headerStyle);
			
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalPrimaryCalls, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalSecondaryCalls, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalOnViewsFlaggedDown, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalIncidentReports, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalAccidentReports, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalSupplementReports, headerStyle);
			
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, "", headerStyle);
			
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalCrimeInitiatives, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalCrimeInitiativesInWcVehicle, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalAdminAssignments, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalAmChecklistCompleted, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalBusinessChecksCompletedEast, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalBusinessChecksCompletedWest, headerStyle);
			
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, "", headerStyle);
			
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalCommunityApartmentLiaisonMeetings, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalCommunityHotelLiaisonMeetings, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalCommunityRetailLiaisonMeetings, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalCommunityOfficeBuildingLiasonMeetings, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalCommunityCitizenContacts, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalCommunityCrimePreventionPamphlets, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalCommunityEvents, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalCommunityCptedInspections, headerStyle);
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalCommunityCrimePreventionSeminars, headerStyle);
			
			wb.write(bos);
			bos.close();
			
			// TODO: merge header cells
//			CellRangeAddress region = new CellRangeAddress(rowIdx, rowIdx, columnIdx, columnIdx+1);
//			sheet.addMergedRegion(region);
			
			for (int i = 0; i < col; i++) {
				sheet.autoSizeColumn(i);
			}
            
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

}
