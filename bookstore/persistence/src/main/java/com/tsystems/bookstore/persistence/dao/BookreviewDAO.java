package com.tsystems.bookstore.persistence.dao;

import java.math.BigDecimal;
import java.util.List;

import com.tsystems.bookstore.persistence.entity.Bookreview;

public interface BookreviewDAO extends GenericDAO<Bookreview, BigDecimal> {
	public List<Bookreview> findByUserId(int userId);
	public List<Bookreview> findByBookId(int bookId);
	public List<Bookreview> getTopRated(int bookId, int count);
	public List<Bookreview> getEditoralReviews(int bookId);
	public List<Bookreview> getUserReviews(int bookId);
}
