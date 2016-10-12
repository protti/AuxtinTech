package controller;


import model.material.*;
import model.shopping.TotalShop;
import model.users.Client;
import java.io.*;
import java.sql.*;
import controller.Connections;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class InsertCart
 */
@WebServlet("/InsertCart")
public class InsertCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private Connection con;
	public void init() throws ServletException
	{
		super.init();
		con = Connections.getConnection();
	}
	
	public void destroy()
	{
		try {
			con.close();
			con = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			con = null;
		}
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
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		synchronized(session)
		{
		String idProdotto = (String)request.getParameter("id");
		Client clie = (Client)session.getAttribute("user");
		
		
		try {
			
			TotalShop shop = clie.getTS();
			shop.addInCart(shop.searchMat(idProdotto));
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			pw.println("<html><body>");
			pw.println("<h3>Oggetto inserito correttamente!</h3>");
			pw.println("</body></html>");
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			response.sendRedirect("error500.jsp");
		}	
		}
	}

}
