package material;

import java.sql.*;

public class Ram extends Material {
	
	private String model, heatsink;
	private double frequency;
	private Connection con;
	
	public Ram(String id, String name, String band, int disp, String description, String use, double price, String model, double frequency, String heatsink) {
		super(id, name, band, disp, description, use, price);
		this.model = model;
		this.heatsink = heatsink;
		this.frequency = frequency;
		
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
		if(!this.isSave() & con != null)
		{
			Statement st = con.createStatement();
			st.executeUpdate("insert into ram values('" + super.getId() + "','" + super.getName() + "', '" + super.getBand() + "', " + super.getAvaylability() + ",'" + super.getDescription() + "', '" + super.getUse() + "','" + this.model + "'," + this.frequency + ",'" + this.heatsink + "', " + super.getPrice() + ")");
		}

	}

	@Override
	public void updateDisp() throws SQLException {
		if(this.isSave())
		{
			Statement st = con.createStatement();
			st.executeUpdate("update ram set disp = " + super.getAvaylability() + " where codiceid = '" + super.getId() + "'");
		}

	}

	@Override
	public boolean isSave() throws SQLException {
		if(con != null)
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from ram where codiceid = '" + super.getId() + "'");
			return rs.next();
		}
		else return false;
	}

	@Override
	public void updatePrice() throws SQLException {
		Statement st = con.createStatement();
		st.executeUpdate("update ram set prezzo = " + super.getPrice() + " where codiceid = '" + super.getId() + "'");
	}

	@Override
	public void deletefromDB() throws SQLException {
		Statement st = con.createStatement();
		st.executeUpdate("delete from ram where codiceid = '" + super.getId() +"'");
	}
	
	public String myTable()
	{
		return "ram";
	}
	
	public String getModel()
	{
		return this.model;
	}
	
	public String getHeatsink()
	{
		return this.heatsink;
	}
	
	public double getFrequency()
	{
		return this.frequency;
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
		return super.toString() + ", model: " + this.model + ", frequency: " + this.frequency + ", heatsink: " + this.heatsink;
	}
	
	public boolean equals(Object obj)
	{
		if(!super.equals(obj)) return false;
		if(!(obj instanceof Ram)) return false;
		Ram ob = (Ram) obj;
		return ob.frequency == this.frequency && ob.heatsink.equals(this.heatsink) && ob.model.equals(this.model);
	}
	
	public Ram clone()
	{
		Ram obj = (Ram) super.clone();
		return obj;
	}
}
