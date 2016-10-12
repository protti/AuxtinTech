package controller;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;
import model.users.*;
import model.shopping.*;

@WebServlet("/payone")
public class PayOneProductController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		synchronized(session)
		{
			Client user = (Client) session.getAttribute("user");
			try {
				TotalShop shop = user.getTS();
				int quan = Integer.parseInt(req.getParameter("quan"));
				String id = req.getParameter("id");
				int q = shop.getQuantity(shop.searchMat(id));
				
				shop.payOneProduct(shop.searchMat(id), quan);
				if(quan <= q)
				{
					PrintWriter pw = resp.getWriter();
					pw.println("<h1>Acquisto avvenuto con successo</h1>");
					pw.println("<a href='homepage.jsp'>Ritorna alla homepage >></a>");
				}
				else
				{
					PrintWriter pw = resp.getWriter();
					pw.println("<p>La quantità inserita è maggiore rispetto a quella presa</p>");
					pw.println("<a href='homepage.jsp'>Ritorna alla homepage >></a>");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				resp.sendRedirect("error500.jsp");
			} catch (ProductNotSaldableException e1) {
				resp.sendRedirect("errorSald.jsp");
			}
			
		}
	}
}

