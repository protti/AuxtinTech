package testing;
import users.*;
import java.sql.SQLException;

import material.HddSsd;
import material.Processor;
import shopping.TotalShop;

public class TestUsers {
	public static void main(String[] args) throws SQLException,ProductNotSaldableException {
		//Client cl = new Client("TN2465DNT95MS4F3","Donato","Tiano");
		Client cl = new Client("TN2465DNT95MS4F7","Donato","Tiano",21,"1995-04-17","milano","3333333333","Napoli","Via Toledo",17,"80066");
		
		System.out.println(cl);
		System.out.println(cl.isSave());
		if(cl.isSave())
		{
			//cl.deleteFromDB();
			System.out.println(cl.getMyCard());
			System.out.println(cl.getName());
			System.out.println(cl.getSurname());
			
			Client cl1 = cl.clone();
			System.out.println(cl1);
			System.out.println(cl.equals(cl1));
			System.out.println(cl.controlMyCart());
			
			System.out.println(cl.getTS());
			TotalShop ts = cl.getTS();
			HddSsd hs = new HddSsd("49yt0","Kingston","collo",3,"","PC",100,7.3,1.5);
			//HddSsd hs = new HddSsd("49tt0","Kingston","collo",3,"","PC",100,7.3,1.5);
			//System.out.println(ts.existInCart(hs));
			//ts.addInCart(hs);
			Processor ps = new Processor("76up8","Acer Aspire","Bassissima",5,"Prestazioni basse","Casalingo",500,"LGA123");
			//ts.addInCart(ps);
			ts.removeFromCart(ps);
			System.out.println(ts.searchMat("22fc9"));
			System.out.println(ts.searchMat("13er5"));
			System.out.println(ts.searchMat("30iu2"));
			System.out.println(ts.searchMat("234o9"));
			System.out.println(ts.searchMat("76re3"));
			System.out.println(ts.searchMat("54678"));
			System.out.println(ts.searchMat("02tg2"));
			System.out.println(ts.searchMat("Ab110ll"));
			System.out.println(ts.searchMat("22fc9tfgjhgh"));
			
			ts.deleteCart();
		}
		else
		{
			//cl.saveToDB();
			
		}
	}
}
