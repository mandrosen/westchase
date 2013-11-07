package com.westchase.persistence.dao;

import java.util.List;

import com.westchase.persistence.model.PatrolActivityHotspot;

public class PatrolActivityHotspotDAO extends BaseDAO<PatrolActivityHotspot> {

	public PatrolActivityHotspotDAO() {
		super();
	}
	
	public boolean deleteAllForPatrolActivity(Long patrolActivityId, boolean eastWest) {
		String query = "delete pah.* from patrol_activity_hotspot pah inner join patrol_hotspot ph on pah.hotspot_id = ph.id where pah.patrol_activity_id = :patrolActivityId and ph.east_west = :eastWest";
		try {
			getSession().createSQLQuery(query).setParameter("patrolActivityId", patrolActivityId).setParameter("eastWest", new Boolean(eastWest)).executeUpdate();
		} catch (Exception e) {
			log.error("", e);
			return false;
		}
		return true;
	}

	public List<Integer> listIdForActivity(Long patrolActivityId, boolean eastWest) {
		List<Integer> hotspotIds = null;
		String query = "select pah.patrolHotspot.id from PatrolActivityHotspot pah where pah.patrolActivity.id = :patrolActivityId and pah.patrolHotspot.eastWest = :eastWest";
		try {
			hotspotIds = getSession().createQuery(query).setParameter("patrolActivityId", patrolActivityId).setParameter("eastWest", new Boolean(eastWest)).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return hotspotIds;
	}

}
