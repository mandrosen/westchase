package com.westchase.file;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractHcadFileReader {

	protected final Log log = LogFactory.getLog(getClass());

	public AbstractHcadFileReader() {
		super();
	}
	
	protected String getAddressLineType(String addressLine) {
		String lineType = StringUtils.substring(addressLine, 15, 16);
		return lineType;
	}
	
	protected String addressLineValue(String addressLine) {
		String value = StringUtils.trim(StringUtils.substring(addressLine, 16, 56));
		return value;
	}
	
	protected String getAddressLineHcad(String addressLine) {
		return StringUtils.substring(addressLine, 0, 13);
	}

}
