package com.westchase.ejb;

import java.util.List;

import javax.ejb.Local;

import com.westchase.persistence.dto.report.ContactDTO;
import com.westchase.persistence.dto.report.PhoneBookCompanyDTO;
import com.westchase.persistence.dto.report.PhoneBookPropertyDTO;
import com.westchase.persistence.dto.report.PropertyCompanyPhoneBookDTO;
import com.westchase.persistence.model.Category;
import com.westchase.persistence.model.Naics;
import com.westchase.persistence.model.PhoneBook;
import com.westchase.persistence.model.Property;

@Local
public interface ReportService {
	
	
	// helper to list categories for reports
	List<Category> getCategories();
	
	// helper to list NAICS codes for reports
	List<Naics> getNaicss();

//	List<CompanyPhoneBookDTO> runCompanyPhoneBookByCategoryReport(List<String> categoryCodes);
	
	List<PhoneBookCompanyDTO> runCompanyByNameReport(String name, boolean westchaseOnly);

	
	List<ContactDTO> runFlagPoleContactsReport(boolean westchaseOnly);
	
	// --- TC reports ----
	
	// this is the 'tc company all employees' report
	List<PhoneBook> runCityOfHoustonEmployeesReport();
	
	// this is the 'tc trespass agreement map#' report
	List<Property> runTrespassAgreementMapNoReport(boolean westchaseOnly);
	
	// this encompasses 'tc security contacts' and 'tc pip email source'
	List<ContactDTO> runEmailContactsByCategoryCodeReport(List<String> categoryCodes, boolean westchaseOnly, int employeeCount);

	// this encompasses 'tc board member' and 'tc property contacts'
	// 'tc hotel managers' is very close and so I am adding it
	// 'sf universal' and 'sf dedup westchase today' same deal
	List<ContactDTO> runContactsByCategoryCodeReport(List<String> categoryCodes, boolean westchaseOnly, int employeeCount);
	
	// this encompasses 'tc bank alert' and 'tc restaurant alert'
	List<ContactDTO> runWCBusinessContactsByNAICSReport(List<String> naicsCodes, boolean westchaseOnly);

	
	// requested by Vivian Little on 6/23/2010 -- see email
	List<PhoneBookPropertyDTO> runOfficeBuildingPropertyReport(int squareFootage, double occupancy, boolean westchaseOnly);

	// added after meeting on 2014-10-23 for Irma/Dave
	List<PropertyCompanyPhoneBookDTO> runPropertyCompanyPhoneBookReport(Integer propertyId);

}
