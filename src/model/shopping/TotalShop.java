package model.shopping;
import java.sql.*;
import java.text.SimpleDateFormat;

import model.users.*;
import model.material.*;
import java.util.ArrayList;
import java.util.Random;

public class TotalShop implements Cloneable{
	
	private String cod, ssn, date;
	private double pricetot;
	private Address address;
	private Card card;
	private Connection con;
	
	public TotalShop(String cod, String ssn, String date, double pricetot, Address address, Card card)
	{
		this.cod = cod;
		this.ssn = ssn;
		this.date = date;
		this.pricetot = pricetot;
		this.address = address;
		this.card = card;
		
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
	
	
	public Card getCard()
	{
		return this.card;
	}
	
	public Address getAddress()
	{
		
		return this.address;
	}
	
	public String getCod()
	{
		return this.cod;
	}
	
	public String getSsn()
	{
		return this.ssn;
	}
	
	public String getDate()
	{
		return this.date;
	}
	
	public double getPriceTot()
	{
		return this.pricetot;
	}
	
	
	
	public boolean isSave() throws SQLException
	{
		if(con != null)
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from acquisto where codacquisto = '" + this.cod + "'");
			return rs.next();
		}
		else return false;
	}
	
	public void saveToDB() throws SQLException
	{
		if(!this.isSave() && con != null)
		{
			Statement st = con.createStatement();
			if(card == null)
			{
				st.executeUpdate("insert into acquisto values('" + this.cod + "','" + this.ssn + "','" + this.date + "'," + this.pricetot + ",'V'," + null + ")");
			}
			else
			{
				st.executeUpdate("insert into acquisto values('" + this.cod + "','" + this.ssn + "','" + this.date + "'," + this.pricetot + ",'V'," + card.getCod() + ")");
			}
		}
	}
	
	public void deleteFromDB() throws SQLException
	{
		if(this.isSave())
		{
			Statement st = con.createStatement();
			st.executeUpdate("delete from acquisto where codacquisto = '" + this.cod + "'");
		}
	}
	
	public boolean existInCart(Material m) throws SQLException
	{
		if(this.isSave() && !this.controlSald())
		{
			if(m != null)
			{
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select * from vengono_posti where codiceID = '" + m.getId() + "' AND codiceAcquisto = '" + this.getCod() + "'");
				return rs.next();
			}
			else return false;
		}
		else return false;
	}
	
	public void addInCart(Material m) throws SQLException
	{
		if(this.isSave() && !this.controlSald() && m != null)
		{
			Statement st = con.createStatement();
			if(!this.existInCart(m))
			{
				if(m.controlAvaylability(1))
				{
					st.executeUpdate("insert into vengono_posti values('" + m.getId() + "','" + this.getCod() + "'," + m.getPrice() + ", 1)");
					m.removeAvaylability(1);
					this.addPrice(m.getPrice());
				}
			}
			else
			{
				if(m.controlAvaylability(1))
				{
					st.executeUpdate("update vengono_posti set quan = quan + 1 where codiceID = '" + m.getId() + "' AND codiceAcquisto = '" + this.getCod() + "'");
					m.removeAvaylability(1);
					this.addPrice(m.getPrice());
				}
			}
		}
	}
	
	public void addInCartAb(Material m) throws SQLException
	{
		if(this.isSave() && !this.controlSald() && m != null)
		{
			Statement st = con.createStatement();
			if(!this.existInCart(m))
			{
				if(m.controlAvaylability(1))
				{
					st.executeUpdate("insert into vengono_posti values('" + m.getId() + "','" + this.getCod() + "'," + m.getPrice() + ", 1)");
					//m.removeAvaylability(1);
					this.addPrice(m.getPrice());
				}
			}
			else
			{
				if(m.controlAvaylability(1))
				{
					st.executeUpdate("update vengono_posti set quan = quan + 1 where codiceID = '" + m.getId() + "' AND codiceAcquisto = '" + this.getCod() + "'");
					//m.removeAvaylability(1);
					this.addPrice(m.getPrice());
				}
			}
		}
	}
	
	public void removeFromCart(Material m) throws SQLException
	{
		if(this.isSave() && this.existInCart(m))
		{
			if(con == null)
			{
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
			
			Statement st = con.createStatement();
			st.executeUpdate("update vengono_posti set quan = quan - 1 where codiceID = '" + m.getId() + "' AND codiceAcquisto = '" + this.getCod() + "'");
			ResultSet rs = st.executeQuery("select quan from vengono_posti where codiceID = '" + m.getId() + "' AND codiceAcquisto = '" + this.getCod() + "'");
			if(rs.next())
			{				
				if(rs.getInt(1) == 0)
				{
					st.executeUpdate("delete from vengono_posti where codiceID = '" + m.getId() + "' AND codiceAcquisto = '" + this.getCod() + "'");
				}
				m.addAvaylability(1);
				this.removePrice(m.getPrice());
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
	
	public boolean verifyCard() throws SQLException
	{
		if(this.isSave())
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select codcard from acquisto where codacquisto = '" + this.cod + "'");
			if(rs.next())
			{
				return rs.getString(1) != null;
			}
			else return false;
		}
		else return false;
	}
	
	public void deleteCart() throws SQLException
	{
		if(this.isSave() && !this.controlSald())
		{
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select codiceID,quan from vengono_posti where codiceAcquisto = '" + this.getCod() + "'");
			while(rs.next())
			{
				Material m = this.searchMat(rs.getString(1));
				for(int i = rs.getInt(2); i > 0; i--)
				{
					this.removeFromCart(m);
				}
				m.addAvaylability(rs.getInt(2));
			}
			this.deleteFromDB();
		}
	}
	
	
	
	public ArrayList<Material> getCart() throws SQLException
	{
		if(this.isSave())
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select codiceID from vengono_posti where codiceAcquisto = '" + this.cod + "'");
			ArrayList<Material> cart = new ArrayList<Material>();
			while(rs.next())
			{
				cart.add(this.searchMat(rs.getString(1)));
			}
			return cart;
		}
		else return null;
	}
	
	
	public boolean controlSald() throws SQLException
	{
		if(this.isSave())
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select in_corso from acquisto where codacquisto = '" + this.cod + "'");
			if(rs.next())
			{
				if(rs.getString(1).equals("S")) return true;
				else return false;
			}
			else return false;
		}
		else return false;
	}
	
	public void payOneProduct(Material m, int quan) throws SQLException,ProductNotSaldableException
	{
		if(this.isSave() && this.existInCart(m) && card != null && address != null && quan >= 0 && this.getQuantity(m) >= quan)
		{
				
				if(card.isSave() && this.ssn.equals(card.getSsn()) && address.isSave() && address.getSsn().equals(this.ssn) && address.getCod().equals(this.cod))
				{
					if(card.getSald() - m.getPrice() < 0) throw new ProductNotSaldableException();
					
					Statement st = con.createStatement();
					ResultSet rs;
					String cod;
					Random rand = new Random();
					do
					{
						cod = "";
						for(int i=0; i < 10;i++)
						{
							int num = rand.nextInt();
							if(num < 0) num = (-num) % 10;
							else num = num % 10;
							cod = cod + num;
						}
						rs = st.executeQuery("select * from acquisto where codacquisto = '" + cod + "'");
						
					}while(rs.next());
					SimpleDateFormat sdf = new SimpleDateFormat();
					String date = sdf.format(new java.util.Date());
					sdf.applyPattern("dd-MM-yyyy");
					Address ad = new Address(cod,this.address.getStreet(),this.address.getSsn(),this.address.getCap(),this.address.getCivico());
					ad.saveToDB();
					TotalShop ts = new TotalShop(cod,this.ssn,date,0,ad,this.card);
					
					ts.saveToDB();
					st.executeUpdate("update vengono_posti set quan = quan - " + quan + " where codiceAcquisto = '" + this.cod + "' AND codiceID = '" + m.getId() + "'");
					st.executeUpdate("delete from vengono_posti where codiceAcquisto = '" + this.cod + "' AND codiceID = '" + m.getId() + "' AND quan = 0");
					for(int i = 0; i < quan; i++) ts.addInCartAb(m);
					
					ts.payProducts();
				}
		}
			
	}
	
	
	public void payProducts() throws SQLException, ProductNotSaldableException
	{
		if(this.isSave() && card != null && address != null)
		{
			Statement st = con.createStatement();
			
			if(card.isSave() && this.ssn.equals(card.getSsn()) && address.isSave() && address.getSsn().equals(this.ssn) && address.getCod().equals(this.cod))
			{
				
				card.removeSald(this.pricetot);
				if(card.getSald() >= this.pricetot) 
				{
					//st = con.createStatement();
					
					st.executeUpdate("update acquisto set in_corso = 'S' where codacquisto = '" + this.cod + "'");
				}
			}
			
			
			
			ResultSet rs = st.executeQuery("select codiceID,quan from vengono_posti where codiceAcquisto = '" + this.cod + "'");
			
			while(rs.next())
			{
				Statement st1 = con.createStatement();
				Statement st2 = con.createStatement();
				Statement st3 = con.createStatement();
				ResultSet rs1 = st1.executeQuery("select ssn from inserting where codiceID = '" + rs.getString(1) + "'");
				
				if(rs1.next())
				{
					
					ResultSet rs2 = st2.executeQuery("select * from cliente where ssn = '" + rs1.getString(1) + "'");
					
					ResultSet rs3 = st3.executeQuery("select * from admin where ssn = '" + rs1.getString(1) + "'");
					
					if(rs2.next() && rs3.next())
					{
						
						Admin ad = new Admin(rs2.getString(1),rs2.getString(2),rs2.getString(3),rs2.getInt(4),rs2.getString(5),rs2.getString(6),rs2.getString(7),rs2.getString(8),rs2.getString(9),rs2.getInt(10),rs2.getString(11),rs3.getInt(4),rs3.getBoolean(5),rs3.getDouble(2),rs3.getDouble(3),rs3.getString(6));
						if(ad.isSave())
						{
							for(int i = 0; i < rs.getInt(2); i++)
							{
								
								ad.saldProduct(this.searchMat(rs.getString(1)));
							}
						}
					}
				}
				
			}
			
			
		}
	}
	
	
	
	public void takeCard(Card c) throws SQLException
	{
		if(c != null)
		{
			if(c.isSave() && c.getSsn().equals(this.ssn))
			{
				this.card = c;
				Statement st = con.createStatement();
				st.executeUpdate("update acquisto set codcard = '" + c.getCod() + "' where codacquisto = '" + this.cod + "'");
			}
		}
	}
	
	public void takeAddress(Address a) throws SQLException
	{
		
		if(a != null){
			
			if(a.getSsn().equals(this.ssn) && a.getCod().equals(this.cod))
			{
				
				this.address = a;
				this.address.saveToDB();
				
			}		
		}
	}
	
	public void updatePrice() throws SQLException
	{
		if(this.isSave())
		{
			Statement st = con.createStatement();
			st.executeUpdate("update acquisto set prezzotot = " + this.pricetot + " where codacquisto = '" + this.cod + "'");
		}
	}
	
	public void addPrice(double quote) throws SQLException
	{
		if(quote > 0 && !this.controlSald())
		{
			this.pricetot = this.pricetot + quote;
			this.updatePrice();
		}
	}
	
	public void removePrice(double quote) throws SQLException
	{
		if(this.pricetot - quote >= 0 && !this.controlSald())
		{
			this.pricetot = this.pricetot - quote;
			this.updatePrice();
		}
	}
	
	public void invalidSald() throws SQLException
	{
		if(this.isSave() && this.controlSald())
		{
			Statement st = con.createStatement();
			st.executeUpdate("update acquisto set in_corso = 'V' where codacquisto = '" + this.cod + "'");
			card.addSald(this.pricetot);
		}
	}
	
	public void changeAddress(Address a) throws SQLException
	{
		if(address != null)
		{
			if(address.isSave())
			{
				this.address.deleteFromDB();
				this.takeAddress(a);
				this.address.saveToDB();
			}
		}
	}
	
	public void changeCard(Card c) throws SQLException
	{
		if(card != null)
		{
			if(card.isSave())
			{
				this.takeCard(c);
			}
		}
	}
	
	
	public int getQuantity(Material m) throws SQLException
	{
		if(this.isSave() && this.existInCart(m))
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select quan from vengono_posti where codiceAcquisto = '" + this.getCod() + "' AND codiceID = '" + m.getId() + "'");
			if(rs.next()) return rs.getInt(1);
			else return -1;
		}
		else return -2;
			
	}
	
	public String toString()
	{
		return this.getClass().getName() + " ssn: " + this.ssn + ", date: " + this.date + ", cod: " + this.cod + ", pricetot: " + this.pricetot + ", (" + this.card + "), (" + this.address + ")";
	}
	
	public boolean equals(Object obj)
	{
		if(obj == null) return false;
		if(!(obj instanceof TotalShop)) return false;
		TotalShop ob = (TotalShop) obj;
		return this.ssn.equals(ob.ssn) && this.date.equals(ob.date) && this.cod.equals(ob.cod) && this.pricetot == ob.pricetot && this.card.equals(ob.card) && this.address.equals(ob.address);
	}
	
	public TotalShop clone()
	{
		try
		{
			TotalShop obj = (TotalShop) super.clone();
			obj.card = this.card.clone();
			obj.address = this.address.clone();
			return obj;
		}
		catch(CloneNotSupportedException e)
		{
			return null;
		}
	}
}
