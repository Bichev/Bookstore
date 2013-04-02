package com.tsystems.bookstore.persistence.dao.impl.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

//import com.tsystems.bookstore.persistence.dao.BookDAO;
import com.tsystems.bookstore.persistence.dao.MyBookDAO;
import com.tsystems.bookstore.persistence.entity.Book;
import com.tsystems.bookstore.persistence.utils.HibernateUtils;

/**
 * 
 * @author Sergey Kozlov
 *
 */

public class MyBookDAOImpl extends GenericDAOImpl<Book, BigDecimal> implements MyBookDAO {

//	@Override
	public Book findByTitle(String title) {
		Book book = null;
		Query query = HibernateUtils.getSession().createQuery("from Book where title = :title");
		query.setParameter("title",title);
		book = findOne(query);
		return book;
	}
	
//	@Override
	public Book changeTitle(String oldTitle, String newTitle) {
		Book book = findByTitle(oldTitle);
		book.setTitle(newTitle);
		merge(book);
		return book;
	}
	
//	@Override
	public void deleteByTitle(String title) {
		Book book = findByTitle(title);
		delete(book);	
	}
}

