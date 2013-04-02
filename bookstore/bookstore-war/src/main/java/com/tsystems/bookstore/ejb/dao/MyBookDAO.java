package com.tsystems.bookstore.ejb.dao;

import java.math.BigDecimal;
import java.util.List;
import com.tsystems.bookstore.persistence.entity.Book;

/**
 * 
 * @author Sergey Kozlov
 *
 */

public interface MyBookDAO extends GenericDAO<Book, BigDecimal> {

	public  Book findByTitle(String title);
	public void deleteByTitle(String title);
	public Book changeTitle(String oldTitle, String newTitle);
	
}
