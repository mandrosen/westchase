package com.westchase.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.apache.commons.lang.StringUtils;
import org.jboss.ejb3.annotation.SecurityDomain;

import com.westchase.persistence.dao.EmailTemplateDAO;
import com.westchase.persistence.dao.PhoneBookDAO;
import com.westchase.persistence.dao.SentEmailDAO;
import com.westchase.persistence.dto.report.ContactDTO;
import com.westchase.persistence.dto.utils.SentEmailDTO;
import com.westchase.persistence.model.EmailTemplate;
import com.westchase.persistence.model.Employee;
import com.westchase.persistence.model.PhoneBook;
import com.westchase.persistence.model.SentEmail;

/**
 * @author marc
 *
 */
@Stateless
@SecurityDomain("WestchaseRealm")
@Local(EmailService.class)
public class EmailServiceBean implements EmailService {

	@Override
	public void storeSentEmail(Integer employeeId, Integer messageTemplateId, Integer phoneBookId, String from, String to, String subject,
			String message, String messageId) {
		SentEmail sent = new SentEmail();
		sent.setEmployee(new Employee(employeeId));
		sent.setEmailTemplate(new EmailTemplate(messageTemplateId));
		sent.setPhoneBook(new PhoneBook(phoneBookId));
		sent.setFromAddress(from);
		sent.setToAddress(to);
		sent.setSubject(subject);
		sent.setMessage(message);
		sent.setMessageId(messageId);
		sent.setSentDate(new Date());
		SentEmailDAO dao = new SentEmailDAO();
		dao.save(sent);
	}
	
	@Override
	public void storeSentEmail(Integer employeeId, String from, String to, String subject, String message, byte[] attachment, String attachmentName, String messageId) {
		SentEmail sent = new SentEmail();
		sent.setEmployee(new Employee(employeeId));
		sent.setFromAddress(from);
		sent.setToAddress(to);
		sent.setSubject(subject);
		sent.setMessage(message);
		sent.setAttachment(attachment);
		sent.setAttachmentName(attachmentName);
		sent.setMessageId(messageId);
		sent.setSentDate(new Date());
		SentEmailDAO dao = new SentEmailDAO();
		dao.save(sent);
	}


	@Override
	public Integer storeEmailTemplate(Integer employeeId, String subject, String message) {
		EmailTemplate email = createEmailTemplate(employeeId, subject, message);
		EmailTemplateDAO dao = new EmailTemplateDAO();
		long id = dao.save(email);
		return new Integer((int) id);
	}


	@Override
	public Integer storeEmailTemplate(Integer employeeId, String subject, String message, byte[] attachment, String attachmentName) {
		EmailTemplate email = createEmailTemplate(employeeId, subject, message);
		email.setAttachment(attachment);
		email.setAttachmentName(attachmentName);
		EmailTemplateDAO dao = new EmailTemplateDAO();
		long id = dao.save(email);
		return new Integer((int) id);
	}


	@Override
	public List<ContactDTO> findEmails(List<String> selectedCategories, List<String> selectedNaics, Integer block,
			String street, String westchaseSearch, String dontEmailSearch) {
		Boolean westchase = null;
		Boolean dontEmail = null;
		try {
			if (StringUtils.isNotBlank(westchaseSearch)) {
				westchase = Boolean.valueOf(westchaseSearch);
			}
			if (StringUtils.isNotBlank(dontEmailSearch)) {
				dontEmail = Boolean.valueOf(dontEmailSearch);
			}
		} catch (Exception e) {}
		PhoneBookDAO dao = new PhoneBookDAO();
		return dao.findEmails(selectedCategories, selectedNaics, block, street, westchase, dontEmail);
	}


	@Override
	public List<ContactDTO> findEmails(List<Integer> phoneBookIds) {
		PhoneBookDAO dao = new PhoneBookDAO();
		return dao.findEmails(phoneBookIds);
	}
	
	private EmailTemplate createEmailTemplate(Integer employeeId, String subject, String message) {
		EmailTemplate email = new EmailTemplate();
		email.setCreateDate(new Date());
		email.setEmployee(new Employee(employeeId));
		email.setSubject(subject);
		email.setMessage(message);
		return email;
	}


	@Override
	public List<SentEmail> findSentEmails(Integer employeeId, Date startDate, Date endDate, String toEmail) {
		SentEmailDAO dao = new SentEmailDAO();
		return dao.findSentEmails(employeeId, startDate, endDate, toEmail);
	}


	@Override
	public SentEmailDTO getSentEmail(Integer sentEmailId) {
		SentEmailDAO dao = new SentEmailDAO();
		return dao.getSentEmail(sentEmailId);
	}

	
}
