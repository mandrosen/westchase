package com.westchase.web.action;

import java.util.List;

import javax.naming.InitialContext;

import com.westchase.ejb.MessageTodoService;
import com.westchase.persistence.model.Employee;
import com.westchase.persistence.model.Message;
import com.westchase.persistence.model.Todo;

/**
 * @author marc
 * 
 */
public class HomeAction extends AbstractWestchaseAction {
	
	private MessageTodoService msgServ;
	private InitialContext ctx = null;

	private List<Message> messages;
	private List<Todo> todos;

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public List<Todo> getTodos() {
		return todos;
	}

	public void setTodos(List<Todo> todos) {
		this.todos = todos;
	}

	@Override
	public String execute() throws Exception {
        try {
            ctx = new InitialContext();
            msgServ = (MessageTodoService) ctx.lookup("westchase/MessageTodoServiceBean/local");
        } catch (Exception e) {
            log.error("", e); 
        }    
        if (msgServ != null) {
        	messages = msgServ.getCurrentMessages();
        	Employee emp = getEmployee();
        	if (emp != null && emp.getId() != null) {
        		todos = msgServ.getCurrentTodos(emp.getId().intValue());
        	}
        }
		
		return SUCCESS;
	}

}
