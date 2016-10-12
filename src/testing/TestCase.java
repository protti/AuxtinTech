package testing;
import material.Case;
import java.sql.SQLException;

public class TestCase {

	public static void main(String[] args) throws SQLException{
		// TODO Auto-generated method stub
		Case cs = new Case("15ty8","Z11RX","Normal",5,"Decorativo","Gamer",90,"ABC",6,10,12,"No Water");
		System.out.println(cs);
		System.out.println(cs.isSave());
		
		if(!cs.isSave())
		{
			Case cs1 = cs.clone();
			System.out.println(cs.equals(cs1));
			
			cs.saveToDB();
			System.out.println(cs.isSave());
		}
		else
		{
			System.out.println(cs.controlAvaylability(6));
			System.out.println(cs.controlAvaylability(2));
			cs.removeAvaylability(2);
			System.out.println(cs.isSave());
			cs.setDiscount(0.5);
		}
	}

}
