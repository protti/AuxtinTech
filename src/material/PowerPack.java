package material;

import java.sql.*;

public class PowerPack extends Material {
	
	private Connection con;
	private double mW;
	private int atx12v;
	private String certificate;
	
	public PowerPack(String id, String name, String band, int disp, String description, String use, double price, double mW, int atx12v, String certificate) {
		super(id, name, band, disp, description, use, price);
		this.mW = mW;
		this.atx12v = atx12v;
		this.certificate = certificate;
		
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
			st.executeUpdate("insert into alimentatore values('" + super.getId() + "', '" + super.getName() + "', '" + super.getBand() + "', " + super.getAvaylability() + ", '" + super.getDescription() + "', '" + super.getUse() + "', " + this.mW + ", '" + this.certificate + "', " + this.atx12v + ", " + super.getPrice() + ")");
		}
	}

	@Override
	public void updateDisp() throws SQLException {
		if(this.isSave())
		{
			Statement st = con.createStatement();
			st.executeUpdate("update alimentatore set disp = " + super.getAvaylability() + " where codiceid = '" + super.getId() + "'");
		}
	}

	@Override
	public boolean isSave() throws SQLException {
		// TODO Auto-generated method stub
		if(con != null)
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from alimentatore where codiceid = '" + super.getId() + "'");
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
			st.executeUpdate("update alimentatore set prezzo = " + super.getPrice() + " where codiceid = '" + super.getId() + "'");
		}
	}

	public void deletefromDB() throws SQLException {
		if(this.isSave())
		{
			Statement st = con.createStatement();
			st.executeUpdate("delete from alimentatore where codiceid = '" + super.getId() + "'");
		}
		
	}
	
	public String myTable()
	{
		return "alimentatore";
	}
	
	public double getMW()
	{
		return this.mW;
	}
	
	public int getAtx12v()
	{
		return this.atx12v;
	}
	
	public String getCertificate()
	{
		return this.certificate;
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
		return super.toString() + ", mW: " + this.mW + ", certificate: " + this.certificate + ", atx12v: " + this.atx12v;
	}
	
	public boolean equals(Object obj)
	{
		if(!super.equals(obj)) return false;
		if(!(obj instanceof PowerPack)) return false;
		PowerPack ob = (PowerPack) obj;
		return this.certificate.equals(ob.certificate) && this.mW == ob.mW && this.atx12v == ob.atx12v;
	}
	
	public PowerPack clone()
	{
		PowerPack obj = (PowerPack) super.clone();
		return obj;
	}
}
