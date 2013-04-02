package com.tsystems.bookstore.persistence.dao.impl.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.tsystems.bookstore.persistence.dao.StockitemDAO;
import com.tsystems.bookstore.persistence.entity.Stockitem;
import com.tsystems.bookstore.persistence.utils.HibernateUtils;



public class StockitemDAOImpl extends GenericDAOImpl<Stockitem, BigDecimal> implements StockitemDAO {

	public Stockitem findByID(int id) {
		Stockitem stockitem = null;
		Query query = HibernateUtils.getSession().createQuery("from Stockitem where id = :id ");
		query.setParameter("id", id);
		stockitem = findOne(query);
		return stockitem;
	}
	
	public Stockitem findByPrice(BigDecimal price) {
		Stockitem stockitem = null;
		Query query = HibernateUtils.getSession().createQuery("from Stockitem where price = :price ");
		query.setParameter("price", price);
		stockitem = findOne(query);
		return stockitem;
	}
	
	public List<Stockitem> findAllStockitem() {
		List<Stockitem> stockitems = new ArrayList<Stockitem>();
		stockitems = findAll(Stockitem.class);
		return stockitems;
	}
	
	public void addStockitem(Stockitem stockitem) {
		save(stockitem);
	}
	
	public void updateStockitem(Stockitem stockitem) {
		merge(stockitem);		
	}
	
}
