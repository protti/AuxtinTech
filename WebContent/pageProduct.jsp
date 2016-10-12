<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,controller.Connections, java.sql.*, model.material.*"  %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="navbar.jsp" %>
<%!
	String mat;

	public Material getType(String id)
	{
		String str;
		try
		{
			if(id.charAt(0) == '0')
			{
				str = "schedamadre";
				mat = "mother.jpg";

			}
			
			else if(id.charAt(0) == '1')
			{
				str = "cas";
				mat = "case.png";

			}
			
			
			else if(id.charAt(0) == '2')
			{
				
				str = "alimentatore";
				mat = "alimenta.png";

			}
			
			else if(id.charAt(0) == '3')
			{
				str = "dissipatore";
				mat = "dissi.jpg";

			}
			
			else if(id.charAt(0) == '4')
			{
				str = "hddssd";
				mat = "ssdhhd.jpg";

			}
			
			else if(id.charAt(0) == '5')
			{
				str = "ram";
				mat = "ram.jpg";

			}
			
			else if(id.charAt(0) == 'A')
			{
				str = "schedavideo";
				mat = "schedavideo.jpg";

			}
			else
			{
				str = "processore";
				mat = "processor.jpg";

			}
			
		Connection con = Connections.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from " + str + " where codiceid='" + id + "'");
		//System.out.println("ci sono");
		rs.next();
		if(str.equals("schedamadre"))
		{
			MotherBoard motherB = new MotherBoard(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(13),rs.getString(7),rs.getString(8),rs.getDouble(9),rs.getDouble(10),rs.getInt(11),rs.getDouble(12),rs.getInt(14));		
			return motherB;
		}
		else if(str.equals("cas"))
		{
			Case cas = new Case(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(12),rs.getString(7),rs.getDouble(8),rs.getDouble(9),rs.getDouble(10),rs.getString(11));		
			return cas;
		}
		else if(str.equals("alimentatore"))
		{
			
			//String id, String name, String band, int disp, String description, String use, double price, double mW, int atx12v, String certificate
			System.out.println(rs.getString(10));
			PowerPack powerP = new PowerPack(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(10),rs.getDouble(7),rs.getInt(9),rs.getString(8));			
			return powerP;
		}
		else if(str.equals("dissipatore"))
		{
			Heatsink diss = new Heatsink(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(11),rs.getString(7),rs.getString(8),rs.getDouble(9),rs.getInt(10));			
			return diss;
		}
		else if(str.equals("hddssd"))
		{
			//String id, String name, String band, int disp, String description, String use, double price, double inch, double sata
			HddSsd disk = new HddSsd(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(9),rs.getDouble(7),rs.getDouble(8));			
			return disk;
		}
		else if(str.equals("ram"))
		{
			//String id, String name, String band, int disp, String description, String use, double price, String model, double frequency, String heatsink
			Ram ram = new Ram(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(10),rs.getString(7),rs.getDouble(8),rs.getString(9));			
			return ram;
		}
		else if(str.equals("schedavideo"))
		{
			//String id, String name, String band, int disp, String description, String use, double price, int port, int PCIe, double height,double width, double depth
			VideoCard videoC = new VideoCard(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(12),rs.getInt(7),rs.getInt(8),rs.getDouble(9),rs.getDouble(10),rs.getDouble(11));			
			return videoC;
		}
		else
		{
			//String id, String name, String band, int disp, String description, String use, double price, String socket
			Processor proces = new Processor(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(8),rs.getString(7));			
			return proces;
		}
		
		

		
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
		
	}%>

<%
	String valore = request.getParameter("id");
	Material materiale = getType(valore);
	String nome = null;
	if(materiale != null)
	{
		nome = materiale.getName();
	}
%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="CSS/pageProduct.css" type="text/css">

</head>

<body>
<div id="pic" style='background-image: url(CSS/image/<%=mat%>); background-size: contain; background-repeat: no-repeat' >
</div>
<section id="info"> 
 
 		
		<div id="geninfo">
			<h3>Informazioni generali</h3>
			<div><b>Nome</b> <%=materiale.getName()%> </div>
			<div><b>Prezzo</b> <%=materiale.getPrice()%>€ </div>
			<div><b>Fascia</b> <%=materiale.getBand()%> </div>
			<div><b>Uso</b> <%=materiale.getUse()%> </div>
			<div><b>Disponibilità</b> <%=materiale.getAvaylability()%> </div>
		</div>
		
		<div id="specinfo">
		<h3>Informazioni specifiche</h3>
		<%if(materiale instanceof Heatsink) {
			Heatsink dissi = (Heatsink) materiale;
		%>
		<div><b>Tipo</b> <%=dissi.getType()%> </div>
		<div><b>Socket</b> <%=dissi.getSocketP()%> </div>
		<div><b>Altezza</b> <%=dissi.getHeight()%> </div>
		<div><b>Livello Ingombranza</b> <%=dissi.getEncumberance()%> </div>
		<%} %>
		<%if(materiale instanceof MotherBoard) {
			MotherBoard motherB = (MotherBoard) materiale;%>
		<div><b>Formato</b> <%=motherB.getFormat()%> </div>
		<div><b>Socket</b> <%=motherB.getSocket()%> </div>
		<div><b>Max Frequenza</b> <%=motherB.getMfr()%> </div>
		<div><b>PCIe</b> <%=motherB.getPCIe()%> </div>
		<div><b>Porte</b> <%=motherB.getPort()%> </div>
		<div><b>Sata</b> <%=motherB.getSata()%> </div>
		<div><b>ATX12V</b> <%=motherB.getAtx12v()%> </div>
		<%} %>
		<%if(materiale instanceof Case) {
			Case cas = (Case) materiale;%>
		<div><b>Formato</b> <%=cas.getFormat()%> </div>
		<div><b>Larghezza</b> <%=cas.getWidth()%> </div>
		<div><b>Altezza</b> <%=cas.getHeight()%> </div>
		<div><b>Profondità</b> <%=cas.getDepth()%> </div>
		<div><b>Watercooling</b> <%=cas.getWatercooling()%> </div>
		<%} %>
		<%if(materiale instanceof HddSsd) {
			HddSsd disk = (HddSsd) materiale;%>
		<div><b>Pollici</b> <%=disk.getInch()%> </div>
		<div><b>Sata</b> <%=disk.getSata()%> </div>
		<%} %>
		<%if(materiale instanceof Processor) {
			Processor proces = (Processor) materiale;%>
		<div><b>Socket</b> <%=proces.getSocket()%> </div>
		<%} %>
		<%if(materiale instanceof PowerPack) {
				PowerPack power = (PowerPack) materiale;%>
		<div><b>Max Watt</b> <%=power.getMW()%> </div>
		<div><b>Classe</b> <%=power.getClass()%> </div>
		<div><b>Atx12V</b> <%=power.getAtx12v()%> </div>
		<%} %>
		<%if(materiale instanceof Ram) {
				Ram ram = (Ram) materiale;%>
		<div><b>Modello</b> <%=ram.getModel()%> </div>
		<div><b>Frequenza</b> <%=ram.getFrequency()%> </div>
		<div><b>Dissipazione</b> <%=ram.getHeatsink()%> </div>
		<%} %>
		<%if(materiale instanceof VideoCard) {
				VideoCard video = (VideoCard) materiale;%>
		<div><b>Porte</b> <%=video.getPort()%> </div>
		<div><b>Altezza</b> <%=video.getHeight()%> </div>
		<div><b>Profondità</b> <%=video.getDepth()%> </div>
		<div><b>Larghezza</b> <%=video.getWidth()%> </div>
		<div><b>PCIe</b> <%=video.getPCIe()%> </div>
		<%} %>
		</div>
		<div id="description">
		<h3>Breve Descrizione</h3>
		<%if(materiale.getDescription() != null && materiale.getDescription() != ""){ %>
		<div><%=materiale.getDescription()%> </div>
		<%} else { %>
			<h4>Descrizione non presente</h4>
		<%} %>
		</div>

</section>



</body>
</html>