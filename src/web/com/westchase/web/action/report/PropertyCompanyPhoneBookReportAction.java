package com.westchase.web.action.report;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.westchase.ejb.ReportService;
import com.westchase.persistence.dto.report.PropertyCompanyPhoneBookDTO;
import com.westchase.utils.ejb.ServiceLocator;

public class PropertyCompanyPhoneBookReportAction extends AbstractReportAction {
	
	private Integer propertyId;
	
	private List<PropertyCompanyPhoneBookDTO> results;

	public PropertyCompanyPhoneBookReportAction() {
		super();
	}

	public String input() {
		return SUCCESS;
	}
	
	@Override
	public String execute() {
		results = new ArrayList<PropertyCompanyPhoneBookDTO>();
		if (propertyId != null && propertyId.intValue() > 0) {
			ReportService reportServ = ServiceLocator.lookupReportService();
			if (reportServ != null) {
				results = reportServ.runPropertyCompanyPhoneBookReport(propertyId);
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
			Workbook wb = new XSSFWorkbook();
		    Sheet sheet = wb.createSheet("report");
			
			writeTitle(wb, sheet, "Property Company PhoneBook Report");
			
            String[] headers = { "Mapno" , "Company", "First Name", "Last Name", "Address", "Room No", "City", "State", "Zip", "Phone", "Email", "Cats" }; 

            writeHeaders(wb, sheet, headers);


    		CellStyle style = wb.createCellStyle();
    		style.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
    		Font font = wb.createFont();
    		font.setFontName(FONT_NAME);
    		font.setFontHeightInPoints(FONT_HEIGHT);
    		style.setFont(font);
            
            
			if (results != null && !results.isEmpty()) {
				int rowNum = FIRST_DATA_ROW;
				
				Integer lastPropertyId = null;
				
				for (PropertyCompanyPhoneBookDTO result : results) {
					int col = 0;
					Row row = sheet.createRow(rowNum);
					
					Integer propertyId = result.getPropertyId();
					if (propertyId == null) {
						continue;
					}
					if (lastPropertyId == null || lastPropertyId.intValue() != propertyId.intValue()) {
						lastPropertyId = propertyId;
						writeCell(wb, sheet, row, col++, propertyId, style);						
					} else {
						writeCell(wb, sheet, row, col++, "", style);
					}

					writeCell(wb, sheet, row, col++, result.getCompany(), style);
					writeCell(wb, sheet, row, col++, result.getFirstName(), style);
					writeCell(wb, sheet, row, col++, result.getLastName(), style);
					writeCell(wb, sheet, row, col++, result.getAddress(), style);
					writeCell(wb, sheet, row, col++, result.getRoomNo(), style);
					writeCell(wb, sheet, row, col++, result.getCity(), style);
					writeCell(wb, sheet, row, col++, result.getState(), style);
					writeCell(wb, sheet, row, col++, result.getZipCode(), style);
					writeCell(wb, sheet, row, col++, result.getWkPhone(), style);
					writeCell(wb, sheet, row, col++, result.getEmail(), style);
					writeCell(wb, sheet, row, col++, result.getCats(), style);

					rowNum++;
				}
			}
			
			fixColumns(sheet, headers.length);
		
			wb.write(bos);
			bos.close();
            
		} catch (Exception e) {
			log.error("", e);
		}
    	
    	
		return bos;
	}

	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public List<PropertyCompanyPhoneBookDTO> getResults() {
		return results;
	}

	public void setResults(List<PropertyCompanyPhoneBookDTO> results) {
		this.results = results;
	}

}
