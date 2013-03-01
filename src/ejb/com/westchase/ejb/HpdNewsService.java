package com.westchase.ejb;

import java.util.List;

import javax.ejb.Remote;

import com.westchase.jobs.beans.NewsInfo;
import com.westchase.persistence.model.HpdNewsRelease;

/**
 * @author marc
 *
 */
@Remote
public interface HpdNewsService {

	List<HpdNewsRelease> findNewReleases(List<NewsInfo> newsInfos);
	void saveReleases(List<HpdNewsRelease> releases);

}
