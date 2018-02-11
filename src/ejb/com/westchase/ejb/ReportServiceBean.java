package com.westchase.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.hibernate.criterion.Order;
import org.jboss.ejb3.annotation.SecurityDomain;

import com.westchase.persistence.dao.CategoryDAO;
import com.westchase.persistence.dao.CompanyDAO;
import com.westchase.persistence.dao.NaicsDAO;
import com.westchase.persistence.dao.PhoneBookDAO;
import com.westchase.persistence.dao.PropertyDAO;
import com.westchase.persistence.dto.report.ContactDTO;
import com.westchase.persistence.dto.report.PhoneBookCompanyDTO;
import com.westchase.persistence.dto.report.PhoneBookPropertyDTO;
import com.westchase.persistence.dto.report.PropertyCompanyPhoneBookDTO;
import com.westchase.persistence.model.Category;
import com.westchase.persistence.model.Naics;
import com.westchase.persistence.model.PhoneBook;
import com.westchase.persistence.model.Property;

@Stateless
@SecurityDomain("WestchaseRealm")
@Local(ReportService.class)
public class ReportServiceBean implements ReportService {
	
	public List<Category> getCategories() {
		CategoryDAO dao = new CategoryDAO();
		return dao.findAll(Order.asc("categoryDesc"));
	}
	
	public List<Naics> getNaicss() {
		NaicsDAO dao = new NaicsDAO();
		return dao.findAll(Order.asc("description"));
	}

	@Override
	public List<ContactDTO> runFlagPoleContactsReport(boolean westchaseOnly) {
		PropertyDAO dao = new PropertyDAO();
		return dao.getFlagPoleContacts(westchaseOnly);
	}

	@Override
	public List<PhoneBookCompanyDTO> runCompanyByNameReport(String name, boolean westchaseOnly) {
		PhoneBookDAO dao = new PhoneBookDAO();
		return dao.findByName(name, westchaseOnly);
	}

	// --- TC reports ----

	@Override
	// this is the 'tc company all employees' report
	public List<PhoneBook> runCityOfHoustonEmployeesReport() {
		PhoneBookDAO dao = new PhoneBookDAO();
		return dao.getCityOfHoustonEmployees();
	}

	@Override
	// this is the 'tc trespass agreement map#' report
	public List<Property> runTrespassAgreementMapNoReport(boolean westchaseOnly) {
		PropertyDAO dao = new PropertyDAO();
		return dao.findWithTrespassAgreement(westchaseOnly);
	}

	@Override
	// this encompasses 'tc security contacts' and 'tc pip email source'
	public List<ContactDTO> runEmailContactsByCategoryCodeReport(List<String> categoryCodes, boolean westchaseOnly, int employeeCount) {
		PhoneBookDAO dao = new PhoneBookDAO();
		return dao.getContactsByCategoryCode(categoryCodes, true, westchaseOnly, employeeCount);
	}

	@Override
	// this encompasses 'tc board member' and 'tc property contacts'
	// 'tc hotel managers' is very close and so I am adding it
	// 'sf universal' and 'sf dedup westchase today' same deal
	public List<ContactDTO> runContactsByCategoryCodeReport(List<String> categoryCodes, boolean westchaseOnly, int employeeCount) {
		PhoneBookDAO dao = new PhoneBookDAO();
		return dao.getContactsByCategoryCode(categoryCodes, false, westchaseOnly, employeeCount);
	}

	@Override
	// this encompasses 'tc bank alert' and 'tc restaurant alert'
	public List<ContactDTO> runWCBusinessContactsByNAICSReport(List<String> naicsCodes, boolean westchaseOnly) {
		PhoneBookDAO dao = new PhoneBookDAO();
		return dao.getWCBusinessContactsByNAICS(naicsCodes, westchaseOnly);
	}

	@Override
	public List<PhoneBookPropertyDTO> runOfficeBuildingPropertyReport(int squareFootage, double occupancy, boolean westchaseOnly) {
		PropertyDAO dao = new PropertyDAO();
		return dao.findOfficeBuildingProperties(squareFootage, occupancy, westchaseOnly);
	}

	@Override
	public List<PropertyCompanyPhoneBookDTO> runPropertyCompanyPhoneBookReport(List<Integer> propertyIdList) {
		PropertyDAO dao = new PropertyDAO();
		return dao.listPropertyCompanyPhoneBooks(propertyIdList);
	}

	@Override
	public List<PropertyCompanyPhoneBookDTO> runContactsByAddressCategory(int startAddress, int endAddress, String street, String streetWildcard, List<String> categoryCodes) {
		PropertyDAO dao = new PropertyDAO();
		return dao.listContactsByAddressCategory(startAddress, endAddress, street, streetWildcard, categoryCodes);
	}
	
	@Override
	public List<ContactDTO> ruMajorEmployersReport() {
		CompanyDAO dao = new CompanyDAO();
		return dao.listMajorEmployers();
	}
}