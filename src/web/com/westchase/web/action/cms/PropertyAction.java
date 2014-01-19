package com.westchase.web.action.cms;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.westchase.ejb.AuditService;
import com.westchase.ejb.CompanyService;
import com.westchase.ejb.PropertyService;
import com.westchase.persistence.criteria.PropertySearchCriteria;
import com.westchase.persistence.model.Company;
import com.westchase.persistence.model.Employee;
import com.westchase.persistence.model.FlagSize;
import com.westchase.persistence.model.Property;
import com.westchase.persistence.model.PropertyHcad;
import com.westchase.persistence.model.PropertyType;
import com.westchase.persistence.model.State;
import com.westchase.persistence.model.Street;
import com.westchase.utils.ejb.ServiceLocator;
import com.westchase.web.bean.KeyValue;


/**
 * @author marc
 *
 */
public class PropertyAction extends AbstractCMSAction<Property, PropertySearchCriteria> {
    
    private PropertyService propServ;
    private AuditService audServ;
    private CompanyService compServ; 

    private Integer propertyId;
    private Property currentProperty;
    private List<PropertyHcad> currentPropertyHcads;
    private List<Property> properties;
    
    private Integer linkCompanyId;
    private boolean linkPrimary;
    
    private List<Company> currentCompanies;
    private List<State> availableStates = new ArrayList<State>();
    private List<Street> availableStreets = new ArrayList<Street>();
    
    private static final String LAST_PROPERTY_SEARCH = "LAST_PROPERTY_SEARCH";
    
    // this field and the getters/setters should not be neccessary but struts2/ognl throw
    // a class cast exception without it
	private Property searchObject;
	
	private List<KeyValue> availableBusinessTypes = new ArrayList<KeyValue>(); 
    private List<PropertyType> availablePropertyTypes = new ArrayList<PropertyType>();
    private List<FlagSize> availableFlagSizes = new ArrayList<FlagSize>();
    

	private String forSaleSearch;
	private String singleTenantSearch;
	private String willDivideSearch;
	private String trpassAfdvtSearch;
	private String flagPoleSearch;

    public PropertyAction() {
    	super();
    }
    public String execute() throws Exception {
        return super.execute();
    }

	public List<State> getAvailableStates() {
		return availableStates;
	}

	public List<Company> getCurrentCompanies() {
		return currentCompanies;
	}
    
    public Property getCurrentProperty() {
        return currentProperty;
    }


    @Override
	public String getLastSearchAttributeName() {
		return LAST_PROPERTY_SEARCH;
	}
	
	public Integer getLinkCompanyId() {
		return linkCompanyId;
	}
    
    public List<Property> getProperties() {
		return this.properties;
	}

    public Integer getPropertyId() {
        return propertyId;
    }

    public Property getSearchObject() {
		return searchObject;
	}

    public boolean isLinkPrimary() {
		return linkPrimary;
	}
    
    public String list() {
    	if (getUseLast() > 0) {
    		refreshLast();
    	} else {
    		getRequest().getSession(true).setAttribute(getLastSearchAttributeName(), getSearchObject());
    		refresh();
    	}
    	return SUCCESS;
    }

	public String goToPage(int page) {
		setPage(page);
    	setSearchObject((Property) getRequest().getSession(true).getAttribute(getLastSearchAttributeName()));
    	refresh();
    	return SUCCESS;
    }
	

    public void prepare() throws Exception {
        propServ = ServiceLocator.lookupPropertyService();
        compServ = ServiceLocator.lookupCompanyService();
        
    	if (getPropertyId() != null && propServ != null) {
		
	        Property preFetched = propServ.get(getPropertyId());
	        if (preFetched != null) {
	            setCurrentProperty(preFetched);
	            
	            currentPropertyHcads = propServ.findHcadsByProperty(getPropertyId());
	            
	            if (compServ != null) {
	            	currentCompanies = compServ.getByProperty(preFetched.getId());
	            }
	        }
    	} else {
    		Property empty = new Property();
    		empty.setGeoState("TX");
    		empty.setGeoCity("Houston");
    		setCurrentProperty(empty);
    	}

    	if (compServ != null) {
    		availableStates = compServ.findAllStates();
    		availableStreets = compServ.listStreets();
    	}
    	if (propServ != null) {
    		availablePropertyTypes = propServ.listPropertyTypes();
    		availableFlagSizes = propServ.listFlagSizes();
    	}

        availableBusinessTypes = new ArrayList<KeyValue>();
        availableBusinessTypes.add(new KeyValue("Development Site", "Development Site"));
        availableBusinessTypes.add(new KeyValue("Hotel", "Hotel"));
        availableBusinessTypes.add(new KeyValue("Industrial", "Industrial"));
        availableBusinessTypes.add(new KeyValue("Multi-Family: Apartment", "Multi-Family: Apartment"));
        availableBusinessTypes.add(new KeyValue("Multi-Family: Condos/Townhomes", "Multi-Family: Condos/Townhomes"));
        availableBusinessTypes.add(new KeyValue("Non-Commercial", "Non-Commercial"));
        availableBusinessTypes.add(new KeyValue("Office", "Office"));
        availableBusinessTypes.add(new KeyValue("Public", "Public"));
        availableBusinessTypes.add(new KeyValue("Retail Center", "Retail Center"));
        availableBusinessTypes.add(new KeyValue("Retail Single", "Retail Single"));
        availableBusinessTypes.add(new KeyValue("Service Center", "Service Center"));
    }

	public void refresh() {
        PropertySearchCriteria criteria = new PropertySearchCriteria();
        criteria.setSearchObject(getSearchObject());
        Employee emp = getEmployee();
        if (isUpdatedByMe() && emp != null) {
        	criteria.setEmployeeId(emp.getId());
        }        
        criteria.setPage(getPage());
        criteria.setNumberOfResults(getNumberOfResults());
        if (StringUtils.isNotBlank(getOrderCol())) {
        	criteria.setOrderCol(getOrderCol());
        	setCurrentOrderCol(getOrderCol());
        } else {
        	criteria.setOrderCol(getCurrentOrderCol());
        }
        criteria.setOrderDir(getOrderDir());

        
        // store for 'back to list' functionality
        getRequest().getSession(true).setAttribute(getLastSearchAttributeName() + SESSION_ATTR_NAME, criteria);
        
        runSearch(criteria);
    }
    
	protected void runSearch(PropertySearchCriteria criteria) {
        propServ = ServiceLocator.lookupPropertyService();
        if (propServ != null) {
        	properties = propServ.findAll(criteria);
			long count = propServ.findAllCount(criteria);
			setMax(count);
        }
    }

	public String save() throws Exception {
        if (getCurrentProperty() != null) {
            propServ = ServiceLocator.lookupPropertyService();
            audServ = ServiceLocator.lookupAuditService();
            compServ = ServiceLocator.lookupCompanyService();

        	Employee emp = getEmployee();
        	if (emp != null && emp.getId() != null) {

            
	            if (propServ != null) {
		            propServ.saveOrUpdate(getCurrentProperty());
		            Integer id = getCurrentProperty().getId();   
		            
		        	setPropertyId(id);
		            
		            if (audServ != null) {
		            	int employeeId = emp.getId().intValue();
		        		audServ.save(employeeId, getCurrentProperty());
		            }
		            
		            // link the company and property (create company_mapno)
		            if (getLinkCompanyId() != null && compServ != null) {
		            	compServ.saveCompanyProperty(null, getLinkCompanyId(), id, isLinkPrimary());
		            }
	            }
        	}
        }
        return SUCCESS;
    }

	public void setAvailableStates(List<State> availableStates) {
		this.availableStates = availableStates;
	}

	public void setCurrentCompanies(List<Company> currentCompanies) {
		this.currentCompanies = currentCompanies;
	}

	public void setCurrentProperty(Property currentEmployee) {
        this.currentProperty = currentEmployee;
    }

	public void setLinkCompanyId(Integer linkCompanyId) {
		this.linkCompanyId = linkCompanyId;
	}

	public void setLinkPrimary(boolean linkPrimary) {
		this.linkPrimary = linkPrimary;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

	public void setSearchObject(Property searchObject) {
		this.searchObject = searchObject;
	}

	public String sort() {
    	setSearchObject((Property) getRequest().getSession(true).getAttribute(getLastSearchAttributeName()));
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
	public List<KeyValue> getAvailableBusinessTypes() {
		return availableBusinessTypes;
	}
	public void setAvailableBusinessTypes(List<KeyValue> availableBusinessTypes) {
		this.availableBusinessTypes = availableBusinessTypes;
	}
	public String getForSaleSearch() {
		return forSaleSearch;
	}
	public void setForSaleSearch(String forSaleSearch) {
		this.forSaleSearch = forSaleSearch;
	}
	public String getSingleTenantSearch() {
		return singleTenantSearch;
	}
	public void setSingleTenantSearch(String singleTenantSearch) {
		this.singleTenantSearch = singleTenantSearch;
	}
	public String getWillDivideSearch() {
		return willDivideSearch;
	}
	public void setWillDivideSearch(String willDivideSearch) {
		this.willDivideSearch = willDivideSearch;
	}
	public String getTrpassAfdvtSearch() {
		return trpassAfdvtSearch;
	}
	public void setTrpassAfdvtSearch(String trpassAfdvtSearch) {
		this.trpassAfdvtSearch = trpassAfdvtSearch;
	}
	public String getFlagPoleSearch() {
		return flagPoleSearch;
	}
	public void setFlagPoleSearch(String flagPoleSearch) {
		this.flagPoleSearch = flagPoleSearch;
	}
	public List<PropertyType> getAvailablePropertyTypes() {
		return availablePropertyTypes;
	}
	public void setAvailablePropertyTypes(List<PropertyType> availablePropertyTypes) {
		this.availablePropertyTypes = availablePropertyTypes;
	}
	public List<Street> getAvailableStreets() {
		return availableStreets;
	}
	public void setAvailableStreets(List<Street> availableStreets) {
		this.availableStreets = availableStreets;
	}
	public List<FlagSize> getAvailableFlagSizes() {
		return availableFlagSizes;
	}
	public void setAvailableFlagSizes(List<FlagSize> availableFlagSizes) {
		this.availableFlagSizes = availableFlagSizes;
	}
	public List<PropertyHcad> getCurrentPropertyHcads() {
		return currentPropertyHcads;
	}
	public void setCurrentPropertyHcads(List<PropertyHcad> currentPropertyHcads) {
		this.currentPropertyHcads = currentPropertyHcads;
	}
}
