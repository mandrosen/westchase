package com.westchase.web.servlet.jasper;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet program to connect to a database and to view a jasper report (.jrxml)
 * 
 * @author Oguzhan Topsakal
 * @since 28 March 2006
 * 
 *        Required jar files to run this class: 1. jasperreports-1.2.0.jar
 *        2.classes12.jar (for Oracle JDBC connection)
 *        3.commons-beanutils-1.5.jar 4. commons-collections-2.1.jar
 *        5.commons-digester-1.7.jar 6. commons-logging-1.0.2.jar
 *        7.commons-fileupload-1.1.jar 8. commons-io-1.2.jar 9. servlet-api.jar
 * 
 */

public class JasperServlet extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * Destroys the servlet.
	 */

	public void destroy() {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public void processRequest(HttpServletRequest req, HttpServletResponse res) {

		/*
		 * try{ }catch(Exception e){
		 * System.out.println("error res.getWriter()"); }
		 */

		try {

			// Check that we have a file upload request
			boolean isMultipart = FileUpload.isMultipartContent(req);

			if (isMultipart) {
				// Create a factory for disk-based file items
				FileItemFactory factory = new DiskFileItemFactory();

				// Create a new file upload handler
				ServletFileUpload upload = new ServletFileUpload(factory);

				// Parse the request
				List /* FileItem */items = upload.parseRequest(req);

				// Process the uploaded items
				Iterator iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();

					/*
					 * String fieldName = item.getFieldName(); String fileName =
					 * item.getName(); String contentType =
					 * item.getContentType(); boolean isInMemory =
					 * item.isInMemory(); long sizeInBytes = item.getSize();
					 */

					InputStream uploadedStream = item.getInputStream();

					/*
					 * out.println("Fiel field - fieldName" + ": " + fieldName);
					 * out.println("- fileName" + ": " + fileName);
					 * out.println("- contentType" + ": " + contentType);
					 * out.println("- sizeInBytes" + ": " + sizeInBytes);
					 */

					JasperPrint jasperPrint = returnReportPrint(uploadedStream);

					// res.setContentType("text/html");
					PrintWriter out = res.getWriter();

					JRHtmlExporter exporter = new JRHtmlExporter();
					exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);

					exporter.exportReport();

					out.close();
				}
			} else {
				System.out.println("No Multipart ");
			}

		} catch (Exception e) {
			System.out.println("Error " + e.getMessage());
		}

	}

	/**
	 * Takes 4 parameters: databaseName, userName, password, inputstream and
	 * connects to the database and prepares the report.
	 * 
	 * @param databaseName
	 *            holds database name,
	 * @param userName
	 *            holds user name
	 * @param password
	 *            holds password to connect the database,
	 * @param inputstream
	 *            holds the stream of the Jasper Report file (.jrxml)
	 * @return jasperReport holds the jasperReport variable that will be used to
	 *         export an HTML report
	 */
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

}
