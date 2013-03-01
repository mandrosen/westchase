package com.westchase.web.action.report;

import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.westchase.ejb.ReportService;
import com.westchase.persistence.dto.report.ContactDTO;

/**
 * @author marc
 *
 */
public class FlagPoleContactsReportAction extends AbstractReportAction {
	
    private ReportService reportServ;
    private InitialContext ctx = null;  
    
    private List<ContactDTO> results;

	public String input() {
		return SUCCESS;
	}
    
	@Override
	protected ByteArrayOutputStream createWorkbook() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
		try {
//			WritableWorkbook wb = Workbook.createWorkbook(bos);
//			WritableSheet sheet = wb.createSheet("report", 0);
			org.apache.poi.ss.usermodel.Workbook wb = new XSSFWorkbook();
//		    FileOutputStream fileOut = new FileOutputStream("workbook.xlsx");
//		    wb.write(bos);
//		    fileOut.close();
		    Sheet sheet = wb.createSheet("report");
			
			writeTitle(wb, sheet, "Flag Pole Properties Report");

            String[] headers = { "Map No", "Building Name", "Geo Number", "Geo Address", "Contact" }; 
            // Write the Header to the excel file
            writeHeaders(wb, sheet, headers);

//			WritableCellFormat cellFormat = new WritableCellFormat();
//			cellFormat.setBackground(Colour.WHITE);
//			WritableFont font = new WritableFont(WritableFont.ARIAL);
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
					writeCell(wb, sheet, row, 0, result.getMapNo(), style);
					writeCell(wb, sheet, row, 1, result.getBuildingName(), style);
					writeCell(wb, sheet, row, 2, result.getGeoNumber(), style);
					writeCell(wb, sheet, row, 3, result.getGeoAddress(), style);
					String contact = "";
					if (StringUtils.isNotBlank(result.getFirstName())) {
						contact = result.getFirstName() + " " + result.getLastName() + " - " + result.getWkPhone();
					}
					writeCell(wb, sheet, row, 4, contact, style);
					rowNum++;
				}
			}
			
			fixColumns(sheet, headers.length);
		
//            wb.write();
//            wb.close();
			wb.write(bos);
			bos.close();
             
		} catch (Exception e) {
			log.error("", e);
		}
    	
    	
		return bos;
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
			results = reportServ.runFlagPoleContactsReport(isWestchaseOnly());
			if (EXCEL.equals(type)) {
				return exportToExcel();
			}
			if (EMAIL.equals(type)) {
				return sendEmail();
			}
		}

		return SUCCESS;	
	}

	public List<ContactDTO> getResults() {
		return results;
	}

	public void setResults(List<ContactDTO> results) {
		this.results = results;
	}

	@Override
	protected String getReportFileName() {
		return "flagpolecontacts";
	}
}
