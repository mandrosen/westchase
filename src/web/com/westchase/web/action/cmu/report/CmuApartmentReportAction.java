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
import com.westchase.persistence.model.CmuApartment;
import com.westchase.persistence.model.CmuQuarter;
import com.westchase.persistence.model.Property;

/**
 * @author marc
 *
 */
public class CmuApartmentReportAction extends AbstractCmuReportAction {
	
	private List<CmuApartment> results;
	
	@Override
	public String execute() {
		results = new ArrayList<CmuApartment>();
		if (getQuarterId() > 0) {
			try {
				InitialContext ctx = new InitialContext();
				reportServ = (CmuReportService) ctx.lookup("westchase/CmuReportServiceBean/local");
			} catch (Exception e) {
				log.error("", e);
			}
			if (reportServ != null) {
				results = reportServ.runCmuApartmentReport(getQuarterId());
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
			String title = "Westchase District CMU Apartments Report Action";
			if (quarter != null) {
				title += ": Quarter #" + quarter.getQuarterNum() + " " + quarter.getYear();
			}
			writeTitle(wb, sheet, title);
			

            String[] headers = { "Name & Address", "# of Units", "Occupance Rate", "Manager", "Mgr. Email", "Management Co.", "Owner" }; 
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
    		
            CellStyle percentStyle = wb.createCellStyle();
    		style.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
            percentStyle.setAlignment(CellStyle.ALIGN_RIGHT);
            percentStyle.setFont(font);
            percentStyle.setDataFormat(wb.createDataFormat().getFormat("0.0%"));
            setBorders(percentStyle);
            
            
            
			if (results != null && !results.isEmpty()) {
				int totalApts = 0;
				int totalRooms = 0;
				double avgOcc = 0;
				
				int rowNum = FIRST_DATA_ROW;
				for (CmuApartment result : results) {
					Property apartment = result.getProperty();
					if (apartment != null && apartment.getId() != null && apartment.getId().intValue() > 0) {
						totalApts++;
						Row row = sheet.createRow(rowNum);
						int col = 0;
						
						StringBuffer nameAddr = new StringBuffer();
						nameAddr.append(apartment.getBuildingName()).append("\n")
							.append(apartment.getGeoNumber()).append(" ").append(WordUtils.capitalizeFully(apartment.getGeoAddress())).append("\n")
							.append(WordUtils.capitalizeFully(apartment.getGeoCity())).append(", ").append(apartment.getGeoState()).append(" ").append(apartment.getGeoZipCode()).append("\n")
							.append("Map #").append(apartment.getId());
						writeCell(wb, sheet, row, col++, nameAddr.toString(), style);
//						String noUnitsStr = "Unknown";
						Integer noUnits = apartment.getNoUnits();
						int noUnitsInt = 0;
						if (noUnits != null) {
							noUnitsInt = noUnits.intValue();
							totalRooms += noUnitsInt;
//							noUnitsStr = noUnits.toString();
							writeCell(wb, sheet, row, col++, noUnits, style);
						} else {
							writeCell(wb, sheet, row, col++, "", style);
						}
//						writeCell(wb, sheet, row, col++, noUnitsStr, style);
						//writeCell(wb, sheet, row, col++, noUnits, style);
						
//						writeCell(wb, sheet, row, col++, formatPercent(result.getOccupancyRate()), style);
//						writeCellPct(wb, sheet, row, col++, result.getOccupancyRate(), percentStyle);
						Double occRate = result.getOccupancyRate();
						if (occRate != null) {
							avgOcc += occRate.doubleValue();
							writeCellPct(wb, sheet, row, col++, occRate, percentStyle);
						} else {
							writeCell(wb, sheet, row, col++, "", style);
						}

						// removed 2013-03-21 meeting
//						int availUnits = 0;
//						if (result.getOccupancyRate() != null) {
//							availUnits = (int) Math.rint((100 - result.getOccupancyRate().doubleValue()) * noUnitsInt / 100);
//						}
////						writeCell(wb, sheet, row, col++, String.valueOf(availUnits), style);
//						writeCell(wb, sheet, row, col++, new Integer(availUnits), style);
						
						
						StringBuffer propertyManager = new StringBuffer();
						propertyManager.append(result.getCommunityMgr()).append("\n");
						if (result.getCommunityMgrPhone() != null) {
							propertyManager.append(result.getCommunityMgrPhone());
						}

						writeCell(wb, sheet, row, col++, propertyManager.toString(), style);
						
						String email = "";
						if (result.getCommunityMgrEmail() != null) {
							email = result.getCommunityMgrEmail();
						}
						writeCell(wb, sheet, row, col++, email, style);
						
						
						StringBuffer propertyMgmt = new StringBuffer();
						propertyMgmt.append(result.getMgmtCompany()).append("\n");
						if (result.getSupervisor() != null) {
							propertyMgmt.append(result.getSupervisor()).append("\n");
						}
						if (result.getMgmtCompanyAddr() != null) {
							propertyMgmt.append(result.getMgmtCompanyAddr()).append("\n");
						}
						if (result.getSupervisorPhone() != null) {
							propertyMgmt.append(result.getSupervisorPhone());
						}

						writeCell(wb, sheet, row, col++, propertyMgmt.toString(), style);
						
						StringBuffer owner = new StringBuffer();
						if (result.getOwner() != null) {
							owner.append(WordUtils.capitalizeFully(result.getOwner())).append("\n");
						}
						if (result.getOwnerAddress() != null) {
							owner.append(result.getOwnerAddress()).append("\n");
						}
						if (result.getOwnerPhone() != null) {
							owner.append(result.getOwnerPhone());
						}
						writeCell(wb, sheet, row, col++, owner.toString(), style);
						
						rowNum++;
					}
				}
				if (totalApts > 0) {
		    		CellStyle boldStyle = wb.createCellStyle();
		    		style.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
		    		Font boldFont = wb.createFont();
		    		boldFont.setFontName(FONT_NAME);
		    		boldFont.setFontHeightInPoints(FONT_HEIGHT);
		    		boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		    		boldStyle.setFont(boldFont);

		    		Row row = sheet.createRow(rowNum);
					writeCell(wb, sheet, row, 0, "TOTAL: " + String.valueOf(totalApts), boldStyle);
					
					writeCell(wb, sheet, row, 1, String.valueOf(totalRooms), boldStyle);
//					writeCell(wb, sheet, row, 3, String.valueOf(totalOccupied), boldStyle);
					
					writeCell(wb, sheet, row, 2, formatPercent(new Double(avgOcc / totalApts)), boldStyle);
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

	public List<CmuApartment> getResults() {
		return results;
	}

	public void setResults(List<CmuApartment> results) {
		this.results = results;
	}

	@Override
	protected String getReportFileName() {
		return "Apartments_" + getReportDate();
	}

}
