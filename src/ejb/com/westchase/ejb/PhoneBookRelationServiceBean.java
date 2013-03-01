package com.westchase.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.hibernate.criterion.Order;
import org.jboss.ejb3.annotation.SecurityDomain;

import com.westchase.persistence.dao.PhoneBookRelationDAO;
import com.westchase.persistence.dao.PhoneBookRelationTypeDAO;
import com.westchase.persistence.dao.PropertyDAO;
import com.westchase.persistence.model.PhoneBook;
import com.westchase.persistence.model.PhoneBookRelation;
import com.westchase.persistence.model.PhoneBookRelationType;
import com.westchase.persistence.model.Property;

/**
 * @author marc
 *
 */
@Stateless
@SecurityDomain("WestchaseRealm")
@Local(PhoneBookRelationService.class)
public class PhoneBookRelationServiceBean implements PhoneBookRelationService {

	@Override
	public List<PhoneBookRelation> list(Integer phoneBookId) {
		PhoneBookRelationDAO dao = new PhoneBookRelationDAO();
		return dao.list(phoneBookId);
	}

	@Override
	public void delete(Long relationId) {
		PhoneBookRelationDAO dao = new PhoneBookRelationDAO();
		PhoneBookRelation relation = dao.get(relationId);
		if (relation != null) {
			dao.delete(relation);
		}
	}

	@Override
	public List<PhoneBookRelationType> listRelationTypes() {
		PhoneBookRelationTypeDAO dao = new PhoneBookRelationTypeDAO();
		return dao.findAll(Order.asc("name"));
	}

	@Override
	public void saveNew(Integer phoneBookId, Integer propertyId, int relationTypeId) {
		PhoneBookRelationDAO dao = new PhoneBookRelationDAO();
		dao.save(new PhoneBookRelation(new PhoneBook(phoneBookId), new Property(propertyId), new PhoneBookRelationType(relationTypeId)));
	}

	@Override
	public List<Property> findAvailableProperties(Integer phoneBookId) {
		PropertyDAO dao = new PropertyDAO();
		return dao.findAllNotCurrentyRelated(phoneBookId);
	}

}
