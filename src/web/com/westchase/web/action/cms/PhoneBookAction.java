package com.westchase.web.action.cms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.westchase.ejb.AuditService;
import com.westchase.ejb.CategoryService;
import com.westchase.ejb.CompanyService;
import com.westchase.ejb.PhoneBookService;
import com.westchase.persistence.criteria.PhoneBookSearchCriteria;
import com.westchase.persistence.model.Category;
import com.westchase.persistence.model.Company;
import com.westchase.persistence.model.Employee;
import com.westchase.persistence.model.PhoneBook;
import com.westchase.persistence.model.State;
import com.westchase.persistence.model.Street;
import com.westchase.utils.ejb.ServiceLocator;
import com.westchase.web.bean.KeyValue;

/**
 * @author marc
 *
 */
public class PhoneBookAction extends AbstractCMSAction<PhoneBook, PhoneBookSearchCriteria> {

    
    private PhoneBookService pbServ;
    private CategoryService catServ;
    private CompanyService compServ;
//    private PropertyService propServ;
    private AuditService audServ;

    private Integer phoneBookId;
    private Integer linkCompanyId;
    private Integer delId;
    private PhoneBook currentPhoneBook;
//    private List<CompanyPropertyDTO> currentProperties;
    private Company currentCompany;
    
    
    private List<String> selectedCategories = new ArrayList<String>();
    private int phoneBookPropCount;
//    private List<Integer> selectedProperties = new ArrayList<Integer>();

	private List<PhoneBook> phoneBooks;

	private List<Category> availableCategories = new ArrayList<Category>();
    private List<Category> currentCategories = new ArrayList<Category>();
    private List<Company> availableCompanies = new ArrayList<Company>();
    private List<State> availableStates = new ArrayList<State>();
    private List<Street> availableStreets = new ArrayList<Street>();
    private String dontEmailSearch;
    private String investorSearch;
    
    private String westchaseTodaySearch;
    private static final String LAST_PHONEBOOK_SEARCH = "LAST_PHONEBOOK_SEARCH";
    
    // this field and the getters/setters should not be neccessary but struts2/ognl throw
    // a class cast exception without it
    private PhoneBook searchObject;

    private List<KeyValue> availableClassifications = new ArrayList<KeyValue>();
    private List<KeyValue> availableSubClassifications = new ArrayList<KeyValue>();
    private List<KeyValue> availableGenders = new ArrayList<KeyValue>();
    
    private boolean orphanedOnly;
    
    public PhoneBookAction() {
    	super();
    }
    
	public PhoneBook getSearchObject() {
		return searchObject;
	}
    public void setSearchObject(PhoneBook searchObject) {
		this.searchObject = searchObject;
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
    	setSearchObject((PhoneBook) getRequest().getSession(true).getAttribute(getLastSearchAttributeName()));
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
    
	public String execute() throws Exception {
        return super.execute();
    }

	public List<Category> getAvailableCategories() {
		return availableCategories;
	}

	public List<Company> getAvailableCompanies() {
		return availableCompanies;
	}

	public List<Category> getCurrentCategories() {
		return currentCategories;
	}

	public Company getCurrentCompany() {
		return currentCompany;
	}

	public PhoneBook getCurrentPhoneBook() {
        return currentPhoneBook;
    }

	public String getDontEmailSearch() {
		return dontEmailSearch;
	}

	public String getInvestorSearch() {
		return investorSearch;
	}

	@Override
	public String getLastSearchAttributeName() {
		return LAST_PHONEBOOK_SEARCH;
	}

	public Integer getPhoneBookId() {
        return phoneBookId;
    }
    public List<PhoneBook> getPhoneBooks() {
		return this.phoneBooks;
	}


	public List<String> getSelectedCategories() {
        return selectedCategories;
    }
    
    public String getWestchaseTodaySearch() {
		return westchaseTodaySearch;
	}
    
//    public String list() {
//    	if (getUseLast() > 0) {
//    		refreshLast();
//    	} else {
//    		getRequest().getSession(true).setAttribute(getLastSearchAttributeName(), getSearchObject());
//    		refresh();
//    	}
//    	return SUCCESS;
//    }	
//
//	public String next() {
//    	setPage(getPage() + 1);
//    	setSearchObject((PhoneBook) getRequest().getSession(true).getAttribute(getLastSearchAttributeName()));
//    	refresh();
//    	return SUCCESS;
//    }
//
//	public String prev() {
//    	setPage(getPage() - 1);
//    	setSearchObject((PhoneBook) getRequest().getSession(true).getAttribute(getLastSearchAttributeName()));
//    	refresh();
//    	return SUCCESS;
//    }
	
	public String delete() throws Exception {
        pbServ = ServiceLocator.lookupPhoneBookService();
        audServ = ServiceLocator.lookupAuditService();
        Integer empId = null;
        Employee emp = getEmployee();
        if (emp != null) {
        	empId = emp.getId();
        }
        if (empId != null && empId.longValue() > 0 && delId != null && delId.intValue() > 0) {
        	if (pbServ != null) {
        		pbServ.delete(delId);
        		getRequest().getSession(true).setAttribute("message", "Deleted PhoneBook #" + delId);
        		if (audServ != null) {
        			audServ.deletePhoneBook(empId, delId);
        		}
        	}
        }
		return SUCCESS;
	}

	public void prepare() throws Exception {
        pbServ = ServiceLocator.lookupPhoneBookService();
        compServ = ServiceLocator.lookupCompanyService();
    	if (getPhoneBookId() != null) {	
            if (pbServ != null) {
		        PhoneBook preFetched = pbServ.get(getPhoneBookId());
		        if (preFetched != null) {
		            setCurrentPhoneBook(preFetched);
		            if (compServ != null) {
		            	currentCompany = compServ.getByPhoneBook(getPhoneBookId());
		            }
//		            if (propServ != null) {
//		            	currentProperties = propServ.findByPhoneBook(preFetched.getId());
//		            }
		        }

            }
    	} else if (getLinkCompanyId() != null) {
    		if (compServ != null) {
            	currentCompany = compServ.get(getLinkCompanyId());
            	if (currentCompany != null) {
            		PhoneBook pb = new PhoneBook();
            		pb.setCompany(currentCompany);
            		
            		pb.setWkPhone(currentCompany.getWkPhone());
            		pb.setFaxPhone(currentCompany.getFaxPhone());
            		
            		setCurrentPhoneBook(pb);
            	}
    		}
    	} else {
    		Company empty = new Company();
    		empty.setState("TX");
    		empty.setCity("Houston");
    		setCurrentCompany(empty);
    	}
        prepareLists();
    }

	private void prepareLists() {
        compServ = ServiceLocator.lookupCompanyService();
        catServ = ServiceLocator.lookupCategoryService();
        if (catServ != null) {
        	List<Category> availCategories = null;
        	List<Category> currCategories = new ArrayList<Category>();
        	if (getPhoneBookId() == null) {
        		availCategories = catServ.findAll();
        	} else {
        		availCategories = catServ.findAllNotSelected(getPhoneBookId());
        		currCategories = catServ.findAllSelected(getPhoneBookId());
        	}
        	
        	setAvailableCategories(availCategories);
        	setCurrentCategories(currCategories);
        }
        if (compServ != null) {
        	List<Company> companies = compServ.findAllWithOrder("company");
        	setAvailableCompanies(companies);
    		availableStates = compServ.findAllStates();
    		availableStreets = compServ.listStreets();
        }
        
        
        availableClassifications = new ArrayList<KeyValue>();
        availableClassifications.add(new KeyValue("WC Business", "WC Business"));
        availableClassifications.add(new KeyValue("Non WC Business", "Non WC Business"));
        availableSubClassifications = new ArrayList<KeyValue>();
        availableSubClassifications.add(new KeyValue("Owner", "Owner"));
        availableSubClassifications.add(new KeyValue("Tenant", "Tenant"));
        
        availableGenders.add(new KeyValue("Male", "Male"));
        availableGenders.add(new KeyValue("Female", "Female"));
    }

	@Override
	public void refresh() {
        PhoneBookSearchCriteria criteria = new PhoneBookSearchCriteria();
        PhoneBook so = getSearchObject();
        try {
	        if (StringUtils.isNotBlank(getInvestorSearch())) {
	        	criteria.setInvestor(Boolean.valueOf(getInvestorSearch()).booleanValue());
	        }
	        if (StringUtils.isNotBlank(getDontEmailSearch())) {
	        	criteria.setDontEmail(Boolean.valueOf(getDontEmailSearch()).booleanValue());
	        }
	        if (StringUtils.isNotBlank(getWestchaseTodaySearch())) {
	        	criteria.setWestchaseToday(Boolean.valueOf(getWestchaseTodaySearch()).booleanValue());
	        }
        } catch (Exception e) {}
        criteria.setSearchObject(so);
        if (isOrphanedOnly()) {
        	criteria.setOrphanedOnly(true);
        }
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

	@Override
	protected void runSearch(PhoneBookSearchCriteria criteria) {
		pbServ = ServiceLocator.lookupPhoneBookService();
		if (pbServ != null) {
			phoneBooks = pbServ.findAll(criteria);
			long count = pbServ.findAllCount(criteria);
			setMax(count);
		}
	}


    public String save() throws Exception {
        if (getCurrentPhoneBook() != null) {

            pbServ = ServiceLocator.lookupPhoneBookService();
            audServ = ServiceLocator.lookupAuditService();
        	
            // create new list because selectedCategories list contains non-serializable XWorkConverter
            List<String> categoryCodes = new ArrayList<String>();
            if (selectedCategories != null && selectedCategories.size() > 0) {
            	for (String selectedCat : selectedCategories) {
            		if (selectedCat != null) {
            			categoryCodes.add(new String(selectedCat.toString()));
            		}
            	}
            }
            
            // new rule (2013-05-16) requested by Irene 
            // at least one category code required
            if (categoryCodes == null || categoryCodes.isEmpty()) {
        		getRequest().getSession(true).setAttribute("message", "At least one category code is required!");
        		return SUCCESS;
            }
            
            Map<Integer, String> propIds = getSelectedProperties();
            
            Employee emp = getEmployee();
            if (emp != null && emp.getId() != null) {
            	
	            if (pbServ != null) {
	            	PhoneBook pb = getCurrentPhoneBook();
	            	
	            	pbServ.saveOrUpdate(pb, categoryCodes, propIds);
	            	Integer id = getCurrentPhoneBook().getId();   
	            
	        		setPhoneBookId(id);
	        		
	        		
	        		if (audServ != null) {
		            	int employeeId = emp.getId().intValue();
		            	audServ.save(employeeId, pb, categoryCodes, propIds);
	        		}
	            }
	            
	            
            }
            
            prepareLists();
        }
        return SUCCESS;
    }

    public String savenew() throws Exception {
        if (getCurrentPhoneBook() != null && getCurrentCompany() != null) {
            pbServ = ServiceLocator.lookupPhoneBookService();
            audServ = ServiceLocator.lookupAuditService();
            compServ = ServiceLocator.lookupCompanyService();
        	
            // create new list because selectedCategories list contains non-serializable XWorkConverter
            List<String> categoryCodes = new ArrayList<String>();
            if (selectedCategories != null && selectedCategories.size() > 0) {
            	for (String selectedCat : selectedCategories) {
            		if (selectedCat != null) {
            			categoryCodes.add(new String(selectedCat.toString()));
            		}
            	}
            }
            

            Map<Integer, String> propIds = getSelectedProperties();
            
            Employee emp = getEmployee();
            if (emp != null && emp.getId() != null) {
            	
	            if (pbServ != null) {
	            	PhoneBook pb = getCurrentPhoneBook();
            		// if PhoneBook Id is empty (this is a new record)
            		// save the company and set it on the PhoneBook
	            	Company company = getCurrentCompany();

	            	compServ.saveOrUpdate(company, emp.getId().intValue());            	
	            	
	            	Integer compId = company.getId();
	            	
	            	pb.setCompany(new Company(compId));
	            	
	            	pbServ.saveOrUpdate(pb, categoryCodes, propIds);
	            	Integer id = getCurrentPhoneBook().getId();   
	            
	        		setPhoneBookId(id);
	        		
	        		
	        		if (audServ != null) {
		            	int employeeId = emp.getId().intValue();
		            	audServ.save(employeeId, pb, categoryCodes, propIds);
	        		}
	            }
	            
	            
            }
            
            prepareLists();
        }
        return SUCCESS;
    }

    public void setAvailableCategories(List<Category> availableCategories) {
		this.availableCategories = availableCategories;
	}

    public void setAvailableCompanies(List<Company> availableCompanies) {
		this.availableCompanies = availableCompanies;
	}

    public void setCurrentCategories(List<Category> currentCategories) {
		this.currentCategories = currentCategories;
	}

    public void setCurrentCompany(Company currentCompany) {
		this.currentCompany = currentCompany;
	}
    
    public void setCurrentPhoneBook(PhoneBook currentPhoneBook) {
        this.currentPhoneBook = currentPhoneBook;
    }
    
    
    public void setDontEmailSearch(String dontEmailSearch) {
		this.dontEmailSearch = dontEmailSearch;
	}

    public void setInvestorSearch(String investorSearch) {
		this.investorSearch = investorSearch;
	}

    public void setPhoneBookId(Integer phoneBookId) {
        this.phoneBookId = phoneBookId;
    }
    
    
    public void setPhoneBooks(List<PhoneBook> phoneBooks) {
		this.phoneBooks = phoneBooks;
	}    

	public void setSelectedCategories(List<String> selectedSkills) {
        this.selectedCategories = selectedSkills;
    }

	public void setWestchaseTodaySearch(String westchaseTodaySearch) {
		this.westchaseTodaySearch = westchaseTodaySearch;
	}
	public List<State> getAvailableStates() {
		return availableStates;
	}
	public void setAvailableStates(List<State> availableStates) {
		this.availableStates = availableStates;
	}

	public String sort() {
    	setSearchObject((PhoneBook) getRequest().getSession(true).getAttribute(getLastSearchAttributeName()));
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
	public Integer getLinkCompanyId() {
		return linkCompanyId;
	}
	public void setLinkCompanyId(Integer linkCompanyId) {
		this.linkCompanyId = linkCompanyId;
	}
//	public List<Integer> getSelectedProperties() {
//		return selectedProperties;
//	}
//	public void setSelectedProperties(List<Integer> selectedProperties) {
//		this.selectedProperties = selectedProperties;
//	}
	private Map<Integer, String> getSelectedProperties() {
		Map<Integer, String> propIds = new TreeMap<Integer, String>();
		
		HttpServletRequest request = getRequest();
		if (phoneBookPropCount > 0) {
			for (int i = 0; i < phoneBookPropCount; i++) {
				String propId = request.getParameter("selectedProperties_" + i);
				String suiteNum = request.getParameter("selectedProperties_suiteNum_" + i);
				if (StringUtils.isNotBlank(propId)) {
					Integer propIdInt = new Integer(propId);
					propIds.put(propIdInt, suiteNum);
				}
			}
		
		}
		
		return propIds;
	}
	public int getPhoneBookPropCount() {
		return phoneBookPropCount;
	}
	public void setPhoneBookPropCount(int phoneBookPropCount) {
		this.phoneBookPropCount = phoneBookPropCount;
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

	public List<KeyValue> getAvailableGenders() {
		return availableGenders;
	}

	public void setAvailableGenders(List<KeyValue> availableGenders) {
		this.availableGenders = availableGenders;
	}

	public List<Street> getAvailableStreets() {
		return availableStreets;
	}

	public void setAvailableStreets(List<Street> availableStreets) {
		this.availableStreets = availableStreets;
	}
}