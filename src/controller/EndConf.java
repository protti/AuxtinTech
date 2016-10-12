package controller;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import model.configuration.*;
import model.users.*;
import model.material.*;
import model.shopping.*;
import java.sql.*;

@WebServlet("/EndConf")
public class EndConf extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		synchronized(session)
		{
			Client user = (Client) session.getAttribute("user");
			
			try{
				TotalShop shop = user.getTS();
				Configuration conf = (Configuration) session.getAttribute("conf");
				PrintWriter pw = response.getWriter();
				if(!conf.isComplete())
				{
					pw.println("<h4>Si prega inserire i prodotti mancanti</h4>");
				}
				else
				{
					shop.addInCartAb(conf.getCas());
					shop.addInCartAb(conf.getDisk());
					shop.addInCartAb(conf.getHeat());
					shop.addInCartAb(conf.getMother());
					shop.addInCartAb(conf.getPower());
					shop.addInCartAb(conf.getProcess());
					shop.addInCartAb(conf.getRam());
					shop.addInCartAb(conf.getVideoc());
					session.setAttribute("conf", null);
					pw.println("<h4>Configurazione terminata</h4>");
					pw.println("<a href=\"homepage.jsp?name="+user.getName()+"&lname="+user.getSurname()+"&id="+user.getId()+"\">Torna alla home >></a>");
					
				}
				
			} catch(SQLException e){
				response.sendRedirect("error500.jsp");
			}
		}
	}
}
