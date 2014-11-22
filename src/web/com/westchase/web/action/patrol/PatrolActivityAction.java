package com.westchase.web.action.patrol;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.westchase.ejb.AuditService;
import com.westchase.ejb.PatrolService;
import com.westchase.persistence.criteria.PatrolActivitySearchCriteria;
import com.westchase.persistence.model.Employee;
import com.westchase.persistence.model.Officer;
import com.westchase.persistence.model.PatrolActivity;
import com.westchase.persistence.model.PatrolActivityDetail;
import com.westchase.persistence.model.PatrolHotspot;
import com.westchase.persistence.model.PatrolPhone;
import com.westchase.persistence.model.PatrolShop;
import com.westchase.persistence.model.PatrolType;
import com.westchase.utils.DateUtils;
import com.westchase.utils.ejb.ServiceLocator;
import com.westchase.web.action.cms.AbstractCMSAction;

public class PatrolActivityAction extends AbstractCMSAction<PatrolActivity, PatrolActivitySearchCriteria> {

    private static final String LAST_PATROL_ACTIVITY_SEARCH = "LAST_PATROL_ACTIVITY_SEARCH";

    private PatrolActivity searchObject;

	private List<PatrolActivity> patrolActivities;

	private Long patrolActivityId;
    private Long delId;
	private PatrolActivity currentPatrolActivity;
	private List<Officer> currentOfficers;
	private List<PatrolActivityDetail> currentPatrolActivityDetails;

	private List<Officer> availableOfficers;
	private List<PatrolType> availablePatrolTypes;
	private List<PatrolShop> availablePatrolShops;
	private List<PatrolPhone> availablePatrolPhones;
	private List<PatrolHotspot> availablePatrolHotspotsEast;
	private List<PatrolHotspot> availablePatrolHotspotsWest;
	private List<Integer> availableCounts;
	private List<Integer> availableCountsBig;

	private String startDate;
	private String startTime;
	private String endDate;
	private String endTime;

	private String hikeDateStart1;
	private String hikeTimeStart1;
	private String hikeDateEnd1;
	private String hikeTimeEnd1;

	private String hikeDateStart2;
	private String hikeTimeStart2;
	private String hikeDateEnd2;
	private String hikeTimeEnd2;

	private String hikeDateStart3;
	private String hikeTimeStart3;
	private String hikeDateEnd3;
	private String hikeTimeEnd3;
	
	private List<Integer> selectedOfficerIdList;
	private List<Integer> patrolTypeIdList;
	private List<Integer> hotspotIdListEast;
	private List<Integer> hotspotIdListWest;

	public PatrolActivityAction() {
		super();
		setOrderCol("startDateTime");
		setOrderDir("desc");
	}

	private void setupDateAndTime() {
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
		if (currentPatrolActivity != null) {
			if (currentPatrolActivity.getStartDateTime() != null) {
				try {
					setStartDate(dateTimeFormat.format(currentPatrolActivity.getStartDateTime()));
					setStartTime(timeFormat.format(currentPatrolActivity.getStartDateTime()));
				} catch (Exception e) {
					log.error("error formatting start date", e);
				}
			}
			if (currentPatrolActivity.getEndDateTime() != null) {
				try {
					setEndDate(dateTimeFormat.format(currentPatrolActivity.getEndDateTime()));
					setEndTime(timeFormat.format(currentPatrolActivity.getEndDateTime()));
				} catch (Exception e) {
					log.error("error formatting end date", e);
				}
			}

			// hike/bike1
			if (currentPatrolActivity.getHikePatrolledDateTimeStart1() != null) {
				try {
					setHikeDateStart1(dateTimeFormat.format(currentPatrolActivity.getHikePatrolledDateTimeStart1()));
					setHikeTimeStart1(timeFormat.format(currentPatrolActivity.getHikePatrolledDateTimeStart1()));
				} catch (Exception e) {
					log.error("error formatting hike start 1", e);
				}
			}
			if (currentPatrolActivity.getHikePatrolledDateTimeEnd1() != null) {
				try {
					setHikeDateEnd1(dateTimeFormat.format(currentPatrolActivity.getHikePatrolledDateTimeEnd1()));
					setHikeTimeEnd1(timeFormat.format(currentPatrolActivity.getHikePatrolledDateTimeEnd1()));
				} catch (Exception e) {
					log.error("error formatting hike end 1", e);
				}
			}

			// hike/bike2
			if (currentPatrolActivity.getHikePatrolledDateTimeStart2() != null) {
				try {
					setHikeDateStart2(dateTimeFormat.format(currentPatrolActivity.getHikePatrolledDateTimeStart2()));
					setHikeTimeStart2(timeFormat.format(currentPatrolActivity.getHikePatrolledDateTimeStart2()));
				} catch (Exception e) {
					log.error("error formatting hike start 2", e);
				}
			}
			if (currentPatrolActivity.getHikePatrolledDateTimeEnd2() != null) {
				try {
					setHikeDateEnd2(dateTimeFormat.format(currentPatrolActivity.getHikePatrolledDateTimeEnd2()));
					setHikeTimeEnd2(timeFormat.format(currentPatrolActivity.getHikePatrolledDateTimeEnd2()));
				} catch (Exception e) {
					log.error("error formatting hike end 2", e);
				}
			}

			// hike/bike3
			if (currentPatrolActivity.getHikePatrolledDateTimeStart3() != null) {
				try {
					setHikeDateStart3(dateTimeFormat.format(currentPatrolActivity.getHikePatrolledDateTimeStart3()));
					setHikeTimeStart3(timeFormat.format(currentPatrolActivity.getHikePatrolledDateTimeStart3()));
				} catch (Exception e) {
					log.error("error formatting hike start 3", e);
				}
			}
			if (currentPatrolActivity.getHikePatrolledDateTimeEnd3() != null) {
				try {
					setHikeDateEnd3(dateTimeFormat.format(currentPatrolActivity.getHikePatrolledDateTimeEnd3()));
					setHikeTimeEnd3(timeFormat.format(currentPatrolActivity.getHikePatrolledDateTimeEnd3()));
				} catch (Exception e) {
					log.error("error formatting hike end 3", e);
				}
			}
		}
	}
	
//	private String formatTime(String time) {
//		String timeStr = "12:00";
//		if (StringUtils.isNotBlank(time) && time.length() == 4) {
//			timeStr = time.substring(0, 2) + ":" + time.substring(2, 4);
//		}
//		return timeStr;
//	}
//
//	private Date getDateTime(final String date, final String time) {
//		Date d = null;
//		if (StringUtils.isNotBlank(date) && StringUtils.isNotBlank(time)) {
//			String datePart = StringUtils.substringBefore(date, "T");
//			//String timePart = StringUtils.substringBeforeLast(StringUtils.substringAfter(time, "T"), "-");
//			String timePart = formatTime(time);
//			SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//			try {
//				d = dateTimeFormat.parse(datePart + "T" + timePart + ":00");
//			} catch (Exception e) {
//				log.error("", e);
//			}
//		}
//		return d;
//	}

	private void setDateAndTimeForSave() {
		if (currentPatrolActivity != null) {
			currentPatrolActivity.setStartDateTime(DateUtils.getDateTime(startDate, startTime));
			currentPatrolActivity.setEndDateTime(DateUtils.getDateTime(endDate, endTime));

			currentPatrolActivity.setHikePatrolledDateTimeStart1(DateUtils.getDateTime(hikeDateStart1, hikeTimeStart1));
			currentPatrolActivity.setHikePatrolledDateTimeEnd1(DateUtils.getDateTime(hikeDateEnd1, hikeTimeEnd1));

			currentPatrolActivity.setHikePatrolledDateTimeStart2(DateUtils.getDateTime(hikeDateStart2, hikeTimeStart2));
			currentPatrolActivity.setHikePatrolledDateTimeEnd2(DateUtils.getDateTime(hikeDateEnd2, hikeTimeEnd2));

			currentPatrolActivity.setHikePatrolledDateTimeStart3(DateUtils.getDateTime(hikeDateStart3, hikeTimeStart3));
			currentPatrolActivity.setHikePatrolledDateTimeEnd3(DateUtils.getDateTime(hikeDateEnd3, hikeTimeEnd3));

		}
	}

	private String validateDateTime() {
		StringBuffer error = new StringBuffer();
		if (currentPatrolActivity.getStartDateTime() == null) {
			error.append("Start date and start time are required.");
		}
		if (currentPatrolActivity.getEndDateTime() == null) {
			error.append("End date and end time are required.");
		} else if (!currentPatrolActivity.getStartDateTime().before(currentPatrolActivity.getEndDateTime())) {
			error.append("Start date must occur before end date.");
		}
		if (StringUtils.isBlank(error.toString())) {
			// start and end date are valid: check the hike and bike patrol times

			// both start and end required for hike and bike if start or end is entered (i.e. can't have start without end or vice versa)
			if ((currentPatrolActivity.getHikePatrolledDateTimeStart1() == null && currentPatrolActivity.getHikePatrolledDateTimeEnd1() != null) ||
				(currentPatrolActivity.getHikePatrolledDateTimeStart1() != null && currentPatrolActivity.getHikePatrolledDateTimeEnd1() == null)) {
				error.append("Hike start and end are required if one is entered.");
			}

			if ((currentPatrolActivity.getHikePatrolledDateTimeStart2() == null && currentPatrolActivity.getHikePatrolledDateTimeEnd2() != null) ||
					(currentPatrolActivity.getHikePatrolledDateTimeStart2() != null && currentPatrolActivity.getHikePatrolledDateTimeEnd2() == null)) {
				error.append("Hike start and end are required if one is entered.");
			}

			if ((currentPatrolActivity.getHikePatrolledDateTimeStart3() == null && currentPatrolActivity.getHikePatrolledDateTimeEnd3() != null) ||
					(currentPatrolActivity.getHikePatrolledDateTimeStart3() != null && currentPatrolActivity.getHikePatrolledDateTimeEnd3() == null)) {
				error.append("Hike start and end are required if one is entered.");
			}



			if (StringUtils.isBlank(error.toString())) {
				error.append(checkHikeBikeValues(currentPatrolActivity.getHikePatrolledDateTimeStart1(), currentPatrolActivity.getHikePatrolledDateTimeEnd1()));
				error.append(checkHikeBikeValues(currentPatrolActivity.getHikePatrolledDateTimeStart2(), currentPatrolActivity.getHikePatrolledDateTimeEnd2()));
				error.append(checkHikeBikeValues(currentPatrolActivity.getHikePatrolledDateTimeStart3(), currentPatrolActivity.getHikePatrolledDateTimeEnd3()));
			}

		}
		return error.toString();
	}

	private String checkHikeBikeValues(Date start, Date end) {
		StringBuffer error = new StringBuffer();

		// hike/bike start can't occur before duty start
		if (start!= null && start.before(currentPatrolActivity.getStartDateTime())) {
			error.append("Hike start date must occur after duty start date.");
		}
		if (start != null && start.before(currentPatrolActivity.getStartDateTime())) {
			error.append("Bike start date must occur after duty start date.");
		}

		// hike/bike end can't occur after duty end
		if (end != null && end.after(currentPatrolActivity.getEndDateTime())) {
			error.append("Hike end date must occur before duty end date.");
		}
		if (end != null && end.after(currentPatrolActivity.getEndDateTime())) {
			error.append("Bike end date must occur before duty end date.");
		}

		// check for consistency
		if (start != null && !start.before(end)) {
			error.append("Hike start must occur before hike end.");
		}
		if (start != null && !start.before(end)) {
			error.append("Bike start must occur before bike end.");
		}

		return error.toString();
	}

	@Override
	public void prepare() throws Exception {
		PatrolService patrolServ = ServiceLocator.lookupPatrolService();
		if (patrolServ != null) {
			if (patrolActivityId != null) {
				currentPatrolActivity = patrolServ.getActivity(patrolActivityId);
				if (currentPatrolActivity != null) {
					currentOfficers = patrolServ.listActivityOfficers(patrolActivityId);
					currentPatrolActivityDetails = patrolServ.findActivityDetail(patrolActivityId);
					hotspotIdListEast = patrolServ.getHotspotIdList(patrolActivityId, false);
					hotspotIdListWest = patrolServ.getHotspotIdList(patrolActivityId, true);
				}
				setupDateAndTime();
			}

			availablePatrolTypes = patrolServ.listPatrolTypes();
			availablePatrolShops = patrolServ.listPatrolShops();
			availablePatrolPhones = patrolServ.listPatrolPhones();
			availableOfficers = patrolServ.listOfficers();
			availablePatrolHotspotsEast = patrolServ.listPatrolHotspots(false);
			availablePatrolHotspotsWest = patrolServ.listPatrolHotspots(true);

			availableCounts = new ArrayList<Integer>();
			for (int i = 1; i <= 100; i++) {
				availableCounts.add(new Integer(i));
			}

			availableCountsBig = new ArrayList<Integer>();
			for (int i = 1; i <= 1000; i++) {
				availableCountsBig.add(new Integer(i));
			}
		}
	}

	public String sort() {
    	setSearchObject((PatrolActivity) getRequest().getSession(true).getAttribute(getLastSearchAttributeName()));
    	if (StringUtils.isBlank(getCurrentOrderCol())) {
    		setOrderDir("asc");
    	} else if (getCurrentOrderCol().equals(getOrderCol())) {
    		setOrderDir("desc");
    	} else {
    		setOrderDir("asc");
    	}
		setPage(0);
    	refresh();
    	return SUCCESS;
    }

	@Override
	public String goToPage(int page) {
		setPage(page);
    	setSearchObject((PatrolActivity) getRequest().getSession(true).getAttribute(getLastSearchAttributeName()));
    	refresh();
    	return SUCCESS;
	}

	@Override
	public void runSearch(PatrolActivitySearchCriteria criteria) {
		PatrolService patrolServ = ServiceLocator.lookupPatrolService();
        if (patrolServ != null) {
        	patrolActivities = patrolServ.findAll(criteria);
			long count = patrolServ.findAllCount(criteria);
			setMax(count);
        }
	}

	@Override
	public String list() {
    	if (getUseLast() > 0) {
    		refreshLast();
    	} else {
    		getRequest().getSession(true).setAttribute(getLastSearchAttributeName(), getSearchObject());
    		refresh();
    	}
    	return SUCCESS;
	}

	@Override
	public void refresh() {
		PatrolActivitySearchCriteria criteria = new PatrolActivitySearchCriteria();
        criteria.setSearchObject(getSearchObject());
        
        criteria.setPatrolTypeIdList(patrolTypeIdList);
        
        criteria.setPage(getPage());
        criteria.setNumberOfResults(getNumberOfResults());
        if (StringUtils.isNotBlank(getOrderCol())) {
        	criteria.setOrderCol(getOrderCol());
        	setCurrentOrderCol(getOrderCol());
        } else {
        	criteria.setOrderCol(getCurrentOrderCol());
        }
        criteria.setOrderDir(getOrderDir());
        if (StringUtils.isNotBlank(startDate)) {
        	try {
        		criteria.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(startDate));
        	} catch (Exception e) {
        		log.error("", e);
        	}
        }
        if (StringUtils.isNotBlank(endDate)) {
        	try {
        		criteria.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
        	} catch (Exception e) {
        		log.error("", e);
        	}
        }

        // store for 'back to list' functionality
        getRequest().getSession(true).setAttribute(getLastSearchAttributeName() + SESSION_ATTR_NAME, criteria);

        runSearch(criteria);
	}

	@Override
	public String getLastSearchAttributeName() {
		return LAST_PATROL_ACTIVITY_SEARCH;
	}

	public String save() throws Exception {
		String error = null;
		PatrolService patrolServ = ServiceLocator.lookupPatrolService();
		AuditService audServ = ServiceLocator.lookupAuditService();
        if (patrolServ != null && currentPatrolActivity != null) {

        	setDateAndTimeForSave();
        	String dateTimeError = validateDateTime();
        	if (StringUtils.isNotBlank(dateTimeError)) {
        		error = dateTimeError;
        	} else {

	        	Employee emp = getEmployee();
	        	if (emp != null && emp.getId() != null) {

	        		// TODO: update this for multiple officers per activity
	        		List<PatrolActivity> possibleConflicts = patrolServ.listOtherByOfficerAndDay(currentPatrolActivity);
	        		if (possibleConflicts != null && !possibleConflicts.isEmpty()) {
	        			StringBuffer conflictStr = new StringBuffer();
	        			for (PatrolActivity pa : possibleConflicts) {
	        				conflictStr.append("<a href='/westchase/patrol/editActivity-").append(pa.getId()).append(".action' target='_pa").append(pa.getId()).append("'>").append(pa.getId()).append("</a>,");
	        			}
	        			getRequest().getSession(true).setAttribute("WCActionWarning", "This record overlaps with another patrol.  Conflicts: " + conflictStr);
	        		}

	        		setPatrolActivityId(patrolServ.saveOrUpdateActivity(selectedOfficerIdList, currentPatrolActivity, hotspotIdListEast, hotspotIdListWest));

	        		if (patrolActivityId == null) {
	        			error = "Unable to save Patrol Activity.  Make sure required fields are filled and the time does not overlap another patrol for this officer.";
	        		} else {
	        			// success
	        			if (audServ != null) {
	        				int employeeId = emp.getId().intValue();
	        				audServ.save(employeeId, currentPatrolActivity, hotspotIdListEast, hotspotIdListWest);
	        			}
		            }
	        	} else {
	        		error = "Session error: can't determine logged in employee.";
	        	}
        	}
        } else {
        	error = "Missing data to save.";
        }
        if (StringUtils.isNotBlank(error)) {
        	addActionError(error);
        	return INPUT;
        } else if (patrolActivityId == null || patrolActivityId.longValue() <= 0) {
        	addActionError("Unable to save record.");
        	return INPUT;
        } else {
        	getRequest().getSession(true).setAttribute("WCActionMessage", "Saved record #" + patrolActivityId);

        }
        return SUCCESS;
    }

	public String delete() throws Exception {
		PatrolService patrolServ = ServiceLocator.lookupPatrolService();
		AuditService audServ = ServiceLocator.lookupAuditService();
        Integer empId = null;
        Employee emp = getEmployee();
        if (emp != null) {
        	empId = emp.getId();
        }
        if (empId != null && empId.longValue() > 0 && delId != null && delId.longValue() > 0) {
        	if (patrolServ != null) {
        		patrolServ.deleteActivity(delId);
        		getRequest().getSession(true).setAttribute("message", "Deleted Patrol #" + delId);
        		if (audServ != null) {
        			audServ.deletePatrolActivity(empId, delId);
        		}
        	}
        }
		return SUCCESS;
	}

	public PatrolActivity getSearchObject() {
		return searchObject;
	}

	public void setSearchObject(PatrolActivity searchObject) {
		this.searchObject = searchObject;
	}

	public List<PatrolActivity> getPatrolActivities() {
		return patrolActivities;
	}

	public void setPatrolActivities(List<PatrolActivity> patrolActivities) {
		this.patrolActivities = patrolActivities;
	}

	public Long getPatrolActivityId() {
		return patrolActivityId;
	}

	public void setPatrolActivityId(Long patrolActivityId) {
		this.patrolActivityId = patrolActivityId;
	}

	public PatrolActivity getCurrentPatrolActivity() {
		return currentPatrolActivity;
	}

	public void setCurrentPatrolActivity(PatrolActivity currentPatrolActivity) {
		this.currentPatrolActivity = currentPatrolActivity;
	}

	public List<PatrolActivityDetail> getCurrentPatrolActivityDetails() {
		return currentPatrolActivityDetails;
	}

	public void setCurrentPatrolActivityDetails(List<PatrolActivityDetail> currentPatrolActivityDetails) {
		this.currentPatrolActivityDetails = currentPatrolActivityDetails;
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

	public List<PatrolShop> getAvailablePatrolShops() {
		return availablePatrolShops;
	}

	public void setAvailablePatrolShops(List<PatrolShop> availablePatrolShops) {
		this.availablePatrolShops = availablePatrolShops;
	}

	public List<PatrolPhone> getAvailablePatrolPhones() {
		return availablePatrolPhones;
	}

	public void setAvailablePatrolPhones(List<PatrolPhone> availablePatrolPhones) {
		this.availablePatrolPhones = availablePatrolPhones;
	}

	public List<Integer> getAvailableCounts() {
		return availableCounts;
	}

	public void setAvailableCounts(List<Integer> availableCounts) {
		this.availableCounts = availableCounts;
	}

	public List<Integer> getAvailableCountsBig() {
		return availableCountsBig;
	}

	public void setAvailableCountsBig(List<Integer> availableCountsBig) {
		this.availableCountsBig = availableCountsBig;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

//	private boolean validTime(String timeStr) {
//		return StringUtils.isBlank(timeStr) || Pattern.matches("[0-2]?[0-9]:[0-5][0-9]", timeStr);
//	}



	public String getHikeDateStart1() {
		return hikeDateStart1;
	}

	public void setHikeDateStart1(String hikeDateStart1) {
		this.hikeDateStart1 = hikeDateStart1;
	}

	public String getHikeTimeStart1() {
		return hikeTimeStart1;
	}

	public void setHikeTimeStart1(String hikeTimeStart1) {
		this.hikeTimeStart1 = hikeTimeStart1;
	}

	public String getHikeDateEnd1() {
		return hikeDateEnd1;
	}

	public void setHikeDateEnd1(String hikeDateEnd1) {
		this.hikeDateEnd1 = hikeDateEnd1;
	}

	public String getHikeTimeEnd1() {
		return hikeTimeEnd1;
	}

	public void setHikeTimeEnd1(String hikeTimeEnd1) {
		this.hikeTimeEnd1 = hikeTimeEnd1;
	}

	public String getHikeDateStart2() {
		return hikeDateStart2;
	}

	public void setHikeDateStart2(String hikeDateStart2) {
		this.hikeDateStart2 = hikeDateStart2;
	}

	public String getHikeTimeStart2() {
		return hikeTimeStart2;
	}

	public void setHikeTimeStart2(String hikeTimeStart2) {
		this.hikeTimeStart2 = hikeTimeStart2;
	}

	public String getHikeDateEnd2() {
		return hikeDateEnd2;
	}

	public void setHikeDateEnd2(String hikeDateEnd2) {
		this.hikeDateEnd2 = hikeDateEnd2;
	}

	public String getHikeTimeEnd2() {
		return hikeTimeEnd2;
	}

	public void setHikeTimeEnd2(String hikeTimeEnd2) {
		this.hikeTimeEnd2 = hikeTimeEnd2;
	}

	public String getHikeDateStart3() {
		return hikeDateStart3;
	}

	public void setHikeDateStart3(String hikeDateStart3) {
		this.hikeDateStart3 = hikeDateStart3;
	}

	public String getHikeTimeStart3() {
		return hikeTimeStart3;
	}

	public void setHikeTimeStart3(String hikeTimeStart3) {
		this.hikeTimeStart3 = hikeTimeStart3;
	}

	public String getHikeDateEnd3() {
		return hikeDateEnd3;
	}

	public void setHikeDateEnd3(String hikeDateEnd3) {
		this.hikeDateEnd3 = hikeDateEnd3;
	}

	public String getHikeTimeEnd3() {
		return hikeTimeEnd3;
	}

	public void setHikeTimeEnd3(String hikeTimeEnd3) {
		this.hikeTimeEnd3 = hikeTimeEnd3;
	}

	public Long getDelId() {
		return delId;
	}

	public void setDelId(Long delId) {
		this.delId = delId;
	}

	private boolean milesRequired() {
		int shopId = -1;

		if (currentPatrolActivity != null &&
				currentPatrolActivity.getPatrolShop() != null &&
				currentPatrolActivity.getPatrolShop().getId() != null) {
			shopId = currentPatrolActivity.getPatrolShop().getId().intValue();
		}
		// only required for 1, 2, 3, 4, and P (5)
		return shopId == 1 || shopId == 2 || shopId == 3 || shopId == 4;
	}

	/**
	 * TODO: figure out why validation.xml file is not getting called
	 * and why addFieldError is not working (error not showing up on JSP)
	 */
	@Override
	public void validate() {
		if ((delId == null || delId.longValue() <= 0) && currentPatrolActivity != null) {
			if (CollectionUtils.isEmpty(selectedOfficerIdList)) {
	//			addFieldError("currentPatrolActivity.officer.id", "Officer is required");
				addActionError("Officer is required");
			}
			if (currentPatrolActivity.getPatrolShop() == null || currentPatrolActivity.getPatrolShop().getId() == null || currentPatrolActivity.getPatrolShop().getId().intValue() <= 0) {
	//			addFieldError("currentPatrolActivity.patrolShop.id", "Shop is required");
				addActionError("Shop is required");
			}
			if (currentPatrolActivity.getPatrolType() == null || currentPatrolActivity.getPatrolType().getId() == null || currentPatrolActivity.getPatrolType().getId().intValue() <= 0) {
	//			addFieldError("currentPatrolActivity.patrolType.id", "Type is required");
				addActionError("Type is required");
			}
			if (milesRequired()) {
				if (currentPatrolActivity.getStartMiles() <= 0) {
		//			addFieldError("currentPatrolActivity.startMiles", "Start miles is required");
					addActionError("Start Miles is required");
				}
				if (currentPatrolActivity.getEndMiles() <= 0) {
		//			addFieldError("currentPatrolActivity.endMiles", "End miles is required");
					addActionError("End Miles is required");
				}
			}
			if (currentPatrolActivity.getStartMiles() > 0 && currentPatrolActivity.getEndMiles() > 0 && currentPatrolActivity.getStartMiles() > currentPatrolActivity.getEndMiles()) {
	//			addFieldError("currentPatrolActivity.startMiles", "Start miles can not be greater than end miles");
				addActionError("Start miles can not be greater than end miles");
			}
		}
	}

	public List<Integer> getPatrolTypeIdList() {
		return patrolTypeIdList;
	}

	public void setPatrolTypeIdList(List<Integer> patrolTypeIdList) {
		this.patrolTypeIdList = patrolTypeIdList;
	}

	public List<PatrolHotspot> getAvailablePatrolHotspotsEast() {
		return availablePatrolHotspotsEast;
	}

	public void setAvailablePatrolHotspotsEast(List<PatrolHotspot> availablePatrolHotspotsEast) {
		this.availablePatrolHotspotsEast = availablePatrolHotspotsEast;
	}

	public List<PatrolHotspot> getAvailablePatrolHotspotsWest() {
		return availablePatrolHotspotsWest;
	}

	public void setAvailablePatrolHotspotsWest(List<PatrolHotspot> availablePatrolHotspotsWest) {
		this.availablePatrolHotspotsWest = availablePatrolHotspotsWest;
	}

	public List<Integer> getHotspotIdListEast() {
		return hotspotIdListEast;
	}

	public void setHotspotIdListEast(List<Integer> hotspotIdListEast) {
		this.hotspotIdListEast = hotspotIdListEast;
	}

	public List<Integer> getHotspotIdListWest() {
		return hotspotIdListWest;
	}

	public void setHotspotIdListWest(List<Integer> hotspotIdListWest) {
		this.hotspotIdListWest = hotspotIdListWest;
	}

	public List<Officer> getCurrentOfficers() {
		return currentOfficers;
	}

	public void setCurrentOfficers(List<Officer> currentOfficers) {
		this.currentOfficers = currentOfficers;
	}

	public List<Integer> getSelectedOfficerIdList() {
		return selectedOfficerIdList;
	}

	public void setSelectedOfficerIdList(List<Integer> selectedOfficerIdList) {
		this.selectedOfficerIdList = selectedOfficerIdList;
	}
}
