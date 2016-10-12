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
@WebServlet("/RemoveConf")
public class RemoveConf extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		synchronized(session)
		{
			Client user = (Client) session.getAttribute("user");
			
			try {
				TotalShop shop = user.getTS();
				Configuration conf = (Configuration) session.getAttribute("conf");
				String id = request.getParameter("id");
				
				Material m = shop.searchMat(id);
				m.addAvaylability(1);
				if(m instanceof MotherBoard) conf.setMother(null);
				else if(m instanceof HddSsd) conf.setDisk(null);
				else if(m instanceof Case) conf.setCas(null);
				else if(m instanceof Heatsink) conf.setHeat(null);
				else if(m instanceof Processor) conf.setProcess(null);
				else if(m instanceof VideoCard) conf.setVideoc(null);
				else if(m instanceof PowerPack) conf.setPower(null);
				else if(m instanceof Ram) conf.setRam(null);
				else response.sendRedirect("");
				session.setAttribute("conf", conf);
				PrintWriter pw = response.getWriter();
				
				pw.println("<h4>Prodotto rimosso</h4>");
			} catch(SQLException e) {
				response.sendRedirect("error500.jsp");
			} 
			
		}
	}
}
