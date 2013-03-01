package com.westchase.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

import com.westchase.persistence.criteria.MessageSearchCriteria;
import com.westchase.persistence.model.Message;

/**
 * @author marc
 *
 */
public class MessageDAO extends BaseDAO<Message> {

	/**
	 * Returns messages whose due date is less than two days from the current date 
	 * (including messages whose due date is in the past) that is not expired.
	 * 
	 * @return list of Message
	 */
	public List<Message> listCurrent() {
		List<Message> messages = new ArrayList<Message>();
		String query = "select m from Message m where m.dueDate < date_add_interval(curdate(), 2, DAY) and m.expireDate > curdate() order by m.dueDate, m.createDate";
		try {
			messages = getSession().createQuery(query).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return messages;
	}

	public List<Message> findAll(MessageSearchCriteria criteria) {
		List<Message> messages = new ArrayList<Message>();
		StringBuffer query = new StringBuffer("select m from Message m where 1=1 ");
		Message m = criteria.getSearchObject();
		if (m != null) {
			if (m.getId() != null) {
				query.append(" and m.id = :id ");
			}
			if (StringUtils.isNotBlank(m.getText())) {
				query.append(" and m.text like concat('%','").append(m.getText()).append("','%')");				
			}
			if (m.getEmployee() != null) {
				query.append(" and m.employee.id = :empid ");
			}
		}
		try {
			Query q = getSession().createQuery(query.toString());
			if (m != null) {
				if (m.getId() != null) {
					q.setParameter("id", m.getId());
				}
				if (m.getEmployee() != null) {
					q.setParameter("empid", m.getEmployee().getId());
				}
			}
			messages = prepareQuery(q, criteria).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return messages;
	}

}
