package controller;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import model.users.*;
import model.shopping.*;
@WebServlet("/payprod")
public class PayProductController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		synchronized(session)
		{
			Client user = (Client) session.getAttribute("user");
			if(user != null)
			{
				String id = req.getParameter("id");
				try {
					TotalShop ts = user.getTS();
					
					
					
					ts.payProducts();
					
					PrintWriter pw = resp.getWriter();
					pw.println("<h1>Acquisto avvenuto con successo</h1>");
					pw.println("<a href='homepage.jsp'>Ritorna alla homepage >></a>");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					resp.sendRedirect("error500.jsp");
				} catch (ProductNotSaldableException e) {
					// TODO Auto-generated catch block
					resp.sendRedirect("errorSald.jsp");
				}
			}
		}
	}
}
