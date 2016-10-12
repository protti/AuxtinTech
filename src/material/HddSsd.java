package material;
import java.sql.*;

public class HddSsd extends Material {

	private double inch, sata;
	private Connection con;
	
	public HddSsd(String id, String name, String band, int disp, String description, String use, double price, double inch, double sata)
	{
		super(id, name, band, disp, description, use, price);
		this.inch = inch;
		this.sata = sata;
		
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
	public void saveToDB() throws SQLException{
		// TODO Auto-generated method stub
		if(con != null)
		{
			Statement st = con.createStatement();
			
			if(!this.isSave())
			{
				st.executeUpdate("insert into hddssd values('" + super.getId() + "', '" + super.getName() + "', '" + super.getBand() + "', " + super.getAvaylability() + ", '" + super.getDescription() + "', '" + super.getUse() + "', " + this.inch + ", " + this.sata + ", " + super.getPrice() + " )");
			}
		}
	}
	
	public boolean isSave() throws SQLException
	{
		if(con != null)
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from hddssd where codiceid = '" + super.getId() + "'");
			return rs.next();
		}
		else return false;
	}
	
	public void updateDisp() throws SQLException
	{
		if(con != null)
		{
			Statement st = con.createStatement();
			if(this.isSave())
			{
				st.executeUpdate("update hddssd set disp = " + super.getAvaylability() + " where codiceid = '" + super.getId() + "'");
			}
		}
	}
	
	public void deletefromDB() throws SQLException {
		if(this.isSave())
		{
			Statement st = con.createStatement();
			st.executeUpdate("delete from hddssd where codiceid = '" + super.getId() + "'");
		}
		
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
	
	public double getInch()
	{
		return this.inch;
	}
	
	public double getSata()
	{
		return this.sata;
	}
	
	
	
	public void updatePrice() throws SQLException
	{
		if(con != null && this.isSave())
		{
			Statement st = con.createStatement();
			st.executeUpdate("update hddssd set prezzo = " + super.getPrice() + " where codiceid = '" + super.getId() + "'");
		}
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
		return super.toString() + ", inch: " + this.inch + ", sata: " + this.sata;
	}
	
	public boolean equals(Object obj)
	{
		if(!super.equals(obj)) return false;
		if(!(obj instanceof HddSsd)) return false;
		HddSsd ob = (HddSsd) obj;
		return super.equals(obj) && this.inch == ob.inch && this.sata == ob.sata;
	}
	
	public HddSsd clone()
	{
		HddSsd obj = (HddSsd) super.clone();
		return obj;
	}

	@Override
	public String myTable() {
		return "hddssd";
	}
}
