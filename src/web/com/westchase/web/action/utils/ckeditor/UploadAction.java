package com.westchase.web.action.utils.ckeditor;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author marc
 * 
 */
public class UploadAction extends ActionSupport {

	private String uploadContentType;

	private String uploadFileName;

	private String CKEditorFuncNum;

	private String CKEditor;

	private String langCode;

	private File upload;

	/**
	 * @return the upload
	 */
	public File getUpload() {
		return upload;
	}

	/**
	 * @param upload
	 *            the upload to set
	 */
	public void setUpload(File upload) {
		this.upload = upload;
	}

	/**
	 * @return the cKEditorFuncNum
	 */
	public String getCKEditorFuncNum() {
		return CKEditorFuncNum;
	}

	/**
	 * @param cKEditorFuncNum
	 *            the cKEditorFuncNum to set
	 */
	public void setCKEditorFuncNum(String cKEditorFuncNum) {
		CKEditorFuncNum = cKEditorFuncNum;
	}

	/**
	 * @return the cKEditor
	 */
	public String getCKEditor() {
		return CKEditor;
	}

	/**
	 * @param cKEditor
	 *            the cKEditor to set
	 */
	public void setCKEditor(String cKEditor) {
		CKEditor = cKEditor;
	}

	/**
	 * @return the langCode
	 */
	public String getLangCode() {
		return langCode;
	}

	/**
	 * @param langCode
	 *            the langCode to set
	 */
	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	/**
	 * @return the uploadContentType
	 */
	public String getUploadContentType() {
		return uploadContentType;
	}

	/**
	 * @param uploadContentType
	 *            the uploadContentType to set
	 */
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	/**
	 * @return the uploadFileName
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}

	/**
	 * @param uploadFileName
	 *            the uploadFileName to set
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	@Override
	public String execute() throws Exception {
/*		String strPath = ServletActionContext.getServletContext().getRealPath("/uploads");
		File path = new File(strPath);
		if (!path.exists()) {
			path.mkdirs();
		}*/
		// FileImageInputStream is = new FileImageInputStream(this.upload);
		// FileImageOutputStream os = new FileImageOutputStream(new File(strPath
		// + File.separator + this.uploadFileName));
		InputStream inputStream = new FileInputStream(this.upload);
		
		FTPClient client = new FTPClient();
		client.connect("www.westchasedistrict.com");
		client.login("westchase", "pleskpass");
		client.changeWorkingDirectory("/httpdocs/app/images/email");
		client.setFileType(FTP.BINARY_FILE_TYPE);
		boolean success = client.storeFile(uploadFileName, inputStream);
//		OutputStream os = new FileOutputStream(new File(strPath + File.separator + this.uploadFileName));

/*		try {

			byte[] buffer = new byte[1024 * 1024];
			while (is.read(buffer) > 0) {
				os.write(buffer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				is.close();
			}
			if (os != null) {
				os.close();
			}
		}*/
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		// Return to the ckeditor
		if (success) {
			out.write("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction(" + this.CKEditorFuncNum
				+ ", 'http://www.westchasedistrict.com/app/images/email/" + this.uploadFileName + "', '');</script>");
		} else {
			// TODO: alert with error
		}
		return Action.NONE;
	}
}