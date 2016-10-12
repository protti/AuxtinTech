package testing;
import java.sql.SQLException;

import material.*;
public class TestMotherBoard {

	public static void main(String[] args) throws SQLException{
		// TODO Auto-generated method stub
		MotherBoard mb = new MotherBoard("Ax23b", "AcerPRO-3306", "Bassa", 3, "E ja compralo", "Domestico", 130, "ABC", "LGA1120", 400, 12, 4, 3, 6);
		System.out.println(mb);
		System.out.println(mb.isSave());
		if(!mb.isSave())
		{
			MotherBoard mb1 = mb.clone();
			System.out.println(mb.equals(mb1));
			System.out.println(mb.getAvaylability());
			System.out.println(mb.getBand());
			System.out.println(mb.getDescription());
			System.out.println(mb.getId());
			System.out.println(mb.getName());
			System.out.println(mb.getPrice());
			System.out.println(mb.getUse());
			
			mb.saveToDB();
			System.out.println(mb.isSave());
		}
		else
		{
			System.out.println(mb.controlAvaylability(4));
			System.out.println(mb.controlAvaylability(1));
			
			mb.removeAvaylability(2);
			mb.setDiscount(0.45);
		}
	}

}
