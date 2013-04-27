package com.tsystems.bookstore.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

//@ServletFilter
public class FilterDemo implements Filter {
	
	private FilterConfig config = null;
	private boolean active = false;

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		String act = config.getInitParameter("active");
		if (act != null)
			active = (act.toUpperCase().equals("TRUE"));
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (active) {
			// Здесь можно вставить код для обработки
			request.setAttribute("name", "Filter Active is TRUE");
//			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/BookServlet");
//			requestDispatcher.forward(request, response);
			chain.doFilter(request, response);
		} else {
			request.setAttribute("name", "Filter Active is FALSE");
//			RequestDispatcher requestDispatcher = request.getRequestDispatcher("book.jsf");
//			requestDispatcher.forward(request, response);
			chain.doFilter(request, response);		
		}
	}

	public void destroy() {
		config = null;
	}

}
