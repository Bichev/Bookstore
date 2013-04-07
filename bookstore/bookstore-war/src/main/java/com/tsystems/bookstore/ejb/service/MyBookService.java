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

import com.tsystems.bookstore.ejb.dao.MyBookDAO;
import com.tsystems.bookstore.ejb.dao.impl.MyBookDAOImpl;
import com.tsystems.bookstore.persistence.entity.Book;

@Stateful
public class MyBookService {

	//Injecting BookDAO with BookDAOImpl by CDI
	@Inject
	MyBookDAO bookDAO;
	
    @PostConstruct
    public void initialize () {
    	System.out.println("----------------------------------------------------------");
        System.out.println("MyBookService Session Bean initialized.");
    } 

    @PreDestroy
    public void destroyBean() {
        System.out.println("MyBookService Session Bean destroy.");
    	System.out.println("----------------------------------------------------------");        
    } 

    public void createBook(Book book) {
		bookDAO.createBook(book);
	}
    
	public void deleteBookByTitle(String title) {
		bookDAO.deleteByTitle(title);
	}

	public Book getBookByTitle(String title) {	
		return bookDAO.findByTitle(title);	
	}
	
	public Book changeBookTitle(String oldTitle,String newTitle) {	
		return bookDAO.changeTitle(oldTitle, newTitle);	
	}

}