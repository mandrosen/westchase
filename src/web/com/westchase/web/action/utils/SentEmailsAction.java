package com.westchase.web.action.utils;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.Preparable;
import com.westchase.ejb.EmailService;
import com.westchase.persistence.dto.utils.SentEmailDTO;
import com.westchase.persistence.model.EmailTemplate;
import com.westchase.persistence.model.Employee;
import com.westchase.persistence.model.SentEmail;
import com.westchase.web.action.AbstractWestchaseAction;

/**
 * @author marc
 *
 */
public class SentEmailsAction  extends AbstractWestchaseAction implements Preparable, ServletResponseAware {

    private EmailService emailServ;
    private InitialContext ctx = null;  
    
    private String startDateStr;
    private String endDateStr;
    private String toEmail;
    
    private List<SentEmail> sentEmails;
    
    private Integer sentEmailId;
    private SentEmailDTO sentEmail;
    
    private HttpServletResponse response;
    
	@Override
	public void prepare() throws Exception {
	}
	
	public String list() {
        try {
            ctx = new InitialContext();
            emailServ = (EmailService) ctx.lookup("westchase/EmailServiceBean/local");
        } catch (Exception e) {
            log.error("", e); 
        } 
        if (emailServ != null) {
        	Employee emp = getEmployee();
        	Date startDate = null;
        	Date endDate = null;
        	if (startDateStr != null) {
        		try {
        			startDate = DATE_FORMAT.parse(startDateStr);
        		} catch (Exception e) {}
        	}
        	if (endDateStr != null) {
        		try {
        			endDate = DATE_FORMAT.parse(endDateStr);
        		} catch (Exception e) {}
        	}
        	sentEmails = emailServ.findSentEmails(emp.getId(), startDate, endDate, toEmail);
        	if (sentEmails != null && !sentEmails.isEmpty()) {
        		for (SentEmail sentEmail : sentEmails) {

	    			
	    			if (sentEmail.getEmailTemplate() != null) {
	    				// set attachment data to email if available
	    				EmailTemplate template = sentEmail.getEmailTemplate();
	    				byte[] attachment = template.getAttachment();
	    				String attachmentName = template.getAttachmentName();
	    				
	    				sentEmail.setAttachment(attachment);
	    				sentEmail.setAttachmentName(attachmentName);
	    			}
        		}
        	}
        }
        return SUCCESS;
	}
	
	public String view() {
        try {
            ctx = new InitialContext();
            emailServ = (EmailService) ctx.lookup("westchase/EmailServiceBean/local");
        } catch (Exception e) {
            log.error("", e); 
        } 
        if (emailServ != null && this.sentEmailId != null) {
        	sentEmail = emailServ.getSentEmail(sentEmailId);

    		SentEmail email = sentEmail.getSentEmail();
			
			if (email.getEmailTemplate() != null) {
				// set attachment data to email if available
				EmailTemplate template = email.getEmailTemplate();
				byte[] attachment = template.getAttachment();
				String attachmentName = template.getAttachmentName();
				
				sentEmail.getSentEmail().setAttachment(attachment);
				sentEmail.getSentEmail().setAttachmentName(attachmentName);
			}
        }
		return SUCCESS;
	}
	
	public void viewAttachment() {
        try {
            ctx = new InitialContext();
            emailServ = (EmailService) ctx.lookup("westchase/EmailServiceBean/local");
        } catch (Exception e) {
            log.error("", e); 
        }
        if (emailServ != null && this.sentEmailId != null) {
        	sentEmail = emailServ.getSentEmail(sentEmailId);
        	
        	if (sentEmail != null) {
        		SentEmail email = sentEmail.getSentEmail();
        		
        		if (email != null) {

        			byte[] attachment = email.getAttachment();
        			String attachmentName = email.getAttachmentName();
        			
        			if (attachment == null && email.getEmailTemplate() != null) {
        				EmailTemplate template = email.getEmailTemplate();
        				attachment = template.getAttachment();
        				attachmentName = template.getAttachmentName();
        			}
        			if (attachment != null && attachmentName != null) {
	        			try {
	        				this.response.setContentType(getContentType(attachmentName));
							this.response.getOutputStream().write(attachment);
							this.response.setContentLength(attachment.length);
	
							this.response.setHeader("Content-disposition", "inline; filename=" + attachmentName);
						} catch (IOException e) {
							log.error("", e);
						}
        			}
        		}
        	}
        }
	}
	
	
	private String getContentType(String attachmentName) {
		String contentType = "text/plain";
		if (StringUtils.isNotBlank(attachmentName)) {
			String ext = StringUtils.substringAfterLast(attachmentName, ".");
			if (StringUtils.isNotBlank(ext)) {
				ext = ext.toLowerCase();
				if ("jpeg".equals(ext) || "jpg".equals(ext) || "jpe".equals(ext)) {
					contentType = "image/jpeg";
				} else if ("gif".equals(ext)) {
					contentType = "image/gif";
				} else if ("png".equals(ext)) {
					contentType = "image/png";
				} else if ("bmp".equals(ext)) {
					contentType = "image/bmp";
				} else if ("doc".equals(ext) || "docx".equals(ext)) {
					contentType = "application/msword";
				} else if ("xls".equals(ext)) {
					contentType = "application/vnd.ms-excel";
				} else if ("ppt".equals(ext)) {
					contentType = "application/vnd.ms-powerpoint";
				} else if ("pdf".equals(ext)) {
					contentType = "application/pdf";
				} else if ("zip".equals(ext)) {
					contentType = "application/zip";
				}
			}
		}
		return contentType;
	}

	public String getStartDateStr() {
		return startDateStr;
	}

	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public List<SentEmail> getSentEmails() {
		return sentEmails;
	}

	public void setSentEmails(List<SentEmail> sentEmails) {
		this.sentEmails = sentEmails;
	}

	public Integer getSentEmailId() {
		return sentEmailId;
	}

	public void setSentEmailId(Integer sentEmailId) {
		this.sentEmailId = sentEmailId;
	}

	public SentEmailDTO getSentEmail() {
		return sentEmail;
	}

	public void setSentEmail(SentEmailDTO sentEmail) {
		this.sentEmail = sentEmail;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

}
