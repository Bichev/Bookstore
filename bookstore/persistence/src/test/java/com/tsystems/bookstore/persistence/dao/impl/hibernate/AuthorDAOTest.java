package com.tsystems.bookstore.persistence.dao.impl.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.HibernateException;
import java.math.*;
import com.tsystems.bookstore.persistence.dao.AuthorDAO;
import com.tsystems.bookstore.persistence.dao.impl.hibernate.AuthorDAOImpl;
import com.tsystems.bookstore.persistence.entity.Author;
import com.tsystems.bookstore.persistence.utils.HibernateUtils;
import java.lang.System;

public class AuthorDAOTest {

	private static AuthorDAO authorDao = new AuthorDAOImpl();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			// start transaction
			HibernateUtils.beginTransaction();
			Author dummyAuthor = generateDummyAuthor();

			// try to find dummy author(by firstname)
			Author author1 = authorDao.findByFirstname("Matthew");
			if (author1 != null) {
				System.out.println("Test find by firstname : ");
				System.out
						.println(author1.getId() + " | "
								+ author1.getFirstname() + " | "
								+ author1.getLastname() + " | "
								+ author1.getBirthday());
				authorDao.changeLastname(author1, "Change");

				System.out.println("Test change lastname : before/after ");
				System.out.println(dummyAuthor.getLastname() + "/"
						+ author1.getLastname());

			} else {
				System.out.println("Nothing has found. ");
				authorDao.addAuthor(dummyAuthor);
			}

			// delete dummy author from db(using method from GenericDAO)
			authorDao.delete(dummyAuthor);
			// delete dummy author from db(using method from AuthorDAO)
			if (author1 != null) {
				authorDao.deleteById(BigDecimal.valueOf(dummyAuthor.getId()));

			}

			// Commit transaction
			HibernateUtils.commitTransaction();

		} catch (HibernateException e) {
			// rollback transaction
			HibernateUtils.rollbackTransaction();

		} finally {
			// this block always execute
			// Close session
			HibernateUtils.closeSession();
		}
	}

	private static Author generateDummyAuthor() {
		String str = "1978-01-12";
		Date dt = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dt = formatter.parse(str);
		} catch (Exception e) {
			System.out.println("Format exception...");
		}
		Author author = new Author();
		author.setId(333);
		author.setFirstname("Matthew");
		author.setLastname("MacDonald");
		author.setBirthday(dt);
		return author;
	}

}
