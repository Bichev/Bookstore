<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Book Info JSP Page</title>
	</head>

	<body>
		<h1>Book Info Page</h1>
		<% System.out.println("ALWAYS DELETE SCRIPLET CRAP FROM YOUR PAGE!!!");		%>
		
		<form action="./BookServlet" method="POST">
			<table>
				<tr>
					<td>Book ID</td>
					<td><input type="text" name="bookId" value="${book.id}"></td>
				</tr>
				<tr>
					<td>Book Title</td>
					<td><input type="text" name="bookTitle" value="${book.title}"></td>
				</tr>
				<tr>
					<td>Book ISBN</td>
					<td><input type="text" name="bookISBN" value="${book.isbn}"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" name="action" value="Add"/>
						<input type="submit" name="action" value="Delete"/>
						<input type="submit" name="action" value="Search"/>												
					</td>
				</tr>				
			</table>
		</form>
		
		<br>
		
		<table border="1">
			<tr>
				<th>ID</th>
				<th>Title</th>
				<th>ISBN</th>
			</tr>
			<c:forEach items="${allBooks}" var="book">
				<tr>
					<td>${book.id}</td>
					<td>${book.title}</td>
					<td>${book.isbn}</td>										
				</tr>
			</c:forEach>						
		</table>
		
	</body>
</html>