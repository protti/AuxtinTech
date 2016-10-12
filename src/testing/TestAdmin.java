package testing;
import users.*;
import java.sql.SQLException;

import material.Processor;

public class TestAdmin {

	public static void main(String[] args) throws SQLException,ProductNotSaldableException{
		// TODO Auto-generated method stub
		Admin ad = new Admin("TN2465DNT95MS4F7","Donato","Tiano",21,"1995-04-17","milano","3333333333","Napoli","Via Toledo",17,"80066",6,false,0,20);
		System.out.println(ad.isSave());
		if(!ad.isSave())
		{
			//ad.saveToDB();
		}
		else
		{
			//ad.renewAdmin(6);
			System.out.println(ad.controlExpired());
			System.out.println(ad.controlToPay());
			//Processor pr = new Processor("74685","Intel","Media",2,"Buon processore","Gamer",200,"LGA1150");
			//ad.insertProduct(pr);
			//ad.removeProduct("74685", 1);
			//ad.saldProduct(ad.searchMat("74685"));
			//Card cr = new Card("00nas654mh",ad.getSsn(),"Visa",10000);
			//ad.payInterest(cr);
			//ad.deleteFromDB();
		}
	}

}
