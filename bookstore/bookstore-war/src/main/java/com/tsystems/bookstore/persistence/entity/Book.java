package com.tsystems.bookstore.persistence.entity;
// Generated 26.03.2013 20:03:54 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Book generated by hbm2java
 */
@Entity
@Table(name = "book", uniqueConstraints = @UniqueConstraint(columnNames = "isbn"))
public class Book implements java.io.Serializable {

	private int id;
	private String title;
	private String isbn;
	private Set<Book2category> book2categories = new HashSet<Book2category>(0);
	private Set<Book2author> book2authors = new HashSet<Book2author>(0);
	private Set<Stockitem> stockitems = new HashSet<Stockitem>(0);
	private Set<Bookreview> bookreviews = new HashSet<Bookreview>(0);
	private Set<Book2genre> book2genres = new HashSet<Book2genre>(0);

	public Book() {
	}

	public Book(int id, String isbn) {
		this.id = id;
		this.isbn = isbn;
	}
	
	public Book(int id, String name, String isbn) {
		this.id = id;
		this.title = name;
		this.isbn = isbn;
	}

	public Book(int id, String title, String isbn,
			Set<Book2category> book2categories, Set<Book2author> book2authors,
			Set<Stockitem> stockitems, Set<Bookreview> bookreviews,
			Set<Book2genre> book2genres) {
		this.id = id;
		this.title = title;
		this.isbn = isbn;
		this.book2categories = book2categories;
		this.book2authors = book2authors;
		this.stockitems = stockitems;
		this.bookreviews = bookreviews;
		this.book2genres = book2genres;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 8, scale = 0)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "title", length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "isbn", unique = true, nullable = false, length = 20)
	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
	public Set<Book2category> getBook2categories() {
		return this.book2categories;
	}

	public void setBook2categories(Set<Book2category> book2categories) {
		this.book2categories = book2categories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
	public Set<Book2author> getBook2authors() {
		return this.book2authors;
	}

	public void setBook2authors(Set<Book2author> book2authors) {
		this.book2authors = book2authors;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "book")
	public Set<Stockitem> getStockitems() {
		return this.stockitems;
	}

	public void setStockitems(Set<Stockitem> stockitems) {
		this.stockitems = stockitems;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
	public Set<Bookreview> getBookreviews() {
		return this.bookreviews;
	}

	public void setBookreviews(Set<Bookreview> bookreviews) {
		this.bookreviews = bookreviews;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
	public Set<Book2genre> getBook2genres() {
		return this.book2genres;
	}

	public void setBook2genres(Set<Book2genre> book2genres) {
		this.book2genres = book2genres;
	}

}
