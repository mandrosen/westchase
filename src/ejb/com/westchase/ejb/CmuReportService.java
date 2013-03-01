package com.westchase.ejb;

import java.util.List;

import javax.ejb.Local;

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
@Local
public interface CmuReportService {
	List<CmuQuarter> listCmuQuarters();
	List<CmuApartment> runCmuApartmentReport(int quarterId);
	List<CmuHotel> runCmuHotelReport(int quarterId);
	List<CmuDevsite> runCmuDevsiteReport(int quarterId);
	List<CmuOfficeRetailSvc> runCmuOfficeRetailSvcReport(int quarterId, int businessTypeId);
	List<CmuLease> runCmuLeaseReport(int quarterId);
	LeaseStatsDTO runCmuLeaseStatsReport(int quarterId);
	
	LeasingAgentDTO getLeasingAgent(Integer propertyId);
}
