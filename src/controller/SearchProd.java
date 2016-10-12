package controller;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import model.users.*;
import model.feedback.*;
import model.material.*;
import java.util.ArrayList;
import model.shopping.*;
@WebServlet("/search")
public class SearchProd extends HttpServlet{

	private Connection con;
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		con = Connections.getConnection();
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		synchronized(session)
		{
			Client user = (Client) session.getAttribute("user");
			
			ArrayList<Material> m = new ArrayList<Material>();
			String tipo = request.getParameter("tipo");
			String name = request.getParameter("name");
			try {
				
				Statement st = con.createStatement();
				if(tipo.equals("all") || tipo.equals("powerpack")){
					ResultSet rs = st.executeQuery("select * from alimentatore where nome like '%" + name + "%' AND disp > 0");
					while(rs.next())
					{
						PowerPack pd = new PowerPack(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(10),rs.getDouble(7),rs.getInt(9),rs.getString(8));
						m.add(pd);
					}
				}
				if(tipo.equals("all") || tipo.equals("case")){
				ResultSet rs = st.executeQuery("select * from cas where nome like '%" + name + "%' AND disp > 0");
					while(rs.next())
					{
						Case pd = new Case(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(12),rs.getString(7),rs.getDouble(8),rs.getDouble(9),rs.getDouble(10),rs.getString(11));
						m.add(pd);
					}
				}
				if(tipo.equals("all") || tipo.equals("heatsink")){
					ResultSet rs = st.executeQuery("select * from dissipatore where nome like '%" + name + "%' AND disp > 0");
					while(rs.next())
					{
						Heatsink pd = new Heatsink(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(11),rs.getString(7),rs.getString(8),rs.getDouble(9),rs.getInt(10));
						m.add(pd);
					}
				}
				if(tipo.equals("all") || tipo.equals("hdd")){
					ResultSet rs = st.executeQuery("select * from hddssd where nome like '%" + name + "%' AND disp > 0");
					while(rs.next())
					{
						HddSsd pd = new HddSsd(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(9),rs.getDouble(7),rs.getDouble(8));
						m.add(pd);
					}
				}
				if(tipo.equals("all") || tipo.equals("processor")){
					ResultSet rs = st.executeQuery("select * from processore where nome like '%" + name + "%' AND disp > 0");
					while(rs.next())
					{
						Processor pd = new Processor(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(8),rs.getString(7));
						m.add(pd);
					}
				}
				if(tipo.equals("all") || tipo.equals("ram")){
					ResultSet rs = st.executeQuery("select * from ram where nome like '%" + name + "%' AND disp > 0");
					while(rs.next())
					{
						Ram pd = new Ram(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(10),rs.getString(7),rs.getDouble(8),rs.getString(9));
						m.add(pd);
					}
				}
				if(tipo.equals("all") || tipo.equals("motherB")){
					ResultSet rs = st.executeQuery("select * from schedamadre where nome like '%" + name + "%' AND disp > 0");
					while(rs.next())
					{
						MotherBoard pd = new MotherBoard(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(13),rs.getString(7),rs.getString(8),rs.getDouble(9),rs.getDouble(10),rs.getInt(11),rs.getDouble(12),rs.getInt(14));
						m.add(pd);
					}
				}
				if(tipo.equals("all") || tipo.equals("videocard")){
					ResultSet rs = st.executeQuery("select * from schedavideo where nome like '%" + name + "%' AND disp > 0");
					while(rs.next())
					{
						VideoCard pd = new VideoCard(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(11),rs.getInt(7),rs.getInt(12),rs.getDouble(8),rs.getDouble(10),rs.getDouble(9));
						m.add(pd);
					}
				}
				
				PrintWriter out = response.getWriter();
				if(m.size() == 0)
				{
					out.println("<h4>Nessun prodotto trovato</h4>");
				}
				else
				{
					out.println("<h4>Lista prodotti: </h4>");
					out.println("<div id=\"prod\">");
					for(Material mat: m)
					{
						
						out.println("<div class=\"products\">");
						out.println("<b>"+ mat.getName() +"</b>");
						out.println("<span>"+ mat.getBand() +"</span>");
						out.println("<span>"+ mat.getPrice() +"</span>");
						out.println("<div class=\"btn\">");
						out.println("<span><a href=\"pageProduct.jsp?id=" + mat.getId() + "\" ><img src=\"lente.png\"></a></span>");
						out.println("<span class=\"carr\"> <button name=\"id\" onclick=\"addCart(this.value)\" value=\"" + mat.getId() +"\"><img src=\"carrello2.png\"></button></span>");
						
						ResultSet rs = st.executeQuery("select nome,cognome,id from cliente where ssn IN (select ssn from inserting where codiceID = '" + mat.getId() + "')");
						out.println("<br>");
						if(rs.next())
						{
							out.println("<span class=\"adm\"><b>Venditore </b><a href=\"user.jsp?name=" + rs.getString(1) + "&lname=" + rs.getString(2) + "&id=" + rs.getString(3) + "\">" + rs.getString(1) +" " + rs.getString(2) + "</a></span><br>");
						}
						out.println("</div>");
						out.println("</div>");
					}
					out.println("</div>");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				response.sendRedirect("error500.jsp");
			}
			
		}
	}
}
