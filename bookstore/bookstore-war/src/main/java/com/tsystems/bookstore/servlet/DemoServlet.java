package com.tsystems.bookstore.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DemoServlet extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		System.out.println("Servlet was initialyzed");
	}
	
	@Override
	public void service (HttpServletRequest request, HttpServletResponse response) throws IOException{
		System.out.println("Service method was started");
		doGet(request, response);
	}
		
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException {
//		response.setHeader("Refresh", "5");
		response.setContentType("text/html");
//		response.setContentType("image/jpeg");
		PrintWriter out = response.getWriter();
		Date today = new Date();
		String name;
		
		if (request.getAttribute("name") != null) {
			name = (String) request.getAttribute("name");
		} else {
			name = request.getParameter("name");
		}
		out.println("<html> " + 
						"<body> " + 
							"<h1 align=center>Demo Servlet</h1> " +
							"<br>" + "Current date: " + today + 
							"<br>" + "Hello " + name + 
						"</body>" + "</html>");
	}
	
}
