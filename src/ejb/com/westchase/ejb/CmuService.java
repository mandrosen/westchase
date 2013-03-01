package com.westchase.ejb;

import java.util.List;

import javax.ejb.Remote;

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
@Remote
public interface CmuService {

	long saveCmuApartment(CmuApartment cmuApt);
	long saveCmuDevsite(CmuDevsite cmuDevsite);
	long saveCmuHotel(CmuHotel cmuHotel);
	long saveCmuLease(CmuLease cmuLease);
	long saveCmuOfficeRetailSvc(CmuOfficeRetailSvc cmuOrs);
	
	CmuApartment getCmuApartment(Integer id);
	CmuDevsite getCmuDevsite(Integer id);
	CmuHotel getCmuHotel(Integer id);
	
	CmuOfficeRetailSvcDTO getCmuOfficeRetailSvc(Integer id);
	CmuLease getCmuLease(Integer id);
	List<CmuTransactionType> listTransactionTypes();
	
}
