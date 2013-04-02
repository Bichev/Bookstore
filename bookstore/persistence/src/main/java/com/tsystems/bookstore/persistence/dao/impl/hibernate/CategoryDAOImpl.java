package com.tsystems.bookstore.persistence.dao.impl;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.tsystems.bookstore.persistence.dao.CategoryDAO;
import com.tsystems.bookstore.persistence.dao.impl.hibernate.GenericDAOImpl;
import com.tsystems.bookstore.persistence.entity.Category;
import com.tsystems.bookstore.persistence.utils.HibernateUtils;

public class CategoryDAOImpl extends GenericDAOImpl<Category, BigDecimal>
		implements CategoryDAO {
	
	public Category findByName(String name) {
		Category category = null;
		Query query = HibernateUtils.getSession().createQuery("from Category where name = :name ");
		query.setParameter("name", name);
		category = findOne(query);
		return category;
	}

	public List<Category> findAllCategories() {
		List<Category> categories = new ArrayList<Category>();
		categories = findAll(Category.class);
		return categories;
	}

	public void addCategory(Category category) {
		save(category);
	}

	public void updateCategory(Category category) {
		merge(category);
	}

}
