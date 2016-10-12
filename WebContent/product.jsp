<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   




<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Prodotti in vendita</title>
</head>
<body>
<form action="Avaylability" method="get"> 
<select name="customers" onchange="showCustomer(this.value)">
<option value="">Tutto</option>
<option value="Heatsink">Dissipatori</option>
</select>
</form>
<br>
<div id="txtHint">Customer info will be listed here...</div>
<script>
function showCustomer(str) {
  var xhttp;    
  if (str == "All") {
    document.getElementById("txtHint").innerHTML = "";
    return;
  }
  xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (xhttp.readyState == 4 && xhttp.status == 200) {
      document.getElementById("txtHint").innerHTML = xhttp.responseText;
    }
  };
  xhttp.open("GET", "disponibilita?customers=heatsink", true);
  xhttp.send();
}
</script>




</body>
</html>