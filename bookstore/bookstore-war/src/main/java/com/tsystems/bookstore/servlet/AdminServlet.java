package com.tsystems.bookstore.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="LoginServlet", urlPatterns={"/LoginServlet"})
public class AdminServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("text/html");

//		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/bookInfo.jsp");
//		requestDispatcher.forward(request, response);
		response.sendRedirect("http://localhost:8080/bookstore-war/" + "BookServlet");
	}

}
