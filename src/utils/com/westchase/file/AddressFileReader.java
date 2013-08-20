package com.westchase.file;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.westchase.file.beans.AddressRecord;

/**
 * @author marc
 *
 */
public class AddressFileReader extends AbstractHcadFileReader {
	
	private static final String MASTER_INDICATOR = "1";
	
	private Set<String> exempts = new HashSet<String>();
	
	private boolean readExemptionFile(List<String> exemptionLineList) {
		if (exemptionLineList == null || exemptionLineList.size() == 1) return false;
		
		// drop the first line because it contains the headers
		List<String> lineList = exemptionLineList.subList(1, exemptionLineList.size());
		
		for (String line : lineList) {
			exempts.add(StringUtils.substring(line, 0, 13));
		}
		
		return true;
	}
	
	private List<AddressRecord> getAddressRecordList(List<String> addressLineList) {
		if (addressLineList == null || addressLineList.size() == 1) return null;
		
		// drop the first line because it contains the headers
		List<String> lineList = addressLineList.subList(1, addressLineList.size());
		
		List<AddressRecord> addressRecordList = new ArrayList<AddressRecord>();
		
		AddressRecord addressRecord = null;
		for (String line : lineList) {
			String recordIndicator = StringUtils.substring(line, 56, 57);
			if (MASTER_INDICATOR.equals(recordIndicator)) {
				if (addressRecord != null) {
					addressRecordList.add(addressRecord);
				}
				addressRecord = new AddressRecord();
				addressRecord.setAccountNumber(StringUtils.substring(line, 0, 13));
			}
			if (addressRecord != null) {
				String lineType = StringUtils.substring(line, 15, 16);
				String value = StringUtils.trim(StringUtils.substring(line, 16, 56));
				if (AddressRecord.OWNER.equals(lineType)) {
					String currentOwner = addressRecord.getOwner();
					if (StringUtils.isBlank(currentOwner)) {
						addressRecord.setOwner(value);
					} else {
						addressRecord.setOwner(currentOwner + " " + value);
					}
				} else if (AddressRecord.MAIL_TO.equals(lineType)) {
					String currentMailTo = addressRecord.getMailTo();
					if (StringUtils.isBlank(currentMailTo)) {
						addressRecord.setMailTo(value);
					} else {
						addressRecord.setMailTo(currentMailTo + " " + value);
					}
				} else if (AddressRecord.ADDRESS.equals(lineType)) {
					if (StringUtils.isBlank(addressRecord.getAddress1())) {
						addressRecord.setAddress1(value);
					} else {
						addressRecord.setAddress2(value);
					}
				} else if (AddressRecord.CITY.equals(lineType)) {
					addressRecord.setCity(value);
				} else if (AddressRecord.STATE.equals(lineType)) {
					addressRecord.setState(value);
				} else if (AddressRecord.ZIP.equals(lineType)) {
					addressRecord.setZip(value);
				} else if (AddressRecord.FOREIGN_COUNTRY.equals(lineType)) {
					addressRecord.setForeignCountry(value);
				}
			}
		}
		addressRecordList.add(addressRecord);
		
		return addressRecordList;
	}
	
	private List<AddressRecord> removeDuplicatesAndExempts(List<AddressRecord> addressRecordList) {
		if (CollectionUtils.isEmpty(addressRecordList)) return null;
		Map<String, AddressRecord> addressRecordMap = new HashMap<String, AddressRecord>();
		for (AddressRecord addressRecord : addressRecordList) {
			if (exempts.contains(addressRecord.getAccountNumber())) continue;
			String key = getKey(addressRecord);
			if (!addressRecordMap.containsKey(key)) {
				addressRecordMap.put(key, addressRecord);
			}
		}
		List<AddressRecord> values = new ArrayList<AddressRecord>(addressRecordMap.values());
		
		return values;
	}
	
	private String getKey(AddressRecord addressRecord) {
		String address = StringUtils.trim(StringUtils.lowerCase(addressRecord.getAddress1()));
		return address;
	}

	public List<AddressRecord> readAddressFile(File addressFile, File exemptionFile) {
		List<AddressRecord> addressRecordList = null;
		try {
			readExemptionFile(IOUtils.readLines(new FileInputStream(exemptionFile)));
			addressRecordList = getAddressRecordList(IOUtils.readLines(new FileInputStream(addressFile)));
			addressRecordList = removeDuplicatesAndExempts(addressRecordList);
			Collections.sort(addressRecordList);
		} catch (Exception e) {
			log.error("", e);
		}
		return addressRecordList;
	}
	
	public static final void main(String[] args) {
//		String line0 = "011126000001101OCENTERPOINT ENERGY HOU ELE              100";
//		String line1 = "011126000001102APROPERTY TAX DEPT 38TH FLR              000";                                         
//		String line2 = "011126000001103APO BOX 1475                             000";                                         
//		String line3 = "011126000001104CHOUSTON                                 000";                                         
//		String line4 = "011126000001105STX                                      000";                                         
//		String line5 = "011126000001106Z77251-1475                              000";                                         
//		String line6 = "011126000001107B772511475759                            000";                                         
//		String line7 = "011126000001108RB020                                    000";  		
//		
//		String recType = StringUtils.substring(line0, 56, 57);
//		System.out.println(recType);
//		
//		String accountNumber = StringUtils.substring(line0, 0, 13);
//		System.out.println(accountNumber);
		
//		String name = StringUtils.substring(line0, 16, 56);
//		System.out.println(name);
		


//		String lineType = StringUtils.substring(line0, 15, 16);
//		System.out.println(lineType);
//		lineType = StringUtils.substring(line1, 15, 16);
//		System.out.println(lineType);
//		lineType = StringUtils.substring(line2, 15, 16);
//		System.out.println(lineType);
//		lineType = StringUtils.substring(line3, 15, 16);
//		System.out.println(lineType);
//		lineType = StringUtils.substring(line4, 15, 16);
//		System.out.println(lineType);
//		lineType = StringUtils.substring(line5, 15, 16);
//		System.out.println(lineType);
//		lineType = StringUtils.substring(line6, 15, 16);
//		System.out.println(lineType);
//		lineType = StringUtils.substring(line7, 15, 16);
//		System.out.println(lineType);
		
//		List<String> test = Arrays.asList("1", "2", "3", "4");
//		test = test.subList(1, test.size());
//		for (String t : test) {
//			System.out.println(t);
//		}
		
//		File addressFile = new File("C:\\Users\\marc\\work\\westchase\\hcad_files\\11RI885ADDR.txt");
//		File exemptFile = new File("C:\\Users\\marc\\work\\westchase\\hcad_files\\11RI885XMPT.txt");
//		Collection<AddressRecord> addressRecords = readAddressFile(addressFile, exemptFile);
//		int i = 0;
//		for (AddressRecord addressRecord : addressRecords) {
//			StringBuffer rec = new StringBuffer();
//			String name = StringUtils.defaultIfEmpty(StringUtils.defaultIfEmpty(addressRecord.getOwner(), addressRecord.getMailTo()), addressRecord.getAddress1());
//			
//			rec.append(i).append("\t").append(addressRecord.getAccountNumber()).append("\t").append(name).append("\t")
//				.append(addressRecord.getAddress1()).append("\t").append(addressRecord.getAddress2()).append("\t")
//				.append(addressRecord.getCity()).append("\t").append(addressRecord.getState()).append("\t")
//				.append(addressRecord.getZip());
//			System.out.println(rec.toString());
//			i++;
//		}
		String line = "0111290000067TOT88500000000176610000000000000000002011                                        0000000001                                                                                                ";
		System.out.println(StringUtils.substring(line, 0, 13));
	}
}
