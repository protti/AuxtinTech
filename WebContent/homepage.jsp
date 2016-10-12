<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="CSS/home.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Homepage</title>
</head>
<body>
	<%if(session.getAttribute("user") != null){ %>
	<%Client user = (Client) session.getAttribute("user"); %>
	<%@ include file="navbar.jsp" %>
	
	<h1>Benvenuto nella homepage</h1>
	<section><%@ include file="prodotti.jsp" %></section>
	
	<%} else {%>
		<%response.sendRedirect("sessionexp.html"); %>
	<%} %>
</body>
</html>