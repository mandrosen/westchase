package com.westchase.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.hibernate.criterion.Order;
import org.jboss.ejb3.annotation.SecurityDomain;

import com.westchase.persistence.dao.CmuApartmentDAO;
import com.westchase.persistence.dao.CmuDevsiteDAO;
import com.westchase.persistence.dao.CmuHotelDAO;
import com.westchase.persistence.dao.CmuLeaseDAO;
import com.westchase.persistence.dao.CmuOfficeRetailSvcDAO;
import com.westchase.persistence.dao.CmuQuarterDAO;
import com.westchase.persistence.dao.PhoneBookDAO;
import com.westchase.persistence.dto.cmu.LeasingAgentDTO;
import com.westchase.persistence.dto.cmu.report.LeaseStatsDTO;
import com.westchase.persistence.model.CmuApartment;
import com.westchase.persistence.model.CmuDevsite;
import com.westchase.persistence.model.CmuHotel;
import com.westchase.persistence.model.CmuLease;
import com.westchase.persistence.model.CmuOfficeRetailSvc;
import com.westchase.persistence.model.CmuQuarter;

/**
 * @author marc
 *
 */
@Stateless
@SecurityDomain("WestchaseRealm")
@Local(CmuReportService.class)
public class CmuReportServiceBean implements CmuReportService {

	@Override
	public List<CmuQuarter> listCmuQuarters() {
		CmuQuarterDAO dao = new CmuQuarterDAO();
		return dao.findAll(Order.asc("id"));
	}

	@Override
	public List<CmuApartment> runCmuApartmentReport(int quarterId) {
		CmuApartmentDAO dao = new CmuApartmentDAO();
		return dao.listAll(quarterId);
	}

	@Override
	public List<CmuOfficeRetailSvc> runCmuOfficeRetailSvcReport(int quarterId, int businessTypeId) {
		CmuOfficeRetailSvcDAO dao = new CmuOfficeRetailSvcDAO();
		return dao.listAll(quarterId, businessTypeId);
	}

	@Override
	public List<CmuHotel> runCmuHotelReport(int quarterId) {
		CmuHotelDAO dao = new CmuHotelDAO();
		return dao.listAll(quarterId);
	}

	@Override
	public List<CmuDevsite> runCmuDevsiteReport(int quarterId) {
		CmuDevsiteDAO dao = new CmuDevsiteDAO();
		return dao.listAll(quarterId);
	}

	@Override
	public List<CmuLease> runCmuLeaseReport(int quarterId) {
		CmuLeaseDAO dao = new CmuLeaseDAO();
		return dao.listAll(quarterId);
	}

	@Override
	public LeaseStatsDTO runCmuLeaseStatsReport(int quarterId) {
		CmuLeaseDAO dao = new CmuLeaseDAO();
		return dao.getStats(quarterId);
	}

	@Override
	public LeasingAgentDTO getLeasingAgent(Integer propertyId) {
		PhoneBookDAO dao = new PhoneBookDAO();
		return dao.findPhoneBookByPropertyAndCategory(propertyId, "LA");
	}
}
