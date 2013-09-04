package com.westchase.file.beans;

import java.math.BigDecimal;

public class TaxRecord {

	private boolean missingRecord = false;
	private String accountNumber;
	private String jurisdiction;
	private String year;
	private String owner;
	private double acres;
	private String useCode;
	private long landValue;
	private long improvementValue;
	private long totalValue;
	private String wdExemptions;
	private long taxableValue;
	private BigDecimal assessments;
	private String certified;
	private Double totalFromHcad;
	
	public TaxRecord() {
		super();
	}

	public TaxRecord(String accountNumber, String jurisdiction, String year, String owner, double acres,
			String useCode, long landValue, long improvementValue, long totalValue, String wdExemptions,
			long taxableValue, BigDecimal assessments, String certified, Double totalFromHcad) {
		super();
		setAccountNumber(accountNumber);
		setJurisdiction(jurisdiction);
		setYear(year);
		setOwner(owner);
		setAcres(acres);
		setUseCode(useCode);
		setLandValue(landValue);
		setImprovementValue(improvementValue);
		setTotalValue(totalValue);
		setWdExemptions(wdExemptions);
		setTaxableValue(taxableValue);
		setAssessments(assessments);
		setCertified(certified);
		setTotalFromHcad(totalFromHcad);
	}
	
	public TaxRecord(String accountNumber, Double totalFromHcad) {
		super();
		setAccountNumber(accountNumber);
		setTotalFromHcad(totalFromHcad);
		setMissingRecord(true);
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public double getAcres() {
		return acres;
	}

	public void setAcres(double acres) {
		this.acres = acres;
	}

	public String getUseCode() {
		return useCode;
	}

	public void setUseCode(String useCode) {
		this.useCode = useCode;
	}

	public long getLandValue() {
		return landValue;
	}

	public void setLandValue(long landValue) {
		this.landValue = landValue;
	}

	public long getImprovementValue() {
		return improvementValue;
	}

	public void setImprovementValue(long improvementValue) {
		this.improvementValue = improvementValue;
	}

	public long getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(long totalValue) {
		this.totalValue = totalValue;
	}

	public String getWdExemptions() {
		return wdExemptions;
	}

	public void setWdExemptions(String wdExemptions) {
		this.wdExemptions = wdExemptions;
	}

	public long getTaxableValue() {
		return taxableValue;
	}

	public void setTaxableValue(long taxableValue) {
		this.taxableValue = taxableValue;
	}

	public BigDecimal getAssessments() {
		return assessments;
	}

	public void setAssessments(BigDecimal assessments) {
		this.assessments = assessments;
	}

	public String getCertified() {
		return certified;
	}

	public void setCertified(String certified) {
		this.certified = certified;
	}

	public Double getTotalFromHcad() {
		return totalFromHcad;
	}

	public void setTotalFromHcad(Double totalFromHcad) {
		this.totalFromHcad = totalFromHcad;
	}

	public boolean isMissingRecord() {
		return missingRecord;
	}

	public void setMissingRecord(boolean missingRecord) {
		this.missingRecord = missingRecord;
	}

}
