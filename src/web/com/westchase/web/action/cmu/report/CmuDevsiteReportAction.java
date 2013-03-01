package com.westchase.web.action.cmu.report;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.lang.WordUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.westchase.ejb.CmuReportService;
import com.westchase.persistence.model.CmuDevsite;
import com.westchase.persistence.model.CmuQuarter;
import com.westchase.persistence.model.Property;

/**
 * @author marc
 *
 */
public class CmuDevsiteReportAction extends AbstractCmuReportAction {

	private List<CmuDevsite> results;
	
	@Override
	public String execute() {
		results = new ArrayList<CmuDevsite>();
		if (getQuarterId() > 0) {
			try {
				InitialContext ctx = new InitialContext();
				reportServ = (CmuReportService) ctx.lookup("westchase/CmuReportServiceBean/local");
			} catch (Exception e) {
				log.error("", e);
			}
			if (reportServ != null) {
				results = reportServ.runCmuDevsiteReport(getQuarterId());
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
			String title = "Westchase District CMU Devsites Report Action";
			if (quarter != null) {
				title += ": Quarter #" + quarter.getQuarterNum() + " " + quarter.getYear();
			}
			writeTitle(wb, sheet, title);
			

            String[] headers = { "Map #", "Location", "Size (Acres)", "Price ($/sq. ft)", "Frontage", "Contact", "Company", "Phone", "Fax", "Restrictions" }; 
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
				double totalAcres = 0;
				int rowNum = FIRST_DATA_ROW;
				for (CmuDevsite result : results) {
					Property devsite = result.getProperty();
					if (devsite != null && devsite.getId() != null && devsite.getId().intValue() > 0) {
						Row row = sheet.createRow(rowNum);
						int col = 0;
						
						writeCell(wb, sheet, row, col++, String.valueOf(devsite.getId()), style);
					
						String location = devsite.getGeoNumber() + " " + WordUtils.capitalizeFully(devsite.getGeoAddress());
						writeCell(wb, sheet, row, col++,  location, style);
					
//						String siteSizeStr = "Unknown";
						Double siteSize = result.getSiteSize();
						if (siteSize != null) {
							totalAcres += siteSize.doubleValue();
//							siteSizeStr = siteSize.toString();
							writeCell(wb, sheet, row, col++, siteSize, style);
						} else {
							writeCell(wb, sheet, row, col++, "", style);
						}
//						writeCell(wb, sheet, row, col++, siteSizeStr, style);
						//writeCell(wb, sheet, row, col++, siteSize, style);
						
						writeCell(wb, sheet, row, col++, result.getPriceSqFt(), style);
						
						writeCell(wb, sheet, row, col++, result.getFrontage(), style);
						
						writeCell(wb, sheet, row, col++, result.getContact(), style);
						writeCell(wb, sheet, row, col++, result.getCompany(), style);
						writeCell(wb, sheet, row, col++, result.getPhone(), style);
						writeCell(wb, sheet, row, col++, result.getFax(), style);
						writeCell(wb, sheet, row, col++, result.getRestrictions(), style);
						
						rowNum++;
					}
				}
				if (totalAcres > 0) {
//		    		WritableCellFormat boldFormat = new WritableCellFormat();
//		    		boldFormat.setBackground(Colour.WHITE);
//		    		WritableFont boldFont = new WritableFont(WritableFont.ARIAL, 11);
//		    		boldFont.setBoldStyle(WritableFont.BOLD);
//		    		boldFormat.setFont(boldFont);
		    		CellStyle boldStyle = wb.createCellStyle();
		    		style.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
		    		Font boldFont = wb.createFont();
		    		boldFont.setFontName(FONT_NAME);
		    		boldFont.setFontHeightInPoints(FONT_HEIGHT);
		    		boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		    		boldStyle.setFont(boldFont);

		    		Row row = sheet.createRow(rowNum);
		    		writeCell(wb, sheet, row, 1, "TOTAL LAND AVAILABLE", boldStyle);
		    		writeCell(wb, sheet, row, 2, String.valueOf(totalAcres), boldStyle);
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

	public List<CmuDevsite> getResults() {
		return results;
	}

	public void setResults(List<CmuDevsite> results) {
		this.results = results;
	}

	@Override
	protected String getReportFileName() {
		return "Devsites_" + getReportDate();
	}
	
}
