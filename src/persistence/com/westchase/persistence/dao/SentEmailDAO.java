package com.westchase.persistence.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;

import com.westchase.persistence.dto.utils.SentEmailDTO;
import com.westchase.persistence.model.SentEmail;

/**
 * @author marc
 *
 */
public class SentEmailDAO extends BaseDAO<SentEmail> {

	public List<SentEmail> findSentEmails(Integer employeeId, Date startDate, Date endDate, String toEmail) {
		List<SentEmail> emails = new ArrayList<SentEmail>();
		StringBuffer query = new StringBuffer("select e from SentEmail e where e.employee.id = :empid ");
		if (startDate != null) {
			query.append(" and e.sentDate >= :startdt ");
		}
		if (endDate != null) {
			query.append(" and e.sentDate <= :enddt ");
		}
		if (StringUtils.isNotBlank(toEmail)) {
			query.append(" and e.toAddress like concat('%','").append(toEmail).append("','%')");
		}
		query.append(" order by e.sentDate desc");
		try {
			Query q = getSession().createQuery(query.toString());
			q.setParameter("empid", employeeId);
			if (startDate != null) {
				q.setParameter("startdt", startDate);
			}
			if (endDate != null) {
				q.setParameter("enddt", endDate);
			}
			emails = q.list();
		} catch (Exception e) {
			log.error("", e);
		}
		return emails;
	}
	
	public SentEmailDTO getSentEmail(Integer sentEmailId) {
		SentEmailDTO email = null;
		String query = "select new com.westchase.persistence.dto.utils.SentEmailDTO(e, t, p) from SentEmail e left outer join e.emailTemplate t left outer join e.phoneBook p where e.id = :emailid";
		try {
			
			email = (SentEmailDTO) getSession().createQuery(query).setParameter("emailid", sentEmailId).setMaxResults(1).uniqueResult();
		} catch (Exception e) {
			log.error("", e);
		}
		return email;
	}

}
