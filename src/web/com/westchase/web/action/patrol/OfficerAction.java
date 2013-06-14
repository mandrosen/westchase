package com.westchase.web.action.patrol;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.westchase.ejb.AuditService;
import com.westchase.ejb.PatrolService;
import com.westchase.persistence.criteria.OfficerSearchCriteria;
import com.westchase.persistence.model.Employee;
import com.westchase.persistence.model.Officer;
import com.westchase.utils.FormatUtils;
import com.westchase.utils.ejb.ServiceLocator;
import com.westchase.web.action.cms.AbstractCMSAction;

public class OfficerAction extends AbstractCMSAction<Officer, OfficerSearchCriteria> {
    
    private static final String LAST_OFFICER_SEARCH = "LAST_OFFICER_SEARCH";
    
    private Officer searchObject;
    
	private List<Officer> officers;
	
	private Integer officerId;
	
	private Officer currentOfficer;
	
	public OfficerAction() {
		super();
	}

	@Override
	public void prepare() throws Exception {
		PatrolService patrolServ = ServiceLocator.lookupPatrolService();
		if (patrolServ != null) {
			if (officerId != null) {
				currentOfficer = patrolServ.getOfficer(officerId);
			}
		}
	}

	public String sort() {
    	setSearchObject((Officer) getRequest().getSession(true).getAttribute(getLastSearchAttributeName()));
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
    	setSearchObject((Officer) getRequest().getSession(true).getAttribute(getLastSearchAttributeName()));
    	refresh();
    	return SUCCESS;
	}

	@Override
	protected void runSearch(OfficerSearchCriteria criteria) {
		PatrolService patrolServ = ServiceLocator.lookupPatrolService();
        if (patrolServ != null) {
        	officers = patrolServ.findAll(criteria);
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
		OfficerSearchCriteria criteria = new OfficerSearchCriteria();
    	criteria.setSearchObject(getSearchObject());
    	criteria.setPage(getPage());
    	criteria.setNumberOfResults(getNumberOfResults());
    	criteria.setOrderDir(getOrderDir());
    	
        if (StringUtils.isNotBlank(getOrderCol())) {
        	criteria.setOrderCol(getOrderCol());
        	setCurrentOrderCol(getOrderCol());
        } else {
        	criteria.setOrderCol(getCurrentOrderCol());
        }

        
        // store for 'back to list' functionality
        getRequest().getSession(true).setAttribute(getLastSearchAttributeName() + SESSION_ATTR_NAME, criteria);
        
        runSearch(criteria);
	}

	@Override
	public String getLastSearchAttributeName() {
		return LAST_OFFICER_SEARCH;
	}

    public String save() throws Exception {
		String error = null;
		PatrolService patrolServ = ServiceLocator.lookupPatrolService();
		AuditService audServ = ServiceLocator.lookupAuditService();
        if (patrolServ != null && currentOfficer != null) {
        	Employee emp = getEmployee();
        	if (emp != null && emp.getId() != null) {
        		
        		currentOfficer.setCellPhone(FormatUtils.formatPhoneNumber(currentOfficer.getCellPhone()));

        		setOfficerId(patrolServ.saveOrUpdateOfficer(currentOfficer));

	            if (audServ != null && officerId != null) {
	            	int employeeId = emp.getId().intValue();
	        		audServ.save(employeeId, currentOfficer);
	            }
        	} else {
        		error = "Session error: can't determine logged in employee.";
        	}
        }
        if (StringUtils.isNotBlank(error)) {
        	addActionError(error);
        	return INPUT;
        } else if (officerId == null || officerId.longValue() <= 0) {
        	addActionError("Unable to save record.");
        	return INPUT;
        } else {
        	addActionMessage("Saved record #" + officerId);
        	
        }
        return SUCCESS;
    }

	public Officer getSearchObject() {
		return searchObject;
	}

	public void setSearchObject(Officer searchObject) {
		this.searchObject = searchObject;
	}

	public List<Officer> getOfficers() {
		return officers;
	}

	public void setOfficers(List<Officer> officers) {
		this.officers = officers;
	}

	public Integer getOfficerId() {
		return officerId;
	}

	public void setOfficerId(Integer officerId) {
		this.officerId = officerId;
	}

	public Officer getCurrentOfficer() {
		return currentOfficer;
	}

	public void setCurrentOfficer(Officer currentOfficer) {
		this.currentOfficer = currentOfficer;
	}

	/**
	 * TODO: figure out why validation.xml file is not getting called
	 * and why addFieldError is not working (error not showing up on JSP)
	 */
	@Override
	public void validate() {
		if (StringUtils.isBlank(currentOfficer.getFirstName())) {
//			addFieldError("currentCitizen.firstName","First Name is required");
			addActionError("First name is required");
		}
		if (StringUtils.isBlank(currentOfficer.getLastName())) {
//			addFieldError("currentCitizen.lastName","Last Name is required");
			addActionError("Last name is reqired");
		}
	}
}
