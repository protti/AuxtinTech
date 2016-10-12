package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import model.users.*;
@WebServlet(name = "/controller.AccessController", urlPatterns = "/access")
public class AccessController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		
		synchronized(session)
		{
			if(session.getAttribute("auto") != null)
			{
				
				if(((Boolean) session.getAttribute("auto")) == true)
				{
					Client user = (Client) session.getAttribute("user");
					req.setAttribute("ssn", user.getSsn());
					req.setAttribute("secret", session.getAttribute("pass"));
					req.setAttribute("access", true);
					
					RequestDispatcher rd = req.getRequestDispatcher("/login");
					rd.forward(req, resp);
				}
				
			}
			else
			{
				resp.sendRedirect("welcome.jsp");
			}
		}
	}
}
