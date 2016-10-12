package model.users;
import java.sql.*;
public class Card implements Cloneable{
	
	private String cod, ssn, type;
	private double sald;
	private Connection con;
	
	public Card(String cod, String ssn, String type, double sald)
	{
		this.cod = cod;
		this.ssn = ssn;
		this.type = type;
		this.sald = sald;
		
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
	
	public void removeSald(double quote) throws SQLException, ProductNotSaldableException
	{
		if(this.sald - quote >= 0)
		{
			this.sald = this.sald - quote;
			this.updateSald();
		}
		else throw new ProductNotSaldableException();
	}
	
	public void addSald(double quote) throws SQLException
	{
		if(quote > 0)
		{
			this.sald = this.sald + quote;
			this.updateSald();
		}
	}
	
	public String getSsn()
	{
		return this.ssn;
	}
	
	public String getCod()
	{
		return this.cod;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public double getSald()
	{
		return this.sald;
	}
	
	public boolean isSave() throws SQLException
	{
		if(con != null)
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from carta where codcard = '" + this.cod + "'");
			return rs.next();
		}
		else return false;
	}
	
	public void saveToDB() throws SQLException
	{
		if(!this.isSave() && con != null)
		{
			Statement st = con.createStatement();
			st.executeUpdate("insert into carta values('" + this.cod + "','" + this.ssn + "','" + this.type + "'," + this.sald + ")");
		}
	}
	
	public void deleteFromDB() throws SQLException
	{
		if(this.isSave())
		{
			Statement st = con.createStatement();
			st.executeUpdate("delete from carta where codcard = '" + this.cod + "'");
		}
	}
	
	public void updateSald() throws SQLException
	{
		if(this.isSave())
		{
			Statement st = con.createStatement();
			st.executeUpdate("update carta set importo = " + this.sald + " where codcard = '" + this.cod + "'");
		}
	}
	
	
	
	public String toString()
	{
		return this.getClass().getName() + " ssn: " + this.ssn + ", cod: " + this.cod + ", type: " + this.type + ", sald: " + this.sald;
	}
	
	public boolean equals(Object obj)
	{
		if(obj == null) return false;
		if(!(obj instanceof Card)) return false;
		Card ob = (Card) obj;
		return ob.ssn.equals(this.ssn) && ob.cod.equals(this.cod) && ob.type.equals(this.type) && ob.sald == this.sald;
	}
	
	public Card clone()
	{
		try
		{
			Card obj = (Card) super.clone();
			return obj;
		}
		catch(CloneNotSupportedException e)
		{
			return null;
		}
	}
}
