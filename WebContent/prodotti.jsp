<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.lang.*,java.io.*,java.sql.*,java.util.*,model.material.Heatsink, controller.Connections, model.users.*"%>
   
<script src="funzioni.js"></script>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Prodotti in vendita</title>
</head>
<body>

<% Client clie = (Client)session.getValue("user"); %>

<%!

public ArrayList<Heatsink> getHeatsinkAvaiable() throws SQLException
{
	ArrayList<Heatsink> HeatAva = new ArrayList<Heatsink>(); 
	Connection con = Connections.getConnection();
	Statement st = con.createStatement();
	ResultSet rs = st.executeQuery("select * from dissipatore where disp > 0");
	while(rs.next())
	{
		HeatAva.add(new Heatsink(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(11),rs.getString(7),rs.getString(8),rs.getDouble(9),rs.getInt(10)));
	}
	return HeatAva;
}
%>
<div id="response"></div>
<div id="bar">
<div id="subbar">
<div id="text"><h3>Scegli tipologia prodotto </h3></div>
<div id="choose" ><form id="sct" action="disponibilita" method="get"> 
	
	<select name="customers" onchange="showCustomer(this.value)">
		<option value="heatsink">Dissipatori</option>
		<option value="case">Case</option>
		<option value="hdd">Dischi</option>
		<option value="motherB">Schede Madri</option>
		<option value="powerpack">Alimentatore</option>
		<option value="processor">Processori</option>
		<option value="ram">RAM</option>
		<option value="videocard">Schede Video</option>
		<option value="all">Tutto</option>
	</select>
</form>
</div>
</div>
</div>
<br>


<div id="txtHint">
<div id="square">
	<%ArrayList<Heatsink> hs = getHeatsinkAvaiable();
		if(hs != null)
		{
			for(Heatsink hss :hs)
			{%>
				<div>
					<div class="prod" style="background-image: url(CSS/image/dissi.jpg); background-size: contain; background-repeat: no-repeat">
						
						<p class="name"> <%=hss.getName()%> </p>
						<p class="band"> <%=hss.getBand()%> </p>
						<p class="use"> <%=hss.getUse()%> </p>
						<div class="anima">
							<a href="pageProduct.jsp?id=<%=hss.getId() %>"><img src="lente.png" ></a>
							<span><button name="id" onclick="addCart(this.value)" value=<%=hss.getId() %>><img src="carrello2.png"></button></span>
						</div>
					</div>
				</div>
			<% 
			}
			
		}
	%>
</div>
</div>

</body>
</html>