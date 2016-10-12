package model.material;

import java.sql.*;

public class Heatsink extends Material {
	
	private String type,socketp;
	private double height;
	private int encumberance;
	private Connection con;
	
	public Heatsink(String id, String name, String band, int disp, String description, String use, double price, String type, String socketp,double height, int encumberance) {
		super(id, name, band, disp, description, use, price);
		this.type = type;
		this.socketp = socketp;
		this.height = height;
		this.encumberance = encumberance;
	
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
			st.executeUpdate("insert into dissipatore values('" + super.getId() + "', '" + super.getName() + "', '" + super.getBand() + "', " + super.getAvaylability() + ", '" + super.getDescription() + "', '" + super.getUse() + "', '" + this.type + "', '" + this.socketp + "', " + this.height + ", " + this.encumberance + ", " + super.getPrice() + ")");
		}

	}

	@Override
	public void updateDisp() throws SQLException {
		if(this.isSave())
		{
			Statement st = con.createStatement();
			st.executeUpdate("update dissipatore set disp = " + super.getAvaylability() + " where codiceid = '" + super.getId() + "'");
		}

	}

	@Override
	public boolean isSave() throws SQLException {
		if(con != null)
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from dissipatore where codiceid = '" + super.getId() + "'");
			return rs.next();
		}
		else return false;
	}

	@Override
	public void updatePrice() throws SQLException {
		if(this.isSave())
		{
			Statement st = con.createStatement();
			st.executeUpdate("update dissipatore set prezzo = " + super.getPrice() + " where codiceid = '" + super.getId() + "'");
		}

	}

	public void deletefromDB() throws SQLException {
		if(this.isSave())
		{
			Statement st = con.createStatement();
			st.executeUpdate("delete from dissipatore where codiceid = '" + super.getId() + "'");
		}
		
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
	
	public String getType()
	{
		return this.type;
	}
	
	public String getSocketP()
	{
		return this.socketp;
	}
	
	public int getEncumberance()
	{
		return this.encumberance;
	}
	
	public double getHeight()
	{
		return this.height;
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
	
	public String myTable()
	{
		return "dissipatore";
	}
	
	public String toString()
	{
		return super.toString() + ", type: " + this.type + ", socketp: " + this.socketp + ", height: " + this.height + ", encumberance: " + this.encumberance;
	}
	
	public boolean equals(Object obj)
	{
		if(!super.equals(obj)) return false;
		if(!(obj instanceof Heatsink)) return false;
		Heatsink ob = (Heatsink) obj;
		return ob.type.equals(this.type) && ob.socketp.equals(this.socketp) && ob.height == this.height && ob.encumberance == this.encumberance;
	}
	
	public Heatsink clone()
	{
		Heatsink obj = (Heatsink) super.clone();
		return obj;
	}
}
