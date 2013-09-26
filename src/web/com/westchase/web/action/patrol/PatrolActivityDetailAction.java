package com.westchase.web.action.patrol;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.Preparable;
import com.westchase.ejb.PatrolService;
import com.westchase.persistence.dto.cms.PropertyDTO;
import com.westchase.persistence.model.Citizen;
import com.westchase.persistence.model.PatrolActivity;
import com.westchase.persistence.model.PatrolActivityDetail;
import com.westchase.persistence.model.PatrolDetailCategory;
import com.westchase.persistence.model.PatrolDetailType;
import com.westchase.persistence.model.Property;
import com.westchase.utils.DateUtils;
import com.westchase.utils.ejb.ServiceLocator;
import com.westchase.web.action.AbstractWestchaseAction;

public class PatrolActivityDetailAction extends AbstractWestchaseAction implements Preparable {

	private Long patrolActivityDetailId;
	private PatrolActivityDetail currentPatrolActivityDetail;
	private List<Citizen> currentCitizens;
	
	private Long patrolActivityId;
	
	private List<PatrolDetailCategory> availableDetailCategories;
	private List<PatrolDetailType> availableDetailTypes;
	private List<PropertyDTO> availableProperties;
	private List<Citizen> availableCitizens;
	
	private List<Long> selectedCitizens;
	
	private List<Date> availableDates;
	
	private Integer propertyIdByMapNum;
	private Integer propertyIdByAddr;
	
	private String receivedDate;
	private String arrivedDate;
	private String clearedDate;
	
	private String receivedTime;
	private String arrivedTime;
	private String clearedTime;
	
	public PatrolActivityDetailAction() {
		super();
	}
	
	private void setDateAndTimeForSave() {
//		SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
		if (currentPatrolActivityDetail != null) {
			try {
//				currentPatrolActivityDetail.setReceivedTime(timeFormat.parse(receivedTime));
				currentPatrolActivityDetail.setReceivedDateTime(DateUtils.getDateTime(receivedDate, receivedTime));
			} catch (Exception e) {
				log.error("bad received time", e);
			}
			try {
//				currentPatrolActivityDetail.setArrivedTime(timeFormat.parse(arrivedTime));
				currentPatrolActivityDetail.setArrivedDateTime(DateUtils.getDateTime(arrivedDate, arrivedTime));
			} catch (Exception e) {
				log.error("bad arrived time", e);
			}
			try {
//				currentPatrolActivityDetail.setClearedTime(timeFormat.parse(clearedTime));
				currentPatrolActivityDetail.setClearedDateTime(DateUtils.getDateTime(clearedDate, clearedTime));
			} catch (Exception e) {
				log.error("bad cleared time", e);
			}
		}
	}
	
	private void setupTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
		if (currentPatrolActivityDetail != null) {
			if (currentPatrolActivityDetail.getReceivedDateTime() != null) {
				setReceivedDate(dateFormat.format(currentPatrolActivityDetail.getReceivedDateTime()));
				setReceivedTime(timeFormat.format(currentPatrolActivityDetail.getReceivedDateTime()));
			}
			if (currentPatrolActivityDetail.getReceivedDateTime() != null) {
				setArrivedDate(dateFormat.format(currentPatrolActivityDetail.getArrivedDateTime()));
				setArrivedTime(timeFormat.format(currentPatrolActivityDetail.getArrivedDateTime()));
			}
			if (currentPatrolActivityDetail.getReceivedDateTime() != null) {
				setClearedDate(dateFormat.format(currentPatrolActivityDetail.getClearedDateTime()));
				setClearedTime(timeFormat.format(currentPatrolActivityDetail.getClearedDateTime()));
			}
		}
	}
	
	@Override
	public void prepare() throws Exception {
		PatrolService patrolServ = ServiceLocator.lookupPatrolService();
		if (patrolServ != null) {

			if (patrolActivityDetailId != null) {
				currentPatrolActivityDetail = patrolServ.getActivityDetail(patrolActivityDetailId);
				if (currentPatrolActivityDetail.getProperty() != null) {
					setPropertyIdByAddr(currentPatrolActivityDetail.getProperty().getId());
					setPropertyIdByMapNum(currentPatrolActivityDetail.getProperty().getId());
				}
				if (currentPatrolActivityDetail != null) {
					setPatrolActivityId(currentPatrolActivityDetail.getPatrolActivity().getId());
					currentCitizens = patrolServ.listActivityDetailCitizens(patrolActivityDetailId);
					availableCitizens = patrolServ.listNonSelectedCitizens(patrolActivityDetailId);
				}
				setupTime();
			} else if (patrolActivityId != null && patrolActivityId.longValue() > 0) {
				currentPatrolActivityDetail = new PatrolActivityDetail();
				currentPatrolActivityDetail.setPatrolActivity(new PatrolActivity(patrolActivityId));
				availableCitizens = patrolServ.listCitizens();
			}
			
			availableDates = patrolServ.listAvailableDates(getPatrolActivityId());
			
			availableDetailCategories = patrolServ.listDetailCategories();
			availableDetailTypes = patrolServ.listDetailTypes();
			availableProperties = patrolServ.listProperties();
		}
	}
	
	public String execute() {
		return SUCCESS;
	}
	
	public String save() {
		String error = null;
		Long savedId = null;
		if (currentPatrolActivityDetail != null) {
			
			Integer propertyId = getPropertyId();
			if (propertyId == null) {
				error = "One of the Property inputs are required.";
			} else {
				Property property = new Property(propertyId);
				currentPatrolActivityDetail.setProperty(property);
			

	        	setDateAndTimeForSave();
	        	String dateTimeError = validateDateTime();
	        	if (StringUtils.isNotBlank(dateTimeError)) {
	        		error = dateTimeError;
	        	} else {
					try {
						PatrolService patrolServ = ServiceLocator.lookupPatrolService();
						if (patrolServ != null) {
							savedId = patrolServ.saveOrUpdateActivityDetail(currentPatrolActivityDetail, selectedCitizens);
						}
					} catch (Exception e) {
						log.error("", e);
					}
	        	}
			}
		}
        if (StringUtils.isNotBlank(error)) {
        	addActionError(error);
        	return INPUT;
        }
		if (savedId != null && savedId.longValue() > 0) {
			addActionMessage("Saved record #" + savedId);
		} else {
//			addActionMessage("Unable to save object");
			addActionError("Unable to save object");
			return INPUT;
		}
		if (currentPatrolActivityDetail != null && currentPatrolActivityDetail.getPatrolActivity() != null) {
			setPatrolActivityId(currentPatrolActivityDetail.getPatrolActivity().getId());
		}
		return SUCCESS;
	}

	private Integer getPropertyId() {
		if (propertyIdByMapNum != null) {
			return propertyIdByMapNum;
		}
		if (propertyIdByAddr != null) {
			return propertyIdByAddr;
		}
		return null;
	}

	public Long getPatrolActivityDetailId() {
		return patrolActivityDetailId;
	}

	public void setPatrolActivityDetailId(Long patrolActivityDetailId) {
		this.patrolActivityDetailId = patrolActivityDetailId;
	}

	public PatrolActivityDetail getCurrentPatrolActivityDetail() {
		return currentPatrolActivityDetail;
	}

	public void setCurrentPatrolActivityDetail(PatrolActivityDetail currentPatrolActivityDetail) {
		this.currentPatrolActivityDetail = currentPatrolActivityDetail;
	}

	public Long getPatrolActivityId() {
		return patrolActivityId;
	}

	public void setPatrolActivityId(Long patrolActivityId) {
		this.patrolActivityId = patrolActivityId;
	}

	public List<PatrolDetailCategory> getAvailableDetailCategories() {
		return availableDetailCategories;
	}

	public void setAvailableDetailCategories(List<PatrolDetailCategory> availableDetailCategories) {
		this.availableDetailCategories = availableDetailCategories;
	}

	public List<PatrolDetailType> getAvailableDetailTypes() {
		return availableDetailTypes;
	}

	public void setAvailableDetailTypes(List<PatrolDetailType> availableDetailTypes) {
		this.availableDetailTypes = availableDetailTypes;
	}

	public List<PropertyDTO> getAvailableProperties() {
		return availableProperties;
	}

	public void setAvailableProperties(List<PropertyDTO> availableProperties) {
		this.availableProperties = availableProperties;
	}

	public List<Citizen> getAvailableCitizens() {
		return availableCitizens;
	}

	public void setAvailableCitizens(List<Citizen> availableCitizens) {
		this.availableCitizens = availableCitizens;
	}

	public List<Date> getAvailableDates() {
		return availableDates;
	}

	public void setAvailableDates(List<Date> availableDates) {
		this.availableDates = availableDates;
	}

	public List<Citizen> getCurrentCitizens() {
		return currentCitizens;
	}

	public void setCurrentCitizens(List<Citizen> currentCitizens) {
		this.currentCitizens = currentCitizens;
	}

	public List<Long> getSelectedCitizens() {
		return selectedCitizens;
	}

	public void setSelectedCitizens(List<Long> selectedCitizens) {
		this.selectedCitizens = selectedCitizens;
	}

	public String getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getArrivedDate() {
		return arrivedDate;
	}

	public void setArrivedDate(String arrivedDate) {
		this.arrivedDate = arrivedDate;
	}

	public String getClearedDate() {
		return clearedDate;
	}

	public void setClearedDate(String clearedDate) {
		this.clearedDate = clearedDate;
	}

	public String getReceivedTime() {
		return receivedTime;
	}

	public void setReceivedTime(String receivedTime) {
		this.receivedTime = receivedTime;
	}

	public String getArrivedTime() {
		return arrivedTime;
	}

	public void setArrivedTime(String arrivedTime) {
		this.arrivedTime = arrivedTime;
	}

	public String getClearedTime() {
		return clearedTime;
	}

	public void setClearedTime(String clearedTime) {
		this.clearedTime = clearedTime;
	}



	/**
	 * TODO: figure out why validation.xml file is not getting called
	 * and why addFieldError is not working (error not showing up on JSP)
	 */
	@Override
	public void validate() {
		if (StringUtils.isBlank(receivedDate)) {
//			addFieldError("receivedDate","Received date is required");
			addActionError("Received date is required");
		}
		if (StringUtils.isBlank(arrivedDate)) {
//			addFieldError("arrivedDate","Arrived date is required");
			addActionError("Arrived date is required");
		}
		if (StringUtils.isBlank(clearedDate)) {
//			addFieldError("clearedDate","Cleared date is required");
			addActionError("Cleared date is required");
		}
		if (StringUtils.isBlank(receivedTime)) {
//			addFieldError("receivedTime","Received time is required");
			addActionError("Received time is required");
		}
		if (StringUtils.isBlank(arrivedTime)) {
//			addFieldError("arrivedTime","Arrived time is required");
			addActionError("Arrived time is required");
		}
		if (StringUtils.isBlank(clearedTime)) {
//			addFieldError("clearedTime","Cleared time is required");
			addActionError("Cleared time is required");
		}
//		if (currentPatrolActivityDetail.getIncidentId() == null) {
////			addFieldError("currentPatrolActivityDetail.incidentId","Incident ID is required");
//			addActionError("Incident ID is required");
//		}
		if (currentPatrolActivityDetail.getPatrolDetailCategory() == null || currentPatrolActivityDetail.getPatrolDetailCategory().getId() == null || currentPatrolActivityDetail.getPatrolDetailCategory().getId().intValue() <= 0) { 
//			addFieldError("currentPatrolActivityDetail.patrolDetailCategory.id", "Category is required");
			addActionError("Category is required");
		}
		if (currentPatrolActivityDetail.getPatrolDetailType() == null || currentPatrolActivityDetail.getPatrolDetailType().getId() == null || currentPatrolActivityDetail.getPatrolDetailType().getId().intValue() <= 0) {
//			addFieldError("currentPatrolActivityDetail.patrolDetailType.id", "Type is required");
			addActionError("Type is required");
		}
	}
	
	private String validateDateTime() {
		StringBuffer error = new StringBuffer();
		if (currentPatrolActivityDetail.getReceivedDateTime() == null) {
			error.append("Received date and time are required.");
		}
		if (currentPatrolActivityDetail.getArrivedDateTime() == null) {
			error.append("Arrived date and time are required.");
		}
		if (currentPatrolActivityDetail.getClearedDateTime() == null) {
			error.append("Cleard date and time are required.");
		}
		if (error.length() > 0) {
			return error.toString();
		}
		if (currentPatrolActivityDetail.getArrivedDateTime().before(currentPatrolActivityDetail.getReceivedDateTime())) {
			error.append("Received date and time must occur before arrived date and time.");
		}
		if (currentPatrolActivityDetail.getClearedDateTime().before(currentPatrolActivityDetail.getArrivedDateTime())) {
			error.append("Arrived date and time must occur before clear date and time.");
		}
		return error.toString();
	}

	public Integer getPropertyIdByMapNum() {
		return propertyIdByMapNum;
	}

	public void setPropertyIdByMapNum(Integer propertyIdByMapNum) {
		this.propertyIdByMapNum = propertyIdByMapNum;
	}

	public Integer getPropertyIdByAddr() {
		return propertyIdByAddr;
	}

	public void setPropertyIdByAddr(Integer propertyIdByAddr) {
		this.propertyIdByAddr = propertyIdByAddr;
	}
}
