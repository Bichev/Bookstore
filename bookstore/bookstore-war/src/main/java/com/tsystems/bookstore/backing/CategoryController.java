package com.tsystems.bookstore.backing;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.tsystems.bookstore.ejb.service.CategoryService;
import com.tsystems.bookstore.persistence.entity.Category;
@Named
//Determine Scope for Managed Bean from javax.enterprise.context package
@RequestScoped
public class CategoryController {

	@Inject
	private CategoryService categoryService;

	public String testCreateCategory() {
		System.out.println("----------------------------------------------------------");
		System.out.println("Create new Category item");
		Category dummyCategory = generateDummyCategory();
		categoryService.createOrUpdateCategory(dummyCategory);
		System.out.println("----------------------------------------------------------");
		return "";
	}

	public String testDeleteCategory() {
		System.out.println("----------------------------------------------------------");
		System.out.println("Delete Category item");
		Category categoryToDelete = generateDummyCategory();
		categoryService.deleteCategoryByID(categoryToDelete.getId());
		System.out.println("----------------------------------------------------------");
		return "";
	}

	public String testFindAllCategories() {
		System.out.println("----------------------------------------------------------");
		System.out.println("Start Finding categoriess");
		List<Category> categories = (ArrayList<Category>) categoryService.getCategories();
		for (Category category : categories) {
			System.out.println(category.getName() + " " + category.getDescription());
		}
		System.out.println("----------------------------------------------------------");
		return "";
	}

	public String testFindCategoryByName() {
		System.out.println("----------------------------------------------------------");
		System.out.println("Start Finding category by name");
		Category category = categoryService.getCategoryByName("Adventure");
		System.out.println(category.getName() + " " + category.getDescription());
		System.out.println("----------------------------------------------------------");
		return "";
	}

	public String testFindCategoryById() {
		System.out.println("----------------------------------------------------------");
		System.out.println("Start Finding category by ID");
		Category category = categoryService.getCategoryById(777);
		System.out.println(category.getName() + " " + category.getDescription());
		System.out.println("----------------------------------------------------------");
		return "";
	}

	private Category generateDummyCategory() {
		Category category = new Category();
		category.setId(777);
		category.setName("Adventure");
		category.setDescription("Adventure fiction is a genre of fiction in which an adventure,"
				+ " an exciting undertaking involving risk and physical danger,"
				+ " forms the main storyline.");
		return category;
	}
}
