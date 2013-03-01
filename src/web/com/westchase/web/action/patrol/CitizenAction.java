package com.westchase.web.action.patrol;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.westchase.ejb.AuditService;
import com.westchase.ejb.PatrolService;
import com.westchase.persistence.criteria.CitizenSearchCriteria;
import com.westchase.persistence.model.Citizen;
import com.westchase.persistence.model.Employee;
import com.westchase.utils.ejb.ServiceLocator;
import com.westchase.web.action.cms.AbstractCMSAction;

public class CitizenAction extends AbstractCMSAction<Citizen, CitizenSearchCriteria> {
    
    private static final String LAST_CITIZEN_SEARCH = "LAST_CITIZEN_SEARCH";
    
    private Citizen searchObject;
    
	private List<Citizen> citizens;
	
	private Long citizenId;
	
	private Citizen currentCitizen;

	public CitizenAction() {
		super();
	}

	@Override
	public void prepare() throws Exception {
		PatrolService patrolServ = ServiceLocator.lookupPatrolService();
		if (patrolServ != null) {
			if (citizenId != null) {
				currentCitizen = patrolServ.getCitizen(citizenId);
			}
		}
	}

	@Override
	public String goToPage(int page) {
		setPage(page);
    	setSearchObject((Citizen) getRequest().getSession(true).getAttribute(getLastSearchAttributeName()));
    	refresh();
    	return SUCCESS;
	}

	@Override
	protected void runSearch(CitizenSearchCriteria criteria) {
		PatrolService patrolServ = ServiceLocator.lookupPatrolService();
        if (patrolServ != null) {
        	citizens = patrolServ.findAll(criteria);
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
		CitizenSearchCriteria criteria = new CitizenSearchCriteria();
    	criteria.setSearchObject(getSearchObject());
    	criteria.setPage(getPage());
    	criteria.setNumberOfResults(getNumberOfResults());
    	criteria.setOrderCol(getOrderCol());
    	criteria.setOrderDir(getOrderDir());

        
        // store for 'back to list' functionality
        getRequest().getSession(true).setAttribute(getLastSearchAttributeName() + SESSION_ATTR_NAME, criteria);
        
        runSearch(criteria);
	}

	@Override
	public String getLastSearchAttributeName() {
		return LAST_CITIZEN_SEARCH;
	}

    public String save() throws Exception {
		String error = null;
		PatrolService patrolServ = ServiceLocator.lookupPatrolService();
		AuditService audServ = ServiceLocator.lookupAuditService();
        if (patrolServ != null && currentCitizen != null) {
        	Employee emp = getEmployee();
        	if (emp != null && emp.getId() != null) {

        		setCitizenId(patrolServ.saveOrUpdateCitizen(currentCitizen));

	            if (audServ != null && citizenId != null) {
	            	int employeeId = emp.getId().intValue();
	        		audServ.save(employeeId, currentCitizen);
	            }
        	} else {
        		error = "Session error: can't determine logged in employee.";
        	}
        }
        if (StringUtils.isNotBlank(error)) {
        	addActionError(error);
        	return INPUT;
        } else if (citizenId == null || citizenId.longValue() <= 0) {
        	addActionError("Unable to save record.");
        	return INPUT;
        } else {
        	addActionMessage("Saved record #" + citizenId);
        	
        }
        return SUCCESS;
    }

	public Citizen getSearchObject() {
		return searchObject;
	}

	public void setSearchObject(Citizen searchObject) {
		this.searchObject = searchObject;
	}

	public List<Citizen> getCitizens() {
		return citizens;
	}

	public void setCitizens(List<Citizen> citizens) {
		this.citizens = citizens;
	}

	public Long getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(Long citizenId) {
		this.citizenId = citizenId;
	}

	public Citizen getCurrentCitizen() {
		return currentCitizen;
	}

	public void setCurrentCitizen(Citizen currentCitizen) {
		this.currentCitizen = currentCitizen;
	}

	/**
	 * TODO: figure out why validation.xml file is not getting called
	 * and why addFieldError is not working (error not showing up on JSP)
	 */
	@Override
	public void validate() {
		if (StringUtils.isBlank(currentCitizen.getFirstName())) {
//			addFieldError("currentCitizen.firstName","First Name is required");
			addActionError("First name is required");
		}
		if (StringUtils.isBlank(currentCitizen.getLastName())) {
//			addFieldError("currentCitizen.lastName","Last Name is required");
			addActionError("Last name is required");
		}
	}

}
