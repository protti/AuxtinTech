package model.users;
import java.sql.*;
import java.util.Random;
import java.text.SimpleDateFormat;

import model.feedback.Feedback;
import model.shopping.TotalShop;
//import material.*;

public class Client implements Cloneable{
	
	private String ssn, name, surname, birthdate, password, number, city, street, cap;
	private int age, civic;
	private Connection con;
	
	public Client(String ssn, String name, String surname, int age, String birthdate, String password, String number, String city, String street, int civic, String cap)
	{
		this.ssn = ssn;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.birthdate = birthdate;
		this.password = password;
		this.number = number;
		this.street = street;
		this.city = city;
		this.cap = cap;
		this.civic = civic;
		
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
	
	public String getPassword()
	{
		return this.password;
	}
	
	public String getSsn()
	{
		return this.ssn;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getSurname()
	{
		return this.surname;
	}
	
	public int getAge()
	{
		return this.age;
	}
	
	public String getBirthdate()
	{
		return this.birthdate;
	}
	
	public String getNumber()
	{
		return this.number;
	}
	
	public String getCity()
	{
		return this.city;
	}
	
	public String getStreet()
	{
		return this.street;
	}
	
	public String getCap()
	{
		return this.cap;
	}
	
	public int getCivic()
	{
		return this.civic;
	}
	
	public int getId() throws SQLException
	{
		if(this.isSave())
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select id from cliente where ssn = '" + this.ssn + "'");
			if(rs.next()) return rs.getInt(1);
			else return -1;
		}
		else return -2;
	}
	
	public boolean isSave() throws SQLException
	{
		if(con != null)
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from cliente where ssn = '" + this.ssn + "'");
			return rs.next();
		}
		else return false;
	}
	
	public void saveToDB() throws SQLException
	{
		if(!this.isSave() && con != null)
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select max(id) from cliente where nome = '" + this.name + "' AND cognome = '" + this.surname + "'");
			int id;
			if(rs.next()) id = rs.getInt(1) + 1;
			else id = 1;
				
			st.executeUpdate("insert into cliente values('" + this.ssn +"','" + this.name + "','" + this.surname + "'," + this.age + ",'" + this.birthdate + "','" + this.password + "','" + this.number + "','" + this.city + "','" + this.street + "'," + this.civic + ",'" + this.cap + "'," + id + ")");
		}
	}
	
	public void deleteFromDB() throws SQLException
	{
		if(this.isSave())
		{
			Statement st = con.createStatement();
			st.executeUpdate("delete from cliente where ssn = '" + this.ssn + "'");
		}
	}
	
	public String getMyCard() throws SQLException
	{
		String myCard = "You aren't logged in server in this moment";
		if(this.isSave() && this.controlMyCart())
		{
			myCard = "Sorry, you don't have any card";
			boolean control = false;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select codcard,tipo,importo from carta where ssn = '" + this.ssn + "'");
			
			while(rs.next())
			{
				if(control == false)
				{
					myCard = "";
					control = true;
				}
				
				myCard = myCard + rs.getString(1) + " " + rs.getString(2) + " " +rs.getString(3) + "\n"; 
			}
		}
		return myCard;
	}
	
	public boolean controlMyCart() throws SQLException
	{
		if(this.isSave())
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from acquisto where ssn = '" + this.ssn + "' AND in_corso = 'V'");
			return rs.next();
		}
		return false;
	}
	
	public void makeCart(TotalShop ts) throws SQLException
	{
		if(!this.controlMyCart() && this.isSave())
		{
			ts.saveToDB();
		}
	}
	
	public TotalShop getTS() throws SQLException
	{
		if(this.isSave())
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from acquisto where ssn = '" + this.ssn + "' AND in_corso = 'V'");
			if(rs.next())
			{
				TotalShop ts = new TotalShop(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDouble(4),null,null);
				if(ts.verifyCard())
				{
					rs = st.executeQuery("select * from carta where codcard = (select codcard from acquisto where codacquisto = '" + ts.getCod() + "')");
					if(rs.next())
					{
						Card cr = new Card(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDouble(4));
						ts.takeCard(cr);
					}
				}
				rs = st.executeQuery("select * from indirizzo where codacquisto = '" + ts.getCod() + "'");
				if(rs.next())
				{
					Address add = new Address(rs.getString(5),rs.getString(1),rs.getString(4),rs.getString(3),rs.getInt(2));
					ts.takeAddress(add);
				}
				return ts;
			}
			else
			{
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
				sdf.applyPattern("yyyy-MM-dd");
				TotalShop ts = new TotalShop(cod,this.ssn,date,0,null,null);
				ts.saveToDB();
				return ts;
			}
		}
		else return null;
	}
	
	public boolean controlPassword(String password) throws SQLException
	{
		if(this.isSave())
		{
			return this.password.equals(password);
		}
		else return false;
	}
	
	public void makeFeedback(String message, String assn) throws SQLException
	{
		if(this.isSave())
		{
			Feedback fb = new Feedback(this.ssn,message,assn,null);
			fb.saveToDB();
		}
	}
	
	public void removeFeedback(Long id) throws SQLException
	{
		if(this.isSave())
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from feedback where id = " + id);
			if(rs.next())
			{
				if(this.ssn == rs.getString(2) || this.ssn == rs.getString(3))
				{
					Feedback fb = new Feedback(rs.getString(2),rs.getString(4),rs.getString(3),rs.getLong(1));
					fb.deleteFromDB();
				}
			}
		}
	}
	
	public boolean controlAdmin() throws SQLException
	{
		if(this.isSave())
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from admin where ssn = '" + this.ssn + "' AND scad = 0");
			return rs.next();
		}
		else return false;
	}
	
	public Admin getAdmin() throws SQLException
	{
		if(this.isSave() && this.controlAdmin())
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from admin where ssn = '" + this.ssn + "' AND scad = 0");
			//String ssn, String name, String surname, int age, String birthdate, String password, String number, String city, String street, int civic, String cap, int type_abb, boolean expired, double to_pay, double paid, String dataSub
			rs.next();
			return new Admin(this.ssn,this.name,this.surname,this.age,this.birthdate,this.password,this.number,this.city,this.street,this.civic,this.cap,rs.getInt(4),rs.getBoolean(5),rs.getDouble(2),rs.getDouble(3),rs.getString(6));
					
		
		}
		else return null;
	}
	
	
	public String toString()
	{
		return this.getClass().getName() +" ssn: " + this.ssn + ", name: " + this.name + ", surname: " + this.surname + ", age: " + this.age + ", birthdate: " + this.birthdate + ", password: " + this.password + ", number: " + this.number + ", city: " + this.city + ", street: " + this.street + ", civic: " + this.civic + ", cap: " + this.cap; 
	}
	
	public boolean equals(Object obj)
	{
		if(obj == null) return false;
		if(!(obj instanceof Client)) return false;
		Client ob = (Client) obj;
		return ob.ssn.equals(this.ssn) && ob.name.equals(this.name) && ob.surname.equals(this.surname) && ob.birthdate.equals(this.birthdate) && ob.password.equals(this.password) && ob.number.equals(this.number) && this.age == ob.age && ob.city.equals(this.city) && ob.street.equals(this.street) && ob.cap.equals(this.cap) && this.civic == ob.civic;
	}
	
	public Client clone()
	{
		try
		{
			Client obj = (Client) super.clone();
			return obj;
		}
		catch(CloneNotSupportedException e)
		{
			return null;
		}
	}
	
}
