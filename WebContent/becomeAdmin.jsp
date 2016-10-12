<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.users.*,model.shopping.*,java.util.ArrayList,model.material.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="CSS/badmin.css" type="text/css">
<meta charset="UTF-8">
	<title>Diventa admin</title>
</head>
<body>
	<script src="funzioni.js"></script>
	<%@include file="navbar.jsp" %>
	<%if(session.getAttribute("user") != null){ %>
		<%Client user = (Client) session.getAttribute("user"); 
		TotalShop ts = user.getTS();
		if(ts.getCard() != null){ %>
			
			<div id="abbonamento">
			<h2>Scegli abbonamento</h2>
			<form action="paySubs" method ="post">
			<table> <tr> <th id="mon1"><b>COSTO: 10€</b><div><input type="submit" name="m1" value="Abbonamento Mensile"></input></div></th></tr> 
					<tr> <th id="mon3"><b>COSTO: 25€</b><div><input type="submit" name="m3" value="Abbonamento Trimestrale"></input></div></th></tr>
					<tr> <th id="mon6"><b>COSTO: 55€</b><div><input type="submit" name="m6" value="Abbonamento Semestrale"></input></div></th></tr>
					<tr> <th id="mon12"><b>COSTO: 95€</b><div><input type="submit" name="m12" value="Abbonamento Annuale"></input></div></th></tr>
			</table>
			</form>
			</div>
		<% }else{ %>
		<div id="response"><h3>Inserisci prima la carta</h3>	
		<input type="text" id="cards"> <button onclick="notCard()">Inserisci Carta!</button>
		</div>
		<% } %>
	<% } %>

</body>
</html>