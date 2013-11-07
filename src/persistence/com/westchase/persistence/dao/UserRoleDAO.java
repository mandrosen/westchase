package com.westchase.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import com.westchase.persistence.model.UserRole;

public class UserRoleDAO extends BaseDAO<UserRole> {

	public UserRoleDAO() {
		super();
	}
	
	public List<Integer> listRoleIdsForUser(Integer userId) {
		List<Integer> roleIdList = new ArrayList<Integer>();
		try {
			roleIdList = getSession().createQuery("select ur.role.id from UserRole ur where ur.wcuser.id = :userId").setParameter("userId", userId).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return roleIdList;
	}
	
	public void deleteAllForUser(Integer userId) {
		String query = "delete from UserRole ur where ur.wcuser.id = :userId";
		try {
			getSession().createQuery(query).setParameter("userId", userId).executeUpdate();
		} catch (Exception e) {
			log.error("", e);
		}
	}

}
