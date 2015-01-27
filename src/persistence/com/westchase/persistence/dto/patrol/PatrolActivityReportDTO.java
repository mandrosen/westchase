package com.westchase.persistence.dto.patrol;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.westchase.persistence.model.Officer;

public class PatrolActivityReportDTO implements Serializable {
	
	private Officer officer;
	
	private Long dutyMinutes;
	private String dutyHours;
	
	private Long miles;

	private String hikePatrolHours;
	private Integer hikePatrolCount;
	
	private Long generalPatrolCount;
	private Long bikePatrolCount;
	private Long aptInitCount;
	private Long specialOpsCount;
	private Long eventCount;
	private Long footPatrolCount;
	private Long otherCount;

	private Double crimeArrestsFelony;
	private Double crimeArrestsClassAbMisdemeanor;
	private Double crimeArrestsClassCTicket;
	private Double crimeArrestsTrafficDrt;
	private Double warrantsCity;
	private Double warrantsFelony;
	private Double warrantsMisdemeanor;
	private Double warrantsSetcic;
	private Double drtInvestigationsWarnings;
	private Double drtInvestigationsAbatements;
	private Double drtInvestigationsTickets;
	private Double drtInvestigationsOffenseReports;
	private Double fieldParking;
	private Double fieldChargesFiled;
	private Double fieldSuspectsInJail;
	private Double fieldHolds;
	private Double fieldTrafficStops;
	private Double trafficMoving;
	private Double trafficNonMoving;
	
	private Double primaryCalls;
	private Double secondaryCalls;
	private Double onViewsFlaggedDown;
	private Double incidentReports;
	private Double accidentReports;
	private Double supplementReports;
	private Double crimeInitiatives;
	private Double crimeInitiativesInWcVehicle;
	private Double adminAssignments;
	
	private Long amChecklistCompleted;
	private Long businessChecksCompletedEast;
	private Long businessChecksCompletedWest;
	private Long communityApartmentLiaisonMeetings;
	private Long communityHotelLiaisonMeetings;
	private Long communityRetailLiaisonMeetings;
	private Long communityOfficeBuildingLiasonMeetings;
	private Long communityCitizenContacts;
	private Long communityCrimePreventionPamphlets;
	private Long communityEvents;
	private Long communityCptedInspections;
	private Long communityCrimePreventionSeminars;

	public PatrolActivityReportDTO() {
		super();
	}
	
	private static String convertMinutesToHoursMinutes(Long t) {
		String hoursMins = null;
		
		if (t != null && t > 0) {
			Long hours = t.longValue() / 60;
			Long minutes = t.longValue() % 60;
			hoursMins = hours + ":" + StringUtils.leftPad(String.valueOf(minutes), 2, '0');
		}

		return hoursMins;
	}

	public PatrolActivityReportDTO(
			Officer officer, 
			Long dutyMinutes,
			Long miles, 
			Long hikePatrolHours,
			
			Long generalPatrolCount,
			Long bikePatrolCount,
			Long aptInitCount,
			Long specialOpsCount,
			Long eventCount,
			Long footPatrolCount,
			Long otherCount,
			
			Double crimeArrestsFelony, 
			Double crimeArrestsClassAbMisdemeanor,
			Double crimeArrestsClassCTicket, 
			Double crimeArrestsTrafficDrt, 
			Double warrantsCity, 
			Double warrantsFelony,
			Double warrantsMisdemeanor, 
			Double warrantsSetcic, 
			Double drtInvestigationsWarnings,
			Double drtInvestigationsAbatements, 
			Double drtInvestigationsTickets, 
			Double drtInvestigationsOffenseReports,
			Double fieldParking, 
			Double fieldChargesFiled, 
			Double fieldSuspectsInJail, 
			Double fieldHolds,
			Double fieldTrafficStops, 
			Double trafficMoving, 
			Double trafficNonMoving, 
			Double primaryCalls, 
			Double secondaryCalls,
			Double onViewsFlaggedDown, 
			Double incidentReports, 
			Double accidentReports, 
			Double supplementReports,
			Double crimeInitiatives, 
			Double crimeInitiativesInWcVehicle, 
			Double adminAssignments, 
			Long amChecklistCompleted,
			Long businessChecksCompletedEast, 
			Long businessChecksCompletedWest, 
			Long communityApartmentLiaisonMeetings, 
			Long communityHotelLiaisonMeetings,
			Long communityRetailLiaisonMeetings, 
			Long communityOfficeBuildingLiasonMeetings,
			Long communityCitizenContacts, 
			Long communityCrimePreventionPamphlets, 
			Long communityEvents,
			Long communityCptedInspections, 
			Long communityCrimePreventionSeminars) {
		this();
		this.officer = officer;
		this.dutyHours = convertMinutesToHoursMinutes(dutyMinutes);
		this.miles = miles;
		this.hikePatrolHours = convertMinutesToHoursMinutes(hikePatrolHours);

		this.generalPatrolCount = generalPatrolCount;
		this.bikePatrolCount = bikePatrolCount;
		this.aptInitCount =  aptInitCount;
		this.specialOpsCount = specialOpsCount;
		this.eventCount = eventCount;
		this.footPatrolCount = footPatrolCount;
		this.otherCount = otherCount;
		
		this.crimeArrestsFelony = crimeArrestsFelony;
		this.crimeArrestsClassAbMisdemeanor = crimeArrestsClassAbMisdemeanor;
		this.crimeArrestsClassCTicket = crimeArrestsClassCTicket;
		this.crimeArrestsTrafficDrt = crimeArrestsTrafficDrt;
		this.warrantsCity = warrantsCity;
		this.warrantsFelony = warrantsFelony;
		this.warrantsMisdemeanor = warrantsMisdemeanor;
		this.warrantsSetcic = warrantsSetcic;
		this.drtInvestigationsWarnings = drtInvestigationsWarnings;
		this.drtInvestigationsAbatements = drtInvestigationsAbatements;
		this.drtInvestigationsTickets = drtInvestigationsTickets;
		this.drtInvestigationsOffenseReports = drtInvestigationsOffenseReports;
		this.fieldParking = fieldParking;
		this.fieldChargesFiled = fieldChargesFiled;
		this.fieldSuspectsInJail = fieldSuspectsInJail;
		this.fieldHolds = fieldHolds;
		this.fieldTrafficStops = fieldTrafficStops;
		this.trafficMoving = trafficMoving;
		this.trafficNonMoving = trafficNonMoving;
		this.primaryCalls = primaryCalls;
		this.secondaryCalls = secondaryCalls;
		this.onViewsFlaggedDown = onViewsFlaggedDown;
		this.incidentReports = incidentReports;
		this.accidentReports = accidentReports;
		this.supplementReports = supplementReports;
		this.crimeInitiatives = crimeInitiatives;
		this.crimeInitiativesInWcVehicle = crimeInitiativesInWcVehicle;
		this.adminAssignments = adminAssignments;
		this.amChecklistCompleted = amChecklistCompleted;
		this.businessChecksCompletedEast = businessChecksCompletedEast;
		this.businessChecksCompletedWest = businessChecksCompletedWest;
		this.communityApartmentLiaisonMeetings = communityApartmentLiaisonMeetings;
		this.communityHotelLiaisonMeetings = communityHotelLiaisonMeetings;
		this.communityRetailLiaisonMeetings = communityRetailLiaisonMeetings;
		this.communityOfficeBuildingLiasonMeetings = communityOfficeBuildingLiasonMeetings;
		this.communityCitizenContacts = communityCitizenContacts;
		this.communityCrimePreventionPamphlets = communityCrimePreventionPamphlets;
		this.communityEvents = communityEvents;
		this.communityCptedInspections = communityCptedInspections;
		this.communityCrimePreventionSeminars = communityCrimePreventionSeminars;
	}
	
	/**
	 * Used for Score Report
	 * @see com.westchase.persistence.dao.PatrolActivityDAO#runScoreReport()
	 */
	public PatrolActivityReportDTO(
			Officer officer, 
			Long dutyMinutes,
			Integer hikePatrolCount,
			
			Double crimeArrestsFelony, 
			Double crimeArrestsClassAbMisdemeanor,
			Double crimeArrestsClassCTicket, 
			Double crimeArrestsTrafficDrt, 
			Double warrantsCity, 
			Double warrantsFelony,
			Double warrantsMisdemeanor, 
			Double warrantsSetcic, 
			Double drtInvestigationsWarnings,
			Double drtInvestigationsAbatements, 
			Double drtInvestigationsTickets, 
			Double drtInvestigationsOffenseReports,
			Double fieldParking, 
			Double fieldChargesFiled, 
			Double fieldSuspectsInJail, 
			Double fieldHolds,
			Double fieldTrafficStops, 
			Double trafficMoving, 
			Double trafficNonMoving, 
			Double primaryCalls, 
			Double secondaryCalls,
			Double onViewsFlaggedDown, 
			Double incidentReports, 
			Double accidentReports, 
			Double supplementReports,
			Double crimeInitiatives, 
			Double crimeInitiativesInWcVehicle, 
			Double adminAssignments, 
			Long amChecklistCompleted,
			Long businessChecksCompletedEast, 
			Long businessChecksCompletedWest, 
			Long communityApartmentLiaisonMeetings, 
			Long communityHotelLiaisonMeetings,
			Long communityRetailLiaisonMeetings, 
			Long communityOfficeBuildingLiasonMeetings,
			Long communityCitizenContacts, 
			Long communityCrimePreventionPamphlets, 
			Long communityEvents,
			Long communityCptedInspections, 
			Long communityCrimePreventionSeminars) {
		this();
		this.officer = officer;
		this.dutyMinutes = dutyMinutes;
		this.dutyHours = convertMinutesToHoursMinutes(dutyMinutes);
		this.hikePatrolCount = hikePatrolCount;
		
		this.crimeArrestsFelony = crimeArrestsFelony;
		this.crimeArrestsClassAbMisdemeanor = crimeArrestsClassAbMisdemeanor;
		this.crimeArrestsClassCTicket = crimeArrestsClassCTicket;
		this.crimeArrestsTrafficDrt = crimeArrestsTrafficDrt;
		this.warrantsCity = warrantsCity;
		this.warrantsFelony = warrantsFelony;
		this.warrantsMisdemeanor = warrantsMisdemeanor;
		this.warrantsSetcic = warrantsSetcic;
		this.drtInvestigationsWarnings = drtInvestigationsWarnings;
		this.drtInvestigationsAbatements = drtInvestigationsAbatements;
		this.drtInvestigationsTickets = drtInvestigationsTickets;
		this.drtInvestigationsOffenseReports = drtInvestigationsOffenseReports;
		this.fieldParking = fieldParking;
		this.fieldChargesFiled = fieldChargesFiled;
		this.fieldSuspectsInJail = fieldSuspectsInJail;
		this.fieldHolds = fieldHolds;
		this.fieldTrafficStops = fieldTrafficStops;
		this.trafficMoving = trafficMoving;
		this.trafficNonMoving = trafficNonMoving;
		this.primaryCalls = primaryCalls;
		this.secondaryCalls = secondaryCalls;
		this.onViewsFlaggedDown = onViewsFlaggedDown;
		this.incidentReports = incidentReports;
		this.accidentReports = accidentReports;
		this.supplementReports = supplementReports;
		this.crimeInitiatives = crimeInitiatives;
		this.crimeInitiativesInWcVehicle = crimeInitiativesInWcVehicle;
		this.adminAssignments = adminAssignments;
		this.amChecklistCompleted = amChecklistCompleted;
		this.businessChecksCompletedEast = businessChecksCompletedEast;
		this.businessChecksCompletedWest = businessChecksCompletedWest;
		this.communityApartmentLiaisonMeetings = communityApartmentLiaisonMeetings;
		this.communityHotelLiaisonMeetings = communityHotelLiaisonMeetings;
		this.communityRetailLiaisonMeetings = communityRetailLiaisonMeetings;
		this.communityOfficeBuildingLiasonMeetings = communityOfficeBuildingLiasonMeetings;
		this.communityCitizenContacts = communityCitizenContacts;
		this.communityCrimePreventionPamphlets = communityCrimePreventionPamphlets;
		this.communityEvents = communityEvents;
		this.communityCptedInspections = communityCptedInspections;
		this.communityCrimePreventionSeminars = communityCrimePreventionSeminars;
	}
	
//	 used to debug hql load issue
//
//	public PatrolActivityReportDTO(
//			Officer officer, 
//			Object onViewsFlaggedDown) {
//		setOfficer(officer);
//	}	
//	
//	public PatrolActivityReportDTO(
//			Object officer, 
//			Object dutyHours,
//			Object miles, 
//			Object hikePatrolHours,
//			Object crimeArrestsFelony, 
//			Object crimeArrestsClassAbMisdemeanor,
//			Object crimeArrestsClassCTicket, 
//			Object crimeArrestsTrafficDrt, 
//			Object warrantsCity, 
//			Object warrantsFelony,
//			Object warrantsMisdemeanor, 
//			Object warrantsSetcic, 
//			Object drtInvestigationsWarnings,
//			Object drtInvestigationsAbatements, 
//			Object drtInvestigationsTickets, 
//			Object drtInvestigationsOffenseReports,
//			Object fieldParking, 
//			Object fieldChargesFiled, 
//			Object fieldSuspectsInJail, 
//			Object fieldHolds,
//			Object fieldTrafficStops, 
//			Object trafficMoving, 
//			Object trafficNonMoving, 
//			Object primaryCalls, 
//			Object secondaryCalls,
//			Object onViewsFlaggedDown, 
//			Object incidentReports, 
//			Object accidentReports, 
//			Object supplementReports,
//			Object crimeInitiatives, 
//			Object crimeInitiativesInWcVehicle, 
//			Object adminAssignments, 
//			Object amChecklistCompleted,
//			Object businessChecksCompletedEast, 
//			Object businessChecksCompletedWest, 
//			Object communityApartmentLiaisonMeetings, 
//			Object communityHotelLiaisonMeetings,
//			Object communityRetailLiaisonMeetings, 
//			Object communityOfficeBuildingLiasonMeetings,
//			Object communityCitizenContacts, 
//			Object communityCrimePreventionPamphlets, 
//			Object communityEvents,
//			Object communityCptedInspections, 
//			Object communityCrimePreventionSeminars) {
//		this();
//		this.officer = (Officer) officer;
//		this.dutyHours = convertMinutesToHoursMinutes((Long) dutyHours);
//		this.miles = (Long) miles;
//		this.hikePatrolHours = convertMinutesToHoursMinutes((Long) hikePatrolHours);
//		this.crimeArrestsFelony = (Long) crimeArrestsFelony;
//		this.crimeArrestsClassAbMisdemeanor = (Long) crimeArrestsClassAbMisdemeanor;
//		this.crimeArrestsClassCTicket = (Long) crimeArrestsClassCTicket;
//		this.crimeArrestsTrafficDrt = (Long) crimeArrestsTrafficDrt;
//		this.warrantsCity = (Long) warrantsCity;
//		this.warrantsFelony = (Long) warrantsFelony;
//		this.warrantsMisdemeanor = (Long) warrantsMisdemeanor;
//		this.warrantsSetcic = (Long) warrantsSetcic;
//		this.drtInvestigationsWarnings = (Long) drtInvestigationsWarnings;
//		this.drtInvestigationsAbatements = (Long) drtInvestigationsAbatements;
//		this.drtInvestigationsTickets = (Long) drtInvestigationsTickets;
//		this.drtInvestigationsOffenseReports = (Long) drtInvestigationsOffenseReports;
//		this.fieldParking = (Long) fieldParking;
//		this.fieldChargesFiled = (Long) fieldChargesFiled;
//		this.fieldSuspectsInJail = (Long) fieldSuspectsInJail;
//		this.fieldHolds = (Long) fieldHolds;
//		this.fieldTrafficStops = (Long) fieldTrafficStops;
//		this.trafficMoving = (Long) trafficMoving;
//		this.trafficNonMoving = (Long) trafficNonMoving;
//		this.primaryCalls = (Long) primaryCalls;
//		this.secondaryCalls = (Long) secondaryCalls;
//		this.onViewsFlaggedDown = (Long) onViewsFlaggedDown;
//		this.incidentReports = (Long) incidentReports;
//		this.accidentReports = (Long) accidentReports;
//		this.supplementReports = (Long) supplementReports;
//		this.crimeInitiatives = (Long) crimeInitiatives;
//		this.crimeInitiativesInWcVehicle = (Long) crimeInitiativesInWcVehicle;
//		this.adminAssignments = (Long) adminAssignments;
//		this.amChecklistCompleted = (Long) amChecklistCompleted;
//		this.businessChecksCompletedEast = (Long) businessChecksCompletedEast;
//		this.businessChecksCompletedWest = (Long) businessChecksCompletedWest;
//		this.communityApartmentLiaisonMeetings = (Long) communityApartmentLiaisonMeetings;
//		this.communityHotelLiaisonMeetings = (Long) communityHotelLiaisonMeetings;
//		this.communityRetailLiaisonMeetings = (Long) communityRetailLiaisonMeetings;
//		this.communityOfficeBuildingLiasonMeetings = (Long) communityOfficeBuildingLiasonMeetings;
//		this.communityCitizenContacts = (Long) communityCitizenContacts;
//		this.communityCrimePreventionPamphlets = (Long) communityCrimePreventionPamphlets;
//		this.communityEvents = (Long) communityEvents;
//		this.communityCptedInspections = (Long) communityCptedInspections;
//		this.communityCrimePreventionSeminars = (Long) communityCrimePreventionSeminars;
//	}

	public Officer getOfficer() {
		return officer;
	}

	public void setOfficer(Officer officer) {
		this.officer = officer;
	}

	public String getDutyHours() {
		return dutyHours;
	}

	public void setDutyHours(String dutyHours) {
		this.dutyHours = dutyHours;
	}

	public Long getMiles() {
		return miles;
	}

	public void setMiles(Long miles) {
		this.miles = miles;
	}

	public String getHikePatrolHours() {
		return hikePatrolHours;
	}

	public void setHikePatrolHours(String hikePatrolHours) {
		this.hikePatrolHours = hikePatrolHours;
	}

	
	public Long getGeneralPatrolCount() {
		return generalPatrolCount;
	}

	public void setGeneralPatrolCount(Long generalPatrolCount) {
		this.generalPatrolCount = generalPatrolCount;
	}

	public Long getBikePatrolCount() {
		return bikePatrolCount;
	}

	public void setBikePatrolCount(Long bikePatrolCount) {
		this.bikePatrolCount = bikePatrolCount;
	}

	public Long getAptInitCount() {
		return aptInitCount;
	}

	public void setAptInitCount(Long aptInitCount) {
		this.aptInitCount = aptInitCount;
	}

	public Long getSpecialOpsCount() {
		return specialOpsCount;
	}

	public void setSpecialOpsCount(Long specialOpsCount) {
		this.specialOpsCount = specialOpsCount;
	}

	public Long getEventCount() {
		return eventCount;
	}

	public void setEventCount(Long eventCount) {
		this.eventCount = eventCount;
	}

	public Long getOtherCount() {
		return otherCount;
	}

	public void setOtherCount(Long otherCount) {
		this.otherCount = otherCount;
	}
	public Double getCrimeArrestsFelony() {
		return crimeArrestsFelony;
	}

	public void setCrimeArrestsFelony(Double crimeArrestsFelony) {
		this.crimeArrestsFelony = crimeArrestsFelony;
	}

	public Double getCrimeArrestsClassAbMisdemeanor() {
		return crimeArrestsClassAbMisdemeanor;
	}

	public void setCrimeArrestsClassAbMisdemeanor(Double crimeArrestsClassAbMisdemeanor) {
		this.crimeArrestsClassAbMisdemeanor = crimeArrestsClassAbMisdemeanor;
	}

	public Double getCrimeArrestsClassCTicket() {
		return crimeArrestsClassCTicket;
	}

	public void setCrimeArrestsClassCTicket(Double crimeArrestsClassCTicket) {
		this.crimeArrestsClassCTicket = crimeArrestsClassCTicket;
	}

	public Double getCrimeArrestsTrafficDrt() {
		return crimeArrestsTrafficDrt;
	}

	public void setCrimeArrestsTrafficDrt(Double crimeArrestsTrafficDrt) {
		this.crimeArrestsTrafficDrt = crimeArrestsTrafficDrt;
	}

	public Double getWarrantsCity() {
		return warrantsCity;
	}

	public void setWarrantsCity(Double warrantsCity) {
		this.warrantsCity = warrantsCity;
	}

	public Double getWarrantsFelony() {
		return warrantsFelony;
	}

	public void setWarrantsFelony(Double warrantsFelony) {
		this.warrantsFelony = warrantsFelony;
	}

	public Double getWarrantsMisdemeanor() {
		return warrantsMisdemeanor;
	}

	public void setWarrantsMisdemeanor(Double warrantsMisdemeanor) {
		this.warrantsMisdemeanor = warrantsMisdemeanor;
	}

	public Double getWarrantsSetcic() {
		return warrantsSetcic;
	}

	public void setWarrantsSetcic(Double warrantsSetcic) {
		this.warrantsSetcic = warrantsSetcic;
	}

	public Double getDrtInvestigationsWarnings() {
		return drtInvestigationsWarnings;
	}

	public void setDrtInvestigationsWarnings(Double drtInvestigationsWarnings) {
		this.drtInvestigationsWarnings = drtInvestigationsWarnings;
	}

	public Double getDrtInvestigationsAbatements() {
		return drtInvestigationsAbatements;
	}

	public void setDrtInvestigationsAbatements(Double drtInvestigationsAbatements) {
		this.drtInvestigationsAbatements = drtInvestigationsAbatements;
	}

	public Double getDrtInvestigationsTickets() {
		return drtInvestigationsTickets;
	}

	public void setDrtInvestigationsTickets(Double drtInvestigationsTickets) {
		this.drtInvestigationsTickets = drtInvestigationsTickets;
	}

	public Double getDrtInvestigationsOffenseReports() {
		return drtInvestigationsOffenseReports;
	}

	public void setDrtInvestigationsOffenseReports(Double drtInvestigationsOffenseReports) {
		this.drtInvestigationsOffenseReports = drtInvestigationsOffenseReports;
	}

	public Double getFieldParking() {
		return fieldParking;
	}

	public void setFieldParking(Double fieldParking) {
		this.fieldParking = fieldParking;
	}

	public Double getFieldChargesFiled() {
		return fieldChargesFiled;
	}

	public void setFieldChargesFiled(Double fieldChargesFiled) {
		this.fieldChargesFiled = fieldChargesFiled;
	}

	public Double getFieldSuspectsInJail() {
		return fieldSuspectsInJail;
	}

	public void setFieldSuspectsInJail(Double fieldSuspectsInJail) {
		this.fieldSuspectsInJail = fieldSuspectsInJail;
	}

	public Double getFieldHolds() {
		return fieldHolds;
	}

	public void setFieldHolds(Double fieldHolds) {
		this.fieldHolds = fieldHolds;
	}

	public Double getFieldTrafficStops() {
		return fieldTrafficStops;
	}

	public void setFieldTrafficStops(Double fieldTrafficStops) {
		this.fieldTrafficStops = fieldTrafficStops;
	}

	public Double getTrafficMoving() {
		return trafficMoving;
	}

	public void setTrafficMoving(Double trafficMoving) {
		this.trafficMoving = trafficMoving;
	}

	public Double getTrafficNonMoving() {
		return trafficNonMoving;
	}

	public void setTrafficNonMoving(Double trafficNonMoving) {
		this.trafficNonMoving = trafficNonMoving;
	}

	public Double getPrimaryCalls() {
		return primaryCalls;
	}

	public void setPrimaryCalls(Double primaryCalls) {
		this.primaryCalls = primaryCalls;
	}

	public Double getSecondaryCalls() {
		return secondaryCalls;
	}

	public void setSecondaryCalls(Double secondaryCalls) {
		this.secondaryCalls = secondaryCalls;
	}

	public Double getOnViewsFlaggedDown() {
		return onViewsFlaggedDown;
	}

	public void setOnViewsFlaggedDown(Double onViewsFlaggedDown) {
		this.onViewsFlaggedDown = onViewsFlaggedDown;
	}

	public Double getIncidentReports() {
		return incidentReports;
	}

	public void setIncidentReports(Double incidentReports) {
		this.incidentReports = incidentReports;
	}

	public Double getAccidentReports() {
		return accidentReports;
	}

	public void setAccidentReports(Double accidentReports) {
		this.accidentReports = accidentReports;
	}

	public Double getSupplementReports() {
		return supplementReports;
	}

	public void setSupplementReports(Double supplementReports) {
		this.supplementReports = supplementReports;
	}

	public Double getCrimeInitiatives() {
		return crimeInitiatives;
	}

	public void setCrimeInitiatives(Double crimeInitiatives) {
		this.crimeInitiatives = crimeInitiatives;
	}

	public Double getCrimeInitiativesInWcVehicle() {
		return crimeInitiativesInWcVehicle;
	}

	public void setCrimeInitiativesInWcVehicle(Double crimeInitiativesInWcVehicle) {
		this.crimeInitiativesInWcVehicle = crimeInitiativesInWcVehicle;
	}

	public Double getAdminAssignments() {
		return adminAssignments;
	}

	public void setAdminAssignments(Double adminAssignments) {
		this.adminAssignments = adminAssignments;
	}

	public Long getAmChecklistCompleted() {
		return amChecklistCompleted;
	}

	public void setAmChecklistCompleted(Long amChecklistCompleted) {
		this.amChecklistCompleted = amChecklistCompleted;
	}
	
	

	public Long getBusinessChecksCompletedEast() {
		return businessChecksCompletedEast;
	}

	public void setBusinessChecksCompletedEast(Long businessChecksCompletedEast) {
		this.businessChecksCompletedEast = businessChecksCompletedEast;
	}
	
	public Long getBusinessChecksCompletedWest() {
		return businessChecksCompletedWest;
	}

	public void setBusinessChecksCompletedWest(Long businessChecksCompletedWest) {
		this.businessChecksCompletedWest = businessChecksCompletedWest;
	}
	
	

	public Long getCommunityApartmentLiaisonMeetings() {
		return communityApartmentLiaisonMeetings;
	}

	public void setCommunityApartmentLiaisonMeetings(Long communityApartmentLiaisonMeetings) {
		this.communityApartmentLiaisonMeetings = communityApartmentLiaisonMeetings;
	}

	public Long getCommunityHotelLiaisonMeetings() {
		return communityHotelLiaisonMeetings;
	}

	public void setCommunityHotelLiaisonMeetings(Long communityHotelLiaisonMeetings) {
		this.communityHotelLiaisonMeetings = communityHotelLiaisonMeetings;
	}

	public Long getCommunityRetailLiaisonMeetings() {
		return communityRetailLiaisonMeetings;
	}

	public void setCommunityRetailLiaisonMeetings(Long communityRetailLiaisonMeetings) {
		this.communityRetailLiaisonMeetings = communityRetailLiaisonMeetings;
	}

	public Long getCommunityOfficeBuildingLiasonMeetings() {
		return communityOfficeBuildingLiasonMeetings;
	}

	public void setCommunityOfficeBuildingLiasonMeetings(Long communityOfficeBuildingLiasonMeetings) {
		this.communityOfficeBuildingLiasonMeetings = communityOfficeBuildingLiasonMeetings;
	}

	public Long getCommunityCitizenContacts() {
		return communityCitizenContacts;
	}

	public void setCommunityCitizenContacts(Long communityCitizenContacts) {
		this.communityCitizenContacts = communityCitizenContacts;
	}

	public Long getCommunityCrimePreventionPamphlets() {
		return communityCrimePreventionPamphlets;
	}

	public void setCommunityCrimePreventionPamphlets(Long communityCrimePreventionPamphlets) {
		this.communityCrimePreventionPamphlets = communityCrimePreventionPamphlets;
	}

	public Long getCommunityEvents() {
		return communityEvents;
	}

	public void setCommunityEvents(Long communityEvents) {
		this.communityEvents = communityEvents;
	}

	public Long getCommunityCptedInspections() {
		return communityCptedInspections;
	}

	public void setCommunityCptedInspections(Long communityCptedInspections) {
		this.communityCptedInspections = communityCptedInspections;
	}

	public Long getCommunityCrimePreventionSeminars() {
		return communityCrimePreventionSeminars;
	}

	public void setCommunityCrimePreventionSeminars(Long communityCrimePreventionSeminars) {
		this.communityCrimePreventionSeminars = communityCrimePreventionSeminars;
	}

	public Long getFootPatrolCount() {
		return footPatrolCount;
	}

	public void setFootPatrolCount(Long footPatrolCount) {
		this.footPatrolCount = footPatrolCount;
	}

	public Integer getHikePatrolCount() {
		return hikePatrolCount;
	}

	public void setHikePatrolCount(Integer hikePatrolCount) {
		this.hikePatrolCount = hikePatrolCount;
	}

	public Long getDutyMinutes() {
		return dutyMinutes;
	}

	public void setDutyMinutes(Long dutyMinutes) {
		this.dutyMinutes = dutyMinutes;
	}
	
	public Double getPoints() {
		return new Double(
			(this.hikePatrolCount * 1) +
			(this.crimeArrestsFelony * 3) +
			(this.crimeArrestsClassAbMisdemeanor * 2) +
			(this.crimeArrestsClassCTicket * 1) +
			(this.crimeArrestsTrafficDrt * 1) +
			(this.warrantsCity * 1) +
			(this.warrantsFelony * 2) +
			(this.warrantsMisdemeanor * 1) +
			(this.warrantsSetcic * 1) +
			(this.drtInvestigationsWarnings * 1) +
			(this.drtInvestigationsAbatements * 1) +
			(this.drtInvestigationsTickets * 1) +
			(this.drtInvestigationsOffenseReports * 0) +
			(this.fieldParking * 0) +
			(this.fieldChargesFiled * 0) +
			(this.fieldSuspectsInJail * 1) +
			(this.fieldHolds * 1) +
			(this.fieldTrafficStops * 2) +
			(this.trafficMoving * 1) +
			(this.trafficNonMoving * 0) +
			(this.primaryCalls * 2) +
			(this.secondaryCalls * 1) +
			(this.onViewsFlaggedDown * 2) +
			(this.incidentReports * 1) +
			(this.accidentReports * 1) +
			(this.supplementReports * 1) +
			(this.crimeInitiatives * 2) +
			(this.crimeInitiativesInWcVehicle * 1) +
			(this.adminAssignments * 1) +
			(this.amChecklistCompleted * 1) +
			(this.businessChecksCompletedEast * 1) +
			(this.businessChecksCompletedWest * 1) +
			(this.communityApartmentLiaisonMeetings * 1) +
			(this.communityHotelLiaisonMeetings * 1) +
			(this.communityRetailLiaisonMeetings * 1) +
			(this.communityOfficeBuildingLiasonMeetings * 1) +
			(this.communityCitizenContacts * 1) +
			(this.communityCrimePreventionPamphlets * 0) +
			(this.communityEvents * 2) +
			(this.communityCptedInspections * 4) +
			(this.communityCrimePreventionSeminars * 3)
		);
	}
	
	public Double getScore() {
		if (this.dutyMinutes == null || this.dutyMinutes.doubleValue() <= 0) {
			return new Double(0);
		}
		return new Double(getPoints().doubleValue() / this.dutyMinutes.doubleValue());
	}

}
