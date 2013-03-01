package com.westchase.hibernate.dialect;

import org.hibernate.Hibernate;
import org.hibernate.dialect.function.SQLFunctionTemplate;

/**
 * @author marc
 * 
 */
public class MySQLDialect extends org.hibernate.dialect.MySQLDialect {

	public MySQLDialect() {
		super();
		registerFunction("date_add_interval", new SQLFunctionTemplate(Hibernate.DATE, "date_add(?1, INTERVAL ?2 ?3)"));
		registerFunction("cast_as_int", new SQLFunctionTemplate(Hibernate.INTEGER, "cast(?1 as UNSIGNED)"));
		registerFunction("group_concat", new SQLFunctionTemplate(Hibernate.STRING, "group_concat(?1)"));
		
		registerFunction("timestampdiff", new SQLFunctionTemplate(Hibernate.LONG, "timestampdiff(?1, ?2, ?3)"));
	}
}