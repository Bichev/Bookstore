package com.tsystems.bookstore.persistence.dao.impl.jpa;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.tsystems.bookstore.persistence.dao.BookDAO;
import com.tsystems.bookstore.persistence.dao.impl.jpa.GenericDAOImpl;
import com.tsystems.bookstore.persistence.entity.Book;
import com.tsystems.bookstore.persistence.utils.JPAUtil;

public class BookDAOImpl extends GenericDAOImpl<Book, BigDecimal> implements BookDAO  {

	//TODO NoResultException
	public Book findByISBN(String isbn) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		javax.persistence.Query query = entityManager.createQuery("select b from Book b where b.isbn = :isbn");
		query.setParameter("isbn", isbn);
		return (Book) query.getSingleResult();
	}

	public List<Book> findAllBooks() {
		return super.findAll(Book.class);
	}

	public void addBook(Book book) {
		super.save(book);
	}

	public void updateBook(Book book) {
		super.save(book);
	}

}
