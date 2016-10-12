<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.users.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="CSS/admin.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin</title>
</head>
<body>
<%Client user = (Client)session.getAttribute("user"); %>
<%@include file="navbar.jsp" %>
<%if(user.controlAdmin()){ 
	Admin admin = user.getAdmin();


%>
	<section id="adm">
		<div id="add">
			<h3>Aggiungi un prodotto!</h3><br>
			<%@ include file="addProducts.jsp" %>
		</div>
		<div id="rem">
			<h3>Elimina o aggiungi una quantita di prodotti!</h3><br>
			<%@ include file="operProducts.jsp" %>
		</div>
		<div id="pay">
			<p>Il saldo da pagare e' = <%= admin.getToPay()%>, <button name="id" onclick="pay()"%>Paga!</button></p>
			<div id="response"></div>
		</div>
	</section>
	
<script type="text/javascript">
	function pay()
	{
		var xhttp;
		  xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (xhttp.readyState == 4 && xhttp.status == 200) {
		      document.getElementById("response").innerHTML = xhttp.responseText;
		      setTimeout(function() {
		    	  window.location.reload()
		    	}, 2000)
		    }
		  };
		  
		  xhttp.open("POST", "paySald", true);
		  //xhttp.setRequestHeader("Content-Type","application/x-www-for-urlencoded");
		  xhttp.send();

	}
</script>	
<%} %>
</body>
</html>