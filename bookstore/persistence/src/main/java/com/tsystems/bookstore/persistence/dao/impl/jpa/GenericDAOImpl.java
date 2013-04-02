package com.tsystems.bookstore.persistence.dao.impl.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.tsystems.bookstore.persistence.dao.GenericDAO;
import com.tsystems.bookstore.persistence.utils.JPAUtil;

public abstract class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

	//TODO EntityExistsException PersistenceException
	public void save(T entity) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		entityManager.persist(entity);
	}
	
	public void merge(T entity) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		entityManager.persist(entity);
	}
	
	public void delete(T entity) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		entityManager.remove(entity);
	}
	
	//TODO EntityNotFoundException
    public T findByID(Class entity, BigDecimal id) {
    	EntityManager entityManager = JPAUtil.getEntityManager();
        T t = null;
        t = (T) entityManager.find(entity, id);
        return t;
    }
	
	public List<T> findAll(Class entity) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		return (List<T>) entityManager.createQuery("select x from " + entity.getCanonicalName() + " x").getResultList(); 
	}

}
