package com.tsystems.bookstore.ejb.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import javax.inject.Inject;

import com.tsystems.bookstore.ejb.dao.CategoryDAO;
import com.tsystems.bookstore.persistence.entity.Category;

@Stateful
public class CategoryService {

	// Injecting BookDAO with BookDAOImpl by CDI
	@Inject
	private CategoryDAO categoryDAO;

	@PostConstruct
	public void initialize() {
		// Initialize here objects which will be used
		// by the session bean
		System.out.println("----------------------------------------------------------");
		System.out.println("CategoryService Session Bean initialized.");
	}

	@PreDestroy
	public void destroyBean() {
		// Free here resources acquired by the session bean
		System.out.println("CategoryService Session Bean destroy.");
		System.out.println("----------------------------------------------------------");
	}

	/**
	 * Create a new category or update an existing one
	 * 
	 * @param Category
	 *            category
	 */
	public void createOrUpdateCategory(Category category) {
		categoryDAO.addCategory(category);
	}

	/**
	 * Delete category from data store
	 * 
	 * @param Category
	 *            category
	 */
	public void deleteCategoryByID(int id) {
		categoryDAO.deleteCategoryByID(id);
	}

	/**
	 * Find all categories in database
	 * 
	 * @return ArrayList<Category> of categories
	 */
	public List<Category> getCategories() {
		return categoryDAO.findAllCategories();
	}

	/**
	 * Retrieve a category
	 * 
	 * @param long id identifier of the category to be retrieved
	 * 
	 * @return Category category represented by the identifier provided
	 */
	public Category getCategoryById(int id) {
		return categoryDAO.findCategoryByID(id);
	}

	/**
	 * Retrieve a category
	 * 
	 * @param String
	 *            name of the category to be retrieved
	 * 
	 * @return Category category represented by the name provided
	 */
	public Category getCategoryByName(String name) {
		return categoryDAO.findByName(name);
	}

}
