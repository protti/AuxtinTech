package controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HeatsinkAva
 */
@WebServlet("/disponibilita")
public class Disponibilita extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
   /* public HeatsinkAva() {
        super();
        // TODO Auto-generated constructor stub
    }
	*/
	private Connection con;
	public void init() throws ServletException
	{
		super.init();
		con = Connections.getConnection();
	}
	public void destroy()
	{
		try {
			con.close();
			con = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			con = null;
		}
	}
		
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		
		synchronized(session)
		{
		String valore = (String)req.getParameter("customers");
		PrintWriter out = resp.getWriter();
		
		out.println("<html><body>");
		out.println("<script language=\"javascript\" type=\"text/javascript\" src=\"funzioni.js\"></script>");
		
		out.println("<table> <tr> <th>Nome</th> <th>Fascia</th> <th>Uso</th> </tr>");
		if(valore.equals("heatsink") || valore.equals("all"))
		{
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from dissipatore where disp > 0");
			
			
			while(rs.next())
			{
				out.println("<tr>");
				out.println("<td>" + rs.getString(2) + "</td>");
				out.println("<td>" + rs.getString(3) + "</td>");
				out.println("<td>" + rs.getString(6) + "</td>");
				out.println("<td><a href=pageProduct.jsp?id=" + rs.getString(1) + ">Vedi pagina dedicata</td>");
				out.println("<td> <button name=id onclick=addCart(this.value) value=" + rs.getString(1) + ">Aggiungi al carrello!</button></td>");
				out.println("</tr>");
			}
			
			out.println("</body></html>");
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			resp.sendRedirect("error500.jsp");
		}		
		}
		if(valore.equals("case") || valore.equals("all"))
		{
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from cas where disp > 0");
			
			
			
			while(rs.next())
			{
				out.println("<tr>");
				out.println("<td>" + rs.getString(2) + "</td>");
				out.println("<td>" + rs.getString(3) + "</td>");
				out.println("<td>" + rs.getString(6) + "</td>");
				out.println("<td><a href=pageProduct.jsp?id=" + rs.getString(1) + ">Vedi pagina dedicata</td>");
				out.println("<td> <button name=id onclick=addCart(this.value) value=" + rs.getString(1) + ">Aggiungi al carrello!</button></td>");
				out.println("</tr>");
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			resp.sendRedirect("error500.jsp");
		}		
		}
		
		if(valore.equals("hdd") || valore.equals("all"))
		{
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from hddssd where disp > 0");
			
			
			while(rs.next())
			{
				out.println("<tr>");
				out.println("<td>" + rs.getString(2) + "</td>");
				out.println("<td>" + rs.getString(3) + "</td>");
				out.println("<td>" + rs.getString(6) + "</td>");
				out.println("<td><a href=pageProduct.jsp?id=" + rs.getString(1) + ">Vedi pagina dedicata</td>");
				out.println("<td> <button name=id onclick=addCart(this.value) value=" + rs.getString(1) + ">Aggiungi al carrello!</button></td>");
				out.println("</tr>");
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			resp.sendRedirect("error500.jsp");
		}		
		}
		if(valore.equals("motherB") || valore.equals("all"))
		{
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from schedamadre where disp > 0");
			
			
			while(rs.next())
			{
				out.println("<tr>");
				out.println("<td>" + rs.getString(2) + "</td>");
				out.println("<td>" + rs.getString(3) + "</td>");
				out.println("<td>" + rs.getString(6) + "</td>");
				out.println("<td><a href=pageProduct.jsp?id=" + rs.getString(1) + ">Vedi pagina dedicata</td>");
				out.println("<td> <button name=id onclick=addCart(this.value) value=" + rs.getString(1) + ">Aggiungi al carrello!</button></td>");
				
				out.println("</tr>");
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			resp.sendRedirect("error500.jsp");
		}		
		}
		if(valore.equals("powerpack") || valore.equals("all"))
		{
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from alimentatore where disp > 0");
			
			
			while(rs.next())
			{
				out.println("<tr>");
				out.println("<td>" + rs.getString(2) + "</td>");
				out.println("<td>" + rs.getString(3) + "</td>");
				out.println("<td>" + rs.getString(6) + "</td>");
				out.println("<td><a href=pageProduct.jsp?id=" + rs.getString(1) + ">Vedi pagina dedicata</td>");
				out.println("<td> <button name=id onclick=addCart(this.value) value=" + rs.getString(1) + ">Aggiungi al carrello!</button></td>");
				
				out.println("</tr>");
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			resp.sendRedirect("error500.jsp");
		}		
		}
		
		if(valore.equals("processor") || valore.equals("all")) 
		{
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from processore where disp > 0");
			
			
			while(rs.next())
			{
				out.println("<tr>");
				out.println("<td>" + rs.getString(2) + "</td>");
				out.println("<td>" + rs.getString(3) + "</td>");
				out.println("<td>" + rs.getString(6) + "</td>");
				out.println("<td><a href=pageProduct.jsp?id=" + rs.getString(1) + ">Vedi pagina dedicata</td>");
				out.println("<td> <button name=id onclick=addCart(this.value) value=" + rs.getString(1) + ">Aggiungi al carrello!</button></td>");
				
				out.println("</tr>");
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			resp.sendRedirect("error500.jsp");
		}		
		}
		if(valore.equals("ram") || valore.equals("all"))
		{
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from ram where disp > 0");
			
			
			while(rs.next())
			{
				out.println("<tr>");
				out.println("<td>" + rs.getString(2) + "</td>");
				out.println("<td>" + rs.getString(3) + "</td>");
				out.println("<td>" + rs.getString(6) + "</td>");
				out.println("<td><a href=pageProduct.jsp?id=" + rs.getString(1) + ">Vedi pagina dedicata</td>");
				out.println("<td> <button name=id onclick=addCart(this.value) value=" + rs.getString(1) + ">Aggiungi al carrello!</button></td>");
				
				out.println("</tr>");
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			resp.sendRedirect("error500.jsp");
		}		
		}
		
		if(valore.equals("videocard") || valore.equals("all"))
		{
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from schedavideo where disp > 0");
			
			
			while(rs.next())
			{
				out.println("<tr>");
				out.println("<td>" + rs.getString(2) + "</td>");
				out.println("<td>" + rs.getString(3) + "</td>");
				out.println("<td>" + rs.getString(6) + "</td>");
				out.println("<td><a href=pageProduct.jsp?id=" + rs.getString(1) + ">Vedi pagina dedicata</td>");
				out.println("<td> <button name=id onclick=addCart(this.value) value=" + rs.getString(1) + ">Aggiungi al carrello!</button></td>");
				
				out.println("</tr>");
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			resp.sendRedirect("error500.jsp");
		}		
		}
			
		out.println("</table>");
		out.println("</body></html>");
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
/*	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
*/
}