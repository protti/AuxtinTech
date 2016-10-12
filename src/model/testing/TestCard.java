package model.testing;
import model.users.Card;
import model.users.ProductNotSaldableException;

import java.sql.SQLException;

public class TestCard {
	public static void main(String[] args) throws SQLException,ProductNotSaldableException{
		Card cr = new Card("1112223330","DVL178RBRT95ML9A","PostePay",5000);
		System.out.println(cr);
		System.out.println(cr.isSave());
		
		if(!cr.isSave())
		{
			Card cr1 = cr.clone();
			System.out.println(cr.equals(cr1));
			
			cr.saveToDB();
			System.out.println(cr.isSave());
		}
		else
		{
			//cr.deleteFromDB();
			//cr.removeSald(5001);
			cr.removeSald(400);
			
			System.out.println(cr.getCod());
			System.out.println(cr.getSald());
			System.out.println(cr.getSsn());
			System.out.println(cr.getType());
		}
	}
}
