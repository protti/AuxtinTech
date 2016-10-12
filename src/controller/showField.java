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

import org.apache.catalina.SessionEvent;

import model.users.Admin;
import model.users.Client;

import java.util.Random;
/**
 * Servlet implementation class showField
 */
@WebServlet("/showField")
public class showField extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showField() {
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
		String tipo = request.getParameter("tipo");
		
		
		try {
			if(cl.controlAdmin())
			{
				System.out.println(tipo);
				Admin ad = cl.getAdmin();
				int a = 10000; // numero minimo
				int b = 99999; // numero massimo
				int c = ((b-a) + 1);
				
				if(tipo.equals("heatsink"))
				{
					PrintWriter out = response.getWriter();
					
					Random val = new Random();
					String id;
					do{	
						Integer miavar = val.nextInt(c) + a;
						id = "3" + miavar.toString();
					}while(ad.searchMat(id) != null);
					
					
					out.println("Id dell tuo dissipatore: " + id +"<br>");
					out.println("<input type=\"hidden\" name=\"id\" value="+id+"><br>");
					out.println("Aggiungi Tipo: <input type=\"text\" name=\"type\"><br>");
					out.println("Aggiungi Socket: <input type=\"text\" name=\"socketp\"><br>");
					out.println("Aggiungi Altezza: <input type=\"text\" name=\"height\"><br>");
					out.println("Aggiungi Ingombranza: <input type=\"text\" name=\"encumberance\"><br>");
					
				}
				else if(tipo.equals("powerpack"))
				{
					PrintWriter out = response.getWriter();
					
					Random val = new Random();
					String id;
					do{	
						Integer miavar = val.nextInt(c) + a;
						id = "2" + miavar.toString();
					}while(ad.searchMat(id) != null);
					out.println("Id dell tuo alimentatore: " + id +"<br>");
					out.println("<input type=\"hidden\" name=\"id\" value="+id+"><br>");
					out.println("Aggiungi minimo Watt: <input type=\"text\" name=\"mW\"><br>");
					out.println("Aggiungi classe certificata: <input type=\"text\" name=\"certify\"><br>");
					out.println("Aggiungi numero atx12v: <input type=\"text\" name=\"atx\"><br>");
				}
				else if(tipo.equals("case"))
				{
					PrintWriter out = response.getWriter();
					
					Random val = new Random();
					String id;
					do{	
						Integer miavar = val.nextInt(c) + a;
						id = "1" + miavar.toString();
					}while(ad.searchMat(id) != null);
					out.println("Id del tuo case: " + id + "<br>");
					out.println("<input type=\"hidden\" name=\"id\" value="+id+"><br>");
					out.println("Aggiungi formato: <input type=\"text\" name=\"format\"><br>");
					out.println("Aggiungi larghezza: <input type=\"text\" name=\"width\"><br>");
					out.println("Aggiungi altezza: <input type=\"text\" name=\"height\"><br>");
					out.println("Aggiungi profondità: <input type=\"text\" name=\"depth\"><br>");
					out.println("Watercooling: <input type=\"text\" name=\"cool\"><br>");
				}
				else if(tipo.equals("hdd"))
				{
					PrintWriter out = response.getWriter();
					
					Random val = new Random();
					String id;
					do{	
						Integer miavar = val.nextInt(c) + a;
						id = "4" + miavar.toString();
					}while(ad.searchMat(id) != null);
					out.println("Id del tuo disco o ssd: " + id + "<br>");
					out.println("<input type=\"hidden\" name=\"id\" value="+id+"><br>");
					out.println("Aggiungi sata: <input type=\"text\" name=\"sata\"><br>");
					out.println("Aggiungi pollici: <input type=\"text\" name=\"pol\"><br>");
				}
				else if(tipo.equals("processor"))
				{
					PrintWriter out = response.getWriter();
					Random val = new Random();
					String id;
					do{	
						Integer miavar = val.nextInt(c) + a;
						id = "7" + miavar.toString();
					}while(ad.searchMat(id) != null);
					out.println("Id del tuo processore: " + id + "<br>");
					out.println("<input type=\"hidden\" name=\"id\" value="+id+"><br>");
					out.println("Aggiungi socket: <input type=\"text\" name=\"sock\"><br>");
				}
				else if(tipo.equals("ram"))
				{
					PrintWriter out = response.getWriter();
					Random val = new Random();
					String id;
					do{	
						Integer miavar = val.nextInt(c) + a;
						id = "5" + miavar.toString();
					}while(ad.searchMat(id) != null);
					out.println("Id della tua memoria ram: " + id + "<br>");
					out.println("<input type=\"hidden\" name=\"id\" value="+id+"><br>");
					out.println("Aggiungi modello RAM: <input type=\"text\" name=\"model\"><br>");
					out.println("Aggiungi frequenza: <input type=\"text\" name=\"freq\"><br>");
					out.println("Descrivi dissipazione: <input type=\"text\" name=\"diss\"><br>");
				}
				else if(tipo.equals("motherB"))
				{
					PrintWriter out = response.getWriter();
					Random val = new Random();
					String id;
					do{	
						Integer miavar = val.nextInt(c) + a;
						id = "0" + miavar.toString();
					}while(ad.searchMat(id) != null);
					out.println("Id della tua scheda madre: " + id + "<br>");
					out.println("<input type=\"hidden\" name=\"id\" value="+id+"><br>");
					out.println("Aggiungi formato: <input type=\"text\" name=\"format\"><br>");
					out.println("Aggiungi socket: <input type=\"text\" name=\"sock\"><br>");
					out.println("Aggiungi massima frquenza RAM: <input type=\"text\" name=\"mfR\"><br>");
					out.println("Aggiungi PCIe: <input type=\"text\" name=\"PCIe\"><br>");
					out.println("Aggiungi numero porte: <input type=\"text\" name=\"port\"><br>");
					out.println("Aggiungi sata: <input type=\"text\" name=\"sata\"><br>");
					out.println("Aggiungi atx12v: <input type=\"text\" name=\"atx\"><br>");
				}
				else if(tipo.equals("videocard"))
				{
					PrintWriter out = response.getWriter();
					Random val = new Random();
					String id;
					do{	
						Integer miavar = val.nextInt(c) + a;
						id = "A" + miavar.toString();
					}while(ad.searchMat(id) != null);
					out.println("Id della tua scheda video: " + id + "<br>");
					out.println("<input type=\"hidden\" name=\"id\" value="+id+"><br>");
					out.println("Aggiungi numero porte: <input type=\"text\" name=\"port\"><br>");
					out.println("Aggiungi altezza: <input type=\"text\" name=\"height\"><br>");
					out.println("Aggiungi profondità: <input type=\"text\" name=\"depth\"><br>");
					out.println("Aggiungi larghezza: <input type=\"text\" name=\"width\"><br>");
					out.println("Aggiungi PCIe: <input type=\"text\" name=\"PCIe\"><br>");
				}
				else
				{
					PrintWriter out = response.getWriter();
					out.println("<h4>Tipo non disponibile</h4>");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			response.sendRedirect("error500.jsp");
		}
		}
		//doGet(request, response);
	}

}
