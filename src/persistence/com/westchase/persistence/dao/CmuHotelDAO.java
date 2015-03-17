package com.westchase.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;

import com.westchase.persistence.dto.cmu.report.HotelDTO;
import com.westchase.persistence.dto.cmu.report.OfficeRetailSvcDTO;
import com.westchase.persistence.model.CmuHotel;

/**
 * @author marc
 *
 */
public class CmuHotelDAO extends BaseDAO<CmuHotel> {
	
	/*@SuppressWarnings("unchecked")
	public List<CmuHotel> listAll(int quarterId) {
		List<CmuHotel> hotels = new ArrayList<CmuHotel>();
		String query = "select c from CmuHotel c where c.cmuQuarter.id = :quarter";
		try {
			hotels = getSession().createQuery(query).setParameter("quarter", quarterId).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return hotels;
	}*/
	
	@SuppressWarnings("unchecked")
	public List<HotelDTO> listAll(int quarterId) {
		List<HotelDTO> hotelList = new ArrayList<HotelDTO>();
		String query = "select new com.westchase.persistence.dto.cmu.report.HotelDTO(c) from CmuHotel c where c.cmuQuarter.id = :quarter";
		try {
			hotelList = getSession().createQuery(query).setParameter("quarter", quarterId).list();
		} catch (Exception e) {
			log.error("", e);
		}
		if (hotelList != null) {
			for (HotelDTO hotel : hotelList) {
				setGeneralManager(hotel);
			}
		}
		return hotelList;
	}

	private void setGeneralManager(HotelDTO hotel) {
		if (hotel != null && hotel.getProperty() != null && hotel.getProperty().getId() != null) {
			String query = "select pb.FirstName, " +
					"pb.LastName, " +
					"pb.WkPhone, " +
					"pb.Wkext, " +
					"pb.Email\r\n" + 
					"from phone_book_category ca\r\n" + 
					"inner join phone_book_relation pbr on ca.phonebookid = pbr.phone_book\r\n" + 
					"inner join phone_book pb on pbr.phone_book = pb.id\r\n" + 
					"inner join company c on pb.companyid = c.id\r\n" + 
					"where ca.categorycode = 'hc'\r\n" + 
					"  and pbr.property = :propertyId";
			try {
				Object[] resultAry = (Object[]) getSession().createSQLQuery(query)
						.addScalar("FirstName", Hibernate.STRING)
						.addScalar("LastName", Hibernate.STRING)
						.addScalar("WkPhone", Hibernate.STRING)
						.addScalar("Wkext", Hibernate.STRING)
						.addScalar("Email", Hibernate.STRING)
						.setParameter("propertyId", hotel.getProperty().getId()).setMaxResults(1).uniqueResult();
				if (resultAry != null) {
					hotel.setGeneralMgr((String) resultAry[0], (String) resultAry[1]);
					hotel.setGeneralMgrEmail((String) resultAry[4]);
					hotel.setGeneralMgrPhone((String) resultAry[2], (String) resultAry[3]);				
				}
			} catch (Exception e) {
				log.error("", e);
			}
		}
	}

	public CmuHotel getByPropertyQuarter(Integer propertyId, Integer quarterId) {
		CmuHotel cmuHotel = null;
		String query = "select c from CmuHotel c where c.property.id = :propertyId and c.cmuQuarter.id = :quarter";
		try {
			cmuHotel = (CmuHotel) getSession().createQuery(query).setParameter("propertyId", propertyId).setParameter("quarter", quarterId).setMaxResults(1).uniqueResult();
		} catch (Exception e) {
			log.error("", e);
		}
		return cmuHotel;
	}
	
}
