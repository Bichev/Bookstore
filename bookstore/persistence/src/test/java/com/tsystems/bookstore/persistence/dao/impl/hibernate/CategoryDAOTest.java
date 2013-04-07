package com.tsystems.bookstore.persistence.dao.impl.hibernate;

import java.util.List;

import org.hibernate.HibernateException;

import com.tsystems.bookstore.persistence.dao.CategoryDAO;
import com.tsystems.bookstore.persistence.dao.impl.hibernate.CategoryDAOImpl;
import com.tsystems.bookstore.persistence.entity.Category;
import com.tsystems.bookstore.persistence.utils.HibernateUtils;

public class CategoryDAOTest {
	private static CategoryDAO categoryDAO = new CategoryDAOImpl();

	public static void main(String[] args) {
		try {

			HibernateUtils.beginTransaction();

			Category dummyCategory = generateDummyCategory();
			// Delete dummy category if exist
		//	categoryDAO.delete(dummyCategory);
			// Try to find dummy category
			Category category1 = categoryDAO.findByName("Detective Story");
			if (category1 != null) {
				System.out.println("\nTest findByName");
				System.out.println(category1.getId() + " "
						+ category1.getName() + " "
						+ category1.getDescription());
			} else {
				// Add dummy category
				categoryDAO.addCategory(dummyCategory);
			}

			System.out.println("\nTest findAllCategorys");
			List<Category> categories = categoryDAO.findAllCategories();
			for (Category bItem : categories) {
				// Should print in the end dummy category
				System.out.println(bItem.getName() + " "
						+ bItem.getDescription());
			}

			// Commit transaction
			HibernateUtils.commitTransaction();
		} catch (HibernateException e) {
			HibernateUtils.rollbackTransaction();
		} finally {
			// Close session
			HibernateUtils.closeSession();
		}

	}

	private static Category generateDummyCategory() {
		Category category = new Category();
		category.setId(100);
		category.setName("Detective story");
		category.setDescription("Twisted plot");
		return category;
	}

}
