package com.westchase.web.action.utils;

import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;

import com.westchase.ejb.EmployeeService;
import com.westchase.ejb.MessageTodoService;
import com.westchase.persistence.criteria.TodoSearchCriteria;
import com.westchase.persistence.model.Employee;
import com.westchase.persistence.model.Todo;
import com.westchase.web.action.cms.AbstractCMSAction;


/**
 * @author marc
 *
 */
public class TodoAction extends AbstractCMSAction<Todo, TodoSearchCriteria> {

    
    
    private MessageTodoService msgServ;
    private EmployeeService empServ;
//    private AuditService audServ;
    private InitialContext ctx = null;     

    private Integer todoId;
    private Todo currentTodo;
    private List<Todo> todos;
    
    private List<Employee> availableEmployees;

	private static final String LAST_TODO_SEARCH = "LAST_TODO_SEARCH";

    // this field and the getters/setters should not be neccessary but struts2/ognl throw
    // a class cast exception without it
	private Todo searchObject;
	private Integer loggedInEmpId;

	public List<Employee> getAvailableEmployees() {
		return availableEmployees;
	}

	public Todo getCurrentTodo() {
		return currentTodo;
	}

	public String execute() {
		return SUCCESS;
	}
	
	@Override
	public String getLastSearchAttributeName() {
		return LAST_TODO_SEARCH;
	}

	public Todo getSearchObject() {
		return searchObject;
	}

	public Integer getTodoId() {
		return todoId;
	}

	public List<Todo> getTodos() {
		return todos;
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
    	setSearchObject((Todo) getRequest().getSession(true).getAttribute(getLastSearchAttributeName()));
    	refresh();
    	return SUCCESS;
    }
//	public String next() {
//    	setPage(getPage() + 1);
//    	setSearchObject((Todo) getRequest().getSession(true).getAttribute(getLastSearchAttributeName()));
//    	refresh();
//    	return SUCCESS;
//    }
//
//	public String prev() {
//    	setPage(getPage() - 1);
//    	setSearchObject((Todo) getRequest().getSession(true).getAttribute(getLastSearchAttributeName()));
//    	refresh();
//    	return SUCCESS;
//    }
	
    @Override
	public void prepare() throws Exception {
        try {
            ctx = new InitialContext();
            msgServ = (MessageTodoService) ctx.lookup("westchase/MessageTodoServiceBean/local");
            empServ = (EmployeeService) ctx.lookup("westchase/EmployeeServiceBean/local");
        } catch (Exception e) {
            log.error("", e); 
        } 
    	if (getTodoId() != null) { 	
    		if (msgServ != null) {
		        Todo preFetched = msgServ.getTodo(getTodoId());
		        if (preFetched != null) {
		            setCurrentTodo(preFetched);
		        }
    		}
    	}
    	Employee emp = getEmployee();
        if (empServ != null) {
        	if (getRequest().isUserInRole("admin")) {
        		availableEmployees = empServ.findAll();
        	} else if (emp != null) {
        		availableEmployees = new ArrayList<Employee>();
        		availableEmployees.add(emp);
        	}
        }
        if (emp != null) {
        	setLoggedInEmpId(emp.getId());
        }
	}

	@Override
	public void refresh() {
        TodoSearchCriteria criteria = new TodoSearchCriteria();
        Todo so = getSearchObject();
    	if (!getRequest().isUserInRole("admin")) {
    		if (so == null) {
    			so = new Todo();
    		}
    		so.setEmployee(getEmployee());
    	}
        criteria.setSearchObject(so);
        criteria.setPage(getPage());
        criteria.setNumberOfResults(getNumberOfResults());
        criteria.setOrderCol(getOrderCol());
        criteria.setOrderDir(getOrderDir());
        
        runSearch(criteria);
        
	}
	
	@Override
	protected void runSearch(TodoSearchCriteria criteria) {
        try {
            ctx = new InitialContext();
            msgServ = (MessageTodoService) ctx.lookup("westchase/MessageTodoServiceBean/local");
            empServ = (EmployeeService) ctx.lookup("westchase/EmployeeServiceBean/local");
        } catch (Exception e) {
            log.error("", e); 
        } 
    	Employee emp = getEmployee();
        if (msgServ != null && emp != null) {
        	todos = msgServ.findAll(criteria);
        }
        

        if (empServ != null) {
        	if (getRequest().isUserInRole("admin")) {
        		availableEmployees = empServ.findAll();
        	} else if (emp != null) {
        		availableEmployees = new ArrayList<Employee>();
        		availableEmployees.add(emp);
        	}
        }
	}
	
	public String save() throws Exception {
        if (getCurrentTodo() != null) {
            try {
                ctx = new InitialContext();
                msgServ = (MessageTodoService) ctx.lookup("westchase/MessageTodoServiceBean/local");
            } catch (Exception e) {
                log.error(e.getMessage()); 
            }
            
            Employee emp = getEmployee();
            
            if (emp != null && emp.getId() != null) {
            
	            if (msgServ != null) {
	            	Todo td = getCurrentTodo();
	            	msgServ.saveOrUpdate(td, emp);
	            	Integer id = getCurrentTodo().getId();   
	            
	            	setTodoId(id);
	            	
	            }
            }
        }
        return SUCCESS;
    }
	
	public String delete() {
        try {
            ctx = new InitialContext();
            msgServ = (MessageTodoService) ctx.lookup("westchase/MessageTodoServiceBean/local");
        } catch (Exception e) {
            log.error(e.getMessage()); 
        }
        Employee emp = getEmployee();
        if (emp != null && emp.getId() != null) {
	        if (msgServ != null && getTodoId() != null) {
	        	msgServ.deleteTodo(getTodoId(), emp);
	        }
        }        
		return SUCCESS;
	}
	
	public String complete() {
        try {
            ctx = new InitialContext();
            msgServ = (MessageTodoService) ctx.lookup("westchase/MessageTodoServiceBean/local");
        } catch (Exception e) {
            log.error(e.getMessage()); 
        }
        Employee emp = getEmployee();
        if (emp != null && emp.getId() != null) {
	        if (msgServ != null && getTodoId() != null) {
    			int employeeId = emp.getId().intValue();
	        	msgServ.completeTodo(employeeId, getTodoId());
	        }
        }        
		return SUCCESS;
	}
	public void setAvailableEmployees(List<Employee> availableEmployees) {
		this.availableEmployees = availableEmployees;
	}

	public void setCurrentTodo(Todo currentTodo) {
		this.currentTodo = currentTodo;
	}

	public void setSearchObject(Todo searchObject) {
		this.searchObject = searchObject;
	}

	public void setTodoId(Integer todoId) {
		this.todoId = todoId;
	}

    public void setTodos(List<Todo> todos) {
		this.todos = todos;
	}

	public Integer getLoggedInEmpId() {
		return loggedInEmpId;
	}

	public void setLoggedInEmpId(Integer loggedInEmpId) {
		this.loggedInEmpId = loggedInEmpId;
	}

}
