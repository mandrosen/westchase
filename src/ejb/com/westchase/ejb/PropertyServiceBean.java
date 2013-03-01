package com.westchase.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.hibernate.criterion.Order;
import org.jboss.ejb3.annotation.SecurityDomain;

import com.westchase.persistence.criteria.PropertySearchCriteria;
import com.westchase.persistence.dao.FlagSizeDAO;
import com.westchase.persistence.dao.PropertyDAO;
import com.westchase.persistence.dao.PropertyTypeDAO;
import com.westchase.persistence.dto.cms.CompanyPropertyDTO;
import com.westchase.persistence.model.FlagSize;
import com.westchase.persistence.model.Property;
import com.westchase.persistence.model.PropertyType;

/**
 * @author marc
 *
 */
@Stateless
@SecurityDomain("WestchaseRealm")
@Local(PropertyService.class)
public class PropertyServiceBean implements PropertyService {

	@Override
	public List<Property> findAll(PropertySearchCriteria criteria) {
		PropertyDAO dao = new PropertyDAO();
		return dao.findAll(criteria);
	}
	@Override
	public long findAllCount(PropertySearchCriteria criteria) {
		PropertyDAO dao = new PropertyDAO();
		return dao.findAllCount(criteria);
	}

	@Override
	public Property get(Integer propertyId) {
		PropertyDAO dao = new PropertyDAO();
		return dao.get(propertyId);
	}

	@Override
	public void saveOrUpdate(Property property) {
		if (property != null) {
			if (property.getInputDate() == null) {
				property.setInputDate(new Date());
			}
			// not sure why this is necessary, but...
			if (property.getPropertyType() != null && property.getPropertyType().getId() == null) {
				property.setPropertyType(null);
			}
			if (property.getFlagSize() != null && property.getFlagSize().getId() == null) {
				property.setFlagSize(null);
			}
			PropertyDAO dao = new PropertyDAO();
			dao.saveOrUpdate(property);
		}
	}

	@Override
	public List<Property> findAllWithOrder(String col) {
		PropertyDAO dao = new PropertyDAO();
		return dao.findAll(Order.asc(col));
	}

	@Override
	public List<Property> findAll() {
		PropertyDAO dao = new PropertyDAO();
		return dao.findAll(Order.asc("buildingName"));
	}

	@Override
	public List<CompanyPropertyDTO> findByCompany(Integer companyId) {
		PropertyDAO dao = new PropertyDAO();
		return dao.findByCompany(companyId);
	}

	@Override
	public List<CompanyPropertyDTO> findByPhoneBook(Integer phoneBookId) {
		PropertyDAO dao = new PropertyDAO();
		return dao.findByPhoneBook(phoneBookId);
	}
	
	@Override
	public List<PropertyType> listPropertyTypes() {
		PropertyTypeDAO dao = new PropertyTypeDAO();
		return dao.findAll(Order.asc("name"));
	}
	
	@Override
	public List<FlagSize> listFlagSizes() {
		FlagSizeDAO dao = new FlagSizeDAO();
		return dao.findAll(Order.asc("name"));
	}
	
	@Override
	public List<Property> findAllWithType(int propertyTypeId) {
		PropertyDAO dao = new PropertyDAO();
		if (propertyTypeId <= 0)
			return dao.findAllWithType();
		return dao.findAllWithType(propertyTypeId);
	}

}
