package com.tsystems.bookstore.ejb.dao.impl;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.tsystems.bookstore.ejb.dao.CategoryDAO;
import com.tsystems.bookstore.persistence.entity.Category;

@Stateful
public class CategoryDAOImpl extends GenericDAOImpl<Category, Integer> implements CategoryDAO {

	@PersistenceContext(unitName = "TUni")
	private EntityManager entityManager;

	public Category findCategoryByID(int id) {
		return super.findByID(Category.class, Integer.valueOf(id));
	}

	public Category findByName(String name) {
		Category category;
		try {
			Query query = entityManager
					.createQuery("select c from Category c where c.name = :name");
			query.setParameter("name", name);
			category = (Category) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return category;
	}

	public List<Category> findAllCategories() {
		return super.findAll(Category.class);
	}

	public void addCategory(Category category) {
		super.save(category);

	}

	public void updateCategory(Category category) {
		super.save(category);

	}

	public void deleteCategory(Category category) {
		super.delete(category);
	}

	public void deleteCategoryByID(int id) {
		Category category = super.findByID(Category.class, Integer.valueOf(id));
		super.delete(category);

	}

}
