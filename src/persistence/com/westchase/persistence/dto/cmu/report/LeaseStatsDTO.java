package com.westchase.persistence.dto.cmu.report;

import java.util.List;

/**
 * @author marc
 *
 */
public class LeaseStatsDTO {
	
	private List<LeaseStatsType> transTypeStats;
	private List<LeaseStatsType> businessTypeStats;
	
	public LeaseStatsDTO() {
		super();
	}

	public List<LeaseStatsType> getTransTypeStats() {
		return transTypeStats;
	}

	public void setTransTypeStats(List<LeaseStatsType> transTypeStats) {
		this.transTypeStats = transTypeStats;
	}

	public List<LeaseStatsType> getBusinessTypeStats() {
		return businessTypeStats;
	}

	public void setBusinessTypeStats(List<LeaseStatsType> businessTypeStats) {
		this.businessTypeStats = businessTypeStats;
	}

}
