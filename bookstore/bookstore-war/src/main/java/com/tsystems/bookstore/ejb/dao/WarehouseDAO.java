package com.tsystems.bookstore.ejb.dao;

import java.math.BigDecimal;
import java.util.List;

import com.tsystems.bookstore.persistence.entity.Book;
import com.tsystems.bookstore.persistence.entity.Warehouse;

public interface WarehouseDAO extends GenericDAO<Warehouse, BigDecimal> {
	public void addWarehouse(Warehouse warehouse);
	public void updateWarehouse(Warehouse warehouse);	
	
	public List<Warehouse> findAllWarehouses();
	public List<Warehouse> findAllByTelephone(String number);
	public List<Warehouse> findAllByBook(Book book);
}
