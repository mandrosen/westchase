package com.westchase.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

import com.westchase.persistence.criteria.TodoSearchCriteria;
import com.westchase.persistence.model.Todo;

/**
 * @author marc
 *
 */
public class TodoDAO extends BaseDAO<Todo> {

	public List<Todo> listCurrent(int employeeId) {
		List<Todo> todos = new ArrayList<Todo>();
		String query = "select t from Todo t where t.completeDate is null and t.employee.id = :empid and t.dueDate < date_add_interval(curdate(), 2, DAY) and t.expireDate > curdate() order by t.dueDate, t.createDate";
		try {
			todos = getSession().createQuery(query).setInteger("empid", new Integer(employeeId)).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return todos;
	}

	public List<Todo> findAll(TodoSearchCriteria criteria) {
		List<Todo> todos = new ArrayList<Todo>();
		StringBuffer query = new StringBuffer("select t from Todo t where 1=1 ");
		Todo t = criteria.getSearchObject();
		if (t != null) {
			if (t.getId() != null) {
				query.append(" and t.id = :id ");
			}
			if (StringUtils.isNotBlank(t.getText())) {
				query.append(" and t.text like concat('%','").append(t.getText()).append("','%')");				
			}
			if (t.getEmployee() != null) {
				query.append(" and t.employee.id = :empid ");
			}
		}
		try {
			Query q = getSession().createQuery(query.toString());
			if (t != null) {
				if (t.getId() != null) {
					q.setParameter("id", t.getId());
				}
				if (t.getEmployee() != null) {
					q.setParameter("empid", t.getEmployee().getId());
				}
			}
			todos = prepareQuery(q, criteria).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return todos;
	}

}
