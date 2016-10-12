package model.testing;

import model.feedback.Feedback;
import java.sql.SQLException;
public class TestFeedback {

	public static void main(String[] args) throws SQLException{
		// TODO Auto-generated method stub
		Feedback fb = new Feedback("NG4256LSS94ND4R7","Mi hai venduto un prodotto scadente","TN2465DNT95MS4F7",3L);
		System.out.println(fb.isSave());
		
		if(!fb.isSave())
		{
			fb.saveToDB();
		}
		else
		{
			//fb.modifyMessage("Mi hai venduto una ciofeca");
			//fb.deleteFromDB();
		}
	}

}
