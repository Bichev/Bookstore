package com.tsystems.bookstore.persistence.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.tsystems.bookstore.persistence.dao.BookDAO;
import com.tsystems.bookstore.persistence.dao.impl.jpa.BookDAOImpl;
import com.tsystems.bookstore.persistence.entity.Book;

public class BookService {

	BookDAO bookDAO = new BookDAOImpl();
	
	/**
	 * Create a new book or update an existing one
	 * 
	 * @param Book book
	 */
	public void createOrUpdateBook(Book book) {
		bookDAO.save(book);
	}
	
	/**
	 * Delete book from data store
	 * 
	 * @param Book book 
	 */
	public void deleteBook(Book book) {
		bookDAO.delete(book);
	}

	/**
	 * Find all books in database
	 * 
	 * @return ArrayList<Book> of books 
	 */
	public List<Book> getBooks() {
		return (ArrayList<Book>) bookDAO.findAllBooks();
	}

	/**
	 * Retrieve a book
	 * 
	 * @param long id identifier of the book to be retrieved
	 * 
	 * @return Book book represented by the identifier provided
	 */
	public Book getBookById(long id) {
		return (Book) bookDAO.findByID(Book.class, BigDecimal.valueOf(id));
	}
	
	/**
	 * Retrieve a book
	 * 
	 * @param String isbn of the book to be retrieved
	 * 
	 * @return Book book represented by the isbn provided
	 */
	public Book getBookByISBN(String isbn) {
		return bookDAO.findByISBN(isbn);
	}

}
