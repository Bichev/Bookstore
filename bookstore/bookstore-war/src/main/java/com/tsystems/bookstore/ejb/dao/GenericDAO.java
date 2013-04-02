package com.tsystems.bookstore.ejb.dao;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

import javax.ejb.Local;
 
public interface GenericDAO<T, ID extends Serializable> {
 
    public void save(T entity);
 
    public void merge(T entity);
 
    public void delete(T entity);
 
    public List findAll(Class clazz);
 
    public T findByID(Class clazz, Integer id);
}
