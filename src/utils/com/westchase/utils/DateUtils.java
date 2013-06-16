package com.westchase.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtils {

	private final static Log log = LogFactory.getLog(DateUtils.class);
	
	public static final String WEB_FORMAT = "yyyy-MM-dd";

	public static Date getDateFromWeb(String dateStr) {
		Date d = null;
		if (StringUtils.isNotBlank(dateStr)) {
			try {
				d = new SimpleDateFormat(WEB_FORMAT).parse(dateStr);
			} catch (Exception e) {
				log.error("bad date: " + dateStr, e);
			}
		}
		return d;
	}

}
