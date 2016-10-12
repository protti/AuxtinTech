<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cerca prodotto</title>
<link rel="stylesheet" href="CSS/search.css" type="text/css">
</head>
<body>
<script src="funzioni.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js" type="text/javascript"></script>
<%@ include file="navbar.jsp" %>
<%if(session.getAttribute("user") != null){ %>
	<div id="prod-form">
	<h3>Cerca prodotti</h3>
	<form action="disponibilita" method="get"> 
		<select id="general" name="customers">
			<option value="case">Case</option>
			<option value="heatsink">Dissipatori</option>
			<option value="hdd">Dischi</option>
			<option value="motherB">Schede Madri</option>
			<option value="powerpack">Alimentatore</option>
			<option value="processor">Processori</option>
			<option value="ram">RAM</option>
			<option value="videocard">Schede Video</option>
			<option value="all">Tutti</option>
		</select>
		<input id="in" type="text"  onkeyup="searchProd()">
	</form>
	</div>
	<section>
		<div id="prod"></div>
	</section>
	
<%} %>
</body>
</html>