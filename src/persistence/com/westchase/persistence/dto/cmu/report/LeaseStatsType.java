package com.westchase.persistence.dto.cmu.report;

/**
 * @author marc
 *
 */
public class LeaseStatsType {

	private String name;
	private long count;
	private double sqFt;
	
	public LeaseStatsType() {
		super();
	}

	public LeaseStatsType(String name, long count, double sqFt) {
		super();
		this.name = name;
		this.count = count;
		this.sqFt = sqFt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public double getSqFt() {
		return sqFt;
	}

	public void setSqFt(double sqFt) {
		this.sqFt = sqFt;
	}

}
