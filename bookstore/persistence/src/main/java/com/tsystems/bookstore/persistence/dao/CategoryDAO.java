package com.tsystems.bookstore.persistence.dao;


import java.math.BigDecimal;
import java.util.List;

import com.tsystems.bookstore.persistence.entity.Category;

public interface CategoryDAO extends GenericDAO<Category, BigDecimal>{
	public Category findByName(String name);
	public List<Category> findAllCategories();
	
	public void addCategory(Category category);
	public void updateCategory(Category category);
}
