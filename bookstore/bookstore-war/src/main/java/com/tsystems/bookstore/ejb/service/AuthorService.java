package com.tsystems.bookstore.ejb.service;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import javax.inject.Inject;

import com.tsystems.bookstore.ejb.dao.AuthorDAO;
import com.tsystems.bookstore.persistence.entity.Author;

@Stateful
public class AuthorService {

	// Injecting AuthorDAO with AuthorDAOImpl by Contexts and Dependency
	// Injection (CDI)
	@Inject
	AuthorDAO authorDAO;

	@PostConstruct
	public void initialize() {
		// Initialize here objects which will be used
		// by the session bean
		System.out
				.println("----------------------------------------------------------");
		System.out.println("AuthorService Session Bean initialized.");
	}

	@PreDestroy
	public void destroyBean() {
		// Free here resources acquired by the session bean
		System.out.println("AuthorService Session Bean destroy.");
		System.out
				.println("----------------------------------------------------------");
	}

	// Create a new author or update an existing one
	public void createOrUpdateAuthor(Author author) {
		authorDAO.addAuthor(author);
	}

	// Delete author from db
	public void deleteAuthorById(int i) {
		authorDAO.deleteAuthorById(i);

	}

	// Retrieve an author
	public Author getAuthorByFirstname(String firstname) {
		return authorDAO.findByFirstname(firstname);
	}
	
	//Change lastname for already existing author
	public void changeAuthorLastname(Author author, String lastname){
		authorDAO.changeLastname(author, lastname);
	}

}
