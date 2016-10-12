<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Aggiungi prodotto</title>
</head>
<body>

<p>Seleziona il prodotto da aggiungere!</p>
<div id="response"></div>
<form action="disponibilita" method="get"> 
	<select name="customers" onchange="showField(this.value)">
		<option value="case">Case</option>
		<option value="heatsink">Dissipatori</option>
		<option value="hdd">Dischi</option>
		<option value="motherB">Schede Madri</option>
		<option value="powerpack">Alimentatore</option>
		<option value="processor">Processori</option>
		<option value="ram">RAM</option>
		<option value="videocard">Schede Video</option>
		
	</select>
</form>
<form action="addProd" method="post">
		
		Inserire Nome: <input type="text" name="name"><br>
		Inserisci fascia media: <input type="text" name="band"><br>
		Inserire Uso: <input type="text" name="use"><br>	
		Inserire Prezzo: <input type="text" name="price"><br>
		Inserire Quantità: <input type="text" name="quantita"><br>
		Inserire Descrizione: <input type="text" name="description"><br>
		<div id="campi">
		
		</div>
		
		<input type="submit" value="Inserisci prodotto!"><br>


</form>




<script type="text/javascript">
function showField(tipo) {
	  var xhttp;
	  xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (xhttp.readyState == 4 && xhttp.status == 200) {
	    	
	    		document.getElementById("campi").innerHTML = xhttp.responseText;
	    		
	    }
	      
	  };

	  xhttp.open("POST", "showField?tipo="+tipo, true);
	  xhttp.send();
}




</script>



</body>
</html>