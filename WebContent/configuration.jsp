<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.users.*,model.configuration.*,java.util.ArrayList,model.material.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="funzioni.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="CSS/configuration.css" type="text/css">
<title>Configurazione</title>
</head>
<body>
	<%if(session.getAttribute("user") != null){ %>
	
	<%@include file="navbar.jsp" %>
		<%Configuration conf; %>
		<%if(session.getAttribute("conf") == null){ %>
			<%conf = new Configuration(); %>
			<%session.setAttribute("conf", conf); %>
		<%} else { %>
			<%conf = (Configuration) session.getAttribute("conf"); %>
		<%} %>
		<%Choose choose = new Choose(); %>
		<section id="complete">
		<h1>Configura prodotti</h1>
		<%if(conf.getMother() == null){ %>
		<%ArrayList<MotherBoard> m = choose.chooseMotherBoard(conf); %>
		<div id="sm">
		<h4>Scegli Scheda Madre</h4>
		
		<%for(MotherBoard mb: m){ %>
			<%String ul = ""; %>
			<%if(m.indexOf(mb) == m.size()-1) ul = "final";%>
			<div class="prod <%= ul%>">
			<div class="info">
			<span><b><%=mb.getName() %></b></span>
			<span><%=mb.getBand() %></span>
			<span><%=mb.getUse() %></span>
			<span><%=mb.getPrice() %></span>
			</div>
			<div class="but">
				<%if(conf.getMother() == null){ %>
					<button value=<%=mb.getId()%> onclick="insertConf(this.value,'sm')">Metti nella configurazione</button>
				<%} %>
			</div>
			</div>	
		<%} %>
		</div>
		
		<%} else {%>
			<div id="sm1">
			<div class="prod1">
			<b>Scheda Madre inserita</b>
			<button value=<%=conf.getMother().getId() %> onclick="removeConf(this.value,'sm1')">Rimuovi dalla configurazione</button>
			</div>
			</div>
		<%} %>
		
		
		
		
		<%if(conf.getCas() == null){ %>
		<%ArrayList<Case> cas = choose.chooseCase(conf); %>
		<div id="cas">
		<h4>Scegli Case</h4>
		
		<%for(Case c: cas){ %>
			<%String ul = ""; %>
			<%if(cas.indexOf(c) == cas.size()-1) ul = "final";%>
			<div class="prod <%= ul%>">
			<b><%=c.getName() %></b>
			<%=c.getBand() %>
			<%=c.getUse() %>
			<%=c.getPrice() %>
				<%if(conf.getCas() == null){ %>
					<button value=<%=c.getId() %> onclick="insertConf(this.value,'cas')">Metti nella configurazione</button>
				<%} %>
			</div>
		<%} %>	
		</div>
		
		
		<%} else { %>
			<div id="cas1">
			<div class="prod1">
			<b>Case inserito</b>
			<button value=<%=conf.getCas().getId() %> onclick="removeConf(this.value,'cas1')">Rimuovi dalla configurazione</button>
			</div>
			</div>
		<%} %>
		
		
		
		
		<%if(conf.getPower() == null){ %>
		
		
		<div id="pp">
		<%ArrayList<PowerPack> pp = choose.choosePowerPack(conf); %>
		<h4>Scegli Alimentatore</h4>
		
		<%for(PowerPack p: pp){ %>
			<%String ul = ""; %>
			<%if(pp.indexOf(p) == pp.size()-1) ul = "final";%>
			<div class="prod <%= ul%>">
			<b><%=p.getName() %></b>
			<%=p.getBand() %>
			<%=p.getUse() %>
			<%=p.getPrice() %>
				<%if(conf.getPower() == null){ %>
					<button value=<%=p.getId() %> onclick="insertConf(this.value,'pp')">Metti nella configurazione</button>
				<%} %>
			</div>
		<%} %>
		</div>
		
			
		<%} else { %>
			<div id="pp1">
				<div class="prod1">
				<b>Alimentatore inserito</b>
				<button value=<%=conf.getPower().getId() %> onclick="removeConf(this.value,'pp1')">Rimuovi dalla configurazione</button>
				</div>
			</div>
			
		<%} %>
		
		
		
		<%if(conf.getProcess() == null){ %>
		<div id="pr">
		<%ArrayList<Processor> pr = choose.chooseProcessor(conf); %>
		<h4>Scegli Processore</h4>
		
		<%for(Processor s: pr){ %><%String ul = ""; %>
			<%if(pr.indexOf(s) == pr.size()-1) ul = "final";%>
			<div class="prod <%= ul%>">
			<b><%=s.getName() %></b>
			<%=s.getBand() %>
			<%=s.getUse() %>
			<%=s.getPrice() %>
				<%if(conf.getProcess() == null){ %>
					<button value=<%=s.getId() %> onclick="insertConf(this.value,'pr')">Metti nella configurazione</button>
				<%} %>
			</div>		
		<%} %>	
		</div>

		
		<%} else { %>
		<div id="pr1">
			<div class="prod1">
			<b>Processore inserito</b>
			<button value=<%=conf.getProcess().getId() %> onclick="removeConf(this.value,'pr1')">Rimuovi dalla configurazione</button>
			</div>
		</div>
		<%} %>
		
		
		
		<%if(conf.getVideoc() == null){ %>
		<%ArrayList<VideoCard> vc = choose.chooseVideoCard(conf); %>
		<div id="vc">
		<h4>Scegli Scheda Video</h4>
		
		<%for(VideoCard v: vc){ %>
			<%String ul = ""; %>
			<%if(vc.indexOf(v) == vc.size()-1) ul = "final";%>
			<div class="prod <%= ul%>">
			<b><%=v.getName() %></b>
			<%=v.getBand() %>
			<%=v.getUse() %>
			<%=v.getPrice() %>
				<%if(conf.getVideoc() == null){ %>
					<button value=<%=v.getId() %> onclick="insertConf(this.value,'vc')">Metti nella configurazione</button>
				<%} %>
			</div>
		<%} %>	
		</div>	
		
		
		<%} else { %>
		<div id="vc1">
			<div class="prod1">
			<b>Scheda Video inserita</b>
			<button value=<%=conf.getVideoc().getId() %> onclick="removeConf(this.value,'vc1')">Rimuovi dalla configurazione</button>
			</div>
		</div>
		<%} %>
		
		
		
		<%if(conf.getDisk() == null){ %>
		<%ArrayList<HddSsd> hdd = choose.chooseDisk(conf); %>
		<div id="hdd">
		<h4>Scegli Hdd o Ssd</h4>
		
		<%for(HddSsd hd: hdd){ %>
			<%String ul = ""; %>
			<%if(hdd.indexOf(hd) == hdd.size()-1) ul = "final";%>
			<div class="prod">
			<b><%=hd.getName() %></b>
			<%=hd.getBand() %>
			<%=hd.getUse() %>
			<%=hd.getPrice() %>
				<%if(conf.getDisk() == null){ %>
					<button value=<%=hd.getId() %> onclick="insertConf(this.value,'hdd')">Metti nella configurazione</button>
				<%} %>
			</div>
		<%} %>	
			
		</div>
		
		<%} else { %>
		<div id="hdd1">
			<div class="prod1">
			<b>Hdd|Ssd inserito|a</b>
			<button value=<%=conf.getDisk().getId() %> onclick="removeConf(this.value,'hdd1')">Rimuovi dalla configurazione</button>
			</div>
		</div>
		<%} %>
		
		
		
		<%if(conf.getHeat() == null){ %>
		<%ArrayList<Heatsink> hs = choose.chooseHeatsink(conf); %>
		<div id="hs">
		<h4>Scegli Dissipatore</h4>
		
		<%for(Heatsink hp: hs){ %>
			<%String ul = ""; %>
			<%if(hs.indexOf(hp) == hs.size()-1) ul = "final";%>
			<div class="prod <%= ul%>">
			<b><%=hp.getName() %></b>
			<%=hp.getBand() %>
			<%=hp.getUse() %>
			<%=hp.getPrice() %>
				<%if(conf.getHeat() == null){ %>
					<button value=<%=hp.getId() %> onclick="insertConf(this.value,'hs')">Metti nella configurazione</button>
				
				<%} %>
			</div>
		<%} %>	
			
		</div>
		
		<%} else { %>
		<div id="hs1">
			<div class="prod1">
			<b>Dissipatore inserito</b>
			<button value=<%=conf.getHeat().getId() %> onclick="removeConf(this.value,'hs1')">Rimuovi dalla configurazione</button>
			</div>
		</div>
		<%} %>
		
		
		
		<%if(conf.getRam() == null){ %>
		<%ArrayList<Ram> ram = choose.chooseRam(conf); %>
		<div id="ram">
		<h4>Scegli Ram</h4>
		
		<%for(Ram r: ram){ %>
			<%String ul = ""; %>
			<%if(ram.indexOf(r) == ram.size()-1) ul = "final";%>
			<div class="prod <%= ul%>">
			<b><%=r.getName() %></b>
			<%=r.getBand() %>
			<%=r.getUse() %>
			<%=r.getPrice() %>
				<%if(conf.getRam() == null){ %>
					<button value=<%=r.getId() %> onclick="insertConf(this.value,'ram')">Metti nella configurazione</button>
				<%} %>
			</div>
			
		<%} %>	
			
		</div>
		
		<%} else { %>
		<div id="ram1">
			<div class="prod1">
			<b>Ram inserita</b>
			<button value=<%=conf.getRam().getId() %> onclick="removeConf(this.value,'ram1')">Rimuovi dalla configurazione</button>
			</div>
		</div>
		<%} %>
		</section>
		
		<div id="err"></div>
		
			<%if(!conf.isComplete()){ %>
				<div id="fin">
				<button onclick="finish('err')">Termina configurazione e metti nel carrello</button>
				</div>
			<%} else { %>
				<div id="fin">
				<button onclick="finish('complete')">Termina configurazione e metti nel carrello</button>
				</div>
			<%} %>
		
	<%} else {%>
		<%response.sendRedirect("sessionexp.html"); %>
	<%} %>
</body>
</html>