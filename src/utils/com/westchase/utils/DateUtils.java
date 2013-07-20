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
	
	public static String formatTime(String time) {
		String timeStr = "12:00";
		if (StringUtils.isNotBlank(time) && time.length() == 4) {
			timeStr = time.substring(0, 2) + ":" + time.substring(2, 4);
		}
		return timeStr;
	}

	public static Date getDateTime(final String date, final String time) {
		Date d = null;
		if (StringUtils.isNotBlank(date) && StringUtils.isNotBlank(time)) {
			String datePart = StringUtils.substringBefore(date, "T");
			//String timePart = StringUtils.substringBeforeLast(StringUtils.substringAfter(time, "T"), "-");
			String timePart = formatTime(time);
			SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			try {
				d = dateTimeFormat.parse(datePart + "T" + timePart + ":00");
			} catch (Exception e) {
				log.error("", e);
			}
		}
		return d;
	}

}
