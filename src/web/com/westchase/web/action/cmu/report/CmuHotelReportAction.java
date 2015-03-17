package com.westchase.web.action.cmu.report;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;

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
import com.westchase.persistence.dto.cmu.report.HotelDTO;
import com.westchase.persistence.model.Property;

/**
 * @author marc
 *
 */
public class CmuHotelReportAction extends AbstractCmuReportAction {

	private List<HotelDTO> results;
	
	@Override
	public String execute() {
		results = new ArrayList<HotelDTO>();
		if (getQuarterId() > 0) {
			try {
				InitialContext ctx = new InitialContext();
				reportServ = (CmuReportService) ctx.lookup("westchase/CmuReportServiceBean/local");
			} catch (Exception e) {
				log.error("", e);
			}
			if (reportServ != null) {
				results = reportServ.runCmuHotelReport(getQuarterId());
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
//			String title = "Westchase District CMU Hotels Report Action";
//			if (quarter != null) {
//				title += ": Quarter #" + quarter.getQuarterNum() + " " + quarter.getYear();
//			}
//			writeTitle(wb, sheet, title);
			

            String[] headers = { "Map#", "Name & Address", "Units", "Occupancy", "Vacant", "General Manager" }; 
            // Write the Header to the excel file
            writeHeaders(wb, sheet, headers, -2, true);

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
				int totalHotels = 0;
				int totalRooms = 0;
//				int totalOccupied = 0;
				double avgOcc = 0;
				int rowNum = 1;
				for (HotelDTO result : results) {
					Property hotel = result.getProperty();
					if (hotel != null && hotel.getId() != null && hotel.getId().intValue() > 0) {
						totalHotels++;
						Row row = sheet.createRow(rowNum);
						int col = 0;
						
						writeCell(wb, sheet, row, col++, hotel.getId(), centerStyle);
						
						StringBuffer nameAddr = new StringBuffer();
						nameAddr.append(hotel.getBuildingName()).append("\n")
							.append(hotel.getGeoNumber()).append(" ").append(WordUtils.capitalizeFully(hotel.getGeoAddress())).append("\n")
							.append(WordUtils.capitalizeFully(hotel.getGeoCity())).append(", ").append(hotel.getGeoState()).append(" ").append(hotel.getGeoZipCode());
						writeCell(wb, sheet, row, col++, nameAddr.toString(), style);
						
//						String numRoomsStr = "Unknown";
						Integer noUnits = hotel.getNoUnits();
						int noUnitsInt = 0;
						if (noUnits != null) {
							noUnitsInt = noUnits.intValue();
							totalRooms += noUnitsInt;
//							numRoomsStr = noUnits.toString();
							writeCell(wb, sheet, row, col++, noUnits, centerStyle);
						} else {
							writeCell(wb, sheet, row, col++, "", style);
						}
//						writeCell(wb, sheet, row, col++, numRoomsStr, style);
						//writeCell(wb, sheet, row, col++, noUnits, style);
						
						Double occRate = result.getOccupancy();
						if (occRate != null) {
							avgOcc += occRate.doubleValue();
							writeCellPct(wb, sheet, row, col++, occRate, percentStyle);
						} else {
							writeCell(wb, sheet, row, col++, "", centerStyle);
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
						
//						writeCell(wb, sheet, row, col++, formatPercent(occRate), style);
						//`writeCellPct(wb, sheet, row, col++, occRate, style);

//						int occRooms = 0;
//						if (occRate != null && noUnitsInt > 0) {
//							occRooms = (int) (occRate * noUnitsInt / 100);
//							totalOccupied += occRooms;
//						}
////						writeCell(wb, sheet, row, col++, String.valueOf(occRooms), style);
//						writeCell(wb, sheet, row, col++, new Integer(occRooms), style);
						
						StringBuffer manager = new StringBuffer();
						if (result.getGeneralMgr() != null) {
							manager.append(result.getGeneralMgr()).append("\n");
						}
						if (result.getGeneralMgrPhone() != null) {
							manager.append(result.getGeneralMgrPhone());
						}
						writeCell(wb, sheet, row, col++, manager.toString(), style);

						rowNum++;
					}
				}
				if (totalHotels > 0) {
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
					writeCell(wb, sheet, row, 1, "TOTAL: " + String.valueOf(totalHotels), boldStyle);
					
					writeCell(wb, sheet, row, 2, totalRooms, boldStyle);
//					writeCell(wb, sheet, row, 3, String.valueOf(totalOccupied), boldStyle);
					
					writeCell(wb, sheet, row, 3, formatPercent(new Double(avgOcc / totalHotels)), boldStyle);
				}				
			}
			
//			fixColumns(sheet, headers.length);
			
			int col = 0;
			sheet.setColumnWidth(col++, 30 * COLUMN_WIDTH_MULT);
			sheet.setColumnWidth(col++, 250 * COLUMN_WIDTH_MULT);
			sheet.setColumnWidth(col++, 70 * COLUMN_WIDTH_MULT);
			sheet.setColumnWidth(col++, 70 * COLUMN_WIDTH_MULT);
			sheet.setColumnWidth(col++, 70 * COLUMN_WIDTH_MULT);
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

	public List<HotelDTO> getResults() {
		return results;
	}

	public void setResults(List<HotelDTO> results) {
		this.results = results;
	}

	@Override
	protected String getReportFileName() {
		return "Hotels_" + getReportDate();
	}

}
