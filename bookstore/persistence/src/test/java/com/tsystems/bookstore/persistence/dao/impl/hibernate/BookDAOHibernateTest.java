package com.tsystems.bookstore.persistence.dao.impl.hibernate;

import java.util.List;

import org.hibernate.HibernateException;

import com.tsystems.bookstore.persistence.dao.BookDAO;
import com.tsystems.bookstore.persistence.dao.impl.hibernate.BookDAOImpl;
import com.tsystems.bookstore.persistence.entity.Book;
import com.tsystems.bookstore.persistence.utils.HibernateUtils;

public class BookDAOHibernateTest {

	private static BookDAO bookDAO = new BookDAOImpl();
	
	public static void main(String...strings ) {
	
		try {
			HibernateUtils.beginTransaction();
			
			Book dummyBook = generateDummyBook();
			
			//Delete dummy book if exist
			bookDAO.delete(dummyBook);
			
			//Try to find dummy book
			Book book1 = bookDAO.findByISBN("978-1932394153");
			if (book1 != null) {
				System.out.println("\nTest findByISBN");
				System.out.println(book1.getId() + " " + book1.getTitle() + " " + book1.getIsbn());
			} else {
				//Add dummy book
				bookDAO.addBook(dummyBook);			
			}

			
			System.out.println("\nTest findAllBooks");
			List<Book> books = bookDAO.findAllBooks();		
			for (Book bItem : books) {
				//Should print in the end dummy book
				System.out.println(bItem.getTitle() + " " + bItem.getIsbn());
			}
			
			//Delete dummy book - check it in db
//			bookDAO.delete(dummyBook);
			
			//Commit transaction
			HibernateUtils.commitTransaction();			
		} catch (HibernateException e) {
			HibernateUtils.rollbackTransaction();
		} finally {
			//Close session
			HibernateUtils.closeSession();			
		}

	}
	
	private static Book generateDummyBook(){
		Book book = new Book();
		book.setId(999);
		book.setTitle("Hibernate in Action");
		book.setIsbn("978-1932394153");
		return book;
	}

}
