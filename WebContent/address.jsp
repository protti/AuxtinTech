<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.users.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inserisci indirizzo</title>
</head>
<body>
	<%if(session.getValue("user") != null) {%>
	<form action="address" method="post">
		<input type="text" name="via">
		<input type="text" name="cap">
		<input type="text" name="civico">
		<input type="submit" value="Inserisci">
	</form>
	<%} else { %>
		<% %>
	<%} %>
</body>
</html>