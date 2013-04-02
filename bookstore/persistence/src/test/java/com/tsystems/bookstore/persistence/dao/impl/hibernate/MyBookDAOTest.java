package com.tsystems.bookstore.persistence.dao.impl.hibernate;

import java.util.List;

import org.hibernate.HibernateException;

//import com.tsystems.bookstore.persistence.dao.BookDAO;
//import com.tsystems.bookstore.persistence.dao.impl.BookDAOImpl;

import com.tsystems.bookstore.persistence.dao.MyBookDAO;
import com.tsystems.bookstore.persistence.dao.impl.hibernate.MyBookDAOImpl;

import com.tsystems.bookstore.persistence.entity.Book;
import com.tsystems.bookstore.persistence.utils.HibernateUtils;

/**
 * 
 * @author Sergey Kozlov
 *
 */
public class MyBookDAOTest {

	private static Book generateDummyBook(){
		Book book = new Book();
		book.setId(999);
		book.setTitle("Hibernate in Action");
		book.setIsbn("978-1932394153");
		return book;
	}
	
	private static MyBookDAO bookDAO = new MyBookDAOImpl();
	
	public static void main(String...strings ) {
		
		//Creating a dummyBook
		try {
			HibernateUtils.beginTransaction();

			Book dummyBook = generateDummyBook();
			bookDAO.save(dummyBook);
			HibernateUtils.commitTransaction();	
 		} catch (HibernateException e) {
			HibernateUtils.rollbackTransaction();
		} finally {
			//Close session
			HibernateUtils.closeSession();			
		}

		//Test MyBookDAOImpl findByTitle
		try {
			HibernateUtils.beginTransaction();

			Book dummyBook = generateDummyBook();
			
			//bookDAO.delete(dummyBook);
			
			//Test MyBookDAOImpl findByTitle
			Book book1 = bookDAO.findByTitle("Hibernate in Action");
			if (book1 != null) {
				System.out.println("findByTitle succeded");
				System.out.println(book1.getId() + " " + book1.getTitle() + " " + book1.getIsbn());
			} else {
				//Add dummy book
				//bookDAO.addBook(dummyBook);	
				System.out.println("findByTitle failed");
			}
			
			/*
			//Test MyBookDAOImpl changeTitle
			bookDAO.changeTitle("Hibernate in Action","Hibernate in Action is cool");
			if ((bookDAO.findByTitle("Hibernate in Action is cool") != null) 
					&& (bookDAO.findByTitle("Hibernate in Action") == null)) {
				System.out.println("Title has been successfully changed");
			}
			else {
				System.out.println("Changing title failed");
			}
			
			//Test MyBookDAOImpl deleteByTitle
			bookDAO.deletebyTitle("Hibernate in Action is cool");
			if (bookDAO.findByTitle("Hibernate in Action is cool") == null) {
				System.out.println("Book has been successfully deleted by title");
			}
			else {
				System.out.println("Deleting by title failed");
			}
			*/
			
			HibernateUtils.commitTransaction();	
 		} catch (HibernateException e) {
			HibernateUtils.rollbackTransaction();
		} finally {
			//Close session
			HibernateUtils.closeSession();			
		}
		
		
		//Changing Title
		try {
			HibernateUtils.beginTransaction();

			//Book dummyBook = generateDummyBook();
			bookDAO.changeTitle("Hibernate in Action","Hibernate in Action is cool");
			
			HibernateUtils.commitTransaction();	
 		} catch (HibernateException e) {
			HibernateUtils.rollbackTransaction();
		} finally {
			//Close session
			HibernateUtils.closeSession();			
		}
		
		//Testing title to be changed
		try {
			HibernateUtils.beginTransaction();

			if ((bookDAO.findByTitle("Hibernate in Action is cool") != null) 
					&& (bookDAO.findByTitle("Hibernate in Action") == null)) {
				System.out.println("Title has been successfully changed");
			}
			else {
				System.out.println("Changing title failed");
			}
			
			HibernateUtils.commitTransaction();	
 		} catch (HibernateException e) {
			HibernateUtils.rollbackTransaction();
		} finally {
			//Close session
			HibernateUtils.closeSession();			
		}
		
		
		//Deleting by Title
		try {
			HibernateUtils.beginTransaction();
			//Book dummyBook = generateDummyBook();
			bookDAO.deleteByTitle("Hibernate in Action is cool");
			HibernateUtils.commitTransaction();	
		 	} catch (HibernateException e) {
				HibernateUtils.rollbackTransaction();
		} finally {
					//Close session
				HibernateUtils.closeSession();			
		}
		
		//Testing book to be deleted by title
		try {
				HibernateUtils.beginTransaction();
				//Book dummyBook = generateDummyBook();
				if (bookDAO.findByTitle("Hibernate in Action is cool") == null) {
					System.out.println("Book has been successfully deleted by title");
				}
				else {
					System.out.println("Deleting by title failed");
				}
				HibernateUtils.commitTransaction();	
		} catch (HibernateException e) {
					HibernateUtils.rollbackTransaction();
		} finally {
					//Close session
					HibernateUtils.closeSession();			
		}
	}
}
