package com.westchase.persistence.model;

// Generated Nov 14, 2012 9:29:30 PM by Hibernate Tools 3.3.0.GA

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PatrolActivity generated by hbm2java
 */
@Entity
@Table(name = "patrol_activity")
public class PatrolActivity implements java.io.Serializable {

	private Long id;
	private Officer officer;
	private int patrolOfficerCount;
	private PatrolShop patrolShop;
	private PatrolPhone patrolPhone;
	private PatrolType patrolType;
	private String patrolTypeDesc;
	private String patrolShopComments;
	private Date startDateTime;
	private Date endDateTime;
	private int startMiles;
	private int endMiles;
	private int crimeArrestsFelony;
	private int crimeArrestsClassAbMisdemeanor;
	private int crimeArrestsClassCTicket;
	private int crimeArrestsTrafficDrt;
	private int warrantsCity;
	private int warrantsFelony;
	private int warrantsMisdemeanor;
	private int warrantsSetcic;
	private int drtInvestigationsWarnings;
	private int drtInvestigationsAbatements;
	private int drtInvestigationsTickets;
	private int drtInvestigationsOffenseReports;
	private int fieldParking;
	private int fieldChargesFiled;
	private int fieldSuspectsInJail;
	private int fieldHolds;
	private int fieldTrafficStops;
	private int trafficMoving;
	private int trafficNonMoving;
	
	private Date hikePatrolledDateTimeStart1;
	private Date hikePatrolledDateTimeEnd1;
	private Date hikePatrolledDateTimeStart2;
	private Date hikePatrolledDateTimeEnd2;
	private Date hikePatrolledDateTimeStart3;
	private Date hikePatrolledDateTimeEnd3;
	
	private int primaryCalls;
	private int secondaryCalls;
	private int onViewsFlaggedDown;
	private int incidentReports;
	private int accidentReports;
	private int supplementReports;
	private int crimeInitiatives;
	private int crimeInitiativesInWcVehicle;
	private int adminAssignments;
	private boolean amChecklistCompleted;
	private boolean businessChecksCompletedEast;
	private boolean businessChecksCompletedWest;
	private int communityApartmentLiaisonMeetings;
	private int communityHotelLiaisonMeetings;
	private int communityRetailLiaisonMeetings;
	private int communityOfficeBuildingLiasonMeetings;
	private int communityCitizenContacts;
	private int communityCrimePreventionPamphlets;
	private int communityEvents;
	private int communityCptedInspections;
	private int communityCrimePreventionSeminars;
	private boolean deleted;
	private Set<PatrolActivityDetail> patrolActivityDetails = new HashSet<PatrolActivityDetail>(0);
	private Set<PatrolActivityHotspot> patrolActivityHotspots = new HashSet<PatrolActivityHotspot>(0);
	private Set<PatrolActivityOfficer> patrolActivityOfficers = new HashSet<PatrolActivityOfficer>(0);

	public PatrolActivity() {
		super();
	}
	
	public PatrolActivity(Long id) {
		this();
		this.id = id;
	}

	public PatrolActivity(Officer officer, PatrolShop patrolShop, PatrolPhone patrolPhone, PatrolType patrolType,
			Date startDateTime, Date endDateTime, int startMiles, int endMiles, int crimeArrestsFelony,
			int crimeArrestsClassAbMisdemeanor, int crimeArrestsClassCTicket, int crimeArrestsTrafficDrt,
			int warrantsCity, int warrantsFelony, int warrantsMisdemeanor, int warrantsSetcic,
			int drtInvestigationsWarnings, int drtInvestigationsAbatements, int drtInvestigationsTickets,
			int drtInvestigationsOffenseReports, int fieldParking, int fieldChargesFiled, int fieldSuspectsInJail,
			int fieldHolds, int fieldTrafficStops, int trafficMoving, int trafficNonMoving, 
			Date hikePatrolledDateTimeStart1, Date hikePatrolledDateTimeEnd1,
			Date hikePatrolledDateTimeStart2, Date hikePatrolledDateTimeEnd2, 
			Date hikePatrolledDateTimeStart3, Date hikePatrolledDateTimeEnd3, 
			int primaryCalls, int secondaryCalls, int onViewsFlaggedDown, int incidentReports, int accidentReports,
			int supplementReports, int crimeInitiatives, int crimeInitiativesInWcVehicle, int adminAssignments,
			boolean amChecklistCompleted, boolean businessChecksCompletedEast, boolean businessChecksCompletedWest, 
			int communityApartmentLiaisonMeetings, int communityHotelLiaisonMeetings, int communityRetailLiaisonMeetings,
			int communityOfficeBuildingLiasonMeetings, int communityCitizenContacts,
			int communityCrimePreventionPamphlets, int communityEvents, int communityCptedInspections,
			int communityCrimePreventionSeminars, boolean deleted) {
		this();
		this.officer = officer;
		this.patrolShop = patrolShop;
		this.patrolPhone = patrolPhone;
		this.patrolType = patrolType;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.startMiles = startMiles;
		this.endMiles = endMiles;
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
		this.hikePatrolledDateTimeStart1 = hikePatrolledDateTimeStart1;
		this.hikePatrolledDateTimeEnd1 = hikePatrolledDateTimeEnd1;
		this.hikePatrolledDateTimeStart2 = hikePatrolledDateTimeStart2;
		this.hikePatrolledDateTimeEnd2 = hikePatrolledDateTimeEnd2;
		this.hikePatrolledDateTimeStart3 = hikePatrolledDateTimeStart1;
		this.hikePatrolledDateTimeEnd3 = hikePatrolledDateTimeEnd3;
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
		this.deleted = deleted;
	}

	public PatrolActivity(Officer officer, PatrolShop patrolShop, PatrolPhone patrolPhone, PatrolType patrolType,
			String patrolTypeDesc, Date startDateTime, Date endDateTime, int startMiles, int endMiles,
			int crimeArrestsFelony, int crimeArrestsClassAbMisdemeanor, int crimeArrestsClassCTicket,
			int crimeArrestsTrafficDrt, int warrantsCity, int warrantsFelony, int warrantsMisdemeanor,
			int warrantsSetcic, int drtInvestigationsWarnings, int drtInvestigationsAbatements,
			int drtInvestigationsTickets, int drtInvestigationsOffenseReports, int fieldParking, int fieldChargesFiled,
			int fieldSuspectsInJail, int fieldHolds, int fieldTrafficStops, int trafficMoving, int trafficNonMoving,
			int primaryCalls, int secondaryCalls, int onViewsFlaggedDown, int incidentReports, int accidentReports,
			int supplementReports, int crimeInitiatives, int crimeInitiativesInWcVehicle, int adminAssignments,
			boolean amChecklistCompleted, boolean businessChecksCompletedEast, boolean businessChecksCompletedWest,  
			int communityApartmentLiaisonMeetings, int communityHotelLiaisonMeetings, int communityRetailLiaisonMeetings,
			int communityOfficeBuildingLiasonMeetings, int communityCitizenContacts,
			int communityCrimePreventionPamphlets, int communityEvents, int communityCptedInspections,
			int communityCrimePreventionSeminars, boolean deleted, Set<PatrolActivityDetail> patrolActivityDetails) {
		this();
		this.officer = officer;
		this.patrolShop = patrolShop;
		this.patrolPhone = patrolPhone;
		this.patrolType = patrolType;
		this.patrolTypeDesc = patrolTypeDesc;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.startMiles = startMiles;
		this.endMiles = endMiles;
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
		this.deleted = deleted;
		this.patrolActivityDetails = patrolActivityDetails;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "officer_id", nullable = true)
	public Officer getOfficer() {
		return this.officer;
	}

	public void setOfficer(Officer officer) {
		this.officer = officer;
	}

	@Column(name = "patrol_officer_count")
	public int getPatrolOfficerCount() {
		return patrolOfficerCount;
	}

	public void setPatrolOfficerCount(int patrolOfficerCount) {
		this.patrolOfficerCount = patrolOfficerCount;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "patrol_shop_id", nullable = false)
	public PatrolShop getPatrolShop() {
		return this.patrolShop;
	}

	public void setPatrolShop(PatrolShop patrolShop) {
		this.patrolShop = patrolShop;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "patrol_phone_id", nullable = true)
	public PatrolPhone getPatrolPhone() {
		return this.patrolPhone;
	}

	public void setPatrolPhone(PatrolPhone patrolPhone) {
		this.patrolPhone = patrolPhone;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "patrol_type_id", nullable = false)
	public PatrolType getPatrolType() {
		return this.patrolType;
	}

	public void setPatrolType(PatrolType patrolType) {
		this.patrolType = patrolType;
	}

	@Column(name = "patrol_type_desc")
	public String getPatrolTypeDesc() {
		return this.patrolTypeDesc;
	}

	public void setPatrolTypeDesc(String patrolTypeDesc) {
		this.patrolTypeDesc = patrolTypeDesc;
	}

	@Column(name = "patrol_shop_comments")
	public String getPatrolShopComments() {
		return this.patrolShopComments;
	}

	public void setPatrolShopComments(String patrolShopComments) {
		this.patrolShopComments = patrolShopComments;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date_time", nullable = false, length = 19)
	public Date getStartDateTime() {
		return this.startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date_time", nullable = false, length = 19)
	public Date getEndDateTime() {
		return this.endDateTime;
	}

	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}

	@Column(name = "start_miles", nullable = false)
	public int getStartMiles() {
		return this.startMiles;
	}

	public void setStartMiles(int startMiles) {
		this.startMiles = startMiles;
	}

	@Column(name = "end_miles", nullable = false)
	public int getEndMiles() {
		return this.endMiles;
	}

	public void setEndMiles(int endMiles) {
		this.endMiles = endMiles;
	}

	@Column(name = "crime_arrests_felony", nullable = false)
	public int getCrimeArrestsFelony() {
		return this.crimeArrestsFelony;
	}

	public void setCrimeArrestsFelony(int crimeArrestsFelony) {
		this.crimeArrestsFelony = crimeArrestsFelony;
	}

	@Column(name = "crime_arrests_class_ab_misdemeanor", nullable = false)
	public int getCrimeArrestsClassAbMisdemeanor() {
		return this.crimeArrestsClassAbMisdemeanor;
	}

	public void setCrimeArrestsClassAbMisdemeanor(int crimeArrestsClassAbMisdemeanor) {
		this.crimeArrestsClassAbMisdemeanor = crimeArrestsClassAbMisdemeanor;
	}

	@Column(name = "crime_arrests_class_c_ticket", nullable = false)
	public int getCrimeArrestsClassCTicket() {
		return this.crimeArrestsClassCTicket;
	}

	public void setCrimeArrestsClassCTicket(int crimeArrestsClassCTicket) {
		this.crimeArrestsClassCTicket = crimeArrestsClassCTicket;
	}

	@Column(name = "crime_arrests_traffic_drt", nullable = false)
	public int getCrimeArrestsTrafficDrt() {
		return this.crimeArrestsTrafficDrt;
	}

	public void setCrimeArrestsTrafficDrt(int crimeArrestsTrafficDrt) {
		this.crimeArrestsTrafficDrt = crimeArrestsTrafficDrt;
	}

	@Column(name = "warrants_city", nullable = false)
	public int getWarrantsCity() {
		return this.warrantsCity;
	}

	public void setWarrantsCity(int warrantsCity) {
		this.warrantsCity = warrantsCity;
	}

	@Column(name = "warrants_felony", nullable = false)
	public int getWarrantsFelony() {
		return this.warrantsFelony;
	}

	public void setWarrantsFelony(int warrantsFelony) {
		this.warrantsFelony = warrantsFelony;
	}

	@Column(name = "warrants_misdemeanor", nullable = false)
	public int getWarrantsMisdemeanor() {
		return this.warrantsMisdemeanor;
	}

	public void setWarrantsMisdemeanor(int warrantsMisdemeanor) {
		this.warrantsMisdemeanor = warrantsMisdemeanor;
	}

	@Column(name = "warrants_setcic", nullable = false)
	public int getWarrantsSetcic() {
		return this.warrantsSetcic;
	}

	public void setWarrantsSetcic(int warrantsSetcic) {
		this.warrantsSetcic = warrantsSetcic;
	}

	@Column(name = "drt_investigations_warnings", nullable = false)
	public int getDrtInvestigationsWarnings() {
		return this.drtInvestigationsWarnings;
	}

	public void setDrtInvestigationsWarnings(int drtInvestigationsWarnings) {
		this.drtInvestigationsWarnings = drtInvestigationsWarnings;
	}

	@Column(name = "drt_investigations_abatements", nullable = false)
	public int getDrtInvestigationsAbatements() {
		return this.drtInvestigationsAbatements;
	}

	public void setDrtInvestigationsAbatements(int drtInvestigationsAbatements) {
		this.drtInvestigationsAbatements = drtInvestigationsAbatements;
	}

	@Column(name = "drt_investigations_tickets", nullable = false)
	public int getDrtInvestigationsTickets() {
		return this.drtInvestigationsTickets;
	}

	public void setDrtInvestigationsTickets(int drtInvestigationsTickets) {
		this.drtInvestigationsTickets = drtInvestigationsTickets;
	}

	@Column(name = "drt_investigations_offense_reports", nullable = false)
	public int getDrtInvestigationsOffenseReports() {
		return this.drtInvestigationsOffenseReports;
	}

	public void setDrtInvestigationsOffenseReports(int drtInvestigationsOffenseReports) {
		this.drtInvestigationsOffenseReports = drtInvestigationsOffenseReports;
	}

	@Column(name = "field_parking", nullable = false)
	public int getFieldParking() {
		return this.fieldParking;
	}

	public void setFieldParking(int fieldParking) {
		this.fieldParking = fieldParking;
	}

	@Column(name = "field_charges_filed", nullable = false)
	public int getFieldChargesFiled() {
		return this.fieldChargesFiled;
	}

	public void setFieldChargesFiled(int fieldChargesFiled) {
		this.fieldChargesFiled = fieldChargesFiled;
	}

	@Column(name = "field_suspects_in_jail", nullable = false)
	public int getFieldSuspectsInJail() {
		return this.fieldSuspectsInJail;
	}

	public void setFieldSuspectsInJail(int fieldSuspectsInJail) {
		this.fieldSuspectsInJail = fieldSuspectsInJail;
	}

	@Column(name = "field_holds", nullable = false)
	public int getFieldHolds() {
		return this.fieldHolds;
	}

	public void setFieldHolds(int fieldHolds) {
		this.fieldHolds = fieldHolds;
	}

	@Column(name = "field_traffic_stops", nullable = false)
	public int getFieldTrafficStops() {
		return this.fieldTrafficStops;
	}

	public void setFieldTrafficStops(int fieldTrafficStops) {
		this.fieldTrafficStops = fieldTrafficStops;
	}

	@Column(name = "traffic_moving", nullable = false)
	public int getTrafficMoving() {
		return this.trafficMoving;
	}

	public void setTrafficMoving(int trafficMoving) {
		this.trafficMoving = trafficMoving;
	}

	@Column(name = "traffic_non_moving", nullable = false)
	public int getTrafficNonMoving() {
		return this.trafficNonMoving;
	}

	public void setTrafficNonMoving(int trafficNonMoving) {
		this.trafficNonMoving = trafficNonMoving;
	}
	
	
	@Column(name = "hike_patrolled_date_time_start1", length = 19)
	public Date getHikePatrolledDateTimeStart1() {
		return hikePatrolledDateTimeStart1;
	}

	public void setHikePatrolledDateTimeStart1(Date hikePatrolledDateTimeStart1) {
		this.hikePatrolledDateTimeStart1 = hikePatrolledDateTimeStart1;
	}

	@Column(name = "hike_patrolled_date_time_end1", length = 19)
	public Date getHikePatrolledDateTimeEnd1() {
		return hikePatrolledDateTimeEnd1;
	}

	public void setHikePatrolledDateTimeEnd1(Date hikePatrolledDateTimeEnd1) {
		this.hikePatrolledDateTimeEnd1 = hikePatrolledDateTimeEnd1;
	}
	

	@Column(name = "hike_patrolled_date_time_start2", length = 19)
	public Date getHikePatrolledDateTimeStart2() {
		return hikePatrolledDateTimeStart2;
	}

	public void setHikePatrolledDateTimeStart2(Date hikePatrolledDateTimeStart2) {
		this.hikePatrolledDateTimeStart2 = hikePatrolledDateTimeStart2;
	}

	@Column(name = "hike_patrolled_date_time_end2", length = 19)
	public Date getHikePatrolledDateTimeEnd2() {
		return hikePatrolledDateTimeEnd2;
	}

	public void setHikePatrolledDateTimeEnd2(Date hikePatrolledDateTimeEnd2) {
		this.hikePatrolledDateTimeEnd2 = hikePatrolledDateTimeEnd2;
	}
	

	@Column(name = "hike_patrolled_date_time_start3", length = 19)
	public Date getHikePatrolledDateTimeStart3() {
		return hikePatrolledDateTimeStart3;
	}

	public void setHikePatrolledDateTimeStart3(Date hikePatrolledDateTimeStart3) {
		this.hikePatrolledDateTimeStart3 = hikePatrolledDateTimeStart3;
	}

	@Column(name = "hike_patrolled_date_time_end3", length = 19)
	public Date getHikePatrolledDateTimeEnd3() {
		return hikePatrolledDateTimeEnd3;
	}

	public void setHikePatrolledDateTimeEnd3(Date hikePatrolledDateTimeEnd3) {
		this.hikePatrolledDateTimeEnd3 = hikePatrolledDateTimeEnd3;
	}
	
	
	@Column(name = "primary_calls", nullable = false)
	public int getPrimaryCalls() {
		return this.primaryCalls;
	}

	public void setPrimaryCalls(int primaryCalls) {
		this.primaryCalls = primaryCalls;
	}

	@Column(name = "secondary_calls", nullable = false)
	public int getSecondaryCalls() {
		return this.secondaryCalls;
	}

	public void setSecondaryCalls(int secondaryCalls) {
		this.secondaryCalls = secondaryCalls;
	}

	@Column(name = "on_views_flagged_down", nullable = false)
	public int getOnViewsFlaggedDown() {
		return this.onViewsFlaggedDown;
	}

	public void setOnViewsFlaggedDown(int onViewsFlaggedDown) {
		this.onViewsFlaggedDown = onViewsFlaggedDown;
	}

	@Column(name = "incident_reports", nullable = false)
	public int getIncidentReports() {
		return this.incidentReports;
	}

	public void setIncidentReports(int incidentReports) {
		this.incidentReports = incidentReports;
	}

	@Column(name = "accident_reports", nullable = false)
	public int getAccidentReports() {
		return this.accidentReports;
	}

	public void setAccidentReports(int accidentReports) {
		this.accidentReports = accidentReports;
	}

	@Column(name = "supplement_reports", nullable = false)
	public int getSupplementReports() {
		return this.supplementReports;
	}

	public void setSupplementReports(int supplementReports) {
		this.supplementReports = supplementReports;
	}

	@Column(name = "crime_initiatives", nullable = false)
	public int getCrimeInitiatives() {
		return this.crimeInitiatives;
	}

	public void setCrimeInitiatives(int crimeInitiatives) {
		this.crimeInitiatives = crimeInitiatives;
	}

	@Column(name = "crime_initiatives_in_wc_vehicle", nullable = false)
	public int getCrimeInitiativesInWcVehicle() {
		return this.crimeInitiativesInWcVehicle;
	}

	public void setCrimeInitiativesInWcVehicle(int crimeInitiativesInWcVehicle) {
		this.crimeInitiativesInWcVehicle = crimeInitiativesInWcVehicle;
	}

	@Column(name = "admin_assignments", nullable = false)
	public int getAdminAssignments() {
		return this.adminAssignments;
	}

	public void setAdminAssignments(int adminAssignments) {
		this.adminAssignments = adminAssignments;
	}

	@Column(name = "am_checklist_completed", nullable = false)
	public boolean getAmChecklistCompleted() {
		return this.amChecklistCompleted;
	}

	public void setAmChecklistCompleted(boolean amChecklistCompleted) {
		this.amChecklistCompleted = amChecklistCompleted;
	}

	@Column(name = "business_checks_completed_east", nullable = false)
	public boolean getBusinessChecksCompletedEast() {
		return this.businessChecksCompletedEast;
	}

	public void setBusinessChecksCompletedEast(boolean businessChecksCompletedEast) {
		this.businessChecksCompletedEast = businessChecksCompletedEast;
	}

	@Column(name = "business_checks_completed_west", nullable = false)
	public boolean getBusinessChecksCompletedWest() {
		return this.businessChecksCompletedWest;
	}

	public void setBusinessChecksCompletedWest(boolean businessChecksCompletedWest) {
		this.businessChecksCompletedWest = businessChecksCompletedWest;
	}

	@Column(name = "community_apartment_liaison_meetings", nullable = false)
	public int getCommunityApartmentLiaisonMeetings() {
		return this.communityApartmentLiaisonMeetings;
	}

	public void setCommunityApartmentLiaisonMeetings(int communityApartmentLiaisonMeetings) {
		this.communityApartmentLiaisonMeetings = communityApartmentLiaisonMeetings;
	}

	@Column(name = "community_hotel_liaison_meetings", nullable = false)
	public int getCommunityHotelLiaisonMeetings() {
		return this.communityHotelLiaisonMeetings;
	}

	public void setCommunityHotelLiaisonMeetings(int communityHotelLiaisonMeetings) {
		this.communityHotelLiaisonMeetings = communityHotelLiaisonMeetings;
	}

	@Column(name = "community_retail_liaison_meetings", nullable = false)
	public int getCommunityRetailLiaisonMeetings() {
		return this.communityRetailLiaisonMeetings;
	}

	public void setCommunityRetailLiaisonMeetings(int communityRetailLiaisonMeetings) {
		this.communityRetailLiaisonMeetings = communityRetailLiaisonMeetings;
	}

	@Column(name = "community_office_building_liason_meetings", nullable = false)
	public int getCommunityOfficeBuildingLiasonMeetings() {
		return this.communityOfficeBuildingLiasonMeetings;
	}

	public void setCommunityOfficeBuildingLiasonMeetings(int communityOfficeBuildingLiasonMeetings) {
		this.communityOfficeBuildingLiasonMeetings = communityOfficeBuildingLiasonMeetings;
	}

	@Column(name = "community_citizen_contacts", nullable = false)
	public int getCommunityCitizenContacts() {
		return this.communityCitizenContacts;
	}

	public void setCommunityCitizenContacts(int communityCitizenContacts) {
		this.communityCitizenContacts = communityCitizenContacts;
	}

	@Column(name = "community_crime_prevention_pamphlets", nullable = false)
	public int getCommunityCrimePreventionPamphlets() {
		return this.communityCrimePreventionPamphlets;
	}

	public void setCommunityCrimePreventionPamphlets(int communityCrimePreventionPamphlets) {
		this.communityCrimePreventionPamphlets = communityCrimePreventionPamphlets;
	}

	@Column(name = "community_events", nullable = false)
	public int getCommunityEvents() {
		return this.communityEvents;
	}

	public void setCommunityEvents(int communityEvents) {
		this.communityEvents = communityEvents;
	}

	@Column(name = "community_cpted_inspections", nullable = false)
	public int getCommunityCptedInspections() {
		return this.communityCptedInspections;
	}

	public void setCommunityCptedInspections(int communityCptedInspections) {
		this.communityCptedInspections = communityCptedInspections;
	}

	@Column(name = "community_crime_prevention_seminars", nullable = false)
	public int getCommunityCrimePreventionSeminars() {
		return this.communityCrimePreventionSeminars;
	}

	public void setCommunityCrimePreventionSeminars(int communityCrimePreventionSeminars) {
		this.communityCrimePreventionSeminars = communityCrimePreventionSeminars;
	}

	@Column(name = "deleted", nullable = false)
	public boolean isDeleted() {
		return this.deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patrolActivity")
	public Set<PatrolActivityDetail> getPatrolActivityDetails() {
		return this.patrolActivityDetails;
	}

	public void setPatrolActivityDetails(Set<PatrolActivityDetail> patrolActivityDetails) {
		this.patrolActivityDetails = patrolActivityDetails;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patrolActivity")
	public Set<PatrolActivityHotspot> getPatrolActivityHotspots() {
		return this.patrolActivityHotspots;
	}

	public void setPatrolActivityHotspots(Set<PatrolActivityHotspot> patrolActivityHotspots) {
		this.patrolActivityHotspots = patrolActivityHotspots;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patrolActivity")
	public Set<PatrolActivityOfficer> getPatrolActivityOfficers() {
		return this.patrolActivityOfficers;
	}

	public void setPatrolActivityOfficers(Set<PatrolActivityOfficer> patrolActivityOfficers) {
		this.patrolActivityOfficers = patrolActivityOfficers;
	}

}
