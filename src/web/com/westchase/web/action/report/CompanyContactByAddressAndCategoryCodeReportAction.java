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
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.westchase.ejb.CompanyService;
import com.westchase.ejb.ReportService;
import com.westchase.persistence.dto.report.PropertyCompanyPhoneBookDTO;
import com.westchase.persistence.model.Category;
import com.westchase.persistence.model.Street;
import com.westchase.utils.ejb.ServiceLocator;

public class CompanyContactByAddressAndCategoryCodeReportAction extends AbstractReportAction {
	
	private static final long serialVersionUID = -4546515120543947910L;

	private static Log log = LogFactory.getLog(CompanyContactByAddressAndCategoryCodeReportAction.class);

	private ReportService reportServ;
    private CompanyService compServ;

	private int startAddress;
	private int endAddress;
	private String street;
	private String streetWildcard;
	private List<String> categoryCodes;

	private List<Category> availableCategories;
    private List<Street> availableStreets;
    
	private List<PropertyCompanyPhoneBookDTO> results;


	public String input() {
		reportServ = ServiceLocator.lookupReportService();
        compServ = ServiceLocator.lookupCompanyService();

		if (reportServ != null) {
			availableCategories = reportServ.getCategories();
    		availableStreets = compServ.listStreets();
		}
		return SUCCESS;
	}

	@Override
	public String execute() {
		reportServ = ServiceLocator.lookupReportService();
        compServ = ServiceLocator.lookupCompanyService();

		if (reportServ != null) {
			availableCategories = reportServ.getCategories();
    		availableStreets = compServ.listStreets();
    		
			List<String> codes = new ArrayList<String>();
			if (categoryCodes != null && !categoryCodes.isEmpty()) {
				for (String categoryCode : categoryCodes) {
					codes.add(new String(categoryCode.toString()));
				}
			}
			results = reportServ.runContactsByAddressCategory(startAddress, endAddress, street, streetWildcard, codes);
			if (results != null && !results.isEmpty() && isFixAddressCaps()) {
				for (PropertyCompanyPhoneBookDTO result : results) {
					result.setAddress(WordUtils.capitalizeFully(result.getAddress()));
					result.setCity(WordUtils.capitalizeFully(result.getCity()));
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

	@Override
	protected ByteArrayOutputStream createWorkbook() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
		try {
			Workbook wb = new XSSFWorkbook();
		    Sheet sheet = wb.createSheet("report");
			
			writeTitle(wb, sheet, "Company PhoneBook By Address Report");
			
            String[] headers = { "Mapno" , "Company", "First Name", "Last Name", "Job Title", "Address", "Room No", "City", "State", "Zip", "Phone", "Fax", "Mobile", "Email", "Cats" }; 

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
					writeCell(wb, sheet, row, col++, result.getJobTitle(), style);
					writeCell(wb, sheet, row, col++, result.getAddress(), style);
					writeCell(wb, sheet, row, col++, result.getRoomNo(), style);
					writeCell(wb, sheet, row, col++, result.getCity(), style);
					writeCell(wb, sheet, row, col++, result.getState(), style);
					writeCell(wb, sheet, row, col++, result.getZipCode(), style);
					writeCell(wb, sheet, row, col++, result.getWkPhone(), style);
					writeCell(wb, sheet, row, col++, result.getFaxPhone(), style);
					writeCell(wb, sheet, row, col++, result.getMobilePhone(), style);
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

	public int getStartAddress() {
		return startAddress;
	}

	public void setStartAddress(int startAddress) {
		this.startAddress = startAddress;
	}

	public int getEndAddress() {
		return endAddress;
	}

	public void setEndAddress(int endAddress) {
		this.endAddress = endAddress;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
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

	public List<Street> getAvailableStreets() {
		return availableStreets;
	}

	public void setAvailableStreets(List<Street> availableStreets) {
		this.availableStreets = availableStreets;
	}

	public List<PropertyCompanyPhoneBookDTO> getResults() {
		return results;
	}

	public void setResults(List<PropertyCompanyPhoneBookDTO> results) {
		this.results = results;
	}

	public String getStreetWildcard() {
		return streetWildcard;
	}

	public void setStreetWildcard(String streetWildcard) {
		this.streetWildcard = streetWildcard;
	}

}
