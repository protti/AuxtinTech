package controller;

import java.io.IOException;
import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import model.users.*;
import model.shopping.*;
@WebServlet("/InsertCard")
public class InsertCard extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		synchronized(session)
		{
			Client cl = (Client) session.getAttribute("user");
			try
			{
				TotalShop ts = cl.getTS();
				String codcard = req.getParameter("card");
				
				Connection con = Connections.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select * from carta where codcard = '"+codcard+"'");
				if(rs.next())
				{
					Card cr = new Card(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDouble(4));
					ts.takeCard(cr);
					PrintWriter pw = resp.getWriter();
					pw.println("<html><body>");
					pw.println("<h3>Carta Inserita Correttamente!</h3>");
					pw.println("</body></html>");
				}
				else
				{
					PrintWriter out = resp.getWriter();
					out.println("<h4>Questa carta è stata rifiutata, inserirne un'altra</h4>");
				}
			}
			catch(SQLException e)
			{
				resp.sendRedirect("error500.jsp");
			}
		}
	}
}
