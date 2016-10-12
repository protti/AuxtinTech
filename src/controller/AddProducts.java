package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.material.Case;
import model.material.HddSsd;
import model.material.Heatsink;
import model.material.Material;
import model.material.MotherBoard;
import model.material.PowerPack;
import model.material.Processor;
import model.material.Ram;
import model.users.Admin;
import model.users.Client;

/**
 * Servlet implementation class AddProducts
 */
@WebServlet("/AddProducts")
public class AddProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProducts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		synchronized(session)
		{
			
			String id = request.getParameter("id");
			Client cl = (Client) session.getAttribute("user");
			Admin ad;
			try {
				ad = cl.getAdmin();
				Material am = ad.searchMat(id);
				
				int quan = Integer.parseInt(request.getParameter("number"));
				System.out.println("Remove=" + quan);
				am.addAvaylability(quan);
				Material mat = ad.searchMat(id);
				if(id.charAt(0) == '0')
				{
					MotherBoard hs = (MotherBoard) mat;
					ad.insertProduct(hs);
					hs.updateDisp();
				}
				else if(id.charAt(0) == '1')
				{
					Case hs = (Case) mat;
					ad.insertProduct(hs);
					hs.updateDisp();
				}
				else if(id.charAt(0) == '2')
				{
					PowerPack hs = (PowerPack) mat;
					ad.insertProduct(hs);
					hs.updateDisp();
				}
				else if(id.charAt(0) == '3')
				{
					Heatsink hs = (Heatsink) mat;
					ad.insertProduct(hs);
					hs.updateDisp();
				}
				else if(id.charAt(0) == '4')
				{	
					HddSsd hs = (HddSsd) mat;
					ad.insertProduct(hs);
					hs.updateDisp();
				}
				else if(id.charAt(0) == '5')
				{
					Ram hs = (Ram) mat;
					ad.insertProduct(hs);
					hs.updateDisp();
				}
				else if(id.charAt(0) == 'A')
				{	
					MotherBoard hs = (MotherBoard) mat;
					ad.insertProduct(hs);
					hs.updateDisp();
				}
				else
				{
					Processor hs = (Processor) mat;
					ad.insertProduct(hs);
					hs.updateDisp();
				}
				PrintWriter out = response.getWriter();
				out.println("<html><body>");
				out.println("Disponibilità Aggiunta");
				out.println("</body></html>");
		
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				response.sendRedirect("error500.jsp");
			}
		}
		//doGet(request, response);
	}
}
