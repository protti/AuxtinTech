<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.users.*,java.sql.*,controller.*,java.util.ArrayList,model.material.*,model.feedback.*" session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="funzioni.js" type="text/javascript"></script>	
<%if(session.getAttribute("user") != null){ %>
<%Client user = (Client) session.getAttribute("user"); %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome <%=user.getName() %></title>
<link href="CSS/profilo.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%@include file="navbar.jsp" %>
	<section id="info">
		<%boolean ad = false,exists = false; %>
		<%Admin admin = null; %>
		<%if(user.getName().equals(request.getParameter("name")) && user.getSurname().equals(request.getParameter("lname")) && user.getId() == Integer.parseInt(request.getParameter("id"))){ %>
			<h1>Benvenuto <%=request.getParameter("name") + " " + request.getParameter("lname")%></h1>
			
		<%}%>
		
		<%Connection con = Connections.getConnection(); %>
		<%Statement st = con.createStatement(); %>
		<%Statement st1 = con.createStatement(); %>
		<%ResultSet rs = st.executeQuery("select * from cliente where nome = '" + request.getParameter("name") + "' AND cognome = '" + request.getParameter("lname") + "' AND id = " + request.getParameter("id") + ""); %>
		<%exists = rs.next();%>
		<%if(exists){ %>
			<%ResultSet rs1 = st1.executeQuery("select * from admin where ssn = (select ssn from cliente where nome = '" + request.getParameter("name") + "' AND cognome = '" + request.getParameter("lname") + "' AND id = " + request.getParameter("id") + ")"); %>
			<%ad = rs1.next(); %>
			<h4>Informazioni</h4>
			<div>
			<img id="uimm" src="profilo.png" width="250px" height="250px">
			<ul>
				<li><b>Nome</b>               <%=user.getName() %> <%=user.getSurname() %></li>
				<li><b>Anno di nascita</b>    <%=user.getBirthdate() %></li>
				<li><b>Età</b>                <%=user.getAge() %></li>
				<li><b>Residente</b>          <%=user.getCity() %></li>
				<%if(ad){ %>
				<li><b>Email</b>              <%=user.getSsn() %></li>
				<li><b>Numero telefono</b>    <%=user.getNumber() %></li>
				<%} %>
			</ul>
			</div>
	</section>
	
		<%if(ad){ %>
			<%admin = new Admin(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getInt(10),rs.getString(11),rs1.getInt(4),rs1.getBoolean(5),rs1.getDouble(2),rs1.getDouble(3),rs1.getString(6)); %>
			<section id="product">
				<h4>Prodotti in vendita</h4>
				<div id="square">
				<%ArrayList<Material> hs = admin.getMyProducts();
				if(hs != null)
				{	
					for(Material hss : hs)
					{%>
						<div>
							<div class="prod">
						
								<p class="name"> <%=hss.getName()%> </p>
								<p class="band"> <%=hss.getBand()%> </p>
								<p class="use"> <%=hss.getUse()%> </p>
								<div class="anima">
									<a href="pageProduct.jsp?id=<%=hss.getId() %>"><img src="lente.png" ></a>
							
									<%if(user.getAdmin() != null){ %>
									<%if(!user.getAdmin().equals(admin)){ %>
								 		<span><button name="id" onclick="addCart(this.value)" value=<%=hss.getId() %>><img src="carrello.png"></button></span>
									<%} %>
								<%} else {%>
							 		<span><button name="id" onclick="addCart(this.value)" value=<%=hss.getId() %>><img src="carrello.png"></button></span>
								<%} %>
								</div>
							</div>
						</div>
					<% 
					}
			
				}
			%>
		</div>
			</section>
			
			
			
			<section id="feedback">
				<h4>Feedback</h4>
				<%ArrayList<Feedback> fd = admin.myFeedback(); %>
				<%if(fd.size() == 0){ %>
					<h4>Non c'è nessun feedback</h4>
				<%} else {%>
					<%for(Feedback f: fd){ %>
						<%String ussn = f.getUssn(); %>
						<%String us = ussn.split("@")[0]; %>
						<b><%=us %> </b><span>ha scritto:</span>
						<p><%=f.getMessage() %></p><br>
					<%} %>
				<%} %>
				<script src="//ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js" type="text/javascript"></script>
				<div class="comment" id="change">
					<b><%= user.getSsn().split("@")[0] %></b> commenta:<br>
					<textarea id="feed"></textarea><br>
					<input id="ad" type="hidden" value=<%=admin.getSsn()%>>
					<button type="submit" onclick="wrFeed()">Commenta</button>
				</div>
			</section>
		<%} %>
	<%} else { %>
		<h1>Utente non esistente</h1>
	<%} %>
<%} else {%>
	<%response.sendRedirect("sessionexp.html"); %>
<%} %>
</body>
</html>