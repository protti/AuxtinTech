<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.users.*,java.sql.*,controller.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="CSS/style.css" rel="stylesheet" type="text/css">
<link href="CSS/responsive.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="page">
	<%String adm,become,cliente1,home,carr,configur,cerca; %>
	<%Client user1; %>
	<%if(session.getAttribute("user") != null){ %>
	<%user1 = (Client) session.getAttribute("user");%>
	<%cliente1 = "user.jsp?name="+user1.getName()+"&lname="+user1.getSurname()+"&id="+user1.getId()+"";%>
	<%home = "homepage.jsp"; %>
	<%carr = "cart.jsp"; %>
	<%become = "becomeAdmin.jsp"; %>
	<%configur = "configuration.jsp"; %>
	<%adm = "adminPage.jsp"; %>
	<%cerca = "searchPage.jsp"; %>
	<%} else {%>
	 <%user1 = null; %>
	<%configur = carr = home = adm = cliente1 = become = cerca = "sessionexp.html"; %>
	<%} %>
	<div id="navigation">
	<%if(session.getAttribute("user") != null){%>
	
	<ul>
	<li><a href=<%=home %>>Home</a></li>
	<li><a href=<%=cliente1 %>>Profilo</a></li>
	<li><a href=<%=carr %>>Carrello</a></li>
	<li><a href=<%=configur %>>Configura</a></li>
	<li><a href=<%=cerca %>>Cerca</a></li>
	<li><form method="get" action=" logout" style="text-decoration: none;">
		<input id="submit" type="submit" value="Logout">
	</form></li>
	<%if(!user1.controlAdmin()){ %>
	<li><a href=<%=become %> >Diventa Admin</a></li>
	<%} else {%>
	<li><a href=<%=adm %>>Admin page</a></li>
	<%} %>
	</ul>
	</div>
	</div>
	<%} %>

</body>
</html>