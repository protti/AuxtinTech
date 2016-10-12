package testing;
import material.Processor;
import java.sql.SQLException;

public class TestProcessor {
	public static void main(String[] args) throws SQLException{
		Processor pr = new Processor("73rr5","Acer Aspire","Media",5,"octa core","Casalingo",300,"LGA1155");
		System.out.println(pr);
		System.out.println(pr.isSave());
		
		if(!pr.isSave())
		{
			Processor pr1 = pr.clone();
			System.out.println(pr.equals(pr1));
			
			pr.saveToDB();
			System.out.println(pr.isSave());
		}
		else
		{
			System.out.println(pr.controlAvaylability(6));
			System.out.println(pr.controlAvaylability(2));
			
			pr.removeAvaylability(3);
			
			pr.setDiscount(0.5);
			//pr.deletefromDB();
		}
	}
}
