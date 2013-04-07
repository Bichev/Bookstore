package com.tsystems.bookstore.ejb.dao.impl;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.CreateException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.tsystems.bookstore.ejb.dao.AuthorDAO;
import com.tsystems.bookstore.persistence.entity.Author;
import com.tsystems.bookstore.ejb.dao.impl.GenericDAOImpl;

@Stateful
public class AuthorDAOImpl extends GenericDAOImpl<Author, BigDecimal> implements
		AuthorDAO {

	@PersistenceContext(unitName = "TUni")
	private EntityManager entityManager;

	@PostConstruct
	public void initialize() {
		// Initialize here objects which will be used
		// by the session bean
		System.out
				.println("----------------------------------------------------------");
		System.out.println("AuthorDAOImpl initialized.");
	}

	@PreDestroy
	public void destroyBean() {
		// Free here session bean resources
		System.out.println("AuthorDAOImpl destroyed.");
		System.out
				.println("----------------------------------------------------------");
	}

	public Author findByFirstname(String firstname) {

		Author author = null;
		try {
			Query q = entityManager
					.createQuery("select a from Author a where a.firstname = :firstname");
			q.setParameter("firstname", firstname);
			author = (Author) q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return author;
	}

	public void deleteAuthorById(int id) {
		Author author = super.findByID(Author.class, Integer.valueOf(id));
		super.delete(author);

	}

	public void changeLastname(Author author, String lastname) {
		author.setLastname(lastname);
		super.merge(author);

	}

	public void addAuthor(Author author) {
		super.save(author);

	}

}
