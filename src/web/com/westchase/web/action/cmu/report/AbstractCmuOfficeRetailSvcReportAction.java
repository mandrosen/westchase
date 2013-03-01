package com.westchase.web.action.cmu.report;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.westchase.persistence.dto.cmu.LeasingAgentDTO;
import com.westchase.persistence.model.CmuOfficeRetailSvc;
import com.westchase.persistence.model.CmuQuarter;
import com.westchase.persistence.model.Property;
import com.westchase.utils.ejb.ServiceLocator;

/**
 * @author marc
 *
 */
public abstract class AbstractCmuOfficeRetailSvcReportAction extends AbstractCmuReportAction {

	private int businessTypeId;
	private String name;
	
	protected List<CmuOfficeRetailSvc> results;
	
	public AbstractCmuOfficeRetailSvcReportAction(int businessTypeId, String name) {
		this.businessTypeId = businessTypeId;
		this.name = name;
	}

	@Override
	public String execute() {
		results = new ArrayList<CmuOfficeRetailSvc>();
		if (getQuarterId() > 0) {
			reportServ = ServiceLocator.lookupCmuReportService();

			if (reportServ != null) {
				results = reportServ.runCmuOfficeRetailSvcReport(getQuarterId(), getBusinessTypeId());
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
			String title = "Westchase District CMU " + name + " Report Action";
			if (quarter != null) {
				title += ": Quarter #" + quarter.getQuarterNum() + " " + quarter.getYear();
			}
			writeTitle(wb, sheet, title);
			

            String[] headers = { "Map #", "Building Name", "Address", "Leasing Agent", "Contact Company", "Email", "Phone", "Fax", "Size (sq. ft.)", "Occupancy Rate", "Largest Space", "Occupied Sq. Ft", "Building Manager", "Phone", "Fax" }; 
            // Write the Header to the excel file
            writeHeaders(wb, sheet, headers);

//    		WritableCellFormat cellFormat = new WritableCellFormat();
//    		cellFormat.setBackground(Colour.WHITE);
//    		cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
//    		WritableFont font = new WritableFont(WritableFont.ARIAL, 11);
//    		cellFormat.setFont(font);
//    		cellFormat.setWrap(true);

    		Font font = wb.createFont();
    		font.setFontName(FONT_NAME);
    		font.setFontHeightInPoints(FONT_HEIGHT);
            
    		CellStyle style = wb.createCellStyle();
    		style.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
    		style.setFont(font);
    		style.setWrapText(true);
    		setBorders(style);
    		
            CellStyle percentStyle = wb.createCellStyle();
    		style.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
            percentStyle.setAlignment(CellStyle.ALIGN_RIGHT);
            percentStyle.setFont(font);
            percentStyle.setDataFormat(wb.createDataFormat().getFormat("0.0%"));
            setBorders(percentStyle);

    		CellStyle numberStyle = wb.createCellStyle();
    		numberStyle.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
    		numberStyle.setFont(font);
    		numberStyle.setWrapText(true);
    		setBorders(numberStyle);
    		
    		
            
			if (results != null && !results.isEmpty()) {
				int totalOrs = 0;
				int totalSize = 0;
				double avgOcc = 0;
				int rowNum = FIRST_DATA_ROW;
				StringBuffer problemProperties = new StringBuffer();
				for (CmuOfficeRetailSvc result : results) {
					Property ors = result.getProperty();
					if (ors != null && ors.getId() != null && ors.getId().intValue() > 0) {
						totalOrs++;
						Row row = sheet.createRow(rowNum);
						try {
							int col = 0;
							writeCell(wb, sheet, row, col++, ors.getId(), style);
	
							writeCell(wb, sheet, row, col++, ors.getBuildingName(), style);
	
							String location = ors.getGeoNumber() + " " + WordUtils.capitalizeFully(ors.getGeoAddress());
							writeCell(wb, sheet, row, col++, location, style);
							
							String leasingAgent = result.getLeasingAgent();
							String leasingCompany = result.getLeasingCompany();
							String leasingEmail = result.getLeasingAgentEmail();
							String leasingAgentPhone = result.getLeasingAgentPhone();
							String leasingAgentFax = result.getLeasingAgentFax();
							
							if (ors.isSingleTenant() && StringUtils.isBlank(leasingAgent)) {
								if (reportServ != null) {
									LeasingAgentDTO leasingAgentDTO = reportServ.getLeasingAgent(ors.getId());
									if (leasingAgentDTO != null) {
										leasingAgent = leasingAgentDTO.getName();
										leasingCompany = leasingAgentDTO.getCompany();
										leasingEmail = leasingAgentDTO.getEmail();
										leasingAgentPhone = leasingAgentDTO.getPhone();
										leasingAgentFax = leasingAgentDTO.getFax();
									}
								}
							}
							
							writeCell(wb, sheet, row, col++, leasingAgent, style);
							writeCell(wb, sheet, row, col++, leasingCompany, style);
							writeCell(wb, sheet, row, col++, leasingEmail, style);
							writeCell(wb, sheet, row, col++, leasingAgentPhone, style);
							writeCell(wb, sheet, row, col++, leasingAgentFax, style);
							
//							String sizeStr = "Unknown";
							Integer size = ors.getBuildingSize();
							int sizeInt = 0;
							if (size != null) {
								sizeInt = size.intValue();
								totalSize += sizeInt;
//								sizeStr = size.toString();
								writeCell(wb, sheet, row, col++, size, style);
							} else {
								writeCell(wb, sheet, row, col++, "", style);
							}
//							writeCell(wb, sheet, row, 7, sizeStr, style);
							//writeCell(wb, sheet, row, col++, size, style);
							
							Double occ = result.getOccupancy();
							if ((occ == null || occ.doubleValue() == 0) && ors.isSingleTenant()) {
								occ = new Double(100);
							}
							if (occ != null) {
								avgOcc += occ.doubleValue();
								writeCellPct(wb, sheet, row, col++, occ, percentStyle);
							} else {
								writeCell(wb, sheet, row, col++, occ, style);
							}
//							writeCell(wb, sheet, row, col++, formatPercent(occ), style);
							//writeCellPct(wb, sheet, row, col++, occ, style);
							
//							String largeStr = "Unknown";
							Double largest = result.getLargestSpace();
//							if (largest != null) {
//								largeStr = largest.toString();
//							}
//							writeCell(wb, sheet, row, col++, largeStr, style);
							writeCell(wb, sheet, row, col++, largest, style);
							
							double occupied = 0;
							Double forLease = result.getSqFtForLease();
							if (forLease != null && sizeInt > 0) {
								double forLeaseDouble = forLease.doubleValue();
								occupied = sizeInt - forLeaseDouble;
								writeCell(wb, sheet, row, col++, new Double(occupied), style);
							} else {
								writeCell(wb, sheet, row, col++, "", style);
							}
//							writeCell(wb, sheet, row, col++, String.valueOf(occupied), style);
							//writeCell(wb, sheet, row, col++, new Double(occupied), style);
							
							writeCell(wb, sheet, row, col++, result.getPropertyMgr(), style);
							writeCell(wb, sheet, row, col++, result.getPropertyMgrPhone(), style);
							writeCell(wb, sheet, row, col++, result.getPropertyMgrFax(), style);
							
							rowNum++;
						} catch (Exception e) {
//							log.error("Unable to add row for Property: " + ors.getId(), e);
							problemProperties.append(ors.getId() + ",");
							sheet.removeRow(row);
							totalOrs--;
						}
					}
				}
				

	    		Font boldFont = wb.createFont();
	    		boldFont.setFontName(FONT_NAME);
	    		boldFont.setFontHeightInPoints(FONT_HEIGHT);
	    		boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
				
				if (totalOrs > 0) {
//		    		WritableCellFormat boldFormat = new WritableCellFormat();
//		    		boldFormat.setBackground(Colour.WHITE);
//		    		WritableFont boldFont = new WritableFont(WritableFont.ARIAL, 11);
//		    		boldFont.setBoldStyle(WritableFont.BOLD);
//		    		boldFormat.setFont(boldFont);
		    		CellStyle boldStyle = wb.createCellStyle();
		    		style.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
		    		boldStyle.setFont(boldFont);

		    		Row row = sheet.createRow(rowNum);
		    		writeCell(wb, sheet, row, 0, String.valueOf(totalOrs), boldStyle);
		    		writeCell(wb, sheet, row, 1, "TOTALS", boldStyle);
		    		writeCell(wb, sheet, row, 7, String.valueOf(totalSize), boldStyle);
		    		writeCell(wb, sheet, row, 8, formatPercent(new Double(avgOcc / totalOrs)), boldStyle);
				}
				
				if (StringUtils.isNotBlank(problemProperties.toString())) {
		    		CellStyle boldStyle = wb.createCellStyle();
		    		style.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
		    		boldStyle.setFont(boldFont);
		    		boldStyle.setWrapText(true);
		    		
					Row row = sheet.createRow(rowNum + 2);
					writeCell(wb, sheet, row, 0, "PROBLEMS:", boldStyle);
					writeCell(wb, sheet, row, 1, problemProperties.toString(), boldStyle);
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

	public int getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(int businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public List<CmuOfficeRetailSvc> getResults() {
		return results;
	}

	public void setResults(List<CmuOfficeRetailSvc> results) {
		this.results = results;
	}

	@Override
	protected String getReportFileName() {
		return this.name + "_" + getReportDate();
	}

	@Override
	protected void fixColumns(Sheet sheet, int cols) {
		super.fixColumns(sheet, cols);
		// change column width of mapno:
		sheet.setColumnWidth(0, 256 * 10);
	}
	
}
