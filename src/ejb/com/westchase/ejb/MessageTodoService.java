package com.westchase.ejb;

import java.util.List;

import javax.ejb.Local;

import com.westchase.persistence.criteria.MessageSearchCriteria;
import com.westchase.persistence.criteria.TodoSearchCriteria;
import com.westchase.persistence.model.Employee;
import com.westchase.persistence.model.Message;
import com.westchase.persistence.model.Todo;

/**
 * @author marc
 *
 */
@Local
public interface MessageTodoService {

	List<Message> getCurrentMessages();

	List<Todo> getCurrentTodos(int employeeId);

	List<Todo> findAll(TodoSearchCriteria criteria);

	void saveOrUpdate(Todo todo, Employee emp);

	List<Message> findAll(MessageSearchCriteria criteria);

	void saveOrUpdate(Message message, Employee emp);

	Message getMessage(Integer messageId);
	
	Todo getTodo(Integer todoId);

	void deleteTodo(Integer todoId, Employee emp);
	
	void deleteMessage(Integer messageId, Employee emp);

	void completeTodo(int employeeId, Integer todoId);

}
