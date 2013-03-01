package com.westchase.ejb;

import java.util.List;

import javax.ejb.Local;

import com.westchase.persistence.criteria.PropertySearchCriteria;
import com.westchase.persistence.dto.cms.CompanyPropertyDTO;
import com.westchase.persistence.model.FlagSize;
import com.westchase.persistence.model.Property;
import com.westchase.persistence.model.PropertyType;

@Local
public interface PropertyService {
	void saveOrUpdate(Property property);

	Property get(Integer propertyId);
	
	List<Property> findAll(PropertySearchCriteria criteria);

	List<Property> findAllWithOrder(String col);

	List<Property> findAll();

	List<CompanyPropertyDTO> findByPhoneBook(Integer phoneBookId);

	List<CompanyPropertyDTO> findByCompany(Integer companyId);

	long findAllCount(PropertySearchCriteria criteria);	
	
	List<PropertyType> listPropertyTypes();
	
	List<FlagSize> listFlagSizes();

	List<Property> findAllWithType(int propertyTypeId);
}
