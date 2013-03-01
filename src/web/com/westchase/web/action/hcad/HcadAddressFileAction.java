package com.westchase.web.action.hcad;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.westchase.file.AddressFileReader;
import com.westchase.file.beans.AddressRecord;
import com.westchase.web.action.AbstractWestchaseAction;

public class HcadAddressFileAction extends AbstractWestchaseAction {

	private InputStream excelStream;
	private String fileName;
	private int contentLength;
	
	private File addressFile;
	private File exemptionFile;
	
	public HcadAddressFileAction() {
		super();
	}
	
	public String init() {
		return SUCCESS;
	}
	
	public String generate() {
		List<AddressRecord> addressRecordList = AddressFileReader.readAddressFile(getAddressFile(), getExemptionFile());
		ByteArrayOutputStream bos = createWorkbook(addressRecordList);
		setExcelStream(new ByteArrayInputStream(bos.toByteArray()));
		setFileName("hcad_addresses_" + new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + ".xlsx");
		setContentLength(bos.size());
		return "excel";
	}

	public File getAddressFile() {
		return addressFile;
	}

	public void setAddressFile(File addressFile) {
		this.addressFile = addressFile;
	}

	public File getExemptionFile() {
		return exemptionFile;
	}

	public void setExemptionFile(File exemptionFile) {
		this.exemptionFile = exemptionFile;
	}

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getContentLength() {
		return contentLength;
	}

	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}

	protected void writeCell(Sheet sheet, CellStyle style, Row row, int col, String value) {
		try {
			Cell cell = row.createCell(col);
			cell.setCellStyle(style);
			if (StringUtils.isNotBlank(value)) {
				cell.setCellValue(value);
			} else {
				cell.setCellValue(StringUtils.EMPTY);
			}
		} catch (Exception e) {
			log.error("unable to write " + value + " to cell", e);
		}
	}
	protected void writeHeaders(Sheet sheet, CellStyle style, int rowNum, String[] headers) {
		Row row = sheet.createRow(rowNum);
		for (int i = 0; i < headers.length; i++) {
			writeCell(sheet, style, row, i, headers[i]);
		}
	}
	
	protected ByteArrayOutputStream createWorkbook(List<AddressRecord> addressRecordList) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
		try {
			Workbook wb = new XSSFWorkbook();
		    Sheet sheet = wb.createSheet("hcad addresses");
		    
		    // Background colour for Cells
			CellStyle style = wb.createCellStyle();
			style.setFillBackgroundColor(IndexedColors.WHITE.getIndex());

			// Colour & Font for the Text
			Font font = wb.createFont();
			font.setFontName("Arial");
			font.setFontHeightInPoints((short) 10);
			style.setFont(font);
			

            String[] headers = { "Account#", "Name", "Address 1", "Address 2", "City", "State", "Zip", "Notes" };
            writeHeaders(sheet, style, 0, headers);
            
            List<String> foreignRecords = new ArrayList<String>();
            int rowNum = 1;
            Row firstRow = sheet.createRow(1);
    		for (AddressRecord addressRecord : addressRecordList) {
    			
    			Row row = rowNum == 1 ? firstRow : sheet.createRow(rowNum);
    			int col = 0;
    			writeCell(sheet, style, row, col++, addressRecord.getAccountNumber());
    			writeCell(sheet, style, row, col++, addressRecord.getName());
    			writeCell(sheet, style, row, col++, addressRecord.getAddress1());
    			writeCell(sheet, style, row, col++, addressRecord.getAddress2());
    			String foreignCountry = addressRecord.getForeignCountry();
    			if (StringUtils.isNotBlank(foreignCountry)) {
    				writeCell(sheet, style, row, col++, foreignCountry);
    				foreignRecords.add(String.valueOf(rowNum));
    			} else {
    				writeCell(sheet, style, row, col++, addressRecord.getCity());
    				writeCell(sheet, style, row, col++, addressRecord.getState());
    				writeCell(sheet, style, row, col++, addressRecord.getZip());
    			}
    			
    			rowNum++;
    		}
    		
    		int count = rowNum - 1;
    		if (!foreignRecords.isEmpty()) {
    			writeCell(sheet, style, firstRow, 7, count + " Records. Records " + StringUtils.join(foreignRecords, ", ") + " out of the US");
    		}
    		
			wb.write(bos);
			bos.close();
            
		} catch (Exception e) {
			log.error("", e);
		}
    	
		return bos;
	}

}
