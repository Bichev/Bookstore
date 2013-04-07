package com.tsystems.bookstore.persistence.service;

import java.math.BigDecimal;
import java.util.List;

import com.tsystems.bookstore.persistence.dao.BookreviewDAO;
import com.tsystems.bookstore.persistence.dao.impl.hibernate.BookreviewDAOImpl;
import com.tsystems.bookstore.persistence.entity.Bookreview;

public class BookreviewService {

	private BookreviewDAO bookreviewDAO = new BookreviewDAOImpl();
	    
    public List<Bookreview> findByUserId(int userId) {
    	return bookreviewDAO.findByUserId(userId);
    }
    
	public List<Bookreview> findByBookId(int bookId) {
		return bookreviewDAO.findByBookId(bookId);
	}
	
	public List<Bookreview> getTopRated(int bookId, int count) {
		return bookreviewDAO.getTopRated(bookId, count);
	}
	
	public List<Bookreview> getEditoralReviews(int bookId) {
		return bookreviewDAO.getEditoralReviews(bookId);
	}
	
	public List<Bookreview> getUserReviews(int bookId) {
		return bookreviewDAO.getUserReviews(bookId);
	}
	
	public void save(Bookreview bookreview) {
		bookreviewDAO.save(bookreview);
	}
	
	public Bookreview findByID(Class bookreview, Integer id) {
		return bookreviewDAO.findByID(bookreview, BigDecimal.valueOf(id));
	}
	
	public void merge(Bookreview bookreview) {
		bookreviewDAO.merge(bookreview);
	}
	
	public void delete(Bookreview bookreview) {
		bookreviewDAO.delete(bookreview);
	}
	
	public List<Bookreview> findAll(Class bookreview) {
		return bookreviewDAO.findAll(bookreview);
	}
}
