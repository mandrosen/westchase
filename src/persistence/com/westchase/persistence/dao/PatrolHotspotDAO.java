package com.westchase.persistence.dao;

import java.util.List;

import com.westchase.persistence.model.PatrolHotspot;

public class PatrolHotspotDAO extends BaseDAO<PatrolHotspot> {

	public PatrolHotspotDAO() {
		super();
	}
	
	public List<PatrolHotspot> listHotspots(boolean eastWest) {
		List<PatrolHotspot> hotspots = null;
		String query = "select ph from PatrolHotspot ph where ph.eastWest = :eastWest order by ph.name";
		try {
			hotspots = getSession().createQuery(query).setParameter("eastWest", new Boolean(eastWest)).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return hotspots;
	}
	
//	public List<PatrolHotspot> listEastHotspots() {
//		return null;
//	}
//	
//	public List<PatrolHotspot> listWestHotspots() {
//		return null;
//	}

}
