<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*, controller.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Prova</title>
</head>
<body>
	<%!
		public String getClient() throws SQLException
		{
			Connection con = Connections.getConnection();
	    	Statement st = con.createStatement();
	    	ResultSet rs = st.executeQuery("select nome from cliente");
	    	String names = "";
	    	while(rs.next())
	    	{
	    		names = names + " " + rs.getString(1);
	    	}
	    	return names;
	    }
	    %>
	    
	    <p><%=getClient() %></p>
	
</body>
</html>