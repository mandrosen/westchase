package com.westchase.web.action.utils.ckeditor;

import java.io.PrintWriter;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author marc
 *
 */
public class BrowseAction extends ActionSupport {
	
	private String directory;
	
	private FTPFile[] files;
	
	private final static String BASE_DIR = "/httpdocs/app/images/email";
	
	private String file;

	public String execute() throws Exception {
		String dir = BASE_DIR;
		if (StringUtils.isNotBlank(directory)) {
			dir += "/" + directory;
		}
		
		FTPClient client = new FTPClient();
		client.connect("www.westchasedistrict.com");
		client.login("westchase", "pleskpass");
		client.changeWorkingDirectory(dir);
		this.files = client.listFiles();
		
		return SUCCESS;
	}
	
	public String select() throws Exception {
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		out.write("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction(1, 'http://www.westchasedistrict.com/app/images/email/" + this.file + "', ''); window.close();</script>");
		return Action.NONE;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public FTPFile[] getFiles() {
		return files;
	}

	public void setFiles(FTPFile[] files) {
		this.files = files;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
}
