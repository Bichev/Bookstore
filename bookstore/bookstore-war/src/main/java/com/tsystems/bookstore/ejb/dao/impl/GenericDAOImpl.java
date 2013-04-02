package com.tsystems.bookstore.ejb.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.tsystems.bookstore.ejb.dao.GenericDAO;

public abstract class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

    @PersistenceContext(unitName="TUni")
    private EntityManager entityManager;

	public void save(T entity) {
		try {
			entityManager.persist(entity);
		//TODO catch correct Exception!!!
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//TODO catch correct Exception!!!
	public void merge(T entity) {
		entityManager.persist(entity);
	}
	
	//TODO catch correct Exception!!!
	public void delete(T entity) {
		entityManager.remove(entity);
	}
	
	//TODO EntityNotFoundException
    public T findByID(Class entity, Integer id) {
        T t = null;
        t = (T) entityManager.find(entity, id);
        return t;
    }
	
	public List<T> findAll(Class entity) {
		return (List<T>) entityManager.createQuery("select x from " + entity.getCanonicalName() + " x").getResultList(); 
	}

}
