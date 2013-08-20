package com.westchase.web.action.hcad;

import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.westchase.web.action.AbstractWestchaseAction;

public abstract class AbstractHcadFileAction extends AbstractWestchaseAction {

	private InputStream excelStream;
	private String fileName;
	private int contentLength;

	public AbstractHcadFileAction() {
		super();
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

	protected void writeCell(Sheet sheet, CellStyle style, Row row, int col, double value) {
		try {
			Cell cell = row.createCell(col);
			cell.setCellStyle(style);
			cell.setCellValue(value);
		} catch (Exception e) {
			log.error("unable to write " + value + " to cell", e);
		}
	}

	protected void writeCell(Sheet sheet, CellStyle style, Row row, int col, long value) {
		try {
			Cell cell = row.createCell(col);
			cell.setCellStyle(style);
			cell.setCellValue(value);
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

}
