package configuration;
import java.util.ArrayList;

import material.*;
import java.sql.*;
public class Choose {
	
	private static Connection getConnection()
	{
		Connection con;
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
		return con;
	}
	
	public static ArrayList<MotherBoard> chooseMotherBoard(Configuration conf) throws SQLException
	{
		String query = "select * from schedamadre where disp > 0 ";
		Connection con = getConnection();
		
		if(con == null) return null;
		if(conf.getCas() != null)
		{
			query = query + "AND formato = '" + conf.getCas().getFormat() + "' ";
		}
		if(conf.getDisk() != null)
		{
			query = query + "AND sata >= " + conf.getDisk().getSata() + " ";
		}
		if(conf.getHeat() != null)
		{
			query = query + "AND socket = '" + conf.getHeat().getSocketP() + "' ";
		}
		if(conf.getPower() != null)
		{
			query = query + "AND atx12v >= " + conf.getPower().getAtx12v() + " ";
		}
		if(conf.getProcess() != null)
		{
			query = query + "AND socket = '" + conf.getProcess().getSocket() + "' ";
		}
		if(conf.getRam() != null)
		{
			query = query + "AND maxfrequenzaRAM >= " + conf.getRam().getFrequency() + " ";
		}
		if(conf.getVideoc() != null)
		{
			query = query + "AND PCIe >= " + conf.getVideoc().getPCIe() + " ";
		}
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		ArrayList<MotherBoard> mb = new ArrayList<MotherBoard>();
		while(rs.next())
		{
			MotherBoard pd = new MotherBoard(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(13),rs.getString(7),rs.getString(8),rs.getDouble(9),rs.getDouble(10),rs.getInt(11),rs.getDouble(12),rs.getInt(14));
			mb.add(pd);
		}
		return mb;
	}
	public static ArrayList<Case> chooseCase(Configuration conf) throws SQLException
	{
		Connection con = getConnection();
		if(con == null) return null;
		
		String query = "select * from cas where disp > 0 ";
		if(conf.getMother() != null)
		{
			query = query + "AND formato = '" + conf.getMother().getFormat() + "'";
		}

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		ArrayList<Case> cs = new ArrayList<Case>();
		while(rs.next())
		{
			Case pd = new Case(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(12),rs.getString(7),rs.getDouble(8),rs.getDouble(9),rs.getDouble(10),rs.getString(11));
			cs.add(pd);
		}
		return cs;
	}
	public static ArrayList<HddSsd> chooseDisk(Configuration conf) throws SQLException
	{
		Connection con = getConnection();
		String query = "select * from hddssd where disp > 0 ";
		if(con == null) return null;
		
		if(conf.getMother() != null)
		{
			query = query + "AND sata <= " + conf.getMother().getSata() + " ";
		}

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		ArrayList<HddSsd> hs = new ArrayList<HddSsd>();
		while(rs.next())
		{
			HddSsd pd = new HddSsd(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(9),rs.getDouble(7),rs.getDouble(8));
			hs.add(pd);
		}
		return hs;
	}
	public static ArrayList<Heatsink> chooseHeatsink(Configuration conf) throws SQLException
	{
		Connection con = getConnection();
		if(con == null) return null;
		String query = "select * from dissipatore where disp > 0 ";
		
		if(conf.getMother() != null)
		{
			query = query + "AND socketp = '" + conf.getMother().getSocket() + "' ";
		}
		if(conf.getProcess() != null)
		{
			query = query + "AND socketp = '" + conf.getProcess().getSocket() + "'";
		}
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		ArrayList<Heatsink> hs = new ArrayList<Heatsink>();
		while(rs.next())
		{
			Heatsink pd = new Heatsink(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(11),rs.getString(7),rs.getString(8),rs.getDouble(9),rs.getInt(10));
			hs.add(pd);
		}
		return hs;
	}
	public static ArrayList<PowerPack> choosePowerPack(Configuration conf) throws SQLException
	{
		Connection con = getConnection();
		if(con == null) return null;
		
		String query = "select * from alimentatore where disp > 0 ";
		if(conf.getMother() != null)
		{
			query = query + "AND atx12v <= " + conf.getMother().getAtx12v() + " ";
		}
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		ArrayList<PowerPack> pp = new ArrayList<PowerPack>();
		while(rs.next())
		{
			PowerPack pd = new PowerPack(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(10),rs.getDouble(7),rs.getInt(9),rs.getString(8));
			pp.add(pd);
		}
		return pp;
	}
	public static ArrayList<Processor> chooseProcessor(Configuration conf) throws SQLException
	{
		Connection con = getConnection();
		if(con == null) return null;
		
		String query = "select * from processore where disp > 0 ";
		if(conf.getMother() != null)
		{
			query = query + "AND socket = '" + conf.getMother().getSocket() + "' "; 
		}
		if(conf.getHeat() != null)
		{
			query = query + "AND socket = '" + conf.getHeat().getSocketP() + "'";
		}
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		ArrayList<Processor> pr = new ArrayList<Processor>();
		while(rs.next())
		{
			Processor pd = new Processor(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(8),rs.getString(7));
			pr.add(pd);
		}
		return pr;
	}
	public static ArrayList<Ram> chooseRam(Configuration conf) throws SQLException
	{
		Connection con = getConnection();
		if(con == null) return null;
		String query = "select * from ram where disp > 0 ";
		if(conf.getMother() != null)
		{
			query = query + "AND frequenza <= " + conf.getMother().getMfr() + " ";
		}
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		ArrayList<Ram> rm = new ArrayList<Ram>();
		
		while(rs.next())
		{
			Ram pd = new Ram(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(10),rs.getString(7),rs.getDouble(8),rs.getString(9));
			rm.add(pd);
		}
		
		return rm;
	}
	public static ArrayList<VideoCard> chooseVideoCard(Configuration conf) throws SQLException
	{
		Connection con = getConnection();
		if(con == null) return null;
		String query = "select * from schedavideo where disp > 0 ";
		
		if(conf.getMother() != null)
		{
			query = query + "AND PCIe <= " + conf.getMother().getPCIe() + " ";
		}
		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		ArrayList<VideoCard> vc = new ArrayList<VideoCard>();
		
		while(rs.next())
		{
			VideoCard pd = new VideoCard(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getDouble(11),rs.getInt(7),rs.getInt(12),rs.getDouble(8),rs.getDouble(10),rs.getDouble(9));
			vc.add(pd);
		}
		return vc;
	}
	

}
