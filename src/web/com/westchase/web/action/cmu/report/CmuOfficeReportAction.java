package com.westchase.web.action.cmu.report;



/**
 * @author marc
 *
 */
public class CmuOfficeReportAction extends AbstractCmuOfficeRetailSvcReportAction {

	public CmuOfficeReportAction() {
		super(1, "Offices");
	}

//	@Override
//	public String execute() {
//		results = new ArrayList<CmuOfficeRetailSvc>();
//		if (getQuarterId() > 0) {
//			try {
//				InitialContext ctx = new InitialContext();
//				reportServ = (CmuReportService) ctx.lookup("westchase/CmuReportServiceBean/local");
//			} catch (Exception e) {
//				log.error("", e);
//			}
//			if (reportServ != null) {
//				results = reportServ.runCmuOfficeRetailSvcReport(getQuarterId(), getBusinessTypeId());
//				if (EXCEL.equals(type)) {
//					return exportToExcel();
//				}
//				if (EMAIL.equals(type)) {
//					return sendEmail();
//				}
//			}
//		}
//		return SUCCESS;	
//	}
//
//	@Override
//	protected ByteArrayOutputStream createWorkbook() {
//		return super.createWorkbook();
//	}
}
