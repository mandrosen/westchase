package com.westchase.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.mail.MultiPartEmail;



/**
 * @author marc
 *
 */
public class EmailUtils {
	private final static Log log = LogFactory.getLog(EmailUtils.class);
	
	public static void addAddress(MultiPartEmail email, String emailAddress, String name) {
		try {
		
			email.addTo(emailAddress, name);
		} catch (Exception e) {
			log.error("error adding address: " + emailAddress, e);
		}
		
		String addr = null;
		try {
			if (StringUtils.endsWithIgnoreCase(emailAddress, "@westchasedistrict.com")) {
				
				String user = StringUtils.substringBefore(emailAddress, "@");
				addr = "westchaseforward+" + user + "@gmail.com";
				email.addTo(addr, name);
				
			}
		} catch (Exception e) {
			log.error("error adding address: " + addr, e);
		}
	}
	
}
