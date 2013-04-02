package com.tsystems.bookstore.persistence.dao.impl.hibernate;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.HibernateException;

import com.tsystems.bookstore.persistence.dao.StockitemDAO;
import com.tsystems.bookstore.persistence.dao.impl.hibernate.StockitemDAOImpl;
import com.tsystems.bookstore.persistence.entity.Book;
import com.tsystems.bookstore.persistence.entity.Stockitem;
import com.tsystems.bookstore.persistence.entity.Warehouse;
import com.tsystems.bookstore.persistence.utils.HibernateUtils;

public class StockitemDAOTest {
	private static StockitemDAO stockitemDAO = new StockitemDAOImpl();

	public static void main(String... strings) {
		try {
			HibernateUtils.beginTransaction();

			Stockitem dummyitem = generateDummyItem();

			stockitemDAO.delete(dummyitem);

			// Try to find dummy item
			Stockitem item = stockitemDAO.findByID(4);
			if (item != null) {
				System.out.println("\nTest findByID");
				System.out.println(item.getId() + " " + item.getBook() + " "
						+ item.getQuantity() + " " + item.getWarehouse() + " "
						+ item.getPrice());
			} else {
				// Add dummy item
				stockitemDAO.addStockitem(dummyitem);
			}

			Stockitem item2 = stockitemDAO.findByPrice(BigDecimal
					.valueOf(37.00));
			if (item2 != null) {
				System.out.println("\nTest findByPrice");
				System.out.println(item2.getId() + " " + item2.getBook() + " "
						+ item2.getQuantity() + " " + item2.getWarehouse()
						+ " " + item2.getPrice());
			}

			System.out.println("\nTest findAllItem");
			List<Stockitem> items = stockitemDAO.findAllStockitem();
			for (Stockitem item1 : items) {
				// Should print in the end dummy item
				System.out.println(item1.getId() + " " + item1.getBook() + " "
						+ item1.getQuantity() + " " + item1.getWarehouse()
						+ " " + item1.getPrice());
			}

			// Commit transaction
			HibernateUtils.commitTransaction();
		} catch (HibernateException e) {
			HibernateUtils.rollbackTransaction();
		} finally {

			// Close session
			HibernateUtils.closeSession();
		}

	}

	private static Stockitem generateDummyItem() {
		Book book = new Book();
		book.setId(2);
		book.setTitle("Domain-Specific Languages");
		book.setIsbn("978-0321712943");
		Warehouse wh = new Warehouse(1, "440 Terry Ave. N Seattle, WA 98109");
		Stockitem stockitem = new Stockitem(99, wh, book, (short) 4,
				BigDecimal.valueOf(100));
		return stockitem;
	}
}
