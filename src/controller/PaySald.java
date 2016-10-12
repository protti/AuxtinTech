package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import model.users.Admin;
import model.users.Card;
import model.users.Client;

/**
 * Servlet implementation class PaySald
 */
@WebServlet("/paySald")
public class PaySald extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection con;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaySald() {
        super();
		con = Connections.getConnection();

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
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		synchronized(session)
		{
		Client cl = (Client) session.getAttribute("user");	
		try {
			if(cl.controlAdmin())
			{
				Admin ad = cl.getAdmin();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select codcard,tipo,importo from carta where ssn = '" + ad.getSsn() + "'");
				if(rs.next()){
					Card crd = new Card(rs.getString(1),ad.getSsn(),rs.getString(2),rs.getInt(3));
					ad.payInterest(crd);
					
					if(ad.getToPay() == 0)
					{
						PrintWriter out = response.getWriter();
						out.println("<html><body>");
						out.println("Pagatoh!");
						out.println("</body></html>");
					}else{
						PrintWriter out = response.getWriter();
						out.println("<html><body>");
						out.println("Non.....Pagatoh!");
						out.println("</body></html>");
					}
				}
			}
		
		
		}catch (Exception e) {
			response.sendRedirect("error500.jsp");
		}
	}
	}
}
