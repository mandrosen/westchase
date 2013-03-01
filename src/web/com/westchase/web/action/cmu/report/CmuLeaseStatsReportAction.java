package com.westchase.web.action.cmu.report;

import java.io.ByteArrayOutputStream;

import javax.naming.InitialContext;

import com.westchase.ejb.CmuReportService;
import com.westchase.persistence.dto.cmu.report.LeaseStatsDTO;

/**
 * @author marc
 *
 */
public class CmuLeaseStatsReportAction extends AbstractCmuReportAction {

	private LeaseStatsDTO leaseStats;
	
	@Override
	public String execute() {
		if (getQuarterId() > 0) {
			try {
				InitialContext ctx = new InitialContext();
				reportServ = (CmuReportService) ctx.lookup("westchase/CmuReportServiceBean/local");
			} catch (Exception e) {
				log.error("", e);
			}
			if (reportServ != null) {
				leaseStats = reportServ.runCmuLeaseStatsReport(getQuarterId());
//				if (EXCEL.equals(type)) {
//					return exportToExcel();
//				}
//				if (EMAIL.equals(type)) {
//					return sendEmail();
//				}
			}
		}
		return SUCCESS;	
	}
	
	@Override
	protected ByteArrayOutputStream createWorkbook() {
		
//		fixColumns(sheet, headers.length);
	
		return null;
	}

	public LeaseStatsDTO getLeaseStats() {
		return leaseStats;
	}

	public void setLeaseStats(LeaseStatsDTO leaseStats) {
		this.leaseStats = leaseStats;
	}

	@Override
	protected String getReportFileName() {
		return "LeaseStats_" + getReportDate();
	}

}
