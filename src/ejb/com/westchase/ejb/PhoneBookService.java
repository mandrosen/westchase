package com.westchase.ejb;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.westchase.persistence.criteria.PhoneBookSearchCriteria;
import com.westchase.persistence.dto.cms.PhoneBookPropertyDTO;
import com.westchase.persistence.model.PhoneBook;

@Local
public interface PhoneBookService {

	void saveOrUpdate(PhoneBook person, List<String> categories, Map<Integer, String> properties);

	PhoneBook get(Integer personId);

	List<PhoneBook> findAll(PhoneBookSearchCriteria criteria);

	List<PhoneBook> findAll();

	long findAllCount(PhoneBookSearchCriteria criteria);

	List<PhoneBook> findByCompany(Integer companyId);

	List<PhoneBookPropertyDTO> findPhoneBookProperties(Integer phoneBookId, Integer companyId);

	void delete(Integer id);

//	List<PhoneBook> findAll(List<String> categoryCodes, boolean westchase, Integer propertyId);

}
