package com.westchase.web.action.patrol;

import java.text.SimpleDateFormat;
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
	
	private String receivedTime;
	private String arrivedTime;
	private String clearedTime;
	
	public PatrolActivityDetailAction() {
		super();
	}
	
	private void setTimeForSave() {
		SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
		if (currentPatrolActivityDetail != null) {
			try {
				currentPatrolActivityDetail.setReceivedTime(timeFormat.parse(receivedTime));
			} catch (Exception e) {
				log.error("bad received time", e);
			}
			try {
				currentPatrolActivityDetail.setArrivedTime(timeFormat.parse(arrivedTime));
			} catch (Exception e) {
				log.error("bad arrived time", e);
			}
			try {
				currentPatrolActivityDetail.setClearedTime(timeFormat.parse(clearedTime));
			} catch (Exception e) {
				log.error("bad cleared time", e);
			}
		}
	}
	
	private void setupTime() {
		SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
		if (currentPatrolActivityDetail != null) {
			if (currentPatrolActivityDetail.getReceivedTime() != null) {
				setReceivedTime(timeFormat.format(currentPatrolActivityDetail.getReceivedTime()));
			}
			if (currentPatrolActivityDetail.getReceivedTime() != null) {
				setArrivedTime(timeFormat.format(currentPatrolActivityDetail.getArrivedTime()));
			}
			if (currentPatrolActivityDetail.getReceivedTime() != null) {
				setClearedTime(timeFormat.format(currentPatrolActivityDetail.getClearedTime()));
			}
		}
	}
	
	@Override
	public void prepare() throws Exception {
		PatrolService patrolServ = ServiceLocator.lookupPatrolService();
		if (patrolServ != null) {

			if (patrolActivityDetailId != null) {
				currentPatrolActivityDetail = patrolServ.getActivityDetail(patrolActivityDetailId);
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
			
			availableDetailCategories = patrolServ.listDetailCategories();
			availableDetailTypes = patrolServ.listDetailTypes();
			availableProperties = patrolServ.listProperties();
		}
	}
	
	public String execute() {
		return SUCCESS;
	}
	
	public String save() {
		Long savedId = null;
		if (currentPatrolActivityDetail != null) {
			setTimeForSave();
			try {
				PatrolService patrolServ = ServiceLocator.lookupPatrolService();
				if (patrolServ != null) {
					savedId = patrolServ.saveOrUpdateActivityDetail(currentPatrolActivityDetail, selectedCitizens);
				}
			} catch (Exception e) {
				log.error("", e);
			}
		}
		if (savedId != null && savedId.longValue() > 0) {
			addActionMessage("Saved record #" + savedId);
		} else {
			addActionMessage("Unable to save object");
			return INPUT;
		}
		if (currentPatrolActivityDetail != null && currentPatrolActivityDetail.getPatrolActivity() != null) {
			setPatrolActivityId(currentPatrolActivityDetail.getPatrolActivity().getId());
		}
		return SUCCESS;
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
}
