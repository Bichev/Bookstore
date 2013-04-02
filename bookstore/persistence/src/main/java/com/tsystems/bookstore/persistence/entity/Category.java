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
 * Category generated by hbm2java
 */
@Entity
@Table(name = "category", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Category implements java.io.Serializable {

	private int id;
	private String name;
	private String description;
	private Set<Book2category> book2categories = new HashSet<Book2category>(0);

	public Category() {
	}

	public Category(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Category(int id, String name, String description,
			Set<Book2category> book2categories) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.book2categories = book2categories;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 8, scale = 0)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name", unique = true, nullable = false, length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 200)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	public Set<Book2category> getBook2categories() {
		return this.book2categories;
	}

	public void setBook2categories(Set<Book2category> book2categories) {
		this.book2categories = book2categories;
	}

}