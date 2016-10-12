package controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.lang.*;
public class Connections {

	public static Connection getConnection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:3306/ecommerce";
			Connection con = DriverManager.getConnection(url,"root","bikeralex");
			return con;
		} catch(SQLException e) {
			
			return null;
		} catch (InstantiationException e) {
			
			return null;
		} catch (IllegalAccessException e) {
			
			return null;
		} catch (ClassNotFoundException e) {
			
			return null;
		}
	}
}
