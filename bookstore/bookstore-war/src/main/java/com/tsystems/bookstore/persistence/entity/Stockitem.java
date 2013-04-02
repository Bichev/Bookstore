package com.tsystems.bookstore.persistence.entity;
// Generated 26.03.2013 20:03:54 by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Stockitem generated by hbm2java
 */
@Entity
@Table(name = "stockitem", uniqueConstraints = @UniqueConstraint(columnNames = {
		"book_id", "wh_id" }))
public class Stockitem implements java.io.Serializable {

	private int id;
	private Warehouse warehouse;
	private Book book;
	private short quantity;
	private BigDecimal price;

	public Stockitem() {
	}

	public Stockitem(int id, Warehouse warehouse, Book book, short quantity,
			BigDecimal price) {
		this.id = id;
		this.warehouse = warehouse;
		this.book = book;
		this.quantity = quantity;
		this.price = price;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false, precision = 8, scale = 0)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wh_id", nullable = false)
	public Warehouse getWarehouse() {
		return this.warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id", nullable = false)
	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Column(name = "quantity", nullable = false, precision = 4, scale = 0)
	public short getQuantity() {
		return this.quantity;
	}

	public void setQuantity(short quantity) {
		this.quantity = quantity;
	}

	@Column(name = "price", nullable = false, precision = 8)
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
