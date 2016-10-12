package controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import org.apache.catalina.connector.Request;

@WebServlet(name = "/controller.LogOutController", urlPatterns="/logout")
public class LogOutController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession(false);
		if(session.getAttribute("auto") != null)
		{
			if(((Boolean) session.getValue("auto")) == true)
			{
				session.setAttribute("auto", false);
			}
		}
		if(session != null) session.invalidate();
		resp.sendRedirect("access");
	}
}
