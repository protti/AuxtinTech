package material;

import java.sql.*;

public class MotherBoard extends Material {
	
	private String format, socket;
	private double mfR, PCIe, sata;
	private int port, atx12v;
	private Connection con;
	public MotherBoard(String id, String name, String band, int disp, String description, String use, double price, String format, String socket, double mfR, double PCIe, int port, double sata, int atx12v) 
	{
		super(id, name, band, disp, description, use, price);
		this.format = format;
		this.socket = socket;
		this.mfR = mfR;
		this.PCIe = PCIe;
		this.sata = sata;
		this.port = port;
		this.atx12v = atx12v;
		
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
			st.executeUpdate("insert into schedamadre values('" + super.getId() + "', '" + super.getName() + "', '" + super.getBand() + "', '" + super.getAvaylability() + "','" + super.getDescription() + "', '" + super.getUse() + "', '" + this.format + "', '" + this.socket + "', " + this.mfR + ", " + this.PCIe + ", " + this.port + ", " + this.sata + ", " + super.getPrice() + ", " + this.atx12v + ")");
		}

	}

	@Override
	public void updateDisp() throws SQLException {
		if(this.isSave())
		{
			Statement st = con.createStatement();
			st.executeUpdate("update schedamadre set disp = " + super.getAvaylability() + " where codiceid = '" + super.getId() + "'");
		}

	}

	@Override
	public boolean isSave() throws SQLException {
		
		if(con != null)
		{
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from schedamadre where codiceid = '" + super.getId() + "'");
			return rs.next();
		}
		else return false;
	}

	@Override
	public void updatePrice() throws SQLException {
		Statement st = con.createStatement();
		st.executeUpdate("update schedamadre set prezzo = " + super.getPrice() + " where codiceid = '" + super.getId() + "'");
	}
	
	public void deletefromDB() throws SQLException {
		if(this.isSave())
		{
			Statement st = con.createStatement();
			st.executeUpdate("delete from schedamadre where codiceid = '" + super.getId() + "'");
		}
		
	}
	
	
	public double getMfr()
	{
		return this.mfR;
	}
	
	public double getPCIe()
	{
		return this.PCIe;
	}
	
	public double getSata()
	{
		return this.sata;
	}
	
	public int getPort()
	{
		return this.port;
	}
	
	public int getAtx12v()
	{
		return this.atx12v;
	}
	
	public String getSocket()
	{
		return this.socket;
	}
	
	public String getFormat()
	{
		return this.format;
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
		return "schedamadre";
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
	
	public void setDiscount(double quote) throws SQLException
	{
		super.setDiscount(quote);
		this.updatePrice();
	}
	
	public void addAvaylability(int numProduct) throws SQLException
	{
		super.addAvaylability(numProduct);
		this.updateDisp();
	}
	
	public String toString()
	{
		return super.toString() + ", format: " + this.format + ", socket: " + this.socket + ", mfR: " + this.mfR + ", PCIe: " + this.PCIe + ", port: " + this.port + ", sata: " + this.sata + ", atx12v: " + this.atx12v;
	}
	
	public boolean equals(Object obj)
	{
		if(!super.equals(obj)) return false;
		if(!(obj instanceof MotherBoard)) return false;
		MotherBoard ob = (MotherBoard) obj;
		return this.format.equals(ob.format) && this.socket.equals(ob.socket) && this.mfR == ob.mfR && this.PCIe == ob.PCIe && this.port == ob.port && this.sata == ob.sata && this.atx12v == ob.atx12v;
	}
	
	public MotherBoard clone()
	{
		MotherBoard obj = (MotherBoard) super.clone();
		return obj;
	}
}
