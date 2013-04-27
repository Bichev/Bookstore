package com.tsystems.bookstore.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tsystems.bookstore.ejb.service.BookService;
import com.tsystems.bookstore.persistence.entity.Book;

@WebServlet(name="BookServlet", urlPatterns={"/BookServlet"})
public class BookServlet extends HttpServlet {
	
	@Inject
	private BookService bookService;
	
	protected void processRequest (HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String action = request.getParameter("action");
		
		String bookIdStr = request.getParameter("bookId");
		
		Book book;

		if (bookIdStr != null && !"".equals(bookIdStr)) {
			int bookId = Integer.parseInt(bookIdStr);
			
			String bookTitle = request.getParameter("bookTitle");
			String bookISBN = request.getParameter("bookISBN");
			
			book = new Book(bookId, bookTitle, bookISBN);
			
			if (ActionType.ADD.equals(action)) {
				bookService.createOrUpdateBook(book);
			}  else if (ActionType.DELETE.equals(action)) {
				bookService.deleteBookByID(bookId);
			} 
			
			request.setAttribute("book", book);
			
			request.setAttribute("allBooks", bookService.getBooks());
		}
		
		if (ActionType.SEARCH.equals(action)) {
			request.setAttribute("allBooks", bookService.getBooks());
		}
//		request.setAttribute("allBooks", bookService.getBooks());
		request.getRequestDispatcher("jsp/bookInfo.jsp").forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
	
	
	
}
