package model.testing;
import model.material.Ram;
import java.sql.SQLException;

public class TestRam {

	public static void main(String[] args) throws SQLException{
		// TODO Auto-generated method stub
		Ram r = new Ram("abcde","Kingston","Alta",3,"Da associare ad un buon dissipatore","Lavorativo",400,"DDR4",1000,"acqua");
		System.out.println(r);
		System.out.println(r.isSave());
		
		if(!r.isSave())
		{
			Ram r1 = r.clone();
			System.out.println(r.equals(r1));
			
			r.saveToDB();
			System.out.println(r.isSave());
		}
		else
		{
			System.out.println(r.controlAvaylability(5));
			System.out.println(r.controlAvaylability(2));
			
			r.removeAvaylability(2);
			r.setDiscount(0.4);
			
			//r.deletefromDB();
		}
	}

}
