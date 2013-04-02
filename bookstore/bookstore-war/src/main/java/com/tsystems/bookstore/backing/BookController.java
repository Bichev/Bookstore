package com.tsystems.bookstore.backing;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.tsystems.bookstore.ejb.service.BookService;
import com.tsystems.bookstore.persistence.entity.Book;

//Setup BookController as Managed Bean by @Named
@Named
//Determine Scope for Managed Bean from javax.enterprise.context package
@RequestScoped
public class BookController {

	@Inject
	private BookService bookService;
	
	public String testCreateBook() {
		System.out.println("----------------------------------------------------------");
		System.out.println("Create new Book item");
		Book dummyBook = generateDummyBook();
		bookService.createOrUpdateBook(dummyBook);
		System.out.println("----------------------------------------------------------");
		return "";
	}
	
	public String testDeleteBook() {
		System.out.println("----------------------------------------------------------");
		System.out.println("Delete Book item");
		Book bookToDelete = generateDummyBook();
		bookService.deleteBookByID(bookToDelete.getId());
		System.out.println("----------------------------------------------------------");
		return "";
	}
	
	public String testFindAllBooks() {
		System.out.println("----------------------------------------------------------");
		System.out.println("Start Finding books");
		List<Book> books = (ArrayList<Book>) bookService.getBooks();
		for (Book book : books) {
			System.out.println(book.getTitle() + " " + book.getIsbn());
		}
		System.out.println("----------------------------------------------------------");
		return "";
	}
	
	public String testFindBookByISBN() {
		System.out.println("----------------------------------------------------------");
		System.out.println("Start Finding book by ISBN");
		Book book = bookService.getBookByISBN("978-0123850171");
		System.out.println(book.getTitle() + " " + book.getIsbn());
		System.out.println("----------------------------------------------------------");
		return "";
	}
	
	public String testFindBookById() {
		System.out.println("----------------------------------------------------------");
		System.out.println("Start Finding book by ID");
		Book book = bookService.getBookById(7);
		System.out.println(book.getTitle() + " " + book.getIsbn());
		System.out.println("----------------------------------------------------------");
		return "";
	}
	
	private Book generateDummyBook(){
		Book book = new Book();
		book.setId(999);
		book.setTitle("Hibernate in Action");
		book.setIsbn("978-1932394153");
		return book;
	}
}
