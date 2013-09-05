package com.westchase.web.action.hcad;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.IOUtils;

import com.westchase.file.HcadTaxExcelFileReader;

public class HcadTaxFlatFileAction extends AbstractHcadFileAction {

	private InputStream textFileStream;
	private File taxExcelFile;
	
	public HcadTaxFlatFileAction() {
		super();
	}
	
	public String init() {
		return SUCCESS;
	}
	
	public String generate() {
		String taxFlatFile = new HcadTaxExcelFileReader().readTaxExcelFile(getTaxExcelFile());
		if (taxFlatFile != null) {
			textFileStream = IOUtils.toInputStream(taxFlatFile);
			setFileName("hcad_taxes_flat_" + new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + ".txt");
			setContentLength(taxFlatFile.getBytes().length);
		}
		return "text";
	}

	public InputStream getTextFileStream() {
		return textFileStream;
	}

	public void setTextFileStream(InputStream textFileStream) {
		this.textFileStream = textFileStream;
	}

	public File getTaxExcelFile() {
		return taxExcelFile;
	}

	public void setTaxExcelFile(File taxExcelFile) {
		this.taxExcelFile = taxExcelFile;
	}

}
