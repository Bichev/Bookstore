package com.tsystems.bookstore.persistence.dao.impl.hibernate;

import java.math.BigDecimal;

import org.hibernate.Query;

import com.tsystems.bookstore.persistence.dao.AuthorDAO;
import com.tsystems.bookstore.persistence.entity.Author;
import com.tsystems.bookstore.persistence.utils.HibernateUtils;

public class AuthorDAOImpl extends GenericDAOImpl<Author, BigDecimal> implements
		AuthorDAO {

	public Author findByFirstname(String firstname) {
		Author author = null;
		Query query = HibernateUtils.getSession().createQuery("from Author where firstname = :firstname");
		query.setParameter("firstname", firstname);
		author = findOne(query);
		return author;

	}

	public void deleteById(BigDecimal id) {
		Author author = findByID(Author.class, id);
		delete(author);
	}

	public void changeLastname(Author author, String lastname) {
		author.setLastname(lastname);
		// update lastname which has been changed
		merge(author);
	}
	
	public void addAuthor(Author author){
		save(author);
	}

}
