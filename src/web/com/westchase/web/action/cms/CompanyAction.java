package com.westchase.web.action.cms;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.westchase.ejb.AuditService;
import com.westchase.ejb.CompanyService;
import com.westchase.ejb.PhoneBookService;
import com.westchase.ejb.PropertyService;
import com.westchase.persistence.criteria.CompanySearchCriteria;
import com.westchase.persistence.dto.cms.CompanyPropertyDTO;
import com.westchase.persistence.dto.cms.PhoneBookCategoryDTO;
import com.westchase.persistence.model.Company;
import com.westchase.persistence.model.CompanyType;
import com.westchase.persistence.model.Employee;
import com.westchase.persistence.model.Naics;
import com.westchase.persistence.model.PhoneBook;
import com.westchase.persistence.model.State;
import com.westchase.persistence.model.Street;
import com.westchase.utils.ejb.ServiceLocator;
import com.westchase.web.bean.KeyValue;

/**
 * @author marc
 *
 */
public class CompanyAction extends AbstractCMSAction<Company, CompanySearchCriteria> {
    private static final String LAST_COMPANY_SEARCH = "LAST_COMPANY_SEARCH";
    
    
    private CompanyService compServ;
    private PropertyService propServ;
    private PhoneBookService pbServ;
    private AuditService audServ;

    private Integer companyId;
    private Company currentCompany;
    private Integer delId;
    private List<Company> companies;
    private List<CompanyPropertyDTO> currentProperties;
    private List<PhoneBookCategoryDTO> currentPhoneBooks;
    private List<PhoneBook> phoneBooksToDelete;
    
    private List<State> availableStates = new ArrayList<State>();
    private List<Naics> availableNaics = new ArrayList<Naics>();
    private List<Street> availableStreets = new ArrayList<Street>();
    private List<KeyValue> availableClassifications = new ArrayList<KeyValue>();
    private List<KeyValue> availableSubClassifications = new ArrayList<KeyValue>();
    private List<CompanyType> availableCompanyTypes = new ArrayList<CompanyType>();
    
    
    private String vendorSearch;

    // this field and the getters/setters should not be neccessary but struts2/ognl throw
    // a class cast exception without it
	private Company searchObject;
	private Integer mapNo;

	private boolean orphanedOnly;
	private boolean outsideDistrict;
	private String employeeRange;

	public CompanyAction() {
    	super();
    }
    
	public String execute() throws Exception {
        return super.execute();
    }

	public List<Company> getCompanies() {
		return this.companies;
	}

	public Integer getCompanyId() {
        return companyId;
    }
	
    public Company getCurrentCompany() {
        return currentCompany;
    }
    public List<CompanyPropertyDTO> getCurrentProperties() {
		return currentProperties;
	}

	@Override
	public String getLastSearchAttributeName() {
		return LAST_COMPANY_SEARCH;
	}

	public Company getSearchObject() {
		return searchObject;
	}
    
    public String getVendorSearch() {
		return vendorSearch;
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
    	setSearchObject((Company) getRequest().getSession(true).getAttribute(getLastSearchAttributeName()));
    	refresh();
    	return SUCCESS;
    }
//	public String first() {
//    	return goToPage(0);
//    }
//	public String next() {
//    	return goToPage(getPage() + 1);
//    }
//	public String prev() {
//    	return goToPage(getPage() - 1);
//    }

	public String delete() throws Exception {
        compServ = ServiceLocator.lookupCompanyService();
        pbServ = ServiceLocator.lookupPhoneBookService();
        propServ = ServiceLocator.lookupPropertyService();
        audServ = ServiceLocator.lookupAuditService();

        Integer empId = null;
        Employee emp = getEmployee();
        if (emp != null) {
        	empId = emp.getId();
        }
        if (empId != null && empId.intValue() > 0 && delId != null && delId.intValue() > 0) {
        	if (pbServ != null && compServ != null && propServ != null) {
		        List<PhoneBookCategoryDTO> currPhoneBooks = pbServ.findByCompany(delId);
		        if (currPhoneBooks == null || currPhoneBooks.isEmpty()) {
		        	compServ.delete(delId);
		        	getRequest().getSession(true).setAttribute("message", "Deleted Company #" + delId);
		        	if (audServ != null) {
		        		audServ.deleteCompany(empId, delId);
		        	}
		        	return SUCCESS;
		        }
		        setPhoneBooksToDelete(currPhoneBooks);
		        return "phonebooks";
        	}
        }		        	
        return SUCCESS;
	}

    @Override
    public void prepare() throws Exception {
        compServ = ServiceLocator.lookupCompanyService();
        propServ = ServiceLocator.lookupPropertyService();
        pbServ = ServiceLocator.lookupPhoneBookService();
        
    	if (getCompanyId() != null) { 		
            if (compServ != null) {
		        Company preFetched = compServ.get(getCompanyId());
		        if (preFetched != null) {
		            setCurrentCompany(preFetched);
		            if (propServ != null) {
		            	currentProperties = propServ.findByCompany(preFetched.getId());
		            }
		            if (pbServ != null) {
		            	currentPhoneBooks = pbServ.findByCompany(preFetched.getId());
		            }
		        }
            }
    	} else {
    		Company empty = new Company();
    		empty.setState("TX");
    		empty.setCity("Houston");
    		setCurrentCompany(empty);
    	}
    	if (compServ != null) {
    		availableStates = compServ.findAllStates();
    		availableNaics = compServ.findAllNaics();
    		availableStreets = compServ.listStreets();
    		availableCompanyTypes = compServ.listCompanyTypes();
    	}
 
        availableClassifications = new ArrayList<KeyValue>();
        availableClassifications.add(new KeyValue("WC Business", "WC Business"));
        availableClassifications.add(new KeyValue("Non WC Business", "Non WC Business"));
        availableSubClassifications = new ArrayList<KeyValue>();
        availableSubClassifications.add(new KeyValue("Owner", "Owner"));
        availableSubClassifications.add(new KeyValue("Tenant", "Tenant"));
    }

    @Override
    public void refresh() {
        CompanySearchCriteria criteria = new CompanySearchCriteria();
        Company so = getSearchObject();
        try {
	        if (StringUtils.isNotBlank(getVendorSearch())) {
	        	criteria.setVendor(Boolean.valueOf(getVendorSearch()).booleanValue());
	        }
        } catch (Exception e) {}
        criteria.setSearchObject(so);
        criteria.setMapNo(getMapNo());
        if (isOrphanedOnly()) {
        	criteria.setOrphanedOnly(true);
        }
        if (isOutsideDistrict()) {
        	criteria.setOutsideDistrict(true);
        }
        Employee emp = getEmployee();
        if (isUpdatedByMe() && emp != null) {
        	criteria.setEmployeeId(emp.getId());
        }
        criteria.setEmployeeRange(getEmployeeRange());
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
    
    protected void runSearch(CompanySearchCriteria criteria) {
        compServ = ServiceLocator.lookupCompanyService();
        if (compServ != null) {
	    	companies = compServ.findAll(criteria);
	    	long count = compServ.findAllCount(criteria);
	    	setMax(count);
        }
    }

    public String save() throws Exception {
        if (getCurrentCompany() != null) {
            compServ = ServiceLocator.lookupCompanyService();
            audServ = ServiceLocator.lookupAuditService();
            
            Employee emp = getEmployee();
            if (emp != null && emp.getId() != null) {
            	if (compServ != null) {
                    compServ.saveOrUpdate(getCurrentCompany(), emp.getId().intValue());
                    Integer id = getCurrentCompany().getId();   
                    
                	setCompanyId(id);
            	}
            }
        }
        return SUCCESS;
    }


	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}
    

	public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public void setCurrentCompany(Company currentEmployee) {
        this.currentCompany = currentEmployee;
    }

    public void setCurrentProperties(List<CompanyPropertyDTO> currentProperties) {
		this.currentProperties = currentProperties;
	}

	public void setSearchObject(Company searchObject) {
		this.searchObject = searchObject;
	}

	public void setVendorSearch(String vendorSearch) {
		this.vendorSearch = vendorSearch;
	}

	public List<State> getAvailableStates() {
		return availableStates;
	}

	public void setAvailableStates(List<State> availableStates) {
		this.availableStates = availableStates;
	}

	public List<KeyValue> getAvailableClassifications() {
		return availableClassifications;
	}

	public void setAvailableClassifications(List<KeyValue> availableClassifications) {
		this.availableClassifications = availableClassifications;
	}

	public List<KeyValue> getAvailableSubClassifications() {
		return availableSubClassifications;
	}

	public void setAvailableSubClassifications(List<KeyValue> availableSubClassifications) {
		this.availableSubClassifications = availableSubClassifications;
	}

	public String sort() {
    	setSearchObject((Company) getRequest().getSession(true).getAttribute(getLastSearchAttributeName()));
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

	public List<PhoneBookCategoryDTO> getCurrentPhoneBooks() {
		return currentPhoneBooks;
	}

	public void setCurrentPhoneBooks(List<PhoneBookCategoryDTO> currentPhoneBooks) {
		this.currentPhoneBooks = currentPhoneBooks;
	}

	public List<Naics> getAvailableNaics() {
		return availableNaics;
	}

	public void setAvailableNaics(List<Naics> availableNaics) {
		this.availableNaics = availableNaics;
	}

	public boolean isOutsideDistrict() {
		return outsideDistrict;
	}

	public void setOutsideDistrict(boolean outsideDistrict) {
		this.outsideDistrict = outsideDistrict;
	}

	public boolean isOrphanedOnly() {
		return orphanedOnly;
	}

	public void setOrphanedOnly(boolean orphanedOnly) {
		this.orphanedOnly = orphanedOnly;
	}

	public Integer getDelId() {
		return delId;
	}

	public void setDelId(Integer delId) {
		this.delId = delId;
	}

	public List<PhoneBook> getPhoneBooksToDelete() {
		return phoneBooksToDelete;
	}

	public void setPhoneBooksToDelete(List<PhoneBookCategoryDTO> phoneBooksToDelete) {
		List<PhoneBook> pbs = new ArrayList<PhoneBook>();
		if (phoneBooksToDelete != null && !phoneBooksToDelete.isEmpty()) {
			for (PhoneBookCategoryDTO pbc : phoneBooksToDelete) {
				pbs.add(pbc.getPhoneBook());
			}
		}
		this.phoneBooksToDelete = pbs;
	}

	public Integer getMapNo() {
		return mapNo;
	}

	public void setMapNo(Integer mapNo) {
		this.mapNo = mapNo;
	}

	public String getEmployeeRange() {
		return employeeRange;
	}

	public void setEmployeeRange(String employeeRange) {
		this.employeeRange = employeeRange;
	}

	public List<CompanyType> getAvailableCompanyTypes() {
		return availableCompanyTypes;
	}

	public void setAvailableCompanyTypes(List<CompanyType> availableCompanyTypes) {
		this.availableCompanyTypes = availableCompanyTypes;
	}

	public List<Street> getAvailableStreets() {
		return availableStreets;
	}

	public void setAvailableStreets(List<Street> availableStreets) {
		this.availableStreets = availableStreets;
	}
}
