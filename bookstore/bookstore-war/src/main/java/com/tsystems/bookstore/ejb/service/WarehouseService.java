package com.tsystems.bookstore.ejb.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import javax.inject.Inject;

import com.tsystems.bookstore.ejb.dao.WarehouseDAO;
import com.tsystems.bookstore.persistence.entity.Book;
import com.tsystems.bookstore.persistence.entity.Warehouse;

@Stateful
public class WarehouseService {
	
	@Inject
	WarehouseDAO warehouseDAO;
	
	@PostConstruct
    public void initialize () {
    	System.out.println("----------------------------------------------------------");
        System.out.println("WarehouseService Session Bean initialized.");
    } 

    @PreDestroy
    public void destroyBean() {
        System.out.println("WarehouseService Session Bean destroy.");
        System.out.println("----------------------------------------------------------");        
    } 
    
    /**
	 * Create a new warehouse or update an existing one
	 * 
	 * @param Warehouse warehouse
	 */
    public void createOrUpdateWarehouse(Warehouse warehouse) {
    	warehouseDAO.updateWarehouse(warehouse);
    }
    
    /**
	 * Delete warehouse from data store
	 * 
	 * @param Warehouse warehouse 
	 */
	public void deleteWarehouseByID(Integer id) {
		warehouseDAO.delete(this.getWarehouseById(id));
	}
    
	/**
	 * Retrieve a warehouse
	 * 
	 * @param long id identifier of the warehouse to be retrieved
	 * 
	 * @return Warehouse warehouse represented by the identifier provided
	 */
	public Warehouse getWarehouseById(int id) {	
		return warehouseDAO.findByID(Warehouse.class, id);	
	}
	
    /**
	 * Find all warehouses in database
	 * 
	 * @return ArrayList<Warehouses> of warehouses 
	 */
	public List<Warehouse> getAllWarehouses() {
		return warehouseDAO.findAllWarehouses();
	}
   
	/**
	 * Retrieve a warehouses
	 * 
	 * @param String telephone of the warehouse to be retrieved
	 * 
	 * @return ArrayList<Warehouses> of warehouses represented by the telephone provided
	 */
	public List<Warehouse> getAllWarehousesByTelephone(String number) {
		return warehouseDAO.findAllByTelephone(number);
	}
	
	/**
	 * Retrieve a warehouses
	 * 
	 * @param Book book in the warehouses to be retrieved
	 * 
	 * @return ArrayList<Warehouses> of warehouses represented by the book provided
	 */
	public List<Warehouse> getAllWarehousesByBook(Book book) {
		return warehouseDAO.findAllByBook(book);
	}
}
