package com.tsystems.bookstore.ejb.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.tsystems.bookstore.ejb.dao.BookDAO;
import com.tsystems.bookstore.ejb.dao.impl.BookDAOImpl;
import com.tsystems.bookstore.persistence.entity.Book;

@Stateful
public class BookService {

	//Injecting BookDAO with BookDAOImpl by CDI
	@Inject
	BookDAO bookDAO;
	
    @PostConstruct
    public void initialize () {
        // Initialize here objects which will be used
        // by the session bean
    	System.out.println("----------------------------------------------------------");
        System.out.println("BookService Session Bean initialized.");
    } 

    @PreDestroy
    public void destroyBean() {
        // Free here resources acquired by the session bean
        System.out.println("BookService Session Bean destroy.");
    	System.out.println("----------------------------------------------------------");        
    } 

	/**
	 * Create a new book or update an existing one
	 * 
	 * @param Book book
	 */
	public void createOrUpdateBook(Book book) {
		bookDAO.addBook(book);
	}
	
	/**
	 * Delete book from data store
	 * 
	 * @param Book book 
	 */
	public void deleteBookByID(int id) {
		bookDAO.deleteBookByID(id);
	}

	/**
	 * Find all books in database
	 * 
	 * @return ArrayList<Book> of books 
	 */
	public List<Book> getBooks() {
		return bookDAO.findAllBooks();
	}

	/**
	 * Retrieve a book
	 * 
	 * @param long id identifier of the book to be retrieved
	 * 
	 * @return Book book represented by the identifier provided
	 */
	public Book getBookById(int id) {	
		return bookDAO.findBookByID(id);	
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
