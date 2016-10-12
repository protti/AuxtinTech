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
@WebServlet("/InsertConf")
public class InsertConf extends HttpServlet{

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
				if(m instanceof MotherBoard) conf.setMother((MotherBoard) m);
				else if(m instanceof HddSsd) conf.setDisk((HddSsd) m);
				else if(m instanceof Case) conf.setCas((Case) m);
				else if(m instanceof Heatsink) conf.setHeat((Heatsink) m);
				else if(m instanceof Processor) conf.setProcess((Processor) m);
				else if(m instanceof VideoCard) conf.setVideoc((VideoCard) m);
				else if(m instanceof PowerPack) conf.setPower((PowerPack) m);
				else if(m instanceof Ram) conf.setRam((Ram) m);
				else response.sendRedirect("#");
				session.setAttribute("conf", conf);
				m.removeAvaylability(1);
				PrintWriter pw = response.getWriter();
				pw.println("<h4>Prodotto inserito</h4>");
			}
			catch(SQLException e) {
				
			} 
			
		}
	}
}
