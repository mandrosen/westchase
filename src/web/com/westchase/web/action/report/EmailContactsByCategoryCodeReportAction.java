package com.westchase.web.action.report;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.westchase.ejb.ReportService;
import com.westchase.persistence.dto.report.ContactDTO;
import com.westchase.persistence.model.Category;
import com.westchase.utils.ListUtils;
import com.westchase.utils.ejb.ServiceLocator;

/**
 * @author marc
 * 
 */
public class EmailContactsByCategoryCodeReportAction extends AbstractReportAction {


	private ReportService reportServ;

	private List<String> categoryCodes;
	private List<ContactDTO> results;

	private List<Category> availableCategories;
	
	private int employeeCount;

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
			
			writeTitle(wb, sheet, "Email Contacts by Category Code Report");

            String[] headers = { "Category Code", "Email", "Title", "Last Name", "First Name", "Salutation", "Company", "PersonId" }; 
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
				int col = 0;
				for (ContactDTO result : results) {
					Row row = sheet.createRow(rowNum);
					writeCell(wb, sheet, row, col++, result.getCategoryCode(), style);
					writeCell(wb, sheet, row, col++, result.getEmail(), style);
					writeCell(wb, sheet, row, col++, result.getTitle(), style);
					writeCell(wb, sheet, row, col++, result.getLastName(), style);
					writeCell(wb, sheet, row, col++, result.getFirstName(), style);
					writeCell(wb, sheet, row, col++, result.getSalutation(), style);
					writeCell(wb, sheet, row, col++, result.getCompany(), style);
					String personId = "";
					if (result.getPersonId() != null) {
						personId = result.getPersonId().toString();
					}
					writeCell(wb, sheet, row, col++, personId, style);
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
	public List<String> getCategoryCodes() {
		return categoryCodes;
	}

	public void setCategoryCodes(List<String> categoryCodes) {
		this.categoryCodes = categoryCodes;
	}


	public List<Category> getAvailableCategories() {
		return availableCategories;
	}

	public void setAvailableCategories(List<Category> availableCategories) {
		this.availableCategories = availableCategories;
	}

	public List<ContactDTO> getResults() {
		return results;
	}

	public void setResults(List<ContactDTO> results) {
		this.results = results;
	}

	public String input() {
		reportServ = ServiceLocator.lookupReportService();

		if (reportServ != null) {
			availableCategories = reportServ.getCategories();
		}
		return SUCCESS;
	}

	@Override
	public String execute() {
		reportServ = ServiceLocator.lookupReportService();

		if (reportServ != null) {
			availableCategories = reportServ.getCategories();
			List<String> codes = new ArrayList<String>();
			if (categoryCodes != null && !categoryCodes.isEmpty()) {
				for (String categoryCode : categoryCodes) {
					codes.add(new String(categoryCode.toString()));
				}
			}
			results = reportServ.runEmailContactsByCategoryCodeReport(codes, isWestchaseOnly(), getEmployeeCount());

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
	protected String getReportFileName() {
		return "emailcontactsbycategorycode_" + ListUtils.toString(getCategoryCodes());
	}
	public int getEmployeeCount() {
		return employeeCount;
	}
	public void setEmployeeCount(int employeeCount) {
		this.employeeCount = employeeCount;
	}
}
