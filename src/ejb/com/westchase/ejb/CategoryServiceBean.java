package com.westchase.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.jboss.ejb3.annotation.SecurityDomain;

import com.westchase.persistence.criteria.CategorySearchCriteria;
import com.westchase.persistence.dao.CategoryDAO;
import com.westchase.persistence.model.Category;

/**
 * @author marc
 *
 */
@Stateless
@SecurityDomain("WestchaseRealm")
@Local(CategoryService.class)
public class CategoryServiceBean implements CategoryService {

	
	public CategoryServiceBean() {
		super();
	}
	
//	public CategoryServiceBean(CategoryDAO categoryDAO) {
//		this.categoryDAO = categoryDAO;
//	}

	@Override
	public List<Category> findAll() {
		CategoryDAO dao = new CategoryDAO();
		return dao.findAll();
	}

	@Override
	public Category get(String categoryCode) {
		CategoryDAO dao = new CategoryDAO();
		return dao.get(categoryCode);
	}

	@Override
	public void saveOrUpdate(Category category) {
		CategoryDAO dao = new CategoryDAO();
		dao.saveOrUpdate(category);
	}

	@Override
	public List<Category> findAll(CategorySearchCriteria criteria) {
		CategoryDAO dao = new CategoryDAO();
		return dao.findAll(criteria);
	}

	@Override
	public List<Category> findAllNotSelected(Integer phoneBookId) {
		CategoryDAO dao = new CategoryDAO();
		return dao.findAllNotSelected(phoneBookId);
	}

	@Override
	public List<Category> findAllSelected(Integer phoneBookId) {
		CategoryDAO dao = new CategoryDAO();
		return dao.findAllSelected(phoneBookId);
	}
	
}
