package controller;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;

import model.users.*;
import model.material.Material;
import model.shopping.*;

@WebServlet("/RemoveCart")
public class RemoveCart extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		synchronized(session)
		{
			Client user = (Client) session.getAttribute("user");
			String idProdotto = req.getParameter("id");
			try
			{
				TotalShop ts = user.getTS();
				Material m = ts.searchMat(idProdotto);
				ts.removeFromCart(m);
				resp.setContentType("text/html");
				PrintWriter pw = resp.getWriter();
				if(ts.existInCart(m))
				{
					pw.println(ts.getQuantity(m));
				}
				else
				{
					pw.println("<h3>Prodotto rimosso correttamente!!!</h3>");
				}
				//pw.println("<h3>Oggetto rimosso correttamente!</h3>");
				//pw.println("</body></html>");
				
			}
			catch(SQLException e)
			{
				resp.sendRedirect("error500.jsp");
			}
		}
	}
}
