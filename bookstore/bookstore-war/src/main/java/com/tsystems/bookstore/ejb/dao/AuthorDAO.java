package com.tsystems.bookstore.ejb.dao;

import java.math.BigDecimal;

import com.tsystems.bookstore.persistence.entity.Author;

public interface AuthorDAO extends GenericDAO<Author, BigDecimal> {

	public Author findByFirstname(String firstname);

	public void deleteById(BigDecimal id);

	public void changeLastname(Author author, String lastname);

	public void addAuthor(Author author);

}
