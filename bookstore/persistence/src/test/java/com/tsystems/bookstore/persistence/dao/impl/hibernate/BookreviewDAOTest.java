package com.tsystems.bookstore.persistence.dao.impl.hibernate;

import java.util.List;

import org.hibernate.HibernateException;

import com.tsystems.bookstore.persistence.dao.BookreviewDAO;
import com.tsystems.bookstore.persistence.dao.impl.hibernate.BookreviewDAOImpl;
import com.tsystems.bookstore.persistence.entity.Bookreview;
import com.tsystems.bookstore.persistence.utils.HibernateUtils;


public class BookreviewDAOTest {
	private static BookreviewDAO bookreviewDAO = new BookreviewDAOImpl();
	
	public static void main(String[] args) {
		try {
			HibernateUtils.beginTransaction();
			
			List<Bookreview> reviewsByBookId = bookreviewDAO.findByBookId(1);
			System.out.println("\nTest findByBookId = 1.");
			printReviews(reviewsByBookId);
			
			List<Bookreview> reviewsByUserId = bookreviewDAO.findByUserId(2);
			System.out.println("\nTest findByUserId = 2.");
			printReviews(reviewsByUserId);
			
			List<Bookreview> topRatedReviews = bookreviewDAO.getTopRated(1, 20);
			System.out.println("\nTest getTopRated for bookId = 1 and count = 20.");
			printReviews(topRatedReviews);
			
			List<Bookreview> editoralReviews = bookreviewDAO.getEditoralReviews(3);
			System.out.println("\nTest getEditoralReviews for bookId = 3.");
			printReviews(editoralReviews);
			
			List<Bookreview> userReviews = bookreviewDAO.getUserReviews(2);
			System.out.println("\nTest getUserReviews for bookId = 2.");
			printReviews(userReviews);
		
			HibernateUtils.commitTransaction();	
 		} catch (HibernateException e) {
 			e.printStackTrace();
			HibernateUtils.rollbackTransaction();
		} finally {
			HibernateUtils.closeSession();			
		}
	}

	private static void printReviews(List<Bookreview> reviews) {
		for (Bookreview review : reviews) {
			System.out.println("Id: " + review.getId());
			System.out.println("Rating: " + review.getRating());
			System.out.println("Text: " + review.getText());
		}
	}
}
