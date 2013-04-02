package com.tsystems.bookstore.persistence.dao;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
 
public interface GenericDAO<T, ID extends Serializable> {
 
    public void save(T entity);
 
    public void merge(T entity);
 
    public void delete(T entity);
 
    public List findAll(Class clazz);
 
    public T findByID(Class clazz, BigDecimal id);
}
