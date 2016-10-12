package users;
import java.sql.*;

import material.*;

public class Admin extends Client {
	private int type_abb;
	private double to_pay, paid;
	private boolean expired;
	private Connection con;
	
	public Admin(String ssn, String name, String surname, int age, String birthdate, String password, String number, String city, String street, int civic, String cap, int type_abb, boolean expired, double to_pay, double paid)
	{
		super(ssn,name,surname,age,birthdate,password,number,city,street,civic,cap);
		this.type_abb = type_abb;
		this.to_pay = to_pay;
		this.paid = paid;
		this.expired = expired;
		
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
	
	public boolean isSave() throws SQLException
	{
		if(con != null)
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from admin where ssn = '" + super.getSsn() + "'");
			return rs.next();
		}
		else return false;
	}
	
	/*Restituisce true nel momento in cui non è scaduto, false altrimenti*/
	public boolean controlExpired() throws SQLException
	{
		if(this.isSave())
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from admin where ssn = '" + super.getSsn() + "' AND scad = 0");
			return rs.next();
		}
		else return false;
	}
	
	
	/* Restituisce true nel momento in cui ha pagato, false altrimenti*/
	public boolean controlToPay() throws SQLException
	{
		if(this.isSave())
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from admin where ssn = '" + super.getSsn() + "' AND to_pay = 0");
			return rs.next();
		}
		else return false;
	}
	
	
	public void saveToDB() throws SQLException
	{
		if(!this.isSave() && con != null)
		{
			Statement st = con.createStatement();
			st.executeUpdate("insert into admin values('" + super.getSsn() + "'," + this.to_pay + "," + this.paid + "," + this.type_abb + "," + this.expired + ")");
		}
	}
	
	public void deleteFromDB() throws SQLException
	{
		if(this.isSave() && this.controlToPay())
		{
			Statement st = con.createStatement();
			st.executeUpdate("delete from admin where ssn = '" + super.getSsn() + "'");
		}
	}
	
	public void updateAll() throws SQLException
	{
		if(this.isSave())
		{
			Statement st = con.createStatement();
			st.executeUpdate("update admin set to_pay = " + this.to_pay + ", paid = " + this.paid +", type_abb = " + this.type_abb + ", scad = " + this.expired + " where ssn = '" + super.getSsn() + "'");
		}
	}
	
	
	public double getToPay()
	{
		return this.to_pay;
	}
	
	public double getPaid()
	{
		return this.paid;
	}
	
	public int getTypeAbb()
	{
		return this.type_abb;
	}
	
	public boolean getExpired()
	{
		return this.expired;
	}
	
	
	public double getPercent()
	{
		if(this.type_abb == 1)
		{
			return 0.02;
		}
		else if(this.type_abb == 3)
		{
			return 0.05;
		}
		else if(this.type_abb == 6)
		{
			return 0.1;
		}
		else if(this.type_abb == 12)
		{
			return 0.2;
		}
		else return -1;
	}
	
	public void renewAdmin(int typeAbb) throws SQLException
	{
		if(this.isSave() && this.controlToPay() && this.getPercent() < 0)
		{
			this.type_abb = typeAbb;
			if(this.getPercent() > 0)
			{
				this.expired = false;
				this.updateAll();
			}
			else this.type_abb = 0;
		}		
	}
	
	public void insertProduct(Material m) throws SQLException
	{
		if(this.isSave() && this.controlExpired())
		{
			Statement st = con.createStatement();
			if(!m.isSave())
			{
				m.saveToDB();
				st.executeUpdate("insert into inserting values('" + super.getSsn() + "','" + m.getId() + "')");
			}
			else
			{
				ResultSet rs = st.executeQuery("select * from inserting where ssn = '" + super.getSsn() + "' AND codiceID = '" + m.getId() + "'");
				if(rs.next())
				{
					String myTable = m.myTable();
					st.executeUpdate("update " + myTable + " set disp = disp + " + m.getAvaylability() + " where codiceID = '" + m.getId() + "'");
				}
			}
		}
	}
	
	public Material searchMat(String id) throws SQLException
	{
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from alimentatore where codiceid = '" + id + "'");
		if(rs.next())
		{
			PowerPack pd = new PowerPack(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(10),rs.getDouble(7),rs.getInt(9),rs.getString(8));
			return pd;
		}
		rs = st.executeQuery("select * from cas where codiceid = '" + id + "'");
		if(rs.next())
		{
			Case pd = new Case(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(12),rs.getString(7),rs.getDouble(8),rs.getDouble(9),rs.getDouble(10),rs.getString(11));
			return pd;
		}
		rs = st.executeQuery("select * from dissipatore where codiceid = '" + id + "'");
		if(rs.next())
		{
			Heatsink pd = new Heatsink(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(11),rs.getString(7),rs.getString(8),rs.getDouble(9),rs.getInt(10));
			return pd;
		}
		rs = st.executeQuery("select * from hddssd where codiceid = '" + id + "'");
		if(rs.next())
		{
			HddSsd pd = new HddSsd(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(9),rs.getDouble(7),rs.getDouble(8));
			return pd;
		}
		rs = st.executeQuery("select * from processore where codiceid = '" + id + "'");
		if(rs.next())
		{
			Processor pd = new Processor(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(8),rs.getString(7));
			return pd;
		}
		rs = st.executeQuery("select * from ram where codiceid = '" + id + "'");
		if(rs.next())
		{
			Ram pd = new Ram(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(10),rs.getString(7),rs.getDouble(8),rs.getString(9));
			return pd;
		}
		rs = st.executeQuery("select * from schedamadre where codiceid = '" + id + "'");
		if(rs.next())
		{
			MotherBoard pd = new MotherBoard(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(13),rs.getString(7),rs.getString(8),rs.getDouble(9),rs.getDouble(10),rs.getInt(11),rs.getDouble(12),rs.getInt(14));
			return pd;
		}
		rs = st.executeQuery("select * from schedavideo where codiceid = '" + id + "'");
		if(rs.next())
		{
			VideoCard pd = new VideoCard(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(11),rs.getInt(7),rs.getInt(12),rs.getDouble(8),rs.getDouble(10),rs.getDouble(9));
			return pd;
		}
		return null; 
	}
	
	
	
	public void removeProduct(String codiceID, int quan) throws SQLException
	{
		if(this.isSave())
		{
			Material m = this.searchMat(codiceID);
			if(m != null )
			{
				if(this.myProduct(m))
				{
					m.removeAvaylability(quan);
					if(m.getAvaylability() == 0)
					{
						m.deletefromDB();
						Statement st = con.createStatement();
						st.executeUpdate("delete from inserting where codiceID = '" + m.getId() + "'");
					}
				}
			}
		}
	}
	
	public void saldProduct(Material m) throws SQLException, ProductNotSaldableException
	{
		if(this.isSave() && this.controlExpired() && this.getPercent() > 0 && m.controlAvaylability(1) && this.myProduct(m))
		{
			this.removeProduct(m.getId(), 1);
			this.to_pay += m.getPrice() * this.getPercent();
			this.updateAll();
		}
		else throw new ProductNotSaldableException(super.getSsn(),m.getId()); 
	}
	
	public void payInterest(Card c) throws SQLException
	{
		if(this.isSave() && c.isSave() && c.getSsn() == super.getSsn() && c.getSald() >= this.to_pay)
		{
			this.paid += this.to_pay;
			c.removeSald(this.to_pay);
			this.to_pay = 0;
			this.updateAll();
		}
	}
	
	public boolean myProduct(Material m) throws SQLException
	{
		if(this.isSave() && m != null)
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select ssn from inserting where codiceID = '" + m.getId() + "'");
			if(rs.next()) return m.getId() == rs.getString(1);
			else return false;
		}
		else return false;
	}
	
	public String toString()
	{
		return super.toString() + ", to_pay: " + this.to_pay + ", paid: " + this.paid + ", expired: " + this.expired + ", type_abb: " + this.type_abb;
	}
	
	public boolean equals(Object obj)
	{
		if(!super.equals(obj)) return false;
		if(!(obj instanceof Admin)) return false;
		Admin ob = (Admin) obj;
		return ob.to_pay == this.to_pay && ob.paid == this.paid && ob.type_abb == this.type_abb && ob.expired == this.expired;
	}
	
	public Admin clone()
	{
		Admin cloned = (Admin) super.clone();
		return cloned;
	}
}
