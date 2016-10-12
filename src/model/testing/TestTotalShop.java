package model.testing;
import model.shopping.TotalShop;
import java.sql.*;
import model.users.*;
public class TestTotalShop {
	public static void main(String[] args) throws SQLException,ProductNotSaldableException{
		
		Address add = new Address("777","Viale delle arachidi","ASER432MPO09IUTY","87753",13);
		Card cr = new Card("223344","ASER432MPO09IUTY","MasterCard",10000);
		TotalShop ts = new TotalShop("777","ASER432MPO09IUTY","2016-08-01",350,add,cr);
		
		System.out.println(ts);
		System.out.println(ts.isSave());
		
		if(!ts.isSave())
		{
			System.out.println(ts.controlSald());
			ts.takeCard(null);
			System.out.println(ts.getCard());
			/*Card c = new Card("223344","ASER432MPO09IUTY","MasterCard",1000);
			ts.takeCard(c);
			System.out.println(ts.getCard());*/
			
			ts.takeAddress(null);
			System.out.println(ts.getAddress());
			/*Address a = new Address("777","Viale delle Arachidi","ASER432MPO09IUTY","87753",130);
			ts.takeAddress(a);
			System.out.println(ts.getAddress());*/
			
			ts.saveToDB();
			System.out.println(ts.isSave());
		}
		else
		{
			ts.addPrice(50);
			ts.addPrice(-2);
			ts.removePrice(50);
			ts.removePrice(400);
			
			Address a = new Address("777","Viale delle Arachidi","ASER432MPO09IUTY","87753",130);
			ts.changeAddress(a);
			
			ts.payProducts();
			
			System.out.println(ts);
			System.out.println(ts.controlSald());
			
			ts.invalidSald();
			
			//ts.deleteFromDB();
		}
	}
}
