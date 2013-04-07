package com.tsystems.bookstore.ejb.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import javax.persistence.Query;

import com.tsystems.bookstore.ejb.dao.WarehouseDAO;
import com.tsystems.bookstore.persistence.entity.Book;
import com.tsystems.bookstore.persistence.entity.Warehouse;

@Stateful
public class WarehouseDAOImpl extends GenericDAOImpl<Warehouse, BigDecimal> implements
		WarehouseDAO {
	
	@PersistenceContext(unitName="TUni")
    private EntityManager entityManager;

    @PostConstruct
    public void initialize () {
    	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("WarehouseDAOImpl initialized.");
    } 

    @PreDestroy
    public void destroyBean() {
        System.out.println("WarehouseDAOImpl destroy.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    } 
    
	
	@Override
	public void addWarehouse(Warehouse warehouse) {
		super.save(warehouse);
	}

	@Override
	public void updateWarehouse(Warehouse warehouse) {
		super.save(warehouse);
	}

	@Override
	public List<Warehouse> findAllWarehouses() {
		return super.findAll(Warehouse.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Warehouse> findAllByTelephone(String number) {		
		List<Warehouse> list = null;
		try {
			Query query = entityManager.createQuery(
							"from Warehouse where (telephone1 = :tel) or (telephone2 = :tel)");
			query.setParameter("tel", number);
			list = (List<Warehouse>) query.getResultList();
		} catch(NoResultException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Warehouse> findAllByBook(Book book) {
		List<Warehouse> list = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("select w ")
			  .append("from Warehouse as w ")
			  .append("where w in ( ")
			  .append("	select s.warehouse ")
			  .append("	from Stockitem as s ")
			  .append("	where s.book.id = :this_id ")
			  .append(")");
			Query query = entityManager.createQuery(sb.toString());
			query.setParameter("this_id", book.getId());							
			list = (List<Warehouse>) query.getResultList();
		} catch(NoResultException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

}
