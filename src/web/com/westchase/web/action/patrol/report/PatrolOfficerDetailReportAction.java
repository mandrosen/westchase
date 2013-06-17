package com.westchase.web.action.patrol.report;

import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.westchase.ejb.PatrolService;
import com.westchase.persistence.dto.patrol.OfficerCountDTO;
import com.westchase.persistence.dto.patrol.OfficerListCountListDTO;
import com.westchase.persistence.model.IdNamed;
import com.westchase.persistence.model.Officer;
import com.westchase.utils.DateUtils;
import com.westchase.utils.ejb.ServiceLocator;
import com.westchase.web.action.report.AbstractReportAction;

public class PatrolOfficerDetailReportAction extends AbstractReportAction {

	private List<Officer> availableOfficers;

	private List<Integer> officerIdList;
	private String startDate;
	private String endDate;
	
	private int reportType;
	
	private OfficerListCountListDTO results;

	public PatrolOfficerDetailReportAction() {
		super();
	}
	
	private void prepareLists() {
		PatrolService patrolServ = ServiceLocator.lookupPatrolService();
		if (patrolServ != null) {
			availableOfficers = patrolServ.listOfficers();
		}
	}
	
	public String input() {
		prepareLists();
		return SUCCESS;
	}
	
	@Override
	public String execute() {
		results = new OfficerListCountListDTO();
		PatrolService patrolServ = ServiceLocator.lookupPatrolService();
		if (patrolServ != null) {
			prepareLists();
			
			if (reportType == 1) {
				results = patrolServ.runOfficerDetailCategoryReport(officerIdList, DateUtils.getDateFromWeb(startDate), DateUtils.getDateFromWeb(endDate));
			} else {
				results = patrolServ.runOfficerDetailTypeReport(officerIdList, DateUtils.getDateFromWeb(startDate), DateUtils.getDateFromWeb(endDate));
			}
			if (EXCEL.equals(type)) {
				return exportToExcel();
			}
			if (EMAIL.equals(type)) {
				return sendEmail();
			}
			setType(null);
		}     
		return SUCCESS;	
	}
	
	private String getReportTypeName() {
		if (reportType == 0) {
			return "Call Codes";
		}
		return "Categories";
	}
	
	@Override
	protected ByteArrayOutputStream createWorkbook() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
		try {
			Workbook wb = new XSSFWorkbook();
		    Sheet sheet = wb.createSheet("report");
			
			writeTitle(wb, sheet, "Patrol Detail Report: " + getReportTypeName());
			

    		CellStyle style = wb.createCellStyle();
    		style.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
//    		style.setFillPattern(CellStyle.BIG_SPOTS);
    		style.setAlignment(CellStyle.ALIGN_CENTER);
    		Font font = wb.createFont();
    		font.setFontName(FONT_NAME);
    		font.setFontHeightInPoints(FONT_HEIGHT);
    		style.setFont(font);
    		
    		CellStyle headerStyle = wb.createCellStyle();
//    		headerStyle.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
//    		headerStyle.setFillPattern(CellStyle.BIG_SPOTS);
//    		headerStyle.setFillPattern((short) 1);
    		Font headerFont = wb.createFont();
    		headerFont.setFontName(FONT_NAME);
    		headerFont.setFontHeightInPoints(FONT_HEIGHT);
    		headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
    		headerStyle.setFont(headerFont);
            
    		// write headers along left column
			int startRowNum = FIRST_DATA_ROW + 1;
			int rowNum = startRowNum;
			
			Row row = sheet.createRow(rowNum++);
			writeCell(wb, sheet, row, 0, "Item", headerStyle);
			
			List<IdNamed> idNamedList = results.getItemList();
			for (IdNamed idNamed : idNamedList) {
				row = sheet.createRow(rowNum++);
				writeCell(wb, sheet, row, 0, idNamed.getName(), headerStyle);
            }
			row = sheet.createRow(rowNum);
			writeCell(wb, sheet, row, 0, "TOTAL", headerStyle);
            
            int totalOfficers = results.getOfficerList().size();
            
			Map<String, OfficerCountDTO> officerCounts = results.getOfficerCounts();

            int col = 2;
			if (results != null && results.getOfficerCounts() != null) {
				
				List<Officer> officerList = results.getOfficerList();
				for (Officer officer : officerList) {
					
					rowNum = 0;
					
					writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, officer.getLastName().toUpperCase(), headerStyle);
					
					for (IdNamed idNamed : idNamedList) {
						
						String officerItemKey = officer.getId() + ":" + idNamed.getId();
						OfficerCountDTO resValue = officerCounts.get(officerItemKey);
						
						if (resValue != null) {
							Long officerItemTotal = resValue.getOfficerItemTotal();
							if (officerItemTotal != null && officerItemTotal.longValue() > 0) {
								Long officerTotal = resValue.getOfficerTotal();
								
								// write officer total (at the bottom of this column)
								writeCell(wb, sheet, sheet.getRow(startRowNum + idNamedList.size() + 1), col, officerTotal, headerStyle);
								
								double pctOfficerTotal = officerItemTotal.doubleValue() / resValue.getOfficerTotal();
								double pctItemTotal = officerItemTotal.doubleValue() / resValue.getItemTotal();
								
								String pctOfficerTotalStr = formatPct(pctOfficerTotal);
								String pctItemTotalStr = formatPct(pctItemTotal);
								
								String cellVal = officerItemTotal + " (" + pctOfficerTotalStr + ") (" + pctItemTotalStr + ")";
								
								writeCell(wb, sheet, sheet.getRow(startRowNum + rowNum++), col, cellVal, style);
							}
						} else {
							rowNum++;
						}
						
					}
					
					col++;
				}
				
			}
			
			// write totals
			
			long totalTotal = 0;
			
			int totalRowStart = 0;
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, "TOTAL (" + totalOfficers + ")", headerStyle);
			for (IdNamed idNamed : idNamedList) {
				String totalItemKey = "0:" + idNamed.getId();
				OfficerCountDTO totalValue = officerCounts.get(totalItemKey);
				if (totalValue != null && totalValue.getItemTotal() != null) {
					long itemTotal = totalValue.getItemTotal().longValue();
					totalTotal += itemTotal;
					writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, itemTotal, headerStyle);
				} else {
					writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, 0, headerStyle);
				}
			}
			writeCell(wb, sheet, sheet.getRow(startRowNum + totalRowStart++), col, totalTotal, headerStyle);


			
			wb.write(bos);
			bos.close();
			
			
//			for (int i = 0; i < col; i++) {
////				sheet.autoSizeColumn(i);
//				sheet.setColumnWidth(i, 150*256);
//			}
            
		} catch (Exception e) {
			log.error("error with PatrolActivityReport", e);
		}
    	
		return bos;
	}

	@Override
	protected String getReportFileName() {
		if (reportType == 0) {
			return "officer_detail_callcodes_" + getReportDate();
		}
		return "officer_detail_categories_" + getReportDate();
	}

	private String formatPct(double pct) {
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMaximumFractionDigits(1);
		return nf.format(pct);
	}

	public List<Officer> getAvailableOfficers() {
		return availableOfficers;
	}

	public void setAvailableOfficers(List<Officer> availableOfficers) {
		this.availableOfficers = availableOfficers;
	}

	public List<Integer> getOfficerIdList() {
		return officerIdList;
	}

	public void setOfficerIdList(List<Integer> officerIdList) {
		this.officerIdList = officerIdList;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getReportType() {
		return reportType;
	}

	public void setReportType(int reportType) {
		this.reportType = reportType;
	}

	public OfficerListCountListDTO getResults() {
		return results;
	}

	public void setResults(OfficerListCountListDTO results) {
		this.results = results;
	}

}
