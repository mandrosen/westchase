package com.westchase.web.action.report;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
public class ContactsByCategoryCodeReportAction extends AbstractReportAction {
	private static Log log = LogFactory.getLog(ContactsByCategoryCodeReportAction.class);

	private ReportService reportServ;

	private List<String> categoryCodes;
	private List<ContactDTO> results;
	private List<ContactDTO> filteredResults;

	private List<Category> availableCategories;
	
	private int employeeCount;
	
	private List<Integer> includedContacts;

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
			results = reportServ.runContactsByCategoryCodeReport(codes, isWestchaseOnly(), getEmployeeCount());
			if (results != null && !results.isEmpty() && isFixAddressCaps()) {
				for (ContactDTO result : results) {
					result.setStAddress(WordUtils.capitalizeFully(result.getStAddress()));
					result.setCity(WordUtils.capitalizeFully(result.getCity()));
				}
			}
			if (EXCEL.equals(type)) {
				return exportToExcel();
			}
			if (EMAIL.equals(type)) {
				return sendEmail();
			}
//            try {
//				JasperCompileManager.compileReportToFile("jasper/labels.jrxml", "jasper/labels.jasper");
//			} catch (JRException e) {
//				log.error("", e);
//			}

			if (("labels".equals(type)) || 
					("labels23".equals(type)) ||
					("labels23wdlogo".equals(type)) ||
					("nametags".equals(type))) {
				generateFilteredResults();
				if ("labels".equals(type)) {
					return "labels";
				}
				if ("labels23".equals(type)) {
					return "labels23";
				}
				if ("labels23wdlogo".equals(type)) {
					return "labels23wdlogo";
				}
				if ("nametags".equals(type)) {
					return "nametags";
				}
			}
		}

		return SUCCESS;
	}

	private void generateFilteredResults() {
		filteredResults = new ArrayList<ContactDTO>();
		if (results != null && includedContacts != null) {
			for (ContactDTO result : results) {
				if (includedContacts.contains(result.getId())) {
					filteredResults.add(result);
				}
			}
		}
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

			writeTitle(wb, sheet, "Contacts by Category Code Report");

			String[] headers = { "Category", "ID", "Salutation", "First Name", "Last Name", "Title", "Job Title",
					"Company", "Naics", "No Employees", "St Number", "St Address", "Room No", "City", "State", "ZipCode", "Email", "Mobile Phone", "Work Phone" };
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
					int col = 0;
					writeCell(wb, sheet, row, col++, result.getCategoryCode(), style);
					writeCell(wb, sheet, row, col++, result.getId(), style);
					writeCell(wb, sheet, row, col++, result.getSalutation(), style);
					writeCell(wb, sheet, row, col++, result.getFirstName(), style);
					writeCell(wb, sheet, row, col++, result.getLastName(), style);
					writeCell(wb, sheet, row, col++, result.getTitle(), style);
					writeCell(wb, sheet, row, col++, result.getJobTitle(), style);
					writeCell(wb, sheet, row, col++, result.getCompany(), style);
					writeCell(wb, sheet, row, col++, result.getNaics(), style);
					writeCell(wb, sheet, row, col++, result.getNoEmployees(), style);
					writeCell(wb, sheet, row, col++, result.getStNumber(), style);
					writeCell(wb, sheet, row, col++, result.getStAddress(), style);
					writeCell(wb, sheet, row, col++, result.getRoomNo(), style);
					writeCell(wb, sheet, row, col++, result.getCity(), style);
					writeCell(wb, sheet, row, col++, result.getState(), style);
					writeCell(wb, sheet, row, col++, result.getZipCode(), style);
					writeCell(wb, sheet, row, col++, result.getEmail(), style);
					writeCell(wb, sheet, row, col++, result.getMobilePhone(), style);
					writeCell(wb, sheet, row, col++, result.getWkPhone(), style);
					rowNum++;
				}
			}
			
			fixColumns(sheet, headers.length);
			
//			wb.write();
//			wb.close();
			wb.write(bos);
			bos.close();

		} catch (Exception e) {
			log.error("", e);
		}

		return bos;
	}

	@Override
	protected String getReportFileName() {
		return "contactsbycategorycode_" + ListUtils.toString(getCategoryCodes());
	}

	public int getEmployeeCount() {
		return employeeCount;
	}

	public void setEmployeeCount(int employeeCount) {
		this.employeeCount = employeeCount;
	}

	public List<ContactDTO> getFilteredResults() {
		return filteredResults;
	}

	public void setFilteredResults(List<ContactDTO> filteredResults) {
		this.filteredResults = filteredResults;
	}

	public List<Integer> getIncludedContacts() {
		return includedContacts;
	}

	public void setIncludedContacts(List<Integer> includedContacts) {
		this.includedContacts = includedContacts;
	}
}
