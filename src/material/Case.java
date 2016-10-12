package material;

import java.sql.*;

public class Case extends Material {

	private String format, watercooling;
	private double height, width, depth;
	private Connection con;
	
	public Case(String id, String name, String band, int disp, String description, String use, double price, String format, double width, double height, double depth, String watercooling) {
		super(id, name, band, disp, description, use, price);
		this.format = format;
		this.watercooling = watercooling;
		this.height = height;
		this.width = width;
		this.depth = depth;
		
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
		// TODO Auto-generated method stub
		if(!this.isSave() && con != null)
		{
			Statement st = con.createStatement();
			st.executeUpdate("insert into cas values('" + super.getId() + "', '" + super.getName() + "', '" + super.getBand() + "', " + super.getAvaylability() + ", '" + super.getDescription() + "', '" + super.getUse() + "', '" + this.format + "', " + this.height + ", " + this.width + ", " + this.depth + ", '" + this.watercooling + "', " + super.getPrice() + ")");
		}
	}

	@Override
	public void updateDisp() throws SQLException {
		// TODO Auto-generated method stub
		if(this.isSave())
		{
			Statement st = con.createStatement();
			st.executeUpdate("update cas set disp = " + super.getAvaylability() + " where codiceid = '" + super.getId() + "'");
		}
	}

	@Override
	public boolean isSave() throws SQLException {
		if(con != null)
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from cas where codiceid = '" + super.getId() + "'");
			return rs.next();
		}
		else return false;
	}

	@Override
	public void updatePrice() throws SQLException {
		// TODO Auto-generated method stub
		if(this.isSave())
		{
			Statement st = con.createStatement();
			st.executeUpdate("update cas set prezzo = " + super.getPrice() + " where codiceid = '" + super.getId() + "'");
		}
	}
	
	@Override
	public void deletefromDB() throws SQLException {
		if(this.isSave())
		{
			Statement st = con.createStatement();
			st.executeUpdate("delete from cas where codiceid = '" + super.getId() + "'");
		}
		
	}
	
	public String myTable()
	{
		return "cas";
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
	
	public String getFormat()
	{
		return this.format;
	}
	
	public String getWatercooling()
	{
		return this.watercooling;
	}
	
	public double getWidth()
	{
		return this.width;
	}
	
	public double getHeight()
	{
		return this.height;
	}
	
	public double getDepth()
	{
		return this.depth;
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
		return super.toString() + ", format: " + this.format + ", watercooling: " + this.watercooling + ", height: " + this.height + ", width: " + this.width + ", depth: " + this.depth;
	}
	
	public boolean equals(Object obj)
	{
		if(!super.equals(obj)) return false;
		if(!(obj instanceof Case)) return false;
		Case ob = (Case) obj;
		return ob.format.equals(this.format) && ob.watercooling.equals(this.watercooling) && this.height == ob.height && this.width == ob.width && this.depth == ob.depth;
	}
	
	public Case clone()
	{
		Case obj = (Case) super.clone();
		return obj;
	}

	
}
