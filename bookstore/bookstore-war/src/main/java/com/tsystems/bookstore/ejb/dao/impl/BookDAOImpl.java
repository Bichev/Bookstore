package com.tsystems.bookstore.ejb.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.tsystems.bookstore.ejb.dao.BookDAO;
import com.tsystems.bookstore.ejb.dao.impl.GenericDAOImpl;
import com.tsystems.bookstore.persistence.entity.Book;

@Stateful
public class BookDAOImpl extends GenericDAOImpl<Book, BigDecimal> implements BookDAO  {

	//Determine Persistence context
    @PersistenceContext(unitName="TUni")
    private EntityManager entityManager;

    //Determine transaction manager not necessary - EJB JEE6 do it 
    //EJB to provide us with declarative transaction support.
//    @Inject
//    private UserTransaction utx;
    
    @PostConstruct
    public void initialize () {
        // Initialize here objects which will be used
        // by the session bean
    	System.out.println("----------------------------------------------------------");
        System.out.println("BookDAOImpl initialized.");
    } 

    @PreDestroy
    public void destroyBean() {
        // Free here resources acquired by the session bean
        System.out.println("BookDAOImpl destroy.");
    	System.out.println("----------------------------------------------------------");
    } 
    
	//TODO NoResultException
	public Book findByISBN(String isbn) {
		Book book;
		try {
			Query query = entityManager.createQuery("select b from Book b where b.isbn = :isbn");
			query.setParameter("isbn", isbn);
			book = (Book) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return book;
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
	
	public void deleteBookByID(int id) {
		Book book = super.findByID(Book.class, Integer.valueOf(id));
		super.delete(book);
	}

	public Book findBookByID(int id) {
		return super.findByID(Book.class, Integer.valueOf(id));
	}

}
