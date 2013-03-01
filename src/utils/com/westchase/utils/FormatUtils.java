package com.westchase.utils;

import org.apache.commons.lang.StringUtils;

/**
 * @author marc
 */
public class FormatUtils {

	public static String formatPhoneNumber(final String str) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		String temp = StringUtils.replaceChars(str, " ().-", "");
		String res = null;
		if (temp.length() == 10) {
			res = "(" + temp.substring(0, 3) + ") " + temp.substring(3, 6) + "-" + temp.substring(6, 10);
		} else {
			// can't figure out the format
			res = str;
		}
		return res;
	}
	
}
