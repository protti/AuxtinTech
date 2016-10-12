package controller;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.Properties;

import javax.servlet.annotation.*;
import model.users.Client;
import sun.misc.*;
@WebServlet(name = "/controller.LogInController", urlPatterns = {"/login"}, initParams = {
@WebInitParam(name = "passwordFile", value = "passwords.properties")})
public class LogInController extends HttpServlet{
	
	private Connection con;
	private HttpSession session;
	
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		con = Connections.getConnection();
		
	}	
	
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			
			HttpSession session = request.getSession();
			synchronized(session)
			{
				Client user = null;
				String email = request.getParameter("ssn");
				String password = Integer.toString(request.getParameter("secret").hashCode());
				String access = request.getParameter("access");
				
				session.setMaxInactiveInterval(60*60);
				if(session.getAttribute("auto") != null)
				{
					if(((Boolean) session.getAttribute("auto")) == true)
					{
						user = (Client) session.getAttribute("user");
					}
				}
				else
				{
					if(access != null)
					{
						session.setAttribute("auto", true);
						//session.putValue("user", request.getParameter("ssn"));
						
					}
				}
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select * from cliente where ssn = '" + email + "'");
				if(user == null)
				{
					if(rs.next())
					{
						if(rs.getString(6).equals(password))
						{
							user = new Client(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getInt(10),rs.getString(11));
							session.setAttribute("user", user);
						}
						else
						{
							response.sendRedirect("welcome.jsp?error=true");
						}
					}
					else
					{
						response.sendRedirect("welcome.jsp?error=true");
					}
				}
				response.sendRedirect("homepage.jsp");
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			PrintWriter wr = response.getWriter();
			wr.println("<html><title>Error page</title><body>");
			wr.println("<h1>Errore collegamento al DATABASE</h1>");
			wr.println(e.getMessage());
			wr.println("</body></html>");
		}
				
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session = req.getSession(true);
		synchronized(session)
		{
			if(session == null) resp.sendRedirect("access");
			this.doGet(req, resp);
		}
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
}
