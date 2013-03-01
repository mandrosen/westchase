package com.westchase.web.action.cmu.report;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.westchase.ejb.CmuReportService;
import com.westchase.persistence.model.CmuLease;
import com.westchase.persistence.model.CmuQuarter;
import com.westchase.persistence.model.CmuTransactionType;
import com.westchase.persistence.model.Property;

/**
 * @author marc
 *
 */
public class CmuLeaseReportAction extends AbstractCmuReportAction {

	private List<CmuLease> results;
	
	@Override
	public String execute() {
		results = new ArrayList<CmuLease>();
		if (getQuarterId() > 0) {
			try {
				InitialContext ctx = new InitialContext();
				reportServ = (CmuReportService) ctx.lookup("westchase/CmuReportServiceBean/local");
			} catch (Exception e) {
				log.error("", e);
			}
			if (reportServ != null) {
				results = reportServ.runCmuLeaseReport(getQuarterId());
				if (EXCEL.equals(type)) {
					return exportToExcel();
				}
				if (EMAIL.equals(type)) {
					return sendEmail();
				}
			}
		}
		return SUCCESS;	
	}
	
	@Override
	protected ByteArrayOutputStream createWorkbook() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
		try {
//			WritableWorkbook wb = Workbook.createWorkbook(bos);
//			WritableSheet sheet = wb.createSheet("cmu report", 0);
			Workbook wb = new XSSFWorkbook();
//		    FileOutputStream fileOut = new FileOutputStream("workbook.xlsx");
//		    wb.write(bos);
//		    fileOut.close();
		    Sheet sheet = wb.createSheet("cmu report");
			
			CmuQuarter quarter = null;
			if (results != null && !results.isEmpty()) {
				quarter = results.get(0).getCmuQuarter();
			}
			String title = "Westchase District Leasing Report Action";
			if (quarter != null) {
				title += ": Quarter #" + quarter.getQuarterNum() + " " + quarter.getYear();
			}
			writeTitle(wb, sheet, title);
			

            String[] headers = { "Type", "Building", "Tenant Name", "Sq. Ft.", "Type", "Tenant's Rep" }; 
            // Write the Header to the excel file
            writeHeaders(wb, sheet, headers);

//    		WritableCellFormat cellFormat = new WritableCellFormat();
//    		cellFormat.setBackground(Colour.WHITE);
//    		cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
//    		WritableFont font = new WritableFont(WritableFont.ARIAL, 11);
//    		cellFormat.setFont(font);
//    		cellFormat.setWrap(true);
    		CellStyle style = wb.createCellStyle();
    		style.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
    		Font font = wb.createFont();
    		font.setFontName(FONT_NAME);
    		font.setFontHeightInPoints(FONT_HEIGHT);
    		style.setFont(font);
    		style.setWrapText(true);
    		setBorders(style);
            
			if (results != null && !results.isEmpty()) {
				int rowNum = FIRST_DATA_ROW;
				for (CmuLease result : results) {
					Property property = result.getProperty();
					if (property != null && property.getId() != null && property.getId().intValue() > 0) {

						Row row = sheet.createRow(rowNum);
						int col = 0;
						String type = getType(property.getBusinessType());
						
						writeCell(wb, sheet, row, col++, type, style);

						writeCell(wb, sheet, row, col++, property.getBuildingName(), style);
						
						writeCell(wb, sheet, row, col++, result.getTenantName(), style);
						
//						String sqFtStr = "0";
						Double sqFt = result.getSqFt();
						if (sqFt != null) {
//							sqFtStr = sqFt.toString();
							writeCell(wb, sheet, row, col++, sqFt, style);
						} else {
							writeCell(wb, sheet, row, col++, "", style);
						}
//						writeCell(wb, sheet, row, col++, sqFtStr, style);
						//writeCell(wb, sheet, row, col++, sqFt, style);
						
						String transType = getTransType(result.getCmuTransactionType());
						writeCell(wb, sheet, row, col++, transType, style);

						writeCell(wb, sheet, row, col++, result.getTenantsRep(), style);
						rowNum++;
					}
				}
			}
			
			fixColumns(sheet, headers.length);
		
//          wb.write();
//          wb.close();
			wb.write(bos);
			bos.close();
            
		} catch (Exception e) {
			log.error("", e);
		}
    	
		return bos;
	}
	
	private String getTransType(CmuTransactionType cmuTransactionType) {
		String transType = "";
		if (cmuTransactionType != null) {
			String desc = cmuTransactionType.getDescription();
			if (StringUtils.isNotBlank(desc)) {
				transType = desc.substring(0, 1);
			}
		}
		return transType;
	}

	private String getType(String businessType) {
		String type = "U"; // unknown
		if ("Office".equals(businessType)) {
			type = "OF";
		} else if ("Service Center".equals(businessType)) {
			type = "SC";
		} else if ("Retail Center".equals(businessType)) {
			type = "R";
		}
		return type;
	}

	public List<CmuLease> getResults() {
		return results;
	}

	public void setResults(List<CmuLease> results) {
		this.results = results;
	}

	@Override
	protected String getReportFileName() {
		return "Leases_" + getReportDate();
	}

}
