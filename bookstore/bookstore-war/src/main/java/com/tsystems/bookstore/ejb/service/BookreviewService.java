package com.tsystems.bookstore.ejb.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import javax.inject.Inject;

import com.tsystems.bookstore.ejb.dao.BookreviewDAO;
import com.tsystems.bookstore.persistence.entity.Book;
import com.tsystems.bookstore.persistence.entity.Bookreview;

@Stateful
public class BookreviewService {
	@Inject
	private BookreviewDAO bookreviewDAO;
	
	@PostConstruct
    public void initialize () {
        System.out.println("----------------------------------------------------------");
        System.out.println("BookService Session Bean initialized.");
    } 

    @PreDestroy
    public void destroyBean() {
        // Free here resources acquired by the session bean
        System.out.println("BookService Session Bean is going to be destroyed.");
    	System.out.println("----------------------------------------------------------");        
    } 
    
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
		return bookreviewDAO.findByID(bookreview, id);
	}
	
	public void delete(Bookreview bookreview) {
		Bookreview bookreviewFromDb = bookreviewDAO.findByID(Bookreview.class, Integer.valueOf(bookreview.getId()));
		bookreviewDAO.delete(bookreviewFromDb);
	}
	
	public List<Bookreview> findAll(Class bookreview) {
		return bookreviewDAO.findAll(bookreview);
	}
}
