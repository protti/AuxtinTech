package model.testing;
import model.users.Address;
import java.sql.SQLException;

public class TestAddress {
	public static void main(String[] args) throws SQLException{
		Address add = new Address("884","Viale delle arachidi","DVL178RBRT95ML9A","80673",13);
		System.out.println(add);
		System.out.println(add.isSave());
		
		if(!add.isSave())
		{
			Address add1 = add.clone();
			System.out.println(add.equals(add1));
			
			add.saveToDB();
			System.out.println(add.isSave());
		}
		else
		{
			add.deleteFromDB();
			System.out.println(add.isSave());
		}
	}
}
