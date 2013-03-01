package com.westchase.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import com.westchase.persistence.criteria.CategorySearchCriteria;
import com.westchase.persistence.model.Category;

/**
 * @author marc
 *
 */
public class CategoryDAO extends BaseDAO<Category>{

	public List<Category> findAll(CategorySearchCriteria criteria) {
		// for now, ignore the criteria max results and page number because there I always 
		// want to show all of the criteria
		List<Category> categories = new ArrayList<Category>();
		String query = "select c from Category c order by c.categoryCode";
		try {
			categories = getSession().createQuery(query).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return categories;
	}

	public List<Category> findAllNotSelected(Integer phoneBookId) {
		List<Category> categories = new ArrayList<Category>();
		String query = "select c from Category c where c.categoryCode not in (select pbc.category.categoryCode from PhoneBookCategory pbc where pbc.phoneBook.id = :phonebook) order by c.categoryCode";
		try {
			categories = getSession().createQuery(query).setParameter("phonebook", phoneBookId).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return categories;
	}

	public List<Category> findAllSelected(Integer phoneBookId) {
		List<Category> categories = new ArrayList<Category>();
		String query = "select pbc.category from PhoneBookCategory pbc where pbc.phoneBook.id = :phonebook order by pbc.category.categoryCode"; 
		try {
			categories = getSession().createQuery(query).setParameter("phonebook", phoneBookId).list();
		} catch (Exception e) {
			log.error("", e);
		}
		return categories;
	}

}
