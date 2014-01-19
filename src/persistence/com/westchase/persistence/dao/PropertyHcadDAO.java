package com.westchase.persistence.dao;

import java.util.List;

import com.westchase.persistence.model.PropertyHcad;

public class PropertyHcadDAO extends BaseDAO<PropertyHcad> {

	public List<PropertyHcad> findByProperty(Integer propertyId) {
		List<PropertyHcad> propertyHcads = null;
		String query = "select p from PropertyHcad p where p.property.id = :propertyId";
		try {
			propertyHcads = getSession().createQuery(query).setParameter("propertyId", propertyId).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return propertyHcads;
	}

	public PropertyHcad findByHcad(String hcad) {
		PropertyHcad propertyHcad = null;
		String query = "select p from PropertyHcad p where p.hcad = :hcad";
		try {
			propertyHcad = (PropertyHcad) getSession().createQuery(query).setParameter("hcad", hcad).uniqueResult();
		} catch (Exception e) {
			log.error("", e);
		}
		return propertyHcad;
	}

	public Integer getPropertyId(Integer propertyHcadId) {
		Integer propertyId = null;
		String query = "select p.property.id from PropertyHcad p where p.id = :propertyHcadId";
		try {
			propertyId = (Integer) getSession().createQuery(query).setParameter("propertyHcadId", propertyHcadId).uniqueResult();
		} catch (Exception e) {
			log.error("", e);
		}
		return propertyId;
	}

}
