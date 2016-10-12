package material;

import java.sql.*;

public class Processor extends Material {

	private String socket;
	private Connection con;
	
	public Processor(String id, String name, String band, int disp, String description, String use, double price, String socket) {
		super(id, name, band, disp, description, use, price);
		this.socket = socket;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:3306/ecommerce";
			con = DriverManager.getConnection(url,"root","bikeralex");
		}
		catch(Exception e)
		{
			con = null;
		}
	}

	@Override
	public void saveToDB() throws SQLException {
		if(!this.isSave() && con != null)
		{
			Statement st = con.createStatement();
			st.executeUpdate("insert into processore values('" + super.getId() + "','" + super.getName() + "','" + super.getBand() + "', " + super.getAvaylability() + ", '" + super.getDescription() + "','" + super.getUse() +"','" + this.socket + "', " + super.getPrice() + ")");
		}
	}

	@Override
	public void updateDisp() throws SQLException {
		if(this.isSave())
		{
			Statement st = con.createStatement();
			st.executeUpdate("update processore set disp = " + super.getAvaylability() + " where codiceid = '" + super.getId() + "'");
		}

	}

	@Override
	public boolean isSave() throws SQLException {
		if(con != null)
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from processore where codiceid = '" + super.getId() + "'");
			return rs.next();
		}
		else return false;
	}

	@Override
	public void updatePrice() throws SQLException {
		if(this.isSave())
		{
			Statement st = con.createStatement();
			st.executeUpdate("update processore set prezzo = " + super.getPrice() + " where codiceid = '" + super.getId() + "'");
		}
	}

	@Override
	public void deletefromDB() throws SQLException {
		if(this.isSave())
		{
			Statement st = con.createStatement();
			st.executeUpdate("delete from processore where codiceid = '" + super.getId() + "'");
		}
	}

	public String myTable()
	{
		return "processore";
	}
	
	public String getSocket()
	{
		return this.socket;
	}
	
	public String getName()
	{
		return super.getName();
	}
	
	public String getBand()
	{
		return super.getBand();
	}
	
	public String getId()
	{
		return super.getId();
	}
	
	public double getPrice()
	{
		return super.getPrice();
	}
	
	public int getAvaylability()
	{
		return super.getAvaylability();
	}
	
	public String getUse()
	{
		return super.getUse();
	}
	
	public String getDescription()
	{
		return super.getDescription();
	}
	
	
	
	public void setDiscount(double quote) throws SQLException
	{
		super.setDiscount(quote);
		this.updatePrice();
	}
	
	public boolean controlAvaylability(int numProduct)
	{
		return super.controlAvaylability(numProduct);
	}
	
	public void removeAvaylability(int numProduct) throws SQLException
	{
		super.removeAvaylability(numProduct);
		this.updateDisp();
	}
	
	public void addAvaylability(int numProduct) throws SQLException
	{
		super.addAvaylability(numProduct);
		this.updateDisp();
	}
	
	public String toString()
	{
		return super.toString() + ", socket: " + this.socket;
	}
	
	public boolean equals(Object obj)
	{
		if(!super.equals(obj)) return false;
		if(!(obj instanceof Processor)) return false;
		Processor ob = (Processor) obj;
		return ob.socket.equals(this.socket);
	}
	
	public Processor clone()
	{
		Processor obj = (Processor) super.clone();
		return obj;
	}
}
