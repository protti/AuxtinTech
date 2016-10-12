package model.users;
import java.sql.*;

public final class Address implements Cloneable{
	
	private String cod, street, ssn, cap;
	private int civico;
	private Connection con;
	
	public Address(String cod, String street, String ssn, String cap, int civico)
	{
		this.cod = cod;
		this.street = street;
		this.ssn = ssn;
		this.cap = cap;
		this.civico = civico;

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
	
	public String getCod()
	{
		return this.cod;
	}
	
	public String getStreet()
	{
		return this.street;
	}
	
	public String getSsn()
	{
		return this.ssn;
	}
	
	public String getCap()
	{
		return this.cap;
	}
	
	public int getCivico()
	{
		return this.civico;
	}
	
	public void saveToDB() throws SQLException
	{
		if(con != null && !this.isSave())
		{
			Statement st = con.createStatement();
			st.executeUpdate("insert into indirizzo values('" + this.street +"'," + this.civico + ",'" + this.cap + "','" + this.ssn + "','" + this.cod +"')");
		}
	}
	
	public boolean isSave() throws SQLException
	{
		if(con != null)
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from indirizzo where ssn = '" + this.ssn + "' AND codacquisto = '" + this.cod +"'");
			return rs.next();
		}
		else return false;
	}
	
	public void deleteFromDB() throws SQLException
	{
		if(this.isSave())
		{
			Statement st = con.createStatement();
			st.executeUpdate("delete from indirizzo where ssn = '" + this.ssn + "' AND codacquisto = '" + this.cod + "'");
		}
	}
	
	
	
	public String toString()
	{
		return this.getClass().getName() + " street: " + this.street + ", ssn: " + this.ssn + ", cap: " + this.cap + ", civico: " + this.civico + ", cod: " + this.cod;
	}
	
	public boolean equals(Object obj)
	{
		if(obj == null) return false;
		if(!(obj instanceof Address)) return false;
		Address ob = (Address) obj;
		return ob.cap.equals(this.cap) && ob.ssn.equals(this.ssn) && ob.street.equals(this.street) && ob.cod.equals(this.cod) && ob.civico == this.civico;
	}
	
	public Address clone()
	{
		try
		{
			Address obj = (Address) super.clone();
			return obj;
		}
		catch(CloneNotSupportedException e)
		{
			return null;
		}
	}
}
