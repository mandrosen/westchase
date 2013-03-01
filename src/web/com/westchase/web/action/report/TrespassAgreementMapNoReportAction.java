package com.westchase.web.action.report;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.westchase.ejb.ReportService;
import com.westchase.persistence.model.Property;

/**
 * @author marc
 *
 */
public class TrespassAgreementMapNoReportAction extends AbstractReportAction implements ServletResponseAware {
	
	private static DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	
    private ReportService repServ;
    private InitialContext ctx = null;  

    private List<Property> results;

	public String input() {
		return SUCCESS;
	}
	
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
			
			writeTitle(wb, sheet, "Trespass Agreement Map Number Report");
			
            String[] headers = { "Map No", "Building Name", "Geo Number", "Geo Address", "Tresspass Date" }; 
            // Write the Header to the excel file
            writeHeaders(wb, sheet, headers);

//    		WritableCellFormat cellFormat = new WritableCellFormat();
//    		cellFormat.setBackground(Colour.WHITE);
//    		WritableFont font = new WritableFont(WritableFont.ARIAL, 11);
//    		cellFormat.setFont(font);
    		CellStyle style = wb.createCellStyle();
    		style.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
    		Font font = wb.createFont();
    		font.setFontName(FONT_NAME);
    		font.setFontHeightInPoints(FONT_HEIGHT);
    		style.setFont(font);
            
            
			if (results != null && !results.isEmpty()) {
				int rowNum = FIRST_DATA_ROW;
				for (Property result : results) {
					Row row = sheet.createRow(rowNum);
					String mapNo = "";
					if (result.getId() != null) {
						mapNo = result.getId().toString();
					}
					writeCell(wb, sheet, row, 0, mapNo, style);
					writeCell(wb, sheet, row, 1, result.getBuildingName(), style);
					writeCell(wb, sheet, row, 2, result.getGeoNumber(), style);
					writeCell(wb, sheet, row, 3, result.getGeoAddress(), style);
					String dateStr = "";
					Date trspassDate = result.getTrspassDate();
					if (trspassDate != null) {
						dateStr = DATE_FORMAT.format(trspassDate);
					}
					writeCell(wb, sheet, row, 4, dateStr, style);
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
    
	@Override
	public String execute() throws Exception {
        try {
            ctx=new InitialContext();
            repServ = (ReportService) ctx.lookup("westchase/ReportServiceBean/local");
        } catch (Exception e) {
            log.error(e.getMessage()); 
        }     		
        if (repServ != null) {
        	this.results = repServ.runTrespassAgreementMapNoReport(isWestchaseOnly());
    		if (EXCEL.equals(type)) {
    			return exportToExcel();
    		}
			if (EMAIL.equals(type)) {
				return sendEmail();
			}
        }
		return SUCCESS;	
	}

	public List<Property> getResults() {
		return results;
	}

	public void setResults(List<Property> results) {
		this.results = results;
	}


	@Override
	protected String getReportFileName() {
		return "trespassagreement";
	}
	
}
