package com.westchase.ejb;

import java.util.List;

import javax.ejb.Local;

import com.westchase.persistence.dto.cmu.LeasingAgentDTO;
import com.westchase.persistence.dto.cmu.report.ApartmentDTO;
import com.westchase.persistence.dto.cmu.report.DevsiteDTO;
import com.westchase.persistence.dto.cmu.report.HotelDTO;
import com.westchase.persistence.dto.cmu.report.LeaseStatsDTO;
import com.westchase.persistence.dto.cmu.report.OfficeRetailSvcDTO;
import com.westchase.persistence.model.CmuLease;
import com.westchase.persistence.model.CmuQuarter;

/**
 * @author marc
 *
 */
@Local
public interface CmuReportService {
	List<CmuQuarter> listCmuQuarters();
	List<ApartmentDTO> runCmuApartmentReport(int quarterId);
	List<HotelDTO> runCmuHotelReport(int quarterId);
	List<DevsiteDTO> runCmuDevsiteReport(int quarterId);
	List<OfficeRetailSvcDTO> runCmuOfficeRetailSvcReport(int quarterId, int businessTypeId);
	List<CmuLease> runCmuLeaseReport(int quarterId);
	LeaseStatsDTO runCmuLeaseStatsReport(int quarterId);
	
	LeasingAgentDTO getLeasingAgent(Integer propertyId);
}
