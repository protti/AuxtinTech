package model.feedback;
import java.sql.*;
public class Feedback implements Cloneable{
	
	private String ussn, assn, message;
	private Long id;
	private Connection con;
	
	public Feedback(String ussn,String message, String assn, Long id)
	{
		this.ussn = ussn;
		this.assn = assn;
		this.message = message;
		if(id == null) this.id = -1L;
		else this.id = id;
		
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
			ResultSet rs = st.executeQuery("select * from feedback where id = " + this.id + " AND assn = '" + this.assn + "' AND ussn = '" + this.ussn + "' AND messagge = '" + this.message + "'");
			return rs.next();
		}
		else return false;
	}
	
	public void saveToDB() throws SQLException
	{
		if(!this.isSave() && con != null)
		{
			Statement st = con.createStatement();
			st.executeUpdate("insert into feedback values(0,'" + this.ussn + "','" + this.assn + "','" + this.message + "')");
			ResultSet rs = st.executeQuery("select max(id) from feedback where ussn = '" + this.ussn + "' AND assn = '" + this.assn + "' AND messagge = '" + this.message + "'");
			if(rs.next())
			{
				this.id = rs.getLong(1);
			}
		}
	}
	
	public void deleteFromDB() throws SQLException
	{
		if(this.isSave())
		{
			Statement st = con.createStatement();
			st.executeUpdate("delete from feedback where id = " + this.id);
		}
	}
	
	public void modifyMessage(String newMessage) throws SQLException
	{
		if(this.isSave())
		{
			Statement st = con.createStatement();
			this.message = newMessage;
			st.executeUpdate("update feedback set messagge = '" + this.message + "' where id = " + this.id);
		}
	}
	
	public Long getId()
	{
		return this.id;
	}
	
	public String getMessage()
	{
		return this.message;
	}
	
	public String getAssn()
	{
		return this.assn;
	}
	
	public String getUssn()
	{
		return this.ussn;
	}
	
	
	public String toString()
	{
		return this.getClass().getName() + " id: " + this.id + ", ussn: " + this.ussn + ", assn: " + this.assn + ", message: " + this.message;
	}
	
	public boolean equals(Object obj)
	{
		if(obj == null) return false;
		if(!(obj instanceof Feedback)) return false;
		Feedback ob = (Feedback) obj;
		return ob.id == this.id && ob.assn.equals(this.assn) && ob.ussn.equals(this.ussn) && ob.message.equals(this.message);
	}
	
	public Feedback clone()
	{
		try
		{
			Feedback cloned = (Feedback) super.clone();
			return cloned;
		}
		catch(CloneNotSupportedException e)
		{
			return null;
		}
	}
}
