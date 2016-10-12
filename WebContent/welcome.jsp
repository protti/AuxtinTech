<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.users.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Auxtin Tech - WELCOME</title>
<link href="CSS/welcome.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%if(session.getAttribute("user") == null){ %>
		<div class="welcome">
		<div id="log">
			<form method="post" action="login">
				<label>Log in</label><br><br>
				<span class="email">EMAIL </span><input class="email" type="text" name="ssn"><br>
				<div id="p"><span class="pass">PASSWORD </span><input class="pass" type="password" name="secret"></div><br>
				<div id="down"><span>Accedi automaticamente </span><input type="radio" name="access" value="Automatic">
				<input id="submit" type="submit" value="Log"></div>
				<%if(request.getParameter("error") != null){ %>
					<%if(request.getParameter("error").equals("true")){ %>
						<p>email o password sbagliata</p>
					<%} %>
				<%} %>
			</form>
		</div>
		
		<div id="reg">
			<h4>OPPURE</h4>
			<a href="newuser.html">Registrati ora</a>
		</div>
		</div>
		<%}else{ %>
			<%Client cl = (Client) session.getValue("user"); %>
			<%response.sendRedirect("homepage.jsp?name="+cl.getName()+"&lname="+cl.getSurname()+"&id="+cl.getId()); %>
		<%} %>
		
</body>
</html>