package com.tsystems.bookstore.ejb.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import com.tsystems.bookstore.ejb.dao.BookreviewDAO;
import com.tsystems.bookstore.persistence.entity.Bookreview;

@Stateful
public class BookreviewDAOImpl extends GenericDAOImpl<Bookreview, BigDecimal> implements BookreviewDAO {

	@PersistenceContext(unitName="TUni")
    private EntityManager entityManager;
	
	@PostConstruct
    public void initialize () {
        System.out.println("----------------------------------------------------------");
        System.out.println("BookreviewDAOImpl initialized.");
    } 
	
	@PreDestroy
	public void destroyBean() {
		System.out.println("BookreviwDAOImpl is going to be destroyed.");
	    System.out.println("----------------------------------------------------------");
	} 
	
	@Override
	public List<Bookreview> findByUserId(int userId) {
		Query query = entityManager.createQuery("select br from Bookreview br where br.usr.id = :usrId");
		query.setParameter("usrId", userId);
		List<Bookreview> reviews = query.getResultList();
		return reviews;
	}

	@Override
	public List<Bookreview> findByBookId(int bookId) {
		Query query = entityManager.createQuery("select br from Bookreview br where br.book.id = :bookId");
		query.setParameter("bookId", bookId);
		List<Bookreview> reviews = query.getResultList();
		return reviews;
	}

	@Override
	public List<Bookreview> getTopRated(int bookId, int count) {
		Query query = entityManager.createQuery("select br from Bookreview br where br.book.id = :bookId order by rating desc")
				.setMaxResults(count);
		query.setParameter("bookId", bookId);
		List<Bookreview> reviews = query.getResultList();
		return reviews;
	}

	@Override
	public List<Bookreview> getEditoralReviews(int bookId) {
		Query query = entityManager.createQuery("select br from Bookreview br where br.book.id = :bookId and br.reviewType = 'E'");
		query.setParameter("bookId", bookId);
		List<Bookreview> reviews = query.getResultList();
		return reviews;
	}

	@Override
	public List<Bookreview> getUserReviews(int bookId) {
		Query query = entityManager.createQuery("select br from Bookreview br where br.book.id = :bookId and br.reviewType = 'R'");
		query.setParameter("bookId", bookId);
		List<Bookreview> reviews = query.getResultList();
		return reviews;
	}

}
