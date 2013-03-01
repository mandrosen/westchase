package com.westchase.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.westchase.persistence.dto.report.ContactDTO;
import com.westchase.persistence.dto.utils.SentEmailDTO;
import com.westchase.persistence.model.SentEmail;

/**
 * @author marc
 *
 */
@Local
public interface EmailService {

	Integer storeEmailTemplate(Integer employeeId, String subject, String message);
	
	Integer storeEmailTemplate(Integer employeeId, String subject, String message, byte[] attachment, String attachmentName);
	
	void storeSentEmail(Integer employeeId, Integer messageTemplateId, Integer phoneBookId, String from, String to, String subject, String message, String messageId);

	void storeSentEmail(Integer employeeId, String from, String to, String subject, String message, byte[] attachment, String attachmentName, String messageId);
	
	// Email Blast
	List<ContactDTO> findEmails(List<String> selectedCategories, List<String> selectedNaics, Integer block,
			String street, String westchaseTodaySearch, String dontEmailSearch);
	

	List<ContactDTO> findEmails(List<Integer> phoneBookIds);

	List<SentEmail> findSentEmails(Integer employeeId, Date startDate, Date endDate, String toEmail);
	
	SentEmailDTO getSentEmail(Integer sentEmailId);
}
