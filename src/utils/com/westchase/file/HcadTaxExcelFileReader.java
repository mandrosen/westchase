package com.westchase.file;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.westchase.file.beans.TaxRecord;

public class HcadTaxExcelFileReader extends AbstractHcadFileReader {

	private List<TaxRecord> taxRecordList;
	
	public HcadTaxExcelFileReader() {
		super();
	}

	public String readTaxExcelFile(File taxExcelFile) {
		StringBuffer fileContents = new StringBuffer();
		readTaxRecords(taxExcelFile);
		if (taxRecordList != null) {
			for (TaxRecord taxRecord : taxRecordList) {
				fileContents.append(createFlatRow(taxRecord));
			}
		}
		return fileContents.toString();
	}
	
	private String createFlatRow(TaxRecord taxRecord) {
		StringBuffer row = new StringBuffer();
		if (taxRecord != null) {
			
			row.append(StringUtils.leftPad(taxRecord.getAccountNumber(), 13, '0'));
			row.append(taxRecord.getYear());
			row.append(StringUtils.leftPad(taxRecord.getJurisdiction(), 4, '0'));
			row.append(StringUtils.leftPad(String.valueOf(taxRecord.getLandValue()), 12, '0'));
			row.append(StringUtils.leftPad(String.valueOf(taxRecord.getImprovementValue()), 12, '0'));
			row.append(StringUtils.leftPad(String.valueOf(taxRecord.getTaxableValue()), 12, '0'));
			
			row.append(IOUtils.LINE_SEPARATOR);
		}
		return row.toString();
	}
	
	private void readTaxRecords(File taxExcelFile) {
		taxRecordList = new ArrayList<TaxRecord>();
		try {
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(taxExcelFile));
			XSSFSheet sheet = wb.getSheetAt(0);
			int rowCount = sheet.getLastRowNum();
			for (int i = 1; i < rowCount; i++) {
				XSSFRow row = sheet.getRow(i);
				if (includeRow(row)) {
					taxRecordList.add(createTaxRecord(row));
				}
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}
	
	private boolean includeRow(XSSFRow row) {
		return row != null;
	}
	
	private String getCellValueString(XSSFCell cell) {
		String value = null;
		if (cell != null) {
			value = cell.getStringCellValue();
		}
		return value;
	}

	private long getCellValueLong(XSSFCell cell) {
		long value = 0;
		if (cell != null) {
			if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				value = (long) cell.getNumericCellValue();
			} else {
				value = getCellValueLongFromString(cell);
			}
		}
		return value;
	}

	private long getCellValueLongFromString(XSSFCell cell) {
		long value = 0;
		if (cell != null) {
			String valueStr = cell.getStringCellValue();
			if (StringUtils.isNotBlank(valueStr)) {
				try {
					value = Long.parseLong(valueStr);
				} catch (Exception e) {
				}
			}
		}
		return value;
	}

	private TaxRecord createTaxRecord(XSSFRow row) {
		TaxRecord taxRecord = new TaxRecord();
		taxRecord.setJurisdiction(getCellValueString(row.getCell(1)));
		taxRecord.setYear(getCellValueString(row.getCell(2)));
		taxRecord.setAccountNumber(unformatAccountNumber(getCellValueString(row.getCell(0))));
		taxRecord.setLandValue(getCellValueLong(row.getCell(6)));
		taxRecord.setImprovementValue(getCellValueLong(row.getCell(7)));
		taxRecord.setTaxableValue(getCellValueLong(row.getCell(10)));
		return taxRecord;
	}
	
	private String unformatAccountNumber(String formattedAccountNumber) {
		if (StringUtils.isBlank(formattedAccountNumber)) {
			return null;
		}
		return formattedAccountNumber.replace("-", "");
	}

	public static void main(String[] args) {
		File taxExcelFile = new File("C:\\Users\\marc\\work\\westchase\\hcad_files\\hcad_2013\\hcad_taxes_201309041249.xlsx");
		String contents = new HcadTaxExcelFileReader().readTaxExcelFile(taxExcelFile);
		System.out.println(contents);
	}

}
