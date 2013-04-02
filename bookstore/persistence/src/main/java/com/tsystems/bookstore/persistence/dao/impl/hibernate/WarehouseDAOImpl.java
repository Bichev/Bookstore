package com.tsystems.bookstore.persistence.dao.impl.hibernate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.tsystems.bookstore.persistence.dao.WarehouseDAO;
import com.tsystems.bookstore.persistence.entity.Book;
import com.tsystems.bookstore.persistence.entity.Warehouse;
import com.tsystems.bookstore.persistence.utils.HibernateUtils;
public class WarehouseDAOImpl extends GenericDAOImpl<Warehouse, BigDecimal> implements WarehouseDAO {

	@Override
	public void addWarehouse(Warehouse warehouse) {		
		save(warehouse);
	}

	@Override
	public void updateWarehouse(Warehouse warehouse) {
		merge(warehouse);		
	}

	@Override
	public List<Warehouse> findAllWarehouses() {
		List<Warehouse> list = new ArrayList<Warehouse>();
		list = findAll(Warehouse.class);
		return list;
	}

	@Override
	public List<Warehouse> findAllByTelephone(String number) {
		List<Warehouse> list = null;
		Query query = HibernateUtils
				.getSession()
				.createQuery(
						"from Warehouse where (telephone1 = :tel) or (telephone2 = :tel)");
		query.setParameter("tel", number);
		list = findMany(query);
		return list;
	}
	
	@Override
	public List<Warehouse> findAllByBook(Book book) {
		List<Warehouse> list = null;
		StringBuilder sb = new StringBuilder();
		sb.append("select w ")
		  .append("from Warehouse as w ")
		  .append("where w in ( ")
		  .append("	select s.warehouse ")
		  .append("	from Stockitem as s ")
		  .append("	where s.book.id = :this_id ")
		  .append(")");
		Query query = HibernateUtils
				.getSession()
				.createQuery(sb.toString())
				.setParameter("this_id", book.getId());							
		list = findMany(query);
		return list;
	}

}
