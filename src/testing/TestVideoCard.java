package testing;
import material.VideoCard;
import java.sql.SQLException;

public class TestVideoCard {

	public static void main(String[] args) throws SQLException{
		// TODO Auto-generated method stub
		VideoCard vc = new VideoCard("Ab110ll","Nvidia730","Media",4,"Quasi HD","Domestico",300,5,10,10,5,6);
		System.out.println(vc);
		System.out.println(vc.isSave());
		
		if(!vc.isSave())
		{
			VideoCard vc1 = vc.clone();
			System.out.println(vc.equals(vc1));
			
			vc.saveToDB();
			System.out.println(vc.isSave());
		}
		else
		{
			System.out.println(vc.controlAvaylability(5));
			System.out.println(vc.controlAvaylability(2));
			
			vc.removeAvaylability(2);
			vc.setDiscount(0.2);
			
			//vc.deletefromDB();
		}
	}

}
