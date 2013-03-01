package com.westchase.ejb;

import java.util.List;

import javax.ejb.Local;

import com.westchase.persistence.criteria.CategorySearchCriteria;
import com.westchase.persistence.model.Category;

/**
 * @author marc
 *
 */
@Local
public interface CategoryService {

	Category get(String categoryCode);

	void saveOrUpdate(Category category);

	List<Category> findAll();

	List<Category> findAll(CategorySearchCriteria criteria);

	List<Category> findAllNotSelected(Integer phoneBookId);

	List<Category> findAllSelected(Integer phoneBookId);

}
