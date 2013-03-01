package com.westchase.web.bean;

import java.io.Serializable;

/**
 * @author marc
 *
 * Class used to hold key and value combinations for html select tags
 */
public class KeyValue implements Serializable {
	private final String key;
	private final String value;
	public KeyValue(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public String getValue() {
		return value;
	}
}
