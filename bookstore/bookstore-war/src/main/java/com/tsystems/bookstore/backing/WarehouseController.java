package com.tsystems.bookstore.backing;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.tsystems.bookstore.ejb.service.WarehouseService;
import com.tsystems.bookstore.persistence.entity.Warehouse;
import com.tsystems.bookstore.persistence.entity.Book;

@Named
@RequestScoped
public class WarehouseController {

	@Inject
	private WarehouseService warehouseService;
	
	public String testCreateWarehouse() {
		System.out.println("\n---------------> N1: Create <---------------");		
		Warehouse warehouse = generateDummyWarehouse(); 
		warehouseService.createOrUpdateWarehouse(warehouse);
		System.out.println("\n<------------------------------------------>");
		return "";
	}
	
	public String testDeleteWarehouse() {
		System.out.println("\n---------------> N2: Delete <---------------");		
		Warehouse dummyWarehouse = generateDummyWarehouse(); 
		warehouseService.deleteWarehouseByID(dummyWarehouse.getId());
		System.out.println("\n<------------------------------------------->");
		return "";
	}
	
	public String testFindWarehouseById() {
		System.out.println("\n-------------> N3: Find by Id <--------------");		
		Warehouse dummyWarehouse = generateDummyWarehouse(); 
		Warehouse foundWarehouse = warehouseService.getWarehouseById(dummyWarehouse.getId());
		printWarehouse(foundWarehouse);
		System.out.println("\n<------------------------------------------->");
		return "";
	}
	
	public String testFindAllWarehouses() {
		System.out.println("\n---------------> N4: Find All <---------------");		
		List<Warehouse> warehouses = null; 
		warehouses = warehouseService.getAllWarehouses();
		printFoundList(warehouses);
		System.out.println("\n<-------------------------------------------->");
		return "";
	}
	
	public String testFindAllWarehousesByTelephone() {
		System.out.println("\n---------> N5: Find All by telephone <---------");		
		List<Warehouse> warehouses = null; 
		String telephone = generateDummyWarehouse().getTelephone1();
		warehouses = warehouseService.getAllWarehousesByTelephone(telephone);
		printFoundList(warehouses);
		System.out.println("\n<--------------------------------------------->");
		return "";
	}
	
	public String testFindAllWarehousesByBook() {
		System.out.println("\n-----------> N6: Find All by book <------------");		
		List<Warehouse> warehouses = null; 
		Book book = generateDummyBook();
		warehouses = warehouseService.getAllWarehousesByBook(book);
		printFoundList(warehouses);
		System.out.println("\n<--------------------------------------------->");
		return "";
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
	
	private static void printFoundList(List<Warehouse> warehouses) {
		System.out.println("Found: ");
		for (Warehouse wh : warehouses) {
			printWarehouse(wh);
		}
	}
	
	private static void printWarehouse(Warehouse wh) {
		StringBuilder sb = new StringBuilder(); 
		sb.append(" > Id: " + wh.getId() + '\n');
		sb.append(" > Address: " + wh.getAddressLine() + '\n');
		sb.append(" > Telephone 1: " + wh.getTelephone1() + '\n');
		sb.append(" > Telephone 2: " + wh.getTelephone2() + '\n');
		sb.append(" +------" + '\n');
		System.out.println(sb.toString());
	}
}
