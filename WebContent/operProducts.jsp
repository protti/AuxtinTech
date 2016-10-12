<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.users.*,java.util.*,model.material.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Elimina Prodotti</title>
</head>
<body>

<%
	Client cliente2 = (Client)session.getAttribute("user");
	
	ArrayList<Material> cartAdmin = new ArrayList<Material>();
	if(cliente2!= null)
	{
		Admin ad = cliente2.getAdmin();
		if(ad != null)
		{
			cartAdmin = ad.getMyProducts();
		}
	%>
	<% for(Material prod : cartAdmin)
	{%>
		
					<%=prod.getName() %>
					<%=prod.getBand() %>
					<%=prod.getUse() %>
					<%=prod.getPrice() %>
					<%=prod.getAvaylability()%>
					<input type="text" id=<%=prod.getId()%>>
					<button name="id" onclick="removeProducts(this.value)" value=<%=prod.getId()%>>Rimuovi</button>
					<button name="ids" onclick="addProducts(this.value)" value=<%=prod.getId()%>>Aggiungi</button><br>

	<%}%>
	<script>
	function removeProducts(id)
	{
		var number = document.getElementById(id).value;
		 xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (xhttp.readyState == 4 && xhttp.status == 200) {		    	
		    		document.getElementById("response").innerHTML = xhttp.responseText;
		    		setTimeout(function() {
		  	    	  window.location.reload()
		  	    	}, 2000)
		    }
		  };
		  xhttp.open("POST", "RemoveProducts?id="+id+"&number="+number, true);
		  xhttp.send();
	}
	function addProducts(id)
	{
		var number = document.getElementById(id).value;
		 xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (xhttp.readyState == 4 && xhttp.status == 200) {		    	
		    		document.getElementById("response").innerHTML = xhttp.responseText;
		    		setTimeout(function() {
		  	    	  window.location.reload()
		  	    	}, 2000)
		    }
		  };
		  xhttp.open("POST", "AddProducts?id="+id+"&number="+number, true);
		  xhttp.send();
	}
	</script>
	<%} %>
	
	
	
	
	<div id="response"></div>


</body>
</html>