<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.users.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inserisci carta acquisto</title>
</head>
<body>
	<%Client cl = (Client) session.getValue("user");%>
	<%if(cl != null){ %>
		<form action="card" method="post">
			<input type="text" name="cod">
			<input type="submit" name="inserisci">
		</form>
	<%} %>
</body>
</html>