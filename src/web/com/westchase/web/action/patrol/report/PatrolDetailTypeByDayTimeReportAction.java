package com.westchase.web.action.patrol.report;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.westchase.ejb.PatrolService;
import com.westchase.persistence.dto.cms.PropertyDTO;
import com.westchase.persistence.dto.patrol.PatrolDetailTypeDayTimeCountDTO;
import com.westchase.persistence.model.PatrolDetailCategory;
import com.westchase.persistence.model.PatrolDetailType;
import com.westchase.utils.DateUtils;
import com.westchase.utils.ejb.ServiceLocator;
import com.westchase.web.action.report.AbstractReportAction;
import com.westchase.web.bean.KeyValue;

public class PatrolDetailTypeByDayTimeReportAction extends AbstractReportAction {

	private List<PropertyDTO> availableProperties;
	private List<PatrolDetailType> availableDetailTypes;
	private List<KeyValue> availableDays;

	private List<Integer> propertyIdList;
	private List<Integer> patrolDetailTypeIdList;
	private List<Integer> dayIdList;
	private String startDate;
	private String endDate;
	
	private int reportType;
	
	private boolean includeProperty = false;
	
	private boolean includeDay = true;
	
	private boolean includeTime = true;
	
	private List<PatrolDetailTypeDayTimeCountDTO> results;
	
	public PatrolDetailTypeByDayTimeReportAction() {
		super();
	}
	
	private void prepareLists() {
		PatrolService patrolServ = ServiceLocator.lookupPatrolService();
		if (patrolServ != null) {
			availableDetailTypes = patrolServ.listDetailTypes();
			availableProperties = patrolServ.listProperties();
		}
		availableDays = new ArrayList<KeyValue>();
		availableDays.add(new KeyValue("1", "Sunday"));
		availableDays.add(new KeyValue("2", "Monday"));
		availableDays.add(new KeyValue("3", "Tuesday"));
		availableDays.add(new KeyValue("4", "Wednesday"));
		availableDays.add(new KeyValue("5", "Thursday"));
		availableDays.add(new KeyValue("6", "Friday"));
		availableDays.add(new KeyValue("7", "Saturday"));
	}
	
	public String input() {
		prepareLists();
		return SUCCESS;
	}
	
	@Override
	public String execute() {
		results = new ArrayList<PatrolDetailTypeDayTimeCountDTO>();
		PatrolService patrolServ = ServiceLocator.lookupPatrolService();
		if (patrolServ != null) {
			prepareLists();
			
			results = patrolServ.runDetailByDayTimeReport(DateUtils.getDateFromWeb(startDate), 
					DateUtils.getDateFromWeb(endDate), includeProperty, includeDay, includeTime,
					patrolDetailTypeIdList, propertyIdList, dayIdList);
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

	@Override
	protected ByteArrayOutputStream createWorkbook() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
		try {
			Workbook wb = new XSSFWorkbook();
		    Sheet sheet = wb.createSheet("report");
			
			writeTitle(wb, sheet, "Patrol Call Codes" + getReportTypeName() + " Report");			
			
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
    		
    		Row headerRow = sheet.createRow(HEADER_ROW);
    		int col = 0;
    		writeCell(wb, sheet, headerRow, col++, "Type Name", headerStyle);
    		if (includeProperty) {
        		writeCell(wb, sheet, headerRow, col++, "Property", headerStyle);
    		}
    		if (includeDay) {
        		writeCell(wb, sheet, headerRow, col++, "Day Name", headerStyle);
    		}
    		if (includeTime) {
        		writeCell(wb, sheet, headerRow, col++, "Hour of Day", headerStyle);
    		}
    		writeCell(wb, sheet, headerRow, col++, "Type Total", headerStyle);
    		
    		if (results != null) {
				int rowNum = FIRST_DATA_ROW;
    			for (PatrolDetailTypeDayTimeCountDTO result : results) {
					Row row = sheet.createRow(rowNum);
					col = 0;
					
					writeCell(wb, sheet, row, col++, result.getTypeName(), style);
		    		if (includeProperty) {
		    			String propertyStr = "[" + StringUtils.leftPad(String.valueOf(result.getPropertyId()), 3, "0") + "] " + result.getPropertyName();
		        		writeCell(wb, sheet, row, col++, propertyStr, headerStyle);
		    		}
		    		if (includeDay) {
		    			writeCell(wb, sheet, row, col++, result.getDayName(), style);
		    		}
		    		if (includeTime) {
		    			writeCell(wb, sheet, row, col++, result.getHourOfDay(), style);
		    		}
					writeCell(wb, sheet, row, col++, result.getTypeTotal(), style);
					rowNum++;
    			}
    		}
    		
			wb.write(bos);
			bos.close();
			
		} catch (Exception e) {
			log.error("error with PatrolActivityReport", e);
		}
    	
		return bos;
	}
	
	private String getReportTypeName() {
		String name = "";
		if (includeDay || includeTime) {
			name += " by";
			String join = "";
			if (includeDay) {
				name += " Day";
				join = " and";
			}
			if (includeTime) {
				name += join + " Time";
			}
		}
		return name;
	}

	@Override
	protected String getReportFileName() {
		String fileName = "callcodes";
		if (includeDay || includeTime) {
			fileName += "_by";
			if (includeDay) {
				fileName += "day";
			}
			if (includeTime) {
				fileName += "time";
			}
		}
		return fileName + "_" + getReportDate();
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

	public boolean isIncludeDay() {
		return includeDay;
	}

	public void setIncludeDay(boolean includeDay) {
		this.includeDay = includeDay;
	}

	public boolean isIncludeTime() {
		return includeTime;
	}

	public void setIncludeTime(boolean includeTime) {
		this.includeTime = includeTime;
	}

	public List<PatrolDetailTypeDayTimeCountDTO> getResults() {
		return results;
	}

	public void setResults(List<PatrolDetailTypeDayTimeCountDTO> results) {
		this.results = results;
	}

	public List<PatrolDetailType> getAvailableDetailTypes() {
		return availableDetailTypes;
	}

	public void setAvailableDetailTypes(List<PatrolDetailType> availableDetailTypes) {
		this.availableDetailTypes = availableDetailTypes;
	}

	public List<KeyValue> getAvailableDays() {
		return availableDays;
	}

	public void setAvailableDays(List<KeyValue> availableDays) {
		this.availableDays = availableDays;
	}

	public List<Integer> getPatrolDetailTypeIdList() {
		return patrolDetailTypeIdList;
	}

	public void setPatrolDetailTypeIdList(List<Integer> patrolDetailTypeIdList) {
		this.patrolDetailTypeIdList = patrolDetailTypeIdList;
	}

	public List<Integer> getDayIdList() {
		return dayIdList;
	}

	public void setDayIdList(List<Integer> dayIdList) {
		this.dayIdList = dayIdList;
	}

	public List<PropertyDTO> getAvailableProperties() {
		return availableProperties;
	}

	public void setAvailableProperties(List<PropertyDTO> availableProperties) {
		this.availableProperties = availableProperties;
	}

	public List<Integer> getPropertyIdList() {
		return propertyIdList;
	}

	public void setPropertyIdList(List<Integer> propertyIdList) {
		this.propertyIdList = propertyIdList;
	}

	public boolean isIncludeProperty() {
		return includeProperty;
	}

	public void setIncludeProperty(boolean includeProperty) {
		this.includeProperty = includeProperty;
	}

}
