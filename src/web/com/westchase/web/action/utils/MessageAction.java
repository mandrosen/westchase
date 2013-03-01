package com.westchase.web.action.utils;

import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;

import com.westchase.ejb.EmployeeService;
import com.westchase.ejb.MessageTodoService;
import com.westchase.persistence.criteria.MessageSearchCriteria;
import com.westchase.persistence.model.Employee;
import com.westchase.persistence.model.Message;
import com.westchase.web.action.cms.AbstractCMSAction;

/**
 * @author marc
 *
 */
public class MessageAction extends AbstractCMSAction<Message, MessageSearchCriteria> {

    
    
    private MessageTodoService msgServ;
    private EmployeeService empServ;
    private InitialContext ctx = null;     

    private Integer messageId;
    private Message currentMessage;
    private List<Message> messages;
    


	private List<Employee> availableEmployees;

	private static final String LAST_MESSAGE_SEARCH = "LAST_MESSAGE_SEARCH";

    // this field and the getters/setters should not be neccessary but struts2/ognl throw
    // a class cast exception without it
	private Message searchObject;

	public List<Employee> getAvailableEmployees() {
		return availableEmployees;
	}

	public Message getCurrentMessage() {
		return currentMessage;
	}

	@Override
	public String getLastSearchAttributeName() {
		return LAST_MESSAGE_SEARCH;
	}

	public Integer getMessageId() {
		return messageId;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public Message getSearchObject() {
		return searchObject;
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
    	setSearchObject((Message) getRequest().getSession(true).getAttribute(getLastSearchAttributeName()));
    	refresh();
    	return SUCCESS;
    }
//	public String next() {
//    	setPage(getPage() + 1);
//    	setSearchObject((Message) getRequest().getSession(true).getAttribute(getLastSearchAttributeName()));
//    	refresh();
//    	return SUCCESS;
//    }
//
//	public String prev() {
//    	setPage(getPage() - 1);
//    	setSearchObject((Message) getRequest().getSession(true).getAttribute(getLastSearchAttributeName()));
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
		if (msgServ != null) {
	    	if (getMessageId() != null) { 	
		        Message preFetched = msgServ.getMessage(getMessageId());
		        if (preFetched != null) {
		            setCurrentMessage(preFetched);
		        }
	    	} else if (getSearchObject() != null && getSearchObject().getEmployee() != null && getSearchObject().getEmployee().getId() != null) {
	    		MessageSearchCriteria criteria = new MessageSearchCriteria();
	    		criteria.setSearchObject(getSearchObject());
	    		messages = msgServ.findAll(criteria);
	    	}
		}
        if (empServ != null) {
        	Employee emp = getEmployee();
        	if (getRequest().isUserInRole("admin")) {
        		availableEmployees = empServ.findAll();
        	} else if (emp != null) {
        		availableEmployees = new ArrayList<Employee>();
        		availableEmployees.add(emp);
        	}
        }
	}

	@Override
	public void refresh() {
		MessageSearchCriteria criteria = new MessageSearchCriteria();
		Message so = getSearchObject();
    	if (!getRequest().isUserInRole("admin")) {
    		if (so == null) {
    			so = new Message();
    		}
    		so.setEmployee(getEmployee());
    	}
        criteria.setSearchObject(so);
        criteria.setPage(getPage());
        criteria.setNumberOfResults(getNumberOfResults());
        criteria.setOrderCol(getOrderCol());
        criteria.setOrderDir(getOrderDir());

        
        // store for 'back to list' functionality
        getRequest().getSession(true).setAttribute(getLastSearchAttributeName() + SESSION_ATTR_NAME, criteria);
        
        runSearch(criteria);
    }
    
    protected void runSearch(MessageSearchCriteria criteria) {
        try {
            ctx = new InitialContext();
            msgServ = (MessageTodoService) ctx.lookup("westchase/MessageTodoServiceBean/local");
            empServ = (EmployeeService) ctx.lookup("westchase/EmployeeServiceBean/local");
        } catch (Exception e) {
            log.error("", e); 
        } 
    	Employee emp = getEmployee();
        if (msgServ != null && emp != null) {
        	messages = msgServ.findAll(criteria);
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
        if (getCurrentMessage() != null) {
            try {
                ctx = new InitialContext();
                msgServ = (MessageTodoService) ctx.lookup("westchase/MessageTodoServiceBean/local");
            } catch (Exception e) {
                log.error(e.getMessage()); 
            }
            
            Employee emp = getEmployee();
            
            if (emp != null && emp.getId() != null) {
            
	            if (msgServ != null) {
	            	Message msg = getCurrentMessage();
	            	msgServ.saveOrUpdate(msg, emp);
	            	Integer id = msg.getId();   
	            
	            	setMessageId(id);
	            	
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
	        if (msgServ != null && getMessageId() != null) {
	        	msgServ.deleteMessage(getMessageId(), emp);
	        }
        }        
		return SUCCESS;
	}
	
	public void setAvailableEmployees(List<Employee> availEmployees) {
		this.availableEmployees = availEmployees;
	}

	public void setCurrentMessage(Message currentMessage) {
		this.currentMessage = currentMessage;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

    public void setSearchObject(Message searchObject) {
		this.searchObject = searchObject;
	}

}
