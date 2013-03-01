package com.westchase.persistence.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.westchase.persistence.model.HpdNewsRelease;

/**
 * @author marc
 *
 */
public class HpdNewsReleaseDAO extends BaseDAO<HpdNewsRelease> {

	public boolean alreadyFound(Date date, String title) {
		boolean found = false;
		if (date != null && StringUtils.isNotBlank(title)) {
			String query = "select h.foundDate from HpdNewsRelease h where h.releaseDate = :rdate and h.title = :title";
			try {
				List results = getSession().createQuery(query).setParameter("rdate", date).setParameter("title", title).list();
				if (results != null && !results.isEmpty()) {
					found = true;
				}
			} catch (Exception e) {
				log.warn("", e);
			}
		}
		return found;
	}
	

}
