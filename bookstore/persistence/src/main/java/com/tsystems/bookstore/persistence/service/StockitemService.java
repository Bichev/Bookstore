package com.tsystems.bookstore.persistence.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.tsystems.bookstore.persistence.dao.StockitemDAO;
import com.tsystems.bookstore.persistence.dao.impl.hibernate.StockitemDAOImpl;
import com.tsystems.bookstore.persistence.entity.Stockitem;

public class StockitemService {
	
	StockitemDAO stockitemDAO = new StockitemDAOImpl();
	
	/**
	 * Create a new item or update an existing one
	 * 
	 * @param Stockitem stockitem
	 */
	public void createOrUpdateItem(Stockitem stockitem) {
		stockitemDAO.save(stockitem);
	}
	
	/**
	 * Delete item from data store
	 * 
	 * @param Stockitem stockitem 
	 */
	public void deleteItem(Stockitem stockitem) {
		stockitemDAO.delete(stockitem);
	}

	/**
	 * Find all items in database
	 * 
	 * @return ArrayList<Stockitem> of items 
	 */
	public List<Stockitem> getItem() {
		return (ArrayList<Stockitem>) stockitemDAO.findAllStockitem();
	}

	/**
	 * Retrieve a book
	 * 
	 * @param int id identifier of the item to be retrieved
	 * 
	 * @return Book book represented by the identifier provided
	 */
	public Stockitem getItemById(int id) {
		return (Stockitem) stockitemDAO.findByID(Stockitem.class, BigDecimal.valueOf(id));
	}
	
	/**
	 * Retrieve a book
	 * 
	 * @param long price of the items to be retrieved
	 * 
	 * @return Stockitem stockitem represented by the price provided
	 */
	public Stockitem getItemByPrice(long price) {
		return stockitemDAO.findByPrice(BigDecimal.valueOf(price));
	}
}
