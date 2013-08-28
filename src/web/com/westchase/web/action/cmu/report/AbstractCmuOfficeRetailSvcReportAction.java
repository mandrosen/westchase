package com.westchase.web.action.cmu.report;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

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

import com.westchase.persistence.dto.cmu.LeasingAgentDTO;
import com.westchase.persistence.model.CmuOfficeRetailSvc;
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

    	    CreationHelper createHelper = wb.getCreationHelper();
    	    
//		    FileOutputStream fileOut = new FileOutputStream("workbook.xlsx");
//		    wb.write(bos);
//		    fileOut.close();
		    Sheet sheet = wb.createSheet("cmu report");
			
//			CmuQuarter quarter = null;
//			if (results != null && !results.isEmpty()) {
//				quarter = results.get(0).getCmuQuarter();
//			}
//			String title = "Westchase District CMU " + name + " Report Action";
//			if (quarter != null) {
//				title += ": Quarter #" + quarter.getQuarterNum() + " " + quarter.getYear();
//			}
//			writeTitle(wb, sheet, title);
			

            String[] headers = { "Map #", "Building Name", "Address", "Leasing Agent", "Contact Company", "Email", "Phone", "Size (sq. ft.)", "Occupancy Rate", "Largest Space", "Occupied Sq. Ft", "Building Manager", "Phone" }; 
            // Write the Header to the excel file
            writeHeaders(wb, sheet, headers, -2, true);

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
            percentStyle.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
            percentStyle.setAlignment(CellStyle.ALIGN_RIGHT);
            percentStyle.setFont(font);
            percentStyle.setDataFormat(createHelper.createDataFormat().getFormat("0%"));
            setBorders(percentStyle);
            
    		CellStyle numberCommaStyle = wb.createCellStyle();
    		numberCommaStyle.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
    		numberCommaStyle.setAlignment(CellStyle.ALIGN_RIGHT);
    		numberCommaStyle.setFont(font);
    		numberCommaStyle.setDataFormat(createHelper.createDataFormat().getFormat("0,000"));
            setBorders(numberCommaStyle);

    		CellStyle numberStyle = wb.createCellStyle();
    		numberStyle.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
    		numberStyle.setFont(font);
    		numberStyle.setWrapText(true);
    		setBorders(numberStyle);
    		
    		
            
			if (results != null && !results.isEmpty()) {
				int totalOrs = 0;
				int totalSize = 0;
				double avgOcc = 0;
				int totalOcc = 0;
				int rowNum = 1;
				StringBuffer problemProperties = new StringBuffer();
				for (CmuOfficeRetailSvc result : results) {
					Property ors = result.getProperty();
					if (ors != null && ors.getId() != null && ors.getId().intValue() > 0) {
						totalOrs++;
						Row row = sheet.createRow(rowNum);
						try {
							int col = 0;
							writeCell(wb, sheet, row, col++, ors.getId(), style);
							
							String buildingName = ors.getBuildingName();
							if (ors.isSingleTenant()) {
								buildingName = "*" + buildingName; 
							}
	
							writeCell(wb, sheet, row, col++, buildingName, style);
	
							String location = ors.getGeoNumber() + " " + WordUtils.capitalizeFully(ors.getGeoAddress());
							writeCell(wb, sheet, row, col++, location, style);
							
							String leasingAgent = result.getLeasingAgent();
							String leasingCompany = result.getLeasingCompany();
							String leasingEmail = result.getLeasingAgentEmail();
							String leasingAgentPhone = result.getLeasingAgentPhone();
//							String leasingAgentFax = result.getLeasingAgentFax();
							
							if (ors.isSingleTenant() && StringUtils.isBlank(leasingAgent)) {
								if (reportServ != null) {
									LeasingAgentDTO leasingAgentDTO = reportServ.getLeasingAgent(ors.getId());
									if (leasingAgentDTO != null) {
										leasingAgent = leasingAgentDTO.getName();
										leasingCompany = leasingAgentDTO.getCompany();
										leasingEmail = leasingAgentDTO.getEmail();
										leasingAgentPhone = leasingAgentDTO.getPhone();
//										leasingAgentFax = leasingAgentDTO.getFax();
									}
								}
							}
							
							writeCell(wb, sheet, row, col++, leasingAgent, style);
							writeCell(wb, sheet, row, col++, leasingCompany, style);
							writeCell(wb, sheet, row, col++, leasingEmail, style);
							writeCell(wb, sheet, row, col++, leasingAgentPhone, style);
//							writeCell(wb, sheet, row, col++, leasingAgentFax, style);
							
							// we are looking for buildingsize, but on the cmu, it is called
							// sqftforlease (confusing)
							
							Double buildingSize = result.getSqFtForLease();
							writeCell(wb, sheet, row, col++, buildingSize, numberCommaStyle);
							if (buildingSize != null) {
								totalSize += buildingSize.intValue();
							}
							
							Double occupancyRate = result.getOccupancy();
							if (occupancyRate != null) {
								avgOcc += occupancyRate.doubleValue();
								writeCell(wb, sheet, row, col++, occupancyRate.doubleValue() / 100, percentStyle);
							} else {
								writeCell(wb, sheet, row, col++, 0, percentStyle);
							}
							
							Double largestSpace = result.getLargestSpace();
							writeCell(wb, sheet, row, col++, largestSpace, numberCommaStyle);
							
							// occupied sq ft - calculated
							if (buildingSize != null && occupancyRate != null) {
								double occupiedSqFt = buildingSize.doubleValue() * occupancyRate.doubleValue() / 100;
								totalOcc += occupiedSqFt;
								writeCell(wb, sheet, row, col++, new Double(occupiedSqFt), numberCommaStyle);
							} else {
								writeCell(wb, sheet, row, col++, new Double(0), style);
							}
							
							writeCell(wb, sheet, row, col++, result.getPropertyMgr(), style);
							writeCell(wb, sheet, row, col++, result.getPropertyMgrPhone(), style);
//							writeCell(wb, sheet, row, col++, result.getPropertyMgrFax(), style);
							
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
		    		CellStyle boldStyle = wb.createCellStyle();
		    		boldStyle.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
		    		boldStyle.setFont(boldFont);
		    		boldStyle.setAlignment(CellStyle.ALIGN_CENTER);
		    		boldStyle.setDataFormat(createHelper.createDataFormat().getFormat("0,000"));
		    		
		    		CellStyle totalBoldStyle = wb.createCellStyle();
		    		totalBoldStyle.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
		    		totalBoldStyle.setFont(boldFont);
		    		totalBoldStyle.setAlignment(CellStyle.ALIGN_CENTER);
		    		totalBoldStyle.setWrapText(true);

		    		CellStyle totalPctStyle = wb.createCellStyle();
		    		totalPctStyle.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
		    		totalPctStyle.setFont(boldFont);
		    		totalPctStyle.setAlignment(CellStyle.ALIGN_CENTER);
		    		totalPctStyle.setDataFormat(createHelper.createDataFormat().getFormat("0%"));
		    		
		    		CellStyle regBoldStyle = wb.createCellStyle();
		    		regBoldStyle.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
		    		regBoldStyle.setFont(boldFont);
		    		regBoldStyle.setWrapText(true);

		    		Row row = sheet.createRow(rowNum);
		    		writeCell(wb, sheet, row, 0, totalOrs, totalBoldStyle);
		    		writeCell(wb, sheet, row, 1, "TOTALS", totalBoldStyle);
		    		writeCell(wb, sheet, row, 7, totalSize, boldStyle);
		    		writeCell(wb, sheet, row, 8, new Double(avgOcc / totalOrs / 100), totalPctStyle);
		    		writeCell(wb, sheet, row, 10, totalOcc, boldStyle);
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
		super.fixColumns(sheet, cols, false);
		// change column width of mapno:
		sheet.setColumnWidth(0, 256 * 10);
	}
	
}
