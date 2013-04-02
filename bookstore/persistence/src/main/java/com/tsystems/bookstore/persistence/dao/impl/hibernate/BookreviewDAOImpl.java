package com.tsystems.bookstore.persistence.dao.impl.hibernate;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;

import com.tsystems.bookstore.persistence.dao.BookreviewDAO;
import com.tsystems.bookstore.persistence.entity.Bookreview;
import com.tsystems.bookstore.persistence.utils.HibernateUtils;

public class BookreviewDAOImpl extends GenericDAOImpl<Bookreview, BigDecimal> implements BookreviewDAO {

	public List<Bookreview> findByUserId(int userId) {
		Query query = HibernateUtils.getSession().createQuery("from Bookreview where usr_id = :usr_id ");
		query.setParameter("usr_id", userId);
		List<Bookreview> reviews = findMany(query);
		return reviews;
	}

	public List<Bookreview> findByBookId(int bookId) {
		Query query = HibernateUtils
				.getSession()
				.createQuery("from Bookreview where book_id = :book_id ");
		query.setParameter("book_id", bookId);
		List<Bookreview> reviews = findMany(query);
		return reviews;
	}

	public List<Bookreview> getTopRated(int bookId, int count) {
		Query query = HibernateUtils
				.getSession()
				.createQuery("from Bookreview where book_id = :book_id order by rating desc")
				.setMaxResults(count);
		query.setParameter("book_id", bookId);
		List<Bookreview> reviews = findMany(query);
		return reviews;
	}

	public List<Bookreview> getEditoralReviews(int bookId) {
		Query query = HibernateUtils
				.getSession()
				.createQuery("from Bookreview where book_id = :book_id and reviewType = 'E'");
		query.setParameter("book_id", bookId);
		List<Bookreview> reviews = findMany(query);
		return reviews;
	}

	public List<Bookreview> getUserReviews(int bookId) {
		Query query = HibernateUtils
				.getSession()
				.createQuery("from Bookreview where book_id = :book_id and reviewType = 'R'");
		query.setParameter("book_id", bookId);
		List<Bookreview> reviews = findMany(query);
		return reviews;
	}
	
}
