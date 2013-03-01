package com.westchase.web.action.cms;

import java.util.List;

import javax.naming.InitialContext;

import com.westchase.ejb.CategoryService;
import com.westchase.persistence.criteria.CategorySearchCriteria;
import com.westchase.persistence.model.Category;
import com.westchase.persistence.model.Employee;

/**
 * @author marc
 *
 */
public class CategoryAction extends AbstractCMSAction<Category, CategorySearchCriteria> {
    
    private CategoryService catServ;
//    private AuditService audServ;
    private InitialContext ctx = null;     

    private String categoryCode;
    private Category currentCategory;
    private List<Category> categories;
    
    private static final String LAST_CATEGORY_SEARCH = "LAST_CATEGORY_SEARCH";
    // this field and the getters/setters should not be neccessary but struts2/ognl throw
    // a class cast exception without it
    private Category searchObject;
    public Category getSearchObject() {
		return searchObject;
	}

	public void setSearchObject(Category searchObject) {
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
    	setSearchObject((Category) getRequest().getSession(true).getAttribute(getLastSearchAttributeName()));
    	refresh();
    	return SUCCESS;
    }
//	public String next() {
//    	setPage(getPage() + 1);
//    	setSearchObject((Category) getRequest().getSession(true).getAttribute(getLastSearchAttributeName()));
//    	refresh();
//    	return SUCCESS;
//    }
//
//	public String prev() {
//    	setPage(getPage() - 1);
//    	setSearchObject((Category) getRequest().getSession(true).getAttribute(getLastSearchAttributeName()));
//    	refresh();
//    	return SUCCESS;
//    }
    
	@Override
	public String getLastSearchAttributeName() {
		return LAST_CATEGORY_SEARCH;
	}
    

	public CategoryAction() {
    	super();
    }
    
    public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

    public Category getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(Category currentEmployee) {
        this.currentCategory = currentEmployee;
    }


    public void refresh() {
		CategorySearchCriteria criteria = new CategorySearchCriteria();
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
    protected void runSearch(CategorySearchCriteria criteria) {
        try {
            ctx=new InitialContext();
            catServ = (CategoryService) ctx.lookup("westchase/CategoryServiceBean/local");
        } catch (Exception e) {
            log.error(e.getMessage()); 
        } 
    	categories = catServ.findAll(criteria);
    }
    
    
    public void prepare() throws Exception {
    	if (getCategoryCode() != null) {
            try {
                ctx = new InitialContext();
                catServ = (CategoryService) ctx.lookup("westchase/CategoryServiceBean/local");
            } catch (Exception e) {
                log.error("", e); 
            }    
            if (catServ != null) {
		        Category preFetched = catServ.get(getCategoryCode());
		        if (preFetched != null) {
		            setCurrentCategory(preFetched);
		        }
            }
    	}
    }

    public String execute() throws Exception {
        return super.execute();
    }

    public String save() throws Exception {
        if (getCurrentCategory() != null) {
            try {
                ctx=new InitialContext();
                catServ = (CategoryService) ctx.lookup("westchase/CategoryServiceBean/local");
//                audServ = (AuditService) ctx.lookup("westchase/AuditServiceBean/local");
            } catch (Exception e) {
                log.error(e.getMessage()); 
            }
            
            Employee emp = getEmployee();
            
            if (emp != null && emp.getId() != null) {
            	
            	if (catServ != null) {
            
            		Category cat = getCurrentCategory();
            		cat.setCategoryCode(cat.getCategoryCode().toUpperCase());
            		catServ.saveOrUpdate(cat);
            		String code = cat.getCategoryCode();   
            
        			setCategoryCode(code);
        			
//        			if (audServ != null) {
//        				int employeeId = emp.getId().intValue();
//		        		StringBuffer desc = new StringBuffer(ToStringBuilder.reflectionToString(getCurrentCategory()));
//		        		audServ.save(employeeId, Category.class.getName(), desc.toString());
//        			}
        		
            	}
        	
            }
        }
        return SUCCESS;
    }

	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

}
