<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.users.*,model.shopping.*,java.util.ArrayList,model.material.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Carrello</title>
<link rel="stylesheet" href="CSS/cart.css" type="text/css">
</head>
<body>
	<script src="funzioni.js"></script>
	<%if(session.getAttribute("user") != null){ %>
	<%@include file="navbar.jsp" %>
	<h1>Carrello</h1>
	
	<%Client user = (Client) session.getAttribute("user"); 
		TotalShop ts = user.getTS();
		ArrayList<Material> cart;
		if(ts != null) cart = ts.getCart();
		else cart = null;%>
		
		<%if(cart != null){ %>
			<% if(cart.size() != 0) {%> 
				<section id="product">
				<h4>Ecco i tuoi prodotti:</h4>
				<div id="square">
				<%	String mat;
					for(Material prod : cart){ 
					
						String id = prod.getId();
						if(id.charAt(0) == '0')
						{
							mat = "mother.jpg";
						}
						else if(id.charAt(0) == '1')
						{
							mat = "case.png";
						}
						else if(id.charAt(0) == '2')
						{
							mat = "alimenta.png";
						}
						else if(id.charAt(0) == '3')
						{
							mat = "dissi.jpg";
						}
						else if(id.charAt(0) == '4')
						{	
							mat = "ssdhhd.jpg";
						}
						else if(id.charAt(0) == '5')
						{
							mat = "ram.jpg";
						}
						else if(id.charAt(0) == 'A')
						{	
							mat = "schedavideo.jpg";
						}
						else
						{
							mat = "processor.jpg";
						}%>
					<div class="change-pos">
					<div id="canc" class="prod" style='background-image: url(CSS/image/<%=mat%>); background-size: contain; background-repeat: no-repeat'>
					<div class="info">
					<span class="name"><%=prod.getName() %></span>
					<span class="band"><%=prod.getBand() %></span>
					<span class="use"><%=prod.getUse() %></span>
					<span class="price"><%=prod.getPrice() %></span>
					<span class="quan" id=<%=prod.getId() %>><%=ts.getQuantity(prod) %></span>
					</div>
					<div class="choose">
					<span class="rem"><button name="id" onclick="removeCart(this.value,<%=ts.getQuantity(prod)%>)" value=<%=prod.getId()%> alt="Rimuovi prodotto"><img width="20px" height="20px" src="trash.gif"></button></span>
				<%if(ts.getCard() != null && ts.getAddress() != null){ %>
				<span class="take-it"><form action="payone" method="post">
					<span><input type="number" name="quan" value=<%=ts.getQuantity(prod) %>></span>
					<input type="hidden" name="id" value=<%=prod.getId()%>>
					<input id="take" type="submit" value="" alt="Compralo">
				</form></span><br>
				<%} %>
					</div>
					</div>
					</div>
				<%} %>	
				</div>
				</section>
				<%if(ts.getCard() != null && ts.getAddress() != null){ %>
				
				<section id="pay">
				<form action="payprod" method="post">
					<input type="submit" value="Compra Carrello">
				</form>
				</section>
				<%} %>	
			<%} else { %>
				<div id="nothing">
				<h4>Sembra che tu non abbia nessun prodotto nel carrello</h4>
				</div>
			<%}
			if(ts.getCard() == null){ %>
							
			<div id="response">
				<h4>Sembra che tu non abbia una carta, aggiungine prima una!</h4>
				<input type="text" id="cards">
				<button onclick="notCard()">Inserisci Carta</button>
			</div>
			<%} if(ts.getAddress() == null){%>
			<div id="respons">
				<h4>Sembra che tu non abbia un indirizzo di spedizione, aggiungine prima uno!</h4>
				<div>Via <input type="text" id="vias" ></div><br>
				<div>Cap <input type="text" id="caps"></div><br>	
				<div>Civico <input type="text" id="civicos"></div><br>
				<button onclick="notAddress()">Inserisci Indirizzo</button>
			</div>
			<%} %>
			<div id="nien"></div>
		<%} else {%>
			<h1>Inserisci la tua carta (PostePay, Visa, MasterCard) per l'acquisto</h1>
		<%} %>
		<%} else{ %>
			<%response.sendRedirect("sessionexp.html"); %>
		<%} %>
		
		
		
</body>
</html>