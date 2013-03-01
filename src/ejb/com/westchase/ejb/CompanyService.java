package com.westchase.ejb;

import java.util.List;

import javax.ejb.Local;

import com.westchase.persistence.criteria.CompanySearchCriteria;
import com.westchase.persistence.model.Company;
import com.westchase.persistence.model.CompanyMapno;
import com.westchase.persistence.model.CompanyType;
import com.westchase.persistence.model.Naics;
import com.westchase.persistence.model.State;
import com.westchase.persistence.model.Street;

@Local
public interface CompanyService {

	void saveOrUpdate(Company company, int employeeId);

	Company get(Integer companyId);

	List<Company> findAll(CompanySearchCriteria criteria);

	List<Company> findAllWithOrder(String order);

	Company getByPhoneBook(Integer phoneBookId);

	boolean saveCompanyProperty(Integer id, Integer companyId, Integer propertyId, boolean primary);

	void removeCompanyProperty(Integer companyPropertyId);

	CompanyMapno getCompanyProperty(Integer companyPropertyId);

	List<Company> getByProperty(Integer propertyId);
	
	List<State> findAllStates();

	long findAllCount(CompanySearchCriteria criteria);

	List<Naics> findAllNaics();

	void delete(Integer id);
	
	List<CompanyType> listCompanyTypes();

	List<Company> findAllWithType(int companyTypeId);
	
	List<Street> listStreets();

}
