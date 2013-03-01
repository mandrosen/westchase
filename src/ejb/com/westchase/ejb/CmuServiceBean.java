package com.westchase.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.hibernate.criterion.Order;

import com.westchase.persistence.dao.CmuApartmentDAO;
import com.westchase.persistence.dao.CmuDevsiteDAO;
import com.westchase.persistence.dao.CmuHotelDAO;
import com.westchase.persistence.dao.CmuLeaseDAO;
import com.westchase.persistence.dao.CmuOfficeRetailSvcDAO;
import com.westchase.persistence.dao.CmuTransactionTypeDAO;
import com.westchase.persistence.dto.cmu.CmuOfficeRetailSvcDTO;
import com.westchase.persistence.model.CmuApartment;
import com.westchase.persistence.model.CmuDevsite;
import com.westchase.persistence.model.CmuHotel;
import com.westchase.persistence.model.CmuLease;
import com.westchase.persistence.model.CmuOfficeRetailSvc;
import com.westchase.persistence.model.CmuTransactionType;

/**
 * @author marc
 *
 */
@Stateless
//@SecurityDomain("WestchaseRealm")
@Remote(CmuService.class)
public class CmuServiceBean implements CmuService {

	@Override
	public long saveCmuApartment(CmuApartment cmuApt) {
		CmuApartmentDAO dao = new CmuApartmentDAO();
		dao.saveOrUpdate(cmuApt);
		return cmuApt.getId().longValue();
	}

	@Override
	public long saveCmuDevsite(CmuDevsite cmuDevsite) {
		CmuDevsiteDAO dao = new CmuDevsiteDAO();
		dao.saveOrUpdate(cmuDevsite);
		return cmuDevsite.getId().longValue();
	}

	@Override
	public long saveCmuHotel(CmuHotel cmuHotel) {
		CmuHotelDAO dao = new CmuHotelDAO();
		dao.saveOrUpdate(cmuHotel);
		return cmuHotel.getId().longValue();
	}

	@Override
	public long saveCmuLease(CmuLease cmuLease) {
		CmuLeaseDAO dao = new CmuLeaseDAO();
		dao.saveOrUpdate(cmuLease);
		return cmuLease.getId().longValue();
	}

	@Override
	public long saveCmuOfficeRetailSvc(CmuOfficeRetailSvc cmuOrs) {
		CmuOfficeRetailSvcDAO dao = new CmuOfficeRetailSvcDAO();
		dao.saveOrUpdate(cmuOrs);
		return cmuOrs.getId().longValue();
	}

	@Override
	public CmuApartment getCmuApartment(Integer id) {
		CmuApartmentDAO dao = new CmuApartmentDAO();
		return dao.get(id);
	}

	@Override
	public CmuDevsite getCmuDevsite(Integer id) {
		CmuDevsiteDAO dao = new CmuDevsiteDAO();
		return dao.get(id);
	}

	@Override
	public CmuHotel getCmuHotel(Integer id) {
		CmuHotelDAO dao = new CmuHotelDAO();
		return dao.get(id);
	}

	@Override
	public CmuOfficeRetailSvcDTO getCmuOfficeRetailSvc(Integer id) {
		CmuOfficeRetailSvcDAO dao = new CmuOfficeRetailSvcDAO();
		return dao.getWithLeases(id);
	}

	@Override
	public CmuLease getCmuLease(Integer id) {
		CmuLeaseDAO dao = new CmuLeaseDAO();
		return dao.get(id);
	}

	@Override
	public List<CmuTransactionType> listTransactionTypes() {
		CmuTransactionTypeDAO dao = new CmuTransactionTypeDAO();
		return dao.findAll(Order.asc("description"));
	}

}
