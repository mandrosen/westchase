package com.westchase.ejb;

import java.util.List;

import javax.ejb.Local;

import com.westchase.persistence.model.PhoneBookRelation;
import com.westchase.persistence.model.PhoneBookRelationType;
import com.westchase.persistence.model.Property;

/**
 * @author marc
 *
 */
@Local
public interface PhoneBookRelationService {

	List<PhoneBookRelation> list(Integer phoneBookId);

	void delete(Long relationId);

	List<PhoneBookRelationType> listRelationTypes();

	void saveNew(Integer phoneBookId, Integer propertyId, int relationTypeId);

	List<Property> findAvailableProperties(Integer phoneBookId);

}
