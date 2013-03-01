package com.westchase.ejb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.apache.commons.lang.StringUtils;

import com.westchase.jobs.beans.NewsInfo;
import com.westchase.persistence.dao.HpdNewsReleaseDAO;
import com.westchase.persistence.model.HpdNewsRelease;

/**
 * @author marc
 * 
 */
@Stateless
@Remote(HpdNewsService.class)
public class HpdNewsServiceBean implements HpdNewsService {
	private final DateFormat DATE_FORMAT = new SimpleDateFormat("m-d-yy");

	@Override
	public List<HpdNewsRelease> findNewReleases(List<NewsInfo> newsInfos) {
		List<HpdNewsRelease> releases = new ArrayList<HpdNewsRelease>();
		if (newsInfos != null && !newsInfos.isEmpty()) {
			HpdNewsReleaseDAO dao = new HpdNewsReleaseDAO();
			for (NewsInfo newsInfo : newsInfos) {
				if (StringUtils.isNotBlank(newsInfo.getDateStr()) && StringUtils.isNotBlank(newsInfo.getTitle())) {
					Date date = createDate(newsInfo.getDateStr());
					if (date != null) {
						if (!dao.alreadyFound(date, newsInfo.getTitle())) {
							HpdNewsRelease release = new HpdNewsRelease();
							release.setReleaseDate(date);
							release.setFoundDate(new Date());
							release.setTitle(newsInfo.getTitle());
							release.setUrl(newsInfo.getUrl());
							releases.add(release);
						}
					}
				}
			}
		}
		return releases;
	}

	@Override
	public void saveReleases(List<HpdNewsRelease> releases) {
		if (releases != null && !releases.isEmpty()) {
			HpdNewsReleaseDAO dao = new HpdNewsReleaseDAO();
			for (HpdNewsRelease release : releases) {
				dao.save(release);
			}
		}
	}

	private final Date createDate(String dateStr) {
		Date d = null;
		try {
			d = DATE_FORMAT.parse(dateStr);
		} catch (Exception e) {
		}
		return d;
	}
}
