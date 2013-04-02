package com.tsystems.bookstore.persistence.dao.impl.hibernate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import com.tsystems.bookstore.persistence.dao.GenericDAO;
import com.tsystems.bookstore.persistence.utils.HibernateUtils;

public abstract class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {
 
    protected Session getSession() {
        return HibernateUtils.getSession();
    }
 
    public void save(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.saveOrUpdate(entity);
    }
 
    public void merge(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.merge(entity);
    }
 
    public void delete(T entity) {
        Session hibernateSession = this.getSession();
        hibernateSession.delete(entity);
    }
 
    public List<T> findMany(Query query) {
        List<T> t;
        t = (List<T>) query.list();
        return t;
    }
 
    public T findOne(Query query) {
        T t;
        t = (T) query.uniqueResult();
        return t;
    }
 
    public T findByID(Class entity, BigDecimal id) {
        Session hibernateSession = this.getSession();
        T t = null;
        t = (T) hibernateSession.get(entity, id);
        return t;
    }
 
    public List findAll(Class entity) {
        Session hibernateSession = this.getSession();
        List T = null;
        Query query = hibernateSession.createQuery("from " + entity.getSimpleName());
//        Query query = hibernateSession.createQuery("from Book");
        T = query.list();
        return T;
    }
}    
