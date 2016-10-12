package controller;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;
import model.users.*;
import model.feedback.*;

@WebServlet("/feedback")
public class FeedbackController extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		synchronized(session)
		{
			Client user = (Client) session.getAttribute("user");
			String message = request.getParameter("feed");
			String assn = request.getParameter("adm");
			try {
				user.makeFeedback(message, assn);
				
				PrintWriter out = response.getWriter();
				out.println("Attendere prego...");
			} catch(SQLException e){
				response.sendRedirect("error500.jsp");
			}
		}
	}
}
