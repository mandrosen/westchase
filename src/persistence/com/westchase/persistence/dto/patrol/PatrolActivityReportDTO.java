package com.westchase.persistence.dto.patrol;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.westchase.persistence.model.Officer;

public class PatrolActivityReportDTO implements Serializable {
	
	private Officer officer;
	
	private String dutyHours;
	
	private Long miles;

	private String hikePatrolHours;
	
	private Long generalPatrolCount;
	private Long bikePatrolCount;
	private Long aptInitCount;
	private Long specialOpsCount;
	private Long eventCount;
	private Long otherCount;

	private Long crimeArrestsFelony;
	private Long crimeArrestsClassAbMisdemeanor;
	private Long crimeArrestsClassCTicket;
	private Long crimeArrestsTrafficDrt;
	private Long warrantsCity;
	private Long warrantsFelony;
	private Long warrantsMisdemeanor;
	private Long warrantsSetcic;
	private Long drtInvestigationsWarnings;
	private Long drtInvestigationsAbatements;
	private Long drtInvestigationsTickets;
	private Long drtInvestigationsOffenseReports;
	private Long fieldParking;
	private Long fieldChargesFiled;
	private Long fieldSuspectsInJail;
	private Long fieldHolds;
	private Long fieldTrafficStops;
	private Long trafficMoving;
	private Long trafficNonMoving;
	
	private Long primaryCalls;
	private Long secondaryCalls;
	private Long onViewsFlaggedDown;
	private Long incidentReports;
	private Long accidentReports;
	private Long supplementReports;
	private Long crimeInitiatives;
	private Long crimeInitiativesInWcVehicle;
	private Long adminAssignments;
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
			Long dutyHours,
			Long miles, 
			Long hikePatrolHours,
			
			Long generalPatrolCount,
			Long bikePatrolCount,
			Long aptInitCount,
			Long specialOpsCount,
			Long eventCount,
			Long otherCount,
			
			Long crimeArrestsFelony, 
			Long crimeArrestsClassAbMisdemeanor,
			Long crimeArrestsClassCTicket, 
			Long crimeArrestsTrafficDrt, 
			Long warrantsCity, 
			Long warrantsFelony,
			Long warrantsMisdemeanor, 
			Long warrantsSetcic, 
			Long drtInvestigationsWarnings,
			Long drtInvestigationsAbatements, 
			Long drtInvestigationsTickets, 
			Long drtInvestigationsOffenseReports,
			Long fieldParking, 
			Long fieldChargesFiled, 
			Long fieldSuspectsInJail, 
			Long fieldHolds,
			Long fieldTrafficStops, 
			Long trafficMoving, 
			Long trafficNonMoving, 
			Long primaryCalls, 
			Long secondaryCalls,
			Long onViewsFlaggedDown, 
			Long incidentReports, 
			Long accidentReports, 
			Long supplementReports,
			Long crimeInitiatives, 
			Long crimeInitiativesInWcVehicle, 
			Long adminAssignments, 
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
		this.dutyHours = convertMinutesToHoursMinutes(dutyHours);
		this.miles = miles;
		this.hikePatrolHours = convertMinutesToHoursMinutes(hikePatrolHours);

		this.generalPatrolCount = generalPatrolCount;
		this.bikePatrolCount = bikePatrolCount;
		this.aptInitCount =  aptInitCount;
		this.specialOpsCount = specialOpsCount;
		this.eventCount = eventCount;
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
	
	// used to debug hql load issue
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
	public Long getCrimeArrestsFelony() {
		return crimeArrestsFelony;
	}

	public void setCrimeArrestsFelony(Long crimeArrestsFelony) {
		this.crimeArrestsFelony = crimeArrestsFelony;
	}

	public Long getCrimeArrestsClassAbMisdemeanor() {
		return crimeArrestsClassAbMisdemeanor;
	}

	public void setCrimeArrestsClassAbMisdemeanor(Long crimeArrestsClassAbMisdemeanor) {
		this.crimeArrestsClassAbMisdemeanor = crimeArrestsClassAbMisdemeanor;
	}

	public Long getCrimeArrestsClassCTicket() {
		return crimeArrestsClassCTicket;
	}

	public void setCrimeArrestsClassCTicket(Long crimeArrestsClassCTicket) {
		this.crimeArrestsClassCTicket = crimeArrestsClassCTicket;
	}

	public Long getCrimeArrestsTrafficDrt() {
		return crimeArrestsTrafficDrt;
	}

	public void setCrimeArrestsTrafficDrt(Long crimeArrestsTrafficDrt) {
		this.crimeArrestsTrafficDrt = crimeArrestsTrafficDrt;
	}

	public Long getWarrantsCity() {
		return warrantsCity;
	}

	public void setWarrantsCity(Long warrantsCity) {
		this.warrantsCity = warrantsCity;
	}

	public Long getWarrantsFelony() {
		return warrantsFelony;
	}

	public void setWarrantsFelony(Long warrantsFelony) {
		this.warrantsFelony = warrantsFelony;
	}

	public Long getWarrantsMisdemeanor() {
		return warrantsMisdemeanor;
	}

	public void setWarrantsMisdemeanor(Long warrantsMisdemeanor) {
		this.warrantsMisdemeanor = warrantsMisdemeanor;
	}

	public Long getWarrantsSetcic() {
		return warrantsSetcic;
	}

	public void setWarrantsSetcic(Long warrantsSetcic) {
		this.warrantsSetcic = warrantsSetcic;
	}

	public Long getDrtInvestigationsWarnings() {
		return drtInvestigationsWarnings;
	}

	public void setDrtInvestigationsWarnings(Long drtInvestigationsWarnings) {
		this.drtInvestigationsWarnings = drtInvestigationsWarnings;
	}

	public Long getDrtInvestigationsAbatements() {
		return drtInvestigationsAbatements;
	}

	public void setDrtInvestigationsAbatements(Long drtInvestigationsAbatements) {
		this.drtInvestigationsAbatements = drtInvestigationsAbatements;
	}

	public Long getDrtInvestigationsTickets() {
		return drtInvestigationsTickets;
	}

	public void setDrtInvestigationsTickets(Long drtInvestigationsTickets) {
		this.drtInvestigationsTickets = drtInvestigationsTickets;
	}

	public Long getDrtInvestigationsOffenseReports() {
		return drtInvestigationsOffenseReports;
	}

	public void setDrtInvestigationsOffenseReports(Long drtInvestigationsOffenseReports) {
		this.drtInvestigationsOffenseReports = drtInvestigationsOffenseReports;
	}

	public Long getFieldParking() {
		return fieldParking;
	}

	public void setFieldParking(Long fieldParking) {
		this.fieldParking = fieldParking;
	}

	public Long getFieldChargesFiled() {
		return fieldChargesFiled;
	}

	public void setFieldChargesFiled(Long fieldChargesFiled) {
		this.fieldChargesFiled = fieldChargesFiled;
	}

	public Long getFieldSuspectsInJail() {
		return fieldSuspectsInJail;
	}

	public void setFieldSuspectsInJail(Long fieldSuspectsInJail) {
		this.fieldSuspectsInJail = fieldSuspectsInJail;
	}

	public Long getFieldHolds() {
		return fieldHolds;
	}

	public void setFieldHolds(Long fieldHolds) {
		this.fieldHolds = fieldHolds;
	}

	public Long getFieldTrafficStops() {
		return fieldTrafficStops;
	}

	public void setFieldTrafficStops(Long fieldTrafficStops) {
		this.fieldTrafficStops = fieldTrafficStops;
	}

	public Long getTrafficMoving() {
		return trafficMoving;
	}

	public void setTrafficMoving(Long trafficMoving) {
		this.trafficMoving = trafficMoving;
	}

	public Long getTrafficNonMoving() {
		return trafficNonMoving;
	}

	public void setTrafficNonMoving(Long trafficNonMoving) {
		this.trafficNonMoving = trafficNonMoving;
	}

	public Long getPrimaryCalls() {
		return primaryCalls;
	}

	public void setPrimaryCalls(Long primaryCalls) {
		this.primaryCalls = primaryCalls;
	}

	public Long getSecondaryCalls() {
		return secondaryCalls;
	}

	public void setSecondaryCalls(Long secondaryCalls) {
		this.secondaryCalls = secondaryCalls;
	}

	public Long getOnViewsFlaggedDown() {
		return onViewsFlaggedDown;
	}

	public void setOnViewsFlaggedDown(Long onViewsFlaggedDown) {
		this.onViewsFlaggedDown = onViewsFlaggedDown;
	}

	public Long getIncidentReports() {
		return incidentReports;
	}

	public void setIncidentReports(Long incidentReports) {
		this.incidentReports = incidentReports;
	}

	public Long getAccidentReports() {
		return accidentReports;
	}

	public void setAccidentReports(Long accidentReports) {
		this.accidentReports = accidentReports;
	}

	public Long getSupplementReports() {
		return supplementReports;
	}

	public void setSupplementReports(Long supplementReports) {
		this.supplementReports = supplementReports;
	}

	public Long getCrimeInitiatives() {
		return crimeInitiatives;
	}

	public void setCrimeInitiatives(Long crimeInitiatives) {
		this.crimeInitiatives = crimeInitiatives;
	}

	public Long getCrimeInitiativesInWcVehicle() {
		return crimeInitiativesInWcVehicle;
	}

	public void setCrimeInitiativesInWcVehicle(Long crimeInitiativesInWcVehicle) {
		this.crimeInitiativesInWcVehicle = crimeInitiativesInWcVehicle;
	}

	public Long getAdminAssignments() {
		return adminAssignments;
	}

	public void setAdminAssignments(Long adminAssignments) {
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

}
