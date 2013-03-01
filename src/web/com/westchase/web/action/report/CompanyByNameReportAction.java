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
import com.westchase.persistence.dto.report.PhoneBookCompanyDTO;

/**
 * @author marc
 *
 */
public class CompanyByNameReportAction extends AbstractReportAction {
	

    private ReportService reportServ;
    private InitialContext ctx = null;  
    
	private String companyName;
	
	private List<PhoneBookCompanyDTO> results;

	public String input() {
		return SUCCESS;
	}
	
	@Override
	public String execute() {
		results = new ArrayList<PhoneBookCompanyDTO>();
		if (StringUtils.isNotBlank(companyName)) {
			try {
				ctx = new InitialContext();
				reportServ = (ReportService) ctx.lookup("westchase/ReportServiceBean/local");
			} catch (Exception e) {
				log.error("", e);
			}
		}
		if (reportServ != null) {
			results = reportServ.runCompanyByNameReport(companyName, isWestchaseOnly());
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

			
			writeTitle(wb, sheet, "Company by Name Report");

            String[] headers = { "Title", "First Name", "Last Name", "Job Title", "Company", "St Number", "St Address",
					"Room No", "City", "State", "ZipCode", "Email", "Wk Phone", "FaxPhone", "Category" }; 
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
				int col = 0;
				for (PhoneBookCompanyDTO result : results) {
					Row row = sheet.createRow(rowNum);
					writeCell(wb, sheet, row, col++, result.getPhoneBook().getTitle(), style);
					writeCell(wb, sheet, row, col++, result.getPhoneBook().getFirstName(), style);
					writeCell(wb, sheet, row, col++, result.getPhoneBook().getLastName(), style);
					writeCell(wb, sheet, row, col++, result.getPhoneBook().getJobTitle(), style);
					writeCell(wb, sheet, row, col++, result.getCompany().getCompany(), style);
					writeCell(wb, sheet, row, col++, result.getCompany().getStNumber(), style);
					writeCell(wb, sheet, row, col++, result.getCompany().getStAddress(), style);
					writeCell(wb, sheet, row, col++, result.getCompany().getRoomNo(), style);
					writeCell(wb, sheet, row, col++, result.getCompany().getCity(), style);
					writeCell(wb, sheet, row, col++, result.getCompany().getState(), style);
					writeCell(wb, sheet, row, col++, result.getCompany().getZipCode(), style);
					writeCell(wb, sheet, row, col++, result.getPhoneBook().getEmail(), style);
					writeCell(wb, sheet, row, col++, result.getPhoneBook().getWkPhone(), style);
					writeCell(wb, sheet, row, col++, result.getPhoneBook().getFaxPhone(), style);
					
					String categories = result.getCategories();
					if (StringUtils.isNotBlank(categories)) {
						String[] categoriesArray = categories.split(",");
						if (categoriesArray != null && categoriesArray.length > 0) {
							for (String category : categoriesArray) {
								writeCell(wb, sheet, row, col++, category, style);
							}
						}
						
					}
					
					rowNum++;
					col = 0;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public List<PhoneBookCompanyDTO> getResults() {
		return results;
	}

	public void setResults(List<PhoneBookCompanyDTO> results) {
		this.results = results;
	}

	@Override
	protected String getReportFileName() {
		return "companybyname_" + getCompanyName() + "_" + getReportDate();
	}
}
