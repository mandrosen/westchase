package com.westchase.web.action.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.ByteArrayDataSource;

import com.opensymphony.xwork2.Preparable;
import com.westchase.ejb.EmailService;
import com.westchase.ejb.ReportService;
import com.westchase.email.HtmlEmail2;
import com.westchase.persistence.dto.report.ContactDTO;
import com.westchase.persistence.model.Category;
import com.westchase.persistence.model.Employee;
import com.westchase.persistence.model.Naics;
import com.westchase.persistence.model.PhoneBook;
import com.westchase.utils.EmailUtils;
import com.westchase.web.action.AbstractWestchaseAction;

/**
 * @author marc
 *
 */
public class EmailBlastAction extends AbstractWestchaseAction implements Preparable {
	
    private ReportService reportServ;

    private EmailService emailServ;
    private InitialContext ctx = null;     
    private List<ContactDTO> contacts;
    
    private List<Category> availableCategories = new ArrayList<Category>();

	private List<String> selectedCategories = new ArrayList<String>();

	private List<Naics> availableNaics = new ArrayList<Naics>();
	private List<String> selectedNaics = new ArrayList<String>();
	private Integer block;
	private String street;
	private List<Integer> selectedPeople;
	private int selectedCount;
	private String emailAddresses;
//	private String phoneBookIds; // stores the selected ids on the email page
	private String emailMessage;
	
	private String emailSubject;
	private String dontEmailSearch = "true";
	private String investorSearch;
	private String fromMe;
	
	private boolean success;
    
    private String westchaseTodaySearch;

	private PhoneBook searchObject;
	
	private int sendTest;
	private String testEmail;
	

//    private File[] embeddedimg;//The actual file
//    private String[] embeddedimgContentType; //The content type of the file
//    private String embeddedimgFileName; //The uploaded file name
	

    private File upload;//The actual file
    private String uploadContentType; //The content type of the file
    private String uploadFileName; //The uploaded file name

	public List<Category> getAvailableCategories() {
		return availableCategories;
	}

	public List<Naics> getAvailableNaics() {
		return availableNaics;
	}

	public Integer getBlock() {
		return block;
	}

	public List<ContactDTO> getContacts() {
		return contacts;
	}

	public String getDontEmailSearch() {
		return dontEmailSearch;
	}

	public String getEmailAddresses() {
		return emailAddresses;
	}

	public String getEmailMessage() {
		return emailMessage;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public String getInvestorSearch() {
		return investorSearch;
	}

	public PhoneBook getSearchObject() {
		return searchObject;
	}

	public List<String> getSelectedCategories() {
		return selectedCategories;
	}

	public List<String> getSelectedNaics() {
		return selectedNaics;
	}


	public String getStreet() {
		return street;
	}

	public String getWestchaseTodaySearch() {
		return westchaseTodaySearch;
	}
    public String input() {
		return SUCCESS;
	}
    @Override
	public void prepare() throws Exception {
        try {
            ctx = new InitialContext();
			reportServ = (ReportService) ctx.lookup("westchase/ReportServiceBean/local");
        } catch (Exception e) {
            log.error("", e); 
        }     		
        if (reportServ != null) {
        	availableCategories = reportServ.getCategories();
        	availableNaics = reportServ.getNaicss();
        }
	}
    
    public String search() {
//        PhoneBookSearchCriteria criteria = new PhoneBookSearchCriteria();
//        PhoneBook so = getSearchObject();
//        criteria.setSearchObject(so);
        try {
            ctx = new InitialContext();
            emailServ = (EmailService) ctx.lookup("westchase/EmailServiceBean/local");
        } catch (Exception e) {
            log.error("", e); 
        } 
        if (emailServ != null) {
        	if ((getSelectedCategories() == null || getSelectedCategories().isEmpty()) &&
        			(getSelectedNaics() == null || getSelectedNaics().isEmpty()) &&
        			(getBlock() == null || getBlock().intValue() == 0) && StringUtils.isBlank(getStreet()) &&
        			StringUtils.isBlank(getWestchaseTodaySearch()) && StringUtils.isBlank(getDontEmailSearch())) {
        		addActionMessage("Must select at least one field");
        	} else { 
	        	contacts = emailServ.findEmails(getSelectedCategories(), getSelectedNaics(),
	        			getBlock(), getStreet(), getWestchaseTodaySearch(), getDontEmailSearch());
        	}
        }
        return SUCCESS;
    }
	
    public String select() {
    	setSelectedCount(0);
        try {
            ctx = new InitialContext();
            emailServ = (EmailService) ctx.lookup("westchase/EmailServiceBean/local");
        } catch (Exception e) {
            log.error("", e); 
        } 

		List<Integer> people = getSelectedPeople();
		if (people == null || people.size() < 1) {
			addActionMessage("Must select at least on person");
			return INPUT;
		}
		if (emailServ != null) {
			contacts = emailServ.findEmails(people);
		}
		if (contacts != null && !contacts.isEmpty()) {
			setSelectedCount(contacts.size());
			return SUCCESS;
		}
		addActionMessage("There was a problem with the selected contacts");
		return INPUT;
	}


    
	public String send() {
        try {
            ctx = new InitialContext();
            emailServ = (EmailService) ctx.lookup("westchase/EmailServiceBean/local");
        } catch (Exception e) {
            log.error("", e); 
        }
        
        boolean test = getSendTest() > 0;
        
        setSuccess(false);

		Employee emp = getEmployee();
		
		Integer templateId = null;
		byte[] data = null;
		// add the attachment
		if (upload != null) {
			try {
				data = FileUtils.readFileToByteArray(upload);
			} catch (Exception e) {
				log.warn("", e);
			}
			if (!test) {
				templateId = emailServ.storeEmailTemplate(emp.getId(), getEmailSubject(), getEmailMessage(), data, uploadFileName);
			}
		} else {
			if (!test) {
				templateId = emailServ.storeEmailTemplate(emp.getId(), getEmailSubject(), getEmailMessage());
			}
		}
		
		 
		
        String[] from = new String[] {"wdguest@westchasedistrict.com", "Westchase District" };
        if (StringUtils.isNotBlank(getFromMe())) {
        	if (Boolean.valueOf(getFromMe()).booleanValue()) {
        		from = new String[] { emp.getEmail(), emp.getFirstName() + " " + emp.getLastName() };
        	}
        }
        
		List<Integer> people = getSelectedPeople();
		if (emailServ != null) {
			contacts = emailServ.findEmails(people);

			if (emp != null) {
				try {
					String fromEmail = from[0];
					String fromName = from[1];
					if (test && StringUtils.isNotBlank(getTestEmail())) {
						ContactDTO testContact = new ContactDTO("Dr.", "Pat", "Pat", "Smith", getTestEmail(), "Test LLC");
						
						sendEmailBlast(test, emp, fromEmail, fromName, testContact, data, templateId);
						return "email";
					} else {
						for (ContactDTO contact : contacts) {
							
							sendEmailBlast(test, emp, fromEmail, fromName, contact, data, templateId);
						}
					}
				} catch (Exception e) {
					log.error("", e);
				}
			}
		}
		
//		return ERROR;
		return SUCCESS;
	}
	
	private void sendEmailBlast(boolean test, Employee emp, String fromEmail, String fromName, ContactDTO contact, byte[] data, Integer templateId) throws Exception {
		HtmlEmail2 email = new HtmlEmail2();
		// Create the email message
//		MultiPartEmail email = new MultiPartEmail();
		// TODO: change in production
//		email.setHostName("localhost");
//        email.setAuthentication("tesuser", "password");
//		email.setHostName("172.25.16.2");
//		email.setHostName("wcd01");
//        email.setAuthentication("MRosenthal", "*Westchase2009!");
//        email.setTLS(true);
//        email.setSSL(false);
		email.setMailSessionFromJNDI("java:/Mail");
		email.setFrom(fromEmail, fromName);

		
		EmailUtils.addAddress(email, contact.getEmail(), contact.getFirstName() + " " + contact.getLastName());
		// TODO: TEST: TESTING
//		email.addTo("mandrosen@gmail.com", contact.getFirstName() + " " + contact.getLastName());
//		email.addTo("testuser@localhost", contact.getFirstName() + " " + contact.getLastName());
//		email.addTo(emp.getEmail(), contact.getFirstName() + " " + contact.getLastName());
		
        String subj = getEmailSubject();
        subj = merge(subj, contact);
		email.setSubject(subj);
		
		if (data != null && data.length > 0) {
			email.attach(new ByteArrayDataSource(data, getUploadContentType()), "attachment", "attachment");
		}					
		
		String msg = getEmailMessage();
		msg = merge(msg, contact);
		email.setMsg(msg);
		
		// send the email
		String messageId = email.send();
		if (!test) {
			emailServ.storeSentEmail(emp.getId(), templateId, contact.getId(), fromEmail, contact.getEmail(), subj, msg, messageId);
		}
		setSuccess(true);
	}

	private String merge(String msg, ContactDTO contact) {
		msg = StringUtils.replace(msg, "[[title]]", StringUtils.defaultIfEmpty(contact.getTitle(), ""));
		msg = StringUtils.replace(msg, "[[salutation]]", StringUtils.defaultIfEmpty(contact.getSalutation(), ""));;
		msg = StringUtils.replace(msg, "[[firstname]]", StringUtils.defaultIfEmpty(contact.getFirstName(), ""));
		msg = StringUtils.replace(msg, "[[lastname]]", StringUtils.defaultIfEmpty(contact.getLastName(), ""));
		msg = StringUtils.replace(msg, "[[company]]", StringUtils.defaultIfEmpty(contact.getCompany(), ""));
		msg = StringUtils.replace(msg, "[[email]]", StringUtils.defaultIfEmpty(contact.getEmail(), ""));
		return msg;
	}

	public void setAvailableCategories(List<Category> availableCategories) {
		this.availableCategories = availableCategories;
	}
	public void setAvailableNaics(List<Naics> availableNaics) {
		this.availableNaics = availableNaics;
	}
	

	public void setBlock(Integer block) {
		this.block = block;
	}
	
	public void setContacts(List<ContactDTO> contacts) {
		this.contacts = contacts;
	}
	
	public void setDontEmailSearch(String dontEmailSearch) {
		this.dontEmailSearch = dontEmailSearch;
	}

	public void setEmailAddresses(String emailAddresses) {
		this.emailAddresses = emailAddresses;
	}

	public void setEmailMessage(String emailMessage) {
		this.emailMessage = emailMessage;
	}


	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public void setInvestorSearch(String investorSearch) {
		this.investorSearch = investorSearch;
	}

	public void setSearchObject(PhoneBook searchObject) {
		this.searchObject = searchObject;
	}

	public void setSelectedCategories(List<String> selectedCategories) {
		this.selectedCategories = selectedCategories;
	}

	public void setSelectedNaics(List<String> selectedNaics) {
		this.selectedNaics = selectedNaics;
	}


	public void setStreet(String street) {
		this.street = street;
	}

	public void setWestchaseTodaySearch(String westchaseTodaySearch) {
		this.westchaseTodaySearch = westchaseTodaySearch;
	}

	public List<Integer> getSelectedPeople() {
		return selectedPeople;
	}

	public void setSelectedPeople(List<Integer> selectedPeople) {
		this.selectedPeople = selectedPeople;
	}

	public String getFromMe() {
		return fromMe;
	}

	public void setFromMe(String fromMe) {
		this.fromMe = fromMe;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getSelectedCount() {
		return selectedCount;
	}

	public void setSelectedCount(int selectedCount) {
		this.selectedCount = selectedCount;
	}

	public int getSendTest() {
		return sendTest;
	}

	public void setSendTest(int sendTest) {
		this.sendTest = sendTest;
	}

	public String getTestEmail() {
		return testEmail;
	}

	public void setTestEmail(String testEmail) {
		this.testEmail = testEmail;
	}

//	public File[] getEmbeddedimg() {
//		return embeddedimg;
//	}
//
//	public void setEmbeddedimg(File[] embeddedimg) {
//		this.embeddedimg = embeddedimg;
//	}
//
//	public String[] getEmbeddedimgContentType() {
//		return embeddedimgContentType;
//	}
//
//	public void setEmbeddedimgContentType(String[] embeddedimgContentType) {
//		this.embeddedimgContentType = embeddedimgContentType;
//	}
//
//	public String getEmbeddedimgFileName() {
//		return embeddedimgFileName;
//	}
//
//	public void setEmbeddedimgFileName(String embeddedimgFileName) {
//		this.embeddedimgFileName = embeddedimgFileName;
//	}

//	public String getPhoneBookIds() {
//		return phoneBookIds;
//	}
//
//	public void setPhoneBookIds(String phoneBookIds) {
//		this.phoneBookIds = phoneBookIds;
//	}
	
//  private List<Integer> getIds() {
//	List<Integer> ids = new ArrayList<Integer>();
//	if (StringUtils.isNotBlank(phoneBookIds)) {
//		if (phoneBookIds.indexOf(',') > -1) {
//	    	String[] idsstr = phoneBookIds.split(",");
//			for (String id : idsstr) {
//    			try {
//    				ids.add(new Integer(id));
//    			} catch (Exception e) {
//    				log.error("invalid integer: " + id, e);
//    			}
//			}
//		} else {
//			try {
//				ids.add(new Integer(phoneBookIds));
//			} catch (Exception e) {
//				log.error("invalid integer: " + phoneBookIds, e);
//			}
//		}
//	}
//	
//	return ids;
//}	
}
