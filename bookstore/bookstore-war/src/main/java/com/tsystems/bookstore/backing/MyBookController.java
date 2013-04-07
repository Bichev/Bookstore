package com.tsystems.bookstore.backing;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.tsystems.bookstore.ejb.service.MyBookService;
import com.tsystems.bookstore.persistence.entity.Book;

//Setup BookController as Managed Bean by @Named
@Named
//Determine Scope for Managed Bean from javax.enterprise.context package
@RequestScoped
public class MyBookController {

	@Inject
	private MyBookService bookService;
	
	public String testCreateBook() {
		System.out.println("----------------------------------------------------------");
		System.out.println("Create new Book item");
		Book dummyBook = generateDummyBook();
		bookService.createBook(dummyBook);
		System.out.println("----------------------------------------------------------");
		return "";
	}
	
	public String testDeleteBook() {
		System.out.println("----------------------------------------------------------");
		System.out.println("Delete Book item");
		Book bookToDelete = generateDummyBook();
		bookService.deleteBookByTitle(bookToDelete.getTitle());
		System.out.println("----------------------------------------------------------");
		return "";
	}
	
	public String testFindBookByTitle() {
		System.out.println("----------------------------------------------------------");
		System.out.println("Start Finding book by Title");
		Book book = bookService.getBookByTitle("This is Sparta!!!");
		System.out.println(book.getTitle() + " " + book.getIsbn());
		System.out.println("----------------------------------------------------------");
		return "";
	}
	
	private Book generateDummyBook(){
		Book book = new Book();
		book.setId(300);
		book.setTitle("This is Sparta!!!");
		book.setIsbn("300-1932100500");
		return book;
	}
}

