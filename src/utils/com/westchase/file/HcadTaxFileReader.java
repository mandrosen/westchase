package com.westchase.file;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.westchase.file.beans.TaxRecord;

public class HcadTaxFileReader extends AbstractHcadFileReader {
	
	private Map<String, String> owners = new HashMap<String, String>();
	private Set<String> exempts = new HashSet<String>();
	private Map<String, Double> totals = new HashMap<String, Double>();
	
	private NumberFormat nfGeneral;
	private NumberFormat nfAcres;

	public HcadTaxFileReader() {
		super();
		nfGeneral = NumberFormat.getIntegerInstance();
		nfGeneral.setGroupingUsed(true);
		nfGeneral.setMinimumFractionDigits(0);
		nfGeneral.setMaximumFractionDigits(2);
		
		nfAcres = NumberFormat.getNumberInstance();
		nfAcres.setMinimumFractionDigits(4);
		nfAcres.setMaximumFractionDigits(4);
	}
	
	
	private String formatHcad(String accountNumber) {
		if (StringUtils.isBlank(accountNumber)) return "";
		String firstThree = StringUtils.substring(accountNumber, 0, 3);
		String secondThree = StringUtils.substring(accountNumber, 3, 6);
		String thirdThree = StringUtils.substring(accountNumber, 6, 9);
		String lastFour = StringUtils.substring(accountNumber, 9, 13);
		StringBuffer buf = new StringBuffer(firstThree).append("-").append(secondThree).append("-").append(thirdThree).append("-").append(lastFour);
		return buf.toString();
	}
	
	private String formatAcreValue(double value) {
		return nfAcres.format(value);
	}
	
	private String formatGeneralNumberValue(long value) {
		if (value == 0) {
			return "\"-\"";
		}
		return "\"" + nfGeneral.format(value) + "\"";
	}
	
	private String formatGeneralNumberValue(float value) {
		if (value == 0) {
			return "\"0.00\"";
		}
		return "\"" + nfGeneral.format(value) + "\"";
	}
	
	private boolean includeRow(String accountNumber) {
		return !exempts.contains(accountNumber);
	}
	
	private Double getTotalFromHcad(String formattedAccountNumber) {
		Double total = null;
		if (totals.containsKey(formattedAccountNumber)) {
			total = totals.get(formattedAccountNumber);
			
			// reset it to null, so we know which ones were not used
			//totals.put(formattedAccountNumber, null);
			totals.remove(formattedAccountNumber);
		} else {
			log.error(formattedAccountNumber + " not in file");
		}
		return total;
	}
	
	private List<TaxRecord> getTaxRecordList(List<String> taxLineList, double assessmentRate) {		
		if (taxLineList == null || taxLineList.size() == 1) return null;
		
		List<TaxRecord> taxRecordList = new ArrayList<TaxRecord>();
		
		// drop the first line because it contains the headers
		List<String> lineList = taxLineList.subList(1, taxLineList.size());
		
		for (String line : lineList) {
//			String zipCode = StringUtils.substring(line,  267, 272);
//			if (Constants.WESTCHASE_ZIP_CODES.contains(zipCode)) {
			
			String accountNumber = StringUtils.substring(line, 2, 15);
			
			//if (includeRow(accountNumber)) {
			if (true) {
				String jur = StringUtils.substring(line, 15, 18);
				String year = StringUtils.substring(line, 18, 22);
				String owner = owners.get(accountNumber);
				String acres = StringUtils.substring(line, 272, 281);
				int acresInt = Integer.parseInt(acres);
				double acresDouble = acresInt / 10000.0d;
				String useCode = StringUtils.substring(line, 281, 283);
				String landValue = StringUtils.substring(line, 364, 376);
				long landValueLong = Long.parseLong(landValue);
				String improvementValue = StringUtils.substring(line, 400, 412);
				long improvementValueLong = Long.parseLong(improvementValue);
				long totalValue = landValueLong + improvementValueLong; 
				String wdExemptions = ""; // TODO: unknown
				String taxableValue = StringUtils.substring(line, 472, 484);
				long taxableValueLong = Long.parseLong(taxableValue);
				BigDecimal assessments = new BigDecimal(taxableValue).multiply(new BigDecimal(assessmentRate / 100));
				String certified = StringUtils.substring(line,  999, 1000);
				
				String formattedAccountNum = formatHcad(accountNumber);
				
				Double totalFromHcad = getTotalFromHcad(formattedAccountNum);
				
				
	
				StringBuffer lineCsv = new StringBuffer(formattedAccountNum).append(",")
						.append(jur).append(",")
						.append(year).append(",")
						.append(owner).append(",")
						.append(formatAcreValue(acresDouble)).append(",")
						.append(useCode).append(",")
						.append(formatGeneralNumberValue(landValueLong)).append(",")
						.append(formatGeneralNumberValue(improvementValueLong)).append(",")
						.append(formatGeneralNumberValue(totalValue)).append(",")
						.append(wdExemptions).append(",")
						.append(formatGeneralNumberValue(taxableValueLong)).append(",")
						.append(formatGeneralNumberValue(assessments.floatValue())).append(",")
						.append(certified).append(",").append(totalFromHcad);
				System.out.println(lineCsv);
				
				taxRecordList.add(new TaxRecord(accountNumber, jur, year, owner, acresDouble, useCode, landValueLong, improvementValueLong, totalValue, wdExemptions, taxableValueLong, assessments, certified, totalFromHcad));
			}
		}
		
		for (Map.Entry<String, Double> entry : totals.entrySet()) {
			if (entry.getValue() != null) {
				//taxRecordList.add(new TaxRecord(entry.getKey(), entry.getValue()));
				log.error("MISSING::::" + entry.getKey() + ", " + entry.getValue());
			}
		}
		return taxRecordList;
	}
	
	private boolean readAddressFile(List<String> addressLineList) {		
		if (addressLineList == null || addressLineList.size() == 1) return false;
		
		for (String addressLine : addressLineList) {
			String lineType = getAddressLineType(addressLine);
			String value = StringUtils.trim(StringUtils.substring(addressLine, 16, 56));
			if ("O".equals(lineType)) {
				String hcad = getAddressLineHcad(addressLine);
				String ownerName = owners.get(hcad);
				if (StringUtils.isNotBlank(ownerName)) {
					ownerName = ownerName + " " + value;
				} else {
					ownerName = value;
				}
				owners.put(hcad, ownerName);
			}
		}
		
		return true;
	}
	
	private boolean readExemptionFile(List<String> exemptionLineList) {	
		if (exemptionLineList == null || exemptionLineList.size() == 1) return false;

		// drop the first line because it contains the headers
		List<String> lineList = exemptionLineList.subList(1, exemptionLineList.size());

		for (String exemptionLine : lineList) {
			exempts.add(StringUtils.substring(exemptionLine, 0, 13));
		}
		
		return true;
	}


	private boolean readTotalFile(List<String> totalLineList) {	
		if (totalLineList == null || totalLineList.size() == 1) return false;

		double totalInFile = 0;

		for (String totalLine : totalLineList) {
			String[] totalArray = totalLine.split(",");
			if (totalArray.length >= 2) {
				String accountNum = StringUtils.trim(totalArray[0]);
				Double amount = null;
				try {
					amount = Double.valueOf(StringUtils.trim(totalArray[1]));
				} catch (Exception e) {
					log.error("error getting amount: " + totalLine, e);
				}
				if (StringUtils.isNotBlank(accountNum) && amount != null) {
					totals.put(accountNum, amount);
					totalInFile += amount.doubleValue();
				} else {
					log.error("BAD DATA: " + totalLine);
				}
			} else {
				log.error("BAD LINE: " + totalLine);
			}
		}
		
		log.warn("TOTAL IN FILE: " + totalInFile);
		
		return true;
	}

	public List<TaxRecord> readTaxFile(File taxFile, File addressFile, File exemptionFile, double assessmentRate, File totalFile) {
		List<TaxRecord> taxRecordList = null;
		try {
			readAddressFile(IOUtils.readLines(new FileInputStream(addressFile)));
			readExemptionFile(IOUtils.readLines(new FileInputStream(exemptionFile)));
			readTotalFile(IOUtils.readLines(new FileInputStream(totalFile)));
			taxRecordList = getTaxRecordList(IOUtils.readLines(new FileInputStream(taxFile)), assessmentRate);
//			if (orderByName) {
//				Collections.sort(taxRecordList, TaxRecordByNameComparator.getInstance());
//			}
		} catch (Exception e) {
			log.error("", e);
		}
		return taxRecordList;
	}
	
//	public static void main(String[] args) {
//		new HcadTaxFileReader().readFiles();
//	}

}
