package controller;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import model.users.*;
import model.shopping.*;

@WebServlet("/InsertAddress")
public class InsertAddress extends HttpServlet{
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
				int civic = Integer.parseInt(req.getParameter("civico"));
				Address a = new Address(ts.getCod(),req.getParameter("via"),cl.getSsn(),req.getParameter("cap"),civic);
				ts.takeAddress(a);
				PrintWriter pw = resp.getWriter();
				pw.println("<html><body>");
				pw.println("<h3>Indirizzo Inserita Correttamente!</h3>");
				pw.println("</body></html>");
			}
			catch(SQLException e)
			{
				resp.sendRedirect("error500.jsp");
			}
		}
	}
}
