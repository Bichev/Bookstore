package com.tsystems.bookstore.persistence.dao.impl.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.tsystems.bookstore.persistence.dao.BookDAO;
import com.tsystems.bookstore.persistence.entity.Book;
import com.tsystems.bookstore.persistence.utils.HibernateUtils;

public class BookDAOImpl extends GenericDAOImpl<Book, BigDecimal> implements BookDAO {

	public Book findByISBN(String isbn) {
		Book book = null;
		Query query = HibernateUtils.getSession().createQuery("from Book where isbn = :isbn ");
		query.setParameter("isbn", isbn);
		book = findOne(query);
		return book;
	}

	public List<Book> findAllBooks() {
		List<Book> books = new ArrayList<Book>();
		books = findAll(Book.class);
		return books;
	}

	public void addBook(Book book) {
		save(book);
	}

	public void updateBook(Book book) {
		merge(book);
	}

}
