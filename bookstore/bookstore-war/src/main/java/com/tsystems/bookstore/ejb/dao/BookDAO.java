package com.tsystems.bookstore.ejb.dao;

import java.math.BigDecimal;
import java.util.List;

import com.tsystems.bookstore.persistence.entity.Book;

public interface BookDAO  extends GenericDAO<Book, BigDecimal> {
	
	public Book findByISBN(String isbn);
	public Book findBookByID(int id);
	public List<Book> findAllBooks();
	
	public void addBook(Book book);
	public void updateBook(Book book);
	public void deleteBookByID(int id);
	
}
