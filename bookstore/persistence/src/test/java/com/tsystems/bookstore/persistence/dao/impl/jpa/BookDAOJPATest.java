package com.tsystems.bookstore.persistence.dao.impl.jpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import com.tsystems.bookstore.persistence.dao.BookDAO;
import com.tsystems.bookstore.persistence.dao.impl.jpa.BookDAOImpl;
import com.tsystems.bookstore.persistence.entity.Book;
import com.tsystems.bookstore.persistence.utils.JPAUtil;

public class BookDAOJPATest {

	private static BookDAO bookDAO = new BookDAOImpl();
	private static EntityManager entityManager = null;
	
	public static void main(String...strings) {		
		Book dummyBook = generateDummyBook();

		try {
			//Open entity manager (the same for Hibernate session)
			entityManager = JPAUtil.getEntityManager();
			
			//One session/manager - any quantity of transactions
			//Start Transaction
			entityManager.getTransaction().begin();
			
			//Test remove object
			try {
				Book bookFromDB = (Book) entityManager.getReference(Book.class, dummyBook.getId());
				
				if (bookFromDB.getIsbn() != null) {
					bookDAO.delete(bookFromDB);
				}
			} catch (EntityNotFoundException ex) {
				//TODO Try catch should be in DAO
				//Noting to do
			}
			
			//Test add object
			bookDAO.addBook(dummyBook);	
			
			//Test findByISBN
			//Try to find dummy book
			try {
				Book bookByISBN = bookDAO.findByISBN("978-1932394887");
				System.out.println("\nTest findByISBN");
				System.out.println(bookByISBN.getId() + " " + bookByISBN.getTitle() + " " + bookByISBN.getIsbn());
			} catch (NoResultException ex) {
				//TODO Try catch should be in DAO
				//Noting to do
			}
			
			//Test find all books
			System.out.println("\nTest findAllBooks");
			List<Book> books = (ArrayList<Book>) bookDAO.findAllBooks();		
			for (Book bItem : books) {
				//Should print in the end dummy book
				System.out.println(bItem.getTitle() + " " + bItem.getIsbn());
			}
		
			//Commit transaction
			entityManager.getTransaction().commit();
		} catch (PersistenceException ex) {
			if (entityManager.getTransaction().isActive()) {
				//Rollback transaction if exception
				entityManager.getTransaction().rollback();
			}
			ex.printStackTrace();
		} finally {
			//Close entity manager (the same for Hibernate session)
			entityManager.close();
		}
	}
	
	private static Book generateDummyBook(){
		Book book = new Book();
		book.setId(777);
		book.setTitle("Java Persistence with Hibernate");
		book.setIsbn("978-1932394887");
		return book;
	}
}
