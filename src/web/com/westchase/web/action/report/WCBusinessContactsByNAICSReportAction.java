package com.westchase.web.action.report;

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

import com.westchase.ejb.ReportService;
import com.westchase.persistence.dto.report.ContactDTO;
import com.westchase.persistence.model.Naics;
import com.westchase.utils.ListUtils;

/**
 * @author marc
 *
 */
public class WCBusinessContactsByNAICSReportAction extends AbstractReportAction {
	
    private ReportService reportServ;
    private InitialContext ctx = null;  
    
	private List<String> naicsCodes;
	private List<ContactDTO> results;

	private List<Naics> availableNaics;

	@Override
	protected ByteArrayOutputStream createWorkbook() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
		try {
//			WritableWorkbook wb = Workbook.createWorkbook(bos);
//			WritableSheet sheet = wb.createSheet("report", 0);
			Workbook wb = new XSSFWorkbook();
//		    FileOutputStream fileOut = new FileOutputStream("workbook.xlsx");
//		    wb.write(bos);
//		    fileOut.close();
		    Sheet sheet = wb.createSheet("report");
			
			writeTitle(wb, sheet, "WC Business Contacts By NAICS Report");
			
	        String[] headers = { "Last Name", "First Name", "Title", "Job Title", "Company", "St Number", 
	        		"St Address", "Room No", "Zip Code", "Email", "Wk Phone", "Category Code", 
	        		"Classification", "NAICS" }; 
	        // Write the Header to the excel file
	        writeHeaders(wb, sheet, headers);
	
//			WritableCellFormat cellFormat = new WritableCellFormat();
//			cellFormat.setBackground(Colour.WHITE);
//			WritableFont font = new WritableFont(WritableFont.ARIAL, 11);
//			cellFormat.setFont(font);
    		CellStyle style = wb.createCellStyle();
    		style.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
    		Font font = wb.createFont();
    		font.setFontName(FONT_NAME);
    		font.setFontHeightInPoints(FONT_HEIGHT);
    		style.setFont(font);
	        
	        
			if (results != null && !results.isEmpty()) {
				int rowNum = FIRST_DATA_ROW;
				for (ContactDTO result : results) {
					Row row = sheet.createRow(rowNum);
					writeCell(wb, sheet, row, 0, result.getLastName(), style);
					writeCell(wb, sheet, row, 1, result.getFirstName(), style);
					writeCell(wb, sheet, row, 2, result.getTitle(), style);
					writeCell(wb, sheet, row, 3, result.getJobTitle(), style);
					writeCell(wb, sheet, row, 4, result.getCompany(), style);
					writeCell(wb, sheet, row, 5, result.getStNumber(), style);
					String address = result.getStAddress();
					if (isFixAddressCaps()) {
						address = WordUtils.capitalizeFully(address);
					}
					writeCell(wb, sheet, row, 6, address, style);
					writeCell(wb, sheet, row, 7, result.getRoomNo(), style);
					writeCell(wb, sheet, row, 8, result.getZipCode(), style);
					writeCell(wb, sheet, row, 9, result.getEmail(), style);
					writeCell(wb, sheet, row, 10, result.getWkPhone(), style);
					writeCell(wb, sheet, row, 11, result.getCategoryCode(), style);
					writeCell(wb, sheet, row, 12, result.getClassification(), style);
					writeCell(wb, sheet, row, 13, result.getNaics(), style);
					rowNum++;
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


	public List<ContactDTO> getResults() {
		return results;
	}

	public void setResults(List<ContactDTO> results) {
		this.results = results;
	}

	public String input() {
		try {
			ctx = new InitialContext();
			reportServ = (ReportService) ctx.lookup("westchase/ReportServiceBean/local");
		} catch (Exception e) {
			log.error("", e);
		}

		if (reportServ != null) {
			availableNaics = reportServ.getNaicss();
	        if ("excel".equals(type)) {
	        	return exportToExcel();
	        }
		}
		return SUCCESS;
	}
	
	@Override
	public String execute() {
		try {
			ctx = new InitialContext();
			reportServ = (ReportService) ctx.lookup("westchase/ReportServiceBean/local");
		} catch (Exception e) {
			log.error("", e);
		}

		if (reportServ != null) {
			availableNaics = reportServ.getNaicss();
			List<String> codes = new ArrayList<String>();
			if (naicsCodes != null && !naicsCodes.isEmpty()) {
				for (String naicsCode : naicsCodes) {
					codes.add(new String(naicsCode.toString()));
				}
			}
			results = reportServ.runWCBusinessContactsByNAICSReport(codes, isWestchaseOnly());
			if (results != null && !results.isEmpty() && isFixAddressCaps()) {
				for (ContactDTO result : results) {
					result.setStAddress(WordUtils.capitalizeFully(result.getStAddress()));
				}
			}			
			if (EXCEL.equals(type)) {
				return exportToExcel();
			}
			if (EMAIL.equals(type)) {
				return sendEmail();
			}
		}

		return SUCCESS;	
	}

	public List<String> getNaicsCodes() {
		return naicsCodes;
	}

	public void setNaicsCodes(List<String> naicsCodes) {
		this.naicsCodes = naicsCodes;
	}

	public List<Naics> getAvailableNaics() {
		return availableNaics;
	}

	public void setAvailableNaics(List<Naics> availableNaics) {
		this.availableNaics = availableNaics;
	}

	@Override
	protected String getReportFileName() {
		return "wccontactsbynaics" + ListUtils.toString(getNaicsCodes());
	}
}
