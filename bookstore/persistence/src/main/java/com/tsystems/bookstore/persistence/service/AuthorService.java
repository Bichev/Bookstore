package com.tsystems.bookstore.persistence.service;

import java.math.BigDecimal;

import com.tsystems.bookstore.persistence.dao.AuthorDAO;
import com.tsystems.bookstore.persistence.dao.impl.hibernate.AuthorDAOImpl;
import com.tsystems.bookstore.persistence.entity.Author;

/**
 * @author Bobina Vlada
 * 
 */
public class AuthorService {

	AuthorDAO authorDAO = new AuthorDAOImpl();

	public void createOrUpdateAuthor(Author author) {
		authorDAO.addAuthor(author);
	}

	public void deleteAuthor(Author author) {
		authorDAO.delete(author);
	}

	public Author findAuthorByFirstname(String firstname) {
		return (Author) authorDAO.findByFirstname(firstname);
	}

	public void deleteAuthorById(BigDecimal id) {
		authorDAO.deleteById(id);
	}

	public void changeAuthorLastname(Author author, String lastname) {
		authorDAO.changeLastname(author, lastname);
	}

}
