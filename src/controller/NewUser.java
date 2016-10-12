package controller;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.users.Client;
import java.text.SimpleDateFormat;
/**
 * Servlet implementation class NewUser
 */
@WebServlet(name = "/controller.NewUser", urlPatterns = {"/newuser"})
public class NewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Connection con;
	
	public void init() throws ServletException
	{
		super.init();
		con = Connections.getConnection();
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		synchronized(session)
		{
			String ssn = req.getParameter("email");
			
			String password = Integer.toString(req.getParameter("secret").hashCode());
			String name = req.getParameter("name");
			String surname = req.getParameter("surname");
		
		
			String d = req.getParameter("birth");
			String[] dt = d.split("-");
		//yyyy-MM-dd
		
			int databd = Integer.parseInt(dt[2]);
			int databm = Integer.parseInt(dt[1]);
			int databy = Integer.parseInt(dt[0]);
		
			SimpleDateFormat sdf = new SimpleDateFormat();
			String datao = sdf.format(new java.util.Date());
			sdf.applyPattern("yyyy/MM/dd");
		
			int age = 0;
			String dto[] = datao.split("/");
			System.out.println(dto[0] + "/" + dto[1] + "/" + dto[2]);
			String[] dt2 = dto[2].split(" ");
			int dataoy = Integer.parseInt(dt2[0]) + 2000;
			int dataom = Integer.parseInt(dto[1]);
		
			int dataod = Integer.parseInt(dto[0]);
		
		
			if(dataoy - databy > 0)
			{
				age = dataoy - databy;
				if(databm < dataom || (dataom == databm && dataod > databd))
				{
					age--;
				}
			}
			else
			{
				//sendRedirect to this page
			}
		
		
			String cell = req.getParameter("cell");
			String city = req.getParameter("city");
			String street = req.getParameter("street");
			String civic = req.getParameter("civic");
			String cap = req.getParameter("cap");
		
		
			try {
				int c = Integer.parseInt(civic);
			
				Client cl = new Client(ssn,name,surname,age,d,password,cell,city,street,c,cap);
			
				if(!cl.isSave())
				{
					cl.saveToDB();
					session.setAttribute("user", cl);
				}
				else
				{
					
				}
			
				req.setAttribute("client", cl);
				req.setAttribute("welcome", true);
				//RequestDispatcher rd = getServletContext().getRequestDispatcher("/homepage.jsp");
				//rd.forward(req, resp);
				resp.sendRedirect("homepage.jsp");
			}	 
			catch (Exception e) {
			
				PrintWriter wr = resp.getWriter();
				resp.sendRedirect("error500.jsp");
			}
		}
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
		
		
		
		
		
	}


