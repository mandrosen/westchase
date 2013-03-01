package com.westchase.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import com.westchase.persistence.model.CmuHotel;

/**
 * @author marc
 *
 */
public class CmuHotelDAO extends BaseDAO<CmuHotel> {
	
	@SuppressWarnings("unchecked")
	public List<CmuHotel> listAll(int quarterId) {
		List<CmuHotel> hotels = new ArrayList<CmuHotel>();
		String query = "select c from CmuHotel c where c.cmuQuarter.id = :quarter";
		try {
			hotels = getSession().createQuery(query).setParameter("quarter", quarterId).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return hotels;
	}
	
}
