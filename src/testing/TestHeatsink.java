package testing;
import material.Heatsink;
import java.sql.SQLException;

public class TestHeatsink {

	public static void main(String[] args) throws SQLException{
		// TODO Auto-generated method stub
		Heatsink hs = new Heatsink("32oo4", "AMD", "Alta", 6, "Raffreddamento ad acqua", "Lavorativo", 250, "acqua", "LGA1150", 9, 20);
		System.out.println(hs);
		System.out.println(hs.isSave());
		if(!hs.isSave())
		{
			Heatsink hs1 = hs.clone();
			System.out.println(hs.equals(hs1));
			
			hs.saveToDB();
			System.out.println(hs.isSave());
		}
		else
		{
			System.out.println(hs.controlAvaylability(7));
			System.out.println(hs.controlAvaylability(2));
			hs.removeAvaylability(2);
			hs.setDiscount(0.3);
		}
	}

}
