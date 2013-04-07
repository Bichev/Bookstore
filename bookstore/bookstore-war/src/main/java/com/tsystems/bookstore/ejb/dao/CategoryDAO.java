package com.tsystems.bookstore.ejb.dao;

import java.util.List;

import com.tsystems.bookstore.persistence.entity.Category;

public interface CategoryDAO extends GenericDAO<Category, Integer> {
	public Category findByName(String name);

	public Category findCategoryByID(int id);

	public List<Category> findAllCategories();

	public void deleteCategoryByID(int id);

	public void addCategory(Category category);

	public void updateCategory(Category category);

	public void deleteCategory(Category category);
}
