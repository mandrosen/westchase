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
import com.westchase.persistence.model.CmuHotel;
import com.westchase.persistence.model.CmuQuarter;
import com.westchase.persistence.model.Property;

/**
 * @author marc
 *
 */
public class CmuHotelReportAction extends AbstractCmuReportAction {

	private List<CmuHotel> results;
	
	@Override
	public String execute() {
		results = new ArrayList<CmuHotel>();
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
//		    FileOutputStream fileOut = new FileOutputStream("workbook.xlsx");
//		    wb.write(bos);
//		    fileOut.close();
		    Sheet sheet = wb.createSheet("cmu report");
			
			CmuQuarter quarter = null;
			if (results != null && !results.isEmpty()) {
				quarter = results.get(0).getCmuQuarter();
			}
			String title = "Westchase District CMU Hotels Report Action";
			if (quarter != null) {
				title += ": Quarter #" + quarter.getQuarterNum() + " " + quarter.getYear();
			}
			writeTitle(wb, sheet, title);
			

            String[] headers = { "Name & Address", "# of Rooms", "Occupancy Rate", "General Manager" }; 
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
				int totalHotels = 0;
				int totalRooms = 0;
//				int totalOccupied = 0;
				double avgOcc = 0;
				int rowNum = FIRST_DATA_ROW;
				for (CmuHotel result : results) {
					Property hotel = result.getProperty();
					if (hotel != null && hotel.getId() != null && hotel.getId().intValue() > 0) {
						totalHotels++;
						Row row = sheet.createRow(rowNum);
						int col = 0;
						
						StringBuffer nameAddr = new StringBuffer();
						nameAddr.append(hotel.getBuildingName()).append("\n")
							.append(hotel.getGeoNumber()).append(" ").append(WordUtils.capitalizeFully(hotel.getGeoAddress())).append("\n")
							.append(WordUtils.capitalizeFully(hotel.getGeoCity())).append(", ").append(hotel.getGeoState()).append(" ").append(hotel.getGeoZipCode()).append("\n")
							.append("Map #").append(hotel.getId());
						writeCell(wb, sheet, row, col++, nameAddr.toString(), style);
						
//						String numRoomsStr = "Unknown";
						Integer noUnits = hotel.getNoUnits();
						int noUnitsInt = 0;
						if (noUnits != null) {
							noUnitsInt = noUnits.intValue();
							totalRooms += noUnitsInt;
//							numRoomsStr = noUnits.toString();
							writeCell(wb, sheet, row, col++, noUnits, style);
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
							writeCell(wb, sheet, row, col++, "", style);
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
//		    		WritableCellFormat boldFormat = new WritableCellFormat();
//		    		boldFormat.setBackground(Colour.WHITE);
//		    		WritableFont boldFont = new WritableFont(WritableFont.ARIAL, 11);
//		    		boldFont.setBoldStyle(WritableFont.BOLD);
//		    		boldFormat.setFont(boldFont);
		    		CellStyle boldStyle = wb.createCellStyle();
		    		style.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
		    		Font boldFont = wb.createFont();
		    		boldFont.setFontName(FONT_NAME);
		    		boldFont.setFontHeightInPoints(FONT_HEIGHT);
		    		boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		    		boldStyle.setFont(boldFont);

		    		Row row = sheet.createRow(rowNum);
					writeCell(wb, sheet, row, 0, "TOTAL: " + String.valueOf(totalHotels), boldStyle);
					
					writeCell(wb, sheet, row, 1, String.valueOf(totalRooms), boldStyle);
//					writeCell(wb, sheet, row, 3, String.valueOf(totalOccupied), boldStyle);
					
					writeCell(wb, sheet, row, 2, formatPercent(new Double(avgOcc / totalHotels)), boldStyle);
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

	public List<CmuHotel> getResults() {
		return results;
	}

	public void setResults(List<CmuHotel> results) {
		this.results = results;
	}

	@Override
	protected String getReportFileName() {
		return "Hotels_" + getReportDate();
	}

}
