package com.tsystems.bookstore.persistence.dao.impl.hibernate;

import java.util.List;

import com.tsystems.bookstore.persistence.dao.WarehouseDAO;
import com.tsystems.bookstore.persistence.dao.impl.hibernate.WarehouseDAOImpl;
import com.tsystems.bookstore.persistence.entity.Book;
import com.tsystems.bookstore.persistence.entity.Warehouse;
import com.tsystems.bookstore.persistence.utils.HibernateUtils;

public class WarehouseDAOTest {
	
	private static WarehouseDAO warehouseDAO = new WarehouseDAOImpl();
	
	public static void main(String...strings ) {
		
		HibernateUtils.beginTransaction();
			
		System.out.println("=========  TESTS  =========");
		Warehouse dummyWarehouse = generateDummyWarehouse();
		List<Warehouse> warehouses = null;		
		
		// ------------------------ Test N1 ---------------------------
		System.out.println("\n----- N1: Delete and Add -----");													
		warehouseDAO.delete(dummyWarehouse);
		warehouseDAO.addWarehouse(dummyWarehouse);
		
		// ------------------------ Test N2 ---------------------------
		System.out.println("\n----- N2: Find All -----");
		warehouses = null; 
		warehouses = warehouseDAO.findAllWarehouses();
		System.out.println("Found: ");
		for (Warehouse wh : warehouses) {
			System.out.println(" > Id: " + wh.getId());
			System.out.println(" > Address: " + wh.getAddressLine());
			System.out.println(" > Telephone 1: " + wh.getTelephone1());
			System.out.println(" > Telephone 2: " + wh.getTelephone2());
			System.out.println(" +------");
		}
		
		// ------------------------ Test N3 ---------------------------
		System.out.println("\n----- N3: Find All by telephone -----");	
		warehouses = null; 
		String telephone = dummyWarehouse.getTelephone1();
		System.out.println("Telephone: " + telephone);
		warehouses = warehouseDAO.findAllByTelephone(telephone);
		System.out.println("Found: ");
		for (Warehouse wh : warehouses) {
			System.out.println(" > Id: " + wh.getId());
			System.out.println(" > Address: " + wh.getAddressLine());
			System.out.println(" > Telephone 1: " + wh.getTelephone1());
			System.out.println(" > Telephone 2: " + wh.getTelephone2());
			System.out.println(" +------");
		}
		
		// ------------------------ Test N4 ---------------------------
		System.out.println("\n----- N4: Find All by book -----");		
		Book dummyBook = generateDummyBook();
		warehouses = null; 		
		System.out.println("Book.id = " + dummyBook.getId());
		warehouses = warehouseDAO.findAllByBook(dummyBook);
		System.out.println("Found: ");
		for (Warehouse wh : warehouses) {
			System.out.println(" > Id: " + wh.getId());
			System.out.println(" > Address: " + wh.getAddressLine());
			System.out.println(" > Telephone 1: " + wh.getTelephone1());
			System.out.println(" > Telephone 2: " + wh.getTelephone2());
			System.out.println(" +------");
		}
		
		System.out.println("\n===========================");
		
		//Commit transaction
		HibernateUtils.commitTransaction();
		
		//Close session
		HibernateUtils.closeSession();		
	}
	
	private static Warehouse generateDummyWarehouse() {
		Warehouse wh = new Warehouse();
		wh.setId(100);
		wh.setAddressLine("Hibernate street, blah blah blah");
		wh.setTelephone1("9138616");
		wh.setTelephone2("1303817");
		return wh;
	}
	
	private static Book generateDummyBook() {
		Book book = new Book();
		book.setId(6);
		book.setTitle("Hibernate in Action");
		book.setIsbn("978-1932394153");
		return book;
	}
}
