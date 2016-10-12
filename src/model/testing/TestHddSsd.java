package model.testing;
import java.sql.SQLException;

import model.material.*;

public class TestHddSsd {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		HddSsd hs = new HddSsd("234o9", "Toshiba", "Normal", 2, "Good and modern", "Gamer", 100.50, 6.5, 3);
		if(!hs.isSave())
		{
			try {
				System.out.println(hs.isSave());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Warning!!!");
			}
		
			System.out.println(hs.getBand());
			System.out.println(hs.getAvaylability());
			System.out.println(hs.getId());
			System.out.println(hs.getName());
			System.out.println(hs.getDescription());
			System.out.println(hs.getUse());
			System.out.println(hs.getPrice());
		
			System.out.println(hs.controlAvaylability(1));
			
			hs.saveToDB();
		}
		else
		{
			System.out.println("Welcome hddssd!!");
			hs.removeAvaylability(1);
			hs.setDiscount(0.3);
			HddSsd hs1 = hs.clone();
			System.out.println(hs.equals(hs1));
			System.out.println(hs.toString());
		}
	}

}
