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

import model.material.*;
import model.users.Admin;
import model.users.Client;
import model.users.ProductNotSaldableException;

/**
 * Servlet implementation class AddProd
 */
@WebServlet("/addProd")
public class AddProd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
			Admin ad = cl.getAdmin();
			String id = (String) request.getParameter("id");
			if(id.charAt(0)== '3')
			{
				//String id, String name, String band, int disp, String description, String use, double price, String type, String socketp,double height, int encumberance
				String name = request.getParameter("name");
				String band = request.getParameter("band");
				int disp = Integer.parseInt(request.getParameter("quantita"));
				String description = request.getParameter("description");
				String use = request.getParameter("use");
				double price = Double.parseDouble(request.getParameter("price"));
				String type = request.getParameter("type");
				String socket = request.getParameter("socketp");
				double height = Double.parseDouble(request.getParameter("height"));
				int encumberance = Integer.parseInt(request.getParameter("encumberance"));
				Heatsink ht = new Heatsink(id,name,band,disp,description,use,price,type,socket,height,encumberance);
				PrintWriter out = response.getWriter();
				ad.insertProduct(ht);
			}
			else if(id.charAt(0) == '2')
			{
				String name = request.getParameter("name");
				String band = request.getParameter("band");
				int disp = Integer.parseInt(request.getParameter("quantita"));
				String description = request.getParameter("description");
				String use = request.getParameter("use");
				double price = Double.parseDouble(request.getParameter("price"));
				double mW = Double.parseDouble(request.getParameter("mW"));
				String cert = request.getParameter("certify");
				int atx = Integer.parseInt(request.getParameter("atx"));
				PowerPack pp = new PowerPack(id,name,band,disp,description,use,price,mW,atx,cert);
				ad.insertProduct(pp);
			}
			else if(id.charAt(0) == '1')
			{
				String name = request.getParameter("name");
				String band = request.getParameter("band");
				int disp = Integer.parseInt(request.getParameter("quantita"));
				String description = request.getParameter("description");
				String use = request.getParameter("use");
				double price = Double.parseDouble(request.getParameter("price"));
				String formato = request.getParameter("format");
				double width = Double.parseDouble(request.getParameter("width"));
				double height = Double.parseDouble(request.getParameter("height"));
				double depth = Double.parseDouble(request.getParameter("depth"));
				String water = request.getParameter("cool");
				Case cs = new Case(id,name,band,disp,description,use,price,formato,width,height,depth,water);
				ad.insertProduct(cs);
			}
			else if(id.charAt(0) == '4')
			{
				String name = request.getParameter("name");
				String band = request.getParameter("band");
				int disp = Integer.parseInt(request.getParameter("quantita"));
				String description = request.getParameter("description");
				String use = request.getParameter("use");
				double price = Double.parseDouble(request.getParameter("price"));
				double sata = Double.parseDouble(request.getParameter("sata"));
				double pollici = Double.parseDouble(request.getParameter("pol"));
				HddSsd hdd = new HddSsd(id,name,band,disp,description,use,price,pollici,sata);
				ad.insertProduct(hdd);
			}
			else if(id.charAt(0) == '7')
			{
				String name = request.getParameter("name");
				String band = request.getParameter("band");
				int disp = Integer.parseInt(request.getParameter("quantita"));
				String description = request.getParameter("description");
				String use = request.getParameter("use");
				double price = Double.parseDouble(request.getParameter("price"));
				String socket = request.getParameter("sock");
				Processor pr = new Processor(id,name,band,disp,description,use,price,socket);
				ad.insertProduct(pr);
			}
			else if(id.charAt(0) == '5')
			{
				String name = request.getParameter("name");
				String band = request.getParameter("band");
				int disp = Integer.parseInt(request.getParameter("quantita"));
				String description = request.getParameter("description");
				String use = request.getParameter("use");
				double price = Double.parseDouble(request.getParameter("price"));
				String model = request.getParameter("model");
				double freq = Double.parseDouble(request.getParameter("freq"));
				String heat = request.getParameter("diss");
				Ram rm = new Ram(id,name,band,disp,description,use,price,model,freq,heat);
				ad.insertProduct(rm);
			}
			else if(id.charAt(0) == '0')
			{
				String name = request.getParameter("name");
				String band = request.getParameter("band");
				int disp = Integer.parseInt(request.getParameter("quantita"));
				String description = request.getParameter("description");
				String use = request.getParameter("use");
				double price = Double.parseDouble(request.getParameter("price"));
				String format = request.getParameter("format");
				String socket = request.getParameter("sock");
				double mfR = Double.parseDouble(request.getParameter("mfR"));
				double PCIe = Double.parseDouble(request.getParameter("PCIe"));
				int porte = Integer.parseInt(request.getParameter("port"));
				double sata = Double.parseDouble(request.getParameter("sata"));
				int atx = Integer.parseInt(request.getParameter("atx"));
				MotherBoard mb = new MotherBoard(id,name,band,disp,description,use,price,format,socket,mfR,PCIe,porte,sata,atx);
				ad.insertProduct(mb);
			}
			else if(id.charAt(0) == 'A')
			{
				String name = request.getParameter("name");
				String band = request.getParameter("band");
				int disp = Integer.parseInt(request.getParameter("quantita"));
				String description = request.getParameter("description");
				String use = request.getParameter("use");
				double price = Double.parseDouble(request.getParameter("price"));
				int porte = Integer.parseInt(request.getParameter("port"));
				double height = Double.parseDouble(request.getParameter("height"));
				double width = Double.parseDouble(request.getParameter("width"));
				double depth = Double.parseDouble(request.getParameter("depth"));
				int PCIe = Integer.parseInt(request.getParameter("PCIe"));
				
				VideoCard vc = new VideoCard(id,name,band,disp,description,use,price,porte,PCIe,height,width,depth);
				ad.insertProduct(vc);
			}
			else
			{
				PrintWriter out = response.getWriter();
				out.println("<h4>Prodotto non disponibile</h4>");
			}
			response.sendRedirect("homepage.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			response.sendRedirect("error500.jsp");
		}
		
		
		}
		
		//doGet(request, response);
	}

}
