package com.westchase.web.action.report;

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

import com.westchase.ejb.ReportService;
import com.westchase.persistence.dto.report.PhoneBookPropertyDTO;
import com.westchase.persistence.model.PhoneBook;
import com.westchase.persistence.model.Property;

/**
 * @author marc
 *
 */
public class OfficeBuildingPropertyReportAction extends AbstractReportAction {

	
    private ReportService reportServ;
    
	private int squareFootage;
	private double occupancy;
	
	private List<PhoneBookPropertyDTO> results;

	public String input() {
		return SUCCESS;
	}
	
	@Override
	public String execute() {
		results = new ArrayList<PhoneBookPropertyDTO>();
		try {
			InitialContext ctx = new InitialContext();
			reportServ = (ReportService) ctx.lookup("westchase/ReportServiceBean/local");
		} catch (Exception e) {
			log.error("", e);
		}
		if (reportServ != null) {
			results = reportServ.runOfficeBuildingPropertyReport(squareFootage, occupancy, isWestchaseOnly());
			if (EXCEL.equals(type)) {
				return exportToExcel();
			}
			if (EMAIL.equals(type)) {
				return sendEmail();
			}
		}     
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
			
			writeTitle(wb, sheet, "Office Building Property Report");

            String[] headers = { "Mapno", "Property", "First Name", "Last Name", "Email", "Phone", "Address", "Occupancy", "Square Foot" }; 
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
				for (PhoneBookPropertyDTO result : results) {
					Property prop = result.getProperty();
					PhoneBook pb = result.getPhoneBook();
					if (prop != null && prop.getId() != null && pb != null) {
						Row row = sheet.createRow(rowNum);
						writeCell(wb, sheet, row, 0, prop.getId().toString(), style);
						writeCell(wb, sheet, row, 1, prop.getBuildingName(), style);
						writeCell(wb, sheet, row, 2, pb.getFirstName(), style);
						writeCell(wb, sheet, row, 3, pb.getLastName(), style);
						writeCell(wb, sheet, row, 4, pb.getEmail(), style);
						String phone = pb.getWkPhone();
						String ext = pb.getWkext();
						if (StringUtils.isNotBlank(ext)) {
							phone += " x" + ext;
						}
						writeCell(wb, sheet, row, 5, phone, style);
						String address = prop.getGeoNumber() + " " + prop.getGeoAddress() + ", " + prop.getGeoZipCode();
						writeCell(wb, sheet, row, 6, address, style);
						
						Double rate = prop.getOccupancyRate();
						String rateStr = "";
						if (rate != null) {
							rateStr = rate.toString();
						}
						writeCell(wb, sheet, row, 7, rateStr, style);
						
						Integer sqft = prop.getBuildingSize();
						String sqftStr = "";
						if (sqft != null) {
							sqftStr = sqft.toString();
						}
						writeCell(wb, sheet, row, 8, sqftStr, style);
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

	public int getSquareFootage() {
		return squareFootage;
	}

	public void setSquareFootage(int squareFootage) {
		this.squareFootage = squareFootage;
	}

	public double getOccupancy() {
		return occupancy;
	}

	public void setOccupancy(double occupancy) {
		this.occupancy = occupancy;
	}

	public List<PhoneBookPropertyDTO> getResults() {
		return results;
	}

	public void setResults(List<PhoneBookPropertyDTO> results) {
		this.results = results;
	}

}
