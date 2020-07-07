package com.westchase.web.action.cmu.report;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.westchase.ejb.CmuReportService;
import com.westchase.persistence.dto.cmu.report.ApartmentDTO;
import com.westchase.persistence.model.Property;

/**
 * @author marc
 *
 */
public class CmuApartmentReportAction extends AbstractCmuReportAction {
	
	private List<ApartmentDTO> results;
	
	@Override
	public String execute() {
		results = new ArrayList<ApartmentDTO>();
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

    	    CreationHelper createHelper = wb.getCreationHelper();
//		    FileOutputStream fileOut = new FileOutputStream("workbook.xlsx");
//		    wb.write(bos);
//		    fileOut.close();
		    Sheet sheet = wb.createSheet("cmu report");
			
//			CmuQuarter quarter = null;
//			if (results != null && !results.isEmpty()) {
//				quarter = results.get(0).getCmuQuarter();
//			}
//			String title = "Westchase District CMU Apartments Report Action";
//			if (quarter != null) {
//				title += ": Quarter #" + quarter.getQuarterNum() + " " + quarter.getYear();
//			}
//			writeTitle(wb, sheet, title);
			

            String[] headers = { "Map#", "Apartment Community", "Units", "Occupancy", "Vacant Units", "Manager", "Email", "Management Co." }; 
            // Write the Header to the excel file
            writeHeaders(wb, sheet, headers, -2, true);

//    		WritableCellFormat cellFormat = new WritableCellFormat();
//    		cellFormat.setBackground(Colour.WHITE);
//    		cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
//    		WritableFont font = new WritableFont(WritableFont.ARIAL, 11);
//    		cellFormat.setFont(font);
            
    		Font font = wb.createFont();
    		font.setFontName(FONT_NAME);
    		font.setFontHeightInPoints(FONT_HEIGHT);
            
    		CellStyle style = wb.createCellStyle();
    		style.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
    		style.setFont(font);
    		style.setWrapText(true);
    		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
    		setBorders(style);
    		
    		CellStyle centerStyle = wb.createCellStyle();
    		centerStyle.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
    		centerStyle.setFont(font);
    		centerStyle.setAlignment(CellStyle.ALIGN_CENTER);
    		centerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
    		setBorders(centerStyle);
    		
            CellStyle percentStyle = wb.createCellStyle();
    		style.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
            percentStyle.setAlignment(CellStyle.ALIGN_CENTER);
            percentStyle.setFont(font);
            percentStyle.setDataFormat(wb.createDataFormat().getFormat("0.0%"));
            percentStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
            setBorders(percentStyle);
            
            
            
			if (results != null && !results.isEmpty()) {
				
				Collections.sort(results, SortByPropertyNameComparator.getInstance());
				
				int totalApts = 0;
				int totalRooms = 0;
				double avgOcc = 0;
				
				int rowNum = 1;
				for (ApartmentDTO result : results) {
					Property apartment = result.getProperty();
					if (apartment != null && apartment.getId() != null && apartment.getId().intValue() > 0) {
						totalApts++;
						Row row = sheet.createRow(rowNum);
						int col = 0;
						
						writeCell(wb, sheet, row, col++, apartment.getId(), centerStyle);
						
						StringBuffer nameAddr = new StringBuffer();
						nameAddr.append(apartment.getBuildingName()).append("\n")
							.append(apartment.getGeoNumber()).append(" ").append(WordUtils.capitalizeFully(apartment.getGeoAddress())).append("\n")
							.append(WordUtils.capitalizeFully(apartment.getGeoCity())).append(", ").append(apartment.getGeoState()).append(" ").append(apartment.getGeoZipCode());
						writeCell(wb, sheet, row, col++, nameAddr.toString(), style);
						
//						String noUnitsStr = "Unknown";
						Integer noUnits = apartment.getNoUnits();
						int noUnitsInt = 0;
						if (noUnits != null) {
							noUnitsInt = noUnits.intValue();
							totalRooms += noUnitsInt;
//							noUnitsStr = noUnits.toString();
							writeCell(wb, sheet, row, col++, noUnits, centerStyle);
						} else {
							writeCell(wb, sheet, row, col++, "", centerStyle);
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
						
						// vacancy
						// added 2013-08-27
						if (noUnits != null && occRate != null) {
							noUnitsInt = noUnits.intValue();
							int vacancy = (int) (noUnitsInt - ((occRate.doubleValue() / 100) * noUnitsInt));
							writeCell(wb, sheet, row, col++, vacancy, centerStyle);
						} else {
							writeCell(wb, sheet, row, col++, "", centerStyle);
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
						writeCell(wb, sheet, row, col++, result.getCommunityMgrEmail(), style);
						
//						String email = "";
//						if (result.getCommunityMgrEmail() != null) {
//							email = result.getCommunityMgrEmail();
//						}
//						writeCell(wb, sheet, row, col++, email, style);
						
						
						StringBuffer propertyMgmt = new StringBuffer();
						if (StringUtils.isNotBlank(result.getMgmtCompany())) {
							propertyMgmt.append(result.getMgmtCompany()).append("\n");
						}
						if (StringUtils.isNotBlank(result.getSupervisor())) {
							propertyMgmt.append(result.getSupervisor()).append("\n");
						}
						if (StringUtils.isNotBlank(result.getMgmtCompanyAddr())) {
							propertyMgmt.append(result.getMgmtCompanyAddr()).append("\n");
						}
						if (StringUtils.isNotBlank(result.getSupervisorPhone())) {
							propertyMgmt.append(result.getSupervisorPhone());
						}

						writeCell(wb, sheet, row, col++, propertyMgmt.toString(), style);
						
						rowNum++;
					}
				}
				if (totalApts > 0) {
		    		CellStyle boldStyle = wb.createCellStyle();
		    		boldStyle.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
		    		Font boldFont = wb.createFont();
		    		boldFont.setFontName(FONT_NAME);
		    		boldFont.setFontHeightInPoints(FONT_HEIGHT);
		    		boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		    		boldStyle.setFont(boldFont);
		    		boldStyle.setAlignment(CellStyle.ALIGN_CENTER);
		    		boldStyle.setDataFormat(createHelper.createDataFormat().getFormat("0,000"));

		    		Row row = sheet.createRow(rowNum);
					writeCell(wb, sheet, row, 1, "TOTAL: " + String.valueOf(totalApts), boldStyle);
					
					writeCell(wb, sheet, row, 2, totalRooms, boldStyle);
//					writeCell(wb, sheet, row, 3, String.valueOf(totalOccupied), boldStyle);
					
					writeCell(wb, sheet, row, 3, formatPercent(new Double(avgOcc / totalApts)), boldStyle);
				}	
			}
			
//			fixColumns(sheet, headers.length);

			int col = 0;
			sheet.setColumnWidth(col++, 30 * COLUMN_WIDTH_MULT);
			sheet.setColumnWidth(col++, 250 * COLUMN_WIDTH_MULT);
			sheet.setColumnWidth(col++, 70 * COLUMN_WIDTH_MULT);
			sheet.setColumnWidth(col++, 70 * COLUMN_WIDTH_MULT);
			sheet.setColumnWidth(col++, 70 * COLUMN_WIDTH_MULT);
			sheet.setColumnWidth(col++, 150 * COLUMN_WIDTH_MULT);
			sheet.setColumnWidth(col++, 250 * COLUMN_WIDTH_MULT);
		
//          wb.write();
//          wb.close();
			wb.write(bos);
			bos.close();
            
		} catch (Exception e) {
			log.error("", e);
		}
    	
		return bos;
	}

	public List<ApartmentDTO> getResults() {
		return results;
	}

	public void setResults(List<ApartmentDTO> results) {
		this.results = results;
	}

	@Override
	protected String getReportFileName() {
		return "Apartments_" + getReportDate();
	}
	
	static class SortByPropertyNameComparator implements Comparator<ApartmentDTO> {
		private static SortByPropertyNameComparator INSTANCE;
		public static SortByPropertyNameComparator getInstance() {
			if (INSTANCE == null) {
				INSTANCE = new SortByPropertyNameComparator();
			}
			return INSTANCE;
		}
		private SortByPropertyNameComparator() {
			super();
		}
		@Override
		public int compare(ApartmentDTO o1, ApartmentDTO o2) {
			return o1.getProperty().getBuildingName().compareTo(o2.getProperty().getBuildingName());
		}
		
	}

}
