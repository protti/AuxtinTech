package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.shopping.TotalShop;
import model.users.Admin;
import model.users.Card;
import model.users.Client;

/**
 * Servlet implementation class paySubs
 */
@WebServlet("/paySubs")
public class paySubs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public paySubs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 
		
		
		
		
		
		
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		synchronized(session)
		{
			Client cl = (Client) session.getAttribute("user");
			int mesi;
			double prezzo;
			 if (request.getParameter("m1") != null) {
				 mesi = 1;
				 prezzo = 10;
			 } else if(request.getParameter("m3") != null){
				 mesi = 3;
				 prezzo = 25;
			 }else if(request.getParameter("m6") != null){
				 mesi = 6;
				 prezzo = 55;
			 }else {
				 mesi = 12;
				 prezzo = 95;
			 }
			 try {
				TotalShop ts = cl.getTS();
				
					if(ts.getCard().getSald() > prezzo)
					{
						Connection con = Connections.getConnection();
						Statement st = con.createStatement();
						if(cl.controlAdmin() == false)
						{
							st.executeUpdate("update carta set importo = "+(ts.getCard().getSald()-prezzo) +" where codcard = '"+ts.getCard().getCod()+"'");
							GregorianCalendar gc = new GregorianCalendar();
							SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
							String dataStr = sdf.format(new Date());
							
							String [] dataS = dataStr.split("-");
							dataStr = dataS[2] + "-" + dataS[1] + "-" + dataS[0]; 
							Admin ad = new Admin(cl.getSsn(),cl.getName(),cl.getSurname(),cl.getAge(),cl.getBirthdate(),cl.getPassword(),cl.getNumber(),cl.getCity(),cl.getStreet(),cl.getCivic(),cl.getCap(),mesi,false,0,prezzo,dataStr);
							ad.saveToDB();
							if(ad.isSave())
							{
								/*RequestDispatcher rd = getServletContext().getRequestDispatcher("/user.jsp");
								rd.include(request,response);*/
								response.sendRedirect("user.jsp?name="+cl.getName()+"&lname="+cl.getSurname()+"&id="+cl.getId());
							}
							else
							{
								response.sendRedirect("error500.jsp");
							}	
						}	
					}					
			 } catch (SQLException e) {
				 response.sendRedirect("error500.jsp");
			 }
		}
	}
}

