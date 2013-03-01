package com.westchase.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.SecurityDomain;

import com.westchase.persistence.criteria.MessageSearchCriteria;
import com.westchase.persistence.criteria.TodoSearchCriteria;
import com.westchase.persistence.dao.MessageDAO;
import com.westchase.persistence.dao.TodoDAO;
import com.westchase.persistence.model.Employee;
import com.westchase.persistence.model.Message;
import com.westchase.persistence.model.Todo;

/**
 * @author marc
 * 
 */
@Stateless
@SecurityDomain("WestchaseRealm")
@Local(MessageTodoService.class)
public class MessageTodoServiceBean implements MessageTodoService {

	@EJB
	private AuditService audServ;
	
	@Override
	public List<Message> getCurrentMessages() {
		MessageDAO dao = new MessageDAO();
		return dao.listCurrent();
	}

	@Override
	public List<Todo> getCurrentTodos(int employeeId) {
		TodoDAO dao = new TodoDAO();
		return dao.listCurrent(employeeId);
	}

	@Override
	public List<Todo> findAll(TodoSearchCriteria criteria) {
		TodoDAO dao = new TodoDAO();
		return dao.findAll(criteria);
	}

	@Override
	public void saveOrUpdate(Todo todo, Employee emp) {
		if (emp != null && (todo.getEmployee() == null || todo.getEmployee().getId() == emp.getId())) {
			TodoDAO dao = new TodoDAO();
			todo.setEmployee(emp);
			if (todo.getId() == null) {
				todo.setCreateDate(new Date());
			}
			dao.saveOrUpdate(todo);
			
			if (audServ != null) {
				audServ.save(emp.getId().intValue(), todo);
			}
		}
	}

	@Override
	public List<Message> findAll(MessageSearchCriteria criteria) {
		MessageDAO dao = new MessageDAO();
		return dao.findAll(criteria);
	}

	@Override
	public void saveOrUpdate(Message message, Employee emp) {
		if (emp != null && (message.getEmployee() == null || message.getEmployee().getId() == emp.getId())) {
			MessageDAO dao = new MessageDAO();
			message.setEmployee(emp);
			if (message.getId() == null) {
				message.setCreateDate(new Date());
			}
			dao.saveOrUpdate(message);
		}
	}

	@Override
	public Message getMessage(Integer messageId) {
		MessageDAO dao = new MessageDAO();
		return dao.findById(messageId);
	}

	@Override
	public Todo getTodo(Integer todoId) {
		TodoDAO dao = new TodoDAO();
		return dao.findById(todoId);
	}

	@Override
	public void deleteTodo(Integer todoId, Employee emp) {
		TodoDAO dao = new TodoDAO();
		Todo todo = dao.get(todoId);
		if (emp != null && (todo.getEmployee() == null || todo.getEmployee().getId().intValue() == emp.getId().intValue())) {
			dao.delete(todoId);

			if (audServ != null) {
				audServ.delete(emp.getId().intValue(), Todo.class, todoId);
			}
		}
	}

	@Override
	public void deleteMessage(Integer messageId, Employee emp) {
		MessageDAO dao = new MessageDAO();
		Message message = dao.get(messageId);
		if (emp != null && (message.getEmployee() == null || message.getEmployee().getId().intValue() == emp.getId().intValue())) {
			dao.delete(messageId);
			
			if (audServ != null) {
				audServ.delete(emp.getId().intValue(), Message.class, messageId);
			}
		}
	}

	@Override
	public void completeTodo(int employeeId, Integer todoId) {
		TodoDAO dao = new TodoDAO();
		Todo todo = dao.get(todoId);
		if (todo != null && todo.getEmployee() != null && todo.getEmployee().getId() != null && 
				todo.getEmployee().getId().intValue() == employeeId && todo.getCompleteDate() == null) {
			todo.setCompleteDate(new Date());
			dao.save(todo);

			if (audServ != null) {
				audServ.completeTodo(employeeId, todoId);
			}
		}
	}

}
