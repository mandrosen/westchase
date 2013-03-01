package com.westchase.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import com.westchase.persistence.criteria.CmuQuarterSearchCriteria;
import com.westchase.persistence.model.CmuQuarter;

/**
 * @author marc
 *
 */
public class CmuQuarterDAO extends BaseDAO<CmuQuarter> {

	public List<CmuQuarter> findAll(CmuQuarterSearchCriteria criteria) {
		List<CmuQuarter> quarters = new ArrayList<CmuQuarter>();
		return quarters;
	}

	public long findAllCount(CmuQuarterSearchCriteria criteria) {
		return 0;
	}

}
