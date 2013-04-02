package com.tsystems.bookstore.ejb.dao;

import java.math.BigDecimal;
import java.util.List;

import com.tsystems.bookstore.persistence.entity.Stockitem;

public interface StockitemDAO extends GenericDAO<Stockitem, BigDecimal>{
	
	public Stockitem findByID(int id);
	public Stockitem findByPrice(BigDecimal price);
	public List<Stockitem> findAllStockitem();
	
	public void addStockitem(Stockitem stockitem);
	public void updateStockitem(Stockitem stockitem);
	
}
