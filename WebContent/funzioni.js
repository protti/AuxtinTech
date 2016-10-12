function addCart(id) {
  var xhttp;
  xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (xhttp.readyState == 4 && xhttp.status == 200) {
      document.getElementById("response").innerHTML = xhttp.responseText;
    }
  };
 
  xhttp.open("POST", "InsertCart?id="+id, true);
  xhttp.send();
}

function searchProd()
{
	var xhttp;
	var tipo = $("#general").val();
	var name = $("#in").val();
	xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (xhttp.readyState == 4 && xhttp.status == 200) {
	      document.getElementById("prod").innerHTML = xhttp.responseText;
	      
	    }
	  };
	  xhttp.open("POST", "search?tipo="+tipo+"&name="+name, true);
	  xhttp.send();
}

function wrFeed()
{
	 var xhttp;
	 var mes = $("#feed").val();
	 var admin = $("#ad").val();
	  xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (xhttp.readyState == 4 && xhttp.status == 200) {
	      document.getElementById("change").innerHTML = xhttp.responseText;
	      setTimeout(function() {
	    	  window.location.reload()
	    	}, 2000)
	    }
	  };
	  xhttp.open("POST", "feedback?adm="+admin+"&feed="+mes, true);
	  xhttp.send();
	
}

function removeCart(id,quan) {
	  var xhttp;
	  xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (xhttp.readyState == 4 && xhttp.status == 200) {
	    	if(quan > 1)
	    	{
	    		document.getElementById(id).innerHTML = xhttp.responseText;
	    	}
	    	else
	    	{
	    		document.getElementById("canc").innerHTML = xhttp.responseText;
	    		setTimeout(function() {
	  	    	  window.location.reload()
	  	    	}, 2000)
	    	}
	    }
	  };
	  
	  xhttp.open("POST", "RemoveCart?id="+id, true);
	  xhttp.send();
}

function showCustomer(str) {
	  var xhttp;
	  xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (xhttp.readyState == 4 && xhttp.status == 200) {
	     
	      document.getElementById("txtHint").innerHTML = xhttp.responseText;
	    }
	  };
	  
	  xhttp.open("GET", "disponibilita?customers="+str, true);
	  xhttp.send();
}
function notCard()
{
	var xhttp;
	var card = document.getElementById("cards");
	  xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (xhttp.readyState == 4 && xhttp.status == 200) {
	      document.getElementById("response").innerHTML = xhttp.responseText;
	      setTimeout(function() {
	    	  window.location.reload()
	    	}, 2000)
	    }
	  };
	  
	  xhttp.open("POST", "InsertCard?card="+card.value, true);
	  //xhttp.setRequestHeader("Content-Type","application/x-www-for-urlencoded");
	  xhttp.send();
}
function notAddress()
{
	var xhttp;
	var via = document.getElementById("vias").value;
	var cap = document.getElementById("caps").value;
	var civico = document.getElementById("civicos").value;
	  xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (xhttp.readyState == 4 && xhttp.status == 200) {
	      document.getElementById("respons").innerHTML = xhttp.responseText;
	      setTimeout(function() {
	    	  window.location.reload()
	    	}, 2000)
	    }
	  };
	  
	  xhttp.open("POST", "InsertAddress?via="+via+"&cap="+cap+"&civico="+civico, true);
	  //xhttp.setRequestHeader("Content-Type","application/x-www-for-urlencoded");
	  xhttp.send();
}
function insertConf(id,prod)
{
	  var xhttp;
	  xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (xhttp.readyState == 4 && xhttp.status == 200) {
	      document.getElementById(prod).innerHTML = xhttp.responseText;
	      setTimeout(function() {
	    	  window.location.reload()
	    	}, 2000)
	    }
	  };
	  
	  xhttp.open("POST", "InsertConf?id="+id, true);
	  xhttp.send();
}
function removeConf(id,prod)
{
	  var xhttp;
	  xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (xhttp.readyState == 4 && xhttp.status == 200) {
	      document.getElementById(prod).innerHTML = xhttp.responseText;
	      setTimeout(function() {
	    	  window.location.reload()
	    	}, 2000)
	    }
	  };
	  
	  xhttp.open("POST", "RemoveConf?id="+id, true);
	  xhttp.send();
}
function finish(tg)
{
	var xhttp;
	  xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (xhttp.readyState == 4 && xhttp.status == 200) {
	     
	      document.getElementById(tg).innerHTML = xhttp.responseText;
	    }
	  };
	  
	  xhttp.open("POST", "EndConf", true);
	  xhttp.send();
}