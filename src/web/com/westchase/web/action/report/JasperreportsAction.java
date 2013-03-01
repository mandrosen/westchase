package com.westchase.web.action.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.westchase.web.action.AbstractWestchaseAction;

/**
 * @author marc
 */
public class JasperreportsAction extends AbstractWestchaseAction implements ServletResponseAware {

	
	private HttpServletResponse response;
	
	private String jrxmlFileName;
    private File upload;//The actual file
    private String uploadContentType; //The content type of the file
    private String uploadFileName; //The uploaded file name
    private String fileCaption;//The caption of the file entered by use
    
//	@Override
//	public String execute() {
//		try {
////			JasperCompileManager.compileReportToFile("jasper/labels.jrxml", "jasper/labels.jasper");
//			HttpServletRequest request = getRequest();
//			InputStream inputStream = request.getSession(true).getServletContext().getResourceAsStream(jrxmlFileName);
//			OutputStream outputStream = new FileOutputStream("jasper/custom_report.jasper");
//			JasperCompileManager.compileReportToStream(inputStream, outputStream);
//		} catch (Exception e) {
//			log.error("", e);
//		}
//		return SUCCESS;
//	}

	@Override
	public String input() {
		return SUCCESS;
	}
	
	@Override
	public String execute() {

		/*
		 * out.println("Fiel field - fieldName" + ": " + fieldName);
		 * out.println("- fileName" + ": " + fileName);
		 * out.println("- contentType" + ": " + contentType);
		 * out.println("- sizeInBytes" + ": " + sizeInBytes);
		 */
//		InputStream inputStream = getRequest().getSession(true).getServletContext().getResourceAsStream(jrxmlFileName);
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(getUpload());
		} catch (Exception e) {}
		if (inputStream != null) {
				
			JasperPrint jasperPrint = returnReportPrint(inputStream);
	
			// res.setContentType("text/html");
			try {
				PrintWriter out = response.getWriter();
		
				JRHtmlExporter exporter = new JRHtmlExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);
		
				exporter.exportReport();
			} catch (Exception e) {}
		}
		return null;

	}
	
	public JasperPrint returnReportPrint(InputStream inputStream) {
		JasperPrint jasperPrint = null;
		try {
			JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			// Connection jdbcConnection = connectDB(databaseName, userName,
			// password);
			Connection mysqlConnection = createConnection();
			jasperPrint = JasperFillManager.fillReport(jasperReport, null, mysqlConnection);
		} catch (Exception ex) {
			String connectMsg = "Could not create the report stream " + ex.getMessage() + " "
					+ ex.getLocalizedMessage();
			System.out.println(connectMsg);
		}
		return jasperPrint;
	}

	private Connection createConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection("jdbc:mysql://localhost/westchase", "root", "");
		} catch (Exception e) {
		}
		return connection;

	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getJrxmlFileName() {
		return jrxmlFileName;
	}

	public void setJrxmlFileName(String jrxmlFileName) {
		this.jrxmlFileName = jrxmlFileName;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getFileCaption() {
		return fileCaption;
	}

	public void setFileCaption(String fileCaption) {
		this.fileCaption = fileCaption;
	}
	
}
