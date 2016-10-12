package model.material;

import java.sql.*;

public class VideoCard extends Material {

	private int port,PCIe;
	private double height,depth,width;
	private Connection con;
	
	public VideoCard(String id, String name, String band, int disp, String description, String use, double price, int port, int PCIe, double height,double width, double depth) {
		super(id, name, band, disp, description, use, price);
		this.port = port;
		this.PCIe = PCIe;
		this.height = height;
		this.depth = depth;
		this.width = width;
		
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
		if(!this.isSave())
		{
			Statement st = con.createStatement();
			st.executeUpdate("insert into schedavideo values('" + super.getId() + "', '" + super.getName() + "', '" + super.getBand() + "'," + super.getAvaylability() + ",'" + super.getDescription() + "','" + super.getUse() + "'," + this.port +"," + this.height + "," + this.depth + "," + this.width + ", " + super.getPrice() + ", " + this.PCIe + ")");
		}

	}

	@Override
	public void updateDisp() throws SQLException {
		if(this.isSave())
		{
			Statement st = con.createStatement();
			st.executeUpdate("update schedavideo set disp = " + super.getAvaylability() + " where codiceid = '" + super.getId() + "'");
		}
	}

	@Override
	public boolean isSave() throws SQLException {
		if(con != null)
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from schedavideo where codiceid = '" + super.getId() + "'");
			return rs.next();
		}
		else return false;
	}

	@Override
	public void updatePrice() throws SQLException {
		if(this.isSave())
		{
			Statement st = con.createStatement();
			st.executeUpdate("update schedavideo set prezzo = " + super.getPrice() + " where codiceid = '" + super.getId() + "'");
		}
	}

	@Override
	public void deletefromDB() throws SQLException {
		// TODO Auto-generated method stub
		if(this.isSave())
		{
			Statement st = con.createStatement();
			st.executeUpdate("delete from schedavideo where codiceid = '" + super.getId() + "'");
		}
	}
	
	
	public int getPort()
	{
		return this.port;
	}
	
	public int getPCIe()
	{
		return this.PCIe;
	}
	
	public double getHeight()
	{
		return this.height;
	}
	
	public double getWidth()
	{
		return this.width;
	}
	
	public double getDepth()
	{
		return this.depth;
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
	
	public String myTable()
	{
		return "schedavideo";
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
		return super.toString() + ", port: " + this.port + ", PCIe: " + this.PCIe + ", depth: " + this.depth + ", width: " + this.width + ", height: " + this.height;
	}
	
	public boolean equals(Object obj)
	{
		if(!super.equals(obj)) return false;
		if(!(obj instanceof VideoCard)) return false;
		VideoCard ob = (VideoCard) obj;
		return ob.PCIe == this.PCIe && ob.port == this.port && ob.height == this.height && ob.width == this.width && this.depth == ob.depth; 
	}
	
	public VideoCard clone()
	{
		VideoCard obj = (VideoCard) super.clone();
		return obj;
	}
}
