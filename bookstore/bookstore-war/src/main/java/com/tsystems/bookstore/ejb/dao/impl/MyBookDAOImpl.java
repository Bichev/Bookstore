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

import com.tsystems.bookstore.ejb.dao.MyBookDAO;
import com.tsystems.bookstore.ejb.dao.impl.GenericDAOImpl;
import com.tsystems.bookstore.persistence.entity.Book;
/**
 * 
 * @author Sergey Kozlov
 *
 */

@Stateful
public class MyBookDAOImpl extends GenericDAOImpl<Book, BigDecimal> implements MyBookDAO {

	@PersistenceContext(unitName="TUni")
    private EntityManager entityManager;
	
	@PostConstruct
    public void initialize () {
    	System.out.println("----------------------------------------------------------");
        System.out.println("MyBookDAOImpl has been initialized.");
	}
	
	@PreDestroy
    public void destroyBean() {
        System.out.println("MyBookDAOImpl has been destroyed.");
    	System.out.println("----------------------------------------------------------");
    }
	
	@Override
	public Book findByTitle(String title) {
		
		Book book;
		
		try {
			Query query = entityManager.createQuery("select b from Book b where b.title = :title");
			query.setParameter("title", title);
			book = (Book) query.getSingleResult();
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return book;
	}

	@Override
	public void deleteByTitle(String title) {
		
		Book book;
		book = findByTitle(title);
		
		try {
		super.delete(book);
		}
		catch (Exception e) {
			System.out.println("Book does not exist!");
		}
	}

	@Override
	public Book changeTitle(String oldTitle, String newTitle) {
		
		Book book;
		book = findByTitle(oldTitle);
		book.setTitle(newTitle);
		
		try {
		super.save(book);
		}
		catch (Exception e) {
			System.out.println("Unable to change title");
			return null;
		}
		return book;
	}

	@Override
	public void createBook(Book book) {
		
		try {
		super.save(book);
		}
		catch (Exception e) {
			System.out.println("Unable to create book");
		}
		
	}

}
