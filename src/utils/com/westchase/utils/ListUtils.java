package com.westchase.utils;

import java.util.List;

/**
 * @author marc
 *
 */
public class ListUtils {

	public static String toString(List<String> values) {
		StringBuffer str = new StringBuffer();
		if (values != null && !values.isEmpty()) {
			for (String value : values) {
				if (str.length() > 0) str.append(",");
				str.append(value);
			}
		}
		return str.toString();
	}
	
}
