package com.westchase.file.beans;

import java.util.Comparator;

public class TaxRecordByNameComparator implements Comparator<TaxRecord> {

	private static TaxRecordByNameComparator INSTANCE;
	
	private TaxRecordByNameComparator() {
		super();
	}
	
	public static TaxRecordByNameComparator getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new TaxRecordByNameComparator();
		}
		return INSTANCE;
	}

	@Override
	public int compare(TaxRecord o1, TaxRecord o2) {
		if (o1 == o2) {
			return 0;
		}
		if (o1 == null || o1.getOwner() == null) {
			return -1;
		}
		if (o2 == null || o2.getOwner() == null) {
			return 1;
		}
		return o1.getOwner().compareTo(o2.getOwner());
	}

}
